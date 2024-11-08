package com.pocket.domain.usecase.album;

public interface AlbumShareUseCase {

    Long saveShareTable(String email, Long albumId);

    void saveNewData(String email, Long token);
}
