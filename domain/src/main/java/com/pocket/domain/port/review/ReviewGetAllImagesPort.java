package com.pocket.domain.port.review;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewGetAllImagesPort {

    List<String> getAllImages(Long photoboothId, Pageable pageable);

}
