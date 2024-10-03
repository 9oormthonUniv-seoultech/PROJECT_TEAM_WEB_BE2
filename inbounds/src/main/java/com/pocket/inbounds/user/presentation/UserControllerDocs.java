package com.pocket.inbounds.user.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.inbounds.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;

@Tag(name = "User", description = "User API")
public interface UserControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "사용자 정보 제공", description = "사용자 정보 전달 API")
    ApplicationResponse<UserResponse> getUserInfo(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO);
}
