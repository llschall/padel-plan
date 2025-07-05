package org.llschall.padel.combinator;

import java.util.ArrayList;
import java.util.List;

public class Enumerator {

    final List<List<Sequence>> combinations = new ArrayList<>();

    final Combinator combinator = new Combinator();

    List<Sequence> list;

    void process(Sequence[] sequences) {

        System.out.println("### Enumerations ###");
        for (Sequence sequence : sequences) {
            this.list = new ArrayList<>();
            combinations.add(this.list);
            processRec(sequence, 0);
        }

        System.out.println("### Combinations ###");
        combinator.combine(combinations);
        System.out.println(combinator.count + " combinations found.");
    }

    void processRec(Sequence sequence, int index) {

        if (index == sequence.data.length) {
            // store the current combination
            this.list.add(sequence.freeze());
            return;
        }

        for (int i = index; i < sequence.data.length; i++) {
            // swap the elements
            String s = sequence.data[i];
            sequence.data[i] = sequence.data[index];
            sequence.data[index] = s;
            processRec(sequence, index + 1);
            // swap back to restore the original order
            sequence.data[index] = sequence.data[i];
            sequence.data[i] = s;
        }
    }
}
