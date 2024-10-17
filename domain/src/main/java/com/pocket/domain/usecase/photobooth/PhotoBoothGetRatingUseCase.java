package com.pocket.domain.usecase.photobooth;

import java.math.BigDecimal;

public interface PhotoBoothGetRatingUseCase {

    BigDecimal getPhotoBoothRating(Long photoboothId);

}
