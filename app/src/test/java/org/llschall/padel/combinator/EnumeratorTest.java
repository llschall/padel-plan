package org.llschall.padel.combinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class EnumeratorTest {

    @Test
    void testEnumerator0() {
        Enumerator enumerator = new Enumerator();
        TestConsumer consumer = new TestConsumer(List.of(
                new Sequence("A"),
                new Sequence("C", "B")
        ));
        enumerator.process(new Sequence[]{
                new Sequence("A"),
                new Sequence("B", "C"),
        }, consumer);
        Assertions.assertEquals(2, enumerator.combinator.count);
        Assertions.assertTrue(consumer.found);
    }

    @Test
    void testEnumerator1() {
        Enumerator enumerator = new Enumerator();
        TestConsumer consumer = new TestConsumer(List.of(
                new Sequence("B", "A", "C"),
                new Sequence("F", "E", "D"),
                new Sequence("G", "H", "I")
        ));
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C"),
                new Sequence("D", "E", "F"),
                new Sequence("G", "H", "I"),
        }, consumer);
        Assertions.assertEquals(216, enumerator.combinator.count);
        Assertions.assertTrue(consumer.found);
    }

    @Test
    void testEnumerator2() {
        Enumerator enumerator = new Enumerator();
        TestConsumer consumer = new TestConsumer(List.of(
                new Sequence("A", "C", "B"),
                new Sequence("D", "E"),
                new Sequence("I", "H", "G", "F")
        ));
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C"),
                new Sequence("D", "E"),
                new Sequence("F", "G", "H", "I"),
        }, consumer);
        Assertions.assertEquals(288, enumerator.combinator.count);
        Assertions.assertTrue(consumer.found);
    }

    @Test
    void testEnumerator3() {
        Enumerator enumerator = new Enumerator();
        TestConsumer consumer = new TestConsumer(List.of(
                new Sequence("A", "B", "C", "D", "E", "I", "G", "H", "F", "J"),
                new Sequence("C", "B", "A")
        ));
        enumerator.process(new Sequence[]{
                new Sequence("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"),
                new Sequence("A", "B", "C"),
        }, consumer);
        Assertions.assertEquals(21772800, enumerator.combinator.count);
        Assertions.assertTrue(consumer.found);
    }
}

class TestConsumer implements Consumer<List<Sequence>> {

    List<Sequence> expected;
    boolean found = false;

    public TestConsumer(List<Sequence> expected) {
        this.expected = expected;
    }

    @Override
    public void accept(List<Sequence> sequences) {
        for (int i = 0; i < sequences.size(); i++) {
            if (!sequences.get(i).equals(expected.get(i))) return;
        }
        found = true;
    }
}