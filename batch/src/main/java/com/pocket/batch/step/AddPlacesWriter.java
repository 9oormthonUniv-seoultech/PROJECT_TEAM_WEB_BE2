package com.pocket.batch.step;

import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            photoBoothRepository.save(item);
        }
    }
}
