package com.pocket.domain.service.album;

import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.port.album.AlbumRegisterPort;
import com.pocket.domain.usecase.image.AlbumRegisterUseCase;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumService implements AlbumRegisterUseCase {

    private final AlbumRegisterPort albumRegisterPort;

    public AlbumRegisterResponseDto registerPhotoResponse(AlbumRegisterRequestDto albumRegisterRequestDto, String name) {
        return albumRegisterPort.registerPhoto(albumRegisterRequestDto, name);
    }

}