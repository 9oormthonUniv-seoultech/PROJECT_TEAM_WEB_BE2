package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.BoothFeatureCountDto;
import com.pocket.domain.port.review.ReviewBoothFeatureCountPort;
import com.pocket.outbound.repository.review.ReviewBoothFeatureRepository;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AdapterService
@RequiredArgsConstructor
public class ReviewBoothFeatureCountAdapter implements ReviewBoothFeatureCountPort {

    private final ReviewRepository reviewRepository;
    private final ReviewBoothFeatureRepository reviewBoothFeatureRepository;

    @Override
    public List<BoothFeatureCountDto> getReviewBoothFeature(Long photoboothId) {

        // photoboothId로 해당 포토부스의 리뷰를 다 검색 -> 거기서 개수세기
        List<Long> reviewIds = reviewRepository.findReviewIdsByPhotoBoothId(photoboothId);

        List<BoothFeatureCountDto> boothFeatures = reviewBoothFeatureRepository.findTopBoothFeaturesByReviewIds(reviewIds);

        return boothFeatures.stream()
                .limit(4)
                .toList();
    }

}
