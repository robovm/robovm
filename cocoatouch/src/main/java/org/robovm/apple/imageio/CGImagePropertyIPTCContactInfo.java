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
/*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyIPTCContactInfo> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyIPTCContactInfo> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyIPTCContactInfo.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyIPTCContactInfo> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyIPTCContactInfo o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo City = new CGImagePropertyIPTCContactInfo("City");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Country = new CGImagePropertyIPTCContactInfo("Country");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Address = new CGImagePropertyIPTCContactInfo("Address");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo PostalCode = new CGImagePropertyIPTCContactInfo("PostalCode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo StateProvince = new CGImagePropertyIPTCContactInfo("StateProvince");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Emails = new CGImagePropertyIPTCContactInfo("Emails");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo Phones = new CGImagePropertyIPTCContactInfo("Phones");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTCContactInfo WebURLs = new CGImagePropertyIPTCContactInfo("WebURLs");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/[] values = new /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/[] {/*<value_list>*/City, Country, Address, PostalCode, StateProvince, Emails, Phones, WebURLs/*</value_list>*/};
    
    /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyIPTCContactInfo/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoCity", optional=true)
        public static native CFString City();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoCountry", optional=true)
        public static native CFString Country();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoAddress", optional=true)
        public static native CFString Address();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoPostalCode", optional=true)
        public static native CFString PostalCode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoStateProvince", optional=true)
        public static native CFString StateProvince();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoEmails", optional=true)
        public static native CFString Emails();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoPhones", optional=true)
        public static native CFString Phones();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContactInfoWebURLs", optional=true)
        public static native CFString WebURLs();
        /*</values>*/
    }
}
