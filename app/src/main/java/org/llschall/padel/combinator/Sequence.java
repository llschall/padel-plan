package org.llschall.padel.combinator;

class Sequence {

    final String[] data;

    public Sequence(String... data) {
        this.data = data;
    }

    Sequence freeze() {
        return new Sequence(data.clone());
    }
}
