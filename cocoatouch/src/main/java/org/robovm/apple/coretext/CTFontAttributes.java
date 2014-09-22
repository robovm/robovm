/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.apple.coretext.CTFont.CTFontPtr;

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CTFontAttributes.Marshaler.class)
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CTFontAttributes(CFDictionary data) {
        this.data = data;
    }
    public CTFontAttributes() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CTFontAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected CFDictionary getDictionary() {
        return data;
    }
    
    public CTFontAttributes set(CTFontAttribute attribute, NativeObject value) {
        data.put(attribute.value(), value);
        return this;
    }
    public <T extends NativeObject> T get(CTFontAttribute attribute, Class<T> type) {
        return data.get(attribute.value(), type);
    }
    public boolean contains(CTFontAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSURL getURL() {
        if (contains(CTFontAttribute.URL)) {
            NSURL val = get(CTFontAttribute.URL, NSURL.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setURL(NSURL url) {
        set(CTFontAttribute.URL, url);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getName() {
        if (contains(CTFontAttribute.Name)) {
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
        if (contains(CTFontAttribute.DisplayName)) {
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
        if (contains(CTFontAttribute.StyleName)) {
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
        if (contains(CTFontAttribute.Traits)) {
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
        if (contains(CTFontAttribute.Variation)) {
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
        if (contains(CTFontAttribute.Size)) {
            CFNumber val = get(CTFontAttribute.Size, CFNumber.class);
            return val.floatValue();
        }
        return 12;
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
    public @ByVal CGAffineTransform getMatrix() {
        if (contains(CTFontAttribute.Matrix)) {
            NSData val = get(CTFontAttribute.Matrix, NSData.class);
            return val.getStructData(CGAffineTransform.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setMatrix(@ByVal CGAffineTransform matrix) {
        set(CTFontAttribute.Matrix, new NSData(matrix));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getCascadeList() {
        if (contains(CTFontAttribute.CascadeList)) {
            CFArray val = get(CTFontAttribute.CascadeList, CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return new ArrayList<CTFontDescriptor>();
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setCascadeList(List<CTFontDescriptor> list) {
        set(CTFontAttribute.CascadeList, CFArray.create(list));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSCharacterSet getCharacterSet() {
        if (contains(CTFontAttribute.CharacterSet)) {
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
        List<String> list = new ArrayList<>();
        if (contains(CTFontAttribute.Languages)) {
            CFArray val = get(CTFontAttribute.Languages, CFArray.class);
            CFString[] arr = val.toArray(CFString.class);
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i].toString());
            }
        }
        return list;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setLanguages(List<String> languages) {
        set(CTFontAttribute.Languages, NSArray.fromStrings(languages));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getBaselineAdjust() {
        if (contains(CTFontAttribute.BaselineAdjust)) {
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
        if (contains(CTFontAttribute.MacintoshEncodings)) {
            CFNumber val = get(CTFontAttribute.MacintoshEncodings, CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setMacintoshEncodings(long encodingBits) {
        set(CTFontAttribute.MacintoshEncodings, CFNumber.valueOf(encodingBits));
        return this;
    }

//    const CFStringRef kCTFontFeaturesAttribute;
//    const CFStringRef kCTFontFeatureSettingsAttribute; TODO
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getFixedAdvance() {
        if (contains(CTFontAttribute.FixedAdvance)) {
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
        if (contains(CTFontAttribute.Orientation)) {
            CFNumber val = get(CTFontAttribute.Orientation, CFNumber.class);
            return CTFontOrientation.valueOf(val.intValue());
        }
        return CTFontOrientation.DefaultOrientation;
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
        if (contains(CTFontAttribute.Format)) {
            CFNumber val = get(CTFontAttribute.Format, CFNumber.class);
            return CTFontFormat.valueOf(val.intValue());
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
        if (contains(CTFontAttribute.RegistrationScope)) {
            CFNumber val = get(CTFontAttribute.RegistrationScope, CFNumber.class);
            return CTFontManagerScope.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setRegistrationScope(CTFontManagerScope scope) {
        set(CTFontAttribute.RegistrationScope, CFNumber.valueOf(scope.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontPriority getPriority() {
        if (contains(CTFontAttribute.Priority)) {
            CFNumber val = get(CTFontAttribute.Priority, CFNumber.class);
            return CTFontPriority.valueOf(val.intValue());
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
        if (contains(CTFontAttribute.Enabled)) {
            CFNumber val = get(CTFontAttribute.Enabled, CFNumber.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes setEnabled(boolean enabled) {
        set(CTFontAttribute.Enabled, CFNumber.valueOf(enabled));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isDownloadable() {
        if (contains(CTFontAttribute.Downloadable)) {
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
        if (contains(CTFontAttribute.Downloaded)) {
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
    /*<methods>*/
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
