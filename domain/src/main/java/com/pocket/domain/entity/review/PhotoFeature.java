package com.pocket.domain.entity.review;

public enum PhotoFeature {
    NO_GLARE("빛번짐 없음"),
    HIGH_DEFINITION("선명한 화질"),
    NATURAL_LIGHT("자연스러운 보정"),
    COOL_FILTERS_AVAILABLE("쿨톤 필터 가능"),
    BRIGHTER_THAN_EXPECTED("생각보다 밝음"),
    DARKER_THAN_EXPECTED("생각보다 어두움"),
    RICH_COLORS("진한 색감");

    private final String description;

    PhotoFeature(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
