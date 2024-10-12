package com.pocket.inbounds.album.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.usecase.image.AlbumRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/album")
public class AlbumController implements AlbumContollerDocs{

    private final AlbumRegisterUseCase albumRegisterUseCase;

    @PostMapping
    public ApplicationResponse<AlbumRegisterResponseDto> postPhoto(
            @RequestBody AlbumRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserInfoDTO user) {

        AlbumRegisterResponseDto url = albumRegisterUseCase.registerPhotoResponse(requestDto, user.name());
        return ApplicationResponse.ok(url); // presigned Url을 리턴해줘야 함.
    }

}
