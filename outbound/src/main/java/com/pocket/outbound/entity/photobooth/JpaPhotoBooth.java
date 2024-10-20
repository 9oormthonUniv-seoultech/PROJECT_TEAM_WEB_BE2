package com.pocket.outbound.entity.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBooth;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PHOTOBOOTH")
public class JpaPhotoBooth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photoBooth_id")
    private Long id;

    @Embedded
    private PhotoBooth photoBooth;

    public void setPhotoBooth(PhotoBooth photoBooth) {
        this.photoBooth = photoBooth;
    }

    public void updateRating(int newRating) {
        photoBooth.updateRating(newRating);
    }

    public BigDecimal getRating() {
        return photoBooth.getAverageRating();
    }

}
