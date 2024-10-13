package com.pocket.outbound.adapter.review.mapper;

import com.pocket.domain.dto.review.ReviewPreviewDto;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.entity.review.BoothFeature;
import com.pocket.domain.entity.review.PhotoFeature;
import com.pocket.domain.entity.review.Review;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.entity.JpaReview;
import com.pocket.outbound.entity.JpaUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ReviewOutBoundMapper {

    public JpaReview toJpaReview(ReviewRegisterRequestDto dto, JpaPhotoBooth photoBooth, JpaUser jpaUser) {

        // 한글로 받은 photoFeatures를 PhotoFeature enum으로 변환
        List<PhotoFeature> photoFeatures = dto.photoFeatures().stream()
                .map(PhotoFeature::fromDescription)  // 한글 설명을 enum으로 변환
                .collect(Collectors.toList());

        // 한글로 받은 boothFeatures를 BoothFeature enum으로 변환
        List<BoothFeature> boothFeatures = dto.boothFeatures().stream()
                .map(BoothFeature::fromDescription)  // 한글 설명을 enum으로 변환
                .collect(Collectors.toList());

        // Review 엔티티 생성
        Review review = new Review(
                dto.rating(),
                dto.content(),
                boothFeatures,
                photoFeatures
        );

        return JpaReview.builder()
                .photoBooth(photoBooth)
                .jpaUser(jpaUser)
                .review(review)
                .build();
    }

    // 문자열을 PhotoFeature enum으로 변환
    private PhotoFeature convertToPhotoFeature(String feature) {
        return PhotoFeature.valueOf(feature.toUpperCase().replace(" ", "_"));
    }

    // 문자열을 BoothFeature enum으로 변환
    private BoothFeature convertToBoothFeature(String feature) {
        return BoothFeature.valueOf(feature.toUpperCase().replace(" ", "_"));
    }

    public ReviewPreviewDto toReviewPreviewDto(JpaReview review, String imageUrl, int imageCount) {
        String name = review.getJpaUser().getUser().getName();
        LocalDateTime createdAt = review.getReview().getCreatedAt();
        String year = String.valueOf(createdAt.getYear());
        String month = String.format("%02d", createdAt.getMonthValue());
        String date = String.format("%02d", createdAt.getDayOfMonth());

        List<String> features = Stream.concat(
                review.getReview().getBoothFeatures().stream().map(BoothFeature::getDescription),
                review.getReview().getPhotoFeatures().stream().map(PhotoFeature::getDescription)
        ).collect(Collectors.toList());

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
