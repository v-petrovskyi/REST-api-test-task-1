package com.example.testTask.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class Price {
    @Id
    private String id;
    private String symbol1;
    private String symbol2;
    private Double lprice;
    private Long date;

    public Price(String symbol1, String symbol2, Double lprice, Long date) {
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
        this.lprice = lprice;
        this.date = date;
    }
}
