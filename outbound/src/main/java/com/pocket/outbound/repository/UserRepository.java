package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<JpaUser, Long> {
}
