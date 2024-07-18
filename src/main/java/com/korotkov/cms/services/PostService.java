package com.korotkov.cms.services;

import com.korotkov.cms.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);
    PostDto getPostById(Long id);
    List<PostDto> getPostsByUserId(Long userId);
    List<PostDto> getAllPosts();
    void deletePost(Long id);
    PostDto updatePost(Long id, PostDto postDto);
}
