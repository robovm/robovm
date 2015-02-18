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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
@Marshaler(/*<name>*/CVBufferAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVBufferAttributes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVBufferAttributes toObject(Class<CVBufferAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CVBufferAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CVBufferAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVBufferAttributes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVBufferAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CVBufferAttributes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVBufferAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVBufferAttributes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CVBufferAttributes(CFDictionary data) {
        super(data);
    }
    public CVBufferAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CVBufferAttribute key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CVBufferAttribute key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CVBufferAttributes set(CVBufferAttribute key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVBufferAttributes getPropagatedAttachments() {
        if (has(CVBufferAttribute.PropagatedAttachments)) {
            CFDictionary val = get(CVBufferAttribute.PropagatedAttachments, CFDictionary.class);
            return new CVBufferAttributes(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVBufferAttributes setPropagatedAttachments(CVBufferAttributes propagatedAttachments) {
        set(CVBufferAttribute.PropagatedAttachments, propagatedAttachments.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVBufferAttributes getNonPropagatedAttachments() {
        if (has(CVBufferAttribute.NonPropagatedAttachments)) {
            CFDictionary val = get(CVBufferAttribute.NonPropagatedAttachments, CFDictionary.class);
            return new CVBufferAttributes(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVBufferAttributes setNonPropagatedAttachments(CVBufferAttributes nonPropagatedAttachments) {
        set(CVBufferAttribute.NonPropagatedAttachments, nonPropagatedAttachments.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVBufferMovieTime getMovieTime() {
        if (has(CVBufferAttribute.MovieTime)) {
            CFDictionary val = get(CVBufferAttribute.MovieTime, CFDictionary.class);
            return new CVBufferMovieTime(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVBufferAttributes setMovieTime(CVBufferMovieTime movieTime) {
        set(CVBufferAttribute.MovieTime, movieTime.getDictionary());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
