package com.pocket.inbounds.album.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.dto.album.AlbumResponseDto;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.usecase.album.AlbumGetByDateUseCase;
import com.pocket.domain.usecase.album.AlbumLikeUseCase;
import com.pocket.domain.usecase.album.AlbumRegisterUseCase;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/album")
public class AlbumController implements AlbumContollerDocs{

    private final AlbumRegisterUseCase albumRegisterUseCase;
    private final AlbumLikeUseCase albumLikeUseCase;
    private final AlbumGetByDateUseCase albumGetByDateUseCase;

    @PostMapping
    public ApplicationResponse<AlbumRegisterResponseDto> postPhoto(
            @RequestBody AlbumRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserInfoDTO user) {

        AlbumRegisterResponseDto response = albumRegisterUseCase.registerPhotoResponse(requestDto, user.name());
        return ApplicationResponse.ok(response);
    }


    // 좋아요 기능
    @PostMapping("/like/{album_id}")
    public ApplicationResponse<String> likeAlbum(
            @PathVariable("album_id") Long albumId
    ) {
        albumLikeUseCase.likeAlbum(albumId);
        return ApplicationResponse.ok("Success");
    }


    // 월별 조회
    @GetMapping("/date/{year}/{month}")
    public ApplicationResponse<List<AlbumResponseDto>> getAlbumByDate(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ) {
        List<AlbumResponseDto> response = albumGetByDateUseCase.getAlbumByDate(year, month);
        return ApplicationResponse.ok(response);
    }


    // 포토부스별 조회



    // 위치별 조회?


    // 해시태그 검색 기능


    // 삭제 기능


}
