package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;

import java.util.List;

public interface PhotoBoothFindPort {

    PhotoBoothFindResponseDto findById(Long id);

    List<NearPhotoBoothInfo> getPhotoboothWithin2Km(double currentLat, double currentLon, List<PhotoBoothBrand> brand);

}
