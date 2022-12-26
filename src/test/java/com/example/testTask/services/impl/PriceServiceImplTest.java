package com.example.testTask.services.impl;

import com.example.testTask.entity.Price;
import com.example.testTask.exeptions.CurrencyException;
import com.example.testTask.repositories.PriceRepository;
import com.example.testTask.services.PriceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

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
    void getMin_Should_TrowCurrencyException_When_CurrencyIsIncorrect() throws CurrencyException {
        String currency = "not/correct";
        String expectedMessage = "currency " + currency + " not exist";

        assertThrows(CurrencyException.class, ()->priceService.getMin(currency), expectedMessage);
    }


        @Test
    void getMax() {

    }

    @Test
    void getAll() {
    }

    @Test
    void getSelectedPageOfCurrencies() {
    }

    @Test
    void listForCSVReport() {
    }
}