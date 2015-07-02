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
@Marshaler(/*<name>*/NSStreamNetworkServiceType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamNetworkServiceType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSStreamNetworkServiceType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSStreamNetworkServiceType toObject(Class<NSStreamNetworkServiceType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSStreamNetworkServiceType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSStreamNetworkServiceType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSStreamNetworkServiceType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSStreamNetworkServiceType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSStreamNetworkServiceType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSStreamNetworkServiceType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSStreamNetworkServiceType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSStreamNetworkServiceType VoIP = new NSStreamNetworkServiceType("VoIP");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSStreamNetworkServiceType Video = new NSStreamNetworkServiceType("Video");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSStreamNetworkServiceType Background = new NSStreamNetworkServiceType("Background");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSStreamNetworkServiceType Voice = new NSStreamNetworkServiceType("Voice");
    /*</constants>*/
    
    private static /*<name>*/NSStreamNetworkServiceType/*</name>*/[] values = new /*<name>*/NSStreamNetworkServiceType/*</name>*/[] {/*<value_list>*/VoIP, Video, Background, Voice/*</value_list>*/};
    
    /*<name>*/NSStreamNetworkServiceType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSStreamNetworkServiceType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSStreamNetworkServiceType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSStreamNetworkServiceType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSStreamNetworkServiceTypeVoIP", optional=true)
        public static native NSString VoIP();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSStreamNetworkServiceTypeVideo", optional=true)
        public static native NSString Video();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSStreamNetworkServiceTypeBackground", optional=true)
        public static native NSString Background();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSStreamNetworkServiceTypeVoice", optional=true)
        public static native NSString Voice();
        /*</values>*/
    }
}
