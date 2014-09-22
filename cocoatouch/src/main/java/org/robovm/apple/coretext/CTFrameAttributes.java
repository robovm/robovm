/*
 * Copyright (C) 2014 Trillian Mobile AB
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
@Marshaler(CTFrameAttributes.Marshaler.class)
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFrameAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CTFrameAttributes(CFDictionary data) {
        this.data = data;
    }
    public CTFrameAttributes() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CTFrameAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected CFDictionary getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFrameProgression getProgression() {
        if (data.containsKey(Progression())) {
            CFNumber val = data.get(Progression(), CFNumber.class);
            return CTFrameProgression.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFrameAttributes setProgression(CTFrameProgression progression) {
        data.put(Progression(), CFNumber.valueOf((int)progression.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CTFramePathFillRule getPathFillRule() {
        if (data.containsKey(PathFillRule())) {
            CFNumber val = data.get(PathFillRule(), CFNumber.class);
            return CTFramePathFillRule.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CTFrameAttributes setPathFillRule(CTFramePathFillRule pathFillRule) {
        data.put(PathFillRule(), CFNumber.valueOf((int)pathFillRule.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public double getPathWidth() {
        if (data.containsKey(PathWidth())) {
            CFNumber val = data.get(PathWidth(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CTFrameAttributes setPathWidth(double width) {
        data.put(PathWidth(), CFNumber.valueOf(width));
        return this;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public List<CTFrameClippingPath> getClippingPaths() {
        List<CTFrameClippingPath> list = new ArrayList<>();
        if (data.containsKey(ClippingPaths())) {
            CFArray val = data.get(ClippingPaths(), CFArray.class);
            CFDictionary[] arr = val.toArray(CFDictionary.class);
            for (CFDictionary d : arr) {
                list.add(new CTFrameClippingPath(d));
            }
        }
        return list;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public CTFrameAttributes setClippingPaths(List<CTFrameClippingPath> paths) {
        CFArray list = CFMutableArray.create();
        for (CTFrameClippingPath path : paths) {
            list.add(path.getDictionary());
        }
        data.put(ClippingPaths(), list);
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFrameProgressionAttributeName", optional=true)
    protected static native CFString Progression();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="kCTFramePathFillRuleAttributeName", optional=true)
    protected static native CFString PathFillRule();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="kCTFramePathWidthAttributeName", optional=true)
    protected static native CFString PathWidth();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCTFrameClippingPathsAttributeName", optional=true)
    protected static native CFString ClippingPaths();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
