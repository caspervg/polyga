package net.caspervg.polyga.bean;

import java.util.List;

public class Organism {
    private List<Point> points;

    public Organism(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Organism{" +
                "points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organism organism = (Organism) o;

        return !(getPoints() != null ? !getPoints().equals(organism.getPoints()) : organism.getPoints() != null);

    }

    @Override
    public int hashCode() {
        return getPoints() != null ? getPoints().hashCode() : 0;
    }
}
