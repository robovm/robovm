/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.util.spi;

import java.util.Locale;

/**
 * This abstract class should be extended by service providers that provide
 * localized locale names.
 * <p>Note that Android does not support user-supplied locale service providers.
 * @since 1.6
 * @hide
 */
public abstract class LocaleNameProvider extends LocaleServiceProvider {
    /**
     * Default constructor, for use by subclasses.
     */
    protected LocaleNameProvider() {
        // do nothing
    }

    /**
     * Returns the localized name for the given ISO 639 language code.
     *
     * @param languageCode an ISO 639 language code
     * @param locale a locale
     * @return the name or null if unavailable
     * @throws NullPointerException
     *             if {@code code == null || locale == null}
     * @throws IllegalArgumentException
     *             if code or locale is not in a legal format or not available
     */
    public abstract String getDisplayLanguage(String languageCode, Locale locale);

    /**
     * Returns the localized name for the given ISO 3166 country code.
     *
     * @param countryCode an ISO 3166 language code
     * @param locale a locale
     * @return the name or null if unavailable
     * @throws NullPointerException
     *             if {@code code == null || locale == null}
     * @throws IllegalArgumentException
     *             if code or locale is not in a legal format or not available
     */
    public abstract String getDisplayCountry(String countryCode, Locale locale);

    /**
     * Returns the localized name for the given variant code.
     *
     * @param variantCode a variant code
     * @param locale a locale
     * @return the name or null if unavailable
     * @throws NullPointerException
     *             if {@code code == null || locale == null}
     * @throws IllegalArgumentException
     *             if code or locale is not in a legal format or not available
     */
    public abstract String getDisplayVariant(String variantCode, Locale locale);
}
