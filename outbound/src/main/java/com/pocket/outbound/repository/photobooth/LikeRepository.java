package com.pocket.outbound.repository.photobooth;

import com.pocket.outbound.entity.photobooth.JpaLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<JpaLike, Long> {

    List<JpaLike> findByUser_UserEmail(String userEmail);

}
