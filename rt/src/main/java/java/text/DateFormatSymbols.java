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

package java.text;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.harmony.text.LocaleServiceProviderLoader;
import org.apache.harmony.text.internal.nls.Messages;

/**
 * Encapsulates localizable date-time formatting data, such as the names of the
 * months, the names of the days of the week, and the time zone data.
 * {@code DateFormat} and {@code SimpleDateFormat} both use
 * {@code DateFormatSymbols} to encapsulate this information.
 * <p>
 * Typically you shouldn't use {@code DateFormatSymbols} directly. Rather, you
 * are encouraged to create a date/time formatter with the {@code DateFormat}
 * class's factory methods: {@code getTimeInstance}, {@code getDateInstance},
 * or {@code getDateTimeInstance}. These methods automatically create a
 * {@code DateFormatSymbols} for the formatter so that you don't have to. After
 * the formatter is created, you may modify its format pattern using the
 * {@code setPattern} method. For more information about creating formatters
 * using {@code DateFormat}'s factory methods, see {@link DateFormat}.
 * <p>
 * If you decide to create a date/time formatter with a specific format pattern
 * for a specific locale, you can do so with:
 * <blockquote>
 *
 * <pre>
 * new SimpleDateFormat(aPattern, new DateFormatSymbols(aLocale)).
 * </pre>
 *
 * </blockquote>
 * <p>
 * {@code DateFormatSymbols} objects can be cloned. When you obtain a
 * {@code DateFormatSymbols} object, feel free to modify the date/time
 * formatting data. For instance, you can replace the localized date/time format
 * pattern characters with the ones that you feel easy to remember or you can
 * change the representative cities to your favorite ones.
 * <p>
 * New {@code DateFormatSymbols} subclasses may be added to support
 * {@code SimpleDateFormat} for date/time formatting for additional locales.
 *
 * @see DateFormat
 * @see SimpleDateFormat
 */
public class DateFormatSymbols implements Serializable, Cloneable {

    private static final long serialVersionUID = -5987973545549424702L;

    private String localPatternChars;

    String[] ampms, eras, months, shortMonths, shortWeekdays, weekdays;

    String[][] zoneStrings;

    private static Set<Locale> buildinLocales;

    private static HashMap<ClassLoader, Object> providersCache = new HashMap<ClassLoader, Object>();

    private static final String PROVIDER_CONFIGURATION_FILE_NAME = "META-INF/services/java.text.spi.DateFormatSymbolsProvider"; //$NON-NLS-1$

    transient private com.ibm.icu.text.DateFormatSymbols icuSymbols;

    /**
     * Constructs a new {@code DateFormatSymbols} instance containing the
     * symbols for the default locale.
     */
    public DateFormatSymbols() {
        this(Locale.getDefault());
    }

    /**
     * Constructs a new {@code DateFormatSymbols} instance containing the
     * symbols for the specified locale.
     * 
     * @param locale
     *            the locale.
     */
    public DateFormatSymbols(Locale locale) {
        this(locale, new com.ibm.icu.text.DateFormatSymbols(locale));
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        if (zoneStrings == null) {
            zoneStrings = icuSymbols.getZoneStrings();
        }
        oos.defaultWriteObject();
    }

    DateFormatSymbols(Locale locale,
            com.ibm.icu.text.DateFormatSymbols icuSymbols) {

        this.icuSymbols = icuSymbols;
        localPatternChars = icuSymbols.getLocalPatternChars();
        ampms = icuSymbols.getAmPmStrings();
        eras = icuSymbols.getEras();
        months = icuSymbols.getMonths();
        shortMonths = icuSymbols.getShortMonths();
        shortWeekdays = icuSymbols.getShortWeekdays();
        weekdays = icuSymbols.getWeekdays();
    }

