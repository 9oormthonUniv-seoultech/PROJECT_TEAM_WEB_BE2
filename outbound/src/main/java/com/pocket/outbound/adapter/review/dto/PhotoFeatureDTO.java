package com.pocket.outbound.adapter.review.dto;

import com.pocket.domain.entity.review.PhotoFeature;

public record PhotoFeatureDTO(
        PhotoFeature photoFeature,
        long featureCount) {
}
