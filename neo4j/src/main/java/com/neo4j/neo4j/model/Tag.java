package com.neo4j.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@NodeEntity
public class Tag {

    @Id @GeneratedValue
    private Long graphId;

    @Property(name="name")
    private String name;
}
