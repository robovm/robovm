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
 * This abstract class should be extended by service providers that provide
 * instances of {@code DateFormat}.
 * <p>Note that Android does not support user-supplied locale service providers.
 * @since 1.6
 * @hide
 */
public abstract class DateFormatProvider extends LocaleServiceProvider {
    /**
     * Default constructor, for use by subclasses.
     */
    protected DateFormatProvider() {
        // Do nothing.
    }

    /**
     * Returns an instance of {@code DateFormat} that formats times
     * in the given style for the given locale.
     *
     * @param style the given time formatting style.
     * @param locale the locale
     * @return an instance of {@code DateFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract DateFormat getTimeInstance(int style, Locale locale);

    /**
     * Returns an instance of {@code DateFormat} that formats dates
     * in the given style for the given locale.
     *
     * @param style the given date formatting style.
     * @param locale the locale
     * @return an instance of {@code DateFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract DateFormat getDateInstance(int style, Locale locale);

    /**
     * Returns an instance of {@code DateFormat} that formats dates and times
     * in the given style for the given locale.
     *
     * @param dateStyle the given date formatting style.
     * @param timeStyle the given time formatting style.
     * @param locale the locale
     * @return an instance of {@code DateFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract DateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale);
}
