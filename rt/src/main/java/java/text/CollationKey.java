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

/**
 * Represents a string under the rules of a specific {@code Collator} object.
 * Comparing two {@code CollationKey} instances returns the relative order of
 * the strings they represent.
 * <p>
 * Since the rule set of collators can differ, the sort orders of the same
 * string under two different {@code Collator} instances might differ. Hence
 * comparing collation keys generated from different {@code Collator} instances
 * can give incorrect results.
 * <p>
 * Both the method {@code CollationKey.compareTo(CollationKey)} and the method
 * {@code Collator.compare(String, String)} compares two strings and returns
 * their relative order. The performance characteristics of these two approaches
 * can differ.
 * <p>
 * During the construction of a {@code CollationKey}, the entire source string
 * is examined and processed into a series of bits terminated by a null, that
 * are stored in the {@code CollationKey}. When
 * {@code CollationKey.compareTo(CollationKey)} executes, it performs bitwise
 * comparison on the bit sequences. This can incur startup cost when creating
 * the {@code CollationKey}, but once the key is created, binary comparisons
 * are fast. This approach is recommended when the same strings are to be
 * compared over and over again.
 * <p>
 * On the other hand, implementations of
 * {@code Collator.compare(String, String)} can examine and process the strings
 * only until the first characters differ in order. This approach is
 * recommended if the strings are to be compared only once.
 * <p>
 * The following example shows how collation keys can be used to sort a
 * list of strings:
 * <blockquote>
 *
 * <pre>
 * // Create an array of CollationKeys for the Strings to be sorted.
 * Collator myCollator = Collator.getInstance();
 * CollationKey[] keys = new CollationKey[3];
 * keys[0] = myCollator.getCollationKey(&quot;Tom&quot;);
 * keys[1] = myCollator.getCollationKey(&quot;Dick&quot;);
 * keys[2] = myCollator.getCollationKey(&quot;Harry&quot;);
 * sort(keys);
 * <br>
 * //...
 * <br>
 * // Inside body of sort routine, compare keys this way
 * if( keys[i].compareTo( keys[j] ) &gt; 0 )
 *    // swap keys[i] and keys[j]
 * <br>
 * //...
 * <br>
 * // Finally, when we've returned from sort.
 * System.out.println(keys[0].getSourceString());
 * System.out.println(keys[1].getSourceString());
 * System.out.println(keys[2].getSourceString());
 * </pre>
 *
 * </blockquote>
 *
 * @see Collator
 * @see RuleBasedCollator
 */
public class CollationKey implements Comparable<CollationKey> {

    private final String source;

    private final com.ibm.icu.text.CollationKey icuKey;

    CollationKey(String source, com.ibm.icu.text.CollationKey key) {
        this.source = source;
        this.icuKey = key;
    }

    /**
     * Compares this object to the specified collation key object to determine
     * their relative order.
     * 
     * @param value
     *            the collation key object to compare this object to.
     * @return a negative value if this {@code CollationKey} is less than the
     *         specified {@code CollationKey}, 0 if they are equal and a
     *         positive value if this {@code CollationKey} is greater.
     */
    public int compareTo(CollationKey value) {
        return icuKey.compareTo(value.icuKey);
    }

    /**
     * Compares the specified object to this {@code CollationKey} and indicates
     * if they are equal. The object must be an instance of {@code CollationKey}
     * and have the same source string and collation key. Both instances of
     * {@code CollationKey} must have been created by the same {@code Collator}.
     * 
     * @param object
     *            the object to compare to this object.
     * @return {@code true} if {@code object} is equal to this collation key;
     *         {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CollationKey)) {
            return false;
        }
        CollationKey collationKey = (CollationKey) object;
        return icuKey.equals(collationKey.icuKey);
    }

    /**
     * Answer the String from which this CollationKey was created.
     * 
     * @return a String
     */
    public String getSourceString() {
        return this.source;
    }

    /**
     * Answers an integer hash code for the receiver. Objects which are equal
     * answer the same value for this method.
     * 
     * @return the receiver's hash
     * 
     * @see #equals
     */
    @Override
    public int hashCode() {
        return icuKey.hashCode();
    }

    /**
     * Answer the collation key as a byte array.
     * 
     * @return an array of bytes
     */
    public byte[] toByteArray() {
        return icuKey.toByteArray();
    }
}
