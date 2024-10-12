package com.pocket.outbound.adapter.album;

import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.entity.album.HashTag;
import com.pocket.domain.entity.album.Memo;
import com.pocket.domain.entity.image.Image;
import com.pocket.domain.entity.image.ImageType;
import com.pocket.outbound.entity.JpaAlbum;
import com.pocket.outbound.entity.JpaAlbumHashTag;
import com.pocket.outbound.entity.JpaHashTag;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.JpaPhotoBooth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumOutBoundMapper {

    public JpaAlbum toJpaAlbum(AlbumRegisterRequestDto dto, JpaPhotoBooth photoBooth, JpaUser jpaUser) {
        Memo memo = new Memo(dto.memo());
        Image image = new Image(ImageType.PHOTO);
        image.makeAlbumImage(dto, dto.filePath());

        return JpaAlbum.builder()
                .photoBooth(photoBooth)
                .jpaUser(jpaUser)
                .memo(memo)
                .image(image)
                .build();
    }

    public JpaHashTag toJpaHashTag(String hashtag, JpaUser jpaUser) {
        HashTag hashTag = new HashTag(hashtag);
        return JpaHashTag.builder()
                .jpaUser(jpaUser)
                .hashTag(hashTag)
                .build();
    }

    public JpaAlbumHashTag toJpaAlbumHashTag(JpaAlbum jpaAlbum, JpaHashTag jpaHashtag) {
        return JpaAlbumHashTag.builder()
                .jpaAlbum(jpaAlbum)
                .jpaHashtag(jpaHashtag)
                .build();
    }
}