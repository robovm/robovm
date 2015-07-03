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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(/*<name>*/CMMetadataFormatDescriptionKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataFormatDescriptionKey/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataFormatDescriptionKey toObject(Class<CMMetadataFormatDescriptionKey> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMMetadataFormatDescriptionKey(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataFormatDescriptionKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMMetadataFormatDescriptionKey> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMMetadataFormatDescriptionKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMMetadataFormatDescriptionKey(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMMetadataFormatDescriptionKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMMetadataFormatDescriptionKey i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMMetadataFormatDescriptionKey(CFDictionary data) {
        super(data);
    }
    public CMMetadataFormatDescriptionKey() {}
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
    public CMMetadataFormatDescriptionKey set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getNamespace() {
        if (has(Keys.Namespace())) {
            CFNumber val = get(Keys.Namespace(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMMetadataFormatDescriptionKey setNamespace(long namespace) {
        set(Keys.Namespace(), CFNumber.valueOf(namespace));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSData getValue() {
        if (has(Keys.Value())) {
            NSData val = get(Keys.Value(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMMetadataFormatDescriptionKey setValue(NSData value) {
        set(Keys.Value(), value);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getLocalID() {
        if (has(Keys.LocalID())) {
            CFNumber val = get(Keys.LocalID(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMMetadataFormatDescriptionKey setLocalID(long localID) {
        set(Keys.LocalID(), CFNumber.valueOf(localID));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSData getDataType() {
        if (has(Keys.DataType())) {
            NSData val = get(Keys.DataType(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionKey setDataType(NSData dataType) {
        set(Keys.DataType(), dataType);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getDataTypeNamespace() {
        if (has(Keys.DataTypeNamespace())) {
            CFNumber val = get(Keys.DataTypeNamespace(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionKey setDataTypeNamespace(long dataTypeNamespace) {
        set(Keys.DataTypeNamespace(), CFNumber.valueOf(dataTypeNamespace));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSDictionary<NSData, NSNumber> getConformingDataTypes() {
        if (has(Keys.ConformingDataTypes())) {
            NSDictionary<NSData, NSNumber> val = get(Keys.ConformingDataTypes(), NSDictionary.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionKey setConformingDataTypes(NSDictionary<NSData, NSNumber> conformingDataTypes) {
        set(Keys.ConformingDataTypes(), conformingDataTypes);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getLanguageTag() {
        if (has(Keys.LanguageTag())) {
            CFString val = get(Keys.LanguageTag(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataFormatDescriptionKey setLanguageTag(String languageTag) {
        set(Keys.LanguageTag(), new CFString(languageTag));
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
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_Namespace", optional=true)
        public static native CFString Namespace();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_Value", optional=true)
        public static native CFString Value();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_LocalID", optional=true)
        public static native CFString LocalID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_DataType", optional=true)
        public static native CFString DataType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_DataTypeNamespace", optional=true)
        public static native CFString DataTypeNamespace();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_ConformingDataTypes", optional=true)
        public static native CFString ConformingDataTypes();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_LanguageTag", optional=true)
        public static native CFString LanguageTag();
    }
    /*</keys>*/
}
