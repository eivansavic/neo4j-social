package com.neo4j.neo4j.service.use_case;

import com.neo4j.neo4j.domain.Tag;
import com.neo4j.neo4j.domain.Tweet;
import com.neo4j.neo4j.repository.TagRepository;
import com.neo4j.neo4j.repository.TweetRepository;
import com.neo4j.neo4j.service.use_case.value.CreateTweetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class PostTweet {

    private final TweetRepository tweetRepository;
    private final TagRepository tagRepository;

    @Autowired
    public PostTweet(TweetRepository tweetRepository, TagRepository tagRepository) {
        this.tweetRepository = tweetRepository;
        this.tagRepository = tagRepository;
    }

    public void postTweet(CreateTweetInfo createTweetInfo) {
        List<Tag> tags = null;
        if (createTweetInfo.getTag() != null) {
            Tag tag = createTag(createTweetInfo.getTag());
            tags = Collections.singletonList(tag);
        }

        createTweet(createTweetInfo.getText(), tags);
    }

    private Tweet createTweet(final String text, final List<Tag> tags) {
        final Random random = new Random();
        Tweet tweet = new Tweet();
        tweet.setId(random.nextLong() + 1);
        tweet.setText(text);
        tweet.setCreated(ZonedDateTime.now().toString());
        tweet.setTags(tags);

        return tweetRepository.save(tweet);
    }

    private Tag createTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);

        return tagRepository.save(tag);
    }
}
