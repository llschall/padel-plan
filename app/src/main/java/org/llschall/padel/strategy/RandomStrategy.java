package org.llschall.padel.strategy;

import org.llschall.padel.Planning;
import org.llschall.padel.Rater;
import org.llschall.padel.Session;

import java.util.Collections;
import java.util.Random;

public class RandomStrategy implements IStrategy {

    Planning optimized;

    @Override
    public String getName() {
        return "Random Strategy";
    }

    @Override
    public String getDetails() {
        return "This strategy tries thousands of random combinations and keeps one of the best rated ones.";
    }

    @Override
    public void process(Planning sessions) {

        Random random = new Random(1982);
        int max = -1;

        for (int i = 0; i < 999_999; i++) {
            Planning copy = sessions.copy();
            for (Session session : copy) {
                int size = session.slots.size();
                int count = 4*(size/4);

                Collections.shuffle(session.slots, random);
                for (int i1 = count; i1 < session.slots.size(); i1++) {
                    session.slots.get(i1).isSubstitute = true;
                }
            }
            Rater rater = new Rater(copy);
            int score = rater.score;
            if (score > max) {
                max = score;
                optimized = copy;
            }
        }
    }

    @Override
    public Planning getOptimized() {
        return optimized;
    }

}
