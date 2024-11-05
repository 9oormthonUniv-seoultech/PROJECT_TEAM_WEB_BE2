package com.pocket.domain.service.album;

import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.dto.album.AlbumResponseDto;
import com.pocket.domain.dto.album.NearAlbumInfo;
import com.pocket.domain.port.album.*;
import com.pocket.domain.port.file.FileDownloadPort;
import com.pocket.domain.usecase.album.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
public class AlbumService implements AlbumRegisterUseCase, AlbumLikeUseCase, AlbumGetByDateUseCase, AlbumGetByBrandUseCase, AlbumGetByLocationUseCase {

    private final FileDownloadPort fileDownloadPort;
    private final AlbumRegisterPort albumRegisterPort;
    private final AlbumLikePort albumLikePort;
    private final AlbumGetByDatePort albumGetByDatePort;
    private final AlbumGetByBrandPort albumGetByBrandPort;
    private final AlbumGetByLocationPort albumGetByLocationPort;

    public AlbumRegisterResponseDto registerPhotoResponse(AlbumRegisterRequestDto albumRegisterRequestDto, String name) {
        return albumRegisterPort.registerPhoto(albumRegisterRequestDto, name);
    }

    @Override
    public void likeAlbum(Long albumId) {
        albumLikePort.likeAlbum(albumId);
    }

    @Override
    public List<AlbumResponseDto> getAlbumByDate(Integer year, Integer month) {
        List<AlbumResponseDto> albumResponseDtos = albumGetByDatePort.getAlbumByDate(year, month);

        return albumResponseDtos.stream()
                .map(dto -> {
                    String presignedUrl = dto.photoUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(dto.photoUrl());
                    return new AlbumResponseDto(presignedUrl, dto.like());
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<AlbumResponseDto> getAlbumByBrand(String brandName) {
        List<AlbumResponseDto> albumResponseDtos = albumGetByBrandPort.getAlbumByBrand(brandName);

        return albumResponseDtos.stream()
                .map(dto -> {
                    String presignedUrl = dto.photoUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(dto.photoUrl());
                    return new AlbumResponseDto(presignedUrl, dto.like());
                })
                .collect(Collectors.toList());
    }

    public List<NearAlbumInfo> getAlbumByLocation(double currentLat, double currentLon, String userEmail) {
        List<NearAlbumInfo> nearAlbumInfos = albumGetByLocationPort.getAlbumByLocation(currentLat, currentLon, userEmail);

        return nearAlbumInfos.stream()
                .map(dto -> {
                    String presignedUrl = dto.photoUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(dto.photoUrl());
                    return new NearAlbumInfo(presignedUrl, dto.x(), dto.y());
                })
                .collect(Collectors.toList());
    }

}