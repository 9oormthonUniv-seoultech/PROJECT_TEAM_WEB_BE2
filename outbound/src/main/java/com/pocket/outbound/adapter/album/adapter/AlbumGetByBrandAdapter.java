package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.album.AlbumResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.domain.port.album.AlbumGetByBrandPort;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.repository.album.AlbumRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class AlbumGetByBrandAdapter implements AlbumGetByBrandPort {

    private final AlbumRepository albumRepository;

    @Override
    public List<AlbumResponseDto> getAlbumByBrand(String brandName) {
        PhotoBoothBrand brand = PhotoBoothBrand.fromKoreanName(brandName);
        List<JpaAlbum> albums = albumRepository.findByPhotoBoothPhotoBoothPhotoBoothBrand(brand);

        return albums.stream()
                .map(jpaAlbum -> new AlbumResponseDto(
                        jpaAlbum.getImage().getImageUrl(),
                        jpaAlbum.isLiked()))
                .collect(Collectors.toList());
    }

}
