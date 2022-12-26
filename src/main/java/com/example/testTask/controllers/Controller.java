package com.example.testTask.controllers;

import com.example.testTask.entity.Price;
import com.example.testTask.exeptions.CurrencyException;
import com.example.testTask.exeptions.ErrorResponse;
import com.example.testTask.services.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final PriceService priceService;

    public Controller(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/cryptocurrencies/minprice")
    public Price returnMinPrice(@RequestParam("name") String currencyName) throws CurrencyException {
        return priceService.getMin(currencyName);
    }

    @GetMapping("/cryptocurrencies/maxprice")
    public Price returnMaxPrice(@RequestParam("name") String currencyName) throws CurrencyException {
        return priceService.getMax(currencyName);
    }


    @GetMapping("/cryptocurrencies")
    public List<Price> returnListOfPrices(
            @RequestParam("name") String currencyName,
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "size", required = false) Optional<Integer> size) throws CurrencyException {
        return priceService.getSelectedPageOfCurrencies(currencyName, page.orElse(1), size.orElse(10));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(CurrencyException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
