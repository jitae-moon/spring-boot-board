package com.example.springbootboard.service.pharmacy;

import com.example.springbootboard.domain.Pharmacy;
import com.example.springbootboard.repository.pharmacy.PharmacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Transactional
@Service
public class PharmacyService {

    private PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public void updateAddress(Long id, String address) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if (ObjectUtils.isEmpty(pharmacy)) {
            log.error("PharmacyService :: updateAddress :: id {} not found.", id);
            throw new RuntimeException("Pharmacy with id " + id + " not found.");
        }

        pharmacy.changeAddress(address);
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacyRepository.findAll();
    }

}
