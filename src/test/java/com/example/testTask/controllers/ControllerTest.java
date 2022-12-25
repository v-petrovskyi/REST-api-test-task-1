package com.example.testTask.controllers;

import com.example.testTask.entity.Price;
import com.example.testTask.services.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class ControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService service;

    @Test
    void returnMinPrice() throws Exception {
        String currency = "BTC/USD";
        LocalDateTime time = LocalDateTime.now();
        when(service.getMin(currency)).thenReturn(new Price("63a720679b98dd0294ccc996", "BTC", "USD", 16860.7, time));

        mockMvc.perform(get("/cryptocurrencies/minprice?name=BTC/USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("63a720679b98dd0294ccc996")))
                .andExpect(jsonPath("$.curr1", equalTo("BTC")))
                .andExpect(jsonPath("$.curr2", equalTo("USD")))
                .andExpect(jsonPath("$.lprice",equalTo(16860.7)));
    }

    @Test
    void returnMaxPrice() throws Exception {
        String currency = "BTC/USD";
        LocalDateTime time = LocalDateTime.now();
        when(service.getMax(currency)).thenReturn(new Price("63a720679b98dd0294ccc996", "BTC", "USD", 16860.7, time));

        mockMvc.perform(get("/cryptocurrencies/maxprice?name=BTC/USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("63a720679b98dd0294ccc996")))
                .andExpect(jsonPath("$.curr1", equalTo("BTC")))
                .andExpect(jsonPath("$.curr2", equalTo("USD")))
                .andExpect(jsonPath("$.lprice",equalTo(16860.7)));
    }

    @Test
    void returnAll() {
    }

    @Test
    void returnListOfPrices() {
    }
}