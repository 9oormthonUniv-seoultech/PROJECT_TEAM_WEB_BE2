package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<JpaHashTag, Long> {
}
