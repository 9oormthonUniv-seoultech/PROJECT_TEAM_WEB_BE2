package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothResponseDto;

public interface PhotoBoothPort {

    PhotoBoothResponseDto findById(Long id);

}
