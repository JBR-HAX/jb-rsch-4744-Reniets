package org.jetbrains.assignment;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Location other) {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location other = (Location) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }

    public int hashCode() {
        return x * 31 + y;
    }
}
