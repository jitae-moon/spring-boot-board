package com.example.springbootboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User info
    @Setter
    private String userAddress;
    @Setter private double userLongitude;
    @Setter private double userLatitude;

    @Setter private String pharmacyName;
    @Setter private String pharmacyAddress;
    @Setter private double pharmacyLatitude;
    @Setter private double pharmacyLongitude;

    private double distance;

    protected Distance() { }

    @Builder
    public Distance(String userAddress, double userLongitude, double userLatitude, String pharmacyName, String pharmacyAddress, double pharmacyLatitude, double pharmacyLongitude, double distance) {
        this.userAddress = userAddress;
        this.userLongitude = userLongitude;
        this.userLatitude = userLatitude;
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.pharmacyLatitude = pharmacyLatitude;
        this.pharmacyLongitude = pharmacyLongitude;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distance distance)) return false;
        return Objects.equals(this.id, distance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
