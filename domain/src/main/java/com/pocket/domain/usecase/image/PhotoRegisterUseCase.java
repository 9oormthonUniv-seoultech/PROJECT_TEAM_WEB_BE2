package com.pocket.domain.usecase.image;

import com.pocket.domain.dto.image.AlbumRegisterRequestDto;
import com.pocket.domain.entity.User;

public interface PhotoRegisterUseCase {

    String registerPhotoResponse(AlbumRegisterRequestDto requestDto, User user);

}
