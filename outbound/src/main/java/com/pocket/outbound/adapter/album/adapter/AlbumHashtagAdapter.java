package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.dto.album.AlbumHashtagResponseDto;
import com.pocket.domain.port.album.AlbumHashtagPort;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.album.JpaAlbumHashTag;
import com.pocket.outbound.repository.UserRepository;
import com.pocket.outbound.repository.album.AlbumHashTagRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class AlbumHashtagAdapter implements AlbumHashtagPort {

    private final UserRepository userRepository;
    private final AlbumHashTagRepository albumHashTagRepository;

    @Override
    public List<AlbumHashtagResponseDto> getAlbumByHashtag(String hashtag, String userEmail) {
        JpaUser user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        List<JpaAlbumHashTag> albumHashTags = albumHashTagRepository.findByJpaAlbum_JpaUserAndJpaHashtag_HashTag_Content(user, hashtag);

        return albumHashTags.stream()
                .map(jpaAlbumHashTag -> {
                    var album = jpaAlbumHashTag.getJpaAlbum();

                    // 해당 앨범과 관련된 해시태그 목록 가져오기
                    List<String> hashtags = albumHashTagRepository.findByJpaAlbum_Id(album.getId()).stream()
                            .map(tag -> tag.getJpaHashtag().getHashTag().getContent())
                            .collect(Collectors.toList());

                    // AlbumHashtagResponseDto 구성
                    return new AlbumHashtagResponseDto(
                            album.getId(),
                            album.getImage().getImageUrl(),
                            hashtags,
                            album.getImage().getYear(),
                            album.getImage().getMonth(),
                            album.getImage().getDate(),
                            album.getMemo().getContent(),
                            album.isLiked()
                    );
                })
                .collect(Collectors.toList());
    }
}
