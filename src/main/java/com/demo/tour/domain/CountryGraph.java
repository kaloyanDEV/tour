package com.demo.tour.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.tour.repository.GeoRepository;

public class CountryGraph implements GeoRepository {

    private Map<Country, List<Country>> adjCountries = new HashMap<>();

    public void addCountry(String label) {
        adjCountries.putIfAbsent(new Country(label), new ArrayList<>());
    }

    public void addNeighbor(String label1, String label2) {
        Country v1 = new Country(label1);
        Country v2 = new Country(label2);
        adjCountries.get(v1).add(v2);
        adjCountries.get(v2).add(v1);
    }

    public List<Country> getAdjCountries(Country country) {
        return adjCountries.get(country);
    }

}
