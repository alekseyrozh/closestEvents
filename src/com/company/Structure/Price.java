package com.company.Structure;

import java.util.Comparator;

public class Price {
    private final static String DISPLAY_FORMAT = "$%02d.%02d";
    public static Comparator<Price> comparator = Comparator.comparing(price -> price.dollars * 100 + price.cents);

    private final int dollars;
    private final int cents;

    public Price(int dollars, int cents) {
        if (dollars < 0 || cents < 0 || dollars + cents == 0 || cents >= 100)
            throw new IllegalArgumentException();
        this.dollars = dollars;
        this.cents = cents;
    }

    @Override
    public String toString() {
        return String.format(DISPLAY_FORMAT, dollars, cents);
    }
}
