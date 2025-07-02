package org.llschall.padel;

import java.util.Objects;

public class Player {

    final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player player)) return false;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
