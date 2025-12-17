package com.solo.practice.postingLike.service;

import com.solo.practice.posting.entity.Posting;
import com.solo.practice.posting.service.PostingService;
import com.solo.practice.postingLike.entity.PostingLike;
import com.solo.practice.postingLike.repository.PostingLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostingLikeService {

    private final PostingLikeRepository postingLikeRepository;
    private final PostingService postingService;

    public PostingLikeService(PostingLikeRepository postingLikeRepository, PostingService postingService) {
        this.postingLikeRepository = postingLikeRepository;
        this.postingService = postingService;
    }

    public Posting createPostingLike(PostingLike postingLike){

        Posting findPosting = postingService.findVerifiedPosting(postingLike.getPosting().getPostingId());

        Optional<PostingLike> optionalPostingLike = postingLikeRepository.findByMemberMemberIdAndPostingPostingId(
                postingLike.getMember().getMemberId(), postingLike.getPosting().getPostingId());

        if(optionalPostingLike.isPresent()){
            postingLikeRepository.delete(optionalPostingLike.get());
        } else{
            postingLikeRepository.save(postingLike);
        }

        return findPosting;
    }
}
