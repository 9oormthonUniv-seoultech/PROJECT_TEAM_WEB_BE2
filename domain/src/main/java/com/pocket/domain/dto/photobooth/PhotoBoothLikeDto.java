package com.pocket.domain.dto.photobooth;

import java.math.BigDecimal;

public record PhotoBoothLikeDto(
        Long photoBoothId,
        String name,
        BigDecimal rating,
        String feature,
        int featureCount
) {

}
