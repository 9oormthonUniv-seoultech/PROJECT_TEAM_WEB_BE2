package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.album.AlbumCustomException;
import com.pocket.core.exception.album.AlbumErrorCode;
import com.pocket.core.exception.hashtag.HashTagCustomException;
import com.pocket.core.exception.hashtag.HashTagErrorCode;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.port.album.AlbumRegisterPort;
import com.pocket.domain.port.album.AlbumSharePort;
import com.pocket.outbound.adapter.album.mapper.AlbumOutBoundMapper;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.entity.album.JpaAlbumHashTag;
import com.pocket.outbound.entity.album.JpaAlbumShare;
import com.pocket.outbound.entity.album.JpaHashTag;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.repository.UserRepository;
import com.pocket.outbound.repository.album.AlbumHashTagRepository;
import com.pocket.outbound.repository.album.AlbumRepository;
import com.pocket.outbound.repository.album.AlbumShareRepository;
import com.pocket.outbound.repository.album.HashTagRepository;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AdapterService
@RequiredArgsConstructor
public class AlbumRegisterAdapter implements AlbumRegisterPort, AlbumSharePort {

    private final AlbumRepository albumRepository;
    private final HashTagRepository hashtagRepository;
    private final AlbumHashTagRepository photoHashtagRepository;
    private final PhotoBoothRepository photoBoothRepository;
    private final UserRepository userRepository;
    private final AlbumOutBoundMapper albumOutBoundMapper;
    private final AlbumShareRepository albumShareRepository;
    private final AlbumHashTagRepository albumHashTagRepository;

    @Override
    public AlbumRegisterResponseDto registerPhoto(AlbumRegisterRequestDto dto, String name) {

        // 포토 부스 찾기
        JpaPhotoBooth photoBooth = photoBoothRepository.findById(dto.photoboothId())
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        // 사용자 찾기
        JpaUser jpaUser = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        // 앨범 생성
        JpaAlbum photoEntity = albumOutBoundMapper.toJpaAlbum(dto, photoBooth, jpaUser);
        final JpaAlbum jpaAlbum = albumRepository.save(photoEntity);

        // 해시태그 처리
        for (String hashtag : dto.hashtag()) {
            JpaHashTag hashtagEntity = hashtagRepository.findByHashTag_ContentAndJpaUser_Id(hashtag, jpaUser.getId())
                    .orElseGet(() -> {
                        // 해시태그가 존재하지 않으면 새로 생성
                        JpaHashTag newHashTag = albumOutBoundMapper.toJpaHashTag(hashtag, jpaUser);
                        return hashtagRepository.save(newHashTag);
                    });

            // 앨범-해시태그 매핑 엔티티 생성 및 저장
            JpaAlbumHashTag albumHashtagEntity = albumOutBoundMapper.toJpaAlbumHashTag(photoEntity, hashtagEntity);
            photoHashtagRepository.save(albumHashtagEntity);
        }

        // 응답 DTO 생성 및 반환
        return new AlbumRegisterResponseDto(
                dto.photoboothId(),
                jpaAlbum.getId(),
                dto.year(),
                dto.month(),
                dto.date(),
                dto.hashtag(),
                dto.memo(),
                dto.filePath()
        );
    }

    @Override
    public Long saveShareTable(String email, Long albumId) {

        JpaAlbum album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumCustomException(AlbumErrorCode.ALBUM_NOT_FOUND));
        JpaUser jpaUser = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        JpaAlbumShare share = JpaAlbumShare.builder()
                .album(album)
                .user(jpaUser)
                .build();

        final JpaAlbumShare jpaAlbumShare = albumShareRepository.save(share);
        return jpaAlbumShare.getId();
    }

    @Override
    public void saveNewData(String email, Long token) {

        // albumShare 가져오기
        JpaAlbumShare albumShare = albumShareRepository.findById(token)
                .orElseThrow(() -> new AlbumCustomException(AlbumErrorCode.ALBUM_SHARE_NOT_FOUND));

        // jpaUser 가져오기
        JpaUser jpaUser = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        // albumShare의 user와 jpaUser가 같은지 비교
        if (albumShare.getUser().equals(jpaUser)) {
            throw new AlbumCustomException(AlbumErrorCode.USER_ALREADY_OWNED_ALBUM);
        }

        // 앨범 저장
        JpaAlbum album = albumRepository.findById(albumShare.getAlbum().getId())
                .orElseThrow(() -> new AlbumCustomException(AlbumErrorCode.ALBUM_NOT_FOUND));

        JpaAlbum newAlbum = JpaAlbum.builder()
                .image(album.getImage())
                .jpaUser(jpaUser)
                .photoBooth(album.getPhotoBooth())
                .isLiked(album.isLiked())
                .memo(album.getMemo())
                .build();
        albumRepository.save(newAlbum);

        // 해시태그들 저장
        List<JpaAlbumHashTag> albumHashTagList = albumHashTagRepository.findByJpaAlbum_Id(album.getId());

        for (JpaAlbumHashTag a : albumHashTagList) {
            JpaHashTag hashTag = hashtagRepository.findById(a.getJpaHashtag().getId())
                    .orElseThrow(() -> new HashTagCustomException(HashTagErrorCode.HASHTAG_NOT_FOUND));

            JpaHashTag newTag = JpaHashTag.builder()
                    .hashTag(hashTag.getHashTag())
                    .jpaUser(jpaUser)
                    .build();

            hashtagRepository.save(newTag);
        }
    }

}