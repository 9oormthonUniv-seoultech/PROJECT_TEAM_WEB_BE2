package com.pocket.domain.entity.image;

import com.pocket.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable // 이거
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ImageType type;

    @Column(name = "image_url")
    private String imageUrl;


    // 이미지 타입을 지정하는 생성자를 만들어서
    // 각각 PHOTO, REVIEW, PROFILE을 설정해주는 방식
    public Image(ImageType type) {
        this.type = type;
    }
}