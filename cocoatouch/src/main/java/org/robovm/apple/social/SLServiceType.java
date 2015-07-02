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
package org.robovm.apple.social;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Social") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/SLServiceType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SLServiceType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SLServiceType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SLServiceType toObject(Class<SLServiceType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SLServiceType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SLServiceType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SLServiceType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SLServiceType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SLServiceType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SLServiceType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SLServiceType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final SLServiceType Twitter = new SLServiceType("Twitter");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final SLServiceType Facebook = new SLServiceType("Facebook");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final SLServiceType SinaWeibo = new SLServiceType("SinaWeibo");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SLServiceType TencentWeibo = new SLServiceType("TencentWeibo");
    /*</constants>*/
    
    private static /*<name>*/SLServiceType/*</name>*/[] values = new /*<name>*/SLServiceType/*</name>*/[] {/*<value_list>*/Twitter, Facebook, SinaWeibo, TencentWeibo/*</value_list>*/};
    
    /*<name>*/SLServiceType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SLServiceType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SLServiceType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SLServiceType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Social") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="SLServiceTypeTwitter", optional=true)
        public static native NSString Twitter();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="SLServiceTypeFacebook", optional=true)
        public static native NSString Facebook();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="SLServiceTypeSinaWeibo", optional=true)
        public static native NSString SinaWeibo();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="SLServiceTypeTencentWeibo", optional=true)
        public static native NSString TencentWeibo();
        /*</values>*/
    }
}
