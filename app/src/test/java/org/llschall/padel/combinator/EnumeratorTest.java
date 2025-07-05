package org.llschall.padel.combinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class EnumeratorTest {

    @Test
    void testEnumerator0() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A"),
                new Sequence("B", "C"),
        }, new TestConsumer());
        Assertions.assertEquals(2, enumerator.combinator.count);
    }

    @Test
    void testEnumerator1() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C"),
                new Sequence("D", "E", "F"),
                new Sequence("G", "H", "I"),
        }, new TestConsumer());
        Assertions.assertEquals(216, enumerator.combinator.count);
    }

    @Test
    void testEnumerator2() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C"),
                new Sequence("D", "E"),
                new Sequence("F", "G", "H", "I"),
        }, new TestConsumer());
        Assertions.assertEquals(288, enumerator.combinator.count);
    }

    @Test
    void testEnumerator3() {
        Enumerator enumerator = new Enumerator();
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"),
                new Sequence("A", "B", "C"),
        }, new TestConsumer());
        Assertions.assertEquals(21772800, enumerator.combinator.count);
    }
}

class TestConsumer implements Consumer<List<Sequence>> {

    @Override
    public void accept(List<Sequence> sequences) {
        // This is a no-op consumer for testing purposes.
        // In a real scenario, you might want to process the sequences further.
    }
}