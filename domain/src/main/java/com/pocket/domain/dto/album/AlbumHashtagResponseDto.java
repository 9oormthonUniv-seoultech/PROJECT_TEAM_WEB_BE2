package com.pocket.domain.dto.album;

import java.util.List;

public record AlbumHashtagResponseDto(
        Long albumId,
        String photoUrl,
        List<String> hashtags,
        Integer year,
        Integer month,
        Integer date,
        String memo,
        boolean isLiked
) {
}
