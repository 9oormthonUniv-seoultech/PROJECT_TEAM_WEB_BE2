package com.pocket.domain.usecase.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothVisitedDto;

import java.util.List;

public interface PhotoBoothVisitedUseCase {

    List<PhotoBoothVisitedDto> getVisitedPhotoBooths(String userEmail);

}
