package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.album.AlbumCustomException;
import com.pocket.core.exception.album.AlbumErrorCode;
import com.pocket.domain.port.album.AlbumLikePort;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.repository.album.AlbumRepository;
import lombok.RequiredArgsConstructor;


@AdapterService
@RequiredArgsConstructor
public class AlbumLikeAdapter implements AlbumLikePort {

    private final AlbumRepository albumRepository;

    @Override
    public void likeAlbum(Long albumId) {
        JpaAlbum album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumCustomException(AlbumErrorCode.ALBUM_NOT_FOUND));

        album.toggleLike();
        albumRepository.save(album);
    }

}
