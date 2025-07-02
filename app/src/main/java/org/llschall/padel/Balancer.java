package org.llschall.padel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Balancer {

    final Map<String, Integer> map = new java.util.HashMap<>();

    List<Session> balance(List<Session> weeks) {

        for (Session week : weeks) {
            for (Slot slot : week.slots) {
                String name = slot.player.name;
                if (!map.containsKey(name)) map.put(name, 0);
                map.compute(name, (k, i) -> i + 1);
            }
        }

        List<Session> list = new ArrayList<>();

        for (Session week : weeks) {

            Set<Wrapper> set = new TreeSet<>(new Comparator<Wrapper>() {
                @Override
                public int compare(Wrapper o1, Wrapper o2) {
                    if (o1.name.equals(o2.name)) {
                        return 0;
                    }
                    int delta = o1.count - o2.count;
                    if (delta != 0) {
                        return delta;
                    }
                    return o1.rank - o2.rank;
                }
            });

            for (int i = 0; i < week.slots.size(); i++) {
                Slot slot = week.slots.get(i);
                String name = slot.player.name;
                set.add(new Wrapper(name, i, map.get(name)));
            }

            Session week1 = new Session(week.name);
            list.add(week1);

            for (Wrapper wrapper : set) {
                Slot newSlot = new Slot(new Player(wrapper.name), false);
                week1.slots.add(newSlot);
            }

        }

        return list;
    }
}

class Wrapper {
    String name;
    int rank;
    int count;

    Wrapper(String name, int rank, int count) {
        this.name = name;
        this.rank = rank;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Wrapper wrapper)) return false;
        return Objects.equals(name, wrapper.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
