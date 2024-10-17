package com.pocket.outbound.adapter.review.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.review.BoothFeatureDto;
import com.pocket.domain.port.review.ReviewGetBoothFeaturePort;
import com.pocket.outbound.entity.review.JpaBoothFeature;
import com.pocket.outbound.repository.review.BoothFeatureRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetBoothFeatureAdapter implements ReviewGetBoothFeaturePort {

    private final BoothFeatureRepository boothFeatureRepository;

    @Override
    public List<BoothFeatureDto> getBoothFeatures() {

        List<JpaBoothFeature> jpaboothFeatures = boothFeatureRepository.findAll();

        List<BoothFeatureDto> boothFeatures = new ArrayList<>();
        for (JpaBoothFeature jpaBoothFeature : jpaboothFeatures) {
            boothFeatures.add(new BoothFeatureDto(jpaBoothFeature.getId(), jpaBoothFeature.getBoothFeature().getDescription()));
        }

        return boothFeatures;
    }
}
