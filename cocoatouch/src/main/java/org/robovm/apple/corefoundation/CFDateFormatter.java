/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDateFormatter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFDateFormatterPtr extends Ptr<CFDateFormatter, CFDateFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFDateFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFDateFormatter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static String getDateFormat(String tmplate, @MachineSizedUInt long options, CFLocale locale) {
        return getDateFormat(null, tmplate, options, locale);
    }
    public static CFDateFormatter create(CFLocale locale, CFDateFormatterStyle dateStyle, CFDateFormatterStyle timeStyle) {
        return create(null, locale, dateStyle, timeStyle);
    }
    public String format(CFDate date) {
        return format(null, this, date);
    }
    public String format(double at) {
        return format(null, this, at);
    }
    public CFDate parse(String string, CFRange rangep) {
        return parse(null, this, string, rangep);
    }
    public double getAbsoluteTime(String string, CFRange rangep) {
        DoublePtr ptr = new DoublePtr();
        getAbsoluteTime(string, rangep, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFDateFormatterCreateDateFormatFromTemplate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getDateFormat(CFAllocator allocator, String tmplate, @MachineSizedUInt long options, CFLocale locale);
    @Bridge(symbol="CFDateFormatterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDateFormatterCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDateFormatter create(CFAllocator allocator, CFLocale locale, CFDateFormatterStyle dateStyle, CFDateFormatterStyle timeStyle);
    @Bridge(symbol="CFDateFormatterGetLocale", optional=true)
    public native CFLocale getLocale();
    @Bridge(symbol="CFDateFormatterGetDateStyle", optional=true)
    public native CFDateFormatterStyle getDateStyle();
    @Bridge(symbol="CFDateFormatterGetTimeStyle", optional=true)
    public native CFDateFormatterStyle getTimeStyle();
    @Bridge(symbol="CFDateFormatterGetFormat", optional=true)
    public native String getFormat();
    @Bridge(symbol="CFDateFormatterSetFormat", optional=true)
    public native void setFormat(String formatString);
    @Bridge(symbol="CFDateFormatterCreateStringWithDate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String format(CFAllocator allocator, CFDateFormatter formatter, CFDate date);
    @Bridge(symbol="CFDateFormatterCreateStringWithAbsoluteTime", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String format(CFAllocator allocator, CFDateFormatter formatter, double at);
    @Bridge(symbol="CFDateFormatterCreateDateFromString", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDate parse(CFAllocator allocator, CFDateFormatter formatter, String string, CFRange rangep);
    @Bridge(symbol="CFDateFormatterGetAbsoluteTimeFromString", optional=true)
    private native boolean getAbsoluteTime(String string, CFRange rangep, DoublePtr atp);
    @Bridge(symbol="CFDateFormatterSetProperty", optional=true)
    public native void setProperty(CFDateFormatterProperty key, CFType value);
    @Bridge(symbol="CFDateFormatterCopyProperty", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getProperty(CFDateFormatterProperty key);
    /*</methods>*/
}
