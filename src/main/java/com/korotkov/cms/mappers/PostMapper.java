package com.korotkov.cms.mappers;

import com.korotkov.cms.dto.PostDto;
import com.korotkov.cms.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "user.id", target = "userId")
    PostDto toDto(Post post);
    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDto postDto);
}