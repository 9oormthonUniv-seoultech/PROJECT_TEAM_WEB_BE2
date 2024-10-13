package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewGetRecentResponseDto;
import com.pocket.domain.dto.review.ReviewPreviewDto;
import com.pocket.domain.port.review.ReviewGetRecentPort;
import com.pocket.outbound.adapter.review.mapper.ReviewOutBoundMapper;
import com.pocket.outbound.entity.JpaReview;
import com.pocket.outbound.entity.JpaReviewImage;
import com.pocket.outbound.repository.ReviewImageRepository;
import com.pocket.outbound.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetRecentAdapter implements ReviewGetRecentPort {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewOutBoundMapper reviewOutBoundMapper;

    @Override
    public ReviewGetRecentResponseDto getRecentReview(Long photoboothId) {
        int totalReviewCount = reviewRepository.countByPhotoBoothId(photoboothId);

        List<JpaReview> recentReviews = reviewRepository.findTop3ByPhotoBoothIdOrderByIdDesc(photoboothId);

        List<ReviewPreviewDto> reviewPreviews = recentReviews.stream().map(review -> {
            List<JpaReviewImage> images = reviewImageRepository.findByReviewId(review.getId());
            String imageUrl = images.isEmpty() ? "" : images.get(0).getImage().getImageUrl();
            int imageCount = images.size();

            return reviewOutBoundMapper.toReviewPreviewDto(review, imageUrl, imageCount);
        }).collect(Collectors.toList());

        return new ReviewGetRecentResponseDto(totalReviewCount, reviewPreviews);
    }
}
