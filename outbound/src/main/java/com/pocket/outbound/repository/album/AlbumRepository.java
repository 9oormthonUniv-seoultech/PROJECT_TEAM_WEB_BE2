package com.pocket.outbound.repository.album;

import com.pocket.outbound.entity.album.JpaAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<JpaAlbum, Long> {

    List<JpaAlbum> findByJpaUser_User_Email(String email);

}

