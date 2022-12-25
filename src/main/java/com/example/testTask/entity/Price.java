package com.example.testTask.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(id, price.id) && Objects.equals(curr1, price.curr1) && Objects.equals(curr2, price.curr2) && Objects.equals(lprice, price.lprice) && Objects.equals(date, price.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, curr1, curr2, lprice, date);
    }
}
