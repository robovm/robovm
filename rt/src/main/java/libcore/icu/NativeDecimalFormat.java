/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.icu;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.NoSuchElementException;

public final class NativeDecimalFormat {
    /**
     * Constants corresponding to the native type UNumberFormatSymbol, for setSymbol.
     */
    private static final int UNUM_DECIMAL_SEPARATOR_SYMBOL = 0;
    private static final int UNUM_GROUPING_SEPARATOR_SYMBOL = 1;
    private static final int UNUM_PATTERN_SEPARATOR_SYMBOL = 2;
    private static final int UNUM_PERCENT_SYMBOL = 3;
    private static final int UNUM_ZERO_DIGIT_SYMBOL = 4;
    private static final int UNUM_DIGIT_SYMBOL = 5;
    private static final int UNUM_MINUS_SIGN_SYMBOL = 6;
    private static final int UNUM_PLUS_SIGN_SYMBOL = 7;
    private static final int UNUM_CURRENCY_SYMBOL = 8;
    private static final int UNUM_INTL_CURRENCY_SYMBOL = 9;
    private static final int UNUM_MONETARY_SEPARATOR_SYMBOL = 10;
    private static final int UNUM_EXPONENTIAL_SYMBOL = 11;
    private static final int UNUM_PERMILL_SYMBOL = 12;
    private static final int UNUM_PAD_ESCAPE_SYMBOL = 13;
    private static final int UNUM_INFINITY_SYMBOL = 14;
    private static final int UNUM_NAN_SYMBOL = 15;
    private static final int UNUM_SIGNIFICANT_DIGIT_SYMBOL = 16;
    private static final int UNUM_MONETARY_GROUPING_SEPARATOR_SYMBOL = 17;
    private static final int UNUM_FORMAT_SYMBOL_COUNT = 18;

    /**
     * Constants corresponding to the native type UNumberFormatAttribute, for
     * getAttribute/setAttribute.
     */
    private static final int UNUM_PARSE_INT_ONLY = 0;
    private static final int UNUM_GROUPING_USED = 1;
    private static final int UNUM_DECIMAL_ALWAYS_SHOWN = 2;
    private static final int UNUM_MAX_INTEGER_DIGITS = 3;
    private static final int UNUM_MIN_INTEGER_DIGITS = 4;
    private static final int UNUM_INTEGER_DIGITS = 5;
    private static final int UNUM_MAX_FRACTION_DIGITS = 6;
    private static final int UNUM_MIN_FRACTION_DIGITS = 7;
    private static final int UNUM_FRACTION_DIGITS = 8;
    private static final int UNUM_MULTIPLIER = 9;
    private static final int UNUM_GROUPING_SIZE = 10;
    private static final int UNUM_ROUNDING_MODE = 11;
    private static final int UNUM_ROUNDING_INCREMENT = 12;
    private static final int UNUM_FORMAT_WIDTH = 13;
    private static final int UNUM_PADDING_POSITION = 14;
    private static final int UNUM_SECONDARY_GROUPING_SIZE = 15;
    private static final int UNUM_SIGNIFICANT_DIGITS_USED = 16;
    private static final int UNUM_MIN_SIGNIFICANT_DIGITS = 17;
    private static final int UNUM_MAX_SIGNIFICANT_DIGITS = 18;
    private static final int UNUM_LENIENT_PARSE = 19;

    /**
     * Constants corresponding to the native type UNumberFormatTextAttribute, for
     * getTextAttribute/setTextAttribute.
     */
    private static final int UNUM_POSITIVE_PREFIX = 0;
    private static final int UNUM_POSITIVE_SUFFIX = 1;
    private static final int UNUM_NEGATIVE_PREFIX = 2;
    private static final int UNUM_NEGATIVE_SUFFIX = 3;
    private static final int UNUM_PADDING_CHARACTER = 4;
    private static final int UNUM_CURRENCY_CODE = 5;
    private static final int UNUM_DEFAULT_RULESET = 6;
    private static final int UNUM_PUBLIC_RULESETS = 7;

