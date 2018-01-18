package com.tidder.api.repository;

import com.tidder.api.domain.associative.UserBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBoardRepository extends JpaRepository<UserBoard, Long> {
}
