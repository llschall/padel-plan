package org.llschall.padel.strategy;

import org.llschall.padel.Planning;
import org.llschall.padel.Session;
import org.llschall.padel.Slot;

import java.util.ArrayList;
import java.util.Random;

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

        Random random = new Random();

        for (Session session : sessions) {
            ArrayList<Slot> list = new ArrayList<>(session.slots);
            int i = random.nextInt(list.size());
            Slot slot = list.remove(i);
            list.add(slot);
            session.slots.clear();
            session.slots.addAll(list);
        }

        optimized = sessions;

    }

    @Override
    public Planning getOptimized() {
        return optimized;
    }

}
