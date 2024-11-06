package com.pocket.domain.port.album;

public interface AlbumSharePort {

    Long saveShareTable(String email, Long albumId);

    void saveNewData(String email, Long token);
}
