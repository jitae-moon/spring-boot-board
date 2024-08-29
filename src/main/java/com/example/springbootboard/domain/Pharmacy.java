package com.example.springbootboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
public class Pharmacy extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter private String name;
    @Setter private String address;
    @Setter private String latitude;
    @Setter private String longitude;

    public void changeAddress(String address) {
        this.address = address;
    }

    protected Pharmacy() { }

    private Pharmacy(String name, String address, String latitude, String longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Pharmacy of(String name, String address, String latitude, String longitude) {
        return new Pharmacy(name, address, latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pharmacy pharmacy)) return false;
        return Objects.equals(this.id, pharmacy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
