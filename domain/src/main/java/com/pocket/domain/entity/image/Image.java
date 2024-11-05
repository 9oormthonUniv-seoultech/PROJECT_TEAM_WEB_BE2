package com.pocket.domain.entity.image;

import com.pocket.domain.dto.album.AlbumRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ImageType type;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer year;
    private Integer month;
    private Integer date;

    public Image(ImageType type) {
        this.type = type;
    }

    public void makeAlbumImage(AlbumRegisterRequestDto dto, String filePath) {
        this.type = ImageType.PHOTO;
        this.imageUrl = filePath;
        this.year = Integer.parseInt(dto.year());
        this.month = Integer.parseInt(dto.month());
        this.date = Integer.parseInt(dto.date());
    }

    public void makeReviewImage(String filePath) {
        this.type = ImageType.REVIEW;
        this.imageUrl = filePath;
    }
}