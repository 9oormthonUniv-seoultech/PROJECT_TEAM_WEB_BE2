package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.PhotoFeatureCountDto;
import com.pocket.domain.port.review.ReviewPhotoFeatureCountPort;
import com.pocket.outbound.repository.review.ReviewPhotoFeatureRepository;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AdapterService
@RequiredArgsConstructor
public class ReviewPhotoFeatureCountAdapter implements ReviewPhotoFeatureCountPort
{
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoFeatureRepository reviewPhotoFeatureRepository;

    @Override
    public List<PhotoFeatureCountDto> getReviewPhotoFeature(Long photoboothId) {

        // photoboothId로 해당 포토부스의 리뷰를 다 검색 -> 거기서 개수세기
        List<Long> reviewIds = reviewRepository.findReviewIdsByPhotoBoothId(photoboothId);

        List<PhotoFeatureCountDto> photoFeatures = reviewPhotoFeatureRepository.findTopPhotoFeaturesByReviewIds(reviewIds);

        return photoFeatures.stream()
                .limit(4)
                .toList();
    }
}
