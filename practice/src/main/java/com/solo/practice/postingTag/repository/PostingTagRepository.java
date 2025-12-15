package com.solo.practice.postingTag.repository;

import com.solo.practice.postingTag.entity.PostingTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingTagRepository extends JpaRepository<PostingTag, Long> {
}
