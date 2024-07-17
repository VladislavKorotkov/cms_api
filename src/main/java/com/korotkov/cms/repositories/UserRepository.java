package com.korotkov.cms.repositories;


import com.korotkov.cms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}