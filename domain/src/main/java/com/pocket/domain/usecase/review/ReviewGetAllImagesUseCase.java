package com.pocket.domain.usecase.review;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewGetAllImagesUseCase {

    List<String> getAllImages(Long photoboothId, Pageable pageable);

}
