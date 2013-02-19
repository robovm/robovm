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

import libcore.icu.RuleBasedCollatorICU;

/**
 * A concrete implementation class for {@code Collation}.
 * <p>
 * {@code RuleBasedCollator} has the following restrictions for efficiency
 * (other subclasses may be used for more complex languages):
 * <ol>
 * <li> If a French secondary ordering is specified it applies to the whole
 * collator object.</li>
 * <li> All non-mentioned Unicode characters are at the end of the collation
 * order.</li>
 * <li> If a character is not located in the {@code RuleBasedCollator}, the
 * default Unicode Collation Algorithm (UCA) rule-based table is automatically
 * searched as a backup.</li>
 * </ol>
 * <p>
 * The collation table is composed of a list of collation rules, where each rule
 * is of three forms:
 * <blockquote>
 * <pre>
 * &lt;modifier&gt;
 * &lt;relation&gt; &lt;text-argument&gt;
 * &lt;reset&gt; &lt;text-argument&gt;
 * </pre>
 * </blockquote>
 * <p>
 * The rule elements are defined as follows:
 * <ul type="disc">
 * <li><strong>Modifier</strong>: There is a single modifier which is used to
 * specify that all accents (secondary differences) are backwards:
 * <ul type=square>
 * <li>'@' : Indicates that accents are sorted backwards, as in French.
 * </ul>
 * </li>
 * <li><strong>Relation</strong>: The relations are the following:
 * <ul type=square>
 * <li>'&lt;' : Greater, as a letter difference (primary)
 * <li>';' : Greater, as an accent difference (secondary)
 * <li>',' : Greater, as a case difference (tertiary)
 * <li>'=' : Equal
 * </ul>
 * </li>
 * <li><strong>Text-Argument</strong>: A text-argument is any sequence of
 * characters, excluding special characters (that is, common whitespace
 * characters [0009-000D, 0020] and rule syntax characters [0021-002F,
 * 003A-0040, 005B-0060, 007B-007E]). If those characters are desired, you can
 * put them in single quotes (for example, use '&amp;' for ampersand). Note that
 * unquoted white space characters are ignored; for example, {@code b c} is
 * treated as {@code bc}.</li>
 * <li><strong>Reset</strong>: There is a single reset which is used primarily
 * for contractions and expansions, but which can also be used to add a
 * modification at the end of a set of rules:
 * <ul type=square>
 * <li>'&amp;' : Indicates that the next rule follows the position to where the reset
 * text-argument would be sorted.
 * </ul>
 * </li>
 * </ul>
 * <p>
 * This sounds more complicated than it is in practice. For example, the
 * following are equivalent ways of expressing the same thing:
 * <blockquote>
 *
 * <pre>
 * a < b < c
 * a < b & b < c
 * a < c & a < b
 * </pre>
 *
 * </blockquote>
 * <p>
 * Notice that the order is important, as the subsequent item goes immediately
 * after the text-argument. The following are not equivalent:
 * <blockquote>
 *
 * <pre>
 * a < b & a < c
 * a < c & a < b
 * </pre>
 *
 * </blockquote>
 * <p>
 * Either the text-argument must already be present in the sequence, or some
 * initial substring of the text-argument must be present. For example
 * {@code "a < b & ae < e"} is valid since "a" is present in the sequence before
 * "ae" is reset. In this latter case, "ae" is not entered and treated as a
 * single character; instead, "e" is sorted as if it were expanded to two
 * characters: "a" followed by an "e". This difference appears in natural
 * languages: in traditional Spanish "ch" is treated as if it contracts to a
 * single character (expressed as {@code "c < ch < d"}), while in traditional
 * German a-umlaut is treated as if it expands to two characters (expressed as
 * {@code "a,A < b,B  ... & ae;\u00e3 & AE;\u00c3"}, where \u00e3 and \u00c3
 * are the escape sequences for a-umlaut).
 * <h4>Ignorable Characters</h4>
 * <p>
 * For ignorable characters, the first rule must start with a relation (the
 * examples we have used above are really fragments; {@code "a < b"} really
 * should be {@code "< a < b"}). If, however, the first relation is not
 * {@code "<"}, then all text-arguments up to the first {@code "<"} are
 * ignorable. For example, {@code ", - < a < b"} makes {@code "-"} an ignorable
 * character.
 * <h4>Normalization and Accents</h4>
 * <p>
 * {@code RuleBasedCollator} automatically processes its rule table to include
 * both pre-composed and combining-character versions of accented characters.
 * Even if the provided rule string contains only base characters and separate
 * combining accent characters, the pre-composed accented characters matching
 * all canonical combinations of characters from the rule string will be entered
 * in the table.
 * <p>
 * This allows you to use a RuleBasedCollator to compare accented strings even
 * when the collator is set to NO_DECOMPOSITION. However, if the strings to be
 * collated contain combining sequences that may not be in canonical order, you
 * should set the collator to CANONICAL_DECOMPOSITION to enable sorting of
 * combining sequences. For more information, see <a
 * href="http://www.aw.com/devpress">The Unicode Standard, Version 3.0</a>.
 * <h4>Errors</h4>
 * <p>
 * The following rules are not valid:
 * <ul type="disc">
 * <li>A text-argument contains unquoted punctuation symbols, for example
 * {@code "a < b-c < d"}.</li>
 * <li>A relation or reset character is not followed by a text-argument, for
 * example {@code "a < , b"}.</li>
 * <li>A reset where the text-argument (or an initial substring of the
 * text-argument) is not already in the sequence or allocated in the default UCA
 * table, for example {@code "a < b & e < f"}.</li>
 * </ul>
 * <p>
 * If you produce one of these errors, {@code RuleBasedCollator} throws a
 * {@code ParseException}.
 * <h4>Examples</h4>
 * <p>
 * Normally, to create a rule-based collator object, you will use
 * {@code Collator}'s factory method {@code getInstance}. However, to create a
 * rule-based collator object with specialized rules tailored to your needs, you
 * construct the {@code RuleBasedCollator} with the rules contained in a
 * {@code String} object. For example:
 * <blockquote>
 *
 * <pre>
 * String Simple = "< a < b < c < d";
 * RuleBasedCollator mySimple = new RuleBasedCollator(Simple);
 * </pre>
 *
 * </blockquote>
 * <p>
 * Or:
 * <blockquote>
 *
 * <pre>
 * String Norwegian = "< a,A< b,B< c,C< d,D< e,E< f,F< g,G< h,H< i,I"
 *         + "< j,J< k,K< l,L< m,M< n,N< o,O< p,P< q,Q< r,R"
 *         + "< s,S< t,T< u,U< v,V< w,W< x,X< y,Y< z,Z"
 *         + "< \u00E5=a\u030A,\u00C5=A\u030A"
 *         + ";aa,AA< \u00E6,\u00C6< \u00F8,\u00D8";
 * RuleBasedCollator myNorwegian = new RuleBasedCollator(Norwegian);
 * </pre>
 *
 * </blockquote>
 * <p>
 * Combining {@code Collator}s is as simple as concatenating strings. Here is
 * an example that combines two {@code Collator}s from two different locales:
 * <blockquote>
 *
 * <pre>
 * // Create an en_US Collator object
 * RuleBasedCollator en_USCollator = (RuleBasedCollator)Collator
 *         .getInstance(new Locale("en", "US", ""));
 *
 * // Create a da_DK Collator object
 * RuleBasedCollator da_DKCollator = (RuleBasedCollator)Collator
 *         .getInstance(new Locale("da", "DK", ""));
 *
 * // Combine the two collators
 * // First, get the collation rules from en_USCollator
 * String en_USRules = en_USCollator.getRules();
 *
 * // Second, get the collation rules from da_DKCollator
 * String da_DKRules = da_DKCollator.getRules();
 *
 * RuleBasedCollator newCollator = new RuleBasedCollator(en_USRules + da_DKRules);
 * // newCollator has the combined rules
 * </pre>
 *
 * </blockquote>
 * <p>
 * The next example shows to make changes on an existing table to create a new
 * {@code Collator} object. For example, add {@code "& C < ch, cH, Ch, CH"} to
 * the {@code en_USCollator} object to create your own:
 * <blockquote>
 *
 * <pre>
 * // Create a new Collator object with additional rules
 * String addRules = "& C < ch, cH, Ch, CH";
 *
 * RuleBasedCollator myCollator = new RuleBasedCollator(en_USCollator + addRules);
 * // myCollator contains the new rules
 * </pre>
 *
 * </blockquote>
 * <p>
 * The following example demonstrates how to change the order of non-spacing
 * accents:
 * <blockquote>
 *
 * <pre>
 * // old rule
 * String oldRules = "= \u00a8 ; \u00af ; \u00bf" + "< a , A ; ae, AE ; \u00e6 , \u00c6"
 *         + "< b , B < c, C < e, E & C < d, D";
 *
 * // change the order of accent characters
 * String addOn = "& \u00bf ; \u00af ; \u00a8;";
 *
 * RuleBasedCollator myCollator = new RuleBasedCollator(oldRules + addOn);
 * </pre>
 *
 * </blockquote>
 * <p>
 * The last example shows how to put new primary ordering in before the default
 * setting. For example, in the Japanese {@code Collator}, you can either sort
 * English characters before or after Japanese characters:
 * <blockquote>
 *
 * <pre>
 * // get en_US Collator rules
 * RuleBasedCollator en_USCollator = (RuleBasedCollator)
 *     Collator.getInstance(Locale.US);
 *
 * // add a few Japanese character to sort before English characters
 * // suppose the last character before the first base letter 'a' in
 * // the English collation rule is \u30A2
 * String jaString = "& \u30A2 , \u30FC < \u30C8";
 *
 * RuleBasedCollator myJapaneseCollator =
 *     new RuleBasedCollator(en_USCollator.getRules() + jaString);
 * </pre>
 *
 * </blockquote>
 */
