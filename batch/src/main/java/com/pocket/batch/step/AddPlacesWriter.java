package com.pocket.batch.step;

import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddPlacesWriter implements ItemWriter<JpaPhotoBooth> {

    private final PhotoBoothRepository photoBoothRepository;

    @Autowired
    public AddPlacesWriter(PhotoBoothRepository photoBoothRepository) {
        this.photoBoothRepository = photoBoothRepository;
    }

    @Override
    public void write(Chunk<? extends JpaPhotoBooth> chunk) throws Exception {
        for (JpaPhotoBooth item : chunk) {
            if (item == null) {
                throw new IllegalArgumentException("Item cannot be null");
            }

            // 중복 체크: 예를 들어 이름과 좌표가 동일한지를 체크
            Optional<JpaPhotoBooth> existingBooth = photoBoothRepository
                    .findByPhotoBoothNameAndPhotoBoothXAndPhotoBoothY(item.getPhotoBooth().getName(),
                            item.getPhotoBooth().getX(),
                            item.getPhotoBooth().getY());

            // 중복이 없을 경우에만 저장
            if (existingBooth.isEmpty()) {
                photoBoothRepository.save(item);
            }
        }
    }
}
