package com.pocket.outbound.entity;

import com.pocket.domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private User user;

    public static JpaUser createUser(User user) {

        JpaUser jpaUser = new JpaUser();
        jpaUser.user = user;
        return jpaUser;
    }
}