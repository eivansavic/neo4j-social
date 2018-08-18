package com.neo4j.neo4j.controller;

import com.neo4j.neo4j.controller.dto.PostTweetDto;
import com.neo4j.neo4j.model.Tweet;
import com.neo4j.neo4j.service.use_case.PostTweet;
import com.neo4j.neo4j.service.use_case.GetTweets;
import com.neo4j.neo4j.service.use_case.value.CreateTweetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/tweets")
public class TweetController {

    private final GetTweets getTweets;
    private final PostTweet postTweet;

    @Autowired
    public TweetController(GetTweets getTweets, PostTweet postTweet) {
        this.getTweets = getTweets;
        this.postTweet = postTweet;
    }

    @GetMapping
    public ResponseEntity<Iterable<Tweet>> getTweets() {
        return new ResponseEntity<>(getTweets.getAllTweets(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postTweet(@RequestBody @Valid PostTweetDto request) {
        postTweet.postTweet(new CreateTweetInfo(request.getText(), request.getTag()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
