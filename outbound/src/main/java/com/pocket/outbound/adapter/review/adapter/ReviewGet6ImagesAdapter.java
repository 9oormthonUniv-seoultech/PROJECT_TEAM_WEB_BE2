package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewGet6ImagesResponseDto;
import com.pocket.domain.port.review.ReviewGet6ImagesPort;
import com.pocket.outbound.entity.JpaReviewImage;
import com.pocket.outbound.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewGet6ImagesAdapter implements ReviewGet6ImagesPort {

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public ReviewGet6ImagesResponseDto get6Images(Long photoboothId) {
        List<JpaReviewImage> reviewImages = reviewImageRepository.findTop6ByReviewPhotoBoothIdOrderByReviewIdDesc(photoboothId);

        // 포토부스에 해당하는 전체 리뷰 이미지 개수 구하기
        int totalImageCount = reviewImageRepository.countByReviewPhotoBoothId(photoboothId);

        List<String> imageUrls = reviewImages.stream()
                .map(reviewImage -> reviewImage.getImage().getImageUrl())
                .collect(Collectors.toList());

        return new ReviewGet6ImagesResponseDto(imageUrls, totalImageCount);
    }


}
