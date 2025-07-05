package org.llschall.padel.combinator;

import java.util.Arrays;
import java.util.Objects;

class Sequence {

    final String[] data;

    public Sequence(String... data) {
        this.data = data;
    }

    Sequence freeze() {
        return new Sequence(data.clone());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sequence sequence)) return false;
        return Objects.deepEquals(data, sequence.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
