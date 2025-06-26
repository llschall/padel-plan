package org.llschall.padel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OptimizerTest {

    Player playerA = new Player("A");
    Player playerB = new Player("B");
    Player playerC = new Player("C");
    Player playerD = new Player("D");
    Player playerE = new Player("E");
    Player playerF = new Player("F");
    Player playerG = new Player("G");

    @Test
    void testOptimize() {

        // Create a sample list of weeks
        Week week1 = new Week("Week 1");

        week1.slots.add(new Slot(playerA, false));
        week1.slots.add(new Slot(playerB, false));
        week1.slots.add(new Slot(playerC, false));
        week1.slots.add(new Slot(playerD, false));
        week1.slots.add(new Slot(playerE, false));
        week1.slots.add(new Slot(playerF, false));

        Week week2 = new Week("Week 2");

        week2.slots.add(new Slot(playerB, false));
        week2.slots.add(new Slot(playerD, false));
        week2.slots.add(new Slot(playerE, false));
        week2.slots.add(new Slot(playerF, false));
        week2.slots.add(new Slot(playerG, false));

        // Create an instance of Optimizer
        Optimizer optimizer = new Optimizer();
        List<Week> optimized = optimizer.optimize(List.of(week1, week2));

        Assertions.assertEquals("ABCDEF",dumpWeek(optimized.get(0)));
        Assertions.assertEquals("EFBDG",dumpWeek(optimized.get(1)));
    }

    String dumpWeek(Week week) {
        StringBuilder sb = new StringBuilder();
        for (Slot slot : week.slots) {
            sb.append(slot.player.name);
        }
        return sb.toString();
    }

}
