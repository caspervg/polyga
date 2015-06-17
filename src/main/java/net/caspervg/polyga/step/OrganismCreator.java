package net.caspervg.polyga.step;

import net.caspervg.jgaf.Arguments;
import net.caspervg.jgaf.Population;
import net.caspervg.jgaf.step.Creator;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Point;
import net.caspervg.polyga.bean.Polygon;

import java.util.ArrayList;
import java.util.List;

public class OrganismCreator implements Creator<Organism> {

    private Polygon polygon;
    private int pointsPerOrganism;

    public OrganismCreator(Polygon polygon) {
        this.polygon = polygon;
        this.pointsPerOrganism = 5;
    }

    @Override
    public Population<Organism> create(Arguments arguments) {
        List<Organism> organisms = new ArrayList<>(arguments.populationSize());

        for (int i = 0; i < arguments.populationSize(); i++) {
            List<Point> points = new ArrayList<>(pointsPerOrganism);
            for (int j = 0; j < pointsPerOrganism; j++) {
                points.add(polygon.randomPoint());
            }
            organisms.add(new Organism(points));
        }

        return new Population.Default<>(organisms);
    }
}
