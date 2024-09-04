package com.example.springbootboard.controller;

import com.example.springbootboard.dto.UserInputDto;
import com.example.springbootboard.service.pharmacy.PharmacyRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pharmacy")
@Controller
public class PharmacyController {

    private final PharmacyRecommendationService pharmacyRecommendationService;

    @GetMapping
    public String pharmacy() {
        return "pharmacy/index";
    }

    @PostMapping("/search")
    public String getDistance(UserInputDto userInputDto, Model model) {
        model.addAttribute("outputList", pharmacyRecommendationService.recommendPharmacyList(userInputDto.address()));

        return "pharmacy/result";
    }

}
