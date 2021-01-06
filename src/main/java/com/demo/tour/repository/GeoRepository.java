package com.demo.tour.repository;

import java.util.List;

import com.demo.tour.domain.Country;

public interface GeoRepository {

    public void addCountry(String label);
    public void addNeighbor(String label1, String label2);
    public List<Country> getAdjCountries(Country country);
    
}
