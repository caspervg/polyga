package net.caspervg.polyga.step;

import net.caspervg.jgaf.Arguments;
import net.caspervg.jgaf.step.Mutator;
import net.caspervg.polyga.Util;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Point;
import net.caspervg.polyga.bean.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrganismMutator implements Mutator<Organism> {

    private Random random ;
    private Polygon polygon;

    public OrganismMutator(Polygon polygon) {
        this.random = new Random();
        this.polygon = polygon;
    }

    @Override
    public Organism mutate(Arguments arguments, Organism child) {
        int[] vectors = {-1, 1};
        int mutationPoint = random.nextInt(child.getPoints().size());
        double maxMutation = arguments.maximumMutationAmount().doubleValue();

        while (true) {
            int mutationX = vectors[random.nextInt(2)];
            int mutationY = vectors[random.nextInt(2)];
            double mutationXAmount = Util.doubleBetween(-maxMutation, maxMutation);
            double mutationYAmount = Util.doubleBetween(-maxMutation, maxMutation);

            Point temp = new Point(
                    child.getPoints().get(mutationPoint).getX() + mutationX*mutationXAmount,
                    child.getPoints().get(mutationPoint).getY() + mutationY*mutationYAmount
            );

            if (polygon.contains(temp)) {
                List<Point> points = new ArrayList<>(child.getPoints());
                points.set(mutationPoint, temp);
                return new Organism(points);
            }
        }
    }
}
