package com.company.IO;

import com.company.Structure.Event;
import com.company.Structure.Position;

import java.util.List;

public interface EventDisplayer {
    void displayEventsWithPoint(List<Event> events, Position position);

    void displayAnswer(Event[] closestEvents, Position position);
}
