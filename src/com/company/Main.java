package com.company;


import com.company.ClosestEventFinders.ClosestEventFinder;
import com.company.ClosestEventFinders.TreeClosestEventFinder;
import com.company.IO.ConsoleEventPrinter;
import com.company.IO.ConsolePositionReader;
import com.company.IO.EventDisplayer;
import com.company.IO.PositionReader;
import com.company.Structure.Event;
import com.company.Structure.Position;

import java.io.IOException;

/**
 * Assumptions:
 * 1) Better to display a closer event with no tickets, than a farther one with available tickets.
 *
 *
 *
 * 
 */

public class Main {

    public final static int WORLD_SIZE = 21;
    public final static int NUMBER_OF_EVENTS_TO_GENERATE = 99;
    public final static int NUMBER_OF_EVENTS_TO_FIND = 5;

    private EventService eventService;
    private EventDisplayer eventPrinter;
    private ClosestEventFinder closestEventFinder;

    public Main() {
        eventService = EventService.instance;
        eventPrinter = new ConsoleEventPrinter();

        //closestEventFinder = new SimpleClosestEventFinder();
        closestEventFinder = new TreeClosestEventFinder();
    }

    public static void main(String[] args) throws IOException {
        PositionReader positionReader = new ConsolePositionReader();
        new Main().execute(positionReader.readPosition());
    }

    public void execute(Position position) {
        eventService.generateEvents(NUMBER_OF_EVENTS_TO_GENERATE);

        //eventPrinter.displayEventsWithPoint(eventService.getEvents(), position);
        Event[] closestEvents = closestEventFinder.findClosestEvents(NUMBER_OF_EVENTS_TO_FIND, position);

        //eventPrinter.displayEventsWithPoint(Arrays.asList(closestEvents), position);
        eventPrinter.displayAnswer(closestEvents, position);
    }
}
