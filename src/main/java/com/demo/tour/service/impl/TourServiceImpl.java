package com.demo.tour.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.tour.domain.Country;
import com.demo.tour.domain.CountryGraph;
import com.demo.tour.dto.TourCalculationDTO;
import com.demo.tour.dto.TourPlanningDTO;
import com.demo.tour.service.TourService;

@Service
public class TourServiceImpl implements TourService {

    private CountryGraph countryGraph;

    public TourServiceImpl(@Qualifier("hardcoded-graph") CountryGraph countryGraph) {
        this.countryGraph = countryGraph;
    }

    @Override
    public TourCalculationDTO calculate(TourPlanningDTO tourPlanningDTO) {

        Country country = Country.of(tourPlanningDTO.getCountryCode());

        List<Country> adj = countryGraph.getAdjCountries().get(country);

        Double tourCost = adj.size() * tourPlanningDTO.getPerCountryBudget();

        TourCalculationDTO tourCalculationDTO = new TourCalculationDTO();

        tourCalculationDTO.setTourCount((int) (tourPlanningDTO.getTotalBudget() / tourCost));

        if (tourCalculationDTO.getTourCount() > 0) {
            tourCalculationDTO.setLeftover(tourPlanningDTO.getTotalBudget() % tourCost);
        } else {
            return tourCalculationDTO;
        }

        return tourCalculationDTO;
    }

}
