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
@Marshaler(/*<name>*/NSFileType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSFileType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSFileType toObject(Class<NSFileType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSFileType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSFileType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSFileType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSFileType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSFileType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSFileType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSFileType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSFileType Directory = new NSFileType("Directory");
    public static final NSFileType Regular = new NSFileType("Regular");
    public static final NSFileType SymbolicLink = new NSFileType("SymbolicLink");
    public static final NSFileType Socket = new NSFileType("Socket");
    public static final NSFileType CharacterSpecial = new NSFileType("CharacterSpecial");
    public static final NSFileType BlockSpecial = new NSFileType("BlockSpecial");
    public static final NSFileType Unknown = new NSFileType("Unknown");
    /*</constants>*/
    
    private static /*<name>*/NSFileType/*</name>*/[] values = new /*<name>*/NSFileType/*</name>*/[] {/*<value_list>*/Directory, Regular, SymbolicLink, Socket, CharacterSpecial, BlockSpecial, Unknown/*</value_list>*/};
    
    /*<name>*/NSFileType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSFileType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSFileType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSFileType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSFileTypeDirectory", optional=true)
        public static native NSString Directory();
        @GlobalValue(symbol="NSFileTypeRegular", optional=true)
        public static native NSString Regular();
        @GlobalValue(symbol="NSFileTypeSymbolicLink", optional=true)
        public static native NSString SymbolicLink();
        @GlobalValue(symbol="NSFileTypeSocket", optional=true)
        public static native NSString Socket();
        @GlobalValue(symbol="NSFileTypeCharacterSpecial", optional=true)
        public static native NSString CharacterSpecial();
        @GlobalValue(symbol="NSFileTypeBlockSpecial", optional=true)
        public static native NSString BlockSpecial();
        @GlobalValue(symbol="NSFileTypeUnknown", optional=true)
        public static native NSString Unknown();
        /*</values>*/
    }
}
