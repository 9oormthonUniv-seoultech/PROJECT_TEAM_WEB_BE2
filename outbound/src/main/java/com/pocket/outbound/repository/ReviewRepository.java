package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<JpaReview, Long> {

}
