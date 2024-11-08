package com.pocket.domain.dto.album;

public record AlbumResponseDto(
        Long albumId,
        String photoUrl,
        boolean like
) {

}
