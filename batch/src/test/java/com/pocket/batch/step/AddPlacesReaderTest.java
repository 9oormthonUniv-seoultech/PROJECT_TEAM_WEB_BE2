package com.pocket.batch.step;

import com.pocket.batch.dto.KakaoDocument;
import com.pocket.batch.dto.KakaoLocalResponse;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AddPlacesReaderTest {

    @InjectMocks
    private AddPlacesReader addPlacesReader;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addPlacesReader.KAKAO_API_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
        addPlacesReader.KAKAO_API_KEY = "KakaoAK your_api_key";
    }

    @Test
    void testRead_SuccessfulResponse() throws Exception {
        // Mock Kakao API response
        KakaoDocument document = new KakaoDocument();
        document.setPlace_name("인생네컷 대구동성로1호직영점");
        document.setRoad_address_name("대구 중구 동성로2길 55");
        document.setX("128.59655091389456");
        document.setY("35.86845663354451");
        document.setCategory_name("문화,예술 > 사진 > 사진관,포토스튜디오 > 즉석사진 > 인생네컷");

        KakaoLocalResponse kakaoResponse = new KakaoLocalResponse();
        kakaoResponse.setDocuments(Arrays.asList(document));

        // Mock the RestTemplate call
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(KakaoLocalResponse.class)))
                .thenReturn(new ResponseEntity<>(kakaoResponse, HttpStatus.OK));

        // Read the first item
        PhotoBoothFindResponseDto result = addPlacesReader.read();
        System.out.println(result);
        assertNotNull(result);
        assertEquals("인생네컷 대구동성로1호직영점", result.name());

        // Read again to check for the end
        PhotoBoothFindResponseDto secondResult = addPlacesReader.read();
        assertNull(secondResult);
    }

    @Test
    void testRead_FailedResponse() {
        // Mock failed response
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(KakaoLocalResponse.class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        // Execute and assert exception
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            addPlacesReader.read();
        });

        assertTrue(exception.getMessage().contains("Kakao API 요청 실패"));
    }

    @Test
    void testRead_AlreadyRead() throws Exception {
        // Mock a successful response
        KakaoDocument document = new KakaoDocument();
        document.setPlace_name("인생네컷 대구동성로1호직영점");
        document.setRoad_address_name("대구 중구 동성로2길 55");
        document.setX("128.59655091389456");
        document.setY("35.86845663354451");
        document.setCategory_name("인생네컷");

        KakaoLocalResponse kakaoResponse = new KakaoLocalResponse();
        kakaoResponse.setDocuments(Arrays.asList(document));

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(KakaoLocalResponse.class)))
                .thenReturn(new ResponseEntity<>(kakaoResponse, HttpStatus.OK));

        // First call (successful)
        PhotoBoothFindResponseDto result = addPlacesReader.read();
        assertNotNull(result);

        // Second call (should return null)
        PhotoBoothFindResponseDto secondResult = addPlacesReader.read();
        assertNull(secondResult);
    }

    @Test
    void testRead_MultipleBrands() throws Exception {
        // Mock Kakao API response for multiple brands
        KakaoDocument document1 = new KakaoDocument();
        document1.setPlace_name("인생네컷 대구동성로1호직영점");
        document1.setRoad_address_name("대구 중구 동성로2길 55");
        document1.setX("128.59655091389456");
        document1.setY("35.86845663354451");
        document1.setCategory_name("문화,예술 > 사진 > 사진관,포토스튜디오 > 즉석사진 > 인생네컷");

        KakaoDocument document2 = new KakaoDocument();
        document2.setPlace_name("포토이즘 여의도점");
        document2.setRoad_address_name("서울특별시 영등포구 여의도동 25-3");
        document2.setX("126.9247");
        document2.setY("37.5249");
        document2.setCategory_name("문화,예술 > 사진 > 사진관,포토스튜디오 > 즉석사진 > 포토이즘");

        KakaoLocalResponse kakaoResponse = new KakaoLocalResponse();
        kakaoResponse.setDocuments(Arrays.asList(document1, document2));

        // Mock the RestTemplate call
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(KakaoLocalResponse.class)))
                .thenReturn(new ResponseEntity<>(kakaoResponse, HttpStatus.OK));

        // Read first item
        PhotoBoothFindResponseDto firstResult = addPlacesReader.read();
        assertNotNull(firstResult);
        assertEquals("인생네컷 대구동성로1호직영점", firstResult.name());

        // Read second item
        PhotoBoothFindResponseDto secondResult = addPlacesReader.read();
        assertNotNull(secondResult);
        assertEquals("포토이즘 여의도점", secondResult.name());

        // Read again to check for the end
        PhotoBoothFindResponseDto thirdResult = addPlacesReader.read();
        assertNull(thirdResult);
    }

    @Test
    void testRead_PageHandling() throws Exception {
        // Mock Kakao API response for pagination
        KakaoDocument document = new KakaoDocument();
        document.setPlace_name("인생네컷 대구동성로1호직영점");
        document.setRoad_address_name("대구 중구 동성로2길 55");
        document.setX("128.59655091389456");
        document.setY("35.86845663354451");
        document.setCategory_name("문화,예술 > 사진 > 사진관,포토스튜디오 > 즉석사진 > 인생네컷");

        KakaoLocalResponse kakaoResponse = new KakaoLocalResponse();
        kakaoResponse.setDocuments(Arrays.asList(document));

        // Mock the RestTemplate call
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(KakaoLocalResponse.class)))
                .thenReturn(new ResponseEntity<>(kakaoResponse, HttpStatus.OK));

        // Read first item
        PhotoBoothFindResponseDto result = addPlacesReader.read();
        assertNotNull(result);

        // Ensure it handles pagination correctly
        // This is a simple demonstration; in a real test, you may want to mock multiple pages
        PhotoBoothFindResponseDto secondResult = addPlacesReader.read();
        assertNull(secondResult);
    }

}

