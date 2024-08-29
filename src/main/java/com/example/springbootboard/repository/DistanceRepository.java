package com.example.springbootboard.repository;

import com.example.springbootboard.domain.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceRepository extends JpaRepository<Distance, Long> {
}
