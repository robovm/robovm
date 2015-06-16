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
package org.robovm.apple.iad;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.mediaplayer.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.avkit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("iAd") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ADBannerContentSizeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ADBannerContentSizeIdentifier/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ADBannerContentSizeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ADBannerContentSizeIdentifier toObject(Class<ADBannerContentSizeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ADBannerContentSizeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ADBannerContentSizeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ADBannerContentSizeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ADBannerContentSizeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ADBannerContentSizeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ADBannerContentSizeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (ADBannerContentSizeIdentifier o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public static final ADBannerContentSizeIdentifier Portrait = new ADBannerContentSizeIdentifier("Portrait");
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public static final ADBannerContentSizeIdentifier Landscape = new ADBannerContentSizeIdentifier("Landscape");
    /*</constants>*/
    
    private static /*<name>*/ADBannerContentSizeIdentifier/*</name>*/[] values = new /*<name>*/ADBannerContentSizeIdentifier/*</name>*/[] {/*<value_list>*/Portrait, Landscape/*</value_list>*/};
    
    /*<name>*/ADBannerContentSizeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ADBannerContentSizeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/ADBannerContentSizeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ADBannerContentSizeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("iAd") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.2 and later.
         * @deprecated Deprecated in iOS 6.0.
         */
        @Deprecated
        @GlobalValue(symbol="ADBannerContentSizeIdentifierPortrait", optional=true)
        public static native NSString Portrait();
        /**
         * @since Available in iOS 4.2 and later.
         * @deprecated Deprecated in iOS 6.0.
         */
        @Deprecated
        @GlobalValue(symbol="ADBannerContentSizeIdentifierLandscape", optional=true)
        public static native NSString Landscape();
        /*</values>*/
    }
}
