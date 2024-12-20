package com.pocket.outbound.entity.album;

import com.pocket.domain.entity.album.Memo;
import com.pocket.domain.entity.image.Image;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.entity.JpaUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.pocket.domain.entity.image.ImageType.PHOTO;

@Getter
@NoArgsConstructor
@Table(name = "ALBUM")
@Entity
@Builder
@AllArgsConstructor
public class JpaAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    @Embedded
    private Image image = new Image(PHOTO);

    @Embedded
    private Memo memo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser jpaUser;

    @ManyToOne
    @JoinColumn(name = "photobooth_id")
    private JpaPhotoBooth photoBooth;

    private boolean isLiked;

    public void toggleLike() {
        this.isLiked = !this.isLiked;
    }

}
