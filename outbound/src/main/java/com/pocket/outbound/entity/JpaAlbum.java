package com.pocket.outbound.entity;

import com.pocket.domain.entity.image.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.pocket.domain.entity.image.ImageType.PHOTO;

@Getter
@NoArgsConstructor
@Table(name = "ALBUM")
@Entity
public class JpaAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    @Embedded
    private Image image = new Image(PHOTO);

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser jpaUser;

    @ManyToOne
    @JoinColumn(name = "photobooth_id")
    private JpaPhotoBooth photoBooth;

}
