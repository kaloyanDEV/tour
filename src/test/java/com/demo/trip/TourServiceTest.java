package com.demo.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.tour.dto.ExpensesDTO;
import com.demo.tour.dto.TourCalculationDTO;
import com.demo.tour.dto.TourPlanningDTO;
import com.demo.tour.exception.ExchangeException;
import com.demo.tour.service.ExchangeService;
import com.demo.tour.service.TourService;

@SpringBootTest(classes = com.demo.tour.TourApplication.class)
public class TourServiceTest {

    private static final String DEFAULT_COUNTRY_CODE = "BG";
    private static final String DEFAULT_CURRENCY_CODE = "EUR";
    private static final String GREECE_NEIGHBOUR_COUNTRY_CODE = "GR";
    private static final String TURKEY_NEIGHBOUR_COUNTRY_CODE = "TR";

    @Autowired
    private TourService tourService;

    @MockBean
    private ExchangeService exchangeService;

    @Test
    public void lowBudget_whenPlanned_then_success() {

        TourPlanningDTO tourPlanningDTO = mockPoоrBudget();

        TourCalculationDTO result = tourService.calculate(tourPlanningDTO);

        assertNotNull(result);
        assertEquals(0, result.getTourCount());
        assertEquals(tourPlanningDTO.getTotalBudget(), result.getLeftover());
        assertNull(result.getExpenses());

    }

    @Test
    public void hasBudget_whenPlanned_then_success() {

        Locale greece = new Locale("", GREECE_NEIGHBOUR_COUNTRY_CODE);
        Currency greekCurrency = Currency.getInstance(greece);

        Locale turkey = new Locale("", TURKEY_NEIGHBOUR_COUNTRY_CODE);
        Currency turkishCurrency = Currency.getInstance(turkey);

        TourPlanningDTO tourPlanningDTO = mockGoodBudget();

        BigDecimal exchanged = BigDecimal.valueOf(3333.30);

        doReturn(exchanged).when(exchangeService).exhange(tourPlanningDTO.getCurrency(),
                turkishCurrency,
                BigDecimal.valueOf(200));

        doThrow(ExchangeException.class).when(exchangeService).exhange(tourPlanningDTO.getCurrency(), greekCurrency,
                BigDecimal.valueOf(200));

        TourCalculationDTO result = tourService.calculate(tourPlanningDTO);

        verify(exchangeService, times(1)).exhange(tourPlanningDTO.getCurrency(), greekCurrency,
                BigDecimal.valueOf(200));

        verify(exchangeService, times(1)).exhange(tourPlanningDTO.getCurrency(), turkishCurrency,
                BigDecimal.valueOf(200));

        assertNotNull(result);
        assertEquals(2, result.getTourCount());
        assertEquals(BigDecimal.valueOf(200), result.getLeftover());
        assertNotNull(result.getExpenses());

        Optional<ExpensesDTO> expense = result.getExpenses().stream()
            .filter(ex -> ex.getCurrency().equals(greekCurrency)).findFirst();

        // default currency no exchange rate for Greece
        assertEquals(true, expense.isPresent());
        assertEquals(tourPlanningDTO.getCurrency(), expense.get().getCurrency());
        assertEquals(BigDecimal.valueOf(200), expense.get().getTotal());

        expense = result.getExpenses().stream()
            .filter(ex -> ex.getCurrency().equals(turkishCurrency)).findFirst();

        // exchange rate available for Turkey
        assertEquals(true, expense.isPresent());
        assertEquals(turkishCurrency, expense.get().getCurrency());
        assertEquals(exchanged, expense.get().getTotal());

    }

    private TourPlanningDTO mockGoodBudget() {

        TourPlanningDTO tourPlanningDTO = new TourPlanningDTO();

        tourPlanningDTO.setCountryCode(DEFAULT_COUNTRY_CODE);
        tourPlanningDTO.setCurrency(DEFAULT_CURRENCY_CODE);
        tourPlanningDTO.setTotalBudget(BigDecimal.valueOf(1200));
        tourPlanningDTO.setPerCountryBudget(BigDecimal.valueOf(100));

        return tourPlanningDTO;

    }

    private TourPlanningDTO mockPoоrBudget() {

        TourPlanningDTO tourPlanningDTO = new TourPlanningDTO();

        tourPlanningDTO.setCountryCode(DEFAULT_COUNTRY_CODE);
        tourPlanningDTO.setCurrency(DEFAULT_CURRENCY_CODE);
        tourPlanningDTO.setTotalBudget(BigDecimal.valueOf(333));
        tourPlanningDTO.setPerCountryBudget(BigDecimal.valueOf(100));

        return tourPlanningDTO;

    }

}
