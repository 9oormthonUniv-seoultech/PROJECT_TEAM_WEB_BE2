package com.pocket.outbound.entity.album;

import com.pocket.outbound.entity.JpaUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "SHARE")
@Entity
@Builder
@AllArgsConstructor
public class JpaAlbumShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_share_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private JpaAlbum album;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser user;
}
