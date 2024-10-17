package com.pocket.outbound.entity.album;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ALBUM_HASHTAG")
public class JpaAlbumHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_hashtag_map_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private JpaAlbum jpaAlbum;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private JpaHashTag jpaHashtag;

    @Builder
    private JpaAlbumHashTag(JpaHashTag jpaHashtag, JpaAlbum jpaAlbum) {
        this.jpaHashtag = jpaHashtag;
        this.jpaAlbum = jpaAlbum;
    }

}
