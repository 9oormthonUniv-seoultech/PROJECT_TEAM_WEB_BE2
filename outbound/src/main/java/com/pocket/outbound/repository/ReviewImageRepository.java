package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<JpaReviewImage, Long> {

    List<JpaReviewImage> findTop6ByReviewPhotoBoothIdOrderByReviewIdDesc(Long photoboothId);

    int countByReviewPhotoBoothId(Long photoboothId);

    List<JpaReviewImage> findByReviewId(Long reviewId);

    List<JpaReviewImage> findByReviewPhotoBoothId(Long photoboothId);

}
