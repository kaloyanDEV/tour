package com.demo.tour.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange", path = "latest", url = "https://api.exchangeratesapi.io")
public interface ExchangeFeignClient {

    @GetMapping
    public ResponseEntity<String> getRate(@RequestParam(value = "base") String base,
            @RequestParam(value = "symbols") String symbol);

}
