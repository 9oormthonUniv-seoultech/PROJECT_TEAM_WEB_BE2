package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.album.AlbumResponseDto;
import com.pocket.domain.port.album.AlbumFavoritePort;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.repository.album.AlbumRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@AdapterService
@RequiredArgsConstructor
public class AlbumFavoriteAdapter implements AlbumFavoritePort {

    private final AlbumRepository albumRepository;

    @Override
    public List<AlbumResponseDto> getFavoriteAlbums(String userEmail) {
        List<JpaAlbum> jpaAlbums = albumRepository.findByJpaUserUserEmail(userEmail);

        List<JpaAlbum> likedAlbums = jpaAlbums.stream()
                .filter(JpaAlbum::isLiked)  // isLiked가 true인 요소만 필터링
                .toList();

        return likedAlbums.stream()
                .map(jpaAlbum -> new AlbumResponseDto(
                        jpaAlbum.getId(),
                        jpaAlbum.getImage().getImageUrl(),
                        jpaAlbum.isLiked()))
                .collect(Collectors.toList());
    }
}
