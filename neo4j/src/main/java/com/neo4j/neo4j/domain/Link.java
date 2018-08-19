package com.neo4j.neo4j.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NoArgsConstructor
@Data
@NodeEntity
public class Link {

    @Id
    @GeneratedValue
    private Long graphId;

    @Property(name = "url")
    private String url;
}