    /**
     * Get all locales which <code>getInstance(Locale)</code> method support
     * to return localize instance. The returned array locales include Java
     * runtime and installed service provider supported locales. And it must
     * contain <code>Locale</code> instance equals to <code>Locale.US</code>.
     * 
     * @return array of locales
     */
    public static Locale[] getAvailableLocales() {
        Locale[] icuLocales = com.ibm.icu.text.DateFormatSymbols
                .getAvailableLocales();
        if (buildinLocales == null) {
            initBuildInLocales(icuLocales);
        }

        Locale[] providerLocales = LocaleServiceProviderLoader
                .getProviderSupportLocales(providersCache,
                        PROVIDER_CONFIGURATION_FILE_NAME);

        if (providerLocales == null) {
            return icuLocales;
        }

        Locale[] locales = new Locale[icuLocales.length
                + providerLocales.length];
        System.arraycopy(icuLocales, 0, locales, 0, icuLocales.length);
        System.arraycopy(providerLocales, 0, locales, icuLocales.length,
                providerLocales.length);
        return locales;
    }

    private static synchronized void initBuildInLocales(Locale[] icuLocales) {
        buildinLocales = new HashSet<Locale>(icuLocales.length);
        for (Locale locale : icuLocales) {
            buildinLocales.add(locale);
        }
    }

    /**
     * Return the DateFormatSymbols instance for the default locale.
     * 
     * @return an instance of DateFormatSymbols
     * 
     * @since 1.6
     */
    public static final DateFormatSymbols getInstance() {
        return new DateFormatSymbols();
    }

    /**
     * Return the DateFormatSymbols for the specified locale. This method return
     * DateFormatSymbols instances for locales supported by the Java runtime and
     * installed DateFormatSymbols implementations.
     * 
     * @param locale
     *            locale for the returned DateFormatSymbols instance
     * 
     * @return an instance of DateFormatSymbols
     * 
     * @exception NullPointerException
     *                if locale is null
     * 
     * @since 1.6
     */
    public static final DateFormatSymbols getInstance(Locale locale) {
        if (null == locale) {
            throw new NullPointerException();
        }

        if (buildinLocales == null) {
            initBuildInLocales(com.ibm.icu.text.DateFormatSymbols
                    .getAvailableLocales());
        }

        if (buildinLocales.contains(locale)) {
            return new DateFormatSymbols(locale);
        }

        DateFormatSymbolsProvider provider = (DateFormatSymbolsProvider) LocaleServiceProviderLoader
                .getProviderByLocale(providersCache, locale,
                        PROVIDER_CONFIGURATION_FILE_NAME);

        if (provider != null) {
            return provider.getInstance(locale);
        }

        // return DateFormatSymbols using default locale
        return new DateFormatSymbols();

    }

