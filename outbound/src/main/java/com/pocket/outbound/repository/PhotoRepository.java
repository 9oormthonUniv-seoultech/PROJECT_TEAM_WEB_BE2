package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PhotoRepository extends JpaRepository<JpaPhoto, Long> {

}
