package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LikeRepository extends JpaRepository<JpaLike, Long> {
}
