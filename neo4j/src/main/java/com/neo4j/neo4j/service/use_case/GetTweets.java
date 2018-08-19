package com.neo4j.neo4j.service.use_case;

import com.neo4j.neo4j.domain.Tweet;
import com.neo4j.neo4j.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTweets {

    private final TweetRepository tweetRepository;

    @Autowired
    public GetTweets(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Iterable<Tweet> getAllTweets() {
        Iterable<Tweet> tweets = tweetRepository.findAll();

        return tweets;
    }
}
