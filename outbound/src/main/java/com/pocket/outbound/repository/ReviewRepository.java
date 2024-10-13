package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<JpaReview, Long> {

    @Query("SELECT COUNT(r) FROM JpaReview r WHERE r.photoBooth.id = :photoboothId")
    int countByPhotoBoothId(@Param("photoboothId") Long photoboothId);

    @Query("SELECT r FROM JpaReview r WHERE r.photoBooth.id = :photoboothId ORDER BY r.id DESC")
    List<JpaReview> findTop3ByPhotoBoothIdOrderByIdDesc(@Param("photoboothId") Long photoboothId);

}
