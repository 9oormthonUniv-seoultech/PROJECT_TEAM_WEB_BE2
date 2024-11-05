package com.pocket.domain.entity.photobooth;

import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;

public enum PhotoBoothBrand {
    LIFE4CUT("인생네컷"),
    PHOTOISM("포토이즘"),
    PHOTOGRAY("포토그레이"),
    DONTLXXKUP("돈룩업"),
    OLDMOON("그믐달"),
    MONOMANSION("모노맨션"),
    PLANBSTUDIO("플랜비스튜디오"),
    PHOTOMATIC("포토매틱"),
    HARUFLIM("하루필름"),
    PHOTOSIGNATURE("포토시그니처"),
    UNKNOWN("존재하지 않음");

    private final String koreanName;

    PhotoBoothBrand(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static PhotoBoothBrand fromKoreanName(String koreanName) {
        for (PhotoBoothBrand brand : PhotoBoothBrand.values()) {
            if (brand.getKoreanName().equals(koreanName)) {
                return brand;
            }
        }
        throw new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTHBRAND_NOT_FOUND);
    }
}