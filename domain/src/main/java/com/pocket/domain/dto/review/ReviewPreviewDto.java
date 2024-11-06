package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewPreviewDto(
        Long photoboothId,
        String profileUrl,
        String name,
        String year,
        String month,
        String date,
        String contents,
        List<String> features,
        String imageUrl,
        int imageCount
) {

}
