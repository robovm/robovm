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
package org.robovm.apple.coretext;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTFrameAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFrameAttributes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTFrameAttributes toObject(Class<CTFrameAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFrameAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CTFrameAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTFrameAttributes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTFrameAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTFrameAttributes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTFrameAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTFrameAttributes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CTFrameAttributes(CFDictionary data) {
        super(data);
    }
    public CTFrameAttributes() {}
    /*</constructors>*/

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
    public CTFrameAttributes set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFrameProgression getProgression() {
        if (has(Keys.Progression())) {
            CFNumber val = get(Keys.Progression(), CFNumber.class);
            return CTFrameProgression.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFrameAttributes setProgression(CTFrameProgression progression) {
        set(Keys.Progression(), CFNumber.valueOf(progression.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CTFramePathFillRule getPathFillRule() {
        if (has(Keys.PathFillRule())) {
            CFNumber val = get(Keys.PathFillRule(), CFNumber.class);
            return CTFramePathFillRule.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CTFrameAttributes setPathFillRule(CTFramePathFillRule pathFillRule) {
        set(Keys.PathFillRule(), CFNumber.valueOf(pathFillRule.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public double getPathWidth() {
        if (has(Keys.PathWidth())) {
            CFNumber val = get(Keys.PathWidth(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CTFrameAttributes setPathWidth(double pathWidth) {
        set(Keys.PathWidth(), CFNumber.valueOf(pathWidth));
        return this;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public List<CTFrameClippingPath> getClippingPaths() {
        if (has(Keys.ClippingPaths())) {
            CFArray val = get(Keys.ClippingPaths(), CFArray.class);
            List<CTFrameClippingPath> list = new ArrayList<>();
            CFDictionary[] array = val.toArray(CFDictionary.class);
            for (CFDictionary d : array) {
               list.add(new CTFrameClippingPath(d));
            }
            return list;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public CTFrameAttributes setClippingPaths(List<CTFrameClippingPath> clippingPaths) {
        CFArray val = CFMutableArray.create();
        for (CTFrameClippingPath e : clippingPaths) {
            val.add(e.getDictionary());
        }
        set(Keys.ClippingPaths(), val);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreText")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFrameProgressionAttributeName", optional=true)
        public static native CFString Progression();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="kCTFramePathFillRuleAttributeName", optional=true)
        public static native CFString PathFillRule();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="kCTFramePathWidthAttributeName", optional=true)
        public static native CFString PathWidth();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="kCTFrameClippingPathsAttributeName", optional=true)
        public static native CFString ClippingPaths();
    }
    /*</keys>*/
}
