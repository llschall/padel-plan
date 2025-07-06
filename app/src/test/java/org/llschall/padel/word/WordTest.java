package org.llschall.padel.word;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordTest {

    @Test
    void testWordCreation() {
        Word word = new Word("abc");
        Assertions.assertEquals("abc", word.chars.toString());
    }
}
