package com.example.springbootboard.dto;

import com.example.springbootboard.domain.Pharmacy;

public record PharmacyDto(Long id,
                          String name,

                          String address,
                          double longitude,
                          double latitude) {

    public static PharmacyDto from(Pharmacy pharmacy) {
        return new PharmacyDto(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getAddress(),
                pharmacy.getLongitude(),
                pharmacy.getLatitude()
        );
    }

    public Pharmacy toEntity() {
        return Pharmacy.of(
                this.name,
                this.address,
                this.longitude,
                this.latitude
        );
    }

}
