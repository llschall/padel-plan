package org.llschall.padel.strategy;

import org.llschall.padel.Planning;
import org.llschall.padel.Session;

public class LegacyStrategy implements IStrategy {

    private Planning optimized;

    @Override
    public String getName() {
        return "Legacy Strategy";
    }

    @Override
    public String getDetails() {
        return "This strategy gives the highest priority to the fastest subscribers.";
    }

    @Override
    public void process(Planning sessions) {

        for (Session session : sessions) {
            int size = session.slots.size();
            int count = 4*(size/4);

            for (int i = 0; i < size; i++) {
                session.slots.get(i).isSubstitute = i >= count;
            }
        }
        optimized = sessions;
    }

    @Override
    public Planning getOptimized() {
        // Return the sessions as they are, since this is a legacy strategy
        return optimized;
    }
}
