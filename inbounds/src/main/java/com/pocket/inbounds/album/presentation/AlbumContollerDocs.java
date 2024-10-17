package com.pocket.inbounds.album.presentation;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.album.AlbumRegisterResponseDto;
import com.pocket.domain.dto.user.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Album", description = "Album API")
public interface AlbumContollerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "사진 앨범에 사진 등록", description = "사용자가 사진 앨범에 사진을 등록하는 API")
    ApplicationResponse<AlbumRegisterResponseDto> postPhoto(
            @RequestBody AlbumRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserInfoDTO user);
}
