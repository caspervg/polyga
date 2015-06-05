package net.caspervg.polyga.bean;

import net.caspervg.polyga.Util;

import java.util.List;

public class Polygon {
    private List<Point> points;

    public Polygon(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public boolean contains(Point p) {
        int counter = 0;
        Side correctSide;

        do {
            correctSide = getSide(getSegment(counter), p);
            counter++;
        } while (correctSide == Side.ON_TOP && (counter + 1) < points.size());

        if (correctSide == Side.ON_TOP) {
            System.err.println("Given point was on top of all sides of the polygon");
            System.exit(-1);
        }

        for (int i = 0; i < points.size(); i++) {
            Side segmentSide = getSide(getSegment(i), p);
            if (segmentSide == Side.ON_TOP) continue;
            if (segmentSide != correctSide) return false;
        }

        return true;
    }

    public Point randomPoint() {
        BoundingBox box = boundingBox();
        Point point;

        do {
            point = new Point(Util.doubleBetween(box.getxMin(), box.getxMax()),
                    Util.doubleBetween(box.getyMin(), box.getyMax()));
        } while (! contains(point));

        return point;
    }

    public BoundingBox boundingBox() {
        BoundingBox box = new BoundingBox(Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, Double.MIN_VALUE);

        for (Point p : points) {
            if (p.getX() < box.getxMin()) box.setxMin(p.getX());
            if (p.getX() > box.getxMax()) box.setxMax(p.getX());
            if (p.getY() < box.getyMin()) box.setyMin(p.getY());
            if (p.getY() > box.getyMax()) box.setyMax(p.getY());
        }

        return box;
    }

    private Segment getSegment(int counter) {
        return new Segment(points.get(counter), points.get((counter + 1) % points.size()));
    }

    private Side getSide(Segment segment, Point p) {
        double calcSide = ((p.getY() - segment.getStart().getY()) * (segment.getEnd().getX() - segment.getStart().getX()))
                            - ((p.getX() - segment.getStart().getX()) * (segment.getEnd().getY() - segment.getStart().getY()));

        if (calcSide > 0) {
            return Side.LEFT;
        } else if (calcSide < 0) {
            return Side.RIGHT;
        } else {
            return Side.ON_TOP;
        }
    }
}