    /**
     * The address of the ICU DecimalFormat* on the native heap.
     */
    private int address;

    /**
     * The last pattern we gave to ICU, so we can make repeated applications cheap.
     * This helps in cases like String.format("%.2f,%.2f\n", x, y) where the DecimalFormat is
     * reused.
     */
    private String lastPattern;

    // TODO: store all these in DecimalFormat instead!
    private boolean negPrefNull;
    private boolean negSuffNull;
    private boolean posPrefNull;
    private boolean posSuffNull;

    private transient boolean parseBigDecimal;

    /**
     * Cache the BigDecimal form of the multiplier. This is null until we've
     * formatted a BigDecimal (with a multiplier that is not 1), or the user has
     * explicitly called {@link #setMultiplier(int)} with any multiplier.
     */
    private BigDecimal multiplierBigDecimal = null;

    public NativeDecimalFormat(String pattern, DecimalFormatSymbols dfs) {
        try {
            this.address = open(pattern, dfs.getCurrencySymbol(),
                    dfs.getDecimalSeparator(), dfs.getDigit(), dfs.getExponentSeparator(),
                    dfs.getGroupingSeparator(), dfs.getInfinity(),
                    dfs.getInternationalCurrencySymbol(), dfs.getMinusSign(),
                    dfs.getMonetaryDecimalSeparator(), dfs.getNaN(), dfs.getPatternSeparator(),
                    dfs.getPercent(), dfs.getPerMill(), dfs.getZeroDigit());
            this.lastPattern = pattern;
        } catch (NullPointerException npe) {
            throw npe;
        } catch (RuntimeException re) {
            throw new IllegalArgumentException("syntax error: " + re.getMessage() + ": " + pattern);
        }
    }

    // Used so java.util.Formatter doesn't need to allocate DecimalFormatSymbols instances.
    public NativeDecimalFormat(String pattern, LocaleData data) {
        this.address = open(pattern, data.currencySymbol,
                data.decimalSeparator, '#', data.exponentSeparator, data.groupingSeparator,
                data.infinity, data.internationalCurrencySymbol, data.minusSign,
                data.monetarySeparator, data.NaN, data.patternSeparator,
                data.percent, data.perMill, data.zeroDigit);
        this.lastPattern = pattern;
    }

    // Used to implement clone.
    private NativeDecimalFormat(NativeDecimalFormat other) {
        this.address = cloneImpl(other.address);
        this.lastPattern = other.lastPattern;
        this.negPrefNull = other.negPrefNull;
        this.negSuffNull = other.negSuffNull;
        this.posPrefNull = other.posPrefNull;
        this.posSuffNull = other.posSuffNull;
    }

    // TODO: remove this and just have DecimalFormat.hashCode do the right thing itself.
    @Override
    public int hashCode() {
        return this.getPositivePrefix().hashCode();
    }

    public synchronized void close() {
        if (address != 0) {
            close(address);
            address = 0;
        }
    }

    @Override
    public Object clone() {
        return new NativeDecimalFormat(this);
    }

