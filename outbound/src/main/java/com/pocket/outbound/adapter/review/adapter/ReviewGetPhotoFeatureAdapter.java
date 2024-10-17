package com.pocket.outbound.adapter.review.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.PhotoFeatureDto;
import com.pocket.domain.port.review.ReviewGetPhotoFeaturePort;
import com.pocket.outbound.entity.review.JpaPhotoFeature;
import com.pocket.outbound.repository.review.PhotoFeatureRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetPhotoFeatureAdapter implements ReviewGetPhotoFeaturePort {

    private final PhotoFeatureRepository photoFeatureRepository;

    @Override
    public List<PhotoFeatureDto> getPhotoFeatures() {

        List<JpaPhotoFeature> jpaPhotoFeatures = photoFeatureRepository.findAll();

        List<PhotoFeatureDto> photoFeatures = new ArrayList<>();

        for (JpaPhotoFeature jpaPhotoFeature : jpaPhotoFeatures) {
            photoFeatures.add(new PhotoFeatureDto(jpaPhotoFeature.getId(), jpaPhotoFeature.getPhotoFeature().getDescription()));
        }

        return photoFeatures;
    }
}
