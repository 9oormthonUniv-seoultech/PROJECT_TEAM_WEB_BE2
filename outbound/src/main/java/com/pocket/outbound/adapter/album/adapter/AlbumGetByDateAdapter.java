package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.album.AlbumResponseDto;
import com.pocket.domain.port.album.AlbumGetByDatePort;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.repository.album.AlbumRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@AdapterService
@RequiredArgsConstructor
public class AlbumGetByDateAdapter implements AlbumGetByDatePort {

    private final AlbumRepository albumRepository;

    @Override
    public List<AlbumResponseDto> getAlbumByDate(Integer year, Integer month) {
        List<JpaAlbum> albums = albumRepository.findByImageYearAndImageMonth(year, month);

        return albums.stream()
                .map(jpaAlbum -> new AlbumResponseDto(
                        jpaAlbum.getImage().getImageUrl(),
                        jpaAlbum.isLiked()))
                .collect(Collectors.toList());

    }
}
