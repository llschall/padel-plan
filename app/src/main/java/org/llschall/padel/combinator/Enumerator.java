package org.llschall.padel.combinator;

import java.util.ArrayList;
import java.util.List;

public class Enumerator {

    final List<List<String[]>> combinations = new ArrayList<>();

    List<String[]> list;

    public static void main(String[] args) {

        System.out.println("### Started ###");
        long time = System.nanoTime();

        Enumerator enumerator = new Enumerator();
        enumerator.process(new String[][]{
                {"A", "B", "C",},
                {"D", "E", "F",},
                {"G", "H", "I",},
        });

        time = System.nanoTime() - time;

        // print time in seconds
        System.out.println("Time taken: " + time / 1_000_000 + " ms");
        System.out.println("### Finished ###");

    }

    void process(String[][] list) {

        System.out.println("### Enumerations ###");
        for (String[] strings : list) {
            this.list = new ArrayList<>();
            combinations.add(this.list);
            processRec(strings, 0);
        }

        System.out.println("### Combinations ###");
        Combinator combinator = new Combinator();
        combinator.combine(combinations);
        System.out.println(combinator.count + " combinations found.");

    }

    void processRec(String[] list, int index) {

        if (index == list.length) {
            // store the current combination
            this.list.add(list.clone());
            return;
        }

        for (int i = index; i < list.length; i++) {
            // swap the elements
            String s = list[i];
            list[i] = list[index];
            list[index] = s;
            processRec(list, index + 1);
            // swap back to restore the original order
            list[index] = list[i];
            list[i] = s;
        }
    }
}
