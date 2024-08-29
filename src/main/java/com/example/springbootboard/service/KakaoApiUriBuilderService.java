package com.example.springbootboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class KakaoApiUriBuilderService {

//    https://dapi.kakao.com/v2/local/search/address.${FORMAT}
    private static final String KAKAO_MAP_API_URL = "https://dapi.kakao.com/v2/local/search/address";
    private static final String KAKAO_Category_API_URL = "https://dapi.kakao.com/v2/local/search/category";


    public URI buildUriByAddress(String address) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(KAKAO_MAP_API_URL)
                .queryParam("query", address)
                .build()
                .encode()
                .toUri();

        log.debug("KakaoApiUriBuilderService :: buildUriByAddress :: address = {}, uri = {}", address, uri);

        return uri;
    }

    public URI buildUriByCategorySearch(double latitude, double longitude, double radius, String category) {
        double meterRadius = radius * 1000;

        return UriComponentsBuilder.fromHttpUrl(KAKAO_Category_API_URL)
                .queryParam("category_group_code", category)
                .queryParam("x", longitude)
                .queryParam("y", latitude)
                .queryParam("radius", meterRadius)
                .queryParam("sort", "distance").encode().build().toUri();
    }

}
