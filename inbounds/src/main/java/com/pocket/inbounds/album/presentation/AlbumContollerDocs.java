package com.pocket.inbounds.album.presentation;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.album.*;
import com.pocket.domain.dto.user.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "앨범 좋아요", description = "특정 앨범에 좋아요를 표시하는 API")
    ApplicationResponse<String> likeAlbum(@PathVariable("album_id") Long albumId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "월별 앨범 조회", description = "특정 연도와 월에 대한 앨범을 조회하는 API")
    ApplicationResponse<List<AlbumResponseDto>> getAlbumByDate(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @AuthenticationPrincipal UserInfoDTO user);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "브랜드별 앨범 조회", description = "특정 포토부스 브랜드에 대한 앨범을 조회하는 API")
    ApplicationResponse<List<AlbumResponseDto>> getAlbumByBrand(
            @PathVariable("brand_name") String brandName,
            @AuthenticationPrincipal UserInfoDTO user);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "위치 기반 앨범 조회", description = "사용자 위치 주변의 앨범을 조회하는 API")
    ApplicationResponse<List<NearAlbumInfo>> getAlbumByLocation(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @AuthenticationPrincipal UserInfoDTO user);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "앨범 삭제", description = "특정 앨범을 삭제하는 API")
    ApplicationResponse<String> deleteAlbum(@PathVariable("album_id") Long albumId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "해시태그로 앨범 검색", description = "특정 해시태그가 포함된 앨범을 조회하는 API")
    ApplicationResponse<List<AlbumHashtagResponseDto>> getAlbumByHashtag(
            @PathVariable("hashtag") String hashtag,
            @AuthenticationPrincipal UserInfoDTO user);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "즐겨찾기 앨범 검색 (마이페이지)", description = "좋아요가 눌린 앨범을 조회하는 API")
    ApplicationResponse<List<AlbumResponseDto>> getFavoriteAlbums(
            @AuthenticationPrincipal UserInfoDTO user);
}
