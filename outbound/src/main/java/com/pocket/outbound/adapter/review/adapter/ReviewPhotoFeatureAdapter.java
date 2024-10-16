package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewPhotoFeatureDto;
import com.pocket.domain.entity.review.PhotoFeature;
import com.pocket.domain.port.review.ReviewPhotoFeaturePort;
import com.pocket.outbound.adapter.review.dto.PhotoFeatureDTO;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewPhotoFeatureAdapter implements ReviewPhotoFeaturePort {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewPhotoFeatureDto> getReviewPhotoFeature(Long photoboothId) {

        // Repository에서 상위 4개의 PhotoFeature를 조회
        List<PhotoFeatureDTO> result = reviewRepository.findTop4ByPhotoFeature(photoboothId);

        // PhotoFeatureDTO에서 직접 값을 가져와서 ReviewPhotoFeatureDto로 변환
        return result.stream()
                .map(dto -> new ReviewPhotoFeatureDto(
                        dto.photoFeature().stream().map(PhotoFeature::getDescription).toList(),
                        (int) dto.featureCount()))
                .limit(4)  // 상위 4개의 결과만 반환
                .collect(Collectors.toList());
    }

}