public class RuleBasedCollator extends Collator {
    RuleBasedCollator(RuleBasedCollatorICU wrapper) {
        super(wrapper);
    }

    /**
     * Constructs a new instance of {@code RuleBasedCollator} using the
     * specified {@code rules}. The {@code rules} are usually either
     * hand-written based on the {@link RuleBasedCollator class description} or
     * the result of a former {@link #getRules()} call.
     * <p>
     * Note that the {@code rules} are actually interpreted as a delta to the
     * standard Unicode Collation Algorithm (UCA). This differs
     * slightly from other implementations which work with full {@code rules}
     * specifications and may result in different behavior.
     *
     * @param rules
     *            the collation rules.
     * @throws NullPointerException
     *             if {@code rules == null}.
     * @throws ParseException
     *             if {@code rules} contains rules with invalid collation rule
     *             syntax.
     */
    public RuleBasedCollator(String rules) throws ParseException {
        if (rules == null) {
            throw new NullPointerException();
        }
        if (rules.isEmpty()) {
            throw new ParseException("empty rules", 0);
        }
        try {
            icuColl = new RuleBasedCollatorICU(rules);
        } catch (Exception e) {
            if (e instanceof ParseException) {
                throw (ParseException) e;
            }
            /*
             * -1 means it's not a ParseException. Maybe IOException thrown when
             * an error occurred while reading internal data.
             */
            throw new ParseException(e.getMessage(), -1);
        }
    }

