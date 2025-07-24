package org.llschall.padel;

import org.llschall.padel.strategy.LegacyStrategy;
import org.llschall.padel.strategy.RandomStrategy;

import java.io.IOException;

public class Planner {

    void process() throws IOException {
        System.out.println("Processing the planner...");

        Planning weeks = new SessionFileReader().readSessions();
        new PlanningWriter().write(weeks,
                new LegacyStrategy(),
                new RandomStrategy()
        );
    }

}
