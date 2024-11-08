package com.pocket.domain.port.album;

import com.pocket.domain.dto.album.AlbumResponseDto;

import java.util.List;

public interface AlbumFavoritePort {

    List<AlbumResponseDto> getFavoriteAlbums(String userEmail);

}
