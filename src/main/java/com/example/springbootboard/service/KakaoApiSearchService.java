package com.example.springbootboard.service;

import com.example.springbootboard.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    public KakaoApiResponseDto getKakaoResponse(String address) {
        if (ObjectUtils.isEmpty(address)) throw new RuntimeException("Address is null.");

        URI uri = kakaoApiUriBuilderService.buildUriByAddress(address);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, requestEntity, KakaoApiResponseDto.class).getBody();
    }

}
