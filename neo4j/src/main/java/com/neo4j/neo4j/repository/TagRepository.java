package com.neo4j.neo4j.repository;

import com.neo4j.neo4j.domain.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends Neo4jRepository<Tag, Long> {

}