    /**
     * Note: this doesn't check that the underlying native DecimalFormat objects' configured
     * native DecimalFormatSymbols objects are equal. It is assumed that the
     * caller (DecimalFormat) will check the DecimalFormatSymbols objects
     * instead, for performance.
     *
     * This is also unreasonably expensive, calling down to JNI multiple times.
     *
     * TODO: remove this and just have DecimalFormat.equals do the right thing itself.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof NativeDecimalFormat)) {
            return false;
        }
        NativeDecimalFormat obj = (NativeDecimalFormat) object;
        if (obj.address == this.address) {
            return true;
        }
        return obj.toPattern().equals(this.toPattern()) &&
                obj.isDecimalSeparatorAlwaysShown() == this.isDecimalSeparatorAlwaysShown() &&
                obj.getGroupingSize() == this.getGroupingSize() &&
                obj.getMultiplier() == this.getMultiplier() &&
                obj.getNegativePrefix().equals(this.getNegativePrefix()) &&
                obj.getNegativeSuffix().equals(this.getNegativeSuffix()) &&
                obj.getPositivePrefix().equals(this.getPositivePrefix()) &&
                obj.getPositiveSuffix().equals(this.getPositiveSuffix()) &&
                obj.getMaximumIntegerDigits() == this.getMaximumIntegerDigits() &&
                obj.getMaximumFractionDigits() == this.getMaximumFractionDigits() &&
                obj.getMinimumIntegerDigits() == this.getMinimumIntegerDigits() &&
                obj.getMinimumFractionDigits() == this.getMinimumFractionDigits() &&
                obj.isGroupingUsed() == this.isGroupingUsed();
    }

    /**
     * Copies the DecimalFormatSymbols settings into our native peer in bulk.
     */
    public void setDecimalFormatSymbols(final DecimalFormatSymbols dfs) {
        setDecimalFormatSymbols(this.address, dfs.getCurrencySymbol(), dfs.getDecimalSeparator(),
                dfs.getDigit(), dfs.getExponentSeparator(), dfs.getGroupingSeparator(),
                dfs.getInfinity(), dfs.getInternationalCurrencySymbol(), dfs.getMinusSign(),
                dfs.getMonetaryDecimalSeparator(), dfs.getNaN(), dfs.getPatternSeparator(),
                dfs.getPercent(), dfs.getPerMill(), dfs.getZeroDigit());
    }

    public void setDecimalFormatSymbols(final LocaleData localeData) {
        setDecimalFormatSymbols(this.address, localeData.currencySymbol, localeData.decimalSeparator,
                '#', localeData.exponentSeparator, localeData.groupingSeparator,
                localeData.infinity, localeData.internationalCurrencySymbol, localeData.minusSign,
                localeData.monetarySeparator, localeData.NaN, localeData.patternSeparator,
                localeData.percent, localeData.perMill, localeData.zeroDigit);
    }

    public char[] formatBigDecimal(BigDecimal value, FieldPosition field) {
        FieldPositionIterator fpi = FieldPositionIterator.forFieldPosition(field);
        char[] result = formatDigitList(this.address, value.toString(), fpi);
        if (fpi != null) {
            FieldPositionIterator.setFieldPosition(fpi, field);
        }
        return result;
    }

    public char[] formatBigInteger(BigInteger value, FieldPosition field) {
        FieldPositionIterator fpi = FieldPositionIterator.forFieldPosition(field);
        char[] result = formatDigitList(this.address, value.toString(10), fpi);
        if (fpi != null) {
            FieldPositionIterator.setFieldPosition(fpi, field);
        }
        return result;
    }

    public char[] formatLong(long value, FieldPosition field) {
        FieldPositionIterator fpi = FieldPositionIterator.forFieldPosition(field);
        char[] result = formatLong(this.address, value, fpi);
        if (fpi != null) {
            FieldPositionIterator.setFieldPosition(fpi, field);
        }
        return result;
    }

    public char[] formatDouble(double value, FieldPosition field) {
        FieldPositionIterator fpi = FieldPositionIterator.forFieldPosition(field);
        char[] result = formatDouble(this.address, value, fpi);
        if (fpi != null) {
            FieldPositionIterator.setFieldPosition(fpi, field);
        }
        return result;
    }

    public void applyLocalizedPattern(String pattern) {
        applyPattern(this.address, true, pattern);
        lastPattern = null;
    }

