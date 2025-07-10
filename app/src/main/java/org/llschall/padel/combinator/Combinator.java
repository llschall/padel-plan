package org.llschall.padel.combinator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Combinator {

    final List<Sequence> cursor = new ArrayList<>();

    long count = 0;

    void combine(List<List<Sequence>> list, Consumer<List<Sequence>> consumer) {
        combineRec(0, list, consumer);
    }

    private void combineRec(int index, List<List<Sequence>> list, Consumer<List<Sequence>> consumer) {
        if (index == list.size()) {
            count++;
            consumer.accept(cursor);
            if (count % 1_000_000_000 == 0) {
                System.out.println(count);
            }
            return;
        }

        List<Sequence> strings1 = list.get(index);
        for (Sequence strings : strings1) {
            cursor.add(strings);
            combineRec(index + 1, list, consumer);
            cursor.removeLast();
        }
    }
}
