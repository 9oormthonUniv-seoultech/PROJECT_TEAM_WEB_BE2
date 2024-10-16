package com.pocket.outbound.adapter.review.dto;

import com.pocket.domain.entity.review.BoothFeature;

public record BoothFeatureDTO(
        BoothFeature boothFeature,
        long featureCount) {
}

