package com.pocket.outbound.adapter.album.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.core.util.DistanceCalculator;
import com.pocket.domain.dto.album.NearAlbumInfo;
import com.pocket.domain.port.album.AlbumGetByLocationPort;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.album.JpaAlbum;
import com.pocket.outbound.repository.UserRepository;
import com.pocket.outbound.repository.album.AlbumRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;



@AdapterService
@RequiredArgsConstructor
public class AlbumGetByLocationAdapter implements AlbumGetByLocationPort {

    private final AlbumRepository albumRepository;

    @Override
    public List<NearAlbumInfo> getAlbumByLocation(double currentLat, double currentLon, String userEmail) {
        List<JpaAlbum> allAlbums = albumRepository.findByJpaUserUserEmail(userEmail);

        List<JpaAlbum> albumList = allAlbums.stream()
                .filter(album -> {
                    double distance = DistanceCalculator.haversineDistance(
                            currentLat, currentLon,album.getPhotoBooth().getPhotoBooth().getX(), album.getPhotoBooth().getPhotoBooth().getY()
                            );
                    return distance <= 2.0;
                })
                .toList();

        return albumList.stream()
                .map(jpaAlbum -> new NearAlbumInfo(
                        jpaAlbum.getImage().getImageUrl(),
                        jpaAlbum.getPhotoBooth().getPhotoBooth().getX(),
                        jpaAlbum.getPhotoBooth().getPhotoBooth().getY()
                ))
                .collect(Collectors.toList());
    }
}
