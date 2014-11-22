/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CTCallState.Marshaler.class)
/*<annotations>*/@Library("CoreTelephony")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTCallState/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CTCallState.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CTCallState Dialing = new CTCallState("DialingValue");
    public static final CTCallState Incoming = new CTCallState("IncomingValue");
    public static final CTCallState Connected = new CTCallState("ConnectedValue");
    public static final CTCallState Disconnected = new CTCallState("DisconnectedValue");
    
    private static CTCallState[] values = new CTCallState[] {Dialing, Incoming, Connected, Disconnected};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CTCallState(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CTCallState valueOf(NSString value) {
        for (CTCallState v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTCallState/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="CTCallStateDialing", optional=true)
    protected static native NSString DialingValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="CTCallStateIncoming", optional=true)
    protected static native NSString IncomingValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="CTCallStateConnected", optional=true)
    protected static native NSString ConnectedValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="CTCallStateDisconnected", optional=true)
    protected static native NSString DisconnectedValue();
    /*</methods>*/
}
