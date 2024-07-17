package com.korotkov.cms.repositories;

import com.korotkov.cms.models.Post;
import com.korotkov.cms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
