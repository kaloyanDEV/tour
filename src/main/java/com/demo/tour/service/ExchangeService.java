package com.demo.tour.service;

import java.math.BigDecimal;
import java.util.Currency;

public interface ExchangeService {

    BigDecimal exhange(Currency from, Currency to, BigDecimal amount);
}
