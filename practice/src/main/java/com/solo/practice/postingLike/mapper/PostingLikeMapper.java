package com.solo.practice.postingLike.mapper;

import com.solo.practice.member.entity.Member;
import com.solo.practice.posting.entity.Posting;
import com.solo.practice.postingLike.dto.PostingLikeDto;
import com.solo.practice.postingLike.entity.PostingLike;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostingLikeMapper {

    default PostingLike postingLikePostDtoToPostingLike(PostingLikeDto.Post postingLikePostDto){

        Member member = new Member();
        member.setMemberId(postingLikePostDto.getMemberId());

        Posting posting = new Posting();
        posting.setPostingId(postingLikePostDto.getPostingId());

        PostingLike postingLike = new PostingLike();
        postingLike.setMember(member);
        postingLike.setPosting(posting);

        return postingLike;
    }

    default PostingLikeDto.Response postingToPostingLikeResponseDto(Posting posting){

        PostingLikeDto.Response response = PostingLikeDto.Response.builder()
                .postingId(posting.getPostingId())
                .likeCount(posting.getPostingLikes().size())
                .build();

        return response;
    }
}
