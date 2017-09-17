package com.company;

import com.company.Structure.Event;
import com.company.Structure.Position;
import com.company.Structure.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.company.Main.WORLD_SIZE;

public class EventService {
    public final static EventService instance = new EventService();

    private List<Event> events = new ArrayList<>();
    private int nextId = 1;

    private EventGenerator eventGenerator = new EventGenerator();

    private EventService() {
    }

    public Event addEvent(Position position) {
        Event event = new Event(nextId++, position);
        events.add(event);
        return event;
    }

    public void generateEvents(int count) {
        eventGenerator.generate(count);
    }

    public List<Event> getEvents() {
        return events;
    }


    private class EventGenerator {
        private Random random = new Random();

        private final int MAX_TICKET_PRICE = 5000; //dollars and cents
        private final int MAX_TICKET_NUMBER = 4;

        private void generate(int count) {
            List<Position> takenPositions = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Position position = generateUniquePosition(takenPositions);
                Event event = addEvent(position);
                addRandomTickets(event);
            }
        }

        private Position generateUniquePosition(List<Position> takenPositions) {
            Position position = null;
            while (takenPositions.contains(position) || position == null) {
                position = new Position(
                        random.nextInt(WORLD_SIZE) - WORLD_SIZE / 2,
                        random.nextInt(WORLD_SIZE) - WORLD_SIZE / 2);
            }
            takenPositions.add(position);
            return position;
        }

        private void addRandomTickets(Event event) {
            IntStream.range(0, random.nextInt(MAX_TICKET_NUMBER))
                    .mapToObj(a -> {
                        int price = random.nextInt(MAX_TICKET_PRICE) + 1; //[1..MAX_TICKET_PRICE]
                        return new Price(price / 100, price % 100);
                    })
                    .forEach(event::addTicket);
        }
    }
}
