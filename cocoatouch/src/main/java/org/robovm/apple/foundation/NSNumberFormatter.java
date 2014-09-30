/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNumberFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSNumberFormatterPtr extends Ptr<NSNumberFormatter, NSNumberFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSNumberFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSNumberFormatter() {}
    protected NSNumberFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringFromNumber:")
    public native String format(NSNumber number);
    @Method(selector = "numberFromString:")
    public native NSNumber parse(String string);
    @Method(selector = "numberStyle")
    public native NSNumberFormatterStyle getNumberStyle();
    @Method(selector = "setNumberStyle:")
    public native void setNumberStyle(NSNumberFormatterStyle style);
    @Method(selector = "locale")
    public native NSLocale getLocale();
    @Method(selector = "setLocale:")
    public native void setLocale(NSLocale locale);
    @Method(selector = "generatesDecimalNumbers")
    public native boolean generatesDecimalNumbers();
    @Method(selector = "setGeneratesDecimalNumbers:")
    public native void setGeneratesDecimalNumbers(boolean b);
    @Method(selector = "formatterBehavior")
    public native NSNumberFormatterBehavior getFormatterBehavior();
    @Method(selector = "setFormatterBehavior:")
    public native void setFormatterBehavior(NSNumberFormatterBehavior behavior);
    @Method(selector = "negativeFormat")
    public native String getNegativeFormat();
    @Method(selector = "setNegativeFormat:")
    public native void setNegativeFormat(String format);
    @Method(selector = "textAttributesForNegativeValues")
    public native NSAttributedStringAttributes getTextAttributesForNegativeValues();
    @Method(selector = "setTextAttributesForNegativeValues:")
    public native void setTextAttributesForNegativeValues(NSAttributedStringAttributes newAttributes);
    @Method(selector = "positiveFormat")
    public native String getPositiveFormat();
    @Method(selector = "setPositiveFormat:")
    public native void setPositiveFormat(String format);
    @Method(selector = "textAttributesForPositiveValues")
    public native NSAttributedStringAttributes getTextAttributesForPositiveValues();
    @Method(selector = "setTextAttributesForPositiveValues:")
    public native void setTextAttributesForPositiveValues(NSAttributedStringAttributes newAttributes);
    @Method(selector = "allowsFloats")
    public native boolean allowsFloats();
    @Method(selector = "setAllowsFloats:")
    public native void setAllowsFloats(boolean flag);
    @Method(selector = "decimalSeparator")
    public native String getDecimalSeparator();
    @Method(selector = "setDecimalSeparator:")
    public native void setDecimalSeparator(String string);
    @Method(selector = "alwaysShowsDecimalSeparator")
    public native boolean alwaysShowsDecimalSeparator();
    @Method(selector = "setAlwaysShowsDecimalSeparator:")
    public native void setAlwaysShowsDecimalSeparator(boolean b);
    @Method(selector = "currencyDecimalSeparator")
    public native String getCurrencyDecimalSeparator();
    @Method(selector = "setCurrencyDecimalSeparator:")
    public native void setCurrencyDecimalSeparator(String string);
    @Method(selector = "usesGroupingSeparator")
    public native boolean usesGroupingSeparator();
    @Method(selector = "setUsesGroupingSeparator:")
    public native void setUsesGroupingSeparator(boolean b);
    @Method(selector = "groupingSeparator")
    public native String getGroupingSeparator();
    @Method(selector = "setGroupingSeparator:")
    public native void setGroupingSeparator(String string);
    @Method(selector = "zeroSymbol")
    public native String getZeroSymbol();
    @Method(selector = "setZeroSymbol:")
    public native void setZeroSymbol(String string);
    @Method(selector = "textAttributesForZero")
    public native NSAttributedStringAttributes getTextAttributesForZero();
    @Method(selector = "setTextAttributesForZero:")
    public native void setTextAttributesForZero(NSAttributedStringAttributes newAttributes);
    @Method(selector = "nilSymbol")
    public native String getNullSymbol();
    @Method(selector = "setNilSymbol:")
    public native void setNullSymbol(String string);
    @Method(selector = "textAttributesForNil")
    public native NSAttributedStringAttributes getTextAttributesForNull();
    @Method(selector = "setTextAttributesForNil:")
    public native void setTextAttributesForNull(NSAttributedStringAttributes newAttributes);
    @Method(selector = "notANumberSymbol")
    public native String getNaNSymbol();
    @Method(selector = "setNotANumberSymbol:")
    public native void setNaNSymbol(String string);
    @Method(selector = "textAttributesForNotANumber")
    public native NSAttributedStringAttributes getTextAttributesForNaN();
    @Method(selector = "setTextAttributesForNotANumber:")
    public native void setTextAttributesForNaN(NSAttributedStringAttributes newAttributes);
    @Method(selector = "positiveInfinitySymbol")
    public native String getPositiveInfinitySymbol();
    @Method(selector = "setPositiveInfinitySymbol:")
    public native void setPositiveInfinitySymbol(String string);
    @Method(selector = "textAttributesForPositiveInfinity")
    public native NSAttributedStringAttributes getTextAttributesForPositiveInfinity();
    @Method(selector = "setTextAttributesForPositiveInfinity:")
    public native void setTextAttributesForPositiveInfinity(NSAttributedStringAttributes newAttributes);
    @Method(selector = "negativeInfinitySymbol")
    public native String getNegativeInfinitySymbol();
    @Method(selector = "setNegativeInfinitySymbol:")
    public native void setNegativeInfinitySymbol(String string);
    @Method(selector = "textAttributesForNegativeInfinity")
    public native NSAttributedStringAttributes getTextAttributesForNegativeInfinity();
    @Method(selector = "setTextAttributesForNegativeInfinity:")
    public native void setTextAttributesForNegativeInfinity(NSAttributedStringAttributes newAttributes);
    @Method(selector = "positivePrefix")
    public native String getPositivePrefix();
    @Method(selector = "setPositivePrefix:")
    public native void setPositivePrefix(String string);
    @Method(selector = "positiveSuffix")
    public native String getPositiveSuffix();
    @Method(selector = "setPositiveSuffix:")
    public native void setPositiveSuffix(String string);
    @Method(selector = "negativePrefix")
    public native String getNegativePrefix();
    @Method(selector = "setNegativePrefix:")
    public native void setNegativePrefix(String string);
    @Method(selector = "negativeSuffix")
    public native String getNegativeSuffix();
    @Method(selector = "setNegativeSuffix:")
    public native void setNegativeSuffix(String string);
    @Method(selector = "currencyCode")
    public native String getCurrencyCode();
    @Method(selector = "setCurrencyCode:")
    public native void setCurrencyCode(String string);
    @Method(selector = "currencySymbol")
    public native String getCurrencySymbol();
    @Method(selector = "setCurrencySymbol:")
    public native void setCurrencySymbol(String string);
    @Method(selector = "internationalCurrencySymbol")
    public native String getInternationalCurrencySymbol();
    @Method(selector = "setInternationalCurrencySymbol:")
    public native void setInternationalCurrencySymbol(String string);
    @Method(selector = "percentSymbol")
    public native String getPercentSymbol();
    @Method(selector = "setPercentSymbol:")
    public native void setPercentSymbol(String string);
    @Method(selector = "perMillSymbol")
    public native String getPerMillSymbol();
    @Method(selector = "setPerMillSymbol:")
    public native void setPerMillSymbol(String string);
    @Method(selector = "minusSign")
    public native String getMinusSign();
    @Method(selector = "setMinusSign:")
    public native void setMinusSign(String string);
    @Method(selector = "plusSign")
    public native String getPlusSign();
    @Method(selector = "setPlusSign:")
    public native void setPlusSign(String string);
    @Method(selector = "exponentSymbol")
    public native String getExponentSymbol();
    @Method(selector = "setExponentSymbol:")
    public native void setExponentSymbol(String string);
    @Method(selector = "groupingSize")
    public native @MachineSizedUInt long getGroupingSize();
    @Method(selector = "setGroupingSize:")
    public native void setGroupingSize(@MachineSizedUInt long number);
    @Method(selector = "secondaryGroupingSize")
    public native @MachineSizedUInt long getSecondaryGroupingSize();
    @Method(selector = "setSecondaryGroupingSize:")
    public native void setSecondaryGroupingSize(@MachineSizedUInt long number);
    @Method(selector = "multiplier")
    public native NSNumber getMultiplier();
    @Method(selector = "setMultiplier:")
    public native void setMultiplier(NSNumber number);
    @Method(selector = "formatWidth")
    public native @MachineSizedUInt long getFormatWidth();
    @Method(selector = "setFormatWidth:")
    public native void setFormatWidth(@MachineSizedUInt long number);
    @Method(selector = "paddingCharacter")
    public native String getPaddingCharacter();
    @Method(selector = "setPaddingCharacter:")
    public native void setPaddingCharacter(String string);
    @Method(selector = "paddingPosition")
    public native NSNumberFormatterPadPosition getPaddingPosition();
    @Method(selector = "setPaddingPosition:")
    public native void setPaddingPosition(NSNumberFormatterPadPosition position);
    @Method(selector = "roundingMode")
    public native NSNumberFormatterRoundingMode getRoundingMode();
    @Method(selector = "setRoundingMode:")
    public native void setRoundingMode(NSNumberFormatterRoundingMode mode);
    @Method(selector = "roundingIncrement")
    public native NSNumber getRoundingIncrement();
    @Method(selector = "setRoundingIncrement:")
    public native void setRoundingIncrement(NSNumber number);
    @Method(selector = "minimumIntegerDigits")
    public native @MachineSizedUInt long getMinimumIntegerDigits();
    @Method(selector = "setMinimumIntegerDigits:")
    public native void setMinimumIntegerDigits(@MachineSizedUInt long number);
    @Method(selector = "maximumIntegerDigits")
    public native @MachineSizedUInt long getMaximumIntegerDigits();
    @Method(selector = "setMaximumIntegerDigits:")
    public native void setMaximumIntegerDigits(@MachineSizedUInt long number);
    @Method(selector = "minimumFractionDigits")
    public native @MachineSizedUInt long getMinimumFractionDigits();
    @Method(selector = "setMinimumFractionDigits:")
    public native void setMinimumFractionDigits(@MachineSizedUInt long number);
    @Method(selector = "maximumFractionDigits")
    public native @MachineSizedUInt long getMaximumFractionDigits();
    @Method(selector = "setMaximumFractionDigits:")
    public native void setMaximumFractionDigits(@MachineSizedUInt long number);
    @Method(selector = "minimum")
    public native NSNumber getMinimum();
    @Method(selector = "setMinimum:")
    public native void setMinimum(NSNumber number);
    @Method(selector = "maximum")
    public native NSNumber getMaximum();
    @Method(selector = "setMaximum:")
    public native void setMaximum(NSNumber number);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "currencyGroupingSeparator")
    public native String getCurrencyGroupingSeparator();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setCurrencyGroupingSeparator:")
    public native void setCurrencyGroupingSeparator(String string);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "isLenient")
    public native boolean isLenient();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setLenient:")
    public native void setLenient(boolean b);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "usesSignificantDigits")
    public native boolean usesSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setUsesSignificantDigits:")
    public native void setUsesSignificantDigits(boolean b);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "minimumSignificantDigits")
    public native @MachineSizedUInt long getMinimumSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setMinimumSignificantDigits:")
    public native void setMinimumSignificantDigits(@MachineSizedUInt long number);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "maximumSignificantDigits")
    public native @MachineSizedUInt long getMaximumSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setMaximumSignificantDigits:")
    public native void setMaximumSignificantDigits(@MachineSizedUInt long number);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "isPartialStringValidationEnabled")
    public native boolean isPartialStringValidationEnabled();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setPartialStringValidationEnabled:")
    public native void setPartialStringValidationEnabled(boolean b);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "localizedStringFromNumber:numberStyle:")
    public static native String formatLocalized(NSNumber num, NSNumberFormatterStyle nstyle);
    @Method(selector = "defaultFormatterBehavior")
    public static native NSNumberFormatterBehavior getDefaultFormatterBehavior();
    @Method(selector = "setDefaultFormatterBehavior:")
    public static native void setDefaultFormatterBehavior(NSNumberFormatterBehavior behavior);
    /*</methods>*/
}
