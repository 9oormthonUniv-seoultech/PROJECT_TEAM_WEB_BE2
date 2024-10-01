package com.pocket.domain.service.album;

import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.image.AlbumRegisterRequestDto;
import com.pocket.domain.entity.User;
import com.pocket.domain.port.album.AlbumRegisterPort;
import com.pocket.domain.usecase.image.PhotoRegisterUseCase;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumService implements PhotoRegisterUseCase {

    private final AlbumRegisterPort albumRegisterPort;

    public String registerPhotoResponse(AlbumRegisterRequestDto albumRegisterRequestDto, User user) {
        return albumRegisterPort.registerPhoto(albumRegisterRequestDto, user);
    }

}