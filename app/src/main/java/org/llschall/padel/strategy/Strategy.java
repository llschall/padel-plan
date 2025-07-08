package org.llschall.padel.strategy;

import org.llschall.padel.Session;

import java.util.List;

public class Strategy {

    public String name;

    public List<Step> steps;

    public List<Session> process(List<Session> sessions) {
        for (Step step : steps) {
            sessions = step.process(sessions);
        }
        return sessions;
    }

}
