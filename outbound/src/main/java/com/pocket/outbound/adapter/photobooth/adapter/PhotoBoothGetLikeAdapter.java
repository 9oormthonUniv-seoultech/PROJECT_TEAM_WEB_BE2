package com.pocket.outbound.adapter.photobooth.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.photobooth.PhotoBoothLikeDto;
import com.pocket.domain.dto.review.BoothFeatureCountDto;
import com.pocket.domain.dto.review.PhotoFeatureCountDto;
import com.pocket.domain.port.photobooth.PhotoBoothGetLikePort;
import com.pocket.outbound.entity.photobooth.JpaLike;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.repository.photobooth.LikeRepository;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import com.pocket.outbound.repository.review.ReviewBoothFeatureRepository;
import com.pocket.outbound.repository.review.ReviewPhotoFeatureRepository;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothGetLikeAdapter implements PhotoBoothGetLikePort {

    private final ReviewRepository reviewRepository;
    private final LikeRepository likeRepository;
    private final ReviewPhotoFeatureRepository reviewPhotoFeatureRepository;
    private final ReviewBoothFeatureRepository reviewBoothFeatureRepository;

    @Override
    public List<PhotoBoothLikeDto> getLikedPhotos(String userEmail) {
        List<JpaLike> jpaLikes = likeRepository.findByUser_UserEmail(userEmail);


        // JpaPhotoBooth 객체에서 필요한 정보를 추출하여 PhotoBoothLikeDto 리스트로 변환
        List<PhotoBoothLikeDto> likedPhotos = jpaLikes.stream()
                .map(jpaLike -> {
                    JpaPhotoBooth photoBooth = jpaLike.getPhotoBooth();

                    // photoboothId로 해당 포토부스의 리뷰를 다 검색 -> 거기서 개수세기
                    List<Long> reviewIds = reviewRepository.findReviewIdsByPhotoBoothId(photoBooth.getId());

                    List<BoothFeatureCountDto> boothFeatures = reviewBoothFeatureRepository.findTopBoothFeaturesByReviewIds(reviewIds);
                    List<PhotoFeatureCountDto> photoFeatures = reviewPhotoFeatureRepository.findTopPhotoFeaturesByReviewIds(reviewIds);

                    List<String> features = new ArrayList<>();


                    photoFeatures.stream()
                            .map(PhotoFeatureCountDto::featureName)
                            .forEach(features::add);

                    boothFeatures.stream()
                            .map(BoothFeatureCountDto::featureName)
                            .forEach(features::add);

                    // features가 비어 있을 경우 null, 아닐 경우 첫 번째 요소 선택
                    String topFeature = features.isEmpty() ? null : features.get(0);

                    return new PhotoBoothLikeDto(
                            photoBooth.getId(),
                            photoBooth.getPhotoBooth().getName(),
                            photoBooth.getRating(),
                            topFeature,
                            features.size()
                    );
                })
                .toList();

        return likedPhotos;
    }
}
