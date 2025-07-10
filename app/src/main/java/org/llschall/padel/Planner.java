package org.llschall.padel;

import org.llschall.padel.strategy.Legacy;
import org.llschall.padel.strategy.Strategy;

import java.io.IOException;

public class Planner {

    void process() throws IOException {
        System.out.println("Processing the planner...");

        Planning weeks = new SessionFileReader().readSessions();
        new PlanningWriter().write(weeks, new Legacy(), new Strategy());
    }

}
