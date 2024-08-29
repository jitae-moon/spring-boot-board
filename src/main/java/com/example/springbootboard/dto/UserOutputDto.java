package com.example.springbootboard.dto;

import lombok.Builder;

public record UserOutputDto(
        String pharmacyName,
        String pharmacyAddress,
        String directionUrl,
        String roadViewUrl,
        String distance
) {
    @Builder
    public UserOutputDto(String pharmacyName, String pharmacyAddress, String directionUrl, String roadViewUrl, String distance) {
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.directionUrl = directionUrl;
        this.roadViewUrl = roadViewUrl;
        this.distance = distance;
    }
}
