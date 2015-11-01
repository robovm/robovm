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
package org.robovm.apple.addressbook;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ABPersonInstantMessageService/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonInstantMessageService/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ABPersonInstantMessageService/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ABPersonInstantMessageService toObject(Class<ABPersonInstantMessageService> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ABPersonInstantMessageService.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ABPersonInstantMessageService o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ABPersonInstantMessageService> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABPersonInstantMessageService> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ABPersonInstantMessageService.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABPersonInstantMessageService> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (ABPersonInstantMessageService o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService Yahoo = new ABPersonInstantMessageService("Yahoo");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService Jabber = new ABPersonInstantMessageService("Jabber");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService MSN = new ABPersonInstantMessageService("MSN");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService ICQ = new ABPersonInstantMessageService("ICQ");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService AIM = new ABPersonInstantMessageService("AIM");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService QQ = new ABPersonInstantMessageService("QQ");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService GoogleTalk = new ABPersonInstantMessageService("GoogleTalk");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService Skype = new ABPersonInstantMessageService("Skype");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService Facebook = new ABPersonInstantMessageService("Facebook");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonInstantMessageService GaduGadu = new ABPersonInstantMessageService("GaduGadu");
    /*</constants>*/
    
    private static /*<name>*/ABPersonInstantMessageService/*</name>*/[] values = new /*<name>*/ABPersonInstantMessageService/*</name>*/[] {/*<value_list>*/Yahoo, Jabber, MSN, ICQ, AIM, QQ, GoogleTalk, Skype, Facebook, GaduGadu/*</value_list>*/};
    
    /*<name>*/ABPersonInstantMessageService/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ABPersonInstantMessageService/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/ABPersonInstantMessageService/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonInstantMessageService/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AddressBook") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceYahoo", optional=true)
        public static native CFString Yahoo();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceJabber", optional=true)
        public static native CFString Jabber();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceMSN", optional=true)
        public static native CFString MSN();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceICQ", optional=true)
        public static native CFString ICQ();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceAIM", optional=true)
        public static native CFString AIM();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceQQ", optional=true)
        public static native CFString QQ();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceGoogleTalk", optional=true)
        public static native CFString GoogleTalk();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceSkype", optional=true)
        public static native CFString Skype();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceFacebook", optional=true)
        public static native CFString Facebook();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonInstantMessageServiceGaduGadu", optional=true)
        public static native CFString GaduGadu();
        /*</values>*/
    }
}
