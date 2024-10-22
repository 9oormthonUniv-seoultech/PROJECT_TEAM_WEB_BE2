package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.port.review.ReviewGetAllImagesPort;
import com.pocket.outbound.entity.review.JpaReviewImage;
import com.pocket.outbound.repository.review.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetAllImagesAdapter implements ReviewGetAllImagesPort {

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public List<String> getAllImages(Long photoboothId, Pageable pageable) {

        Page<JpaReviewImage> reviewImages = reviewImageRepository.findByReviewPhotoBoothId(photoboothId, pageable);

        return reviewImages.stream()
                .map(reviewImage -> reviewImage.getImage().getImageUrl())
                .collect(Collectors.toList());
    }
}
