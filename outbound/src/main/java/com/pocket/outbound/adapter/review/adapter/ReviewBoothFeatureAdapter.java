package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewBoothFeatureDto;
import com.pocket.domain.entity.review.BoothFeature;
import com.pocket.domain.port.review.ReviewBoothFeaturePort;
import com.pocket.outbound.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewBoothFeatureAdapter implements ReviewBoothFeaturePort {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewBoothFeatureDto> getReviewBoothFeature(Long photoboothId) {
        // Repository에서 상위 4개의 BoothFeature를 조회
        List<Object[]> result = reviewRepository.findTop4ByBoothFeature(photoboothId);

        // Object[]로 반환된 결과를 DTO로 변환
        return result.stream()
                .map(obj -> new ReviewBoothFeatureDto(((BoothFeature) obj[0]).getDescription(), ((Long) obj[1]).intValue()))
                .limit(4)  // 상위 4개의 결과만 반환
                .collect(Collectors.toList());
    }

}
