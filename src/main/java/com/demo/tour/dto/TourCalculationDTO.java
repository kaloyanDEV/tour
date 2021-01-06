package com.demo.tour.dto;

import java.math.BigDecimal;
import java.util.Set;

public class TourCalculationDTO {

    private int tourCount;
    private BigDecimal leftover;
    private Set<ExpensesDTO> expenses;

    public int getTourCount() {
        return tourCount;
    }

    public void setTourCount(int tourCount) {
        this.tourCount = tourCount;
    }

    public BigDecimal getLeftover() {
        return leftover;
    }

    public void setLeftover(BigDecimal leftover) {
        this.leftover = leftover;
    }

    public Set<ExpensesDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<ExpensesDTO> expenses) {
        this.expenses = expenses;
    }

}
