package com.neo4j.neo4j.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@NoArgsConstructor
@Data
@NodeEntity
public class User {

    @Id
    @GeneratedValue
    private Long graphId;

    @Property(name = "followers")
    private Long followers;

    @Index(unique = true)
    @Property(name = "screen_name")
    private String screenName;

    @Property(name = "following")
    private Long following;

    @Property(name = "name")
    private String name;

    @Property(name = "statuses")
    private Long statuses;

    @Property(name = "profile_image_url")
    private String profileImageUrl;

    @Property(name = "location")
    private String location;
}