    /**
     * Obtains a {@code CollationElementIterator} for the given
     * {@code CharacterIterator}. The source iterator's integrity will be
     * preserved since a new copy will be created for use.
     *
     * @param source
     *            the source character iterator.
     * @return a {@code CollationElementIterator} for {@code source}.
     */
    public CollationElementIterator getCollationElementIterator(CharacterIterator source) {
        if (source == null) {
            throw new NullPointerException();
        }
        return new CollationElementIterator(icuColl.getCollationElementIterator(source));
    }

    /**
     * Obtains a {@code CollationElementIterator} for the given string.
     *
     * @param source
     *            the source string.
     * @return the {@code CollationElementIterator} for {@code source}.
     */
    public CollationElementIterator getCollationElementIterator(String source) {
        if (source == null) {
            throw new NullPointerException();
        }
        return new CollationElementIterator(icuColl.getCollationElementIterator(source));
    }

    /**
     * Returns the collation rules of this collator. These {@code rules} can be
     * fed into the {@code RuleBasedCollator(String)} constructor.
     * <p>
     * Note that the {@code rules} are actually interpreted as a delta to the
     * standard Unicode Collation Algorithm (UCA). Hence, an empty {@code rules}
     * string results in the default UCA rules being applied. This differs
     * slightly from other implementations which work with full {@code rules}
     * specifications and may result in different behavior.
     *
     * @return the collation rules.
     */
    public String getRules() {
        return icuColl.getRules();
    }

    /**
     * Returns a new collator with the same collation rules, decomposition mode and
     * strength value as this collator.
     *
     * @return a shallow copy of this collator.
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        RuleBasedCollator clone = (RuleBasedCollator) super.clone();
        return clone;
    }

    /**
     * Compares the {@code source} text to the {@code target} text according to
     * the collation rules, strength and decomposition mode for this
     * {@code RuleBasedCollator}. See the {@code Collator} class description
     * for an example of use.
     * <p>
     * General recommendation: If comparisons are to be done with the same strings
     * multiple times, it is more efficient to generate {@code CollationKey}
     * objects for the strings and use
     * {@code CollationKey.compareTo(CollationKey)} for the comparisons. If each
     * string is compared to only once, using
     * {@code RuleBasedCollator.compare(String, String)} has better performance.
     *
     * @param source
     *            the source text.
     * @param target
     *            the target text.
     * @return an integer which may be a negative value, zero, or else a
     *         positive value depending on whether {@code source} is less than,
     *         equivalent to, or greater than {@code target}.
     */
    @Override
    public int compare(String source, String target) {
        if (source == null || target == null) {
            throw new NullPointerException();
        }
        return icuColl.compare(source, target);
    }

    /**
     * Returns the {@code CollationKey} for the given source text.
     *
     * @param source
     *            the specified source text.
     * @return the {@code CollationKey} for the given source text.
     */
    @Override
    public CollationKey getCollationKey(String source) {
        return icuColl.getCollationKey(source);
    }

    @Override
    public int hashCode() {
        return icuColl.getRules().hashCode();
    }

    /**
     * Compares the specified object with this {@code RuleBasedCollator} and
     * indicates if they are equal. In order to be equal, {@code object} must be
     * an instance of {@code Collator} with the same collation rules and the
     * same attributes.
     *
     * @param obj
     *            the object to compare with this object.
     * @return {@code true} if the specified object is equal to this
     *         {@code RuleBasedCollator}; {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Collator)) {
            return false;
        }
        return super.equals(obj);
    }
}
