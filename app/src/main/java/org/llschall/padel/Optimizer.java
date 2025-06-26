package org.llschall.padel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Optimizer {

    List<Week> optimize(List<Week> weeks) {

        ArrayList<Week> list = new ArrayList<>();

        Set<String> substitutes = new HashSet<>();

        for (Week week : weeks) {

            List<String> first = new ArrayList<>();
            List<String> second = new ArrayList<>();

            List<String> names = week.slots.stream().map((s) -> s.player.name).toList();
            for (String name : names) {
                if (substitutes.contains(name)) {
                    first.add(name);
                } else {
                    second.add(name);
                }
            }

            Week week1 = new Week(week.name);
            list.add(week1);

            for (String name : first) {
                Slot newSlot = new Slot(new Player(name), false);
                week1.slots.add(newSlot);
            }

            for (String name : second) {
                Slot newSlot = new Slot(new Player(name), false);
                week1.slots.add(newSlot);
                if (week1.slots.size() > 4)
                    substitutes.add(name);
            }
        }
        return list;
    }

}
