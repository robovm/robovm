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
@Marshaler(/*<name>*/CMTimeCodeFormatDescriptionSourceReferenceName/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTimeCodeFormatDescriptionSourceReferenceName/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTimeCodeFormatDescriptionSourceReferenceName toObject(Class<CMTimeCodeFormatDescriptionSourceReferenceName> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMTimeCodeFormatDescriptionSourceReferenceName(o);
        }
        @MarshalsPointer
        public static long toNative(CMTimeCodeFormatDescriptionSourceReferenceName o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTimeCodeFormatDescriptionSourceReferenceName> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTimeCodeFormatDescriptionSourceReferenceName> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMTimeCodeFormatDescriptionSourceReferenceName(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTimeCodeFormatDescriptionSourceReferenceName> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTimeCodeFormatDescriptionSourceReferenceName i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMTimeCodeFormatDescriptionSourceReferenceName(CFDictionary data) {
        super(data);
    }
    public CMTimeCodeFormatDescriptionSourceReferenceName() {}
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
    public CMTimeCodeFormatDescriptionSourceReferenceName set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getValue() {
        if (has(Keys.Value())) {
            CFString val = get(Keys.Value(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeCodeFormatDescriptionSourceReferenceName setValue(String value) {
        set(Keys.Value(), new CFString(value));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getLangCode() {
        if (has(Keys.LangCode())) {
            CFNumber val = get(Keys.LangCode(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeCodeFormatDescriptionSourceReferenceName setLangCode(long langCode) {
        set(Keys.LangCode(), CFNumber.valueOf(langCode));
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
        @GlobalValue(symbol="kCMTimeCodeFormatDescriptionKey_Value", optional=true)
        public static native CFString Value();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTimeCodeFormatDescriptionKey_LangCode", optional=true)
        public static native CFString LangCode();
    }
    /*</keys>*/
}
