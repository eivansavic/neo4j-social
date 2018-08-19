package com.neo4j.neo4j.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@NoArgsConstructor
@Data
@NodeEntity
public class Tag {

    @Id
    @GeneratedValue
    private Long graphId;

    @Index(unique = true)
    @Property(name = "name")
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
