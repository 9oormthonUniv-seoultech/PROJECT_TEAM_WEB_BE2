package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewBoothFeatureDto;
import com.pocket.domain.entity.review.BoothFeature;
import com.pocket.domain.port.review.ReviewBoothFeaturePort;
import com.pocket.outbound.adapter.review.dto.BoothFeatureDTO;
import com.pocket.outbound.repository.review.ReviewRepository;
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
        List<BoothFeatureDTO> result = reviewRepository.findTop4ByBoothFeature(photoboothId);

        // BoothFeatureDTO에서 직접 값을 가져와서 ReviewBoothFeatureDto로 변환
        return result.stream()
                .map(dto -> new ReviewBoothFeatureDto(
                        dto.boothFeature().stream().map(BoothFeature::getDescription).toList(),
                        (int) dto.featureCount()))
                .limit(4)  // 상위 4개의 결과만 반환
                .collect(Collectors.toList());
    }

}
