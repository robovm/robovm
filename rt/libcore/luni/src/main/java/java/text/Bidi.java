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

import java.awt.font.NumericShaper;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implements the <a href="http://unicode.org/reports/tr9/">Unicode Bidirectional Algorithm</a>.
 *
 * <p>Use a {@code Bidi} object to get the information on the position reordering of a
 * bidirectional text, such as Arabic or Hebrew. The natural display ordering of
 * horizontal text in these languages is from right to left, while they order
 * numbers from left to right.
 *
 * <p>If the text contains multiple runs, the information of each run can be
 * obtained from the run index. The level of any particular run indicates the
 * direction of the text as well as the nesting level. Left-to-right runs have
 * even levels while right-to-left runs have odd levels.
 */
public final class Bidi {
    /**
     * Constant that indicates the default base level. If there is no strong
     * character, then set the paragraph level to 0 (left-to-right).
     */
    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT = -2;

    /**
     * Constant that indicates the default base level. If there is no strong
     * character, then set the paragraph level to 1 (right-to-left).
     */
    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT = -1;

    /**
     * Constant that specifies the default base level as 0 (left-to-right).
     */
    public static final int DIRECTION_LEFT_TO_RIGHT = 0;

    /**
     * Constant that specifies the default base level as 1 (right-to-left).
     */
    public static final int DIRECTION_RIGHT_TO_LEFT = 1;

    /**
     * TODO: if we care about performance, we might just want to use an int[] instead of a Run[].
     */
    static class Run {
        private final int start;
        private final int limit;
        private final int level;

        public Run(int start, int limit, int level) {
            this.start = start;
            this.limit = limit;
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public int getLimit() {
            return limit;
        }

        public int getStart() {
            return start;
        }
    }

    /**
     * Creates a {@code Bidi} object from the {@code
     * AttributedCharacterIterator} of a paragraph text. The RUN_DIRECTION
     * attribute determines the base direction of the bidirectional text. If it
     * is not specified explicitly, the algorithm uses
     * DIRECTION_DEFAULT_LEFT_TO_RIGHT by default. The BIDI_EMBEDDING attribute
     * specifies the level of embedding for each character. Values between -1
     * and -62 denote overrides at the level's absolute value, values from 1 to
     * 62 indicate embeddings, and the 0 value indicates the level is calculated
     * by the algorithm automatically. For the character with no BIDI_EMBEDDING
     * attribute or with a improper attribute value, such as a {@code null}
     * value, the algorithm treats its embedding level as 0. The NUMERIC_SHAPING
     * attribute specifies the instance of NumericShaper used to convert
     * European digits to other decimal digits before performing the bidi
     * algorithm.
     *
     * @param paragraph
     *            the String containing the paragraph text to perform the
     *            algorithm.
     * @throws IllegalArgumentException if {@code paragraph == null}
     * @see java.awt.font.TextAttribute#BIDI_EMBEDDING
     * @see java.awt.font.TextAttribute#NUMERIC_SHAPING
     * @see java.awt.font.TextAttribute#RUN_DIRECTION
     */
    public Bidi(AttributedCharacterIterator paragraph) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph is null");
        }

        int begin = paragraph.getBeginIndex();
        int end = paragraph.getEndIndex();
        int length = end - begin;
        char[] text = new char[length + 1]; // One more char for AttributedCharacterIterator.DONE

        if (length != 0) {
            text[0] = paragraph.first();
        } else {
            paragraph.first();
        }

        // First check the RUN_DIRECTION attribute.
        int flags = DIRECTION_DEFAULT_LEFT_TO_RIGHT;
        Object direction = paragraph.getAttribute(TextAttribute.RUN_DIRECTION);
        if (direction != null && direction instanceof Boolean) {
            if (direction.equals(TextAttribute.RUN_DIRECTION_LTR)) {
                flags = DIRECTION_LEFT_TO_RIGHT;
            } else {
                flags = DIRECTION_RIGHT_TO_LEFT;
            }
        }

