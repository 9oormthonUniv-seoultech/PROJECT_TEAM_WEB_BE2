package com.pocket.domain.port.album;

import com.pocket.domain.dto.album.NearAlbumInfo;

import java.util.List;

public interface AlbumGetByLocationPort {

    List<NearAlbumInfo> getAlbumByLocation(double currentLat, double currentLon, String userEmail);

}
