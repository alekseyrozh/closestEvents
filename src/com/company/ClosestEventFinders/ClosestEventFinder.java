package com.company.ClosestEventFinders;

import com.company.Structure.Event;
import com.company.Structure.Position;
import com.company.Structure.Ticket;

import java.util.Comparator;

public interface ClosestEventFinder {
    Event[] findClosestEvents(int count, Position position);

    default void insertIntoPriorityQueue(Event[] priorityQueue, Event event, Position target) {
        int i = 0;
        while (priorityQueue[i] != null && firstEventMoreSuitable(priorityQueue[i], event, target)) {
            i++;
            if (i >= priorityQueue.length)
                return;
        }

        if (priorityQueue[i] == null) {
            priorityQueue[i] = event;
        } else {
            for (int j = priorityQueue.length - 1; j > i; j--) {
                priorityQueue[j] = priorityQueue[j - 1];
            }
            priorityQueue[i] = event;
        }
    }

    default boolean firstEventMoreSuitable(Event event1, Event event2, Position target) {
        return Comparator.comparing((Event e) -> target.manhattanDistanceTo(e.getPosition()), Comparator.reverseOrder())
                .thenComparing(Event::getCheapestTicket, Comparator.nullsFirst(Ticket.comparator))
                .compare(event1, event2) > 0;
    }
}
