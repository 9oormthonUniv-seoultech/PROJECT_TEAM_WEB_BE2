package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;

public interface PhotoBoothFindPort {

    PhotoBoothFindResponseDto findById(Long id);

}
