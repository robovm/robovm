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

package java.util;

import java.io.Serializable;

/**
 * This class represents a currency as identified in the ISO 4217 currency
 * codes.
 */
public final class Currency implements Serializable {

    private static final long serialVersionUID = -158308464356906721L;

    private static Hashtable<String, Currency> codesToCurrencies = new Hashtable<String, Currency>();

    private String currencyCode;

    /**
     * @param currencyCode
     */
    private Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Returns the {@code Currency} instance for this currency code.
     * <p>
     * 
     * @param currencyCode
     *            the currency code.
     * @return the {@code Currency} instance for this currency code.
     * 
     * @throws IllegalArgumentException
     *             if the currency code is not a supported ISO 4217 currency
     *             code.
     */
    public static Currency getInstance(String currencyCode) {
        Currency currency = codesToCurrencies.get(currencyCode);

        if (currency == null) {
            currency = new Currency(currencyCode);
            codesToCurrencies.put(currencyCode, currency);
        }

        return currency;
    }

    /**
     * Returns the {@code Currency} instance for this {@code Locale}'s country.
     * 
     * @param locale
     *            the {@code Locale} of a country.
     * @return the {@code Currency} used in the country defined by the locale parameter.
     * 
     * @throws IllegalArgumentException
     *             if the locale's country is not a supported ISO 3166 Country.
     */
    public static Currency getInstance(Locale locale) {
        com.ibm.icu.util.Currency currency = null;
        try {
            currency = com.ibm.icu.util.Currency.getInstance(locale);
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (currency == null) {
            throw new IllegalArgumentException(locale.getCountry());
        }
        String currencyCode = currency.getCurrencyCode();

        if (currencyCode.equals("None")) { //$NON-NLS-1$
            return null;
        }

        return getInstance(currencyCode);
    }

    /**
     * Returns this {@code Currency}'s ISO 4217 currency code.
     * 
     * @return this {@code Currency}'s ISO 4217 currency code.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }
    
    /**
     * Returns the symbol for this currency in the default locale. For instance,
     * if the default locale is the US, the symbol of the US dollar is "$". For
     * other locales it may be "US$". If no symbol can be determined, the ISO
     * 4217 currency code of the US dollar is returned.
     * 
     * @return the symbol for this {@code Currency} in the default {@code Locale}.
     */
    public String getSymbol() {
        return getSymbol(Locale.getDefault());
    }

    /**
     * Returns the symbol for this currency in the given {@code Locale}.
     * <p>
     * If the locale doesn't have any countries (e.g.
     * {@code Locale.JAPANESE, new Locale("en","")}), then the ISO
     * 4217 currency code is returned.
     * <p>
     * First the locale's resource bundle is checked, if the locale has the same currency,
     * the CurrencySymbol in this locale bundle is returned.
     * <p>
     * Then a currency bundle for this locale is searched.
     * <p>
     * If a currency bundle for this locale does not exist, or there is no
     * symbol for this currency in this bundle, then the
     * ISO 4217 currency code is returned.
     * <p>
     * 
     * @param locale
     *            the locale for which the currency symbol should be returned.
     * @return the representation of this {@code Currency}'s symbol in the specified
     *         locale.
     */
    public String getSymbol(Locale locale) {
        if (locale.getCountry().equals("")) { //$NON-NLS-1$
            return currencyCode;
        }
        return com.ibm.icu.util.Currency.getInstance(currencyCode).getSymbol(locale);
    }

    /**
     * Returns the default number of fraction digits for this currency. For
     * instance, the default number of fraction digits for the US dollar is 2.
     * For the Japanese Yen the number is 0. In the case of pseudo-currencies,
     * such as IMF Special Drawing Rights, -1 is returned.
     * 
     * @return the default number of fraction digits for this currency.
     */
    public int getDefaultFractionDigits() {
        return com.ibm.icu.util.Currency.getInstance(currencyCode).getDefaultFractionDigits();
    }

    /**
     * Returns this currency's ISO 4217 currency code.
     * 
     * @return this currency's ISO 4217 currency code.
     */
    @Override
    public String toString() {
        return currencyCode;
    }

    private Object readResolve() {
        return getInstance(currencyCode);
    }
}
