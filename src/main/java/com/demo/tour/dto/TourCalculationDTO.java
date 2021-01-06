package com.demo.tour.dto;

import java.util.Set;

public class TourCalculationDTO {

    private int tourCount;
    private double leftover;
    private Set<ExpensesDTO> expenses;

    public int getTourCount() {
        return tourCount;
    }

    public void setTourCount(int tourCount) {
        this.tourCount = tourCount;
    }

    public double getLeftover() {
        return leftover;
    }

    public void setLeftover(double leftover) {
        this.leftover = leftover;
    }

    public Set<ExpensesDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<ExpensesDTO> expenses) {
        this.expenses = expenses;
    }

}
