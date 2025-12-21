package com.solo.practice.postingLike.controller;

import com.solo.practice.auth.userDetailsService.CustomUserDetails;
import com.solo.practice.dto.SingleResponseDto;
import com.solo.practice.posting.entity.Posting;
import com.solo.practice.postingLike.dto.PostingLikeDto;
import com.solo.practice.postingLike.mapper.PostingLikeMapper;
import com.solo.practice.postingLike.service.PostingLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/postingLikes")
public class PostingLikeController {

    private final PostingLikeService postingLikeService;
    private final PostingLikeMapper mapper;

    public PostingLikeController(PostingLikeService postingLikeService, PostingLikeMapper mapper) {
        this.postingLikeService = postingLikeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postPostingLike(@RequestBody @Valid PostingLikeDto.Post postingLikePostDto,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Posting posting = postingLikeService.createPostingLike(
                mapper.postingLikePostDtoToPostingLike(postingLikePostDto), customUserDetails);

        return new ResponseEntity(new SingleResponseDto<>(mapper.postingToPostingLikeResponseDto(posting)), HttpStatus.OK);
    }
}
