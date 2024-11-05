package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewMypageDto;

public interface ReviewMypageUseCase {

    ReviewMypageDto reviewMypage(String userEmail);

}
