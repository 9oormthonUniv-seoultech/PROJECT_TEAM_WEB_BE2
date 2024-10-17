package com.pocket.outbound.entity.review;


import com.pocket.domain.entity.review.PhotoFeature;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PHOTOFEATURE")
@AllArgsConstructor
public class JpaPhotoFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photofeature_id")
    private Long id;

    @Embedded
    private PhotoFeature photoFeature;

}
