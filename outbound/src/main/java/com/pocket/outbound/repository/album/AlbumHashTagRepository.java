package com.pocket.outbound.repository.album;

import com.pocket.domain.entity.album.HashTag;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.album.JpaAlbumHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumHashTagRepository extends JpaRepository<JpaAlbumHashTag, Long> {

    List<JpaAlbumHashTag> findByJpaAlbum_JpaUserAndJpaHashtag_HashTag_Content(JpaUser user, String hashTag);

    List<JpaAlbumHashTag> findByJpaAlbum_Id(Long albumId);

    void deleteByJpaAlbum_Id(Long albumId);

}
