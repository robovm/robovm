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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTTypesetterOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTTypesetterOptions/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTTypesetterOptions toObject(Class<CTTypesetterOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTTypesetterOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CTTypesetterOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTTypesetterOptions> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTTypesetterOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTTypesetterOptions(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTTypesetterOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTTypesetterOptions i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CTTypesetterOptions(CFDictionary data) {
        super(data);
    }
    public CTTypesetterOptions() {}
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
    public CTTypesetterOptions set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public boolean isBidiProcessingDisabled() {
        if (has(Keys.DisableBidiProcessing())) {
            CFBoolean val = get(Keys.DisableBidiProcessing(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public CTTypesetterOptions setBidiProcessingDisabled(boolean bidiProcessingDisabled) {
        set(Keys.DisableBidiProcessing(), CFBoolean.valueOf(bidiProcessingDisabled));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public int getEmbeddingLevel() {
        if (has(Keys.ForcedEmbeddingLevel())) {
            CFNumber val = get(Keys.ForcedEmbeddingLevel(), CFNumber.class);
            return val.intValue();
        }
        return -1;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTTypesetterOptions setEmbeddingLevel(int embeddingLevel) {
        set(Keys.ForcedEmbeddingLevel(), CFNumber.valueOf(embeddingLevel));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreText")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.2 and later.
         * @deprecated Deprecated in iOS 6.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCTTypesetterOptionDisableBidiProcessing", optional=true)
        public static native CFString DisableBidiProcessing();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTTypesetterOptionForcedEmbeddingLevel", optional=true)
        public static native CFString ForcedEmbeddingLevel();
    }
    /*</keys>*/
}
