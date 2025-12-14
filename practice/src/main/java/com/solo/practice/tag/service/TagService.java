package com.solo.practice.tag.service;

import com.solo.practice.tag.entity.Tag;
import com.solo.practice.tag.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Page<Tag> findTags(int page, int size){

        return tagRepository.findAll(PageRequest.of(page, size, Sort.by("tagId").descending()));
    }
}
