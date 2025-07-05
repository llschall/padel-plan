package org.llschall.padel.combinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumeratorTest {

    @Test
    void testEnumerator0() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A"),
                new Sequence("B", "C"),
        });
        Assertions.assertEquals(2, enumerator.combinator.count);
    }

    @Test
    void testEnumerator1() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C"),
                new Sequence("D", "E", "F"),
                new Sequence("G", "H", "I"),
        });
        Assertions.assertEquals(216, enumerator.combinator.count);
    }

    @Test
    void testEnumerator2() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C"),
                new Sequence("D", "E"),
                new Sequence("F", "G", "H", "I"),
        });
        Assertions.assertEquals(288, enumerator.combinator.count);
    }

    @Test
    void testEnumerator3() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"),
                new Sequence("A", "B", "C"),
        });
        Assertions.assertEquals(21772800, enumerator.combinator.count);
    }
}
