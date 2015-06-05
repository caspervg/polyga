package net.caspervg.polyga.step;

import net.caspervg.jgaf.step.Fitter;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Point;

import java.util.List;

public class OrganismFitter implements Fitter<Organism> {
    @SuppressWarnings("unchecked")
    @Override
    public Double calculate(Organism organism) {
        List<Point> points = organism.getPoints();
        Double fitness = 0.0D;

        if (points.size() < 1) {
            System.err.println("Could not calculate fitness of an empty organism");
            System.exit(-1);
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                double xDiffSquared, yDiffSquared, distance;
                xDiffSquared = Math.pow(points.get(i).getX() - points.get(j).getX(), 2);
                yDiffSquared = Math.pow(points.get(i).getY() - points.get(j).getY(), 2);
                distance = Math.sqrt(xDiffSquared + yDiffSquared);
                fitness += Math.sqrt(distance);
            }
        }

        return fitness;
    }
}
