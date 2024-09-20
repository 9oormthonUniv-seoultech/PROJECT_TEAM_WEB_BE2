package com.pocket.outbound.repository;

import com.pocket.outbound.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<JpaUser, Long> {

    Optional<JpaUser> findByUserSubId(String subId);

    Optional<JpaUser> findByUserName(String name);

    Optional<JpaUser> findByUserEmail(String email);
}
