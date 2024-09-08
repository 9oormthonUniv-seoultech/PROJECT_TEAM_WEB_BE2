package com.pocket.outbound.entity;

import com.pocket.domain.entity.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "IMAGE")
@Entity
public class JpaImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Embedded
    private Image image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser jpaUser;
}
