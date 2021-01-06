package com.demo.tour.dto;

import java.math.BigDecimal;
import java.util.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TourPlanningDTO {

    @NotBlank(message = "countryCode is mandatory")
    private String countryCode;

    @NotNull
    private BigDecimal perCountryBudget;

    @NotNull
    private BigDecimal totalBudget;

    private Currency currency;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getPerCountryBudget() {
        return perCountryBudget;
    }

    public void setPerCountryBudget(BigDecimal perCountryBudget) {
        this.perCountryBudget = perCountryBudget;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = Currency.getInstance(currency);
    }

}
