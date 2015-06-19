package net.caspervg.polyga.step;

import net.caspervg.jgaf.Goal;
import net.caspervg.jgaf.Optimizer;
import net.caspervg.jgaf.step.*;
import net.caspervg.jgaf.step.breeder.BasicBreeder;
import net.caspervg.jgaf.step.killer.BasicKiller;
import net.caspervg.jgaf.step.selector.FitnessProportionateSelector;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Polygon;

public class PolyStepProvider implements StepProvider<Organism> {

    private Fitter<Organism> fitter;
    private Creator<Organism> creator;
    private Breeder<Organism> breeder;
    private Mutator<Organism> mutator;
    private Killer<Organism> killer;
    private Optimizer<Organism> optimizer;
    private Selector<Organism> selector;

    public PolyStepProvider(Polygon polygon) {
        this.fitter = new OrganismFitter();
        this.creator = new OrganismCreator(polygon);
        this.breeder = new BasicBreeder<>(new OrganismCrosser());
        this.mutator = new OrganismMutator(polygon);
        this.killer = new BasicKiller<>();
        this.optimizer = new Optimizer<>(fitter, new Goal.Maximum());
        this.selector = new FitnessProportionateSelector<>(fitter);
    }

    @Override
    public Creator<Organism> creator() {
        return creator;
    }

    @Override
    public Breeder<Organism> breeder() {
        return breeder;
    }

    @Override
    public Mutator<Organism> mutator() {
        return mutator;
    }

    @Override
    public Killer<Organism> killer() {
        return killer;
    }

    @Override
    public Fitter<Organism> fitter() {
        return fitter;
    }

    @Override
    public Selector<Organism> selector() {
        return selector;
    }

    @Override
    public Optimizer<Organism> optimizer() {
        return optimizer;
    }
}
