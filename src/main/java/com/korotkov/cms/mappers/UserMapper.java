package com.korotkov.cms.mappers;

import com.korotkov.cms.dto.UserDto;
import com.korotkov.cms.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    @Mapping(target = "posts", ignore = true)
    User toEntity(UserDto userDto);
}