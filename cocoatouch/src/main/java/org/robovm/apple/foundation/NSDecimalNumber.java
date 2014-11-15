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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDecimalNumber/*</name>*/ 
    extends /*<extends>*/NSNumber/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDecimalNumberPtr extends Ptr<NSDecimalNumber, NSDecimalNumberPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDecimalNumber.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSDecimalNumber(SkipInit skipInit) { super(skipInit); }
    public NSDecimalNumber(long mantissa, short exponent, boolean flag) { super((SkipInit) null); initObject(initWithMantissa$exponent$isNegative$(mantissa, exponent, flag)); }
    public NSDecimalNumber(@ByVal NSDecimal dcm) { super((SkipInit) null); initObject(initWithDecimal$(dcm)); }
    public NSDecimalNumber(String numberValue) { super((SkipInit) null); initObject(initWithString$(numberValue)); }
    public NSDecimalNumber(String numberValue, NSLocale locale) { super((SkipInit) null); initObject(initWithString$locale$(numberValue, locale)); }
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
    protected native @Pointer long initWithString$locale$(String numberValue, NSLocale locale);
    @Method(selector = "decimalNumberByAdding:")
    public native NSDecimalNumber add(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberByAdding:withBehavior:")
    public native NSDecimalNumber add(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberBySubtracting:")
    public native NSDecimalNumber subtract(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberBySubtracting:withBehavior:")
    public native NSDecimalNumber subtract(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByMultiplyingBy:")
    public native NSDecimalNumber multiply(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberByMultiplyingBy:withBehavior:")
    public native NSDecimalNumber multiply(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByDividingBy:")
    public native NSDecimalNumber divide(NSDecimalNumber decimalNumber);
    @Method(selector = "decimalNumberByDividingBy:withBehavior:")
    public native NSDecimalNumber divide(NSDecimalNumber decimalNumber, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByRaisingToPower:")
    public native NSDecimalNumber raise(@MachineSizedUInt long power);
    @Method(selector = "decimalNumberByRaisingToPower:withBehavior:")
    public native NSDecimalNumber raise(@MachineSizedUInt long power, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByMultiplyingByPowerOf10:")
    public native NSDecimalNumber multiplyByPowerOf10(short power);
    @Method(selector = "decimalNumberByMultiplyingByPowerOf10:withBehavior:")
    public native NSDecimalNumber multiplyByPowerOf10(short power, NSDecimalNumberBehaviors behavior);
    @Method(selector = "decimalNumberByRoundingAccordingToBehavior:")
    public native NSDecimalNumber round(NSDecimalNumberBehaviors behavior);
    @Method(selector = "zero")
    public static native NSDecimalNumber zero();
    @Method(selector = "one")
    public static native NSDecimalNumber one();
    @Method(selector = "minimumDecimalNumber")
    public static native NSDecimalNumber getMin();
    @Method(selector = "maximumDecimalNumber")
    public static native NSDecimalNumber getMax();
    @Method(selector = "notANumber")
    public static native NSDecimalNumber getNaN();
    @Method(selector = "setDefaultBehavior:")
    public static native void setDefaultBehavior(NSDecimalNumberBehaviors behavior);
    @Method(selector = "defaultBehavior")
    public static native NSDecimalNumberBehaviors getDefaultBehavior();
    /*</methods>*/
}
