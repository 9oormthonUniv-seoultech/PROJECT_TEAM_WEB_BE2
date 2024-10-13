package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<JpaReviewImage, Long> {

    @Query("SELECT ri FROM JpaReviewImage ri JOIN ri.review r WHERE r.photoBooth.id = :photoboothId ORDER BY r.id DESC")
    List<JpaReviewImage> findTop6ByPhotoBoothOrderByReviewIdDesc(@Param("photoboothId") Long photoboothId);

    @Query("SELECT COUNT(ri) FROM JpaReviewImage ri JOIN ri.review r WHERE r.photoBooth.id = :photoboothId")
    int countByPhotoBoothId(@Param("photoboothId") Long photoboothId);

}
