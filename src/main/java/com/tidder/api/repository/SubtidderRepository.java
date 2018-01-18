package com.tidder.api.repository;

import com.tidder.api.domain.Subtidder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtidderRepository extends JpaRepository<Subtidder, Long> {

    Subtidder findByName(String name);

}
