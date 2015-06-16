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
package org.robovm.apple.security;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/SecAttrAccessible/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecAttrAccessible/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFType>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SecAttrAccessible/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecAttrAccessible toObject(Class<SecAttrAccessible> cls, long handle, long flags) {
            CFType o = (CFType) CFType.Marshaler.toObject(CFType.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SecAttrAccessible.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SecAttrAccessible o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecAttrAccessible> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecAttrAccessible> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SecAttrAccessible.valueOf(o.get(i, CFType.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecAttrAccessible> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecAttrAccessible o : l) {
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
    public static final SecAttrAccessible WhenUnlocked = new SecAttrAccessible("WhenUnlocked");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final SecAttrAccessible AfterFirstUnlock = new SecAttrAccessible("AfterFirstUnlock");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final SecAttrAccessible Always = new SecAttrAccessible("Always");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SecAttrAccessible WhenPasscodeSetThisDeviceOnly = new SecAttrAccessible("WhenPasscodeSetThisDeviceOnly");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final SecAttrAccessible WhenUnlockedThisDeviceOnly = new SecAttrAccessible("WhenUnlockedThisDeviceOnly");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final SecAttrAccessible AfterFirstUnlockThisDeviceOnly = new SecAttrAccessible("AfterFirstUnlockThisDeviceOnly");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final SecAttrAccessible AlwaysThisDeviceOnly = new SecAttrAccessible("AlwaysThisDeviceOnly");
    /*</constants>*/
    
    private static /*<name>*/SecAttrAccessible/*</name>*/[] values = new /*<name>*/SecAttrAccessible/*</name>*/[] {/*<value_list>*/WhenUnlocked, AfterFirstUnlock, Always, WhenPasscodeSetThisDeviceOnly, WhenUnlockedThisDeviceOnly, AfterFirstUnlockThisDeviceOnly, AlwaysThisDeviceOnly/*</value_list>*/};
    
    /*<name>*/SecAttrAccessible/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SecAttrAccessible/*</name>*/ valueOf(/*<type>*/CFType/*</type>*/ value) {
        for (/*<name>*/SecAttrAccessible/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SecAttrAccessible/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Security") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleWhenUnlocked", optional=true)
        public static native CFType WhenUnlocked();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlock", optional=true)
        public static native CFType AfterFirstUnlock();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleAlways", optional=true)
        public static native CFType Always();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleWhenPasscodeSetThisDeviceOnly", optional=true)
        public static native CFType WhenPasscodeSetThisDeviceOnly();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleWhenUnlockedThisDeviceOnly", optional=true)
        public static native CFType WhenUnlockedThisDeviceOnly();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly", optional=true)
        public static native CFType AfterFirstUnlockThisDeviceOnly();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessibleAlwaysThisDeviceOnly", optional=true)
        public static native CFType AlwaysThisDeviceOnly();
        /*</values>*/
    }
}
