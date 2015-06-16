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
package org.robovm.apple.imageio;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImagePropertyIPTCContactInfo.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyIPTCContactInfo toObject(Class<CGImagePropertyIPTCContactInfo> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyIPTCContactInfo.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyIPTCContactInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyIPTCContactInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo City = new CGImagePropertyIPTCContactInfo("CityKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Country = new CGImagePropertyIPTCContactInfo("CountryKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Address = new CGImagePropertyIPTCContactInfo("AddressKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo PostalCode = new CGImagePropertyIPTCContactInfo("PostalCodeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo StateProvince = new CGImagePropertyIPTCContactInfo("StateProvinceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Emails = new CGImagePropertyIPTCContactInfo("EmailsKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Phones = new CGImagePropertyIPTCContactInfo("PhonesKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo WebURLs = new CGImagePropertyIPTCContactInfo("WebURLsKey");
    
    private static CGImagePropertyIPTCContactInfo[] values = new CGImagePropertyIPTCContactInfo[] {City, Country, Address, PostalCode, 
        StateProvince, Emails, Phones, WebURLs};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyIPTCContactInfo(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyIPTCContactInfo valueOf(CFString value) {
        for (CGImagePropertyIPTCContactInfo v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoCity", optional=true)
    protected static native CFString CityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoCountry", optional=true)
    protected static native CFString CountryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoAddress", optional=true)
    protected static native CFString AddressKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoPostalCode", optional=true)
    protected static native CFString PostalCodeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoStateProvince", optional=true)
    protected static native CFString StateProvinceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoEmails", optional=true)
    protected static native CFString EmailsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoPhones", optional=true)
    protected static native CFString PhonesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoWebURLs", optional=true)
    protected static native CFString WebURLsKey();
    /*</methods>*/
}
