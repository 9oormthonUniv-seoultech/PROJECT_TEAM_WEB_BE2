package com.pocket.domain.service.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.port.photobooth.PhotoBoothFindPort;
import com.pocket.domain.usecase.photobooth.PhotoBoothFindUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PhotoBoothFindService implements PhotoBoothFindUseCase {

    private final PhotoBoothFindPort photoBoothFindPort;

    public PhotoBoothFindResponseDto getPhotoBoothFindResponse(Long id) {
        return photoBoothFindPort.findById(id);
    }
}
