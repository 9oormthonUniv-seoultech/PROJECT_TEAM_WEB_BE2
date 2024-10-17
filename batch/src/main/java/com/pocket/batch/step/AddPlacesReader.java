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
import java.util.Arrays;
import java.util.List;

@Component
public class AddPlacesReader implements ItemReader<PhotoBoothFindResponseDto> {

    private final RestTemplate restTemplate;
    private List<PhotoBoothFindResponseDto> dtoList;
    private int nextIndex = 0;
    private boolean isRead = false;
    private boolean isLastPage = false;
    private int currentPage = 1;

    @Value("${batch.kakao.url}")
    String KAKAO_API_URL;
    @Value("${batch.kakao.client-id}")
    String KAKAO_API_KEY;

    // 모든 브랜드 목록 (인생네컷 제거)
    private final List<String> keywords = Arrays.asList(
            "포토이즘",
            "포토그레이",
            "돈룩업",
            "그믐달",
            "모노맨션",
            "플랜비스튜디오",
            "포토매틱",
            "하루필름",
            "포토시그니처"
    );

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
            fetchAllBrands(); // 모든 브랜드에 대한 호출
            isRead = true;
        }

        // 다음 항목을 반환
        PhotoBoothFindResponseDto nextItem = null;
        if (nextIndex < dtoList.size()) {
            nextItem = dtoList.get(nextIndex);
            nextIndex++;
        }

        return nextItem; // 항목 반환, 더 이상 없으면 null 반환
    }

    private void fetchAllBrands() {
        for (String keyword : keywords) {
            while (!isLastPage) {
                List<PhotoBoothFindResponseDto> newItems = fetchNextPage(keyword);
                if (newItems.isEmpty()) {
                    isLastPage = true; // 더 이상 데이터가 없는 경우
                } else {
                    if (dtoList == null) {
                        dtoList = new ArrayList<>(); // 첫 번째 호출 시 리스트 초기화
                    }
                    dtoList.addAll(newItems);
                }
            }
            isLastPage = false; // 다음 브랜드 검색을 위해 재설정
            currentPage = 1; // 페이지 초기화
        }
    }

    private List<PhotoBoothFindResponseDto> fetchNextPage(String keyword) {
        // Kakao API의 최대 페이지 수 설정 (최대 45 페이지)
        if (currentPage > 45) {
            isLastPage = true; // 더 이상 요청하지 않도록 설정
            return new ArrayList<>(); // 빈 리스트 반환
        }

        String apiUrl = String.format("%s?query=%s&region=서울&page=%d", KAKAO_API_URL, keyword, currentPage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

        ResponseEntity<KakaoLocalResponse> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, new HttpEntity<>(headers), KakaoLocalResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            currentPage++; // 다음 페이지로 이동
            return mapToDto(response.getBody());
        } else {
            throw new IllegalStateException("Kakao API 요청 실패: " + response.getStatusCode());
        }
    }

    private List<PhotoBoothFindResponseDto> mapToDto(KakaoLocalResponse response) {
        List<PhotoBoothFindResponseDto> dtoList = new ArrayList<>();

        for (KakaoDocument document : response.getDocuments()) {
            PhotoBoothFindResponseDto dto = new PhotoBoothFindResponseDto(
                    document.getPlace_name(),
                    document.getRoad_address_name(),
                    Double.parseDouble(document.getY()),
                    Double.parseDouble(document.getX()),
                    mapToBrand(document.getCategory_name())
            );
            dtoList.add(dto);
        }

        return dtoList;
    }

    private PhotoBoothBrand mapToBrand(String categoryName) {
        if (categoryName.contains("포토이즘")) {
            return PhotoBoothBrand.PHOTOISM;
        } else if (categoryName.contains("포토그레이")) {
            return PhotoBoothBrand.PHOTOGRAY;
        } else if (categoryName.contains("돈룩업")) {
            return PhotoBoothBrand.DONTLXXKUP;
        } else if (categoryName.contains("그믐달")) {
            return PhotoBoothBrand.OLDMOON;
        } else if (categoryName.contains("모노맨션")) {
            return PhotoBoothBrand.MONOMANSION;
        } else if (categoryName.contains("플랜비스튜디오")) {
            return PhotoBoothBrand.PLANBSTUDIO;
        } else if (categoryName.contains("포토매틱")) {
            return PhotoBoothBrand.PHOTOMATIC;
        } else if (categoryName.contains("하루필름")) {
            return PhotoBoothBrand.HARUFLIM;
        } else if (categoryName.contains("포토시그니처")) {
            return PhotoBoothBrand.PHOTOSIGNATURE;
        }
        return PhotoBoothBrand.UNKNOWN;
    }
}
