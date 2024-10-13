package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewRegisterRequestDto(
        Long photoboothId,
        int rating,
        List<String> boothFeatures,
        List<String> photoFeatures,
        List<String> filePaths,
        String content
 ) {

}