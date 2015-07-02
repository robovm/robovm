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
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
@Marshaler(/*<name>*/CGImagePropertyTIFFData/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyTIFFData/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyTIFFData toObject(Class<CGImagePropertyTIFFData> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGImagePropertyTIFFData(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyTIFFData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyTIFFData> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyTIFFData> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGImagePropertyTIFFData(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyTIFFData> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyTIFFData i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGImagePropertyTIFFData(CFDictionary data) {
        super(data);
    }
    public CGImagePropertyTIFFData() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CGImagePropertyTIFF key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CGImagePropertyTIFF key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CGImagePropertyTIFFData set(CGImagePropertyTIFF key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    /*</methods>*/
    public String getString(CGImagePropertyTIFF property) {
        if (has(property)) {
            CFString val = get(property, CFString.class);
            return val.toString();
        }
        return null;
    }
    public double getNumber(CGImagePropertyTIFF property) {
        if (has(property)) {
            CFNumber val = get(property, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    public CGImagePropertyTIFFData set(CGImagePropertyTIFF property, String value) {
        set(property, new CFString(value));
        return this;
    }
    public CGImagePropertyTIFFData set(CGImagePropertyTIFF property, double value) {
        set(property, CFNumber.valueOf(value));
        return this;
    }
    /*<keys>*/
    /*</keys>*/
}
