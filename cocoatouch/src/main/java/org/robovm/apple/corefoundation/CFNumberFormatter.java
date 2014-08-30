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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNumberFormatter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNumberFormatterPtr extends Ptr<CFNumberFormatter, CFNumberFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNumberFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNumberFormatter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFNumberFormatterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNumberFormatterCreate", optional=true)
    public static native CFNumberFormatter create(CFAllocator allocator, CFLocale locale, CFNumberFormatterStyle style);
    @Bridge(symbol="CFNumberFormatterGetLocale", optional=true)
    public native CFLocale getLocale();
    @Bridge(symbol="CFNumberFormatterGetStyle", optional=true)
    public native CFNumberFormatterStyle getStyle();
    @Bridge(symbol="CFNumberFormatterGetFormat", optional=true)
    public native CFString getFormat();
    @Bridge(symbol="CFNumberFormatterSetFormat", optional=true)
    public native void setFormat(CFString formatString);
    @Bridge(symbol="CFNumberFormatterCreateStringWithNumber", optional=true)
    public static native CFString createStringWithNumber(CFAllocator allocator, CFNumberFormatter formatter, CFNumber number);
    @Bridge(symbol="CFNumberFormatterCreateStringWithValue", optional=true)
    public static native CFString createStringWithValue(CFAllocator allocator, CFNumberFormatter formatter, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterCreateNumberFromString", optional=true)
    public static native CFNumber createNumberFromString(CFAllocator allocator, CFNumberFormatter formatter, CFString string, CFRange rangep, CFNumberFormatterOptionFlags options);
    @Bridge(symbol="CFNumberFormatterGetValueFromString", optional=true)
    public native boolean getValueFromString(CFString string, CFRange rangep, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterSetProperty", optional=true)
    public native void setProperty(CFString key, CFType value);
    @Bridge(symbol="CFNumberFormatterCopyProperty", optional=true)
    public native CFType copyProperty(CFString key);
    @Bridge(symbol="CFNumberFormatterGetDecimalInfoForCurrencyCode", optional=true)
    public static native boolean getDecimalInfoForCurrencyCode(CFString currencyCode, IntPtr defaultFractionDigits, DoublePtr roundingIncrement);
    /*</methods>*/
}
