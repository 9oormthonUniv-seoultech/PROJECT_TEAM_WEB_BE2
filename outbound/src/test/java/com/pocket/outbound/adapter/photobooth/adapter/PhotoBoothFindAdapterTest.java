package com.pocket.outbound.adapter.photobooth.adapter;

import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.domain.entity.photobooth.PhotoBooth;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.outbound.adapter.photobooth.mapper.PhotoBoothOutBoundMapper;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoBoothFindAdapterTest {

    @Mock
    private PhotoBoothRepository photoBoothRepository;

    @Mock
    private PhotoBoothOutBoundMapper photoBoothOutBoundMapper;

    @InjectMocks
    private PhotoBoothFindAdapter photoBoothFindAdapter;

    @BeforeEach
    void setUp() {
        // 추가 설정이 필요한 경우 여기에 작성
    }

    @Test
    void getPhotoboothWithin2Km_without_brand() {

        // Given: 현재 위치 설정
        double currentLat = 37.5665;
        double currentLon = 126.9780;
        List<PhotoBoothBrand> brand = null;

        // Sample Photobooth instances
        PhotoBooth boothA = PhotoBooth.create("Booth A", "서울특별시 중구", 37.5665, 126.9780, PhotoBoothBrand.LIFE4CUT);
        PhotoBooth boothB = PhotoBooth.create("Booth B", "서울특별시 종로구", 37.5650, 126.9800, PhotoBoothBrand.DONTLXXKUP);
        PhotoBooth boothC = PhotoBooth.create("Booth C", "서울특별시 강남구", 37.5800, 127.0000, PhotoBoothBrand.MONOMANSION); // 2키로 초과 데이터


        // Sample JpaPhotoBooth instances
        JpaPhotoBooth jpaBooth1 = new JpaPhotoBooth();
        jpaBooth1.setPhotoBooth(boothA);

        JpaPhotoBooth jpaBooth2 = new JpaPhotoBooth();
        jpaBooth2.setPhotoBooth(boothB);

        JpaPhotoBooth jpaBooth3 = new JpaPhotoBooth();
        jpaBooth3.setPhotoBooth(boothC);

        List<JpaPhotoBooth> allPhotobooths = Arrays.asList(jpaBooth1, jpaBooth2, jpaBooth3);

        // Mock repository to return allPhotobooths
        when(photoBoothRepository.findAll()).thenReturn(allPhotobooths);

        // Mock mapper to convert JpaPhotoBooth to NearPhotoBoothInfo
        NearPhotoBoothInfo info1 = new NearPhotoBoothInfo(1L, "Booth A", PhotoBoothBrand.LIFE4CUT, 37.5665, 126.9780);
        NearPhotoBoothInfo info2 = new NearPhotoBoothInfo(2L, "Booth B", PhotoBoothBrand.DONTLXXKUP, 37.5665, 126.9780);
        // Booth C is beyond 2km, so no need to map

        when(photoBoothOutBoundMapper.toDTO(jpaBooth1)).thenReturn(info1);
        when(photoBoothOutBoundMapper.toDTO(jpaBooth2)).thenReturn(info2);
        // No mapping for jpaBooth3 as it should be filtered out

        // When: 메서드 호출
        List<NearPhotoBoothInfo> result = photoBoothFindAdapter.getPhotoboothWithin2Km(currentLat, currentLon, brand);

        // Then: 결과 검증
        assertNotNull(result, "결과는 null이 아니어야 합니다.");
        assertEquals(2, result.size(), "반경 2km 이내의 포토부스는 2개여야 합니다.");
        assertTrue(result.contains(info1), "결과에 Booth A가 포함되어야 합니다.");
        assertTrue(result.contains(info2), "결과에 Booth B가 포함되어야 합니다.");
        assertFalse(result.stream().anyMatch(info -> info.id().equals(3L)), "결과에 Booth C가 포함되어 있지 않아야 합니다.");

        // Verify repository and mapper interactions
        verify(photoBoothRepository, times(1)).findAll();
        verify(photoBoothOutBoundMapper, times(1)).toDTO(jpaBooth1);
        verify(photoBoothOutBoundMapper, times(1)).toDTO(jpaBooth2);
        verify(photoBoothOutBoundMapper, never()).toDTO(jpaBooth3);
    }

    @Test
    void getPhotoboothWithin2Km_with_brand() {

        // Given: 현재 위치 설정
        double currentLat = 37.5665;
        double currentLon = 126.9780;
        List<PhotoBoothBrand> brand = List.of();

        // Sample Photobooth instances
        PhotoBooth boothA = PhotoBooth.create("Booth A", "서울특별시 중구", 37.5665, 126.9780, PhotoBoothBrand.LIFE4CUT);
        PhotoBooth boothB = PhotoBooth.create("Booth B", "서울특별시 종로구", 37.5650, 126.9800, PhotoBoothBrand.DONTLXXKUP);
        PhotoBooth boothC = PhotoBooth.create("Booth C", "서울특별시 강남구", 37.5800, 127.0000, PhotoBoothBrand.MONOMANSION); // 2키로 초과 데이터


        // Sample JpaPhotoBooth instances
        JpaPhotoBooth jpaBooth1 = new JpaPhotoBooth();
        jpaBooth1.setPhotoBooth(boothA);

        JpaPhotoBooth jpaBooth2 = new JpaPhotoBooth();
        jpaBooth2.setPhotoBooth(boothB);

        JpaPhotoBooth jpaBooth3 = new JpaPhotoBooth();
        jpaBooth3.setPhotoBooth(boothC);

        List<JpaPhotoBooth> brandPhotobooths = Arrays.asList(jpaBooth1, jpaBooth2, jpaBooth3);

        // Mock repository to return allPhotobooths
        when(photoBoothRepository.findByPhotoBoothPhotoBoothBrandIn(brand)).thenReturn(brandPhotobooths);

        // Mock mapper to convert JpaPhotoBooth to NearPhotoBoothInfo
        NearPhotoBoothInfo info1 = new NearPhotoBoothInfo(1L, "Booth A", PhotoBoothBrand.LIFE4CUT, 37.5665, 126.9780);
        NearPhotoBoothInfo info2 = new NearPhotoBoothInfo(2L, "Booth B", PhotoBoothBrand.DONTLXXKUP, 37.5665, 126.9780);
        // Booth C is beyond 2km, so no need to map

        when(photoBoothOutBoundMapper.toDTO(jpaBooth1)).thenReturn(info1);
        when(photoBoothOutBoundMapper.toDTO(jpaBooth2)).thenReturn(info2);
        // No mapping for jpaBooth3 as it should be filtered out

        // When: 메서드 호출
        List<NearPhotoBoothInfo> result = photoBoothFindAdapter.getPhotoboothWithin2Km(currentLat, currentLon, brand);

        // Then: 결과 검증
        assertNotNull(result, "결과는 null이 아니어야 합니다.");
        assertEquals(2, result.size(), "반경 2km 이내의 포토부스는 2개여야 합니다.");
        assertFalse(result.stream().anyMatch(info -> info.id().equals(3L)), "결과에 Booth C가 포함되어 있지 않아야 합니다.");
    }
}
