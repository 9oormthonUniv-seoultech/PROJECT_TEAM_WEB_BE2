package com.pocket.outbound.repository.review;

import com.pocket.domain.dto.review.BoothFeatureCountDto;
import com.pocket.outbound.entity.review.JpaReviewBoothFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewBoothFeatureRepository extends JpaRepository<JpaReviewBoothFeature, Long> {

    @Query("SELECT j.jpaBoothFeature.id FROM JpaReviewBoothFeature j WHERE j.jpaReview.id = :reviewId")
    List<Long> findBoothFeatureIdByReviewId(@Param("reviewId") Long reviewId);

    // reviewIds를 기준으로 boothfeature의 이름과 빈도수를 계산하여 BoothFeatureCountDto로 가져옴
    @Query("SELECT new com.pocket.domain.dto.review.BoothFeatureCountDto(j.jpaBoothFeature.boothFeature.description, COUNT(j.jpaBoothFeature.id)) " +
            "FROM JpaReviewBoothFeature j " +
            "WHERE j.jpaReview.id IN :reviewIds " +
            "GROUP BY j.jpaBoothFeature.boothFeature.description " +
            "ORDER BY COUNT(j.jpaBoothFeature.id) DESC")
    List<BoothFeatureCountDto> findTopBoothFeaturesByReviewIds(@Param("reviewIds") List<Long> reviewIds);

}
