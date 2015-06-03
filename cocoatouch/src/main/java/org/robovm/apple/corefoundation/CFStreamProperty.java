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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFStreamProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStreamProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFStreamProperty/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFStreamProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFStreamProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFStreamProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFStreamProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFStreamProperty i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFStreamProperty DataWritten = new CFStreamProperty("DataWritten");
    public static final CFStreamProperty AppendToFile = new CFStreamProperty("AppendToFile");
    public static final CFStreamProperty FileCurrentOffset = new CFStreamProperty("FileCurrentOffset");
    public static final CFStreamProperty SocketNativeHandle = new CFStreamProperty("SocketNativeHandle");
    public static final CFStreamProperty SocketRemoteHostName = new CFStreamProperty("SocketRemoteHostName");
    public static final CFStreamProperty SocketRemotePortNumber = new CFStreamProperty("SocketRemotePortNumber");
    /*</constants>*/
    
    private static /*<name>*/CFStreamProperty/*</name>*/[] values = new /*<name>*/CFStreamProperty/*</name>*/[] {/*<value_list>*/DataWritten, AppendToFile, FileCurrentOffset, SocketNativeHandle, SocketRemoteHostName, SocketRemotePortNumber/*</value_list>*/};
    
    /*<name>*/CFStreamProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFStreamProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFStreamProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFStreamProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFStreamPropertyDataWritten", optional=true)
        public static native CFString DataWritten();
        @GlobalValue(symbol="kCFStreamPropertyAppendToFile", optional=true)
        public static native CFString AppendToFile();
        @GlobalValue(symbol="kCFStreamPropertyFileCurrentOffset", optional=true)
        public static native CFString FileCurrentOffset();
        @GlobalValue(symbol="kCFStreamPropertySocketNativeHandle", optional=true)
        public static native CFString SocketNativeHandle();
        @GlobalValue(symbol="kCFStreamPropertySocketRemoteHostName", optional=true)
        public static native CFString SocketRemoteHostName();
        @GlobalValue(symbol="kCFStreamPropertySocketRemotePortNumber", optional=true)
        public static native CFString SocketRemotePortNumber();
        /*</values>*/
    }
}
