package com.demo.tour.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.tour.domain.CountryGraph;
import com.demo.tour.repository.GeoRepository;

@Configuration
public class CountryConfig {

    @Bean("hardcoded-graph")
    public GeoRepository getCountryGraph() {

        CountryGraph countryGraph = new CountryGraph();

        countryGraph.addCountry("BG");
        countryGraph.addCountry("TR");
        countryGraph.addCountry("GR");
        countryGraph.addCountry("MK");
        countryGraph.addCountry("SR");
        countryGraph.addCountry("RO");

        countryGraph.addNeighbor("BG", "TR");
        countryGraph.addNeighbor("BG", "GR");
        countryGraph.addNeighbor("BG", "MK");
        countryGraph.addNeighbor("BG", "SR");
        countryGraph.addNeighbor("BG", "RO");

        return countryGraph;
    }
}
