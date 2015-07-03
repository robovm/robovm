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
@Marshaler(/*<name>*/NSURLFileResourceType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLFileResourceType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLFileResourceType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSURLFileResourceType toObject(Class<NSURLFileResourceType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLFileResourceType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLFileResourceType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLFileResourceType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLFileResourceType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLFileResourceType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLFileResourceType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLFileResourceType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType NamedPipe = new NSURLFileResourceType("NamedPipe");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType CharacterSpecial = new NSURLFileResourceType("CharacterSpecial");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Directory = new NSURLFileResourceType("Directory");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType BlockSpecial = new NSURLFileResourceType("BlockSpecial");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Regular = new NSURLFileResourceType("Regular");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType SymbolicLink = new NSURLFileResourceType("SymbolicLink");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Socket = new NSURLFileResourceType("Socket");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Unknown = new NSURLFileResourceType("Unknown");
    /*</constants>*/
    
    private static /*<name>*/NSURLFileResourceType/*</name>*/[] values = new /*<name>*/NSURLFileResourceType/*</name>*/[] {/*<value_list>*/NamedPipe, CharacterSpecial, Directory, BlockSpecial, Regular, SymbolicLink, Socket, Unknown/*</value_list>*/};
    
    /*<name>*/NSURLFileResourceType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLFileResourceType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLFileResourceType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLFileResourceType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeNamedPipe", optional=true)
        public static native NSString NamedPipe();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeCharacterSpecial", optional=true)
        public static native NSString CharacterSpecial();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeDirectory", optional=true)
        public static native NSString Directory();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeBlockSpecial", optional=true)
        public static native NSString BlockSpecial();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeRegular", optional=true)
        public static native NSString Regular();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeSymbolicLink", optional=true)
        public static native NSString SymbolicLink();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeSocket", optional=true)
        public static native NSString Socket();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeUnknown", optional=true)
        public static native NSString Unknown();
        /*</values>*/
    }
}
