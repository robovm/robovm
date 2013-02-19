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

import java.text.NumberFormat;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * This abstract class should be extended by service providers that provide
 * {@code NumberFormat} instances.
 * <p>Note that Android does not support user-supplied locale service providers.
 * @since 1.6
 * @hide
 */
public abstract class NumberFormatProvider extends LocaleServiceProvider {
    /**
     * Default constructor, for use by subclasses.
     */
    protected NumberFormatProvider() {
        // Do nothing.
    }

    /**
     * Returns an instance of {@code NumberFormat} that formats
     * monetary values for the given locale.
     *
     * @param locale the locale
     * @return an instance of {@code NumberFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract NumberFormat getCurrencyInstance(Locale locale);

    /**
     * Returns an instance of {@code NumberFormat} that formats
     * integer values for the given locale. The returned {@code NumberFormat}
     * is configured to round floating point numbers to the nearest integer
     * using half-even rounding mode for formatting, and to parse only the
     * integer part of an input string.
     *
     * @param locale the locale
     * @return an instance of {@code NumberFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract NumberFormat getIntegerInstance(Locale locale);

    /**
     * Returns an instance of {@code NumberFormat} class for general
     * use in the given locale.
     *
     * @param locale the locale
     * @return an instance of {@code NumberFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract NumberFormat getNumberInstance(Locale locale);

    /**
     * Returns an instance of {@code NumberFormat} class that formats
     * percentage values for the given locale.
     *
     * @param locale the locale
     * @return an instance of {@code NumberFormat}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract NumberFormat getPercentInstance(Locale locale);
}
