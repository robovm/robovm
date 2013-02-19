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

/**
* Package static class for declaring all native methods for collation use.
* @author syn wee quek
* @internal ICU 2.4
*/
public final class NativeCollation {
    private NativeCollation() {
    }

    // Collator.
    public static native void closeCollator(int address);
    public static native int compare(int address, String source, String target);
    public static native int getAttribute(int address, int type);
    public static native int getCollationElementIterator(int address, String source);
    public static native String getRules(int address);
    public static native byte[] getSortKey(int address, String source);
    public static native int openCollator(String locale);
    public static native int openCollatorFromRules(String rules, int normalizationMode, int collationStrength);
    public static native int safeClone(int address);
    public static native void setAttribute(int address, int type, int value);

    // CollationElementIterator.
    public static native void closeElements(int address);
    public static native int getMaxExpansion(int address, int order);
    public static native int getOffset(int address);
    public static native int next(int address);
    public static native int previous(int address);
    public static native void reset(int address);
    public static native void setOffset(int address, int offset);
    public static native void setText(int address, String source);
}
