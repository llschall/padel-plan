package org.llschall.padel;

public class Slot {

    final Player player;
    public boolean isSubstitute;

    public Slot(Player player, boolean isSubstitute) {
        this.player = player;
        this.isSubstitute = isSubstitute;
    }

    Slot copy() {
        return new Slot(player, isSubstitute);
    }

}
