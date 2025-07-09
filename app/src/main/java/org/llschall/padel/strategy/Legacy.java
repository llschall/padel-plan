package org.llschall.padel.strategy;

import org.llschall.padel.Session;

import java.util.List;

public class Legacy implements IStrategy {

    private List<Session> optimized;

    @Override
    public String getName() {
        return "Legacy Strategy";
    }

    @Override
    public String getDetails() {
        return "This is a legacy strategy that processes sessions in a basic way.";
    }

    @Override
    public void process(List<Session> sessions) {

        for (Session session : sessions) {
            for (int i = 0; i < session.slots.size(); i++) {
                session.slots.get(i).isSubstitute = i > 3;
            }
        }


        optimized = sessions;
    }

    @Override
    public List<Session> getOptimized() {
        // Return the sessions as they are, since this is a legacy strategy
        return optimized;
    }
}
