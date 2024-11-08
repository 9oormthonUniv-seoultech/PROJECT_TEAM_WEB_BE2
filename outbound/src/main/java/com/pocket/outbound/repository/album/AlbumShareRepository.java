package com.pocket.outbound.repository.album;

import com.pocket.outbound.entity.album.JpaAlbumShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumShareRepository extends JpaRepository<JpaAlbumShare, Long> {

    void deleteByAlbumIdAndUser_User_Email(Long albumId, String userEmail);

}
