package com.pocket.inbounds.photobooth.presentation;


import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.usecase.photobooth.PhotoBoothFindUseCase;
import com.pocket.inbounds.photobooth.response.PhotoBoothResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PhotoBoothController implements PhotoBoothControllerDocs{

    private final PhotoBoothFindUseCase photoBoothFindUseCase;


    @GetMapping("/api/v1/photobooth/{id}")
    public ApplicationResponse<PhotoBoothResponse> getPhotoBooth(@PathVariable("id") Long id) {
        PhotoBoothFindResponseDto dto = photoBoothFindUseCase.getPhotoBoothFindResponse(id);

        // 여기서 dto를 response로 변환
        PhotoBoothResponse response = new PhotoBoothResponse(
                dto.name(),
                dto.location(),
                dto.photoBoothBrand()
        );

        return ApplicationResponse.ok(response);
    }

}
