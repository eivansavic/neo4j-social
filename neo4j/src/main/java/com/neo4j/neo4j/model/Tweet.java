package com.neo4j.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@NodeEntity
public class Tweet {

    @Id @GeneratedValue
    private Long graphId;

    @Property(name="favorites")
    private Long favorites;

    @Index(unique = true)
    @Property(name="id")
    private Long id;

    @Property(name="text")
    private String text;

    @Property(name="created")
    private ZonedDateTime created;

    @Relationship(type="LINKED", direction = Relationship.INCOMING)
    private Iterable<Link> links;

    @Relationship(type="MENTIONED", direction = Relationship.INCOMING)
    private Iterable<Link> users;

    private User postedBy;

    private Tweet repliedTo;

    private Tweet retweetedTo;

    @Relationship(type="TAGGED", direction = Relationship.INCOMING)
    private Iterable<Tag> tags;
}
