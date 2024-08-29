package com.example.springbootboard.service;

import com.example.springbootboard.domain.Distance;
import com.example.springbootboard.dto.DocumentDto;
import com.example.springbootboard.dto.PharmacyDto;
import com.example.springbootboard.repository.DistanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DistanceService {

    private static final int MAX_NUMBER = 3;
    private static final double MAX_RADIUS_KM = 10.0;

    private final PharmacySearchService pharmacySearchService;
    private final DistanceRepository distanceRepository;
    private final KakaoCategorySearchService kakaoCategorySearchService;

    public List<Distance> saveAll(List<Distance> distanceList) {
        if (distanceList.isEmpty()) return Collections.emptyList();
        return distanceRepository.saveAll(distanceList);
    }

    // Haversine formula
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double diffOfLatitude = Math.toRadians(Math.abs(latitude2 - latitude1));
        double diffOfLongitude = Math.toRadians(Math.abs(longitude2 - longitude1));

        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(diffOfLatitude / 2), 2) +
                Math.pow(Math.sin(diffOfLongitude / 2), 2) *
                        Math.cos(latitude1) *
                        Math.cos(latitude2);
        double earthRadius = 6371;

        return earthRadius * 2 * Math.asin(Math.sqrt(a));
    }

    public List<Distance> getPharmacyList(DocumentDto documentDto) {
        if (ObjectUtils.isEmpty(documentDto)) return Collections.emptyList();

        // Search pharmacy data
        List<PharmacyDto> pharmacyDtoList = pharmacySearchService.getPharmacyDtoList();
        // Sort by distance in Direction
        return pharmacyDtoList.stream()
                .map(dto ->
                    Distance.builder()
                            .userAddress(documentDto.getAddressName())
                            .userLongitude(documentDto.getLongitude())
                            .userLatitude(documentDto.getLatitude())
                            .pharmacyName(dto.name())
                            .pharmacyAddress(dto.address())
                            .pharmacyLongitude(dto.longitude())
                            .pharmacyLatitude(dto.latitude())
                            .distance(this.calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(), dto.latitude(), dto.longitude()))
                            .build()
                )
                .filter(direction -> direction.getDistance() <= MAX_RADIUS_KM)
                .sorted(Comparator.comparing(Distance::getDistance))
                .limit(MAX_NUMBER)
                .collect(Collectors.toList());
    }

    public List<Distance> getDistanceByCategoryApi(DocumentDto documentDto) {
        return kakaoCategorySearchService.requestPharmacyCategorySearch(documentDto.getLatitude(), documentDto.getLongitude(), MAX_RADIUS_KM)
                .getDocumentDtoList()
                .stream()
                .map(resultDto ->
                        Distance.builder()
                                .userAddress(documentDto.getAddressName())
                                .userLatitude(documentDto.getLatitude())
                                .userLongitude(documentDto.getLongitude())
                                .pharmacyName(resultDto.getPlaceName())
                                .pharmacyAddress(resultDto.getAddressName())
                                .pharmacyLongitude(resultDto.getLongitude())
                                .distance(resultDto.getDistance() * 0.001)
                                .build())
                .limit(MAX_NUMBER)
                .collect(Collectors.toList());
    }

}
