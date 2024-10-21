package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewGetResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewGetAllUseCase {

    ReviewGetResponseDto getAllReviews(Long photoboothId, Pageable pageable);

}
