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
@Marshaler(/*<name>*/CNLabel/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNLabel/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNLabel/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNLabel toObject(Class<CNLabel> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNLabel.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNLabel o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNLabel> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNLabel> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNLabel.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNLabel> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNLabel o : l) {
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
    public static final CNLabel Home = new CNLabel("Home");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabel Work = new CNLabel("Work");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabel Other = new CNLabel("Other");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabel EmailiCloud = new CNLabel("EmailiCloud");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabel URLAddressHomePage = new CNLabel("URLAddressHomePage");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNLabel DateAnniversary = new CNLabel("DateAnniversary");
    /*</constants>*/
    
    private static /*<name>*/CNLabel/*</name>*/[] values = new /*<name>*/CNLabel/*</name>*/[] {/*<value_list>*/Home, Work, Other, EmailiCloud, URLAddressHomePage, DateAnniversary/*</value_list>*/};
    
    /*<name>*/CNLabel/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNLabel/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNLabel/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNLabel/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelHome", optional=true)
        public static native NSString Home();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelWork", optional=true)
        public static native NSString Work();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelOther", optional=true)
        public static native NSString Other();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelEmailiCloud", optional=true)
        public static native NSString EmailiCloud();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelURLAddressHomePage", optional=true)
        public static native NSString URLAddressHomePage();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNLabelDateAnniversary", optional=true)
        public static native NSString DateAnniversary();
        /*</values>*/
    }
}
