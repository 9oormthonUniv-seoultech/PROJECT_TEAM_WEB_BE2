package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewBoothFeatureDto(
        List<String> featureName,
        int count
) {
}
