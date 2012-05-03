/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.text;

import java.util.Locale;
import libcore.icu.ICU;
import libcore.icu.NativeBreakIterator;

/**
 * Locates boundaries in text. This class defines a protocol for objects that
 * break up a piece of natural-language text according to a set of criteria.
 * Instances or subclasses of {@code BreakIterator} can be provided, for
 * example, to break a piece of text into words, sentences, or logical
 * characters according to the conventions of some language or group of
 * languages. We provide four built-in types of {@code BreakIterator}:
 * <ul>
 * <li>{@link #getSentenceInstance()} returns a {@code BreakIterator} that
 * locates boundaries between sentences. This is useful for triple-click
 * selection, for example.</li>
 * <li>{@link #getWordInstance()} returns a {@code BreakIterator} that locates
 * boundaries between words. This is useful for double-click selection or "find
 * whole words" searches. This type of {@code BreakIterator} makes sure there is
 * a boundary position at the beginning and end of each legal word (numbers
 * count as words, too). Whitespace and punctuation are kept separate from real
 * words.</li>
 * <li>{@code getLineInstance()} returns a {@code BreakIterator} that locates
 * positions where it is legal for a text editor to wrap lines. This is similar
 * to word breaking, but not the same: punctuation and whitespace are generally
 * kept with words (you don't want a line to start with whitespace, for
 * example), and some special characters can force a position to be considered a
 * line break position or prevent a position from being a line break position.</li>
 * <li>{@code getCharacterInstance()} returns a {@code BreakIterator} that
 * locates boundaries between logical characters. Because of the structure of
 * the Unicode encoding, a logical character may be stored internally as more
 * than one Unicode code point. (A with an umlaut may be stored as an a followed
 * by a separate combining umlaut character, for example, but the user still
 * thinks of it as one character.) This iterator allows various processes
 * (especially text editors) to treat as characters the units of text that a
 * user would think of as characters, rather than the units of text that the
 * computer sees as "characters".</li>
 * </ul> {@code BreakIterator}'s interface follows an "iterator" model (hence
 * the name), meaning it has a concept of a "current position" and methods like
 * {@code first()}, {@code last()}, {@code next()}, and {@code previous()} that
 * update the current position. All {@code BreakIterator}s uphold the following
 * invariants:
 * <ul>
 * <li>The beginning and end of the text are always treated as boundary
 * positions.</li>
 * <li>The current position of the iterator is always a boundary position
 * (random- access methods move the iterator to the nearest boundary position
 * before or after the specified position, not <i>to</i> the specified
 * position).</li>
 * <li>{@code DONE} is used as a flag to indicate when iteration has stopped.
 * {@code DONE} is only returned when the current position is the end of the
 * text and the user calls {@code next()}, or when the current position is the
 * beginning of the text and the user calls {@code previous()}.</li>
 * <li>Break positions are numbered by the positions of the characters that
 * follow them. Thus, under normal circumstances, the position before the first
 * character is 0, the position after the first character is 1, and the position
 * after the last character is 1 plus the length of the string.</li>
 * <li>The client can change the position of an iterator, or the text it
 * analyzes, at will, but cannot change the behavior. If the user wants
 * different behavior, he must instantiate a new iterator.</li>
 * </ul>
 * <p>
 * {@code BreakIterator} accesses the text it analyzes through a
 * {@link CharacterIterator}, which makes it possible to use {@code
 * BreakIterator} to analyze text in any text-storage vehicle that provides a
 * {@code CharacterIterator} interface.
 * <p>
 * <em>Note:</em> Some types of {@code BreakIterator} can take a long time to
 * create, and instances of {@code BreakIterator} are not currently cached by
 * the system. For optimal performance, keep instances of {@code BreakIterator}
 * around as long as it makes sense. For example, when word-wrapping a document,
 * don't create and destroy a new {@code BreakIterator} for each line. Create
 * one break iterator for the whole document (or whatever stretch of text you're
 * wrapping) and use it to do the whole job of wrapping the text.
 * <p>
 * <em>Examples</em>:
 * <p>
 * Creating and using text boundaries:
 * <blockquote>
 *
 * <pre>
 * public static void main(String args[]) {
 *     if (args.length == 1) {
 *         String stringToExamine = args[0];
 *         //print each word in order
 *         BreakIterator boundary = BreakIterator.getWordInstance();
 *         boundary.setText(stringToExamine);
 *         printEachForward(boundary, stringToExamine);
 *         //print each sentence in reverse order
 *         boundary = BreakIterator.getSentenceInstance(Locale.US);
 *         boundary.setText(stringToExamine);
 *         printEachBackward(boundary, stringToExamine);
 *         printFirst(boundary, stringToExamine);
 *         printLast(boundary, stringToExamine);
 *     }
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * Print each element in order:
 * <blockquote>
 *
 * <pre>
 * public static void printEachForward(BreakIterator boundary, String source) {
 *     int start = boundary.first();
 *     for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
 *         System.out.println(source.substring(start, end));
 *     }
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * Print each element in reverse order:
 * <blockquote>
 *
 * <pre>
 * public static void printEachBackward(BreakIterator boundary, String source) {
 *     int end = boundary.last();
 *     for (int start = boundary.previous(); start != BreakIterator.DONE; end = start, start = boundary
 *             .previous()) {
 *         System.out.println(source.substring(start, end));
 *     }
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * Print the first element:
 * <blockquote>
 *
 * <pre>
 * public static void printFirst(BreakIterator boundary, String source) {
 *     int start = boundary.first();
 *     int end = boundary.next();
 *     System.out.println(source.substring(start, end));
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * Print the last element:
 * <blockquote>
 *
 * <pre>
 * public static void printLast(BreakIterator boundary, String source) {
 *     int end = boundary.last();
 *     int start = boundary.previous();
 *     System.out.println(source.substring(start, end));
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * Print the element at a specified position:
 * <blockquote>
 *
 * <pre>
 * public static void printAt(BreakIterator boundary, int pos, String source) {
 *     int end = boundary.following(pos);
 *     int start = boundary.previous();
 *     System.out.println(source.substring(start, end));
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * Find the next word:
 * <blockquote>
 *
 * <pre>
 * public static int nextWordStartAfter(int pos, String text) {
 *     BreakIterator wb = BreakIterator.getWordInstance();
 *     wb.setText(text);
 *     int last = wb.following(pos);
 *     int current = wb.next();
 *     while (current != BreakIterator.DONE) {
 *         for (int p = last; p &lt; current; p++) {
 *             if (Character.isLetter(text.charAt(p)))
 *                 return last;
 *         }
 *         last = current;
 *         current = wb.next();
 *     }
 *     return BreakIterator.DONE;
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * The iterator returned by {@code BreakIterator.getWordInstance()} is unique in
 * that the break positions it returns don't represent both the start and end of
 * the thing being iterated over. That is, a sentence-break iterator returns
 * breaks that each represent the end of one sentence and the beginning of the
 * next. With the word-break iterator, the characters between two boundaries
 * might be a word, or they might be the punctuation or whitespace between two
 * words. The above code uses a simple heuristic to determine which boundary is
 * the beginning of a word: If the characters between this boundary and the next
 * boundary include at least one letter (this can be an alphabetical letter, a
 * CJK ideograph, a Hangul syllable, a Kana character, etc.), then the text
 * between this boundary and the next is a word; otherwise, it's the material
 * between words.)
 *
 * @see CharacterIterator
 */
