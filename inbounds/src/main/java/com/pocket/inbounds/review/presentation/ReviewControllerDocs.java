package com.pocket.inbounds.review.presentation;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.review.*;
import com.pocket.domain.dto.user.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Review", description = "Review API")
public interface ReviewControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "리뷰 등록", description = "리뷰를 등록하는 API")
    ApplicationResponse<ReviewRegisterResponseDto> postReview(
            @RequestBody ReviewRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserInfoDTO user
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 6개의 리뷰 이미지 조회", description = "특정 포토부스의 최신 6개 리뷰 이미지를 가져오는 API")
    ApplicationResponse<ReviewGet6ImagesResponseDto> getReviewHomeImage(
            @PathVariable("photobooth_id") Long photoboothId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "최신 리뷰 조회", description = "특정 포토부스에 대한 최신 리뷰를 조회하는 API")
    ApplicationResponse<ReviewGetResponseDto> getRecentReview(
            @PathVariable("photobooth_id") Long photoboothId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 전체 리뷰 이미지 조회", description = "특정 포토부스에 대한 전체 리뷰 이미지를 조회하는 API")
    ApplicationResponse<List<String>> getReviewImages(
            @PathVariable("photobooth_id") Long photoboothId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스 특징 조회", description = "특정 포토부스의 특징을 조회하는 API")
    ApplicationResponse<List<BoothFeatureCountDto>> getReviewBoothFeatures(
            @PathVariable("photobooth_id") Long photoboothId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토 특징 조회", description = "특정 포토부스에서 찍힌 사진의 특징을 조회하는 API")
    ApplicationResponse<List<PhotoFeatureCountDto>> getReviewPhotoFeatures(
            @PathVariable("photobooth_id") Long photoboothId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스의 모든 특징 조회", description = "포토부스의 모든 특징을 가져오는 API")
    ApplicationResponse<List<BoothFeatureDto>> getAllBoothFeature();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "포토부스의 모든 사진 특징 조회", description = "포토부스에서 찍힌 모든 사진의 특징을 가져오는 API")
    ApplicationResponse<List<PhotoFeatureDto>> getAllPhotoFeature();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "전체 리뷰 조회", description = "페이징을 이용한 전체 리뷰 조회 API (ex. /api/v1/review/allreviews/336?page=0&size=10&sort=id,desc)")
    ApplicationResponse<ReviewGetResponseDto> getAllReviews(
            @Parameter(description = "포토부스 ID", example = "336")
            @PathVariable("photobooth_id") Long photoboothId,

            @ParameterObject Pageable pageable
    );

}
