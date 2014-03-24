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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDecimalNumber/*</name>*/ 
    extends /*<extends>*/NSNumber/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDecimalNumberPtr extends Ptr<NSDecimalNumber, NSDecimalNumberPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDecimalNumber.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSDecimalNumber() {}
    protected NSDecimalNumber(SkipInit skipInit) { super(skipInit); }
    public NSDecimalNumber(long mantissa, short exponent, boolean flag) { super((SkipInit) null); initObject(initWithMantissa$exponent$isNegative$(mantissa, exponent, flag)); }
    public NSDecimalNumber(@ByVal NSDecimal dcm) { super((SkipInit) null); initObject(initWithDecimal$(dcm)); }
    public NSDecimalNumber(String numberValue) { super((SkipInit) null); initObject(initWithString$(numberValue)); }
    public NSDecimalNumber(String numberValue, NSDecimalNumber locale) { super((SkipInit) null); initObject(initWithString$locale$(numberValue, locale)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithMantissa:exponent:isNegative:")
    protected native @Pointer long initWithMantissa$exponent$isNegative$(long mantissa, short exponent, boolean flag);
    @Method(selector = "initWithDecimal:")
    protected native @Pointer long initWithDecimal$(@ByVal NSDecimal dcm);
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String numberValue);
    @Method(selector = "initWithString:locale:")
    protected native @Pointer long initWithString$locale$(String numberValue, NSDecimalNumber locale);
    @Method(selector = "descriptionWithLocale:")
    public native String descriptionWithLocale$(NSObject locale);
    @Method(selector = "decimalValue")
    public native @ByVal NSDecimal decimalValue();
    @Method(selector = "decimalNumberByAdding:")
    public native NSDecimalNumber decimalNumberByAdding$(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberByAdding:withBehavior:")
    public native NSDecimalNumber decimalNumberByAdding$withBehavior$(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberBySubtracting:")
    public native NSDecimalNumber decimalNumberBySubtracting$(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberBySubtracting:withBehavior:")
    public native NSDecimalNumber decimalNumberBySubtracting$withBehavior$(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByMultiplyingBy:")
    public native NSDecimalNumber decimalNumberByMultiplyingBy$(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberByMultiplyingBy:withBehavior:")
    public native NSDecimalNumber decimalNumberByMultiplyingBy$withBehavior$(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByDividingBy:")
    public native NSDecimalNumber decimalNumberByDividingBy$(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberByDividingBy:withBehavior:")
    public native NSDecimalNumber decimalNumberByDividingBy$withBehavior$(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByRaisingToPower:")
    public native NSDecimalNumber decimalNumberByRaisingToPower$(@MachineSizedUInt long power);
    @Method(selector = "decimalNumberByRaisingToPower:withBehavior:")
    public native NSDecimalNumber decimalNumberByRaisingToPower$withBehavior$(@MachineSizedUInt long power, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByMultiplyingByPowerOf10:")
    public native NSDecimalNumber decimalNumberByMultiplyingByPowerOf10$(short power);
    @Method(selector = "decimalNumberByMultiplyingByPowerOf10:withBehavior:")
    public native NSDecimalNumber decimalNumberByMultiplyingByPowerOf10$withBehavior$(short power, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByRoundingAccordingToBehavior:")
    public native NSDecimalNumber decimalNumberByRoundingAccordingToBehavior$(NSDecimalNumberBehaviors behavior);
    @Method(selector = "compare:")
    public native NSComparisonResult compare$(NSNumber decimalNumber);
    @Method(selector = "objCType")
    public native BytePtr objCType();
    @Method(selector = "doubleValue")
    public native double doubleValue();
    @Method(selector = "decimalNumberWithMantissa:exponent:isNegative:")
    public static native NSDecimalNumber decimalNumberWithMantissa$exponent$isNegative$(long mantissa, short exponent, boolean flag);
    @Method(selector = "decimalNumberWithDecimal:")
    public static native NSDecimalNumber decimalNumberWithDecimal$(@ByVal NSDecimal dcm);
    @Method(selector = "decimalNumberWithString:")
    public static native NSDecimalNumber decimalNumberWithString$(String numberValue);
    @Method(selector = "decimalNumberWithString:locale:")
    public static native NSDecimalNumber decimalNumberWithString$locale$(String numberValue, NSObject locale);
    @Method(selector = "zero")
    public static native NSDecimalNumber zero();
    @Method(selector = "one")
    public static native NSDecimalNumber one();
    @Method(selector = "minimumDecimalNumber")
    public static native NSDecimalNumber minimumDecimalNumber();
    @Method(selector = "maximumDecimalNumber")
    public static native NSDecimalNumber maximumDecimalNumber();
    @Method(selector = "notANumber")
    public static native NSDecimalNumber notANumber();
    @Method(selector = "setDefaultBehavior:")
    public static native void setDefaultBehavior$(NSDecimalNumberBehaviors behavior);
    @Method(selector = "defaultBehavior")
    public static native NSDecimalNumberBehaviors defaultBehavior();
    /*</methods>*/
}
