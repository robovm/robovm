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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(/*<name>*/CMMetadataKeySpace/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataKeySpace/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataKeySpace toObject(Class<CMMetadataKeySpace> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMMetadataKeySpace.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataKeySpace o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMMetadataKeySpace> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMMetadataKeySpace> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMMetadataKeySpace.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMMetadataKeySpace> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMMetadataKeySpace i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace QuickTimeUserData = new CMMetadataKeySpace("QuickTimeUserData");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace ISOUserData = new CMMetadataKeySpace("ISOUserData");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace QuickTimeMetadata = new CMMetadataKeySpace("QuickTimeMetadata");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace iTunes = new CMMetadataKeySpace("iTunes");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace ID3 = new CMMetadataKeySpace("ID3");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace Icy = new CMMetadataKeySpace("Icy");
    /*</constants>*/
    
    private static /*<name>*/CMMetadataKeySpace/*</name>*/[] values = new /*<name>*/CMMetadataKeySpace/*</name>*/[] {/*<value_list>*/QuickTimeUserData, ISOUserData, QuickTimeMetadata, iTunes, ID3, Icy/*</value_list>*/};
    
    /*<name>*/CMMetadataKeySpace/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMMetadataKeySpace/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMMetadataKeySpace/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMMetadataKeySpace/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataKeySpace_QuickTimeUserData", optional=true)
        public static native CFString QuickTimeUserData();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataKeySpace_ISOUserData", optional=true)
        public static native CFString ISOUserData();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataKeySpace_QuickTimeMetadata", optional=true)
        public static native CFString QuickTimeMetadata();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataKeySpace_iTunes", optional=true)
        public static native CFString iTunes();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataKeySpace_ID3", optional=true)
        public static native CFString ID3();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataKeySpace_Icy", optional=true)
        public static native CFString Icy();
        /*</values>*/
    }
}
