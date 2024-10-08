package com.example.springbootboard.repository.pharmacy;

import com.example.springbootboard.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
