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
 * This class provider Unicode Normalization functions, which transform Unicode
 * text into an equivalent composed or decomposed form, allowing for easier
 * sorting and searching of text.
 * 
 * @since 1.6
 */
public final class Normalizer {

    /**
     * This enum providers constants of normalization forms defined in Unicode
     * Standard Annex #15 Unicode Normalization Forms.
     * 
     * @since 1.6
     */
    public static enum Form {
        /**
         * Canonical decomposition.
         */
        NFD,
        /**
         * Canonical decomposition followed by canonical composition.
         */
        NFC,
        /**
         * Compatibility decomposition.
         */
        NFKD,
        /**
         * Compatibility decomposition followed by canonical composition.
         */
        NFKC
    }

    /**
     * Normalize the given sequence of char <code>src</code> according to the
     * specified normalization form.
     * 
     * @param src
     *            The sequence of char values.
     * @param form
     *            The specified normalization form.
     * @return The normalized String
     * 
     * @exception NullPointerException
     *                If <code>src</code> or <code>form</code> is null.
     */
    public static String normalize(CharSequence src, Form form) {
        if (src == null || form == null) {
            throw new NullPointerException();
        }
        
        switch (form) {
        case NFC:
            return com.ibm.icu.text.Normalizer.normalize(src.toString(),
                    com.ibm.icu.text.Normalizer.NFC);
        case NFD:
            return com.ibm.icu.text.Normalizer.normalize(src.toString(),
                    com.ibm.icu.text.Normalizer.NFD);
        case NFKC:
            return com.ibm.icu.text.Normalizer.normalize(src.toString(),
                    com.ibm.icu.text.Normalizer.NFKC);
        case NFKD:
            return com.ibm.icu.text.Normalizer.normalize(src.toString(),
                    com.ibm.icu.text.Normalizer.NFKD);
        default:
            // never reach
            return null;
        }
        
    }

    /**
     * Test wheather the given sequence of char <code>src</code> is normalized
     * according to the specified normalization form.
     * 
     * @param src
     *            The sequence of char values.
     * @param form
     *            The specified normalization form.
     * @return true if <code>src</code> is normalized; false otherwise.
     * 
     * @exception NullPointerException
     *                If <code>src</code> or <code>form</code> is null.
     */
    public static boolean isNormalized(CharSequence src, Form form) {
        if (src == null || form == null) {
            throw new NullPointerException();
        }

        switch (form) {
        case NFC:
            return com.ibm.icu.text.Normalizer.isNormalized(src.toString(),
                    com.ibm.icu.text.Normalizer.NFC,
                    com.ibm.icu.text.Normalizer.UNICODE_3_2);
        case NFD:
            return com.ibm.icu.text.Normalizer.isNormalized(src.toString(),
                    com.ibm.icu.text.Normalizer.NFD,
                    com.ibm.icu.text.Normalizer.UNICODE_3_2);
        case NFKC:
            return com.ibm.icu.text.Normalizer.isNormalized(src.toString(),
                    com.ibm.icu.text.Normalizer.NFKC,
                    com.ibm.icu.text.Normalizer.UNICODE_3_2);
        case NFKD:
            return com.ibm.icu.text.Normalizer.isNormalized(src.toString(),
                    com.ibm.icu.text.Normalizer.NFKD,
                    com.ibm.icu.text.Normalizer.UNICODE_3_2);
        default:
            // never reach
            return false;
        }
    }
}