        // Retrieve the text and gather BIDI_EMBEDDINGS
        byte[] embeddings = null;
        for (int textLimit = 1, i = 1; i < length; textLimit = paragraph
                .getRunLimit(TextAttribute.BIDI_EMBEDDING)
                - begin + 1) {
            Object embedding = paragraph.getAttribute(TextAttribute.BIDI_EMBEDDING);
            if (embedding != null && embedding instanceof Integer) {
                int embLevel = ((Integer) embedding).intValue();

                if (embeddings == null) {
                    embeddings = new byte[length];
                }

                for (; i < textLimit; i++) {
                    text[i] = paragraph.next();
                    embeddings[i - 1] = (byte) embLevel;
                }
            } else {
                for (; i < textLimit; i++) {
                    text[i] = paragraph.next();
                }
            }
        }

        // Apply NumericShaper to the text
        Object numericShaper = paragraph.getAttribute(TextAttribute.NUMERIC_SHAPING);
        if (numericShaper != null && numericShaper instanceof NumericShaper) {
            ((NumericShaper) numericShaper).shape(text, 0, length);
        }

        long bidi = 0;
        try {
            bidi = createUBiDi(text, 0, embeddings, 0, length, flags);
            readBidiInfo(bidi);
        } finally {
            ubidi_close(bidi);
        }
    }

    /**
     * Creates a {@code Bidi} object.
     *
     * @param text
     *            the char array of the paragraph text that is processed.
     * @param textStart
     *            the index in {@code text} of the start of the paragraph.
     * @param embeddings
     *            the embedding level array of the paragraph text, specifying
     *            the embedding level information for each character. Values
     *            between -1 and -61 denote overrides at the level's absolute
     *            value, values from 1 to 61 indicate embeddings, and the 0
     *            value indicates the level is calculated by the algorithm
     *            automatically.
     * @param embStart
     *            the index in {@code embeddings} of the start of the paragraph.
     * @param paragraphLength
     *            the length of the text to perform the algorithm.
     * @param flags
     *            indicates the base direction of the bidirectional text. It is
     *            expected that this will be one of the direction constant
     *            values defined in this class. An unknown value is treated as
     *            DIRECTION_DEFAULT_LEFT_TO_RIGHT.
     * @throws IllegalArgumentException
     *             if {@code textStart}, {@code embStart}, or {@code
     *             paragraphLength} is negative; if
     *             {@code text.length < textStart + paragraphLength} or
     *             {@code embeddings.length < embStart + paragraphLength}.
     * @see #DIRECTION_LEFT_TO_RIGHT
     * @see #DIRECTION_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_LEFT_TO_RIGHT
     */
    public Bidi(char[] text, int textStart, byte[] embeddings, int embStart,
            int paragraphLength, int flags) {

        if (text == null || text.length - textStart < paragraphLength) {
            throw new IllegalArgumentException();
        }

        if (embeddings != null) {
            if (embeddings.length - embStart < paragraphLength) {
                throw new IllegalArgumentException();
            }
        }

        if (textStart < 0) {
            throw new IllegalArgumentException("Negative textStart value " + textStart);
        }
        if (embStart < 0) {
            throw new IllegalArgumentException("Negative embStart value " + embStart);
        }
        if (paragraphLength < 0) {
            throw new IllegalArgumentException("Negative paragraph length " + paragraphLength);
        }

        long bidi = 0;
        try {
            bidi = createUBiDi(text, textStart, embeddings, embStart, paragraphLength, flags);
            readBidiInfo(bidi);
        } finally {
            ubidi_close(bidi);
        }
    }

    /**
     * Creates a {@code Bidi} object.
     *
     * @param paragraph
     *            the string containing the paragraph text to perform the
     *            algorithm on.
     * @param flags
     *            indicates the base direction of the bidirectional text. It is
     *            expected that this will be one of the direction constant
     *            values defined in this class. An unknown value is treated as
     *            DIRECTION_DEFAULT_LEFT_TO_RIGHT.
     * @see #DIRECTION_LEFT_TO_RIGHT
     * @see #DIRECTION_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_LEFT_TO_RIGHT
     */
    public Bidi(String paragraph, int flags) {
        this((paragraph == null ? null : paragraph.toCharArray()), 0, null, 0,
                (paragraph == null ? 0 : paragraph.length()), flags);
    }

    // create the native UBiDi struct, need to be closed with ubidi_close().
    private static long createUBiDi(char[] text, int textStart,
            byte[] embeddings, int embStart, int paragraphLength, int flags) {
        char[] realText = null;

        byte[] realEmbeddings = null;

        if (text == null || text.length - textStart < paragraphLength) {
            throw new IllegalArgumentException();
        }
        realText = new char[paragraphLength];
        System.arraycopy(text, textStart, realText, 0, paragraphLength);

        if (embeddings != null) {
            if (embeddings.length - embStart < paragraphLength) {
                throw new IllegalArgumentException();
            }
            if (paragraphLength > 0) {
                Bidi temp = new Bidi(text, textStart, null, 0, paragraphLength, flags);
                realEmbeddings = new byte[paragraphLength];
                System.arraycopy(temp.offsetLevel, 0, realEmbeddings, 0, paragraphLength);
                for (int i = 0; i < paragraphLength; i++) {
                    byte e = embeddings[i];
                    if (e < 0) {
                        realEmbeddings[i] = (byte) (UBIDI_LEVEL_OVERRIDE - e);
                    } else if (e > 0) {
                        realEmbeddings[i] = e;
                    } else {
                        realEmbeddings[i] |= (byte) UBIDI_LEVEL_OVERRIDE;
                    }
                }
            }
        }

        if (flags > 1 || flags < -2) {
            flags = 0;
        }

        long bidi = 0;
        boolean needsDeletion = true;
        try {
            bidi = ubidi_open();
            ubidi_setPara(bidi, realText, paragraphLength, flags, realEmbeddings);
            needsDeletion = false;
        } finally {
            if (needsDeletion) {
                ubidi_close(bidi);
            }
        }
        return bidi;
    }

    /* private constructor used by createLineBidi() */
    private Bidi(long pBidi) {
        readBidiInfo(pBidi);
    }

    // read info from the native UBiDi struct
    private void readBidiInfo(long pBidi) {
        length = ubidi_getLength(pBidi);

        offsetLevel = (length == 0) ? null : ubidi_getLevels(pBidi);

        baseLevel = ubidi_getParaLevel(pBidi);

        int runCount = ubidi_countRuns(pBidi);
        if (runCount == 0) {
            unidirectional = true;
            runs = null;
        } else if (runCount < 0) {
            runs = null;
        } else {
            runs = ubidi_getRuns(pBidi);

            // Simplified case for one run which has the base level
            if (runCount == 1 && runs[0].getLevel() == baseLevel) {
                unidirectional = true;
                runs = null;
            }
        }

        direction = ubidi_getDirection(pBidi);
    }

    private int baseLevel;

    private int length;

    private byte[] offsetLevel;

    private Run[] runs;

    private int direction;

    private boolean unidirectional;

    /**
     * Returns whether the base level is from left to right.
     *
     * @return true if the base level is from left to right.
     */
    public boolean baseIsLeftToRight() {
        return baseLevel % 2 == 0 ? true : false;
    }

    /**
     * Creates a new {@code Bidi} object containing the information of one line
     * from this object.
     *
     * @param lineStart
     *            the start offset of the line.
     * @param lineLimit
     *            the limit of the line.
     * @return the new line Bidi object. In this new object, the indices will
     *         range from 0 to (limit - start - 1).
     * @throws IllegalArgumentException
     *             if {@code lineStart < 0}, {@code lineLimit < 0}, {@code
     *             lineStart > lineLimit} or if {@code lineStart} is greater
     *             than the length of this object's paragraph text.
     */
    public Bidi createLineBidi(int lineStart, int lineLimit) {
        if (lineStart < 0 || lineLimit < 0 || lineLimit > length || lineStart > lineLimit) {
            throw new IllegalArgumentException("Invalid ranges (start=" + lineStart + ", " +
                    "limit=" + lineLimit + ", length=" + length + ")");
        }

        char[] text = new char[this.length];
        Arrays.fill(text, 'a');
        byte[] embeddings = new byte[this.length];
        for (int i = 0; i < embeddings.length; i++) {
            embeddings[i] = (byte) -this.offsetLevel[i];
        }

        int dir = this.baseIsLeftToRight()
                ? Bidi.DIRECTION_LEFT_TO_RIGHT
                : Bidi.DIRECTION_RIGHT_TO_LEFT;
        long parent = 0;
        try {
            parent = createUBiDi(text, 0, embeddings, 0, this.length, dir);
            if (lineStart == lineLimit) {
                return createEmptyLineBidi(parent);
            }
            return new Bidi(ubidi_setLine(parent, lineStart, lineLimit));
        } finally {
            ubidi_close(parent);
        }
    }

    private Bidi createEmptyLineBidi(long parent) {
        // ICU4C doesn't allow this case, but the RI does.
        Bidi result = new Bidi(parent);
        result.length = 0;
        result.offsetLevel = null;
        result.runs = null;
        result.unidirectional = true;
        return result;
    }

    /**
     * Returns the base level.
     *
     * @return the base level.
     */
    public int getBaseLevel() {
        return baseLevel;
    }

    /**
     * Returns the length of the text in the {@code Bidi} object.
     *
     * @return the length.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the level of a specified character.
     *
     * @param offset
     *            the offset of the character.
     * @return the level.
     */
    public int getLevelAt(int offset) {
        try {
            return offsetLevel[offset] & ~UBIDI_LEVEL_OVERRIDE;
        } catch (RuntimeException e) {
            return baseLevel;
        }
    }

    /**
     * Returns the number of runs in the bidirectional text.
     *
     * @return the number of runs, at least 1.
     */
    public int getRunCount() {
        return unidirectional ? 1 : runs.length;
    }

    /**
     * Returns the level of the specified run.
     *
     * @param run
     *            the index of the run.
     * @return the level of the run.
     */
    public int getRunLevel(int run) {
        return unidirectional ? baseLevel : runs[run].getLevel();
    }

    /**
     * Returns the limit offset of the specified run.
     *
     * @param run
     *            the index of the run.
     * @return the limit offset of the run.
     */
    public int getRunLimit(int run) {
        return unidirectional ? length : runs[run].getLimit();
    }

    /**
     * Returns the start offset of the specified run.
     *
     * @param run
     *            the index of the run.
     * @return the start offset of the run.
     */
    public int getRunStart(int run) {
        return unidirectional ? 0 : runs[run].getStart();
    }

    /**
     * Indicates whether the text is from left to right, that is, both the base
     * direction and the text direction is from left to right.
     *
     * @return {@code true} if the text is from left to right; {@code false}
     *         otherwise.
     */
    public boolean isLeftToRight() {
        return direction == UBiDiDirection_UBIDI_LTR;
    }

    /**
     * Indicates whether the text direction is mixed.
     *
     * @return {@code true} if the text direction is mixed; {@code false}
     *         otherwise.
     */
    public boolean isMixed() {
        return direction == UBiDiDirection_UBIDI_MIXED;
    }

    /**
     * Indicates whether the text is from right to left, that is, both the base
     * direction and the text direction is from right to left.
     *
     * @return {@code true} if the text is from right to left; {@code false}
     *         otherwise.
     */
    public boolean isRightToLeft() {
        return direction == UBiDiDirection_UBIDI_RTL;
    }

    /**
     * Reorders a range of objects according to their specified levels. This is
     * a convenience function that does not use a {@code Bidi} object. The range
     * of objects at {@code index} from {@code objectStart} to {@code
     * objectStart + count} will be reordered according to the range of levels
     * at {@code index} from {@code levelStart} to {@code levelStart + count}.
     *
     * @param levels
     *            the level array, which is already determined.
     * @param levelStart
     *            the start offset of the range of the levels.
     * @param objects
     *            the object array to reorder.
     * @param objectStart
     *            the start offset of the range of objects.
     * @param count
     *            the count of the range of objects to reorder.
     * @throws IllegalArgumentException
     *             if {@code count}, {@code levelStart} or {@code objectStart}
     *             is negative; if {@code count > levels.length - levelStart} or
     *             if {@code count > objects.length - objectStart}.
     */
    public static void reorderVisually(byte[] levels, int levelStart,
            Object[] objects, int objectStart, int count) {
        if (count < 0 || levelStart < 0 || objectStart < 0
                || count > levels.length - levelStart
                || count > objects.length - objectStart) {
            throw new IllegalArgumentException("Invalid ranges (levels=" + levels.length +
                    ", levelStart=" + levelStart + ", objects=" + objects.length +
                    ", objectStart=" + objectStart + ", count=" + count + ")");
        }

        byte[] realLevels = new byte[count];
        System.arraycopy(levels, levelStart, realLevels, 0, count);

        int[] indices = ubidi_reorderVisual(realLevels, count);

        ArrayList<Object> result = new ArrayList<Object>(count);
        for (int i = 0; i < count; i++) {
            result.add(objects[objectStart + indices[i]]);
        }

        System.arraycopy(result.toArray(), 0, objects, objectStart, count);
    }

    /**
     * Indicates whether a range of characters of a text requires a {@code Bidi}
     * object to display properly.
     *
     * @param text
     *            the char array of the text.
     * @param start
     *            the start offset of the range of characters.
     * @param limit
     *            the limit offset of the range of characters.
     * @return {@code true} if the range of characters requires a {@code Bidi}
     *         object; {@code false} otherwise.
     * @throws IllegalArgumentException
     *             if {@code start} or {@code limit} is negative; {@code start >
     *             limit} or {@code limit} is greater than the length of this
     *             object's paragraph text.
     */
    public static boolean requiresBidi(char[] text, int start, int limit) {
        if (limit < 0 || start < 0 || start > limit || limit > text.length) {
            throw new IllegalArgumentException();
        }

        Bidi bidi = new Bidi(text, start, null, 0, limit - start, 0);
        return !bidi.isLeftToRight();
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[direction: " + direction + " baseLevel: " + baseLevel
                + " length: " + length + " runs: " + Arrays.toString(runs) + "]";
    }

    // ICU4C constants.
    private static final int UBIDI_LEVEL_OVERRIDE = 0x80;
    private static final int UBiDiDirection_UBIDI_LTR = 0;
    private static final int UBiDiDirection_UBIDI_RTL = 1;
    private static final int UBiDiDirection_UBIDI_MIXED = 2;

    // ICU4C functions.
    private static native long ubidi_open();
    private static native void ubidi_close(long pBiDi);
    private static native void ubidi_setPara(long pBiDi, char[] text, int length, int paraLevel, byte[] embeddingLevels);
    private static native long ubidi_setLine(final long pParaBiDi, int start, int limit);
    private static native int ubidi_getDirection(final long pBiDi);
    private static native int ubidi_getLength(final long pBiDi);
    private static native byte ubidi_getParaLevel(final long pBiDi);
    private static native byte[] ubidi_getLevels(long pBiDi);
    private static native int ubidi_countRuns(long pBiDi);
    private static native Bidi.Run[] ubidi_getRuns(long pBidi);
    private static native int[] ubidi_reorderVisual(byte[] levels, int length);
}
