package com.example.exchangerates;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Currency {
    private Double price;
    private Integer day;

    public Currency(double price) {
        this.price = price;
        day = 0;
    }

    public double getPrice() {
        return price;
    }

    public Integer getDay() {
        return day;
    }

    public void updatePriceForNextDay() {
        day++;
        if (price != null) {
            double k = 0.02;
            price = price * (1 + k * ( new Random().nextDouble(0, 1) - 0.5));
        }
    }
}
