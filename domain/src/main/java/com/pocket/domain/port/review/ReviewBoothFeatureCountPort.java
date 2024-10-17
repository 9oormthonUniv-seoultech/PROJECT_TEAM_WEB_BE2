package com.pocket.domain.port.review;


import com.pocket.domain.dto.review.BoothFeatureCountDto;

import java.util.List;

public interface ReviewBoothFeatureCountPort {

    List<BoothFeatureCountDto> getReviewBoothFeature(Long photoboothId);

}
