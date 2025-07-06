package org.llschall.padel.word;

import org.llschall.padel.PadelPlanException;

import java.io.StringWriter;
import java.util.Iterator;

public class WordIterator implements Iterator<Word> {

    private Word word;

    public WordIterator(Word word) {
        this.word = word;
    }

    @Override
    public boolean hasNext() {
        String chars = word.chars;
        for (int i = chars.length() - 1; i > 0; i--) {
            char c0 = chars.charAt(i - 1);
            char c1 = chars.charAt(i);
            if (c1 > c0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Word next() {
        String chars = word.chars;
        for (int i = chars.length() - 1; i > 0; i--) {
            char c0 = chars.charAt(i - 1);
            char c1 = chars.charAt(i);
            if (c1 > c0) {
                word = buildNext(i - 1);
                return word;
            }
        }
        throw new PadelPlanException("No more characters in the word to iterate over.");
    }

    Word buildNext(int i) {
        CharSequence chars = word.chars;
        char c = chars.charAt(i);
        String next = chars.subSequence(i + 1, chars.length()).toString();
        StringWriter writer = new StringWriter();
        writer.write(chars.subSequence(0, i).toString());
        // Sort the characters of next in ascending order
        char[] nextChars = next.toCharArray();
        java.util.Arrays.sort(nextChars);
        for (int i1 = 0; i1 < nextChars.length; i1++) {
            char n = nextChars[i1];
            if (n > c) {
                writer.write(n);
                nextChars[i1] = c;
                break;
            }
        }
        for (char n : nextChars) {
            writer.write(n);
        }

        return new Word(writer.toString());
    }
}
