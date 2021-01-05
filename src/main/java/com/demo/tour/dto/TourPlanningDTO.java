package com.demo.tour.dto;

import java.util.Currency;

public class TourPlanningDTO {

    private String countryCode;

    private Double perCountryBudget;

    private Double totalBudget;

    private Currency currency;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getPerCountryBudget() {
        return perCountryBudget;
    }

    public void setPerCountryBudget(Double perCountryBudget) {
        this.perCountryBudget = perCountryBudget;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = Currency.getInstance(currency);
    }

}
