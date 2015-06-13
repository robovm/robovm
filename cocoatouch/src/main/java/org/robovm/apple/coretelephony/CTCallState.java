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
package org.robovm.apple.coretelephony;

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
/*<annotations>*/@Library("CoreTelephony") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CTCallState/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTCallState/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CTCallState/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTCallState toObject(Class<CTCallState> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CTCallState.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CTCallState o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CTCallState> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTCallState> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CTCallState.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTCallState> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CTCallState i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CTCallState Dialing = new CTCallState("Dialing");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CTCallState Incoming = new CTCallState("Incoming");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CTCallState Connected = new CTCallState("Connected");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CTCallState Disconnected = new CTCallState("Disconnected");
    /*</constants>*/
    
    private static /*<name>*/CTCallState/*</name>*/[] values = new /*<name>*/CTCallState/*</name>*/[] {/*<value_list>*/Dialing, Incoming, Connected, Disconnected/*</value_list>*/};
    
    /*<name>*/CTCallState/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CTCallState/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CTCallState/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTCallState/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreTelephony") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="CTCallStateDialing", optional=true)
        public static native NSString Dialing();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="CTCallStateIncoming", optional=true)
        public static native NSString Incoming();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="CTCallStateConnected", optional=true)
        public static native NSString Connected();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="CTCallStateDisconnected", optional=true)
        public static native NSString Disconnected();
        /*</values>*/
    }
}
