package com.pocket.outbound.repository;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.outbound.entity.JpaPhotoBooth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoBoothRepository extends JpaRepository<JpaPhotoBooth, Long> {

    List<JpaPhotoBooth> findByPhotoBoothPhotoBoothBrand(PhotoBoothBrand photoBoothBrand);
}
