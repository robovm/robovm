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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CFStreamProperty.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStreamProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFStreamProperty toObject(Class<CFStreamProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFStreamProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFStreamProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFStreamProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFStreamProperty DataWritter = new CFStreamProperty("DataWrittenValue");
    public static final CFStreamProperty AppendToFile = new CFStreamProperty("AppendToFileValue");
    public static final CFStreamProperty FileCurrentOffset = new CFStreamProperty("FileCurrentOffsetValue");
    public static final CFStreamProperty SocketNativeHandle = new CFStreamProperty("SocketNativeHandleValue");
    public static final CFStreamProperty SocketRemoteHostName = new CFStreamProperty("SocketRemoteHostNameValue");
    public static final CFStreamProperty SocketRemotePortNumber = new CFStreamProperty("SocketRemotePortNumberValue");
    
    private static CFStreamProperty[] values = new CFStreamProperty[] {DataWritter, AppendToFile, FileCurrentOffset, 
        SocketNativeHandle, SocketRemoteHostName, SocketRemotePortNumber};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFStreamProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFStreamProperty valueOf(CFString value) {
        for (CFStreamProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFStreamProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFStreamPropertyDataWritten", optional=true)
    protected static native String DataWrittenValue();
    @GlobalValue(symbol="kCFStreamPropertyAppendToFile", optional=true)
    protected static native String AppendToFileValue();
    @GlobalValue(symbol="kCFStreamPropertyFileCurrentOffset", optional=true)
    protected static native String FileCurrentOffsetValue();
    @GlobalValue(symbol="kCFStreamPropertySocketNativeHandle", optional=true)
    protected static native String SocketNativeHandleValue();
    @GlobalValue(symbol="kCFStreamPropertySocketRemoteHostName", optional=true)
    protected static native String SocketRemoteHostNameValue();
    @GlobalValue(symbol="kCFStreamPropertySocketRemotePortNumber", optional=true)
    protected static native String SocketRemotePortNumberValue();
    /*</methods>*/
}
