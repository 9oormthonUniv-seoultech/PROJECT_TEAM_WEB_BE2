package com.pocket.domain.service.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothResponseDto;
import com.pocket.domain.port.photobooth.PhotoBoothPort;
import com.pocket.domain.usecase.photobooth.PhotoBoothUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PhotoBoothService implements PhotoBoothUseCase {

    private final PhotoBoothPort photoBoothPort;

    public PhotoBoothResponseDto getFindPhotoBoothResponse(Long id) {
        return photoBoothPort.findById(id);
    }
}
