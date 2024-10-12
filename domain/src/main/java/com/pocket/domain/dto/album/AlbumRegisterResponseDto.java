package com.pocket.domain.dto.album;

import java.util.List;

public record AlbumRegisterResponseDto(
        Long photoboothId,
        String year,
        String month,
        String date,
        List<String> hashtag,
        String memo,
        String filePath
) {

}
