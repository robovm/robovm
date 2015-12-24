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
package org.robovm.apple.contacts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CNSocialProfilePropertyKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNSocialProfilePropertyKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNSocialProfilePropertyKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNSocialProfilePropertyKey toObject(Class<CNSocialProfilePropertyKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNSocialProfilePropertyKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNSocialProfilePropertyKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNSocialProfilePropertyKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNSocialProfilePropertyKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNSocialProfilePropertyKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNSocialProfilePropertyKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNSocialProfilePropertyKey o : l) {
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
    public static final CNSocialProfilePropertyKey URLString = new CNSocialProfilePropertyKey("URLString");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfilePropertyKey Username = new CNSocialProfilePropertyKey("Username");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfilePropertyKey UserIdentifier = new CNSocialProfilePropertyKey("UserIdentifier");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfilePropertyKey Service = new CNSocialProfilePropertyKey("Service");
    /*</constants>*/
    
    private static /*<name>*/CNSocialProfilePropertyKey/*</name>*/[] values = new /*<name>*/CNSocialProfilePropertyKey/*</name>*/[] {/*<value_list>*/URLString, Username, UserIdentifier, Service/*</value_list>*/};
    
    /*<name>*/CNSocialProfilePropertyKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNSocialProfilePropertyKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNSocialProfilePropertyKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNSocialProfilePropertyKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileURLStringKey", optional=true)
        public static native NSString URLString();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileUsernameKey", optional=true)
        public static native NSString Username();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileUserIdentifierKey", optional=true)
        public static native NSString UserIdentifier();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceKey", optional=true)
        public static native NSString Service();
        /*</values>*/
    }
}
