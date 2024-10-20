package com.pocket.outbound.adapter.photobooth.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.photobooth.PhotoBoothSearchDto;
import com.pocket.domain.port.photobooth.PhotoBoothSearchPort;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothSerachAdapter implements PhotoBoothSearchPort {

    private final PhotoBoothRepository photoBoothRepository;

    public List<PhotoBoothSearchDto> searchPhotoBooth(String keyword) {
        List<JpaPhotoBooth> photoBoothList = photoBoothRepository.findByPhotoBoothNameContaining(keyword);

        return photoBoothList.stream()
                .map(jpaPhotoBooth -> new PhotoBoothSearchDto(
                        jpaPhotoBooth.getId(),
                        jpaPhotoBooth.getPhotoBooth().getName()))
                .collect(Collectors.toList());
    }

}
