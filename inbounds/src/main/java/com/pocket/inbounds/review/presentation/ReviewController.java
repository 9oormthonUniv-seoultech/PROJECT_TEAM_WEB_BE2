package com.pocket.inbounds.review.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.review.*;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.usecase.review.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewRegisterUseCase reviewRegisterUseCase;
    private final ReviewGet6ImagesUseCase reviewGet6ImagesUseCase;
    private final ReviewGetRecentUseCase reviewGetRecentUseCase;
    private final ReviewGetAllImagesUseCase reviewGetAllImagesUseCase;
    private final ReviewBoothFeatureCountUseCase reviewBoothFeatureCountUseCase;
    private final ReviewPhotoFeatureCountUseCase reviewPhotoFeatureCountUseCase;
    private final ReviewGetBoothFeatureUseCase reviewGetBoothFeatureUseCase;
    private final ReviewGetPhotoFeatureUseCase reviewGetPhotoFeatureUseCase;

    @PostMapping
    public ApplicationResponse<ReviewRegisterResponseDto> postReview(
            @RequestBody ReviewRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserInfoDTO user) {
        ReviewRegisterResponseDto response = reviewRegisterUseCase.registerReviewResponse(requestDto, user.name());
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/allboothfeature")
    public ApplicationResponse<List<BoothFeatureDto>> getAllBoothFeature() {
        List<BoothFeatureDto> response = reviewGetBoothFeatureUseCase.getBoothFeatures();
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/allphotofeature")
    public ApplicationResponse<List<PhotoFeatureDto>> getAllPhotoFeature() {
        List<PhotoFeatureDto> response = reviewGetPhotoFeatureUseCase.getPhotoFeatures();
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/images/{photobooth_id}")
    public ApplicationResponse<ReviewGet6ImagesResponseDto> getReviewHomeImage(
            @PathVariable("photobooth_id") Long photoboothId
    ) {
        ReviewGet6ImagesResponseDto response = reviewGet6ImagesUseCase.get6Images(photoboothId);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/reviews/{photobooth_id}")
    public ApplicationResponse<ReviewGetRecentResponseDto> getRecentReview(
            @PathVariable("photobooth_id") Long photoboothId
    ) {
        ReviewGetRecentResponseDto response = reviewGetRecentUseCase.getRecentReview(photoboothId);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/allimages/{photobooth_id}")
    public ApplicationResponse<List<String>> getReviewImages(
            @PathVariable("photobooth_id") Long photoboothId
    ) {
        List<String> response = reviewGetAllImagesUseCase.getAllImages(photoboothId);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/boothfeatures/{photobooth_id}")
    public ApplicationResponse<List<BoothFeatureCountDto>> getReviewBoothFeatures(
            @PathVariable("photobooth_id") Long photoboothId
    ) {
        List<BoothFeatureCountDto> response = reviewBoothFeatureCountUseCase.getReviewBoothFeatures(photoboothId);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/photofeatures/{photobooth_id}")
    public ApplicationResponse<List<PhotoFeatureCountDto>> getReviewPhotoFeatures(
            @PathVariable("photobooth_id") Long photoboothId
    ) {
        List<PhotoFeatureCountDto> response = reviewPhotoFeatureCountUseCase.getReviewPhotoFeatures(photoboothId);
        return ApplicationResponse.ok(response);
    }

}
