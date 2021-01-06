package com.demo.tour.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tour.dto.TourCalculationDTO;
import com.demo.tour.dto.TourPlanningDTO;
import com.demo.tour.service.TourService;

@RestController
@RequestMapping("/api")
public class TourResource {

    private TourService tourService;

    public TourResource(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping("/plan")
    public ResponseEntity<TourCalculationDTO> plan(@Valid @RequestBody TourPlanningDTO tourPlanningDTO) {

        TourCalculationDTO result = tourService.calculate(tourPlanningDTO);

        return ResponseEntity.ok(result);
    }

}
