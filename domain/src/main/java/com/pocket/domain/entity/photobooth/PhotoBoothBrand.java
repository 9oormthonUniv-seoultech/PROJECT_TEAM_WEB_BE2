package com.pocket.domain.entity.photobooth;


// JSON으로 변환시 한글명이 반환되도록 하기 위해, @JsonFormat 어노테이션을 지정한다.
public enum PhotoBoothBrand {
    LIFE4CUT("인생네컷"),
    PHOTOISM("포토이즘"),
    PHOTOGRAY("포토그레이"),
    HARUFLIM("하루필름"),
    PHOTOSIGNATURE("포토시그니처");

    final private String name;

    PhotoBoothBrand(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

}
