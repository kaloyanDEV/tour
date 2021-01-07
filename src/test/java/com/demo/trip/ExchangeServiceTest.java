package com.demo.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.demo.tour.feign.ExchangeFeignClient;
import com.demo.tour.service.impl.ExchangeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeServiceTest {

    private static final String DEFAULT_COUNTRY_CODE = "BG";
    private static final String GREECE_NEIGHBOUR_COUNTRY_CODE = "GR";

    private static final String RATE1 = "{\"rates\":{\"EUR\":0.7874015748},\"base\":\"BGN\"}";
    private static final String RATE2 = "{\"rates\":{\"BGN\":1.27},\"base\":\"EUR\"}";

    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    @Mock
    private ExchangeFeignClient exchangeFeignClient;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void money_whenExchange_thenNotLoose() {

        ReflectionTestUtils.setField(exchangeService, "objectMapper", new ObjectMapper());

        Locale defaultBase = new Locale("", DEFAULT_COUNTRY_CODE);
        Currency defaultCurrency = Currency.getInstance(defaultBase);

        Locale greece = new Locale("", GREECE_NEIGHBOUR_COUNTRY_CODE);
        Currency greekCurrency = Currency.getInstance(greece);

        BigDecimal initial = BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_EVEN);

        doReturn(ResponseEntity.ok(RATE1)).when(exchangeFeignClient).getRate(defaultCurrency.getCurrencyCode(),
                greekCurrency.getCurrencyCode());

        doReturn(ResponseEntity.ok(RATE2)).when(exchangeFeignClient).getRate(greekCurrency.getCurrencyCode(),
                defaultCurrency.getCurrencyCode());

        BigDecimal after = exchangeService.exhange(defaultCurrency, greekCurrency, initial);

        assertNotNull(after);

        after = exchangeService.exhange(greekCurrency, defaultCurrency, after);

        assertNotNull(after);

        assertEquals(initial, after.setScale(2, RoundingMode.HALF_EVEN));
    }

}
