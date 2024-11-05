package com.pocket.domain.usecase.album;

import com.pocket.domain.dto.album.AlbumHashtagResponseDto;

import java.util.List;

public interface AlbumHashtagUseCase {

    List<AlbumHashtagResponseDto> getAlbumByHashtag(String hashtag, String userEmail);

}
