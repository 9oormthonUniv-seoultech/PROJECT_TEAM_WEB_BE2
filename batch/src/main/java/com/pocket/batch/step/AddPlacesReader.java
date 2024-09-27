package com.pocket.batch.step;

import com.pocket.batch.dto.KakaoDocument;
import com.pocket.batch.dto.KakaoLocalResponse;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddPlacesReader implements ItemReader<PhotoBoothFindResponseDto> {

    private final RestTemplate restTemplate;
    private List<PhotoBoothFindResponseDto> dtoList;
    private int nextIndex = 0;
    private boolean isRead = false;

    @Value("${batch.kakao.url}")
    String KAKAO_API_URL;
    @Value("${batch.kakao.client-id}")
    String KAKAO_API_KEY;

    public AddPlacesReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PhotoBoothFindResponseDto read() throws Exception {

        // API가 이미 호출된 경우
        if (isRead && nextIndex >= dtoList.size()) {
            return null;  // 더 이상 읽을 항목이 없음
        }

        // API 호출이 처음인 경우
        if (!isRead) {
            String keyword = "인생네컷";
            String apiUrl = String.format("%s?query=%s", KAKAO_API_URL, keyword);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY); // 인증키 앞에 KakaoAK 추가 필요

            ResponseEntity<KakaoLocalResponse> response = restTemplate.exchange(
                    apiUrl, HttpMethod.GET, new HttpEntity<>(headers), KakaoLocalResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // API 호출 성공 시 dtoList에 저장
                dtoList = mapToDto(response.getBody());
                isRead = true;
            } else {
                throw new IllegalStateException("Kakao API 요청 실패: " + response.getStatusCode());
            }
        }

        // 다음 항목을 반환
        PhotoBoothFindResponseDto nextItem = null;
        if (nextIndex < dtoList.size()) {
            nextItem = dtoList.get(nextIndex);
            nextIndex++;
        }

        return nextItem; // 항목 반환, 더 이상 없으면 null 반환
    }

    private List<PhotoBoothFindResponseDto> mapToDto(KakaoLocalResponse response) {
        List<PhotoBoothFindResponseDto> dtoList = new ArrayList<>();

        for (KakaoDocument document : response.getDocuments()) {
            PhotoBoothFindResponseDto dto = new PhotoBoothFindResponseDto(
                    document.getPlace_name(),
                    document.getRoad_address_name(),
                    Double.parseDouble(document.getX()),
                    Double.parseDouble(document.getY()),
                    mapToBrand(document.getCategory_name())
            );
            dtoList.add(dto);
        }

        return dtoList;
    }

    private PhotoBoothBrand mapToBrand(String categoryName) {
        if (categoryName.contains("인생네컷")) {
            return PhotoBoothBrand.LIFE4CUT;
        }
        return PhotoBoothBrand.UNKNOWN;
    }
}
