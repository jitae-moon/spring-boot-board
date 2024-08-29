package com.example.springbootboard.service;

import com.example.springbootboard.domain.Distance;
import com.example.springbootboard.dto.DocumentDto;
import com.example.springbootboard.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PharmacyRecommendationService {

    private final KakaoApiSearchService kakaoApiSearchService;
    private final DistanceService distanceService;

    public void recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoResponse = kakaoApiSearchService.getKakaoResponse(address);

        if (ObjectUtils.isEmpty(kakaoResponse) || ObjectUtils.isEmpty(kakaoResponse.getDocumentDtoList())) {

            log.error("PharmacyRecommendationService :: recommendPharmacyList :: address = {}", address);
        }

        DocumentDto documentDto = kakaoResponse.getDocumentDtoList().get(0);

        List<Distance> listOfDirections = distanceService.getPharmacyList(documentDto);
        distanceService.saveAll(listOfDirections);
    }

}
