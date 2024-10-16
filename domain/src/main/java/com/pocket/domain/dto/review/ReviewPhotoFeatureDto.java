package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewPhotoFeatureDto(
        List<String> featureName,
        int count
) {
}
