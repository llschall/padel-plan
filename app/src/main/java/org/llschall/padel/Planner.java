package org.llschall.padel;

import java.io.IOException;
import java.util.List;

public class Planner {

    void process() throws IOException {
        System.out.println("Processing the planner...");

        List<Week> weeks = new WeekFileReader().readWeeks();
        new PlanningWriter().write(weeks);
    }

}
