package org.llschall.padel.combinator;

import java.util.ArrayList;
import java.util.List;

public class Enumerator {

    final List<List<String[]>> combinations = new ArrayList<>();

    List<String[]> list;

    public static void main(String[] args) {
        System.out.println("Enumerator.main");
        Enumerator enumerator = new Enumerator();
        enumerator.process(new String[][]{
                {"A", "B", "C",},
                {"D", "E", "F",},
                {"G", "H", "I",},
        });
    }

    void process(String[][] list) {
        for (String[] strings : list) {
            this.list = new ArrayList<>();
            combinations.add(this.list);
            processRec(strings, 0);
        }

        for (List<String[]> combination : combinations) {
            System.out.println("Combination:");
            for (String[] team : combination) {
                System.out.println("  " + String.join(", ", team));
            }
            System.out.println("combination.size() = " + combination.size());
        }

        Combinator combinator = new Combinator();
        combinator.combine(combinations);

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
