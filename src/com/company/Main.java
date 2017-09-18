package com.company;


import com.company.ClosestEventFinders.ClosestEventFinder;
import com.company.ClosestEventFinders.SimpleClosestEventFinder;
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
 * 1)Better to display a closer event with no tickets, than a farther one with available tickets.
 * ----------------------------------------------------------------------------------------
 * Time Complexity:
 * 1)SimpleClosestEventFinder
 *   search: O(n)
 *
 * 2)TreeClosestEventFinder
 *   construction: O(n^2*log n) (assuming that sorting takes n*log n)
 *
 *                 construction can take O(n*log n) if the points are presorted for both axes
 *                 so there is no need to sort the points at each level to keep the tree balanced.
 *                 This is not implemented here.
 *
 *   search: O(log n)
 *
 * For one time query the 1st solution would be more suitable giving the constant O(n) performance.
 * However, for every new query no performance benefits will be present.
 *
 * Using the 2nd solution, on a given set of events, first the 2-dimensional kd-tree constructed
 * and then it can be used for many queries in O(log n) time.
 * ----------------------------------------------------------------------------------------
 * Supporting multiple events at one location:
 *
 * Both algorithms would work correctly with multiple events at one location.
 * The only change in the program would be changing the generator so it allows that.
 *
 * However, to increase the efficiency of the algorithms, modifications to them could be made.
 * ----------------------------------------------------------------------------------------
 * Dealing with larger world sizes:
 *
 * The 2nd approach could bring significant efficiency benefits when dealing with large worlds and making
 * multiple queries. For larger worlds presorting of events must be implemented so construction of the tree
 * reduces to O(n*log n)
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

        closestEventFinder = new SimpleClosestEventFinder();
        //closestEventFinder = new TreeClosestEventFinder();
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
