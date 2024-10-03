package com.pocket.domain.service.photobooth;

import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.domain.port.photobooth.PhotoBoothFindPort;
import com.pocket.domain.usecase.photobooth.PhotoBoothFindUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PhotoBoothFindService implements PhotoBoothFindUseCase {

    private final PhotoBoothFindPort photoBoothFindPort;

    public PhotoBoothFindResponseDto findPhotoBoothResponse(Long id) {
        return photoBoothFindPort.findById(id);
    }

    public List<NearPhotoBoothInfo> findNearPhotoBooth(double lat, double lon, PhotoBoothBrand brand) {
        return photoBoothFindPort.getPhotoboothWithin2Km(lat, lon, brand);
    }
}
