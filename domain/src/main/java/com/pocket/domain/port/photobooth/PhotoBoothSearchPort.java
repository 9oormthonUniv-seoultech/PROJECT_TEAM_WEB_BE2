package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothSearchDto;

import java.util.List;

public interface PhotoBoothSearchPort {

    List<PhotoBoothSearchDto> searchPhotoBooth(String keyword);

}
