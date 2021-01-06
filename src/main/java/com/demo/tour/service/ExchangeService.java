package com.demo.tour.service;

import java.util.Currency;
import java.util.Locale;

public interface ExchangeService {

    double exhange(Currency from, Locale to, double amount);
}
