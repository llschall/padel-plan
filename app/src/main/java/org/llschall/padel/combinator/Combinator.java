package org.llschall.padel.combinator;

import java.util.ArrayList;
import java.util.List;

public class Combinator {

    List<String[]> cursor = new ArrayList<>();

    void combine(List<List<String[]>> list) {
        combineRec(0, list);
    }

    void combineRec(int index, List<List<String[]>> list) {
        if (index == list.size()) {
            for (String[] strings : cursor) {
                System.out.print(" > " + String.join(" ", strings));
            }
            System.out.println();
            return;
        }

        List<String[]> strings1 = list.get(index);
        for (String[] strings : strings1) {
            cursor.add(strings);
            combineRec(index + 1, list);
            cursor.removeLast();
        }
    }
}
