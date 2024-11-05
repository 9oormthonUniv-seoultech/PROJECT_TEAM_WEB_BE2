package com.pocket.outbound.repository.album;

import com.pocket.outbound.entity.album.JpaAlbumHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumHashTagRepository extends JpaRepository<JpaAlbumHashTag, Long> {

    void deleteByJpaAlbum_Id(Long albumId);

}
