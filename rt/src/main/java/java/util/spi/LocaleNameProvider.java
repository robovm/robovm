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
 * LocaleNameProvider is an abstract class to get localized names from service
 * providers.
 * 
 * @since 1.6
 * 
 */
public abstract class LocaleNameProvider extends LocaleServiceProvider {

	/**
	 * The constructor
	 * 
	 */
	protected LocaleNameProvider() {
		// do nothing
	}

	/**
	 * Gets the localized name for the specified language code in "ISO 639" and
	 * the specified locale to display.
	 * 
	 * @param code
	 *            the code of language in "ISO 639"
	 * @param locale
	 *            the locale
	 * @return the name or null if unavailable
	 * @throws NullPointerException
	 *             if code or locale is null
	 * @throws IllegalArgumentException
	 *             if code or locale is not in a legal format or not available
	 */
	public abstract String getDisplayLanguage(String code, Locale locale);

	/**
	 * Gets the localized name for the specified country code in "ISO 3166" and
	 * the specified locale to display.
	 * 
	 * @param code
	 *            the code of country in "ISO 3166"
	 * @param locale
	 *            the locale
	 * @return the name or null if unavailable
	 * @throws NullPointerException
	 *             if code or locale is null
	 * @throws IllegalArgumentException
	 *             if code or locale is not in a legal format or not available
	 */
	public abstract String getDisplayCountry(String code, Locale locale);

	/**
	 * Gets the localized name for the specified variant code and the specified
	 * locale to display.
	 * 
	 * @param variant
	 *            the variant code
	 * @param locale
	 *            the locale
	 * @return the name or null if unavailable
	 * @throws NullPointerException
	 *             if variant code or locale is null
	 * @throws IllegalArgumentException
	 *             if locale is not available
	 */
	public abstract String getDisplayVariant(String variant, Locale locale);
}
