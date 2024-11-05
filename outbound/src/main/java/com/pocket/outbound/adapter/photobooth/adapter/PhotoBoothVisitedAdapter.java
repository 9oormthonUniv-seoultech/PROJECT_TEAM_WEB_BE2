package com.pocket.outbound.adapter.photobooth.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.photobooth.PhotoBoothVisitedDto;
import com.pocket.domain.port.photobooth.PhotoBoothVisitedPort;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.entity.review.JpaReview;
import com.pocket.outbound.repository.album.AlbumRepository;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothVisitedAdapter implements PhotoBoothVisitedPort {

    private final AlbumRepository albumRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<PhotoBoothVisitedDto> getVisitedPhotoBooths(String userEmail) {
        List<JpaAlbum> allAlbums = albumRepository.findByJpaUser_User_Email(userEmail);
        List<JpaReview> allReviews = reviewRepository.findByJpaUser_User_Email(userEmail);

        // 모든 JpaPhotoBooth를 추출하여 Set으로 변환
        Set<JpaPhotoBooth> reviewPhotoBooths = allReviews.stream()
                .map(JpaReview::getPhotoBooth)
                .collect(Collectors.toSet());

        // allAlbums에서 reviewPhotoBooths에 없는 JpaPhotoBooth만 필터링하여 리스트로 수집
        List<PhotoBoothVisitedDto> photoBoothVisitedDtos = allAlbums.stream()
                .filter(album -> !reviewPhotoBooths.contains(album.getPhotoBooth()))
                .map(album -> {
                    JpaPhotoBooth jpaPhotoBooth = album.getPhotoBooth();
                    Long photoboothId = jpaPhotoBooth.getId();
                    String name = jpaPhotoBooth.getPhotoBooth().getName();

                    // JpaAlbum의 createdAt 필드에서 월과 일 정보 추출
                    LocalDate createdAt = album.getImage().getCreatedAt().toLocalDate();
                    Integer month = createdAt.getMonthValue();
                    Integer date = createdAt.getDayOfMonth();

                    return new PhotoBoothVisitedDto(photoboothId, name, month, date);
                })
                .collect(Collectors.toList());

        return photoBoothVisitedDtos;
    }
}
