package com.company.Structure;

import java.util.Comparator;

public class Ticket {
    public static Comparator<Ticket> comparator = Comparator.comparing(Ticket::getPrice, Price.comparator.reversed());

    public Price getPrice() {
        return price;
    }

    private Price price;

    public Ticket(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return price.toString();
    }
}
