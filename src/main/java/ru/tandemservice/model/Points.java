package ru.tandemservice.model;

public class Points {
    private int points;

    public Points(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.valueOf(points);
    }
}
