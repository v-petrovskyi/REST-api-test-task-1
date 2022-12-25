package com.example.testTask.util;

import com.example.testTask.entity.PairPrice;
import com.example.testTask.entity.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PairPriceToPriceTest {


    @Test
    void convertToPrice_Should_ReturnTrue() {
        PairPrice pairPrice = new PairPrice("BTC", "USD", "17656.2");
        LocalDateTime time = LocalDateTime.now();
        Price expected = new Price("BTC", "USD", 17656.2, time);

        Price actual = PairPriceToPrice.convertToPrice(pairPrice, time);

        assertEquals(expected, actual);
    }

    @Test
    void convertToPrice_Should_ReturnFalse() {
        PairPrice pairPrice = new PairPrice("BTC", "USD", "17656.2");
        LocalDateTime time = LocalDateTime.now();
        Price expected = new Price("Wrong", "currency", 0000.0, time);

        Price actual = PairPriceToPrice.convertToPrice(pairPrice, time);

        assertNotEquals(expected, actual);
    }

}