package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaAlbumHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumHashTagRepository extends JpaRepository<JpaAlbumHashTag, Long> {
}
