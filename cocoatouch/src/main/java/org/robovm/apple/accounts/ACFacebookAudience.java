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
@Marshaler(/*<name>*/ACFacebookAudience/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACFacebookAudience/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ACFacebookAudience/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ACFacebookAudience toObject(Class<ACFacebookAudience> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ACFacebookAudience.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ACFacebookAudience o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ACFacebookAudience> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ACFacebookAudience> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ACFacebookAudience.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ACFacebookAudience> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (ACFacebookAudience o : l) {
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
    public static final ACFacebookAudience Everyone = new ACFacebookAudience("Everyone");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACFacebookAudience Friends = new ACFacebookAudience("Friends");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACFacebookAudience OnlyMe = new ACFacebookAudience("OnlyMe");
    /*</constants>*/
    
    private static /*<name>*/ACFacebookAudience/*</name>*/[] values = new /*<name>*/ACFacebookAudience/*</name>*/[] {/*<value_list>*/Everyone, Friends, OnlyMe/*</value_list>*/};
    
    /*<name>*/ACFacebookAudience/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ACFacebookAudience/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/ACFacebookAudience/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ACFacebookAudience/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Accounts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACFacebookAudienceEveryone", optional=true)
        public static native NSString Everyone();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACFacebookAudienceFriends", optional=true)
        public static native NSString Friends();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACFacebookAudienceOnlyMe", optional=true)
        public static native NSString OnlyMe();
        /*</values>*/
    }
}
