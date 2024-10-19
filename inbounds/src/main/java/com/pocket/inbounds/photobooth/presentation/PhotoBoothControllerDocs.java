package com.pocket.inbounds.photobooth.presentation;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.dto.photobooth.PhotoBoothSearchDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            @PathVariable("keyword") String keyword
    );
}
