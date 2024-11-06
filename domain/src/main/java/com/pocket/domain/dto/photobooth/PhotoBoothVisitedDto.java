package com.pocket.domain.dto.photobooth;

public record PhotoBoothVisitedDto(
        Long photoboothId,
        String name,
        Integer month,
        Integer date
) {
}
