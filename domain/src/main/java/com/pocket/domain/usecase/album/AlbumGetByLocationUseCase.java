package com.pocket.domain.usecase.album;

import com.pocket.domain.dto.album.NearAlbumInfo;

import java.util.List;

public interface AlbumGetByLocationUseCase {

    List<NearAlbumInfo> getAlbumByLocation(double currentLat, double currentLon, String userEmail);

}
