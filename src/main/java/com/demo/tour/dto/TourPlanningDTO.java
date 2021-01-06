package com.demo.tour.dto;

import java.util.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TourPlanningDTO {

    @NotBlank(message = "countryCode is mandatory")
    private String countryCode;

    @NotNull
    private Double perCountryBudget;

    @NotNull
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
