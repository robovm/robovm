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
@Marshaler(/*<name>*/CMTextFormatDescriptionColor/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextFormatDescriptionColor/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextFormatDescriptionColor toObject(Class<CMTextFormatDescriptionColor> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMTextFormatDescriptionColor(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextFormatDescriptionColor o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTextFormatDescriptionColor> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTextFormatDescriptionColor> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMTextFormatDescriptionColor(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTextFormatDescriptionColor> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTextFormatDescriptionColor i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMTextFormatDescriptionColor(CFDictionary data) {
        super(data);
    }
    public CMTextFormatDescriptionColor() {}
    /*</constructors>*/
    public CMTextFormatDescriptionColor(int red, int green, int blue, int alpha) {
        setRed((short) red);
        setGreen((short) green);
        setBlue((short) blue);
        setAlpha((short) alpha);
    }
    
    /*<methods>*/
    public boolean has(CFString key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFString key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    public CMTextFormatDescriptionColor set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getRed() {
        if (has(Keys.Red())) {
            CFNumber val = get(Keys.Red(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionColor setRed(short red) {
        set(Keys.Red(), CFNumber.valueOf(red));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getGreen() {
        if (has(Keys.Green())) {
            CFNumber val = get(Keys.Green(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionColor setGreen(short green) {
        set(Keys.Green(), CFNumber.valueOf(green));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getBlue() {
        if (has(Keys.Blue())) {
            CFNumber val = get(Keys.Blue(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionColor setBlue(short blue) {
        set(Keys.Blue(), CFNumber.valueOf(blue));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getAlpha() {
        if (has(Keys.Alpha())) {
            CFNumber val = get(Keys.Alpha(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionColor setAlpha(short alpha) {
        set(Keys.Alpha(), CFNumber.valueOf(alpha));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreMedia")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionColor_Red", optional=true)
        public static native CFString Red();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionColor_Green", optional=true)
        public static native CFString Green();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionColor_Blue", optional=true)
        public static native CFString Blue();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionColor_Alpha", optional=true)
        public static native CFString Alpha();
    }
    /*</keys>*/
}
