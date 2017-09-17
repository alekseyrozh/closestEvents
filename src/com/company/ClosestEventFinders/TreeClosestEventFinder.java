package com.company.ClosestEventFinders;

import com.company.EventService;
import com.company.EventTreeNode;
import com.company.Structure.Event;
import com.company.Structure.Position;

import java.util.Comparator;
import java.util.List;

public class TreeClosestEventFinder implements ClosestEventFinder {

    @Override
    public Event[] findClosestEvents(int count, Position position) {
        EventTreeNode tree = constructTree(EventService.instance.getEvents());
        return findClosestEvents(tree, position, 0, new Event[count]);
    }

    private EventTreeNode constructTree(List<Event> events) {
        return constructTree(events, 0);
    }

    private EventTreeNode constructTree(List<Event> events, int depth) {
        if (events.isEmpty())
            return null;

        Comparator<Position> comparator = getAxisComparator(depth);

        events.sort(Comparator.comparing(Event::getPosition, comparator));
        int median = events.size() / 2;

        return new EventTreeNode(events.get(median),
                constructTree(events.subList(0, median), depth + 1),
                constructTree(events.subList(median + 1, events.size()), depth + 1));
    }

    private Comparator<Position> getAxisComparator(int depth) {
        return depth % 2 == 0 ?
                Comparator.comparing(position -> position.x) :
                Comparator.comparing(position -> position.y);
    }

    private Event[] findClosestEvents(EventTreeNode current, Position target, int depth, Event[] best) {
        int direction = getAxisComparator(depth).compare(target, current.getEvent().getPosition());
        EventTreeNode next = (direction < 0) ? current.getLeft() : current.getRight();
        EventTreeNode other = (direction < 0) ? current.getRight() : current.getLeft();

        if (next != null) {
            findClosestEvents(next, target, depth + 1, best);
        }

        if (best[best.length - 1] == null || firstEventMoreSuitable(current.getEvent(), best[best.length - 1], target)) {
            insertIntoPriorityQueue(best, current.getEvent(), target);
        }

        if (other != null) {
            if (best[best.length - 1] == null || current.getEvent().getPosition().straightDistance(target, depth)
                    < best[best.length - 1].getPosition().manhattanDistanceTo(target)) {
                findClosestEvents(other, target, depth + 1, best);
            }
        }
        return best;
    }
}