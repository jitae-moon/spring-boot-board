package com.example.springbootboard.service;

import com.example.springbootboard.dto.PharmacyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacySearchService {

    private PharmacyService pharmacyService;

    public List<PharmacyDto> getPharmacyDtoList() {
        // Redis

        // DB
        return pharmacyService.getPharmacies().stream().map(PharmacyDto::from).toList();
    }


}
