package com.pocket.inbounds.review.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterResponseDto;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.usecase.review.ReviewRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewRegisterUseCase reviewRegisterUseCase;

    @PostMapping
    public ApplicationResponse<ReviewRegisterResponseDto> postReview(
            @RequestBody ReviewRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserInfoDTO user) {
        ReviewRegisterResponseDto response = reviewRegisterUseCase.registerReviewResponse(requestDto, user.name());
        return ApplicationResponse.ok(response);
    }

}
