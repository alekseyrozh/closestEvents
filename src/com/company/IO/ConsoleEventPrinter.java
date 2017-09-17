package com.company.IO;

import com.company.Structure.Event;
import com.company.Structure.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.company.Main.WORLD_SIZE;

public class ConsoleEventPrinter implements EventDisplayer {
    @Override
    public void displayEventsWithPoint(List<Event> events, Position position) {
        System.out.println(events.stream().map(Event::toString).collect(Collectors.joining(", ")));

        for (int i = 0; i < WORLD_SIZE + 2; i++)
            System.out.print("* ");
        System.out.println();

        for (int i = -WORLD_SIZE / 2; i <= (WORLD_SIZE - 1) / 2; i++) {
            System.out.print("* ");
            for (int j = -WORLD_SIZE / 2; j <= (WORLD_SIZE - 1) / 2; j++) {
                int finalJ = j;
                int finalI = i;

                Optional<Event> eventOpt = events.stream().filter(event -> event.getPosition().x == finalJ && event.getPosition().y == finalI).findAny();
                boolean eventHere = eventOpt.isPresent();
                boolean pointHere = position.x == j && position.y == i;

                if (eventHere) {
                    if (pointHere) {
                        System.out.print("X ");
                    } else {
                        int id = eventOpt.get().getId();
                        System.out.print(id + (id >= 10 ? "" : " "));
                    }
                } else {
                    if (pointHere) {
                        System.out.print("O ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println("* ");
        }

        for (int i = 0; i < WORLD_SIZE + 2; i++)
            System.out.print("* ");
        System.out.println();
    }

    @Override
    public void displayAnswer(Event[] closestEvents, Position target) {
        System.out.println("Closest Events to (" + target + "):");

        Arrays.stream(closestEvents).forEach(event ->
                System.out.println("Event " + event.idToString()
                        + " - " +
                        Optional.of(event.getTickets()).filter(tickets -> tickets.size() > 0)
                                .map(tickets -> tickets.first().getPrice().toString())
                                .orElse("no tickets")
                        + ", Distance " + event.getPosition().manhattanDistanceTo(target))
        );
    }
}
