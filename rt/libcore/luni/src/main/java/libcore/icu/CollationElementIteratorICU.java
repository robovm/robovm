/**
*******************************************************************************
* Copyright (C) 1996-2005, International Business Machines Corporation and    *
* others. All Rights Reserved.                                                *
*******************************************************************************
*
*******************************************************************************
*/

package libcore.icu;

import java.text.CharacterIterator;

/**
* Collation element iterator JNI wrapper.
* Iterates over the collation elements of a data string.
* The iterator supports both forward and backwards full iteration, ie if
* backwards iteration is performed in the midst of a forward iteration, the
* result is undefined.
* To perform a backwards iteration in the midst of a forward iteration,
* reset() has to be called.
* This will shift the position to either the start or the last character in the
* data string depending on whether next() is called or previous().
* <pre>
*   RuleBasedCollator coll = Collator.getInstance();
*   CollationElementIterator iterator = coll.getCollationElementIterator("abc");
*   int ce = 0;
*   while (ce != CollationElementIterator.NULLORDER) {
*     ce = iterator.next();
*   }
*   iterator.reset();
*   while (ce != CollationElementIterator.NULLORDER) {
*     ce = iterator.previous();
*   }
* </pre>
* @author syn wee quek
* @stable ICU 2.4
*/
public final class CollationElementIteratorICU {
    // public data member -------------------------------------------

    /**
     * @stable ICU 2.4
     */
    public static final int NULLORDER = 0xFFFFFFFF;

    // public methods -----------------------------------------------

    /**
     * Reset the collation elements to their initial state.
     * This will move the 'cursor' to the beginning of the text.
     * @stable ICU 2.4
     */
    public void reset() {
        NativeCollation.reset(address);
    }

    /**
     * Get the ordering priority of the next collation element in the text.
     * A single character may contain more than one collation element.
     * @return next collation elements ordering, or NULLORDER if the end of the
     *         text is reached.
     * @stable ICU 2.4
     */
    public int next() {
        return NativeCollation.next(address);
    }

    /**
     * Get the ordering priority of the previous collation element in the text.
     * A single character may contain more than one collation element.
     * @return previous collation element ordering, or NULLORDER if the end of
     *         the text is reached.
     * @stable ICU 2.4
     */
    public int previous() {
        return NativeCollation.previous(address);
    }

    /**
     * Get the maximum length of any expansion sequences that end with the
     * specified comparison order.
     * @param order collation order returned by previous or next.
     * @return maximum size of the expansion sequences ending with the collation
     *              element or 1 if collation element does not occur at the end of
     *              any expansion sequence
     * @stable ICU 2.4
     */
    public int getMaxExpansion(int order) {
        return NativeCollation.getMaxExpansion(address, order);
    }

    /**
     * Set the text containing the collation elements.
     * @param source text containing the collation elements.
     * @stable ICU 2.4
     */
    public void setText(String source) {
        NativeCollation.setText(address, source);
    }

    public void setText(CharacterIterator source) {
        NativeCollation.setText(address, source.toString());
    }

    /**
     * Get the offset of the current source character.
     * This is an offset into the text of the character containing the current
     * collation elements.
     * @return offset of the current source character.
     * @stable ICU 2.4
     */
    public int getOffset() {
        return NativeCollation.getOffset(address);
    }

    /**
     * Set the offset of the current source character.
     * This is an offset into the text of the character to be processed.
     * @param offset The desired character offset.
     * @stable ICU 2.4
     */
    public void setOffset(int offset) {
        NativeCollation.setOffset(address, offset);
    }

    /**
     * Gets the primary order of a collation order.
     * @param order the collation order
     * @return the primary order of a collation order.
     * @stable ICU 2.4
     */
    public static int primaryOrder(int order) {
        return ((order & PRIMARY_ORDER_MASK_) >> PRIMARY_ORDER_SHIFT_) &
                UNSIGNED_16_BIT_MASK_;
    }

    /**
     * Gets the secondary order of a collation order.
     * @param order the collation order
     * @return the secondary order of a collation order.
     * @stable ICU 2.4
     */
    public static int secondaryOrder(int order) {
        return (order & SECONDARY_ORDER_MASK_) >> SECONDARY_ORDER_SHIFT_;
    }

    /**
     * Gets the tertiary order of a collation order.
     * @param order the collation order
     * @return the tertiary order of a collation order.
     * @stable ICU 2.4
     */
    public static int tertiaryOrder(int order) {
        return order & TERTIARY_ORDER_MASK_;
    }

    public static CollationElementIteratorICU getInstance(int collatorAddress, String source) {
        int iteratorAddress = NativeCollation.getCollationElementIterator(collatorAddress, source);
        return new CollationElementIteratorICU(iteratorAddress);
    }

    private CollationElementIteratorICU(int address) {
        this.address = address;
    }

    // protected methods --------------------------------------------

    /**
     * Garbage collection.
     * Close C collator and reclaim memory.
     * @stable ICU 2.4
     */
    @Override protected void finalize() throws Throwable {
        try {
            NativeCollation.closeElements(address);
        } finally {
            super.finalize();
        }
    }

    // private data members -----------------------------------------

    /**
     * C collator
     */
    private int address;

    /**
     * ICU constant primary order mask for collation elements
     */
    private static final int PRIMARY_ORDER_MASK_ = 0xffff0000;
    /**
     * ICU constant secondary order mask for collation elements
     */
    private static final int SECONDARY_ORDER_MASK_ = 0x0000ff00;
    /**
     * ICU constant tertiary order mask for collation elements
     */
    private static final int TERTIARY_ORDER_MASK_ = 0x000000ff;
    /**
     * ICU constant primary order shift for collation elements
     */
    private static final int PRIMARY_ORDER_SHIFT_ = 16;
    /**
     * ICU constant secondary order shift for collation elements
     */
    private static final int SECONDARY_ORDER_SHIFT_ = 8;
    /**
     * Unsigned 16 bit mask
     */
    private static final int UNSIGNED_16_BIT_MASK_ = 0x0000FFFF;
}
