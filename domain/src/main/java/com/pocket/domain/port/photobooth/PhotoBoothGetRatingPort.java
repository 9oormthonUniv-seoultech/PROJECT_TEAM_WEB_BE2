package com.pocket.domain.port.photobooth;

import java.math.BigDecimal;

public interface PhotoBoothGetRatingPort {

    BigDecimal getRating(Long photoboothId);

}
