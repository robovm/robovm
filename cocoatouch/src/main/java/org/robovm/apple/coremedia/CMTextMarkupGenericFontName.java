/*
 * Copyright (C) 2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/CMTextMarkupGenericFontName/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextMarkupGenericFontName/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextMarkupGenericFontName toObject(Class<CMTextMarkupGenericFontName> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMTextMarkupGenericFontName.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextMarkupGenericFontName o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTextMarkupGenericFontName> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTextMarkupGenericFontName> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMTextMarkupGenericFontName.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTextMarkupGenericFontName> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTextMarkupGenericFontName i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Default = new CMTextMarkupGenericFontName("Default");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Serif = new CMTextMarkupGenericFontName("Serif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName SansSerif = new CMTextMarkupGenericFontName("SansSerif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Monospace = new CMTextMarkupGenericFontName("Monospace");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName ProportionalSerif = new CMTextMarkupGenericFontName("ProportionalSerif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName ProportionalSansSerif = new CMTextMarkupGenericFontName("ProportionalSansSerif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName MonospaceSerif = new CMTextMarkupGenericFontName("MonospaceSerif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName MonospaceSansSerif = new CMTextMarkupGenericFontName("MonospaceSansSerif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Casual = new CMTextMarkupGenericFontName("Casual");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Cursive = new CMTextMarkupGenericFontName("Cursive");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Fantasy = new CMTextMarkupGenericFontName("Fantasy");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName SmallCapital = new CMTextMarkupGenericFontName("SmallCapital");
    /*</constants>*/
    
    private static /*<name>*/CMTextMarkupGenericFontName/*</name>*/[] values = new /*<name>*/CMTextMarkupGenericFontName/*</name>*/[] {/*<value_list>*/Default, Serif, SansSerif, Monospace, ProportionalSerif, ProportionalSansSerif, MonospaceSerif, MonospaceSansSerif, Casual, Cursive, Fantasy, SmallCapital/*</value_list>*/};
    
    /*<name>*/CMTextMarkupGenericFontName/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMTextMarkupGenericFontName/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMTextMarkupGenericFontName/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMTextMarkupGenericFontName/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_Default", optional=true)
        public static native CFString Default();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_Serif", optional=true)
        public static native CFString Serif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_SansSerif", optional=true)
        public static native CFString SansSerif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_Monospace", optional=true)
        public static native CFString Monospace();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_ProportionalSerif", optional=true)
        public static native CFString ProportionalSerif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_ProportionalSansSerif", optional=true)
        public static native CFString ProportionalSansSerif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_MonospaceSerif", optional=true)
        public static native CFString MonospaceSerif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_MonospaceSansSerif", optional=true)
        public static native CFString MonospaceSansSerif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_Casual", optional=true)
        public static native CFString Casual();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_Cursive", optional=true)
        public static native CFString Cursive();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_Fantasy", optional=true)
        public static native CFString Fantasy();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMTextMarkupGenericFontName_SmallCapital", optional=true)
        public static native CFString SmallCapital();
        /*</values>*/
    }
}
