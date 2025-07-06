package org.llschall.padel.word;

import org.jspecify.annotations.NonNull;

public class Word implements Iterable<Word> {

    public final String chars;

    public Word(String chars) {
        this.chars = chars;
    }

    @Override
    public @NonNull WordIterator iterator() {
        return new WordIterator(this);
    }
}
