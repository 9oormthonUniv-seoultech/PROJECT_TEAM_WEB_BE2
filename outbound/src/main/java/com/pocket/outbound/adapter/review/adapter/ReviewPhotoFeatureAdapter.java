package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewPhotoFeatureDto;
import com.pocket.domain.entity.review.PhotoFeature;
import com.pocket.domain.port.review.ReviewPhotoFeaturePort;
import com.pocket.outbound.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewPhotoFeatureAdapter implements ReviewPhotoFeaturePort
{
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewPhotoFeatureDto> getReviewPhotoFeature(Long photoboothId) {

        List<Object[]> result = reviewRepository.findTop4ByPhotoFeature(photoboothId);

        return result.stream()
                .map(obj -> new ReviewPhotoFeatureDto(((PhotoFeature) obj[0]).getDescription(), ((Long) obj[1]).intValue()))
                .limit(4)  // 상위 4개의 결과만 반환
                .collect(Collectors.toList());
    }
}
