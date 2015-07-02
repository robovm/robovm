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
package org.robovm.apple.imageio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CGImagePropertyJFIF/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyJFIF/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyJFIF/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyJFIF toObject(Class<CGImagePropertyJFIF> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyJFIF.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyJFIF o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyJFIF> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyJFIF> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyJFIF.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyJFIF> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyJFIF o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF Version = new CGImagePropertyJFIF("Version");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF XDensity = new CGImagePropertyJFIF("XDensity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF YDensity = new CGImagePropertyJFIF("YDensity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF DensityUnit = new CGImagePropertyJFIF("DensityUnit");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF IsProgressive = new CGImagePropertyJFIF("IsProgressive");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyJFIF/*</name>*/[] values = new /*<name>*/CGImagePropertyJFIF/*</name>*/[] {/*<value_list>*/Version, XDensity, YDensity, DensityUnit, IsProgressive/*</value_list>*/};
    
    /*<name>*/CGImagePropertyJFIF/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyJFIF/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyJFIF/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyJFIF/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyJFIFVersion", optional=true)
        public static native CFString Version();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyJFIFXDensity", optional=true)
        public static native CFString XDensity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyJFIFYDensity", optional=true)
        public static native CFString YDensity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyJFIFDensityUnit", optional=true)
        public static native CFString DensityUnit();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyJFIFIsProgressive", optional=true)
        public static native CFString IsProgressive();
        /*</values>*/
    }
}
