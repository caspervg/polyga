package net.caspervg.polyga;

import net.caspervg.jgaf.Arguments;
import net.caspervg.jgaf.Goal;

public class PolyArguments extends Arguments.Default {

    public Goal goal() {
        return new Goal.Maximum();
    }

    public int populationSize() {
        return 10000;
    }

    public int numIterations() {
        return 500;
    }

}
