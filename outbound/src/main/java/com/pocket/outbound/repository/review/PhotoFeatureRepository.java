package com.pocket.outbound.repository.review;

import com.pocket.outbound.entity.review.JpaPhotoFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoFeatureRepository extends JpaRepository<JpaPhotoFeature, Long> {
}
