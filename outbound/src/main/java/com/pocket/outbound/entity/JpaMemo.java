package com.pocket.outbound.entity;

import com.pocket.domain.entity.album.Memo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "MEMO")
@Entity
public class JpaMemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long id;

    @Embedded
    private Memo memo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser jpaUser;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private JpaAlbum jpaAlbum;
}
