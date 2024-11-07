package com.pocket.outbound.repository.photobooth;

import com.pocket.outbound.entity.photobooth.JpaLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<JpaLike, Long> {

    List<JpaLike> findByUser_UserEmail(String userEmail);

    Optional<JpaLike> findByUser_UserEmailAndPhotoBooth_Id(String userEmail, Long photoBoothId);

}
