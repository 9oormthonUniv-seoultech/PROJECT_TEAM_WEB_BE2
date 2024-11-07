package com.pocket.outbound.adapter.photobooth.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.port.photobooth.PhotoBoothCheckLikePort;
import com.pocket.outbound.entity.photobooth.JpaLike;
import com.pocket.outbound.repository.photobooth.LikeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@AdapterService
@RequiredArgsConstructor
public class PhotoBoothCheckLikeAdapter implements PhotoBoothCheckLikePort {

    private final LikeRepository likeRepository;

    @Override
    public Boolean checkLike(Long photoBoothId, String userEmail) {
        return likeRepository.findByUser_UserEmailAndPhotoBooth_Id(userEmail, photoBoothId).isPresent();
    }

}
