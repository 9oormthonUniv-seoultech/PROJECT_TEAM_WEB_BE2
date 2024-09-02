package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<JpaPhoto, Long> {

}
