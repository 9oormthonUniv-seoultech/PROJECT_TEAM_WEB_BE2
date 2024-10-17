package com.pocket.outbound.repository.album;

import com.pocket.outbound.entity.album.JpaAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<JpaAlbum, Long> {

}
