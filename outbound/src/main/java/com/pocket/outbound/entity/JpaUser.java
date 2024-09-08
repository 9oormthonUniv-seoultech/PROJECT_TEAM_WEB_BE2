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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JpaPhoto> images = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<JpaLike> likes = new ArrayList<>();

    public static JpaUser createUser(User user) {

        JpaUser jpaUser = new JpaUser();
        jpaUser.user = user;
        return jpaUser;
    }
}