package com.neo4j.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@AllArgsConstructor
@NoArgsConstructor
@Data
@NodeEntity
public class User {

    @Id @GeneratedValue
    private Long graphId;

    @Property(name="followers")
    private Long followers;

    @Property(name="screen_name")
    private String screenName;

    @Property(name="following")
    private Long following;

    @Property(name="name")
    private String name;

    @Property(name="statuses")
    private Long statuses;

    @Property(name="profile_image_url")
    private String profileImageUrl;

    @Property(name="location")
    private String location;
}
