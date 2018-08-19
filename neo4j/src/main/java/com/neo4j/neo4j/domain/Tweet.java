package com.neo4j.neo4j.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@NoArgsConstructor
@Data
@NodeEntity
public class Tweet {

    @Id
    @GeneratedValue
    private Long graphId;

    @Property(name = "favorites")
    private Long favorites;

    @Index(unique = true)
    @Property(name = "id")
    private Long id;

    @Property(name = "text")
    private String text;

    @Property(name = "created")
    private String created;

    @Relationship(type = "LINKED", direction = Relationship.INCOMING)
    private Iterable<Link> links;

    @Relationship(type = "MENTIONED", direction = Relationship.INCOMING)
    private Iterable<Link> users;

    private User postedBy;

    private Tweet repliedTo;

    private Tweet retweetedTo;

    @Relationship(type = "TAGGED", direction = Relationship.INCOMING)
    private Iterable<Tag> tags;
}
