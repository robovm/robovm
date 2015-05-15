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
@Marshaler(/*<name>*/CMMetadataFormatDescriptionMetadataSpecification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataFormatDescriptionMetadataSpecification/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataFormatDescriptionMetadataSpecification toObject(Class<CMMetadataFormatDescriptionMetadataSpecification> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMMetadataFormatDescriptionMetadataSpecification(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataFormatDescriptionMetadataSpecification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMMetadataFormatDescriptionMetadataSpecification> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMMetadataFormatDescriptionMetadataSpecification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMMetadataFormatDescriptionMetadataSpecification(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMMetadataFormatDescriptionMetadataSpecification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMMetadataFormatDescriptionMetadataSpecification i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMMetadataFormatDescriptionMetadataSpecification(CFDictionary data) {
        super(data);
    }
    public CMMetadataFormatDescriptionMetadataSpecification() {}
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
    public CMMetadataFormatDescriptionMetadataSpecification set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 8.0 and later.
     */
    public CFString getIdentifier() {
        if (has(Keys.Identifier())) {
            CFString val = get(Keys.Identifier(), CFString.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionMetadataSpecification setIdentifier(CFString identifier) {
        set(Keys.Identifier(), identifier);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CFString getDataType() {
        if (has(Keys.DataType())) {
            CFString val = get(Keys.DataType(), CFString.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionMetadataSpecification setDataType(CFString dataType) {
        set(Keys.DataType(), dataType);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CFString getExtendedLanguageTag() {
        if (has(Keys.ExtendedLanguageTag())) {
            CFString val = get(Keys.ExtendedLanguageTag(), CFString.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionMetadataSpecification setExtendedLanguageTag(CFString extendedLanguageTag) {
        set(Keys.ExtendedLanguageTag(), extendedLanguageTag);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreMedia")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionMetadataSpecificationKey_Identifier", optional=true)
        public static native CFString Identifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionMetadataSpecificationKey_DataType", optional=true)
        public static native CFString DataType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionMetadataSpecificationKey_ExtendedLanguageTag", optional=true)
        public static native CFString ExtendedLanguageTag();
    }
    /*</keys>*/
}
