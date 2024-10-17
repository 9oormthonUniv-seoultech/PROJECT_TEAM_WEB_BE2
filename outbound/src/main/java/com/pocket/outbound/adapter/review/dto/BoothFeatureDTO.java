package com.pocket.outbound.adapter.review.dto;

import com.pocket.domain.entity.review.BoothFeature;

import java.util.List;

public record BoothFeatureDTO(
        List<BoothFeature> boothFeature,
        long featureCount) {
}

