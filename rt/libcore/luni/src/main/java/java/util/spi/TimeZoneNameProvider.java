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
 * localized time zone names.
 * <p>Note that Android does not support user-supplied locale service providers.
 * @since 1.6
 * @hide
 */
public abstract class TimeZoneNameProvider extends LocaleServiceProvider {
    /**
     * Default constructor, for use by subclasses.
     */
    protected TimeZoneNameProvider() {
        // do nothing
    }

    /**
     * Returns the localized name for the given time zone in the given locale.
     *
     * @param id the time zone id
     * @param daylight true to return the name for daylight saving time.
     * @param style TimeZone.LONG or TimeZone.SHORT
     * @param locale the locale
     * @return the readable time zone name, or null if it is unavailable
     * @throws NullPointerException
     *             if {@code id == null || locale == null}
     * @throws IllegalArgumentException
     *             if locale is not available or style is invalid
     */
    public abstract String getDisplayName(String id, boolean daylight, int style, Locale locale);
}
