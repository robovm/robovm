/*
 * Copyright (C) 2014 Trillian AB
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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    @Method(selector = "getObjectValue:forString:range:error:")
    public native boolean getObjectValue$forString$range$error$(NSObject obj, String string, NSRange rangep, NSError.NSErrorPtr error);
    @Method(selector = "stringFromNumber:")
    public native String stringFromNumber$(NSNumber number);
    @Method(selector = "numberFromString:")
    public native NSNumber numberFromString$(String string);
    @Method(selector = "numberStyle")
    public native NSNumberFormatterStyle numberStyle();
    @Method(selector = "setNumberStyle:")
    public native void setNumberStyle$(NSNumberFormatterStyle style);
    @Method(selector = "locale")
    public native NSLocale locale();
    @Method(selector = "setLocale:")
    public native void setLocale$(NSLocale locale);
    @Method(selector = "generatesDecimalNumbers")
    public native boolean generatesDecimalNumbers();
    @Method(selector = "setGeneratesDecimalNumbers:")
    public native void setGeneratesDecimalNumbers$(boolean b);
    @Method(selector = "formatterBehavior")
    public native NSNumberFormatterBehavior formatterBehavior();
    @Method(selector = "setFormatterBehavior:")
    public native void setFormatterBehavior$(NSNumberFormatterBehavior behavior);
    @Method(selector = "negativeFormat")
    public native String negativeFormat();
    @Method(selector = "setNegativeFormat:")
    public native void setNegativeFormat$(String format);
    @Method(selector = "textAttributesForNegativeValues")
    public native NSDictionary<?, ?> textAttributesForNegativeValues();
    @Method(selector = "setTextAttributesForNegativeValues:")
    public native void setTextAttributesForNegativeValues$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "positiveFormat")
    public native String positiveFormat();
    @Method(selector = "setPositiveFormat:")
    public native void setPositiveFormat$(String format);
    @Method(selector = "textAttributesForPositiveValues")
    public native NSDictionary<?, ?> textAttributesForPositiveValues();
    @Method(selector = "setTextAttributesForPositiveValues:")
    public native void setTextAttributesForPositiveValues$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "allowsFloats")
    public native boolean allowsFloats();
    @Method(selector = "setAllowsFloats:")
    public native void setAllowsFloats$(boolean flag);
    @Method(selector = "decimalSeparator")
    public native String decimalSeparator();
    @Method(selector = "setDecimalSeparator:")
    public native void setDecimalSeparator$(String string);
    @Method(selector = "alwaysShowsDecimalSeparator")
    public native boolean alwaysShowsDecimalSeparator();
    @Method(selector = "setAlwaysShowsDecimalSeparator:")
    public native void setAlwaysShowsDecimalSeparator$(boolean b);
    @Method(selector = "currencyDecimalSeparator")
    public native String currencyDecimalSeparator();
    @Method(selector = "setCurrencyDecimalSeparator:")
    public native void setCurrencyDecimalSeparator$(String string);
    @Method(selector = "usesGroupingSeparator")
    public native boolean usesGroupingSeparator();
    @Method(selector = "setUsesGroupingSeparator:")
    public native void setUsesGroupingSeparator$(boolean b);
    @Method(selector = "groupingSeparator")
    public native String groupingSeparator();
    @Method(selector = "setGroupingSeparator:")
    public native void setGroupingSeparator$(String string);
    @Method(selector = "zeroSymbol")
    public native String zeroSymbol();
    @Method(selector = "setZeroSymbol:")
    public native void setZeroSymbol$(String string);
    @Method(selector = "textAttributesForZero")
    public native NSDictionary<?, ?> textAttributesForZero();
    @Method(selector = "setTextAttributesForZero:")
    public native void setTextAttributesForZero$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "nilSymbol")
    public native String nilSymbol();
    @Method(selector = "setNilSymbol:")
    public native void setNilSymbol$(String string);
    @Method(selector = "textAttributesForNil")
    public native NSDictionary<?, ?> textAttributesForNil();
    @Method(selector = "setTextAttributesForNil:")
    public native void setTextAttributesForNil$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "notANumberSymbol")
    public native String notANumberSymbol();
    @Method(selector = "setNotANumberSymbol:")
    public native void setNotANumberSymbol$(String string);
    @Method(selector = "textAttributesForNotANumber")
    public native NSDictionary<?, ?> textAttributesForNotANumber();
    @Method(selector = "setTextAttributesForNotANumber:")
    public native void setTextAttributesForNotANumber$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "positiveInfinitySymbol")
    public native String positiveInfinitySymbol();
    @Method(selector = "setPositiveInfinitySymbol:")
    public native void setPositiveInfinitySymbol$(String string);
    @Method(selector = "textAttributesForPositiveInfinity")
    public native NSDictionary<?, ?> textAttributesForPositiveInfinity();
    @Method(selector = "setTextAttributesForPositiveInfinity:")
    public native void setTextAttributesForPositiveInfinity$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "negativeInfinitySymbol")
    public native String negativeInfinitySymbol();
    @Method(selector = "setNegativeInfinitySymbol:")
    public native void setNegativeInfinitySymbol$(String string);
    @Method(selector = "textAttributesForNegativeInfinity")
    public native NSDictionary<?, ?> textAttributesForNegativeInfinity();
    @Method(selector = "setTextAttributesForNegativeInfinity:")
    public native void setTextAttributesForNegativeInfinity$(NSDictionary<?, ?> newAttributes);
    @Method(selector = "positivePrefix")
    public native String positivePrefix();
    @Method(selector = "setPositivePrefix:")
    public native void setPositivePrefix$(String string);
    @Method(selector = "positiveSuffix")
    public native String positiveSuffix();
    @Method(selector = "setPositiveSuffix:")
    public native void setPositiveSuffix$(String string);
    @Method(selector = "negativePrefix")
    public native String negativePrefix();
    @Method(selector = "setNegativePrefix:")
    public native void setNegativePrefix$(String string);
    @Method(selector = "negativeSuffix")
    public native String negativeSuffix();
    @Method(selector = "setNegativeSuffix:")
    public native void setNegativeSuffix$(String string);
    @Method(selector = "currencyCode")
    public native String currencyCode();
    @Method(selector = "setCurrencyCode:")
    public native void setCurrencyCode$(String string);
    @Method(selector = "currencySymbol")
    public native String currencySymbol();
    @Method(selector = "setCurrencySymbol:")
    public native void setCurrencySymbol$(String string);
    @Method(selector = "internationalCurrencySymbol")
    public native String internationalCurrencySymbol();
    @Method(selector = "setInternationalCurrencySymbol:")
    public native void setInternationalCurrencySymbol$(String string);
    @Method(selector = "percentSymbol")
    public native String percentSymbol();
    @Method(selector = "setPercentSymbol:")
    public native void setPercentSymbol$(String string);
    @Method(selector = "perMillSymbol")
    public native String perMillSymbol();
    @Method(selector = "setPerMillSymbol:")
    public native void setPerMillSymbol$(String string);
    @Method(selector = "minusSign")
    public native String minusSign();
    @Method(selector = "setMinusSign:")
    public native void setMinusSign$(String string);
    @Method(selector = "plusSign")
    public native String plusSign();
    @Method(selector = "setPlusSign:")
    public native void setPlusSign$(String string);
    @Method(selector = "exponentSymbol")
    public native String exponentSymbol();
    @Method(selector = "setExponentSymbol:")
    public native void setExponentSymbol$(String string);
    @Method(selector = "groupingSize")
    public native @MachineSizedUInt long groupingSize();
    @Method(selector = "setGroupingSize:")
    public native void setGroupingSize$(@MachineSizedUInt long number);
    @Method(selector = "secondaryGroupingSize")
    public native @MachineSizedUInt long secondaryGroupingSize();
    @Method(selector = "setSecondaryGroupingSize:")
    public native void setSecondaryGroupingSize$(@MachineSizedUInt long number);
    @Method(selector = "multiplier")
    public native NSNumber multiplier();
    @Method(selector = "setMultiplier:")
    public native void setMultiplier$(NSNumber number);
    @Method(selector = "formatWidth")
    public native @MachineSizedUInt long formatWidth();
    @Method(selector = "setFormatWidth:")
    public native void setFormatWidth$(@MachineSizedUInt long number);
    @Method(selector = "paddingCharacter")
    public native String paddingCharacter();
    @Method(selector = "setPaddingCharacter:")
    public native void setPaddingCharacter$(String string);
    @Method(selector = "paddingPosition")
    public native NSNumberFormatterPadPosition paddingPosition();
    @Method(selector = "setPaddingPosition:")
    public native void setPaddingPosition$(NSNumberFormatterPadPosition position);
    @Method(selector = "roundingMode")
    public native NSNumberFormatterRoundingMode roundingMode();
    @Method(selector = "setRoundingMode:")
    public native void setRoundingMode$(NSNumberFormatterRoundingMode mode);
    @Method(selector = "roundingIncrement")
    public native NSNumber roundingIncrement();
    @Method(selector = "setRoundingIncrement:")
    public native void setRoundingIncrement$(NSNumber number);
    @Method(selector = "minimumIntegerDigits")
    public native @MachineSizedUInt long minimumIntegerDigits();
    @Method(selector = "setMinimumIntegerDigits:")
    public native void setMinimumIntegerDigits$(@MachineSizedUInt long number);
    @Method(selector = "maximumIntegerDigits")
    public native @MachineSizedUInt long maximumIntegerDigits();
    @Method(selector = "setMaximumIntegerDigits:")
    public native void setMaximumIntegerDigits$(@MachineSizedUInt long number);
    @Method(selector = "minimumFractionDigits")
    public native @MachineSizedUInt long minimumFractionDigits();
    @Method(selector = "setMinimumFractionDigits:")
    public native void setMinimumFractionDigits$(@MachineSizedUInt long number);
    @Method(selector = "maximumFractionDigits")
    public native @MachineSizedUInt long maximumFractionDigits();
    @Method(selector = "setMaximumFractionDigits:")
    public native void setMaximumFractionDigits$(@MachineSizedUInt long number);
    @Method(selector = "minimum")
    public native NSNumber minimum();
    @Method(selector = "setMinimum:")
    public native void setMinimum$(NSNumber number);
    @Method(selector = "maximum")
    public native NSNumber maximum();
    @Method(selector = "setMaximum:")
    public native void setMaximum$(NSNumber number);
    @Method(selector = "currencyGroupingSeparator")
    public native String currencyGroupingSeparator();
    @Method(selector = "setCurrencyGroupingSeparator:")
    public native void setCurrencyGroupingSeparator$(String string);
    @Method(selector = "isLenient")
    public native boolean isLenient();
    @Method(selector = "setLenient:")
    public native void setLenient$(boolean b);
    @Method(selector = "usesSignificantDigits")
    public native boolean usesSignificantDigits();
    @Method(selector = "setUsesSignificantDigits:")
    public native void setUsesSignificantDigits$(boolean b);
    @Method(selector = "minimumSignificantDigits")
    public native @MachineSizedUInt long minimumSignificantDigits();
    @Method(selector = "setMinimumSignificantDigits:")
    public native void setMinimumSignificantDigits$(@MachineSizedUInt long number);
    @Method(selector = "maximumSignificantDigits")
    public native @MachineSizedUInt long maximumSignificantDigits();
    @Method(selector = "setMaximumSignificantDigits:")
    public native void setMaximumSignificantDigits$(@MachineSizedUInt long number);
    @Method(selector = "isPartialStringValidationEnabled")
    public native boolean isPartialStringValidationEnabled();
    @Method(selector = "setPartialStringValidationEnabled:")
    public native void setPartialStringValidationEnabled$(boolean b);
    @Method(selector = "localizedStringFromNumber:numberStyle:")
    public static native String localizedStringFromNumber$numberStyle$(NSNumber num, NSNumberFormatterStyle nstyle);
    @Method(selector = "defaultFormatterBehavior")
    public static native NSNumberFormatterBehavior defaultFormatterBehavior();
    @Method(selector = "setDefaultFormatterBehavior:")
    public static native void setDefaultFormatterBehavior$(NSNumberFormatterBehavior behavior);
    /*</methods>*/
}
