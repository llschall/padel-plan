package org.llschall.padel.word;

import org.jspecify.annotations.NonNull;

import java.util.Iterator;

public class Word implements Iterable<Word> {

    public final CharSequence chars;

    public Word(CharSequence chars) {
        this.chars = chars;
    }

    @Override
    public @NonNull Iterator<Word> iterator() {
        return new WordIterator(this);
    }
}
