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
package org.robovm.apple.assetslibrary;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AssetsLibrary") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ALAssetProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ALAssetProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ALAssetProperty toObject(Class<ALAssetProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ALAssetProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ALAssetProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ALAssetProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<ALAssetProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ALAssetProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ALAssetProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (ALAssetProperty o : l) {
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
    public static final ALAssetProperty Type = new ALAssetProperty("Type");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Location = new ALAssetProperty("Location");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Duration = new ALAssetProperty("Duration");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Orientation = new ALAssetProperty("Orientation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Date = new ALAssetProperty("Date");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Representations = new ALAssetProperty("Representations");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty URLs = new ALAssetProperty("URLs");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ALAssetProperty AssetURL = new ALAssetProperty("AssetURL");
    /*</constants>*/
    
    private static /*<name>*/ALAssetProperty/*</name>*/[] values = new /*<name>*/ALAssetProperty/*</name>*/[] {/*<value_list>*/Type, Location, Duration, Orientation, Date, Representations, URLs, AssetURL/*</value_list>*/};
    
    /*<name>*/ALAssetProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ALAssetProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/ALAssetProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ALAssetProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AssetsLibrary") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyType", optional=true)
        public static native NSString Type();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyLocation", optional=true)
        public static native NSString Location();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyDuration", optional=true)
        public static native NSString Duration();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyOrientation", optional=true)
        public static native NSString Orientation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyDate", optional=true)
        public static native NSString Date();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyRepresentations", optional=true)
        public static native NSString Representations();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyURLs", optional=true)
        public static native NSString URLs();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ALAssetPropertyAssetURL", optional=true)
        public static native NSString AssetURL();
        /*</values>*/
    }
}
