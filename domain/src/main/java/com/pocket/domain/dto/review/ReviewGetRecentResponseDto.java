package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewGetRecentResponseDto(
        int reviewCount,
        List<ReviewPreviewDto> reviews
) {
}
