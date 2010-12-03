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
 * CurrencyNameProvider is an abstract class to get localized currency symbols
 * from service providers.
 * 
 * @since 1.6
 * 
 */
public abstract class CurrencyNameProvider extends LocaleServiceProvider {

	/**
	 * The constructor
	 * 
	 */
	protected CurrencyNameProvider() {
		// do nothing
	}

	/**
	 * Returns the symbol for the specified currency
	 * 
	 * @param code
	 *            the code of the specified currency in "ISO 4217"
	 * @param locale
	 *            the locale
	 * @return the symbol or null if there is no available symbol in the locale
	 * @throws NullPointerException
	 *             if code or locale is null
	 * @throws IllegalArgumentException
	 *             if code or locale is not in a legal format or not available
	 */
	public abstract String getSymbol(String code, Locale locale);

}
