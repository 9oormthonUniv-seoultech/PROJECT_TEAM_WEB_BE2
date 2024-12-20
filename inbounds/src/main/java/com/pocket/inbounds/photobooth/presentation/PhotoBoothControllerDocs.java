package com.pocket.inbounds.photobooth.presentation;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.photobooth.*;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "PhotoBooth", description = "PhotoBooth API")
public interface PhotoBoothControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "해당 id 의 포토부스 정보 제공", description = "해당 id 의 포토부스 정보 전달 API")
    ApplicationResponse<PhotoBoothFindResponseDto> getPhotoBoothById(
            @PathVariable("id") Long id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 이름 조회", description = "해당 id 의 포토부스 이름 전달 API")
    ApplicationResponse<String> getPhotoBoothNameById(
            @PathVariable("id") Long id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "근처 전체 포토부스 정보 제공", description = "사용자의 위치 기반으로 근처 포토부스 목록을 제공하는 API")
    ApplicationResponse<List<NearPhotoBoothInfo>> getAllPhotoBooth(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam(value = "brand", required = false) List<PhotoBoothBrand> brand
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 평점 조회", description = "해당 id의 포토부스에 대한 평점을 제공하는 API")
    ApplicationResponse<BigDecimal> getPhotoBoothRating(
            @PathVariable("id") Long id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 검색", description = "keyword를 주면 해당 keyword가 포함된 이름의 포토부스 id와 이름을 제공하는 API")
    ApplicationResponse<List<PhotoBoothSearchDto>> searchPhotoBooth(
            @RequestParam("keyword") String keyword
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 모달창 정보 조회", description = "포토부스 모달창 정보(이름, 좌표, 특징, 별점, 이미지 개수, 리뷰 개수)")
    ApplicationResponse<PhotoBoothModalDto> getPhotoBoothModal(
            @PathVariable("id") Long id
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "방문한 포토부스 조회", description = "앨범에는 저장됐지만, 리뷰가 작성되지 않은 포토부스 조회")
    ApplicationResponse<List<PhotoBoothVisitedDto>> getPhotoBoothVisited(
            @AuthenticationPrincipal UserInfoDTO user
    );


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 찜하기 기능 (마이페이지)", description = "포토부스 찜하기 기능 (마이페이지에서 확인)")
    ApplicationResponse<String> likePhotoBooth(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserInfoDTO user
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "찜한 포토부스 조회", description = "찜한 포토부스의 이름, 별점, 특징 조회")
    ApplicationResponse<List<PhotoBoothLikeDto>> getPhotoBoothLike(
            @AuthenticationPrincipal UserInfoDTO user
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 찜 여부 확인", description = "찜한 포토부스인지 확인하는 API")
    ApplicationResponse<Boolean> likePhotoBoothCheck(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserInfoDTO user
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 찜 삭제", description = "포토부스 찜 삭제하는 API")
    ApplicationResponse<String> deletePhotoBoothLike(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserInfoDTO user
    );

}
