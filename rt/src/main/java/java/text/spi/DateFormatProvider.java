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

import java.text.DateFormat;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * This abstract class should be extended by service provider which provides
 * instances of <code>DateFormat</code> class.
 */
public abstract class DateFormatProvider extends LocaleServiceProvider {

    /**
     * The constructor
     * 
     */
    protected DateFormatProvider() {
        // Do nothing.
    }

    /**
     * Get an instance of <code>DateFormat</code> class which formats time
     * with the given formatting style for the given locale
     * 
     * @param style
     *            the given formatting style.
     * @param locale
     *            the desired locale
     * @return an instance of <code>DateFormat</code> class
     * @throws IllegalArgumentException
     *             if style is invalid, or if locale isn't one of the locales
     *             returned from getAvailableLocales().
     * @throws NullPointerException
     *             if locale is null
     */
    public abstract DateFormat getTimeInstance(int style, Locale locale);

    /**
     * Get an instance of <code>DateFormat</code> class which formats date
     * with the given formatting style for the given locale
     * 
     * @param style
     *            the given formatting style.
     * @param locale
     *            the desired locale
     * @return an instance of <code>DateFormat</code> class
     * @throws IllegalArgumentException
     *             if style is invalid, or if locale isn't one of the locales
     *             returned from getAvailableLocales().
     * @throws NullPointerException
     *             if locale is null
     */
    public abstract DateFormat getDateInstance(int style, Locale locale);

    /**
     * Get an instance of <code>DateFormat</code> class which formats date and
     * time with the given formatting style for the given locale
     * 
     * @param dateStyle
     *            the given date formatting style.
     * @param timeStyle
     *            the given time formatting style.
     * @param locale
     *            the desired locale
     * @return an instance of <code>DateFormat</code> class
     * @throws IllegalArgumentException
     *             if dateStyle or timeStyle is invalid, or if locale isn't one
     *             of the locales returned from getAvailableLocales().
     * @throws NullPointerException
     *             if locale is null
     */
    public abstract DateFormat getDateTimeInstance(int dateStyle,
            int timeStyle, Locale locale);
}
