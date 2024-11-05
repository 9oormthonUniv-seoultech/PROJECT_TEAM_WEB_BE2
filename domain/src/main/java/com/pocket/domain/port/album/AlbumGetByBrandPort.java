package com.pocket.domain.port.album;

import com.pocket.domain.dto.album.AlbumResponseDto;

import java.util.List;

public interface AlbumGetByBrandPort {

    List<AlbumResponseDto> getAlbumByBrand(String brandName);

}
