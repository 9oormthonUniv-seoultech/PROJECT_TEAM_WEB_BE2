package com.pocket.outbound.adapter.album;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.port.album.AlbumRegisterPort;
import com.pocket.outbound.entity.*;
import com.pocket.outbound.repository.*;
import lombok.RequiredArgsConstructor;

@AdapterService
@RequiredArgsConstructor
public class AlbumAdapter implements AlbumRegisterPort {

    private final AlbumRepository albumRepository;
    private final HashTagRepository hashtagRepository;
    private final AlbumHashTagRepository photoHashtagRepository;
    private final PhotoBoothRepository photoBoothRepository;
    private final UserRepository userRepository;
    private final AlbumOutBoundMapper albumOutBoundMapper;

    @Override
    public AlbumRegisterResponseDto registerPhoto(AlbumRegisterRequestDto dto, String name) {

        JpaPhotoBooth photoBooth = photoBoothRepository.findById(dto.photoboothId())
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        JpaUser jpaUser = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        JpaAlbum photoEntity = albumOutBoundMapper.toJpaAlbum(dto, photoBooth, jpaUser);
        albumRepository.save(photoEntity);

        for (String hashtag : dto.hashtag()) {
            JpaHashTag hashtagEntity = albumOutBoundMapper.toJpaHashTag(hashtag, jpaUser);
            JpaAlbumHashTag imageHashtagEntity = albumOutBoundMapper.toJpaAlbumHashTag(photoEntity, hashtagEntity);

            hashtagRepository.save(hashtagEntity);
            photoHashtagRepository.save(imageHashtagEntity);
        }

        return new AlbumRegisterResponseDto(
                dto.photoboothId(),
                dto.year(),
                dto.month(),
                dto.date(),
                dto.hashtag(),
                dto.memo(),
                dto.filePath()
        );
    }
}