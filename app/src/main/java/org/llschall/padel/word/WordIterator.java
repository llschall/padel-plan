package org.llschall.padel.word;

import org.llschall.padel.PadelPlanException;

import java.util.Iterator;

public class WordIterator implements Iterator<Word> {

    private Word word;

    public WordIterator(Word word) {
        this.word = word;
    }

    @Override
    public boolean hasNext() {
        CharSequence chars = word.chars;
        for (int i = chars.length() - 1; i > 0; i--) {
            char c0 = chars.charAt(i);
            char c1 = chars.charAt(i - 1);
            if (c1 < c0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Word next() {
        if (!hasNext()) {
            throw new PadelPlanException("No more characters in the word to iterate over.");
        }
        return new Word("");
    }
}
