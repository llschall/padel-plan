package org.llschall.padel.word;

import org.jspecify.annotations.NonNull;

public class Word implements Iterable<Word> {

    public final CharSequence chars;

    public Word(CharSequence chars) {
        this.chars = chars;
    }

    @Override
    public @NonNull WordIterator iterator() {
        return new WordIterator(this);
    }
}
