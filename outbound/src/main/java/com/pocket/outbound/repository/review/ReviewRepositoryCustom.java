package com.pocket.outbound.repository.review;

import com.pocket.outbound.adapter.review.dto.BoothFeatureDTO;
import com.pocket.outbound.adapter.review.dto.PhotoFeatureDTO;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<BoothFeatureDTO> findTop4ByBoothFeature(Long photoboothId);

    List<PhotoFeatureDTO> findTop4ByPhotoFeature(Long photoboothId);

}
