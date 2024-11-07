package com.pocket.domain.port.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBooth;

public interface PhotoBoothDeleteLikePort {

    void deleteLike(Long photoBoothId, String userEmail);

}
