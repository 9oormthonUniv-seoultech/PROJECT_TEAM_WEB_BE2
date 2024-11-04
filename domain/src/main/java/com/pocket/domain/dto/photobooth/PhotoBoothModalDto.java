package com.pocket.domain.dto.photobooth;

import java.math.BigDecimal;
import java.util.List;

public record PhotoBoothModalDto(
        String name,
        Double x,
        Double y,
        List<String> features,
        BigDecimal rating,
        int imageCount,
        int reviewCount
) {
}
