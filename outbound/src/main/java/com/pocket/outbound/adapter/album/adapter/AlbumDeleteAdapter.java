package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.port.album.AlbumDeletePort;
import com.pocket.outbound.repository.album.AlbumHashTagRepository;
import com.pocket.outbound.repository.album.AlbumRepository;
import com.pocket.outbound.repository.album.AlbumShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AdapterService
@RequiredArgsConstructor
public class AlbumDeleteAdapter implements AlbumDeletePort {

    private final AlbumHashTagRepository albumHashTagRepository;
    private final AlbumRepository albumRepository;
    private final AlbumShareRepository albumShareRepository;

    @Override
    @Transactional
    public void deleteAlbum(Long albumId, String userEmail) {
        albumShareRepository.deleteByAlbumIdAndUser_User_Email(albumId, userEmail);
        albumHashTagRepository.deleteByJpaAlbum_Id(albumId);
        albumRepository.deleteById(albumId);
    }
}
