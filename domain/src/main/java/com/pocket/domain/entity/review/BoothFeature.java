package com.pocket.domain.entity.review;


public enum BoothFeature {
    CLEAN_PROPS("깔끔한 소품"),
    PRETTY_SELFIE_ZONE("예쁜 셀카존"),
    SPACIOUS_BOOTH("넓은 부스 공간"),
    SPACIOUS_WAITING_AREA("넓은 대기 공간"),
    OUTPUT_PRINT_AVAILABLE("출력 가능"),
    GOOD_POWDER_ROOM("좋은 파우더룸"),
    CLEAN_BOOTH("청결한 부스"),
    VARIED_BACKGROUNDS("다양한 배경색"),
    VARIED_FRAMES("다양한 프레임");

    private final String description;

    BoothFeature(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}