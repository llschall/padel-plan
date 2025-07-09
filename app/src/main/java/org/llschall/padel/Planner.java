package org.llschall.padel;

import org.llschall.padel.strategy.Strategy;

import java.io.IOException;
import java.util.List;

public class Planner {

    void process() throws IOException {
        System.out.println("Processing the planner...");

        List<Session> weeks = new SessionFileReader().readSessions();
        Strategy balancedStrategy = new Strategy("Balanced Strategy");
        balancedStrategy.process(weeks);
        
        new PlanningWriter().write(weeks, balancedStrategy);
    }

}
