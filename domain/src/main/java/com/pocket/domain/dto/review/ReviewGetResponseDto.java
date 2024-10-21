package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewGetResponseDto(
        int reviewCount,
        List<ReviewPreviewDto> reviews
) {
}
