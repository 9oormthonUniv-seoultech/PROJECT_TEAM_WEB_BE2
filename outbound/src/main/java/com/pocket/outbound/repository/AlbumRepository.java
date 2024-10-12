package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<JpaAlbum, Long> {

}
