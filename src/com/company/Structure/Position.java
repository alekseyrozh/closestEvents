package com.company.Structure;

public class Position {
    public final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Position) obj).x == x && ((Position) obj).y == y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public int manhattanDistanceTo(Position position) {
        return Math.abs(position.x - this.x) + Math.abs(position.y - this.y);
    }

    public int straightDistance(Position otherPosition, int axis) {
        return axis % 2 == 0 ? this.x - otherPosition.x : this.y - otherPosition.y;
    }

}
