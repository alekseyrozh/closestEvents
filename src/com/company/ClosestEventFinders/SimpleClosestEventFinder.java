package com.company.ClosestEventFinders;

import com.company.EventService;
import com.company.Structure.Event;
import com.company.Structure.Position;

public class SimpleClosestEventFinder implements ClosestEventFinder {
    @Override
    public Event[] findClosestEvents(int count, Position position) {
        EventService eventService = EventService.instance;
        if (eventService.getEvents().size() < count || count <= 0)
            throw new IllegalArgumentException();

        Event[] closestEvents = new Event[count];

        for (Event event : eventService.getEvents()) {
            insertIntoPriorityQueue(closestEvents, event, position);
        }

        return closestEvents;
    }
}