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
@Marshaler(NSCalendarIdentifier.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCalendarIdentifier/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSCalendarIdentifier toObject(Class<NSCalendarIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSCalendarIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSCalendarIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSCalendarIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Gregorian = new NSCalendarIdentifier("GregorianValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Buddhist = new NSCalendarIdentifier("BuddhistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Chinese = new NSCalendarIdentifier("ChineseValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Coptic = new NSCalendarIdentifier("CopticValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier EthiopicAmeteMihret = new NSCalendarIdentifier("EthiopicAmeteMihretValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier EthiopicAmeteAlem = new NSCalendarIdentifier("EthiopicAmeteAlemValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Hebrew = new NSCalendarIdentifier("HebrewValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier ISO8601 = new NSCalendarIdentifier("ISO8601Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Indian = new NSCalendarIdentifier("IndianValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Islamic = new NSCalendarIdentifier("IslamicValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier IslamicCivil = new NSCalendarIdentifier("IslamicCivilValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Japanese = new NSCalendarIdentifier("JapaneseValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier Persian = new NSCalendarIdentifier("PersianValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarIdentifier RepublicOfChina = new NSCalendarIdentifier("RepublicOfChinaValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSCalendarIdentifier IslamicTabular = new NSCalendarIdentifier("IslamicTabularValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSCalendarIdentifier IslamicUmmAlQura = new NSCalendarIdentifier("IslamicUmmAlQuraValue");
    
    private static NSCalendarIdentifier[] values = new NSCalendarIdentifier[] {Gregorian, Buddhist, Chinese, Coptic, EthiopicAmeteMihret, EthiopicAmeteAlem, Hebrew, ISO8601, Indian, Islamic, IslamicCivil, 
        Japanese, Persian, RepublicOfChina, IslamicTabular, IslamicUmmAlQura};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSCalendarIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSCalendarIdentifier valueOf(NSString value) {
        for (NSCalendarIdentifier v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSCalendarIdentifier/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierGregorian", optional=true)
    protected static native NSString GregorianValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierBuddhist", optional=true)
    protected static native NSString BuddhistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierChinese", optional=true)
    protected static native NSString ChineseValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierCoptic", optional=true)
    protected static native NSString CopticValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteMihret", optional=true)
    protected static native NSString EthiopicAmeteMihretValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteAlem", optional=true)
    protected static native NSString EthiopicAmeteAlemValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierHebrew", optional=true)
    protected static native NSString HebrewValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierISO8601", optional=true)
    protected static native NSString ISO8601Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIndian", optional=true)
    protected static native NSString IndianValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamic", optional=true)
    protected static native NSString IslamicValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamicCivil", optional=true)
    protected static native NSString IslamicCivilValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierJapanese", optional=true)
    protected static native NSString JapaneseValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierPersian", optional=true)
    protected static native NSString PersianValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierRepublicOfChina", optional=true)
    protected static native NSString RepublicOfChinaValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamicTabular", optional=true)
    protected static native NSString IslamicTabularValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamicUmmAlQura", optional=true)
    protected static native NSString IslamicUmmAlQuraValue();
    /*</methods>*/
}
