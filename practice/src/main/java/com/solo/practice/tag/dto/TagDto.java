package com.solo.practice.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TagDto {

    @Getter
    @Setter
    @Builder
    public static class Response{
        private long tagId;
        private String tagName;
    }

    @Getter
    @Setter
    @Builder
    public static class TagResponse{
        private long tagId;
        private String tagName;
    }
}
