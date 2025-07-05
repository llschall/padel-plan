package org.llschall.padel.combinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumeratorTest {

    @Test
    void testEnumerator0() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new String[][]{
                {"A",},
                {"B", "C",},
        });
        Assertions.assertEquals(2, enumerator.combinator.count);
    }

    @Test
    void testEnumerator1() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new String[][]{
                {"A", "B", "C",},
                {"D", "E", "F",},
                {"G", "H", "I",},
        });
        Assertions.assertEquals(216, enumerator.combinator.count);
    }

    @Test
    void testEnumerator2() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new String[][]{
                {"A", "B", "C",},
                {"D", "E",},
                {"F", "G", "H", "I",},
        });
        Assertions.assertEquals(288, enumerator.combinator.count);
    }

    @Test
    void testEnumerator3() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new String[][]{
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"},
                {"A", "B", "C"},
        });
        Assertions.assertEquals(21772800, enumerator.combinator.count);
    }
}
