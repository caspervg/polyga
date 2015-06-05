package net.caspervg.polyga.step;

import com.google.common.collect.Lists;
import net.caspervg.jgaf.step.Crosser;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrganismCrosser implements Crosser<Organism> {
    @Override
    public List<Organism> cross(List<Organism> parents) {
        Organism father = parents.get(0);
        Organism mother = parents.get(1);
        int childSize = Math.min(father.getPoints().size(), mother.getPoints().size());
        int crossoverPoint = new Random().nextInt(childSize - 1) + 1;

        List<Point> child1Points = new ArrayList<>(childSize);
        List<Point> child2Points = new ArrayList<>(childSize);
        for (int i = 0; i < childSize; i++) {
            if (i < crossoverPoint) {
                child1Points.add(new Point(father.getPoints().get(i).getX(), father.getPoints().get(i).getY()));
                child2Points.add(new Point(mother.getPoints().get(i).getX(), mother.getPoints().get(i).getY()));
            } else {
                child1Points.add(new Point(mother.getPoints().get(i).getX(), mother.getPoints().get(i).getY()));
                child2Points.add(new Point(father.getPoints().get(i).getX(), father.getPoints().get(i).getY()));
            }
        }

        return Lists.newArrayList(new Organism(child1Points), new Organism(child2Points));
    }
}
