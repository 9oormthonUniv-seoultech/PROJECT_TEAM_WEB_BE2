package com.pocket.domain.usecase.album;

import com.pocket.domain.dto.album.AlbumResponseDto;

import java.util.List;

public interface AlbumGetByBrandUseCase {

    List<AlbumResponseDto> getAlbumByBrand(String brandName, String userEmail);

}
