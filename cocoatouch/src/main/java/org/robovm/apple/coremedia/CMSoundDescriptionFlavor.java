/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/CMSoundDescriptionFlavor/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSoundDescriptionFlavor/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMSoundDescriptionFlavor toObject(Class<CMSoundDescriptionFlavor> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMSoundDescriptionFlavor.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMSoundDescriptionFlavor o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMSoundDescriptionFlavor> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMSoundDescriptionFlavor> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMSoundDescriptionFlavor.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMSoundDescriptionFlavor> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMSoundDescriptionFlavor i : l) {
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
    public static final CMSoundDescriptionFlavor QuickTimeMovie = new CMSoundDescriptionFlavor("QuickTimeMovie");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMSoundDescriptionFlavor QuickTimeMovieV2 = new CMSoundDescriptionFlavor("QuickTimeMovieV2");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMSoundDescriptionFlavor ISOFamily = new CMSoundDescriptionFlavor("ISOFamily");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMSoundDescriptionFlavor _3GPFamily = new CMSoundDescriptionFlavor("_3GPFamily");
    /*</constants>*/
    
    private static /*<name>*/CMSoundDescriptionFlavor/*</name>*/[] values = new /*<name>*/CMSoundDescriptionFlavor/*</name>*/[] {/*<value_list>*/QuickTimeMovie, QuickTimeMovieV2, ISOFamily, _3GPFamily/*</value_list>*/};
    
    /*<name>*/CMSoundDescriptionFlavor/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMSoundDescriptionFlavor/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMSoundDescriptionFlavor/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMSoundDescriptionFlavor/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMSoundDescriptionFlavor_QuickTimeMovie", optional=true)
        public static native CFString QuickTimeMovie();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMSoundDescriptionFlavor_QuickTimeMovieV2", optional=true)
        public static native CFString QuickTimeMovieV2();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMSoundDescriptionFlavor_ISOFamily", optional=true)
        public static native CFString ISOFamily();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMSoundDescriptionFlavor_3GPFamily", optional=true)
        public static native CFString _3GPFamily();
        /*</values>*/
    }
}
