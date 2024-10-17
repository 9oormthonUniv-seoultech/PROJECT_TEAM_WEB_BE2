package com.pocket.outbound.repository.review;

import com.pocket.domain.dto.review.PhotoFeatureCountDto;
import com.pocket.outbound.entity.review.JpaReviewPhotoFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewPhotoFeatureRepository extends JpaRepository<JpaReviewPhotoFeature, Long> {

    @Query("SELECT j.jpaPhotoFeature.id FROM JpaReviewPhotoFeature j WHERE j.jpaReview.id = :reviewId")
    List<Long> findPhotoFeatureIdByReviewId(@Param("reviewId") Long reviewId);

    // reviewIds를 기준으로 photofeature의 이름과 빈도수를 계산하여 PhotoFeatureCountDto로 가져옴
    @Query("SELECT new com.pocket.domain.dto.review.PhotoFeatureCountDto(j.jpaPhotoFeature.photoFeature.description, COUNT(j.jpaPhotoFeature.id)) " +
            "FROM JpaReviewPhotoFeature j " +
            "WHERE j.jpaReview.id IN :reviewIds " +
            "GROUP BY j.jpaPhotoFeature.photoFeature.description " +
            "ORDER BY COUNT(j.jpaPhotoFeature.id) DESC")
    List<PhotoFeatureCountDto> findTopPhotoFeaturesByReviewIds(@Param("reviewIds") List<Long> reviewIds);

}