    public void applyPattern(String pattern) {
        if (lastPattern != null && pattern.equals(lastPattern)) {
            return;
        }
        applyPattern(this.address, false, pattern);
        lastPattern = pattern;
    }

    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
        if (!(object instanceof Number)) {
            throw new IllegalArgumentException();
        }
        Number number = (Number) object;
        FieldPositionIterator fpIter = new FieldPositionIterator();
        String text;
        if (number instanceof BigInteger || number instanceof BigDecimal) {
            text = new String(formatDigitList(this.address, number.toString(), fpIter));
        } else if (number instanceof Double || number instanceof Float) {
            double dv = number.doubleValue();
            text = new String(formatDouble(this.address, dv, fpIter));
        } else {
            long lv = number.longValue();
            text = new String(formatLong(this.address, lv, fpIter));
        }

        AttributedString as = new AttributedString(text);

        while (fpIter.next()) {
            Format.Field field = fpIter.field();
            as.addAttribute(field, field, fpIter.start(), fpIter.limit());
        }

        // return the CharacterIterator from AttributedString
        return as.getIterator();
    }

    private int makeScalePositive(int scale, StringBuilder val) {
        if (scale < 0) {
            scale = -scale;
            for (int i = scale; i > 0; i--) {
                val.append('0');
            }
            scale = 0;
        }
        return scale;
    }

    public String toLocalizedPattern() {
        return toPatternImpl(this.address, true);
    }

    public String toPattern() {
        return toPatternImpl(this.address, false);
    }

    public Number parse(String string, ParsePosition position) {
        return parse(address, string, position, parseBigDecimal);
    }

    // start getter and setter

    public int getMaximumFractionDigits() {
        return getAttribute(this.address, UNUM_MAX_FRACTION_DIGITS);
    }

    public int getMaximumIntegerDigits() {
        return getAttribute(this.address, UNUM_MAX_INTEGER_DIGITS);
    }

    public int getMinimumFractionDigits() {
        return getAttribute(this.address, UNUM_MIN_FRACTION_DIGITS);
    }

    public int getMinimumIntegerDigits() {
        return getAttribute(this.address, UNUM_MIN_INTEGER_DIGITS);
    }

    public int getGroupingSize() {
        return getAttribute(this.address, UNUM_GROUPING_SIZE);
    }

    public int getMultiplier() {
        return getAttribute(this.address, UNUM_MULTIPLIER);
    }

    public String getNegativePrefix() {
        if (negPrefNull) {
            return null;
        }
        return getTextAttribute(this.address, UNUM_NEGATIVE_PREFIX);
    }

    public String getNegativeSuffix() {
        if (negSuffNull) {
            return null;
        }
        return getTextAttribute(this.address, UNUM_NEGATIVE_SUFFIX);
    }

    public String getPositivePrefix() {
        if (posPrefNull) {
            return null;
        }
        return getTextAttribute(this.address, UNUM_POSITIVE_PREFIX);
    }

    public String getPositiveSuffix() {
        if (posSuffNull) {
            return null;
        }
        return getTextAttribute(this.address, UNUM_POSITIVE_SUFFIX);
    }

    public boolean isDecimalSeparatorAlwaysShown() {
        return getAttribute(this.address, UNUM_DECIMAL_ALWAYS_SHOWN) != 0;
    }

    public boolean isParseBigDecimal() {
        return parseBigDecimal;
    }

    public boolean isParseIntegerOnly() {
        return getAttribute(this.address, UNUM_PARSE_INT_ONLY) != 0;
    }

    public boolean isGroupingUsed() {
        return getAttribute(this.address, UNUM_GROUPING_USED) != 0;
    }

    public void setDecimalSeparatorAlwaysShown(boolean value) {
        int i = value ? -1 : 0;
        setAttribute(this.address, UNUM_DECIMAL_ALWAYS_SHOWN, i);
    }

    public void setCurrency(Currency currency) {
        setSymbol(this.address, UNUM_CURRENCY_SYMBOL, currency.getSymbol());
        setSymbol(this.address, UNUM_INTL_CURRENCY_SYMBOL, currency.getCurrencyCode());
    }

    public void setGroupingSize(int value) {
        setAttribute(this.address, UNUM_GROUPING_SIZE, value);
    }

    public void setGroupingUsed(boolean value) {
        int i = value ? -1 : 0;
        setAttribute(this.address, UNUM_GROUPING_USED, i);
    }

    public void setMaximumFractionDigits(int value) {
        setAttribute(this.address, UNUM_MAX_FRACTION_DIGITS, value);
    }

    public void setMaximumIntegerDigits(int value) {
        setAttribute(this.address, UNUM_MAX_INTEGER_DIGITS, value);
    }

    public void setMinimumFractionDigits(int value) {
        setAttribute(this.address, UNUM_MIN_FRACTION_DIGITS, value);
    }

    public void setMinimumIntegerDigits(int value) {
        setAttribute(this.address, UNUM_MIN_INTEGER_DIGITS, value);
    }

    public void setMultiplier(int value) {
        setAttribute(this.address, UNUM_MULTIPLIER, value);
        // Update the cached BigDecimal for multiplier.
        multiplierBigDecimal = BigDecimal.valueOf(value);
    }

    public void setNegativePrefix(String value) {
        negPrefNull = value == null;
        if (!negPrefNull) {
            setTextAttribute(this.address, UNUM_NEGATIVE_PREFIX, value);
        }
    }

    public void setNegativeSuffix(String value) {
        negSuffNull = value == null;
        if (!negSuffNull) {
            setTextAttribute(this.address, UNUM_NEGATIVE_SUFFIX, value);
        }
    }

    public void setPositivePrefix(String value) {
        posPrefNull = value == null;
        if (!posPrefNull) {
            setTextAttribute(this.address, UNUM_POSITIVE_PREFIX, value);
        }
    }

    public void setPositiveSuffix(String value) {
        posSuffNull = value == null;
        if (!posSuffNull) {
            setTextAttribute(this.address, UNUM_POSITIVE_SUFFIX, value);
        }
    }

    public void setParseBigDecimal(boolean value) {
        parseBigDecimal = value;
    }

    public void setParseIntegerOnly(boolean value) {
        int i = value ? -1 : 0;
        setAttribute(this.address, UNUM_PARSE_INT_ONLY, i);
    }

    private static void applyPattern(int addr, boolean localized, String pattern) {
        try {
            applyPatternImpl(addr, localized, pattern);
        } catch (NullPointerException npe) {
            throw npe;
        } catch (RuntimeException re) {
            throw new IllegalArgumentException("syntax error: " + re.getMessage() + ": " + pattern);
        }
    }

    public void setRoundingMode(RoundingMode roundingMode, double roundingIncrement) {
        final int nativeRoundingMode;
        switch (roundingMode) {
        case CEILING: nativeRoundingMode = 0; break;
        case FLOOR: nativeRoundingMode = 1; break;
        case DOWN: nativeRoundingMode = 2; break;
        case UP: nativeRoundingMode = 3; break;
        case HALF_EVEN: nativeRoundingMode = 4; break;
        case HALF_DOWN: nativeRoundingMode = 5; break;
        case HALF_UP: nativeRoundingMode = 6; break;
        default: throw new AssertionError();
        }
        setRoundingMode(address, nativeRoundingMode, roundingIncrement);
    }

    // Utility to get information about field positions from native (ICU) code.
    private static class FieldPositionIterator {
        private int[] data;
        private int pos = -3; // so first call to next() leaves pos at 0

        private FieldPositionIterator() {
        }

        public static FieldPositionIterator forFieldPosition(FieldPosition fp) {
            if (fp != null && fp.getField() != -1) {
                return new FieldPositionIterator();
            }
            return null;
        }

        private static int getNativeFieldPositionId(FieldPosition fp) {
            // NOTE: -1, 0, and 1 were the only valid original java field values
            // for NumberFormat.  They take precedence.  This assumes any other
            // value is a mistake and the actual value is in the attribute.
            // Clients can construct FieldPosition combining any attribute with any field
            // value, which is just wrong, but there you go.

            int id = fp.getField();
            if (id < -1 || id > 1) {
                id = -1;
            }
            if (id == -1) {
                Format.Field attr = fp.getFieldAttribute();
                if (attr != null) {
                    for (int i = 0; i < fields.length; ++i) {
                        if (fields[i].equals(attr)) {
                            id = i;
                            break;
                        }
                    }
                }
            }
            return id;
        }

        private static void setFieldPosition(FieldPositionIterator fpi, FieldPosition fp) {
            if (fpi != null && fp != null) {
                int field = getNativeFieldPositionId(fp);
                if (field != -1) {
                    while (fpi.next()) {
                        if (fpi.fieldId() == field) {
                            fp.setBeginIndex(fpi.start());
                            fp.setEndIndex(fpi.limit());
                            break;
                        }
                    }
                }
            }
        }

        public boolean next() {
            // if pos == data.length, we've already returned false once
            if (data == null || pos == data.length) {
                throw new NoSuchElementException();
            }
            pos += 3;
            return pos < data.length;
        }

        private void checkValid() {
            if (data == null || pos < 0 || pos == data.length) {
                throw new NoSuchElementException();
            }
        }

        public int fieldId() {
            return data[pos];
        }

        public Format.Field field() {
            checkValid();
            return fields[data[pos]];
        }

        public int start() {
            checkValid();
            return data[pos + 1];
        }

        public int limit() {
            checkValid();
            return data[pos + 2];
        }

        private static Format.Field fields[] = {
            // The old java field values were 0 for integer and 1 for fraction.
            // The new java field attributes are all objects.  ICU assigns the values
            // starting from 0 in the following order; note that integer and
            // fraction positions match the old field values.
            NumberFormat.Field.INTEGER,
            NumberFormat.Field.FRACTION,
            NumberFormat.Field.DECIMAL_SEPARATOR,
            NumberFormat.Field.EXPONENT_SYMBOL,
            NumberFormat.Field.EXPONENT_SIGN,
            NumberFormat.Field.EXPONENT,
            NumberFormat.Field.GROUPING_SEPARATOR,
            NumberFormat.Field.CURRENCY,
            NumberFormat.Field.PERCENT,
            NumberFormat.Field.PERMILLE,
            NumberFormat.Field.SIGN,
        };

        // called by native
        private void setData(int[] data) {
            this.data = data;
            this.pos = -3;
        }
    }

    private static native void applyPatternImpl(int addr, boolean localized, String pattern);
    private static native int cloneImpl(int addr);
    private static native void close(int addr);
    private static native char[] formatLong(int addr, long value, FieldPositionIterator iter);
    private static native char[] formatDouble(int addr, double value, FieldPositionIterator iter);
    private static native char[] formatDigitList(int addr, String value, FieldPositionIterator iter);
    private static native int getAttribute(int addr, int symbol);
    private static native String getTextAttribute(int addr, int symbol);
    private static native int open(String pattern, String currencySymbol,
            char decimalSeparator, char digit, String exponentSeparator, char groupingSeparator,
            String infinity, String internationalCurrencySymbol, char minusSign,
            char monetaryDecimalSeparator, String nan, char patternSeparator, char percent,
            char perMill, char zeroDigit);
    private static native Number parse(int addr, String string, ParsePosition position, boolean parseBigDecimal);
    private static native void setDecimalFormatSymbols(int addr, String currencySymbol,
            char decimalSeparator, char digit, String exponentSeparator, char groupingSeparator,
            String infinity, String internationalCurrencySymbol, char minusSign,
            char monetaryDecimalSeparator, String nan, char patternSeparator, char percent,
            char perMill, char zeroDigit);
    private static native void setSymbol(int addr, int symbol, String str);
    private static native void setAttribute(int addr, int symbol, int i);
    private static native void setRoundingMode(int addr, int roundingMode, double roundingIncrement);
    private static native void setTextAttribute(int addr, int symbol, String str);
    private static native String toPatternImpl(int addr, boolean localized);
}
