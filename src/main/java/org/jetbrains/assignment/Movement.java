package org.jetbrains.assignment;

enum Direction {
    NORTH, EAST, SOUTH, WEST
}
public class Movement {
    private Direction direction;
    private int steps;

    public Movement(Direction direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }

    public String toString() {
        return direction + " " + steps;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Movement other) {
            return direction == other.direction && steps == other.steps;
        }
        return false;
    }

    public int hashCode() {
        return direction.hashCode() * 31 + steps;
    }
}
