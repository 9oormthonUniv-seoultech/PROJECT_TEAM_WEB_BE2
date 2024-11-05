package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothVisitedDto;

import java.util.List;

public interface PhotoBoothVisitedPort {

    List<PhotoBoothVisitedDto> getVisitedPhotoBooths(String userEmail);

}
