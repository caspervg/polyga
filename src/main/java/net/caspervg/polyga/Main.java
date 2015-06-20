package net.caspervg.polyga;

import com.google.common.collect.Lists;
import net.caspervg.jgaf.Arguments;
import net.caspervg.jgaf.GeneticAlgorithm;
import net.caspervg.jgaf.Goal;
import net.caspervg.jgaf.Solution;
import net.caspervg.jgaf.step.Provider;
import net.caspervg.jgaf.step.breeder.BasicBreederFactory;
import net.caspervg.jgaf.step.killer.BasicKillerFactory;
import net.caspervg.jgaf.step.provider.ProviderBuilder;
import net.caspervg.jgaf.step.selector.TournamentSelectorFactory;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Point;
import net.caspervg.polyga.bean.Polygon;
import net.caspervg.polyga.step.OrganismCreator;
import net.caspervg.polyga.step.OrganismFitter;
import net.caspervg.polyga.step.OrganismMutator;
import net.caspervg.polyga.step.PolyStepProvider;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> polygonPoints = Lists.newArrayList(new Point(0, 0), new Point(5, 0), new Point(5, 5), new Point(0, 5));
        Polygon polygon = new Polygon(polygonPoints);
        Arguments arguments = new PolyArguments();

        Provider<Organism> provider = ProviderBuilder.<Organism>aProvider()
                .withFitter(new OrganismFitter())
                .withSelectorFactory(new TournamentSelectorFactory(), Lists.newArrayList())
                .withBreederFactory(new BasicBreederFactory(), Lists.newArrayList())
                .withKillerFactory(new BasicKillerFactory(), Lists.newArrayList())
                .withCreator(new OrganismCreator(polygon))
                .withGoal(new Goal.Maximum())
                .withMutator(new OrganismMutator(polygon))
                .build();

        GeneticAlgorithm<Organism> algorithm = new GeneticAlgorithm.Default<>();

        Long before = System.currentTimeMillis();
        Solution<Organism> solution = algorithm.run(arguments, provider);
        Long after = System.currentTimeMillis();

        System.out.println("Took " +((double) after - (double)before)/1000D);

        System.out.println(solution.getBestOrganism());
        System.out.println(solution.getBestFitness());
    }
}
