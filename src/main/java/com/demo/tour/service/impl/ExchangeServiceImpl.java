package com.demo.tour.service.impl;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.tour.exception.ExchangeException;
import com.demo.tour.feign.ExchangeFeignClient;
import com.demo.tour.service.ExchangeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private ExchangeFeignClient exchangeFeignClient;
    private ObjectMapper objectMapper;

    public ExchangeServiceImpl(ExchangeFeignClient exchangeFeignClient, ObjectMapper objectMapper) {
        this.exchangeFeignClient = exchangeFeignClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public BigDecimal exhange(Currency from, Currency toCurrency, BigDecimal amount) {

        ResponseEntity<String> response = exchangeFeignClient.getRate(from.getCurrencyCode(),
                toCurrency.getCurrencyCode());

        try {

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            BigDecimal rate = new BigDecimal(jsonNode.get("rates").get(toCurrency.getCurrencyCode()).asText());

            return amount.multiply(rate);

        } catch (Exception e) {
            throw new ExchangeException(e);
        }

    }

}
