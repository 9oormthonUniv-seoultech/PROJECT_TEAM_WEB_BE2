package com.pocket.outbound.repository.review;

import com.pocket.outbound.entity.review.JpaReviewImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<JpaReviewImage, Long> {

    List<JpaReviewImage> findTop6ByReviewPhotoBoothIdOrderByReviewIdDesc(Long photoboothId);

    JpaReviewImage findTop1ByReviewIdOrderByReviewIdDesc(Long reviewId);

    int countByReviewPhotoBoothId(Long photoboothId);

    List<JpaReviewImage> findByReviewId(Long reviewId);

    Page<JpaReviewImage> findByReviewPhotoBoothId(Long photoboothId, Pageable pageable);

}
