package com.tidder.api.repository;

import com.tidder.api.domain.associative.UserSubtidder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubtidderRepository extends JpaRepository<UserSubtidder, Long> {
}
