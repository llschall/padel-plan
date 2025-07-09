package org.llschall.padel;

import java.util.ArrayList;
import java.util.List;

public class Session {

    final String name;

    public Session(String name) {
        this.name = name;
    }

    public final List<Slot> slots = new ArrayList<>();

}
