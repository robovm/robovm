/**
*******************************************************************************
* Copyright (C) 1996-2005, International Business Machines Corporation and    *
* others. All Rights Reserved.                                                *
*******************************************************************************
*
*
*******************************************************************************
*/

package libcore.icu;

import java.text.CharacterIterator;
import java.text.CollationKey;
import java.text.ParseException;
import java.util.Locale;

public final class RuleBasedCollatorICU implements Cloneable {
    // Values from the native UColAttributeValue enum.
    public static final int VALUE_DEFAULT = -1;
    public static final int VALUE_PRIMARY = 0;
    public static final int VALUE_SECONDARY = 1;
    public static final int VALUE_TERTIARY = 2;
    public static final int VALUE_DEFAULT_STRENGTH = VALUE_TERTIARY;
    public static final int VALUE_QUATERNARY = 3;
    public static final int VALUE_IDENTICAL = 15;
    public static final int VALUE_OFF = 16;
    public static final int VALUE_ON = 17;
    public static final int VALUE_SHIFTED = 20;
    public static final int VALUE_NON_IGNORABLE = 21;
    public static final int VALUE_LOWER_FIRST = 24;
    public static final int VALUE_UPPER_FIRST = 25;
    public static final int VALUE_ON_WITHOUT_HANGUL = 28;
    public static final int VALUE_ATTRIBUTE_VALUE_COUNT = 29;

    // Values from the UColAttribute enum.
    public static final int FRENCH_COLLATION = 0;
    public static final int ALTERNATE_HANDLING = 1;
    public static final int CASE_FIRST = 2;
    public static final int CASE_LEVEL = 3;
    public static final int DECOMPOSITION_MODE = 4;
    public static final int STRENGTH = 5;

    // The address of the ICU4C native peer.
    private int address;

    public RuleBasedCollatorICU(String rules) throws ParseException {
        if (rules == null) {
            throw new NullPointerException();
        }
        address = NativeCollation.openCollatorFromRules(rules, VALUE_OFF, VALUE_DEFAULT_STRENGTH);
    }

    public RuleBasedCollatorICU(Locale locale) {
        address = NativeCollation.openCollator(locale.toString());
    }

    private RuleBasedCollatorICU(int address) {
        this.address = address;
    }

    public Object clone() {
        return new RuleBasedCollatorICU(NativeCollation.safeClone(address));
    }

    public int compare(String source, String target) {
        return NativeCollation.compare(address, source, target);
    }

    public int getDecomposition() {
        return NativeCollation.getAttribute(address, DECOMPOSITION_MODE);
    }

    public void setDecomposition(int mode) {
        NativeCollation.setAttribute(address, DECOMPOSITION_MODE, mode);
    }

    public int getStrength() {
        return NativeCollation.getAttribute(address, STRENGTH);
    }

    public void setStrength(int strength) {
        NativeCollation.setAttribute(address, STRENGTH, strength);
    }

    public void setAttribute(int type, int value) {
        NativeCollation.setAttribute(address, type, value);
    }

    public int getAttribute(int type) {
        return NativeCollation.getAttribute(address, type);
    }

    public CollationKey getCollationKey(String source) {
        if (source == null) {
            return null;
        }
        byte[] key = NativeCollation.getSortKey(address, source);
        if (key == null) {
            return null;
        }
        return new CollationKeyICU(source, key);
    }

    public String getRules() {
        return NativeCollation.getRules(address);
    }

    public CollationElementIteratorICU getCollationElementIterator(String source) {
        return CollationElementIteratorICU.getInstance(address, source);
    }

    public CollationElementIteratorICU getCollationElementIterator(CharacterIterator it) {
        // We only implement the String-based API, so build a string from the iterator.
        return getCollationElementIterator(characterIteratorToString(it));
    }

    private String characterIteratorToString(CharacterIterator it) {
        StringBuilder result = new StringBuilder();
        for (char ch = it.current(); ch != CharacterIterator.DONE; ch = it.next()) {
            result.append(ch);
        }
        return result.toString();
    }

    @Override public int hashCode() {
        return 42; // No-one uses RuleBasedCollatorICU as a hash key.
    }

    public boolean equals(String source, String target) {
        return (compare(source, target) == 0);
    }

    @Override public boolean equals(Object object) {
        if (object ==  this) {
            return true;
        }
        if (!(object instanceof RuleBasedCollatorICU)) {
            return false;
        }
        RuleBasedCollatorICU rhs = (RuleBasedCollatorICU) object;
        return getRules().equals(rhs.getRules()) &&
                getStrength() == rhs.getStrength() &&
                getDecomposition() == rhs.getDecomposition();
    }

    @Override protected void finalize() throws Throwable {
        try {
            NativeCollation.closeCollator(address);
        } finally {
            super.finalize();
        }
    }
}
