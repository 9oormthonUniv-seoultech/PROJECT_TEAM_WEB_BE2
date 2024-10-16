package com.pocket.outbound.repository.review;

import com.pocket.outbound.entity.JpaReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<JpaReview, Long>, ReviewRepositoryCustom {

    int countByPhotoBoothId(Long photoboothId);

    List<JpaReview> findTop2ByPhotoBoothIdOrderByIdDesc(Long photoboothId);

}
