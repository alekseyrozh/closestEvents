package com.company.Structure;

import java.util.*;

public class Event {
    private int id;
    private Position position;

    private SortedSet<Ticket> tickets = new TreeSet<>(Ticket.comparator.reversed());

    public Event(int id, Position position) {
        this.id = id;
        this.position = position;
    }

    public void addTicket(Price price) {
        tickets.add(new Ticket(price));
    }

    public String idToString() {
        return String.format("%03d", id);
    }

    @Override
    public String toString() {
        return "Event " + idToString() +
                ", " + position +
                ", tickets: " + Arrays.toString(tickets.toArray());
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public SortedSet<Ticket> getTickets() {
        return tickets;
    }

    public Ticket getCheapestTicket() {
        return tickets.size() != 0 ? tickets.first() : null;
    }
}
