package com.pocket.outbound.repository.review;

import com.pocket.outbound.entity.review.JpaBoothFeature;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoothFeatureRepository extends JpaRepository<JpaBoothFeature, Long> {
}
