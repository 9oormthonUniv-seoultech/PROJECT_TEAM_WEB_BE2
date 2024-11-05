package com.pocket.domain.port.album;

import com.pocket.domain.dto.album.AlbumHashtagResponseDto;

import java.util.List;

public interface AlbumHashtagPort {

    List<AlbumHashtagResponseDto> getAlbumByHashtag(String hashtag, String userEmail);

}
