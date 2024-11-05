package com.pocket.domain.service.album;

import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.dto.album.AlbumResponseDto;
import com.pocket.domain.port.album.AlbumGetByDatePort;
import com.pocket.domain.port.album.AlbumLikePort;
import com.pocket.domain.port.album.AlbumRegisterPort;
import com.pocket.domain.port.file.FileDownloadPort;
import com.pocket.domain.usecase.album.AlbumGetByDateUseCase;
import com.pocket.domain.usecase.album.AlbumLikeUseCase;
import com.pocket.domain.usecase.album.AlbumRegisterUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
public class AlbumService implements AlbumRegisterUseCase, AlbumLikeUseCase, AlbumGetByDateUseCase {

    private final FileDownloadPort fileDownloadPort;
    private final AlbumRegisterPort albumRegisterPort;
    private final AlbumLikePort albumLikePort;
    private final AlbumGetByDatePort albumGetByDatePort;

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

        // presigned URL로 변환
        return albumResponseDtos.stream()
                .map(dto -> {
                    String presignedUrl = dto.photoUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(dto.photoUrl());
                    return new AlbumResponseDto(presignedUrl, dto.like());
                })
                .collect(Collectors.toList());
    }
}