package com.solo.practice.tag.mapper;

import com.solo.practice.tag.dto.TagDto;
import com.solo.practice.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto.Response tagToTagResponseDto(Tag tag);

    List<TagDto.Response> tagsToTagResponseDtos(List<Tag> tags);
}
