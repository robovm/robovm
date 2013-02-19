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

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * This abstract class should be extended by service providers that provide
 * instances of {@code DateFormatSymbols}.
 * <p>Note that Android does not support user-supplied locale service providers.
 * @since 1.6
 * @hide
 */
public abstract class DateFormatSymbolsProvider extends LocaleServiceProvider {
    /**
     * Default constructor, for use by subclasses.
     */
    protected DateFormatSymbolsProvider() {
        // Do nothing.
    }

    /**
     * Returns an instance of {@code DateFormatSymbols} for the given locale.
     *
     * @param locale the locale
     * @return an instance of {@code DateFormatSymbols}
     * @throws NullPointerException if {@code locale == null}
     * @throws IllegalArgumentException
     *             if locale isn't one of the locales returned from
     *             getAvailableLocales().
     */
    public abstract DateFormatSymbols getInstance(Locale locale);
}
