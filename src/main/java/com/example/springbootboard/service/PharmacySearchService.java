package com.example.springbootboard.service;

import com.example.springbootboard.dto.PharmacyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PharmacySearchService {

    private final PharmacyService pharmacyService;

    public List<PharmacyDto> getPharmacyDtoList() {
        // Redis

        // DB
        return pharmacyService.getPharmacies().stream().map(PharmacyDto::from).toList();
    }


}
