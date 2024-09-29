package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaPhotoBooth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoBoothRepository extends JpaRepository<JpaPhotoBooth, Long> {

}
