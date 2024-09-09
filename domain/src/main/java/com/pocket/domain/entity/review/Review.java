package com.pocket.domain.entity.review;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Column(name = "rating")
    private int rating;

    @Column(name = "content")
    private String content;

}
