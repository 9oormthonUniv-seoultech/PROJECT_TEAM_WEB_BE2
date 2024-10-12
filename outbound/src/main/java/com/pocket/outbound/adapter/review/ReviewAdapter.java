package com.pocket.outbound.adapter.review;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterResponseDto;
import com.pocket.domain.entity.image.Image;
import com.pocket.domain.entity.image.ImageType;
import com.pocket.domain.port.review.ReviewRegisterPort;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.entity.JpaReview;
import com.pocket.outbound.entity.JpaReviewImage;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.repository.*;
import lombok.RequiredArgsConstructor;


@AdapterService
@RequiredArgsConstructor
public class ReviewAdapter implements ReviewRegisterPort {

    private final AlbumRepository albumRepository;
    private final PhotoBoothRepository photoBoothRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewOutBoundMapper reviewOutBoundMapper;
    private final ReviewImageRepository reviewImageRepository;


    @Override
    public ReviewRegisterResponseDto registerReview(ReviewRegisterRequestDto dto, String name) {

        JpaPhotoBooth photoBooth = photoBoothRepository.findById(dto.photoboothId())
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        JpaUser jpaUser = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        JpaReview reviewEntity = reviewOutBoundMapper.toJpaReview(dto, photoBooth, jpaUser);
        reviewRepository.save(reviewEntity);



        for (String filePath : dto.filePaths()) {
            Image image = new Image(ImageType.REVIEW);
            image.makeReviewImage(filePath);
            JpaReviewImage reviewImage = JpaReviewImage.builder()
                    .image(image)
                    .review(reviewEntity)
                    .build();

            reviewImageRepository.save(reviewImage);
        }

        // 포토부스의 별점 업데이트
        photoBooth.updateRating(dto.rating());
        photoBoothRepository.save(photoBooth);

        return new ReviewRegisterResponseDto(
                dto.photoboothId(),
                dto.rating(),
                dto.boothFeatures(),
                dto.photoFeatures(),
                dto.filePaths(),
                dto.content()
        );
    }
}
