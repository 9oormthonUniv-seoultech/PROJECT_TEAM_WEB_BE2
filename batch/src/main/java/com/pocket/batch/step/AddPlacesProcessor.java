package com.pocket.batch.step;

import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBooth;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AddPlacesProcessor implements ItemProcessor<PhotoBoothFindResponseDto, JpaPhotoBooth> {

    @Override
    public JpaPhotoBooth process(PhotoBoothFindResponseDto dto) throws Exception {
        // DTO를 사용하여 PhotoBooth 객체 생성
        PhotoBooth photoBooth = PhotoBooth.create(
                dto.name(),
                dto.road(),
                dto.x(),
                dto.y(),
                dto.photoBoothBrand()
        );

        // JpaPhotoBooth 객체 생성 및 PhotoBooth 설정
        JpaPhotoBooth jpaPhotoBooth = new JpaPhotoBooth();
        jpaPhotoBooth.setPhotoBooth(photoBooth);

        return jpaPhotoBooth; // 변환된 객체 반환
    }
}
