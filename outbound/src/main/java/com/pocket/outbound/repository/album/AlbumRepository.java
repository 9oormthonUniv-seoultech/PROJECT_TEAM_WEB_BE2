package com.pocket.outbound.repository.album;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.outbound.entity.album.JpaAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<JpaAlbum, Long> {

    List<JpaAlbum> findByPhotoBoothPhotoBoothPhotoBoothBrand(PhotoBoothBrand brand);

    List<JpaAlbum> findByImageYearAndImageMonth(Integer year, Integer month);

}
