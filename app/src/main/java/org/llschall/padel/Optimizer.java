package org.llschall.padel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Optimizer {

    public Planning optimize(Planning weeks) {

        ArrayList<Session> list = new ArrayList<>();

        Set<String> substitutes = new HashSet<>();

        for (Session week : weeks) {

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

            List<String> names1 = new ArrayList<>();
            names1.addAll(first);
            names1.addAll(second);

            Session week1 = new Session(week.name);
            list.add(week1);

            for (int i = 0; i < names1.size(); i++) {
                String name = names1.get(i);
                boolean isSubstitute = i > 3;
                if (isSubstitute) {
                    substitutes.add(name);
                }
                Slot newSlot = new Slot(new Player(name), isSubstitute);
                week1.slots.add(newSlot);
            }
        }
        return new Planning(list);
    }

}
