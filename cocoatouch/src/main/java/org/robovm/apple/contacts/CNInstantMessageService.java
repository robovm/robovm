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
@Marshaler(/*<name>*/CNInstantMessageService/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNInstantMessageService/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNInstantMessageService/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNInstantMessageService toObject(Class<CNInstantMessageService> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNInstantMessageService.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNInstantMessageService o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNInstantMessageService> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNInstantMessageService> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNInstantMessageService.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNInstantMessageService> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNInstantMessageService o : l) {
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
    public static final CNInstantMessageService AIM = new CNInstantMessageService("AIM");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService Facebook = new CNInstantMessageService("Facebook");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService GaduGadu = new CNInstantMessageService("GaduGadu");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService GoogleTalk = new CNInstantMessageService("GoogleTalk");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService ICQ = new CNInstantMessageService("ICQ");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService Jabber = new CNInstantMessageService("Jabber");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService MSN = new CNInstantMessageService("MSN");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService QQ = new CNInstantMessageService("QQ");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService Skype = new CNInstantMessageService("Skype");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNInstantMessageService Yahoo = new CNInstantMessageService("Yahoo");
    /*</constants>*/
    
    private static /*<name>*/CNInstantMessageService/*</name>*/[] values = new /*<name>*/CNInstantMessageService/*</name>*/[] {/*<value_list>*/AIM, Facebook, GaduGadu, GoogleTalk, ICQ, Jabber, MSN, QQ, Skype, Yahoo/*</value_list>*/};
    
    /*<name>*/CNInstantMessageService/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNInstantMessageService/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNInstantMessageService/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNInstantMessageService/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceAIM", optional=true)
        public static native NSString AIM();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceFacebook", optional=true)
        public static native NSString Facebook();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceGaduGadu", optional=true)
        public static native NSString GaduGadu();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceGoogleTalk", optional=true)
        public static native NSString GoogleTalk();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceICQ", optional=true)
        public static native NSString ICQ();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceJabber", optional=true)
        public static native NSString Jabber();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceMSN", optional=true)
        public static native NSString MSN();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceQQ", optional=true)
        public static native NSString QQ();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceSkype", optional=true)
        public static native NSString Skype();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNInstantMessageServiceYahoo", optional=true)
        public static native NSString Yahoo();
        /*</values>*/
    }
}
