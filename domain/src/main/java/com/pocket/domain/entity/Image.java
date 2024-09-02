package com.pocket.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable // 이거
public class Image extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ImageType type;

    @Column(name = "image_url")
    private String imageUrl;
}