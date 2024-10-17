package com.pocket.domain.port.review;

import java.util.List;

public interface ReviewGetAllImagesPort {

    List<String> getAllImages(Long photoboothId);

}
