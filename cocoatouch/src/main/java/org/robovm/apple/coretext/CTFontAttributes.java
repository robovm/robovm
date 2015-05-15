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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTFontAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontAttributes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTFontAttributes toObject(Class<CTFontAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFontAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTFontAttributes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTFontAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTFontAttributes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTFontAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTFontAttributes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CTFontAttributes(CFDictionary data) {
        super(data);
    }
    public CTFontAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CTFontAttribute key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CTFontAttribute key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CTFontAttributes set(CTFontAttribute key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSURL getURL() {
        if (has(CTFontAttribute.URL)) {
            NSURL val = get(CTFontAttribute.URL, NSURL.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setURL(NSURL uRL) {
        set(CTFontAttribute.URL, uRL);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getName() {
        if (has(CTFontAttribute.Name)) {
            CFString val = get(CTFontAttribute.Name, CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setName(String name) {
        set(CTFontAttribute.Name, new CFString(name));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getDisplayName() {
        if (has(CTFontAttribute.DisplayName)) {
            CFString val = get(CTFontAttribute.DisplayName, CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setDisplayName(String displayName) {
        set(CTFontAttribute.DisplayName, new CFString(displayName));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getStyleName() {
        if (has(CTFontAttribute.StyleName)) {
            CFString val = get(CTFontAttribute.StyleName, CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setStyleName(String styleName) {
        set(CTFontAttribute.StyleName, new CFString(styleName));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits getTraits() {
        if (has(CTFontAttribute.Traits)) {
            CFDictionary val = get(CTFontAttribute.Traits, CFDictionary.class);
            return new CTFontTraits(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setTraits(CTFontTraits traits) {
        set(CTFontAttribute.Traits, traits.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes getVariationAxes() {
        if (has(CTFontAttribute.Variation)) {
            CFDictionary val = get(CTFontAttribute.Variation, CFDictionary.class);
            return new CTFontVariationAxes(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setVariationAxes(CTFontVariationAxes variationAxes) {
        set(CTFontAttribute.Variation, variationAxes.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getSize() {
        if (has(CTFontAttribute.Size)) {
            CFNumber val = get(CTFontAttribute.Size, CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setSize(float size) {
        set(CTFontAttribute.Size, CFNumber.valueOf(size));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CGAffineTransform getMatrix() {
        if (has(CTFontAttribute.Matrix)) {
            NSData val = get(CTFontAttribute.Matrix, NSData.class);
            return val.getStructData(CGAffineTransform.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setMatrix(CGAffineTransform matrix) {
        set(CTFontAttribute.Matrix, new NSData(matrix));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getCascadeList() {
        if (has(CTFontAttribute.CascadeList)) {
            CFArray val = get(CTFontAttribute.CascadeList, CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setCascadeList(List<CTFontDescriptor> cascadeList) {
        set(CTFontAttribute.CascadeList, CFArray.create(cascadeList));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSCharacterSet getCharacterSet() {
        if (has(CTFontAttribute.CharacterSet)) {
            NSCharacterSet val = get(CTFontAttribute.CharacterSet, NSCharacterSet.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setCharacterSet(NSCharacterSet characterSet) {
        set(CTFontAttribute.CharacterSet, characterSet);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<String> getLanguages() {
        if (has(CTFontAttribute.Languages)) {
            CFArray val = get(CTFontAttribute.Languages, CFArray.class);
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setLanguages(List<String> languages) {
        set(CTFontAttribute.Languages, CFArray.fromStrings(languages));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getBaselineAdjust() {
        if (has(CTFontAttribute.BaselineAdjust)) {
            CFNumber val = get(CTFontAttribute.BaselineAdjust, CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setBaselineAdjust(float baselineAdjust) {
        set(CTFontAttribute.BaselineAdjust, CFNumber.valueOf(baselineAdjust));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public long getMacintoshEncodings() {
        if (has(CTFontAttribute.MacintoshEncodings)) {
            CFNumber val = get(CTFontAttribute.MacintoshEncodings, CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setMacintoshEncodings(long macintoshEncodings) {
        set(CTFontAttribute.MacintoshEncodings, CFNumber.valueOf(macintoshEncodings));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getFixedAdvance() {
        if (has(CTFontAttribute.FixedAdvance)) {
            CFNumber val = get(CTFontAttribute.FixedAdvance, CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setFixedAdvance(float fixedAdvance) {
        set(CTFontAttribute.FixedAdvance, CFNumber.valueOf(fixedAdvance));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontOrientation getOrientation() {
        if (has(CTFontAttribute.Orientation)) {
            CFNumber val = get(CTFontAttribute.Orientation, CFNumber.class);
            return CTFontOrientation.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setOrientation(CTFontOrientation orientation) {
        set(CTFontAttribute.Orientation, CFNumber.valueOf(orientation.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontFormat getFormat() {
        if (has(CTFontAttribute.Format)) {
            CFNumber val = get(CTFontAttribute.Format, CFNumber.class);
            return CTFontFormat.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setFormat(CTFontFormat format) {
        set(CTFontAttribute.Format, CFNumber.valueOf(format.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontManagerScope getRegistrationScope() {
        if (has(CTFontAttribute.RegistrationScope)) {
            CFNumber val = get(CTFontAttribute.RegistrationScope, CFNumber.class);
            return CTFontManagerScope.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setRegistrationScope(CTFontManagerScope registrationScope) {
        set(CTFontAttribute.RegistrationScope, CFNumber.valueOf(registrationScope.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontPriority getPriority() {
        if (has(CTFontAttribute.Priority)) {
            CFNumber val = get(CTFontAttribute.Priority, CFNumber.class);
            return CTFontPriority.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setPriority(CTFontPriority priority) {
        set(CTFontAttribute.Priority, CFNumber.valueOf(priority.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public boolean isEnabled() {
        if (has(CTFontAttribute.Enabled)) {
            CFBoolean val = get(CTFontAttribute.Enabled, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setEnabled(boolean enabled) {
        set(CTFontAttribute.Enabled, CFBoolean.valueOf(enabled));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isDownloadable() {
        if (has(CTFontAttribute.Downloadable)) {
            CFBoolean val = get(CTFontAttribute.Downloadable, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CTFontAttributes setDownloadable(boolean downloadable) {
        set(CTFontAttribute.Downloadable, CFBoolean.valueOf(downloadable));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isDownloaded() {
        if (has(CTFontAttribute.Downloaded)) {
            CFBoolean val = get(CTFontAttribute.Downloaded, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CTFontAttributes setDownloaded(boolean downloaded) {
        set(CTFontAttribute.Downloaded, CFBoolean.valueOf(downloaded));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
