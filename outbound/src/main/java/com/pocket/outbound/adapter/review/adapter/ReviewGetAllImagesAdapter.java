package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.port.review.ReviewGetAllImagesPort;
import com.pocket.outbound.entity.JpaReviewImage;
import com.pocket.outbound.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetAllImagesAdapter implements ReviewGetAllImagesPort {

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public List<String> getAllImages(Long photoboothId) {

        List<JpaReviewImage> reviewImages = reviewImageRepository.findByReviewPhotoBoothId(photoboothId);

        return reviewImages.stream()
                .map(reviewImage -> reviewImage.getImage().getImageUrl())
                .collect(Collectors.toList());
    }
}
