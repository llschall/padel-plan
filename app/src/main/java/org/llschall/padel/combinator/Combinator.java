package org.llschall.padel.combinator;

import java.util.ArrayList;
import java.util.List;

public class Combinator {

    List<Sequence> cursor = new ArrayList<>();

    long count = 0;

    void combine(List<List<Sequence>> list) {
        combineRec(0, list);
    }

    private void combineRec(int index, List<List<Sequence>> list) {
        if (index == list.size()) {
            count++;
            if (count % 1_000_000_000 == 0) {
                System.out.println(count);
            }
            return;
        }

        List<Sequence> strings1 = list.get(index);
        for (Sequence strings : strings1) {
            cursor.add(strings);
            combineRec(index + 1, list);
            cursor.removeLast();
        }
    }
}
