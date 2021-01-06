package com.demo.tour.service.impl;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.tour.domain.Country;
import com.demo.tour.dto.ExpensesDTO;
import com.demo.tour.dto.TourCalculationDTO;
import com.demo.tour.dto.TourPlanningDTO;
import com.demo.tour.repository.GeoRepository;
import com.demo.tour.service.ExchangeService;
import com.demo.tour.service.TourService;

@Service
public class TourServiceImpl implements TourService {

    private final Logger log = LoggerFactory.getLogger(TourServiceImpl.class);

    private GeoRepository geoRepository;
    private ExchangeService exchangeService;

    public TourServiceImpl(GeoRepository geoRepository, ExchangeService exchangeService) {
        this.geoRepository = geoRepository;
        this.exchangeService = exchangeService;
    }

    @Override
    public TourCalculationDTO calculate(TourPlanningDTO tourPlanningDTO) {

        Country country = Country.of(tourPlanningDTO.getCountryCode());

        List<Country> adj = geoRepository.getAdjCountries(country);

        BigDecimal tourCost = tourPlanningDTO.getPerCountryBudget().multiply(BigDecimal.valueOf(adj.size()));

        TourCalculationDTO tourCalculationDTO = new TourCalculationDTO();

        tourCalculationDTO.setTourCount((int) (tourPlanningDTO.getTotalBudget().intValue() / tourCost.intValue()));

        if (tourCalculationDTO.getTourCount() > 0) {
            tourCalculationDTO.setLeftover(tourPlanningDTO.getTotalBudget().remainder(tourCost));
        } else {
            return tourCalculationDTO;
        }

        Currency baseCurrency = tourPlanningDTO.getCurrency();
        BigDecimal totalPerCountry = tourPlanningDTO.getPerCountryBudget()
            .multiply(BigDecimal.valueOf(tourCalculationDTO.getTourCount()));

        Set<ExpensesDTO> expenses = adj.stream().map(c -> this.createExpense(baseCurrency, c, totalPerCountry))
            .collect(Collectors.toSet());

        tourCalculationDTO.setExpenses(expenses);

        return tourCalculationDTO;
    }

    private ExpensesDTO createExpense(Currency baseCurrency, Country dstCounry, BigDecimal totalPerCountry) {

        ExpensesDTO expensesDTO = new ExpensesDTO();

        try {

            Locale to = new Locale("", dstCounry.getCode());

            Currency toCurrency = Currency.getInstance(to);

            BigDecimal exchanged = exchangeService.exhange(baseCurrency, toCurrency,
                    totalPerCountry);

            expensesDTO.setTotal(exchanged);
            expensesDTO.setCountry(dstCounry);
            expensesDTO.setCurrency(toCurrency);

            return expensesDTO;

        } catch (Exception e) {
            log.error("Exchange failed ", e);
        }

        expensesDTO = new ExpensesDTO();

        expensesDTO.setTotal(totalPerCountry);
        expensesDTO.setCountry(dstCounry);
        expensesDTO.setCurrency(baseCurrency);

        return expensesDTO;

    }

}