    @Override
    public Object clone() {
        if (zoneStrings == null) {
            zoneStrings = icuSymbols.getZoneStrings();
        }
        try {
            DateFormatSymbols symbols = (DateFormatSymbols) super.clone();
            symbols.ampms = ampms.clone();
            symbols.eras = eras.clone();
            symbols.months = months.clone();
            symbols.shortMonths = shortMonths.clone();
            symbols.shortWeekdays = shortWeekdays.clone();
            symbols.weekdays = weekdays.clone();
            symbols.zoneStrings = new String[zoneStrings.length][];
            for (int i = 0; i < zoneStrings.length; i++) {
                symbols.zoneStrings[i] = zoneStrings[i].clone();
            }
            return symbols;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Compares this object with the specified object and indicates if they are
     * equal.
     * 
     * @param object
     *            the object to compare with this object.
     * @return {@code true} if {@code object} is an instance of
     *         {@code DateFormatSymbols} and has the same symbols as this
     *         object, {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DateFormatSymbols)) {
            return false;
        }
        if (zoneStrings == null) {
            zoneStrings = icuSymbols.getZoneStrings();
        }

        DateFormatSymbols obj = (DateFormatSymbols) object;

        if (obj.zoneStrings == null) {
            obj.zoneStrings = obj.icuSymbols.getZoneStrings();
        }
        if (!localPatternChars.equals(obj.localPatternChars)) {
            return false;
        }
        if (!Arrays.equals(ampms, obj.ampms)) {
            return false;
        }
        if (!Arrays.equals(eras, obj.eras)) {
            return false;
        }
        if (!Arrays.equals(months, obj.months)) {
            return false;
        }
        if (!Arrays.equals(shortMonths, obj.shortMonths)) {
            return false;
        }
        if (!Arrays.equals(shortWeekdays, obj.shortWeekdays)) {
            return false;
        }
        if (!Arrays.equals(weekdays, obj.weekdays)) {
            return false;
        }
        if (zoneStrings.length != obj.zoneStrings.length) {
            return false;
        }
        for (String[] element : zoneStrings) {
            if (element.length != element.length) {
                return false;
            }
            for (int j = 0; j < element.length; j++) {
                if (element[j] != element[j]
                        && !(element[j].equals(element[j]))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the array of strings which represent AM and PM. Use the
     * {@link java.util.Calendar} constants {@code Calendar.AM} and
     * {@code Calendar.PM} as indices for the array.
     * 
     * @return an array of strings.
     */
    public String[] getAmPmStrings() {
        return ampms.clone();
    }

    /**
     * Returns the array of strings which represent BC and AD. Use the
     * {@link java.util.Calendar} constants {@code GregorianCalendar.BC} and
     * {@code GregorianCalendar.AD} as indices for the array.
     * 
     * @return an array of strings.
     */
    public String[] getEras() {
        return eras.clone();
    }

    /**
     * Returns the pattern characters used by {@link SimpleDateFormat} to
     * specify date and time fields.
     * 
     * @return a string containing the pattern characters.
     */
    public String getLocalPatternChars() {
        return localPatternChars;
    }

    /**
     * Returns the array of strings containing the full names of the months. Use
     * the {@link java.util.Calendar} constants {@code Calendar.JANUARY} etc. as
     * indices for the array.
     * 
     * @return an array of strings.
     */
    public String[] getMonths() {
        return months.clone();
    }

    /**
     * Returns the array of strings containing the abbreviated names of the
     * months. Use the {@link java.util.Calendar} constants
     * {@code Calendar.JANUARY} etc. as indices for the array.
     * 
     * @return an array of strings.
     */
    public String[] getShortMonths() {
        return shortMonths.clone();
    }

    /**
     * Returns the array of strings containing the abbreviated names of the days
     * of the week. Use the {@link java.util.Calendar} constants
     * {@code Calendar.SUNDAY} etc. as indices for the array.
     * 
     * @return an array of strings.
     */
    public String[] getShortWeekdays() {
        return shortWeekdays.clone();
    }

    /**
     * Returns the array of strings containing the full names of the days of the
     * week. Use the {@link java.util.Calendar} constants
     * {@code Calendar.SUNDAY} etc. as indices for the array.
     * 
     * @return an array of strings.
     */
    public String[] getWeekdays() {
        return weekdays.clone();
    }

    /**
     * Returns the two-dimensional array of strings containing the names of the
     * time zones. Each element in the array is an array of five strings, the
     * first is a TimeZone ID, the second and third are the full and abbreviated
     * time zone names for standard time, and the fourth and fifth are the full
     * and abbreviated names for daylight time.
     * 
     * @return a two-dimensional array of strings.
     */
    public String[][] getZoneStrings() {
        if (zoneStrings == null) {
            zoneStrings = icuSymbols.getZoneStrings();
        }
        String[][] clone = new String[zoneStrings.length][];
        for (int i = zoneStrings.length; --i >= 0;) {
            clone[i] = zoneStrings[i].clone();
        }
        return clone;
    }

    @Override
    public int hashCode() {
        if (zoneStrings == null) {
            zoneStrings = icuSymbols.getZoneStrings();
        }
        int hashCode;
        hashCode = localPatternChars.hashCode();
        for (String element : ampms) {
            hashCode += element.hashCode();
        }
        for (String element : eras) {
            hashCode += element.hashCode();
        }
        for (String element : months) {
            hashCode += element.hashCode();
        }
        for (String element : shortMonths) {
            hashCode += element.hashCode();
        }
        for (String element : shortWeekdays) {
            hashCode += element.hashCode();
        }
        for (String element : weekdays) {
            hashCode += element.hashCode();
        }
        for (String[] element : zoneStrings) {
            for (int j = 0; j < element.length; j++) {
                if (element[j] != null) {
                    hashCode += element[j].hashCode();
                }
            }
        }
        return hashCode;
    }

    /**
     * Sets the array of strings which represent AM and PM. Use the
     * {@link java.util.Calendar} constants {@code Calendar.AM} and
     * {@code Calendar.PM} as indices for the array.
     * 
     * @param data
     *            the array of strings for AM and PM.
     */
    public void setAmPmStrings(String[] data) {
        ampms = data.clone();
    }

    /**
     * Sets the array of Strings which represent BC and AD. Use the
     * {@link java.util.Calendar} constants {@code GregorianCalendar.BC} and
     * {@code GregorianCalendar.AD} as indices for the array.
     * 
     * @param data
     *            the array of strings for BC and AD.
     */
    public void setEras(String[] data) {
        eras = data.clone();
    }

    /**
     * Sets the pattern characters used by {@link SimpleDateFormat} to specify
     * date and time fields.
     * 
     * @param data
     *            the string containing the pattern characters.
     * @throws NullPointerException
     *            if {@code data} is null
     */
    public void setLocalPatternChars(String data) {
        if (data == null) {
            throw new NullPointerException();
        }
        localPatternChars = data;
    }

    /**
     * Sets the array of strings containing the full names of the months. Use
     * the {@link java.util.Calendar} constants {@code Calendar.JANUARY} etc. as
     * indices for the array.
     * 
     * @param data
     *            the array of strings.
     */
    public void setMonths(String[] data) {
        months = data.clone();
    }

    /**
     * Sets the array of strings containing the abbreviated names of the months.
     * Use the {@link java.util.Calendar} constants {@code Calendar.JANUARY}
     * etc. as indices for the array.
     * 
     * @param data
     *            the array of strings.
     */
    public void setShortMonths(String[] data) {
        shortMonths = data.clone();
    }

    /**
     * Sets the array of strings containing the abbreviated names of the days of
     * the week. Use the {@link java.util.Calendar} constants
     * {@code Calendar.SUNDAY} etc. as indices for the array.
     * 
     * @param data
     *            the array of strings.
     */
    public void setShortWeekdays(String[] data) {
        shortWeekdays = data.clone();
    }

    /**
     * Sets the array of strings containing the full names of the days of the
     * week. Use the {@link java.util.Calendar} constants
     * {@code Calendar.SUNDAY} etc. as indices for the array.
     * 
     * @param data
     *            the array of strings.
     */
    public void setWeekdays(String[] data) {
        weekdays = data.clone();
    }

    /**
     * Sets the two-dimensional array of strings containing the names of the
     * time zones. Each element in the array is an array of five strings, the
     * first is a TimeZone ID, and second and third are the full and abbreviated
     * time zone names for standard time, and the fourth and fifth are the full
     * and abbreviated names for daylight time.
     * 
     * @param data
     *            the two-dimensional array of strings.
     */
    public void setZoneStrings(String[][] data) {
        validateZoneStrings(data);
        zoneStrings = data.clone();
    }

    /**
     * Check that each row in the 2D array is at least length 5
     * 
     * @param data
     * @throws IllegalArgumentException if array contains a short row
     */
    private void validateZoneStrings(String[][] data) {
        for (String[] row : data)
            if (row.length < 5)
                throw new IllegalArgumentException(Messages.getString("text.1B"));
    }
}
