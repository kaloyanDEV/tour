package com.demo.tour.service;

import com.demo.tour.dto.TourCalculationDTO;
import com.demo.tour.dto.TourPlanningDTO;

public interface TourService {

    public TourCalculationDTO calculate(TourPlanningDTO tourPlanningDTO );
}
