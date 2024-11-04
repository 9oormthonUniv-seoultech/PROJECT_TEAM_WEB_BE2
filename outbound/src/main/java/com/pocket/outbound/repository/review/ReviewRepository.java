package com.pocket.outbound.repository.review;

import com.pocket.outbound.entity.review.JpaReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<JpaReview, Long> {

    int countByPhotoBoothId(Long photoboothId);

    List<JpaReview> findTop2ByPhotoBoothIdOrderByIdDesc(Long photoboothId);

    // photoBoothId를 기준으로 JpaReview의 id만 반환
    @Query("SELECT r.id FROM JpaReview r WHERE r.photoBooth.id = :photoBoothId")
    List<Long> findReviewIdsByPhotoBoothId(@Param("photoBoothId") Long photoBoothId);

    Page<JpaReview> findByPhotoBoothId(Long photoboothId, Pageable pageable);
}
