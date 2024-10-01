package com.pocket.inbounds.image.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.image.AlbumRegisterRequestDto;
import com.pocket.domain.entity.User;
import com.pocket.domain.usecase.image.PhotoRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/album")
public class ImageController implements ImageControllerDocs {

    private final PhotoRegisterUseCase photoRegisterUseCase;

    @PostMapping
    public ApplicationResponse<String> postPhoto(
            @RequestBody AlbumRegisterRequestDto requestDto,
            @AuthenticationPrincipal User user) {

        String url = photoRegisterUseCase.registerPhotoResponse(requestDto, user);
        return ApplicationResponse.ok(url);// presigned Url을 리턴해줘야 함.
    }

}