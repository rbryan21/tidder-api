package com.tidder.api.repository;

import com.tidder.api.domain.associative.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {
}
