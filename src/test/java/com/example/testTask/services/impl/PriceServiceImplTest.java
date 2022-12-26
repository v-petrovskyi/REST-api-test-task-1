package com.example.testTask.services.impl;

import com.example.testTask.entity.Price;
import com.example.testTask.exeptions.CurrencyException;
import com.example.testTask.exeptions.IncorrectPageException;
import com.example.testTask.repositories.PriceRepository;
import com.example.testTask.services.PriceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class PriceServiceImplTest {
    @Mock
    private PriceRepository priceRepository;

    private final PriceService priceService;

    PriceServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.priceService = new PriceServiceImpl(priceRepository);
    }


    @Test
    void add_Should_ReturnCorrectObjectPrice() {
        LocalDateTime time = LocalDateTime.now();
        Price expected = new Price("1", "BTC", "USD", 1531.0, time);
        given(priceRepository.save(expected)).willReturn(new Price("1", "BTC", "USD", 1531.0, time));

        Price actual = priceService.add(expected);

        assertEquals(expected, actual);
    }

    @Test
    void getMin_Should_ReturnCorrectObjectPrice() throws CurrencyException {
        LocalDateTime time = LocalDateTime.now();
        Price expected = new Price("1", "BTC", "USD", 1531.0, time);
        given(priceRepository.getFirstByCurr1AndCurr2OrderByLpriceAsc("BTC", "USD")).willReturn(new Price("1", "BTC", "USD", 1531.0, time));

        Price actual = priceService.getMin("BTC/USD");

        assertEquals(expected, actual);
    }

    @Test
    void getMin_Should_TrowCurrencyException_When_CurrencyIsIncorrect() {
        String currency = "not/correct";
        String expectedMessage = "currency " + currency + " not exist";

        assertThrows(CurrencyException.class, () -> priceService.getMin(currency), expectedMessage);
    }

    @Test
    void getMax_Should_ReturnCorrectObjectPrice() throws CurrencyException {
        LocalDateTime time = LocalDateTime.now();
        Price expected = new Price("1", "BTC", "USD", 1531.0, time);
        given(priceRepository.getFirstByCurr1AndCurr2OrderByLpriceDesc("BTC", "USD")).willReturn(new Price("1", "BTC", "USD", 1531.0, time));

        Price actual = priceService.getMax("BTC/USD");

        assertEquals(expected, actual);
    }

    @Test
    void getMax_Should_TrowCurrencyException_When_CurrencyIsIncorrect() {
        String currency = "not/correct";
        String expectedMessage = "currency " + currency + " not exist";

        assertThrows(CurrencyException.class, () -> priceService.getMax(currency), expectedMessage);
    }

    @Test
    void getSelectedPageOfCurrencies_Should_ReturnCorrectListOfPrices() throws CurrencyException, IncorrectPageException {
        LocalDateTime time = LocalDateTime.now();
        List<Price> allPrices = new ArrayList<>();
        allPrices.add(new Price("1", "BTC", "USD", 344.4, time));
        allPrices.add(new Price("2", "BTC", "USD", 345.4, time));
        allPrices.add(new Price("3", "BTC", "USD", 1345.4, time));
        allPrices.add(new Price("4", "BTC", "USD", 14345.4, time));
        allPrices.add(new Price("5", "BTC", "USD", 15345.4, time));
        allPrices.add(new Price("6", "BTC", "USD", 16345.4, time));

        List<Price> expected = Arrays.asList(new Price("3", "BTC", "USD", 1345.4, time), new Price("4", "BTC", "USD", 14345.4, time));

        given(priceRepository.getPriceByCurr1AndCurr2OrderByLpriceAsc("BTC", "USD")).willReturn(allPrices);

        List<Price> actual = priceService.getSelectedPageOfCurrencies("BTC/USD", 2, 2);

        assertEquals(expected, actual);
    }

    @Test
    void getSelectedPageOfCurrencies_Should_TrowCurrencyException_When_CurrencyIsIncorrect() {
        String currency = "not/correct";
        String expectedMessage = "currency " + currency + " not exist";

        assertThrows(CurrencyException.class, () -> priceService.getSelectedPageOfCurrencies(currency, 2, 2), expectedMessage);
    }

    @Test
    void getSelectedPageOfCurrencies_Should_TrowException_When_CurrencyIsIncorrect() throws CurrencyException, IncorrectPageException {
        int page = 200;
        int size = 100;
        String currency = "BTC/USD";
        String expectedMessage = "page " + page + " not exist";
        LocalDateTime time = LocalDateTime.now();
        List<Price> allPrices = new ArrayList<>();
        allPrices.add(new Price("1", "BTC", "USD", 344.4, time));
        allPrices.add(new Price("2", "BTC", "USD", 345.4, time));
        allPrices.add(new Price("3", "BTC", "USD", 1345.4, time));
        allPrices.add(new Price("4", "BTC", "USD", 14345.4, time));
        allPrices.add(new Price("5", "BTC", "USD", 15345.4, time));
        allPrices.add(new Price("6", "BTC", "USD", 16345.4, time));

        given(priceRepository.getPriceByCurr1AndCurr2OrderByLpriceAsc("BTC", "USD")).willReturn(allPrices);

        assertThrows(IncorrectPageException.class, () -> priceService.getSelectedPageOfCurrencies(currency, page, size), expectedMessage);
    }

}