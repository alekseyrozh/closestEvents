package com.company;

import com.company.Structure.Event;

import java.util.Optional;

public class EventTreeNode {
    private Event event;
    private EventTreeNode left, right;

    public EventTreeNode(Event event, EventTreeNode left, EventTreeNode right) {
        this.event = event;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + event.getPosition()
                + ", " + Optional.ofNullable(left).map(EventTreeNode::toString).orElse("null")
                + ", " + Optional.ofNullable(right).map(EventTreeNode::toString).orElse("null")
                + ")";
    }

    public Event getEvent() {
        return event;
    }

    public EventTreeNode getLeft() {
        return left;
    }

    public EventTreeNode getRight() {
        return right;
    }
}
