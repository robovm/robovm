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
/*<annotations>*/@Library("Foundation")/*</annotations>*/
@Marshaler(/*<name>*/NSURLErrorUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLErrorUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSURLErrorUserInfoKey toObject(Class<NSURLErrorUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLErrorUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLErrorUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLErrorUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLErrorUserInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLErrorUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLErrorUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLErrorUserInfoKey i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLErrorUserInfoKey KeysOfUnsetValues = new NSURLErrorUserInfoKey("KeysOfUnsetValues");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLErrorUserInfoKey FailingURL = new NSURLErrorUserInfoKey("FailingURL");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLErrorUserInfoKey FailingURLString = new NSURLErrorUserInfoKey("FailingURLString");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLErrorUserInfoKey FailingURLPeerTrust = new NSURLErrorUserInfoKey("FailingURLPeerTrust");
    /*</constants>*/
    
    private static /*<name>*/NSURLErrorUserInfoKey/*</name>*/[] values = new /*<name>*/NSURLErrorUserInfoKey/*</name>*/[] {/*<value_list>*/KeysOfUnsetValues, FailingURL, FailingURLString, FailingURLPeerTrust/*</value_list>*/};
    
    /*<name>*/NSURLErrorUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLErrorUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLErrorUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLErrorUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLKeysOfUnsetValuesKey", optional=true)
        public static native NSString KeysOfUnsetValues();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLErrorFailingURLErrorKey", optional=true)
        public static native NSString FailingURL();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLErrorFailingURLStringErrorKey", optional=true)
        public static native NSString FailingURLString();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLErrorFailingURLPeerTrustErrorKey", optional=true)
        public static native NSString FailingURLPeerTrust();
        /*</values>*/
    }
}
