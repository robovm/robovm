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
@Marshaler(/*<name>*/CMTextFormatDescriptionRect/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextFormatDescriptionRect/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextFormatDescriptionRect toObject(Class<CMTextFormatDescriptionRect> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMTextFormatDescriptionRect(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextFormatDescriptionRect o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTextFormatDescriptionRect> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTextFormatDescriptionRect> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMTextFormatDescriptionRect(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTextFormatDescriptionRect> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTextFormatDescriptionRect i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
     CMTextFormatDescriptionRect(CFDictionary data) {
        super(data);
    }
    public CMTextFormatDescriptionRect() {}
    /*</constructors>*/
    public CMTextFormatDescriptionRect(int top, int left, int bottom, int right) {
        setTop((short)top);
        setLeft((short)left);
        setBottom((short)bottom);
        setRight((short)right);
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
    public CMTextFormatDescriptionRect set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getTop() {
        if (has(Keys.Top())) {
            CFNumber val = get(Keys.Top(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionRect setTop(short top) {
        set(Keys.Top(), CFNumber.valueOf(top));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getLeft() {
        if (has(Keys.Left())) {
            CFNumber val = get(Keys.Left(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionRect setLeft(short left) {
        set(Keys.Left(), CFNumber.valueOf(left));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getBottom() {
        if (has(Keys.Bottom())) {
            CFNumber val = get(Keys.Bottom(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionRect setBottom(short bottom) {
        set(Keys.Bottom(), CFNumber.valueOf(bottom));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getRight() {
        if (has(Keys.Right())) {
            CFNumber val = get(Keys.Right(), CFNumber.class);
            return val.shortValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionRect setRight(short right) {
        set(Keys.Right(), CFNumber.valueOf(right));
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
        @GlobalValue(symbol="kCMTextFormatDescriptionRect_Top", optional=true)
        public static native CFString Top();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionRect_Left", optional=true)
        public static native CFString Left();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionRect_Bottom", optional=true)
        public static native CFString Bottom();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionRect_Right", optional=true)
        public static native CFString Right();
    }
    /*</keys>*/
}
