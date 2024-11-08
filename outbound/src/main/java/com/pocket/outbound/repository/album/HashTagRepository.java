package com.pocket.outbound.repository.album;

import com.pocket.outbound.entity.album.JpaHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<JpaHashTag, Long> {

    Optional<JpaHashTag> findByHashTag_ContentAndJpaUser_Id(String hashtag, Long userId);
}
