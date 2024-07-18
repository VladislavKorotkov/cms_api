package com.korotkov.cms.services;

import com.korotkov.cms.dto.UserDto;
import com.korotkov.cms.models.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    User getUser(Long id);
}
