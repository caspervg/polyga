package net.caspervg.polyga;

import com.google.common.collect.Lists;
import net.caspervg.jgaf.Arguments;
import net.caspervg.jgaf.GeneticAlgorithm;
import net.caspervg.jgaf.Solution;
import net.caspervg.jgaf.step.StepProvider;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Point;
import net.caspervg.polyga.bean.Polygon;
import net.caspervg.polyga.step.PolyStepProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> polygonPoints = Lists.newArrayList(new Point(0, 0), new Point(5, 0), new Point(5, 5), new Point(0, 5));
        Polygon polygon = new Polygon(polygonPoints);
        Arguments arguments = new PolyArguments();

        StepProvider<Organism> provider = new PolyStepProvider(polygon);
        GeneticAlgorithm<Organism> algorithm = new GeneticAlgorithm.Default<>();

        Long before = System.currentTimeMillis();
        Solution<Organism> solution = algorithm.run(arguments, provider);
        Long after = System.currentTimeMillis();

        System.out.println("Took " +((double) after - (double)before)/1000D);

        System.out.println(solution.getBestOrganism());
        System.out.println(solution.getBestFitness());
    }
}
