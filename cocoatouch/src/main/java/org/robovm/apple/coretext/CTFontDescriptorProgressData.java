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
@Marshaler(/*<name>*/CTFontDescriptorProgressData/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontDescriptorProgressData/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTFontDescriptorProgressData toObject(Class<CTFontDescriptorProgressData> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFontDescriptorProgressData(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontDescriptorProgressData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTFontDescriptorProgressData> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTFontDescriptorProgressData> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTFontDescriptorProgressData(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTFontDescriptorProgressData> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTFontDescriptorProgressData i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CTFontDescriptorProgressData(CFDictionary data) {
        super(data);
    }
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
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public CTFontDescriptor getSourceDescriptor() {
        if (has(Keys.SourceDescriptor())) {
            CTFontDescriptor val = get(Keys.SourceDescriptor(), CTFontDescriptor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public List<CTFontDescriptor> getDescriptors() {
        if (has(Keys.Descriptors())) {
            CFArray val = get(Keys.Descriptors(), CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public List<CTFontDescriptor> getResult() {
        if (has(Keys.Result())) {
            CFArray val = get(Keys.Result(), CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getPercentage() {
        if (has(Keys.Percentage())) {
            CFNumber val = get(Keys.Percentage(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getCurrentAssetSize() {
        if (has(Keys.CurrentAssetSize())) {
            CFNumber val = get(Keys.CurrentAssetSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getTotalDownloadedSize() {
        if (has(Keys.TotalDownloadedSize())) {
            CFNumber val = get(Keys.TotalDownloadedSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getTotalAssetSize() {
        if (has(Keys.TotalAssetSize())) {
            CFNumber val = get(Keys.TotalAssetSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSError getError() {
        if (has(Keys.Error())) {
            NSError val = get(Keys.Error(), NSError.class);
            return val;
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreText") @StronglyLinked
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingSourceDescriptor", optional=true)
        public static native CFString SourceDescriptor();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingDescriptors", optional=true)
        public static native CFString Descriptors();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingResult", optional=true)
        public static native CFString Result();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingPercentage", optional=true)
        public static native CFString Percentage();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingCurrentAssetSize", optional=true)
        public static native CFString CurrentAssetSize();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingTotalDownloadedSize", optional=true)
        public static native CFString TotalDownloadedSize();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingTotalAssetSize", optional=true)
        public static native CFString TotalAssetSize();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDescriptorMatchingError", optional=true)
        public static native CFString Error();
    }
    /*</keys>*/
}
