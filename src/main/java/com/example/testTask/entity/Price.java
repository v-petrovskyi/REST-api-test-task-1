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
    private String curr1;
    private String curr2;
    private Double lprice;
    private LocalDateTime date;

    public Price(String curr1, String curr2, Double lprice, LocalDateTime date) {
        this.curr1 = curr1;
        this.curr2 = curr2;
        this.lprice = lprice;
        this.date = date;
    }
}
