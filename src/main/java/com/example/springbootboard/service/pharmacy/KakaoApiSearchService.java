package com.example.springbootboard.service.pharmacy;

import com.example.springbootboard.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoApiSearchService {

    private final RestTemplate restTemplate;
    private final KakaoApiUriBuilderService kakaoApiUriBuilderService;

//    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey = "3338ac005325113c2116a49e64856631";

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000)
    )
    public KakaoApiResponseDto getKakaoResponse(String address) {
        if (ObjectUtils.isEmpty(address)) throw new RuntimeException("Address is null.");

        URI uri = kakaoApiUriBuilderService.buildUriByAddress(address);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, requestEntity, KakaoApiResponseDto.class).getBody();
    }

    // Recover when retry fails
    @Recover
    public KakaoApiResponseDto recover(RuntimeException e, String address) {
        log.error("Cannot connect to Kakao Server. address = {}, exception = {}", address, e.getMessage());

        return null;
    }

}
