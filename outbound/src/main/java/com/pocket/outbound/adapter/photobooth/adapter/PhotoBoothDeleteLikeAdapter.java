package com.pocket.outbound.adapter.photobooth.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.domain.port.photobooth.PhotoBoothDeleteLikePort;
import com.pocket.outbound.entity.photobooth.JpaLike;
import com.pocket.outbound.repository.photobooth.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothDeleteLikeAdapter implements PhotoBoothDeleteLikePort {

    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void deleteLike(Long photoBoothId, String userEmail) {
        Optional<JpaLike> entity = likeRepository.findByUser_UserEmailAndPhotoBooth_Id(userEmail, photoBoothId);
        if (entity.isPresent()) {
            likeRepository.delete(entity.get());
        } else {
            throw new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTHLIKE_NOT_FOUND);
        }
    }
}
