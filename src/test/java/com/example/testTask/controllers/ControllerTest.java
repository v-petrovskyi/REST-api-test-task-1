package com.example.testTask.controllers;

import com.example.testTask.entity.Price;
import com.example.testTask.exeptions.CurrencyException;
import com.example.testTask.exeptions.IncorrectPageException;
import com.example.testTask.services.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
    void returnMinPrice_Return_CorrectJson_When_ParameterExist() throws Exception {
        String currency = "BTC/USD";
        LocalDateTime time = LocalDateTime.now();
        when(service.getMin(currency)).thenReturn(new Price("63a720679b98dd0294ccc996", "BTC", "USD", 16860.7, time));

        mockMvc.perform(get("/cryptocurrencies/minprice?name=BTC/USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("63a720679b98dd0294ccc996")))
                .andExpect(jsonPath("$.curr1", equalTo("BTC")))
                .andExpect(jsonPath("$.curr2", equalTo("USD")))
                .andExpect(jsonPath("$.lprice", equalTo(16860.7)));
    }

    @Test
    void returnMinPrice_Return_ErrorResponseJson_When_ParameterIsIncorrect() throws Exception {
        String currency = "BTC/UD";
        when(service.getMin(currency)).thenThrow(new CurrencyException("currency " + currency + " not exist"));

        mockMvc.perform(get("/cryptocurrencies/minprice?name=" + currency))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("currency " + currency + " not exist")));
    }


    @Test
    void returnMaxPrice_Return_CorrectJson_When_ParameterExist() throws Exception {
        String currency = "BTC/USD";
        LocalDateTime time = LocalDateTime.now();
        when(service.getMax(currency)).thenReturn(new Price("63a720679b98dd0294ccc996", "BTC", "USD", 16860.7, time));

        mockMvc.perform(get("/cryptocurrencies/maxprice?name=BTC/USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("63a720679b98dd0294ccc996")))
                .andExpect(jsonPath("$.curr1", equalTo("BTC")))
                .andExpect(jsonPath("$.curr2", equalTo("USD")))
                .andExpect(jsonPath("$.lprice", equalTo(16860.7)));
    }

    @Test
    void returnMaxPrice_Return_ErrorResponseJson_When_CurrencyIsIncorrect() throws Exception {
        String currency = "BTC/UD";
        when(service.getMax(currency)).thenThrow(new CurrencyException("currency " + currency + " not exist"));

        mockMvc.perform(get("/cryptocurrencies/maxprice?name=" + currency))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("currency " + currency + " not exist")));
    }

    @Test
    void returnListOfPrices_Should_ReturnCorrectJson() throws Exception {
        String currency = "BTC/USD";
        LocalDateTime time = LocalDateTime.now();
        when(service.getSelectedPageOfCurrencies(anyString(), anyInt(), anyInt()))
                .thenReturn(new ArrayList<>(Arrays.asList(
                        new Price("63a720679b98dd0294ccc996", "BTC", "USD", 16860.7, time),
                        new Price("63a720679b98dd029455ccs6", "BTC", "USD", 17860.7, time)
                )));

        mockMvc.perform(get("/cryptocurrencies?name=BTC/USD&page=2&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder("63a720679b98dd0294ccc996", "63a720679b98dd029455ccs6")))
                .andExpect(jsonPath("$[*].curr1", containsInAnyOrder("BTC", "BTC")))
                .andExpect(jsonPath("$[*].curr2", containsInAnyOrder("USD", "USD")))
                .andExpect(jsonPath("$[*].lprice", containsInAnyOrder(16860.7, 17860.7)));
    }

    @Test
    void returnMaxPrice_Return_ErrorResponseJson_When_PageNotExist() throws Exception {
        String currency = "BTC/USD";
        int page = 100;
        int size = 100;
        when(service.getSelectedPageOfCurrencies(currency, page, size)).thenThrow(new IncorrectPageException("page " + page + " not exist"));

        mockMvc.perform(get("/cryptocurrencies?name=" + currency + "&page=" + page + "&size=" + size))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("page " + page + " not exist")));
    }
}