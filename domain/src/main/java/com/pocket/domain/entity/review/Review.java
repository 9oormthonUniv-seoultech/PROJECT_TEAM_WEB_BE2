package com.pocket.domain.entity.review;

import com.pocket.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Column(name = "rating")
    private int rating;

    @Column(name = "content")
    private String content;

}
