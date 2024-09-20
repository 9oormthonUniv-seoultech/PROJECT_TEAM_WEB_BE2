package com.pocket.outbound.adapter.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothResponseDto;
import com.pocket.domain.port.photobooth.PhotoBoothPort;
import com.pocket.domain.service.photobooth.PhotoBoothService;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoBoothAdapter implements PhotoBoothPort {

    private final PhotoBoothRepository photoBoothRepository;

    public PhotoBoothResponseDto findById(Long id) {
        JpaPhotoBooth entity = photoBoothRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포토부스가 존재하지 않습니다."));

        return new PhotoBoothResponseDto(
                entity.getPhotoBooth().getName(),
                entity.getPhotoBooth().getLocation(),
                entity.getPhotoBooth().getPhotoBoothBrand()
        );
    }

}
