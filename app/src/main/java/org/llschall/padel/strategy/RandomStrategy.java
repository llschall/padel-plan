package org.llschall.padel.strategy;

import org.llschall.padel.Planning;

public class RandomStrategy implements IStrategy {

    Planning optimized;

    @Override
    public String getName() {
        return "Random Strategy";
    }

    @Override
    public String getDetails() {
        return "This strategy randomly assigns players to sessions.";
    }

    @Override
    public void process(Planning sessions) {
        optimized = sessions;
    }

    @Override
    public Planning getOptimized() {
        return optimized;
    }

}
