package com.pocket.domain.port.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBooth;

public interface PhotoBoothCheckLikePort {

    Boolean checkLike(Long photoBoothId, String userEmail);

}
