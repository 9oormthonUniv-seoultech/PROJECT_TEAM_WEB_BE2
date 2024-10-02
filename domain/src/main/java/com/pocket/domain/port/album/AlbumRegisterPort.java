package com.pocket.domain.port.album;

import com.pocket.domain.dto.image.AlbumRegisterRequestDto;
import com.pocket.domain.entity.User;

public interface AlbumRegisterPort {

    String registerPhoto(AlbumRegisterRequestDto albumRegisterRequestDto, String name);

}