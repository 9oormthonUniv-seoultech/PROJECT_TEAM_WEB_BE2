package com.pocket.domain.port.review;


import com.pocket.domain.dto.review.PhotoFeatureCountDto;

import java.util.List;

public interface ReviewPhotoFeatureCountPort {

    List<PhotoFeatureCountDto> getReviewPhotoFeature(Long photoboothId);

}
