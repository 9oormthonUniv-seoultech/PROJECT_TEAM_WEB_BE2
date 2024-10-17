package com.pocket.outbound.repository.album;

import com.pocket.outbound.entity.album.JpaHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<JpaHashTag, Long> {
}
