package com.pocket.outbound.entity.review;


import com.pocket.domain.entity.review.BoothFeature;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOTHFEATURE")
@AllArgsConstructor
public class JpaBoothFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boothfeature_id")
    private Long id;

    @Embedded
    private BoothFeature boothFeature;
}
