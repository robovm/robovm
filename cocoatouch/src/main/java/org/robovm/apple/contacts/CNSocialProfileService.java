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
@Marshaler(/*<name>*/CNSocialProfileService/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNSocialProfileService/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNSocialProfileService/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNSocialProfileService toObject(Class<CNSocialProfileService> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNSocialProfileService.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNSocialProfileService o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNSocialProfileService> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNSocialProfileService> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNSocialProfileService.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNSocialProfileService> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNSocialProfileService o : l) {
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
    public static final CNSocialProfileService Facebook = new CNSocialProfileService("Facebook");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService Flickr = new CNSocialProfileService("Flickr");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService LinkedIn = new CNSocialProfileService("LinkedIn");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService MySpace = new CNSocialProfileService("MySpace");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService SinaWeibo = new CNSocialProfileService("SinaWeibo");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService TencentWeibo = new CNSocialProfileService("TencentWeibo");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService Twitter = new CNSocialProfileService("Twitter");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService Yelp = new CNSocialProfileService("Yelp");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNSocialProfileService GameCenter = new CNSocialProfileService("GameCenter");
    /*</constants>*/
    
    private static /*<name>*/CNSocialProfileService/*</name>*/[] values = new /*<name>*/CNSocialProfileService/*</name>*/[] {/*<value_list>*/Facebook, Flickr, LinkedIn, MySpace, SinaWeibo, TencentWeibo, Twitter, Yelp, GameCenter/*</value_list>*/};
    
    /*<name>*/CNSocialProfileService/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNSocialProfileService/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNSocialProfileService/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNSocialProfileService/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceFacebook", optional=true)
        public static native NSString Facebook();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceFlickr", optional=true)
        public static native NSString Flickr();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceLinkedIn", optional=true)
        public static native NSString LinkedIn();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceMySpace", optional=true)
        public static native NSString MySpace();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceSinaWeibo", optional=true)
        public static native NSString SinaWeibo();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceTencentWeibo", optional=true)
        public static native NSString TencentWeibo();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceTwitter", optional=true)
        public static native NSString Twitter();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceYelp", optional=true)
        public static native NSString Yelp();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNSocialProfileServiceGameCenter", optional=true)
        public static native NSString GameCenter();
        /*</values>*/
    }
}
