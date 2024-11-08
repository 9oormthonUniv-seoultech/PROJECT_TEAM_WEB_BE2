package com.pocket.domain.usecase.album;

import com.pocket.domain.dto.album.AlbumResponseDto;

import java.util.List;

public interface AlbumFavoriteUseCase {

    List<AlbumResponseDto> getFavoriteAlbums(String userEmail);

}
