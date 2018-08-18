package com.neo4j.neo4j.service.use_case;

import com.neo4j.neo4j.model.Tweet;
import com.neo4j.neo4j.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
