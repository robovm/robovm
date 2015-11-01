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
@Marshaler(/*<name>*/NSPersonNameComponent/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersonNameComponent/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSPersonNameComponent/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSPersonNameComponent toObject(Class<NSPersonNameComponent> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSPersonNameComponent.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSPersonNameComponent o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSPersonNameComponent> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSPersonNameComponent> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSPersonNameComponent.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSPersonNameComponent> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSPersonNameComponent o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent GivenName = new NSPersonNameComponent("GivenName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent FamilyName = new NSPersonNameComponent("FamilyName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent MiddleName = new NSPersonNameComponent("MiddleName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent Prefix = new NSPersonNameComponent("Prefix");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent Suffix = new NSPersonNameComponent("Suffix");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent Nickname = new NSPersonNameComponent("Nickname");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSPersonNameComponent Delimiter = new NSPersonNameComponent("Delimiter");
    /*</constants>*/
    
    private static /*<name>*/NSPersonNameComponent/*</name>*/[] values = new /*<name>*/NSPersonNameComponent/*</name>*/[] {/*<value_list>*/GivenName, FamilyName, MiddleName, Prefix, Suffix, Nickname, Delimiter/*</value_list>*/};
    
    /*<name>*/NSPersonNameComponent/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSPersonNameComponent/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSPersonNameComponent/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSPersonNameComponent/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentGivenName", optional=true)
        public static native NSString GivenName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentFamilyName", optional=true)
        public static native NSString FamilyName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentMiddleName", optional=true)
        public static native NSString MiddleName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentPrefix", optional=true)
        public static native NSString Prefix();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentSuffix", optional=true)
        public static native NSString Suffix();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentNickname", optional=true)
        public static native NSString Nickname();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSPersonNameComponentDelimiter", optional=true)
        public static native NSString Delimiter();
        /*</values>*/
    }
}
