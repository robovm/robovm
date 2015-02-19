/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.foundation.*;
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
    public static CFNumberFormatter create(CFLocale locale, CFNumberFormatterStyle style) {
        return create(null, locale, style);
    }
    
    public String format(CFNumber number) {
        return format(null, this, number);
    }
    public CFNumber parse(String string, CFRange rangep, CFNumberFormatterOptionFlags options) {
        return parse(null, this, string, rangep, options);
    }
    public static int getDefaultFractionDigitsForCurrencyCode(String currencyCode) {
        IntPtr ptr = new IntPtr();
        getDecimalInfoForCurrencyCode(currencyCode, ptr, new DoublePtr());
        return ptr.get();
    }
    public static double getRoundingIncrementForCurrencyCode(String currencyCode) {
        DoublePtr ptr = new DoublePtr();
        getDecimalInfoForCurrencyCode(currencyCode, new IntPtr(), ptr);
        return ptr.get();
    }
    /*<methods>*/
    @Bridge(symbol="CFNumberFormatterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNumberFormatterCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFNumberFormatter create(CFAllocator allocator, CFLocale locale, CFNumberFormatterStyle style);
    @Bridge(symbol="CFNumberFormatterGetLocale", optional=true)
    public native CFLocale getLocale();
    @Bridge(symbol="CFNumberFormatterGetStyle", optional=true)
    public native CFNumberFormatterStyle getStyle();
    @Bridge(symbol="CFNumberFormatterGetFormat", optional=true)
    public native String getFormat();
    @Bridge(symbol="CFNumberFormatterSetFormat", optional=true)
    public native void setFormat(String formatString);
    @Bridge(symbol="CFNumberFormatterCreateStringWithNumber", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String format(CFAllocator allocator, CFNumberFormatter formatter, CFNumber number);
    @Bridge(symbol="CFNumberFormatterCreateStringWithValue", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String formatValue(CFAllocator allocator, CFNumberFormatter formatter, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterCreateNumberFromString", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFNumber parse(CFAllocator allocator, CFNumberFormatter formatter, String string, CFRange rangep, CFNumberFormatterOptionFlags options);
    @Bridge(symbol="CFNumberFormatterGetValueFromString", optional=true)
    protected native boolean parseValue(String string, CFRange rangep, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterSetProperty", optional=true)
    public native void setProperty(CFNumberFormatterProperty key, CFType value);
    @Bridge(symbol="CFNumberFormatterCopyProperty", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getProperty(CFNumberFormatterProperty key);
    @Bridge(symbol="CFNumberFormatterGetDecimalInfoForCurrencyCode", optional=true)
    private static native boolean getDecimalInfoForCurrencyCode(String currencyCode, IntPtr defaultFractionDigits, DoublePtr roundingIncrement);
    /*</methods>*/
}
