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
@Marshaler(/*<name>*/CFBundleInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFBundleInfoKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFBundleInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFBundleInfoKey toObject(Class<CFBundleInfoKey> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFBundleInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFBundleInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFBundleInfoKey> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFBundleInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFBundleInfoKey.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFBundleInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFBundleInfoKey i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFBundleInfoKey InfoDictionaryVersion = new CFBundleInfoKey("InfoDictionaryVersion");
    public static final CFBundleInfoKey Executable = new CFBundleInfoKey("Executable");
    public static final CFBundleInfoKey Identifier = new CFBundleInfoKey("Identifier");
    public static final CFBundleInfoKey Version = new CFBundleInfoKey("Version");
    public static final CFBundleInfoKey DevelopmentRegion = new CFBundleInfoKey("DevelopmentRegion");
    public static final CFBundleInfoKey Name = new CFBundleInfoKey("Name");
    public static final CFBundleInfoKey Localizations = new CFBundleInfoKey("Localizations");
    /*</constants>*/
    
    private static /*<name>*/CFBundleInfoKey/*</name>*/[] values = new /*<name>*/CFBundleInfoKey/*</name>*/[] {/*<value_list>*/InfoDictionaryVersion, Executable, Identifier, Version, DevelopmentRegion, Name, Localizations/*</value_list>*/};
    
    /*<name>*/CFBundleInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFBundleInfoKey/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFBundleInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFBundleInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFBundleInfoDictionaryVersionKey", optional=true)
        public static native CFString InfoDictionaryVersion();
        @GlobalValue(symbol="kCFBundleExecutableKey", optional=true)
        public static native CFString Executable();
        @GlobalValue(symbol="kCFBundleIdentifierKey", optional=true)
        public static native CFString Identifier();
        @GlobalValue(symbol="kCFBundleVersionKey", optional=true)
        public static native CFString Version();
        @GlobalValue(symbol="kCFBundleDevelopmentRegionKey", optional=true)
        public static native CFString DevelopmentRegion();
        @GlobalValue(symbol="kCFBundleNameKey", optional=true)
        public static native CFString Name();
        @GlobalValue(symbol="kCFBundleLocalizationsKey", optional=true)
        public static native CFString Localizations();
        /*</values>*/
    }
}
