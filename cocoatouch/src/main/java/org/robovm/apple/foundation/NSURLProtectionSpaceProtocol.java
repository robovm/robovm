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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSURLProtectionSpaceProtocol/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLProtectionSpaceProtocol/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSURLProtectionSpaceProtocol toObject(Class<NSURLProtectionSpaceProtocol> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLProtectionSpaceProtocol.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLProtectionSpaceProtocol o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLProtectionSpaceProtocol> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLProtectionSpaceProtocol> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLProtectionSpaceProtocol.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLProtectionSpaceProtocol> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLProtectionSpaceProtocol o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLProtectionSpaceProtocol HTTP = new NSURLProtectionSpaceProtocol("HTTP");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLProtectionSpaceProtocol HTTPS = new NSURLProtectionSpaceProtocol("HTTPS");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLProtectionSpaceProtocol FTP = new NSURLProtectionSpaceProtocol("FTP");
    /*</constants>*/
    
    private static /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/[] values = new /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/[] {/*<value_list>*/HTTP, HTTPS, FTP/*</value_list>*/};
    
    /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLProtectionSpaceProtocol/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSURLProtectionSpaceHTTP", optional=true)
        public static native NSString HTTP();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSURLProtectionSpaceHTTPS", optional=true)
        public static native NSString HTTPS();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSURLProtectionSpaceFTP", optional=true)
        public static native NSString FTP();
        /*</values>*/
    }
}
