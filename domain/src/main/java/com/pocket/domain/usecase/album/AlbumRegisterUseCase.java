package com.pocket.domain.usecase.album;

import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;

public interface AlbumRegisterUseCase {

    AlbumRegisterResponseDto registerPhotoResponse(AlbumRegisterRequestDto requestDto, String name);

}
