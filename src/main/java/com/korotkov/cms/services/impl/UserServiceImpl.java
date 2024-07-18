package com.korotkov.cms.services.impl;

import com.korotkov.cms.dto.UserDto;
import com.korotkov.cms.exceptions.custom.UserNotFoundException;
import com.korotkov.cms.mappers.UserMapper;
import com.korotkov.cms.models.User;
import com.korotkov.cms.repositories.UserRepository;
import com.korotkov.cms.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(()->new UserNotFoundException("The user was not found"));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if(userRepository.existsById(id))
            userRepository.deleteById(id);
        else
            throw new UserNotFoundException("The user was not found");
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("The user was not found"));
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        return userMapper.toDto(userRepository.save(existingUser));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("The user was not found"));
    }
}
