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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CFCalendarIdentifier.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFCalendarIdentifier/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFCalendarIdentifier toObject(Class<CFCalendarIdentifier> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFCalendarIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFCalendarIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFCalendarIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFCalendarIdentifier Gregorian = new CFCalendarIdentifier("GregorianValue");
    public static final CFCalendarIdentifier Buddhist = new CFCalendarIdentifier("BuddhistValue");
    public static final CFCalendarIdentifier Chinese = new CFCalendarIdentifier("ChineseValue");
    public static final CFCalendarIdentifier Hebrew = new CFCalendarIdentifier("HebrewValue");
    public static final CFCalendarIdentifier Islamic = new CFCalendarIdentifier("IslamicValue");
    public static final CFCalendarIdentifier IslamicCivil = new CFCalendarIdentifier("IslamicCivilValue");
    public static final CFCalendarIdentifier Japanese = new CFCalendarIdentifier("JapaneseValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFCalendarIdentifier RepublicOfChina = new CFCalendarIdentifier("RepublicOfChinaValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFCalendarIdentifier Persian = new CFCalendarIdentifier("PersianValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFCalendarIdentifier Indian = new CFCalendarIdentifier("IndianValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFCalendarIdentifier ISO8601 = new CFCalendarIdentifier("ISO8601Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CFCalendarIdentifier IslamicTabular = new CFCalendarIdentifier("IslamicTabularValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CFCalendarIdentifier IslamicUmmAlQura = new CFCalendarIdentifier("IslamicUmmAlQuraValue");
    
    private static CFCalendarIdentifier[] values = new CFCalendarIdentifier[] {Gregorian, Buddhist, Chinese, Hebrew, Islamic, 
        IslamicCivil, Japanese, RepublicOfChina, Persian, Indian, ISO8601, IslamicTabular, IslamicUmmAlQura};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFCalendarIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFCalendarIdentifier valueOf(CFString value) {
        for (CFCalendarIdentifier v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFCalendarIdentifier/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFGregorianCalendar", optional=true)
    protected static native CFString GregorianValue();
    @GlobalValue(symbol="kCFBuddhistCalendar", optional=true)
    protected static native CFString BuddhistValue();
    @GlobalValue(symbol="kCFChineseCalendar", optional=true)
    protected static native CFString ChineseValue();
    @GlobalValue(symbol="kCFHebrewCalendar", optional=true)
    protected static native CFString HebrewValue();
    @GlobalValue(symbol="kCFIslamicCalendar", optional=true)
    protected static native CFString IslamicValue();
    @GlobalValue(symbol="kCFIslamicCivilCalendar", optional=true)
    protected static native CFString IslamicCivilValue();
    @GlobalValue(symbol="kCFJapaneseCalendar", optional=true)
    protected static native CFString JapaneseValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFRepublicOfChinaCalendar", optional=true)
    protected static native CFString RepublicOfChinaValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFPersianCalendar", optional=true)
    protected static native CFString PersianValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFIndianCalendar", optional=true)
    protected static native CFString IndianValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFISO8601Calendar", optional=true)
    protected static native CFString ISO8601Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCFIslamicTabularCalendar", optional=true)
    protected static native CFString IslamicTabularValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCFIslamicUmmAlQuraCalendar", optional=true)
    protected static native CFString IslamicUmmAlQuraValue();
    /*</methods>*/
}
