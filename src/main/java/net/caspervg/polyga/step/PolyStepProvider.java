package net.caspervg.polyga.step;

import net.caspervg.jgaf.Optimizer;
import net.caspervg.jgaf.step.*;
import net.caspervg.polyga.bean.Organism;
import net.caspervg.polyga.bean.Polygon;

public class PolyStepProvider implements StepProvider<Organism> {

    private Fitter<Organism> fitter;
    private Creator<Organism> creator;
    private Breeder<Organism> breeder;
    private Mutator<Organism> mutator;
    private Killer<Organism> killer;
    private Optimizer<Organism> optimizer;

    public PolyStepProvider(Polygon polygon) {
        this.fitter = new OrganismFitter();
        this.creator = new OrganismCreator(polygon);
        this.breeder = new Breeder.Default<>(new OrganismCrosser(), fitter);
        this.mutator = new OrganismMutator(polygon);
        this.killer = new Killer.Default<>(fitter);
        this.optimizer = new Optimizer<>(fitter, Optimizer.Goal.MAXIMUM);
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
    public Optimizer<Organism> optimizer() {
        return optimizer;
    }
}
