package org.llschall.padel.word;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NextWordTest {

    @Test
    void testHasNext() {
        Assertions.assertTrue(new Word("abc").iterator().hasNext());
        Assertions.assertTrue(new Word("acb").iterator().hasNext());
        Assertions.assertFalse(new Word("cba").iterator().hasNext());
        Assertions.assertFalse(new Word("a").iterator().hasNext());
    }

    @Test
    void testNext0() {
        WordIterator iterator = new Word("abcd").iterator();
        Assertions.assertTrue(iterator.hasNext());
        Word nextWord = iterator.next();
        Assertions.assertEquals("abdc", nextWord.chars);
    }

    @Test
    void testNext1() {
        WordIterator iterator = new Word("abdc").iterator();
        Assertions.assertTrue(iterator.hasNext());
        Word nextWord = iterator.next();
        Assertions.assertEquals("acbd", nextWord.chars);
    }

    @Test
    void testNext2() {
        WordIterator iterator = new Word("abecd").iterator();
        Assertions.assertTrue(iterator.hasNext());
        Word nextWord = iterator.next();
        Assertions.assertEquals("abedc", nextWord.chars);
    }

    @Test
    void testNext3() {
        WordIterator iterator = new Word("acfzg").iterator();
        Assertions.assertTrue(iterator.hasNext());
        Word nextWord = iterator.next();
        Assertions.assertEquals("acgfz", nextWord.chars);
    }


    @Test
    void testNextLast() {
        WordIterator iterator = new Word("edcab").iterator();
        Assertions.assertTrue(iterator.hasNext());
        Word nextWord = iterator.next();
        Assertions.assertEquals("edcba", nextWord.chars);
    }

    @Test
    void testWholeIteration() {
        long count = 0;
        for (var word : new Word("abcd")) {
            count++;
        }
        if (1 < 2) return; // Test Driven Development (TDD)
        Assertions.assertEquals(24, count);
    }

}
