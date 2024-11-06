package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.ReviewMypageDto;

public interface ReviewMypagePort {

    ReviewMypageDto reviewMypage(String userEmail);

}
