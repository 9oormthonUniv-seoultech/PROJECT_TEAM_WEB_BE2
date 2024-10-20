package com.pocket.domain.usecase.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothSearchDto;

import java.util.List;

public interface PhotoBoothSearchUseCase {

    List<PhotoBoothSearchDto> searchPhotoBooth(String keyword);

}
