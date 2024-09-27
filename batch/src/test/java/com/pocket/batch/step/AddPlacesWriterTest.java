package com.pocket.batch.step;

import com.pocket.domain.entity.photobooth.PhotoBooth;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.Chunk;

import static org.mockito.Mockito.verify;

class AddPlacesWriterTest {

    @InjectMocks
    private AddPlacesWriter addPlacesWriter;

    @Mock
    private PhotoBoothRepository photoBoothRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWrite() throws Exception {
        PhotoBooth photoBooth = PhotoBooth.create(
                "인생네컷 대구동성로1호직영점",  // name
                "대구 중구 동성로2길 55",      // road
                128.59655091389456,            // x
                35.86845663354451,             // y
                PhotoBoothBrand.LIFE4CUT       // photoBoothBrand
        );

        // Create a JpaPhotoBooth instance and set the PhotoBooth
        JpaPhotoBooth jpaPhotoBooth = new JpaPhotoBooth();
        jpaPhotoBooth.setPhotoBooth(photoBooth);

        // Create a chunk with the JpaPhotoBooth instance
        Chunk<JpaPhotoBooth> chunk = new Chunk<>();
        chunk.add(jpaPhotoBooth);

        // Execute the write method
        addPlacesWriter.write(chunk);

        // Verify that the photoBoothRepository's save method was called with the correct JpaPhotoBooth
        verify(photoBoothRepository).save(jpaPhotoBooth);
    }
}
