package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<JpaUser, Long> {

    Optional<JpaUser> findByUserSubId(String subId);

    Optional<JpaUser> findByUserName(String name);

    Optional<JpaUser> findByUserEmail(String email);
}
