package org.llschall.padel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Rater {

    final List<Session> sessions;
    final Map<Player, Rating> map = new HashMap<>();

    Rater(List<Session> sessions) {
        this.sessions = sessions;

        for (Session session : sessions) {
            for (Slot slot : session.slots) {
                Player player = slot.player;
                if (!map.containsKey(player)) {
                    map.put(player, new Rating());
                }
                Rating rating = map.get(player);
                if (slot.isSubstitute) {
                    rating.substitute++;
                } else {
                    rating.slot++;
                }
            }
        }
    }
}

class Rating {

    int slot = 0;
    int substitute = 0;

    int getTotal() {
        return slot + substitute;
    }

    int getRating() {
        int total = getTotal();
        if (total == 0) throw new PadelPlanException("Unexpected total rating of 0");
        return 100 * slot / total;
    }

}