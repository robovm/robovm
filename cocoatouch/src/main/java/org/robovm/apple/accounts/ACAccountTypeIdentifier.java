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
package org.robovm.apple.accounts;

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
/*<annotations>*/@Library("Accounts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ACAccountTypeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACAccountTypeIdentifier/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ACAccountTypeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ACAccountTypeIdentifier toObject(Class<ACAccountTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ACAccountTypeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ACAccountTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ACAccountTypeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ACAccountTypeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ACAccountTypeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ACAccountTypeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (ACAccountTypeIdentifier o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ACAccountTypeIdentifier Twitter = new ACAccountTypeIdentifier("Twitter");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACAccountTypeIdentifier Facebook = new ACAccountTypeIdentifier("Facebook");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACAccountTypeIdentifier SinaWeibo = new ACAccountTypeIdentifier("SinaWeibo");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final ACAccountTypeIdentifier TencentWeibo = new ACAccountTypeIdentifier("TencentWeibo");
    /*</constants>*/
    
    private static /*<name>*/ACAccountTypeIdentifier/*</name>*/[] values = new /*<name>*/ACAccountTypeIdentifier/*</name>*/[] {/*<value_list>*/Twitter, Facebook, SinaWeibo, TencentWeibo/*</value_list>*/};
    
    /*<name>*/ACAccountTypeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ACAccountTypeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/ACAccountTypeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ACAccountTypeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Accounts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="ACAccountTypeIdentifierTwitter", optional=true)
        public static native NSString Twitter();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACAccountTypeIdentifierFacebook", optional=true)
        public static native NSString Facebook();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACAccountTypeIdentifierSinaWeibo", optional=true)
        public static native NSString SinaWeibo();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="ACAccountTypeIdentifierTencentWeibo", optional=true)
        public static native NSString TencentWeibo();
        /*</values>*/
    }
}
