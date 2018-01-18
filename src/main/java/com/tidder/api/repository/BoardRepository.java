package com.tidder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tidder.api.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
