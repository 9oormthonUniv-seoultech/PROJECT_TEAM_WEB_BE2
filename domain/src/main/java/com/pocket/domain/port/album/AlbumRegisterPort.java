package com.pocket.domain.port.album;

import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;

public interface AlbumRegisterPort {

    AlbumRegisterResponseDto registerPhoto(AlbumRegisterRequestDto albumRegisterRequestDto, String name);

}