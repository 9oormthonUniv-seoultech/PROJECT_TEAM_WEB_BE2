package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.ReviewGetResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewGetAllPort {

    ReviewGetResponseDto getAllReviews(Long photoboothId, Pageable pageable);

}
