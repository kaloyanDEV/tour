package com.demo.tour.service.impl;

import java.util.Currency;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.demo.tour.service.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Override
    public double exhange(Currency from, Locale to, double amount) {

        return 0;
    }

}
