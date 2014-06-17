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
    public static native void closeCollator(long address);
    public static native int compare(long address, String source, String target);
    public static native int getAttribute(long address, int type);
    public static native int getCollationElementIterator(long address, String source);
    public static native String getRules(long address);
    public static native byte[] getSortKey(long address, String source);
    public static native long openCollator(String locale);
    public static native long openCollatorFromRules(String rules, int normalizationMode, int collationStrength);
    public static native long safeClone(long address);
    public static native void setAttribute(long address, int type, int value);

    // CollationElementIterator.
    public static native void closeElements(long address);
    public static native int getMaxExpansion(long address, int order);
    public static native int getOffset(long address);
    public static native int next(long address);
    public static native int previous(long address);
    public static native void reset(long address);
    public static native void setOffset(long address, int offset);
    public static native void setText(long address, String source);
}
