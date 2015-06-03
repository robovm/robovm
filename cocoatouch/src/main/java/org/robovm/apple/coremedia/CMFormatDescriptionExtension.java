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
@Marshaler(/*<name>*/CMFormatDescriptionExtension/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMFormatDescriptionExtension/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMFormatDescriptionExtension toObject(Class<CMFormatDescriptionExtension> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMFormatDescriptionExtension(o);
        }
        @MarshalsPointer
        public static long toNative(CMFormatDescriptionExtension o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMFormatDescriptionExtension> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMFormatDescriptionExtension> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMFormatDescriptionExtension(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMFormatDescriptionExtension> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMFormatDescriptionExtension i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMFormatDescriptionExtension(CFDictionary data) {
        super(data);
    }
    public CMFormatDescriptionExtension() {}
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
    public CMFormatDescriptionExtension set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDictionary<NSString, NSObject> getOriginalCompressionSettings() {
        if (has(Keys.OriginalCompressionSettings())) {
            NSDictionary<NSString, NSObject> val = get(Keys.OriginalCompressionSettings(), NSDictionary.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMFormatDescriptionExtension setOriginalCompressionSettings(NSDictionary<NSString, NSObject> originalCompressionSettings) {
        set(Keys.OriginalCompressionSettings(), originalCompressionSettings);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDictionary<NSString, NSObject> getSampleDescriptionExtensionAtoms() {
        if (has(Keys.SampleDescriptionExtensionAtoms())) {
            NSDictionary<NSString, NSObject> val = get(Keys.SampleDescriptionExtensionAtoms(), NSDictionary.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMFormatDescriptionExtension setSampleDescriptionExtensionAtoms(NSDictionary<NSString, NSObject> sampleDescriptionExtensionAtoms) {
        set(Keys.SampleDescriptionExtensionAtoms(), sampleDescriptionExtensionAtoms);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSData getVerbatimSampleDescription() {
        if (has(Keys.VerbatimSampleDescription())) {
            NSData val = get(Keys.VerbatimSampleDescription(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMFormatDescriptionExtension setVerbatimSampleDescription(NSData verbatimSampleDescription) {
        set(Keys.VerbatimSampleDescription(), verbatimSampleDescription);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSData getVerbatimISOSampleEntry() {
        if (has(Keys.VerbatimISOSampleEntry())) {
            NSData val = get(Keys.VerbatimISOSampleEntry(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMFormatDescriptionExtension setVerbatimISOSampleEntry(NSData verbatimISOSampleEntry) {
        set(Keys.VerbatimISOSampleEntry(), verbatimISOSampleEntry);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreMedia") @StronglyLinked
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_OriginalCompressionSettings", optional=true)
        public static native CFString OriginalCompressionSettings();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_SampleDescriptionExtensionAtoms", optional=true)
        public static native CFString SampleDescriptionExtensionAtoms();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_VerbatimSampleDescription", optional=true)
        public static native CFString VerbatimSampleDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_VerbatimISOSampleEntry", optional=true)
        public static native CFString VerbatimISOSampleEntry();
    }
    /*</keys>*/
}
