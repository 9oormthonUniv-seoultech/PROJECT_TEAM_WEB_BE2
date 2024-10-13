package com.pocket.domain.port.review;


import com.pocket.domain.dto.review.ReviewBoothFeatureDto;

import java.util.List;

public interface ReviewBoothFeaturePort {

    List<ReviewBoothFeatureDto> getReviewBoothFeature(Long photoboothId);

}
