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
@Marshaler(/*<name>*/CVBufferMovieTime/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVBufferMovieTime/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVBufferMovieTime toObject(Class<CVBufferMovieTime> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CVBufferMovieTime(o);
        }
        @MarshalsPointer
        public static long toNative(CVBufferMovieTime o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVBufferMovieTime> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVBufferMovieTime> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CVBufferMovieTime(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVBufferMovieTime> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVBufferMovieTime i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CVBufferMovieTime(CFDictionary data) {
        super(data);
    }
    public CVBufferMovieTime() {}
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
    public CVBufferMovieTime set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreVideo")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVBufferTimeValueKey", optional=true)
        public static native CFString Value();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVBufferTimeScaleKey", optional=true)
        public static native CFString Scale();
    }
    /*</keys>*/
}
