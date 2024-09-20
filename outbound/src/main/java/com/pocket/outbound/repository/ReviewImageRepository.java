package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImageRepository extends JpaRepository<JpaReviewImage, Long> {

}
