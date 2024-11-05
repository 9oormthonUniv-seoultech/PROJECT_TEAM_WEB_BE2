package com.pocket.domain.usecase.album;

import com.pocket.domain.dto.album.AlbumResponseDto;

import java.util.List;

public interface AlbumGetByDateUseCase {

    List<AlbumResponseDto> getAlbumByDate(Integer year, Integer month, String userEmail);

}