public abstract class BreakIterator implements Cloneable {

    /**
     * This constant is returned by iterate methods like {@code previous()} or
     * {@code next()} if they have returned all valid boundaries.
     */
    public static final int DONE = -1;

    // the wrapped ICU implementation
    NativeBreakIterator wrapped;

    /**
     * Default constructor, for use by subclasses.
     */
    protected BreakIterator() {
    }

    /*
     * wrapping constructor
     */
    BreakIterator(NativeBreakIterator iterator) {
        wrapped = iterator;
    }

    /**
     * Returns an array of locales for which custom {@code BreakIterator} instances
     * are available.
     * <p>Note that Android does not support user-supplied locale service providers.
     */
    public static Locale[] getAvailableLocales() {
        return ICU.getAvailableBreakIteratorLocales();
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * characters using the user's default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     * @return a new instance of {@code BreakIterator} using the default locale.
     */
    public static BreakIterator getCharacterInstance() {
        return getCharacterInstance(Locale.getDefault());
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * characters using the given locale.
     *
     * @param where
     *            the given locale.
     * @return a new instance of {@code BreakIterator} using the given locale.
     */
    public static BreakIterator getCharacterInstance(Locale where) {
        return new RuleBasedBreakIterator(NativeBreakIterator.getCharacterInstance(where));
    }

    /**
     * Returns a new instance of {{@code BreakIterator} to iterate over
     * line breaks using the user's default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     * @return a new instance of {@code BreakIterator} using the default locale.
     */
    public static BreakIterator getLineInstance() {
        return getLineInstance(Locale.getDefault());
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * line breaks using the given locale.
     *
     * @param where
     *            the given locale.
     * @return a new instance of {@code BreakIterator} using the given locale.
     * @throws NullPointerException if {@code where} is {@code null}.
     */
    public static BreakIterator getLineInstance(Locale where) {
        return new RuleBasedBreakIterator(NativeBreakIterator.getLineInstance(where));
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * sentence-breaks using the default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     * @return a new instance of {@code BreakIterator} using the default locale.
     */
    public static BreakIterator getSentenceInstance() {
        return getSentenceInstance(Locale.getDefault());
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * sentence-breaks using the given locale.
     *
     * @param where
     *            the given locale.
     * @return a new instance of {@code BreakIterator} using the given locale.
     * @throws NullPointerException if {@code where} is {@code null}.
     */
    public static BreakIterator getSentenceInstance(Locale where) {
        return new RuleBasedBreakIterator(NativeBreakIterator.getSentenceInstance(where));
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * word-breaks using the default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     * @return a new instance of {@code BreakIterator} using the default locale.
     */
    public static BreakIterator getWordInstance() {
        return getWordInstance(Locale.getDefault());
    }

    /**
     * Returns a new instance of {@code BreakIterator} to iterate over
     * word-breaks using the given locale.
     *
     * @param where
     *            the given locale.
     * @return a new instance of {@code BreakIterator} using the given locale.
     * @throws NullPointerException if {@code where} is {@code null}.
     */
    public static BreakIterator getWordInstance(Locale where) {
        return new RuleBasedBreakIterator(NativeBreakIterator.getWordInstance(where));
    }

    /**
     * Indicates whether the given offset is a boundary position. If this method
     * returns true, the current iteration position is set to the given
     * position; if the function returns false, the current iteration position
     * is set as though {@link #following(int)} had been called.
     *
     * @param offset
     *            the given offset to check.
     * @return {@code true} if the given offset is a boundary position; {@code
     *         false} otherwise.
     */
    public boolean isBoundary(int offset) {
        return wrapped.isBoundary(offset);
    }

    /**
     * Returns the position of last boundary preceding the given offset, and
     * sets the current position to the returned value, or {@code DONE} if the
     * given offset specifies the starting position.
     *
     * @param offset
     *            the given start position to be searched for.
     * @return the position of the last boundary preceding the given offset.
     * @throws IllegalArgumentException
     *            if the offset is invalid.
     */
    public int preceding(int offset) {
        return wrapped.preceding(offset);
    }

    /**
     * Sets the new text string to be analyzed, the current position will be
     * reset to the beginning of this new string, and the old string will be
     * lost.
     *
     * @param newText
     *            the new text string to be analyzed.
     */
    public void setText(String newText) {
        wrapped.setText(newText);
    }

    /**
     * Returns this iterator's current position.
     *
     * @return this iterator's current position.
     */
    public abstract int current();

    /**
     * Sets this iterator's current position to the first boundary and returns
     * that position.
     *
     * @return the position of the first boundary.
     */
    public abstract int first();

    /**
     * Sets the position of the first boundary to the one following the given
     * offset and returns this position. Returns {@code DONE} if there is no
     * boundary after the given offset.
     *
     * @param offset
     *            the given position to be searched for.
     * @return the position of the first boundary following the given offset.
     * @throws IllegalArgumentException
     *            if the offset is invalid.
     */
    public abstract int following(int offset);

    /**
     * Returns a {@code CharacterIterator} which represents the text being
     * analyzed. Please note that the returned value is probably the internal
     * iterator used by this object. If the invoker wants to modify the status
     * of the returned iterator, it is recommended to first create a clone of
     * the iterator returned.
     *
     * @return a {@code CharacterIterator} which represents the text being
     *         analyzed.
     */
    public abstract CharacterIterator getText();

    /**
     * Sets this iterator's current position to the last boundary and returns
     * that position.
     *
     * @return the position of last boundary.
     */
    public abstract int last();

    /**
     * Sets this iterator's current position to the next boundary after the
     * current position, and returns this position. Returns {@code DONE} if no
     * boundary was found after the current position.
     *
     * @return the position of last boundary.
     */
    public abstract int next();

    /**
     * Sets this iterator's current position to the next boundary after the
     * given position, and returns that position. Returns {@code DONE} if no
     * boundary was found after the given position.
     *
     * @param n
     *            the given position.
     * @return the position of last boundary.
     */
    public abstract int next(int n);

    /**
     * Sets this iterator's current position to the previous boundary before the
     * current position and returns that position. Returns {@code DONE} if
     * no boundary was found before the current position.
     *
     * @return the position of last boundary.
     */
    public abstract int previous();

    /**
     * Sets the new text to be analyzed by the given {@code CharacterIterator}.
     * The position will be reset to the beginning of the new text, and other
     * status information of this iterator will be kept.
     *
     * @param newText
     *            the {@code CharacterIterator} referring to the text to be
     *            analyzed.
     */
    public abstract void setText(CharacterIterator newText);

    /**
     * Returns a copy of this iterator.
     */
    @Override
    public Object clone() {
        try {
            BreakIterator cloned = (BreakIterator) super.clone();
            cloned.wrapped = (NativeBreakIterator) wrapped.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
