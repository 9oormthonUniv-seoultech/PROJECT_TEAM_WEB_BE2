package com.pocket.batch.step;

import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.outbound.entity.JpaPhotoBooth;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddPlacesProcessorTest {

    private final AddPlacesProcessor addPlacesProcessor = new AddPlacesProcessor();

    @Test
    void testProcess() throws Exception {
        // Sample DTO
        PhotoBoothFindResponseDto dto = new PhotoBoothFindResponseDto(
                "인생네컷 대구동성로1호직영점",
                "대구 중구 동성로2길 55",
                128.59655091389456,
                35.86845663354451,
                PhotoBoothBrand.LIFE4CUT
        );

        // Process the DTO
        JpaPhotoBooth result = addPlacesProcessor.process(dto);

        // Assertions
        assertEquals("인생네컷 대구동성로1호직영점", result.getPhotoBooth().getName());
        assertEquals("대구 중구 동성로2길 55", result.getPhotoBooth().getRoad());
        assertEquals(128.59655091389456, result.getPhotoBooth().getX());
        assertEquals(35.86845663354451, result.getPhotoBooth().getY());
        assertEquals(PhotoBoothBrand.LIFE4CUT, result.getPhotoBooth().getPhotoBoothBrand());
    }
}
