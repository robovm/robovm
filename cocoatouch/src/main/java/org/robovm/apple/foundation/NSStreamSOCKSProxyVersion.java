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
@Marshaler(/*<name>*/NSStreamSOCKSProxyVersion/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamSOCKSProxyVersion/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSStreamSOCKSProxyVersion/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSStreamSOCKSProxyVersion toObject(Class<NSStreamSOCKSProxyVersion> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSStreamSOCKSProxyVersion.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSStreamSOCKSProxyVersion o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSStreamSOCKSProxyVersion> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSStreamSOCKSProxyVersion> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSStreamSOCKSProxyVersion.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSStreamSOCKSProxyVersion> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSStreamSOCKSProxyVersion o : l) {
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
    public static final NSStreamSOCKSProxyVersion Version4 = new NSStreamSOCKSProxyVersion("Version4");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamSOCKSProxyVersion Version5 = new NSStreamSOCKSProxyVersion("Version5");
    /*</constants>*/
    
    private static /*<name>*/NSStreamSOCKSProxyVersion/*</name>*/[] values = new /*<name>*/NSStreamSOCKSProxyVersion/*</name>*/[] {/*<value_list>*/Version4, Version5/*</value_list>*/};
    
    /*<name>*/NSStreamSOCKSProxyVersion/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSStreamSOCKSProxyVersion/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSStreamSOCKSProxyVersion/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSStreamSOCKSProxyVersion/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyVersion4", optional=true)
        public static native NSString Version4();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyVersion5", optional=true)
        public static native NSString Version5();
        /*</values>*/
    }
}
