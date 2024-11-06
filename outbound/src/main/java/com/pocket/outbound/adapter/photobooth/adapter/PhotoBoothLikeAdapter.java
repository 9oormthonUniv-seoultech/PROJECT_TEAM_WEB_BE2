package com.pocket.outbound.adapter.photobooth.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.port.photobooth.PhotoBoothLikePort;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.photobooth.JpaLike;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.repository.UserRepository;
import com.pocket.outbound.repository.photobooth.LikeRepository;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothLikeAdapter implements PhotoBoothLikePort {

    private final UserRepository userRepository;
    private final PhotoBoothRepository photoBoothRepository;
    private final LikeRepository likeRepository;

    @Override
    public void photoBoothLike(Long photoBoothId, String userEmail) {
        JpaUser jpaUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        JpaPhotoBooth jpaPhotoBooth = photoBoothRepository.findById(photoBoothId)
                .orElseThrow(() -> new PhotoBoothCustomException((PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND)));

        JpaLike jpaLike = JpaLike.createLike(jpaUser, jpaPhotoBooth);
        likeRepository.save(jpaLike);
    }
}
