package com.pocket.domain.port.review;


import com.pocket.domain.dto.review.ReviewPhotoFeatureDto;

import java.util.List;

public interface ReviewPhotoFeaturePort {

    List<ReviewPhotoFeatureDto> getReviewPhotoFeature(Long photoboothId);

}
