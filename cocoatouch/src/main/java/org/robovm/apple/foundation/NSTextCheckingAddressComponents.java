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
/*<annotations>*/@Library("Foundation")/*</annotations>*/
@Marshaler(/*<name>*/NSTextCheckingAddressComponents/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextCheckingAddressComponents/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSTextCheckingAddressComponents toObject(Class<NSTextCheckingAddressComponents> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSTextCheckingAddressComponents(o);
        }
        @MarshalsPointer
        public static long toNative(NSTextCheckingAddressComponents o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSTextCheckingAddressComponents> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSTextCheckingAddressComponents> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSTextCheckingAddressComponents(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSTextCheckingAddressComponents> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (NSTextCheckingAddressComponents i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSTextCheckingAddressComponents(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getName() {
        if (has(Keys.Name())) {
            NSString val = (NSString) get(Keys.Name());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getJobTitle() {
        if (has(Keys.JobTitle())) {
            NSString val = (NSString) get(Keys.JobTitle());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getOrganization() {
        if (has(Keys.Organization())) {
            NSString val = (NSString) get(Keys.Organization());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getStreet() {
        if (has(Keys.Street())) {
            NSString val = (NSString) get(Keys.Street());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getCity() {
        if (has(Keys.City())) {
            NSString val = (NSString) get(Keys.City());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getState() {
        if (has(Keys.State())) {
            NSString val = (NSString) get(Keys.State());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getZIP() {
        if (has(Keys.ZIP())) {
            NSString val = (NSString) get(Keys.ZIP());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getCountry() {
        if (has(Keys.Country())) {
            NSString val = (NSString) get(Keys.Country());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getPhone() {
        if (has(Keys.Phone())) {
            NSString val = (NSString) get(Keys.Phone());
            return val.toString();
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Foundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingNameKey", optional=true)
        public static native NSString Name();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingJobTitleKey", optional=true)
        public static native NSString JobTitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingOrganizationKey", optional=true)
        public static native NSString Organization();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingStreetKey", optional=true)
        public static native NSString Street();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingCityKey", optional=true)
        public static native NSString City();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingStateKey", optional=true)
        public static native NSString State();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingZIPKey", optional=true)
        public static native NSString ZIP();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingCountryKey", optional=true)
        public static native NSString Country();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSTextCheckingPhoneKey", optional=true)
        public static native NSString Phone();
    }
    /*</keys>*/
}
