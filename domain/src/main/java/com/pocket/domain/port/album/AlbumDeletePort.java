package com.pocket.domain.port.album;

public interface AlbumDeletePort {

    void deleteAlbum(Long albumId, String userEmail);

}
