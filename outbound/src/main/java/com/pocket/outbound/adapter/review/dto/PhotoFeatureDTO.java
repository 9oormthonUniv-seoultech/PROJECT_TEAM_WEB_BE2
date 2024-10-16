package com.pocket.outbound.adapter.review.dto;

import com.pocket.domain.entity.review.PhotoFeature;

import java.util.List;

public record PhotoFeatureDTO(
        List<PhotoFeature> photoFeature,
        long featureCount) {
}
