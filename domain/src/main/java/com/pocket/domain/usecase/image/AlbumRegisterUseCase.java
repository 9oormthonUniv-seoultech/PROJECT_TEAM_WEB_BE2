package com.pocket.domain.usecase.image;

import com.pocket.domain.dto.image.AlbumRegisterRequestDto;

public interface AlbumRegisterUseCase {

    String registerPhotoResponse(AlbumRegisterRequestDto requestDto, String name);

}
