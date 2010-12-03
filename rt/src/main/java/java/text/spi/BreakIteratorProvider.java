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

package java.text.spi;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * This abstract class should be extended by service provider which provides
 * instances of <code>BreakIterator</code> class.
 */
public abstract class BreakIteratorProvider extends LocaleServiceProvider {

    /**
     * The constructor
     * 
     */
    protected BreakIteratorProvider() {
        // Do nothing.
    }

    /**
     * Get an instance of <code>BreakIterator</code> for word breaks for the
     * given locale.
     * 
     * @param locale
     *            the specified locale
     * @return an intance of <code>BreakIterator</code> class
     * @throws NullPointerException,
     *             if locale is null
     * @throws IllegalArgumentException,
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract BreakIterator getWordInstance(Locale locale);

    /**
     * Get an instance of <code>BreakIterator</code> for line breaks for the
     * given locale.
     * 
     * @param locale
     *            the specified locale
     * @return an instance of <code>BreakIterator</code> class
     * @throws NullPointerException,
     *             if locale is null
     * @throws IllegalArgumentException,
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract BreakIterator getLineInstance(Locale locale);

    /**
     * Get an instance of <code>BreakIterator</code> for character breaks for
     * the given locale.
     * 
     * @param locale
     *            the specified locale
     * @return an instance of <code>BreakIterator</code> class
     * @throws NullPointerException,
     *             if locale is null
     * @throws IllegalArgumentException,
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract BreakIterator getCharacterInstance(Locale locale);

    /**
     * Get an instance of <code>BreakIterator</code> for sentence breaks for
     * the given locale.
     * 
     * @param locale
     *            the specified locale
     * @return an instance of <code>BreakIterator</code> class
     * @throws NullPointerException,
     *             if locale is null
     * @throws IllegalArgumentException,
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract BreakIterator getSentenceInstance(Locale locale);

}
