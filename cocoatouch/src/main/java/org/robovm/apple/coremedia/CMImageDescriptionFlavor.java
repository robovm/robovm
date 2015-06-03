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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CMImageDescriptionFlavor/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMImageDescriptionFlavor/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMImageDescriptionFlavor toObject(Class<CMImageDescriptionFlavor> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMImageDescriptionFlavor.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMImageDescriptionFlavor o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMImageDescriptionFlavor> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMImageDescriptionFlavor> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMImageDescriptionFlavor.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMImageDescriptionFlavor> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMImageDescriptionFlavor i : l) {
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
    public static final CMImageDescriptionFlavor QuickTimeMovie = new CMImageDescriptionFlavor("QuickTimeMovie");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMImageDescriptionFlavor ISOFamily = new CMImageDescriptionFlavor("ISOFamily");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMImageDescriptionFlavor _3GPFamily = new CMImageDescriptionFlavor("_3GPFamily");
    /*</constants>*/
    
    private static /*<name>*/CMImageDescriptionFlavor/*</name>*/[] values = new /*<name>*/CMImageDescriptionFlavor/*</name>*/[] {/*<value_list>*/QuickTimeMovie, ISOFamily, _3GPFamily/*</value_list>*/};
    
    /*<name>*/CMImageDescriptionFlavor/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMImageDescriptionFlavor/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMImageDescriptionFlavor/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMImageDescriptionFlavor/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMImageDescriptionFlavor_QuickTimeMovie", optional=true)
        public static native CFString QuickTimeMovie();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMImageDescriptionFlavor_ISOFamily", optional=true)
        public static native CFString ISOFamily();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMImageDescriptionFlavor_3GPFamily", optional=true)
        public static native CFString _3GPFamily();
        /*</values>*/
    }
}
