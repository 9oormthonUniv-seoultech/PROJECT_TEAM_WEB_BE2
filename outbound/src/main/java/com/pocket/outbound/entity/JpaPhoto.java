package com.pocket.outbound.entity;

import com.pocket.domain.entity.image.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.pocket.domain.entity.image.ImageType.PHOTO;

@Getter
@NoArgsConstructor
@Table(name = "IMAGE")
@Entity
public class JpaPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Embedded
    private Image image = new Image(PHOTO);

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser jpaUser;
}
