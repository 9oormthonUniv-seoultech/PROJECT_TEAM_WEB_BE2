package com.pocket.outbound.entity;

import com.pocket.domain.entity.photobooth.PhotoBooth;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PHOROBOOTH")
public class JpaPhotoBooth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photoBooth_id")
    private Long id;

    @Embedded
    private PhotoBooth photoBooth;

    @OneToMany(mappedBy = "photoBooth", fetch = FetchType.LAZY)
    private List<JpaLike> likes = new ArrayList<>();

}
