package com.solo.practice.postingTag.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostingTagDto {

    @NotBlank
    private String tagName;
}
