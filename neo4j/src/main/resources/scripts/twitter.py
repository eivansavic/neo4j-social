import os
import time
import requests
import urllib
from neo4j.v1 import GraphDatabase, basic_auth

neo4jUrl = os.environ.get('NEO4J_URL',"bolt://localhost")
neo4jUser = os.environ.get('NEO4J_USER',"neo4j")
neo4jPass = os.environ.get('NEO4J_PASSWORD',"root")
bearerToken = os.environ.get('TWITTER_BEARER',"AAAAAAAAAAAAAAAAAAAAAEH04QAAAAAA6RFhHaG2La%2B9JqqT7knBdDXOj6o%3DphvxAFrcGeJlPG0ycoXrrwkJ0Pqx654yubqnSx4XdDAkp55b5x")
    
driver = GraphDatabase.driver(neo4jUrl, auth=basic_auth(neo4jUser, neo4jPass))

session = driver.session()

# Add uniqueness constraints.
session.run( "CREATE CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE;")
session.run( "CREATE CONSTRAINT ON (u:User) ASSERT u.screen_name IS UNIQUE;")
session.run( "CREATE CONSTRAINT ON (h:Tag) ASSERT h.name IS UNIQUE;")
session.run( "CREATE CONSTRAINT ON (l:Link) ASSERT l.url IS UNIQUE;")

# Build query.
importQuery = """
UNWIND {tweets} AS t
WITH t
ORDER BY t.id
WITH t,
     t.entities AS e,
     t.user AS u,
     t.retweeted_status AS retweet
MERGE (tweet:Tweet {id:t.id})
SET tweet:Content, tweet.text = t.text,
    tweet.created = t.created_at,
    tweet.favorites = t.favorite_count
MERGE (user:User {screen_name:u.screen_name})
SET user.name = u.name,
    user.location = u.location,
    user.followers = u.followers_count,
    user.following = u.friends_count,
    user.statuses = u.statuses_count,
    user.profile_image_url = u.profile_image_url
MERGE (user)-[:POSTED]->(tweet)
FOREACH (h IN e.hashtags |
  MERGE (tag:Tag {name:LOWER(h.text)})
  MERGE (tag)<-[:TAGGED]-(tweet)
)
FOREACH (u IN e.urls |
  MERGE (url:Link {url:u.expanded_url})
  MERGE (tweet)-[:LINKED]->(url)
)
FOREACH (m IN e.user_mentions |
  MERGE (mentioned:User {screen_name:m.screen_name})
  ON CREATE SET mentioned.name = m.name
  MERGE (tweet)-[:MENTIONED]->(mentioned)
)
FOREACH (r IN [r IN [t.in_reply_to_status_id] WHERE r IS NOT NULL] |
  MERGE (reply_tweet:Tweet {id:r})
  MERGE (tweet)-[:REPLIED_TO]->(reply_tweet)
)
FOREACH (retweet_id IN [x IN [retweet.id] WHERE x IS NOT NULL] |
    MERGE (retweet_tweet:Tweet {id:retweet_id})
    MERGE (tweet)-[:RETWEETED]->(retweet_tweet)
)
"""

q = urllib.quote_plus(os.environ.get("TWITTER_SEARCH",'neo4j OR java OR python OR golang OR #java OR #spring OR #neo4j'))

count = 1000
result_type = "recent"
lang = "en"

apiUrl = "https://api.twitter.com/1.1/search/tweets.json?q=%s&count=%s&result_type=%s&lang=%s" % (q, count, result_type, lang)

response = requests.get(apiUrl, headers = {"accept":"application/json", "Authorization":"Bearer " + bearerToken})

if response.status_code != 200:
    print("%s : %s" % (response.status_code, response.text))

json = response.json()
meta = json["search_metadata"]
# print(meta)

tweets = json.get("statuses",[])    

if len(tweets) > 0:
    result = session.run(importQuery,{"tweets":tweets})
    print(result.consume().counters)

driver.close()