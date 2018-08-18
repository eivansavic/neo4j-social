package com.neo4j.neo4j.service.use_case.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTweetInfo {

    private String text;

    private String tag;
}
