package com.pocket.inbounds.photobooth.presentation;


import com.pocket.domain.dto.photobooth.PhotoBoothResponseDto;
import com.pocket.domain.usecase.photobooth.PhotoBoothUseCase;
import com.pocket.inbounds.photobooth.response.PhotoBoothResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PhotoBoothController {

    private final PhotoBoothUseCase photoBoothUseCase;


    @GetMapping("/api/v1/photobooth/{id}")
    public PhotoBoothResponse getPhotoBooth(@PathVariable("id") Long id) {
        PhotoBoothResponseDto dto = photoBoothUseCase.getFindPhotoBoothResponse(id);

        // 여기서 dto를 response로 변환
        PhotoBoothResponse response = new PhotoBoothResponse(
                dto.name(),
                dto.location(),
                dto.photoBoothBrand()
        );

        return response;
    }

}
