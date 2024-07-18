package com.korotkov.cms.services.impl;

import com.korotkov.cms.dto.PostDto;
import com.korotkov.cms.exceptions.custom.PostNotFoundException;
import com.korotkov.cms.mappers.PostMapper;
import com.korotkov.cms.models.Post;
import com.korotkov.cms.repositories.PostRepository;
import com.korotkov.cms.services.PostService;
import com.korotkov.cms.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;

    @Override
    @Transactional
    public PostDto savePost(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        post.setUser(userService.getUser(postDto.getUserId()));
        post.setCreatedAt(LocalDateTime.now());
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public PostDto getPostById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toDto)
                .orElseThrow(()->new PostNotFoundException("The post was not found"));
    }

    @Override
    public List<PostDto> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if(postRepository.existsById(id))
            postRepository.deleteById(id);
        else
            throw new PostNotFoundException("The post was not found");
    }

    @Override
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto) {
        Post existingPost = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("The post was not found"));
        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        existingPost.setUpdatedAt(postDto.getUpdatedAt());
        existingPost.setUser(userService.getUser(postDto.getUserId()));
        existingPost.setUpdatedAt(LocalDateTime.now());
        return postMapper.toDto(postRepository.save(existingPost));
    }
}
