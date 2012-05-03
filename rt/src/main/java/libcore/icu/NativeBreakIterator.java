/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.icu;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Locale;

public final class NativeBreakIterator implements Cloneable {
    // Acceptable values for the 'type' field.
    private static final int BI_CHAR_INSTANCE = 1;
    private static final int BI_WORD_INSTANCE = 2;
    private static final int BI_LINE_INSTANCE = 3;
    private static final int BI_SENT_INSTANCE = 4;

    private final int address;
    private final int type;
    private CharacterIterator charIter;

    private NativeBreakIterator(int address, int type) {
        this.address = address;
        this.type = type;
        this.charIter = new StringCharacterIterator("");
    }

    @Override
    public Object clone() {
        int cloneAddr = cloneImpl(this.address);
        NativeBreakIterator clone = new NativeBreakIterator(cloneAddr, this.type);
        // The RI doesn't clone the CharacterIterator.
        clone.charIter = this.charIter;
        return clone;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof NativeBreakIterator)) {
            return false;
        }
        // TODO: is this sufficient? shouldn't we be checking the underlying rules?
        NativeBreakIterator rhs = (NativeBreakIterator) object;
        return type == rhs.type && charIter.equals(rhs.charIter);
    }

    @Override
    public int hashCode() {
        return 42; // No-one uses BreakIterator as a hash key.
    }

    @Override protected void finalize() throws Throwable {
        try {
            closeBreakIteratorImpl(this.address);
        } finally {
            super.finalize();
        }
    }

    public int current() {
        return currentImpl(this.address);
    }

    public int first() {
        return firstImpl(this.address);
    }

    public int following(int offset) {
        return followingImpl(this.address, offset);
    }

    public CharacterIterator getText() {
        int newLoc = currentImpl(this.address);
        this.charIter.setIndex(newLoc);
        return this.charIter;
    }

    public int last() {
        return lastImpl(this.address);
    }

    public int next(int n) {
        return nextImpl(this.address, n);
    }

    public int next() {
        return nextImpl(this.address, 1);
    }

    public int previous() {
        return previousImpl(this.address);
    }

    public void setText(CharacterIterator newText) {
        StringBuilder sb = new StringBuilder();
        for (char c = newText.first(); c != CharacterIterator.DONE; c = newText.next()) {
            sb.append(c);
        }
        setText(sb.toString(), newText);
    }

    public void setText(String newText) {
        setText(newText, new StringCharacterIterator(newText));
    }

    private void setText(String s, CharacterIterator it) {
        this.charIter = it;
        setTextImpl(this.address, s);
    }

    public boolean isBoundary(int offset) {
        return isBoundaryImpl(this.address, offset);
    }

    public int preceding(int offset) {
        return precedingImpl(this.address, offset);
    }

    public static NativeBreakIterator getCharacterInstance(Locale where) {
        return new NativeBreakIterator(getCharacterInstanceImpl(where.toString()), BI_CHAR_INSTANCE);
    }

    public static NativeBreakIterator getLineInstance(Locale where) {
        return new NativeBreakIterator(getLineInstanceImpl(where.toString()), BI_LINE_INSTANCE);
    }

    public static NativeBreakIterator getSentenceInstance(Locale where) {
        return new NativeBreakIterator(getSentenceInstanceImpl(where.toString()), BI_SENT_INSTANCE);
    }

    public static NativeBreakIterator getWordInstance(Locale where) {
        return new NativeBreakIterator(getWordInstanceImpl(where.toString()), BI_WORD_INSTANCE);
    }

    private static native int getCharacterInstanceImpl(String locale);
    private static native int getWordInstanceImpl(String locale);
    private static native int getLineInstanceImpl(String locale);
    private static native int getSentenceInstanceImpl(String locale);
    private static native void closeBreakIteratorImpl(int address);
    private static native void setTextImpl(int address, String text);
    private static native int cloneImpl(int address);
    private static native int precedingImpl(int address, int offset);
    private static native boolean isBoundaryImpl(int address, int offset);
    private static native int nextImpl(int address, int n);
    private static native int previousImpl(int address);
    private static native int currentImpl(int address);
    private static native int firstImpl(int address);
    private static native int followingImpl(int address, int offset);
    private static native int lastImpl(int address);
}
