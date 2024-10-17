package com.pocket.outbound.adapter.review.mapper;

import com.pocket.domain.dto.review.ReviewPreviewDto;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.entity.review.Review;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.entity.review.JpaReview;
import com.pocket.outbound.entity.JpaUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReviewOutBoundMapper {

    public JpaReview toJpaReview(ReviewRegisterRequestDto dto, JpaPhotoBooth photoBooth, JpaUser jpaUser) {

        // Review 엔티티 생성
        Review review = new Review(
                dto.rating(),
                dto.content()
        );

        return JpaReview.builder()
                .photoBooth(photoBooth)
                .jpaUser(jpaUser)
                .review(review)
                .build();
    }

    public ReviewPreviewDto toReviewPreviewDto(List<String> features, JpaReview review, String imageUrl, int imageCount) {
        String name = review.getJpaUser().getUser().getName();
        LocalDateTime createdAt = review.getReview().getCreatedAt();
        String year = String.valueOf(createdAt.getYear());
        String month = String.format("%02d", createdAt.getMonthValue());
        String date = String.format("%02d", createdAt.getDayOfMonth());

        return new ReviewPreviewDto(
                review.getId(),
                name,
                year,
                month,
                date,
                review.getReview().getContent(),
                features,
                imageUrl,
                imageCount
        );
    }

}
