package com.neo4j.neo4j.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@NoArgsConstructor
@Data
@NodeEntity
public class Link {

    @Id
    @GeneratedValue
    private Long graphId;

    @Index(unique = true)
    @Property(name = "url")
    private String url;
}
