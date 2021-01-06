package com.demo.tour.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryGraph {

    private Map<Country, List<Country>> adjCountries = new HashMap<>();

    public Map<Country, List<Country>> getAdjCountries() {
        return adjCountries;
    }

    public void addCountry(String label) {
        adjCountries.putIfAbsent(new Country(label), new ArrayList<>());
    }

    public void addNeighbor(String label1, String label2) {
        Country v1 = new Country(label1);
        Country v2 = new Country(label2);
        adjCountries.get(v1).add(v2);
        adjCountries.get(v2).add(v1);
    }

}
