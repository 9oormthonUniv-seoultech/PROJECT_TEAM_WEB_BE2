package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.ReviewMypageDetailDto;
import com.pocket.domain.dto.review.ReviewMypageDto;
import com.pocket.domain.port.review.ReviewMypagePort;
import com.pocket.outbound.entity.review.JpaReview;
import com.pocket.outbound.entity.review.JpaReviewImage;
import com.pocket.outbound.repository.review.ReviewImageRepository;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.amazonaws.services.ec2.model.ResourceType.Image;

@AdapterService
@RequiredArgsConstructor
public class ReviewMypageAdapter implements ReviewMypagePort {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public ReviewMypageDto reviewMypage(String userEmail) {
        List<JpaReview> jpaReviews = reviewRepository.findByJpaUser_User_Email(userEmail);

        List<ReviewMypageDetailDto> reviewMypageDetailDtos = new ArrayList<>();

        for (JpaReview jpaReview : jpaReviews) {

            JpaReviewImage reviewImage = reviewImageRepository.findTop1ByReviewIdOrderByReviewIdDesc(jpaReview.getId());
            String imageUrl = (reviewImage != null && reviewImage.getImage() != null) ? reviewImage.getImage().getImageUrl() : "";


            reviewMypageDetailDtos.add(new ReviewMypageDetailDto(
                    jpaReview.getId(),
                    imageUrl,
                    jpaReview.getReview().getCreatedAt().getMonthValue(),
                    jpaReview.getReview().getCreatedAt().getDayOfMonth(),
                    jpaReview.getPhotoBooth().getPhotoBooth().getName(),
                    jpaReview.getPhotoBooth().getRating().doubleValue()
            ));
        }

        return new ReviewMypageDto(jpaReviews.size(), reviewMypageDetailDtos);
    }
}
