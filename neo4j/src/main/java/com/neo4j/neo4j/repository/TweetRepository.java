package com.neo4j.neo4j.repository;

import com.neo4j.neo4j.domain.Tweet;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends Neo4jRepository<Tweet, Long> {

}
