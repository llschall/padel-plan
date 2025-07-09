package org.llschall.padel;

public class Slot {

    Player player;
    public boolean isSubstitute;

    public Slot(Player player, boolean isSubstitute) {
        this.player = player;
        this.isSubstitute = isSubstitute;
    }
}
