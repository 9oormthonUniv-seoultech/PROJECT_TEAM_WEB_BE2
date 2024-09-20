package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<JpaReview, Long> {

}
