package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewRegisterRequestDto(
        Long photoboothId,
        int rating,
        List<Long> boothFeatures,
        List<Long> photoFeatures,
        List<String> filePaths,
        String content
 ) {

}
