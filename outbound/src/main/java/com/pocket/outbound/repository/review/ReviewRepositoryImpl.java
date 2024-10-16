package com.pocket.outbound.repository.review;

import com.pocket.outbound.adapter.review.dto.BoothFeatureDTO;
import com.pocket.outbound.adapter.review.dto.PhotoFeatureDTO;
import com.pocket.outbound.entity.QJpaReview;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<BoothFeatureDTO> findTop4ByBoothFeature(Long photoboothId) {
        QJpaReview review = QJpaReview.jpaReview;

        return queryFactory
                .select(Projections.constructor(BoothFeatureDTO.class,
                        review.review.boothFeatures,
                        review.count()))
                .from(review)
                .where(review.photoBooth.id.eq(photoboothId))
                .groupBy(review.review.boothFeatures)
                .orderBy(review.count().desc())
                .limit(4)
                .fetch();
    }

    @Override
    public List<PhotoFeatureDTO> findTop4ByPhotoFeature(Long photoboothId) {
        QJpaReview review = QJpaReview.jpaReview;

        return queryFactory
                .select(Projections.constructor(PhotoFeatureDTO.class,
                        review.review.photoFeatures,         // pf (사진 기능)
                        review.count()))       // COUNT(r) as featureCount
                .from(review)
                .where(review.photoBooth.id.eq(photoboothId))
                .groupBy(review.review.photoFeatures)
                .orderBy(review.count().desc())
                .limit(4)
                .fetch();
    }
}
