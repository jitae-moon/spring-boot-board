package com.example.springbootboard.service.pharmacy;

import com.example.springbootboard.domain.Distance;
import com.example.springbootboard.dto.DocumentDto;
import com.example.springbootboard.dto.KakaoApiResponseDto;
import com.example.springbootboard.dto.UserOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PharmacyRecommendationService {

    private final KakaoApiSearchService kakaoApiSearchService;
    private final DistanceService distanceService;

    public List<UserOutputDto> recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoResponse = kakaoApiSearchService.getKakaoResponse(address);

        if (ObjectUtils.isEmpty(kakaoResponse) || ObjectUtils.isEmpty(kakaoResponse.getDocumentDtoList())) {
            log.error("PharmacyRecommendationService :: recommendPharmacyList :: address = {}", address);
            return Collections.emptyList();
        }

        DocumentDto documentDto = kakaoResponse.getDocumentDtoList().get(0);

        List<Distance> listOfDirections = distanceService.getPharmacyList(documentDto);
        return distanceService.saveAll(listOfDirections)
                .stream()
                .map(this::convertToUserOutputDto)
                .collect(Collectors.toList());
    }

    private UserOutputDto convertToUserOutputDto(Distance distance) {
        return UserOutputDto.builder()
                .pharmacyAddress(distance.getPharmacyAddress())
                .pharmacyName(distance.getPharmacyName())
                .directionUrl("TODO")
                .roadViewUrl("TODO")
                .distance(String.format("%.2f km", distance.getDistance()))
                .build();
    }

}
