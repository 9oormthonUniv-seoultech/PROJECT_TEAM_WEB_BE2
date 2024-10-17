package com.pocket.outbound.repository.photobooth;

import com.pocket.outbound.entity.photobooth.JpaLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<JpaLike, Long> {
}
