package org.llschall.padel;

public class Slot {

    Player player;
    boolean isSubstitute;

    public Slot(Player player, boolean isSubstitute) {
        this.player = player;
        this.isSubstitute = isSubstitute;
    }
}
