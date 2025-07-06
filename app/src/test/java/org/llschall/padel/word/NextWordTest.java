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

}
