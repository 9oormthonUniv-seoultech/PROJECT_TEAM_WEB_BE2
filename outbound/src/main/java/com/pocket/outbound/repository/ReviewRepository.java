package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<JpaReview, Long> {

    int countByPhotoBoothId(Long photoboothId);

    List<JpaReview> findTop2ByPhotoBoothIdOrderByIdDesc(Long photoboothId);

    @Query("SELECT bf, COUNT(r) as featureCount FROM JpaReview r JOIN r.review.boothFeatures bf WHERE r.photoBooth.id = :photoboothId GROUP BY bf ORDER BY featureCount DESC")
    List<Object[]> findTop4ByBoothFeature(@Param("photoboothId") Long photoboothId);

    @Query("SELECT pf, COUNT(r) as featureCount FROM JpaReview r JOIN r.review.photoFeatures pf WHERE r.photoBooth.id = :photoboothId GROUP BY pf ORDER BY featureCount DESC")
    List<Object[]> findTop4ByPhotoFeature(@Param("photoboothId") Long photoboothId);

}
