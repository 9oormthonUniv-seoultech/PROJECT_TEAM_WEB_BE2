package com.pocket.outbound.repository.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoBoothRepository extends JpaRepository<JpaPhotoBooth, Long> {

    Optional<JpaPhotoBooth> findByPhotoBoothNameAndPhotoBoothXAndPhotoBoothY(String name, double x, double y);

    List<JpaPhotoBooth> findByPhotoBoothPhotoBoothBrandIn(List<PhotoBoothBrand> brands);
}
