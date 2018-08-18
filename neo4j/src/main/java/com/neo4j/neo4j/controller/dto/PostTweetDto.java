package com.neo4j.neo4j.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostTweetDto {

    @NotBlank
    private String text;

    private String tag;
}
