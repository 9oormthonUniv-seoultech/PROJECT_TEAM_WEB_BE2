package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<JpaLike, Long> {
}
