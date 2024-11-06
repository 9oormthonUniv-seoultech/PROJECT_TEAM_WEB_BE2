package com.pocket.inbounds.photobooth.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.photobooth.*;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.domain.port.photobooth.PhotoBoothGetNamePort;
import com.pocket.domain.usecase.photobooth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photobooth")
public class PhotoBoothController implements PhotoBoothControllerDocs {

    private final PhotoBoothFindUseCase photoBoothFindUseCase;
    private final PhotoBoothGetRatingUseCase photoBoothGetRatingUseCase;
    private final PhotoBoothGetNameUseCase photoBoothGetNameUseCase;
    private final PhotoBoothSearchUseCase photoBoothSearchUseCase;
    private final PhotoBoothGetModalUseCase photoBoothGetModalUseCase;
    private final PhotoBoothVisitedUseCase photoBoothVisitedUseCase;

    @GetMapping("{id}")
    public ApplicationResponse<PhotoBoothFindResponseDto> getPhotoBoothById(@PathVariable("id") Long id) {

        PhotoBoothFindResponseDto response = photoBoothFindUseCase.findPhotoBoothResponse(id);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/name/{id}")
    public ApplicationResponse<String> getPhotoBoothNameById(@PathVariable("id") Long id) {
        String response = photoBoothGetNameUseCase.getPhotoBoothName(id);
        return ApplicationResponse.ok(response);
    }

    @GetMapping()
    public ApplicationResponse<List<NearPhotoBoothInfo>> getAllPhotoBooth(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam(value = "brand", required = false) List<PhotoBoothBrand> brand
    ) {

        List<NearPhotoBoothInfo> responses = photoBoothFindUseCase.findNearPhotoBooth(lat, lon, brand);
        return ApplicationResponse.ok(responses);
    }

    @GetMapping("/rating/{id}")
    public ApplicationResponse<BigDecimal> getPhotoBoothRating(@PathVariable("id") Long id) {
        BigDecimal response = photoBoothGetRatingUseCase.getPhotoBoothRating(id);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/search")
    public ApplicationResponse<List<PhotoBoothSearchDto>> searchPhotoBooth(@RequestParam("keyword") String keyword) {
        List<PhotoBoothSearchDto> response = photoBoothSearchUseCase.searchPhotoBooth(keyword);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/modal/{id}")
    public ApplicationResponse<PhotoBoothModalDto> getPhotoBoothModal(@PathVariable("id") Long id) {
        PhotoBoothModalDto response = photoBoothGetModalUseCase.getPhotoBoothModal(id);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/visited")
    public ApplicationResponse<List<PhotoBoothVisitedDto>> getPhotoBoothVisited(
            @AuthenticationPrincipal UserInfoDTO user
    ) {
        List<PhotoBoothVisitedDto> response = photoBoothVisitedUseCase.getVisitedPhotoBooths(user.email());
        return ApplicationResponse.ok(response);
    }
}
