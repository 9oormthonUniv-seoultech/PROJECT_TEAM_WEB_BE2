package com.pocket.outbound.adapter.album;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.core.image.service.FileService;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.entity.album.HashTag;
import com.pocket.domain.entity.album.Memo;
import com.pocket.domain.entity.image.Image;
import com.pocket.domain.entity.image.ImageType;
import com.pocket.domain.port.album.AlbumRegisterPort;
import com.pocket.outbound.entity.*;
import com.pocket.outbound.repository.*;
import lombok.RequiredArgsConstructor;

@AdapterService
@RequiredArgsConstructor
public class AlbumAdapter implements AlbumRegisterPort {

    private final PhotoRepository photoRepository;
    private final HashTagRepository hashtagRepository;
    private final AlbumHashTagRepository photoHashtagRepository;
    private final PhotoBoothRepository photoBoothRepository;
    private final UserRepository userRepository;
    private final AlbumMapper albumMapper;

    @Override
    public AlbumRegisterResponseDto registerPhoto(AlbumRegisterRequestDto dto, String name) {

        JpaPhotoBooth photoBooth = photoBoothRepository.findById(dto.photoboothId())
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        JpaUser jpaUser = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        JpaAlbum photoEntity = albumMapper.toJpaAlbum(dto, photoBooth, jpaUser);
        photoRepository.save(photoEntity);

        for (String hashtag : dto.hashtag()) {
            JpaHashTag hashtagEntity = albumMapper.toJpaHashTag(hashtag, jpaUser);
            JpaAlbumHashTag imageHashtagEntity = albumMapper.toJpaAlbumHashTag(photoEntity, hashtagEntity);

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