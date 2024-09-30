package com.pocket.outbound.entity;

import com.pocket.domain.entity.album.HashTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "HASHTAG")
@Entity
public class JpaHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Embedded
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser jpaUser;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private JpaAlbum jpaAlbum;
}
