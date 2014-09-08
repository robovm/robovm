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
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    public CTFontAttributes() {
        this.data = CFMutableDictionary.create();
    }
    protected CTFontAttributes(CFDictionary data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CTFontAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected CFDictionary getDictionary() {
        return data;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSURL getURL() {
        if (data.containsKey(CTFontAttribute.URLValue())) {
            NSURL val = data.get(CTFontAttribute.URLValue(), NSURL.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setURL(NSURL url) {
        data.put(CTFontAttribute.URLValue(), url);
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getName() {
        if (data.containsKey(CTFontAttribute.NameValue())) {
            CFString val = data.get(CTFontAttribute.NameValue(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setName(String name) {
        data.put(CTFontAttribute.NameValue(), new CFString(name));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getDisplayName() {
        if (data.containsKey(CTFontAttribute.DisplayNameValue())) {
            CFString val = data.get(CTFontAttribute.DisplayNameValue(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setDisplayName(String displayName) {
        data.put(CTFontAttribute.DisplayNameValue(), new CFString(displayName));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getStyleName() {
        if (data.containsKey(CTFontAttribute.StyleNameValue())) {
            CFString val = data.get(CTFontAttribute.StyleNameValue(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setStyleName(String styleName) {
        data.put(CTFontAttribute.StyleNameValue(), new CFString(styleName));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits getTraits() {
        if (data.containsKey(CTFontAttribute.TraitsValue())) {
            CFDictionary val = data.get(CTFontAttribute.TraitsValue(), CFDictionary.class);
            return new CTFontTraits(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setTraits(CTFontTraits traits) {
        data.put(CTFontAttribute.TraitsValue(), traits.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes getVariationAxes() {
        if (data.containsKey(CTFontAttribute.VariationValue())) {
            CFDictionary val = data.get(CTFontAttribute.VariationValue(), CFDictionary.class);
            return new CTFontVariationAxes(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setVariationAxes(CTFontVariationAxes variationAxes) {
        data.put(CTFontAttribute.VariationValue(), variationAxes.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getSize() {
        if (data.containsKey(CTFontAttribute.SizeValue())) {
            CFNumber val = data.get(CTFontAttribute.SizeValue(), CFNumber.class);
            return val.floatValue();
        }
        return 12;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setSize(float size) {
        data.put(CTFontAttribute.SizeValue(), CFNumber.valueOf(size));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @ByVal CGAffineTransform getMatrix() {
        if (data.containsKey(CTFontAttribute.MatrixValue())) {
            NSData val = data.get(CTFontAttribute.MatrixValue(), NSData.class);
            return val.getStructData(CGAffineTransform.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setMatrix(@ByVal CGAffineTransform matrix) {
        data.put(CTFontAttribute.MatrixValue(), new NSData(matrix));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getCascadeList() {
        if (data.containsKey(CTFontAttribute.CascadeListValue())) {
            CFArray val = data.get(CTFontAttribute.CascadeListValue(), CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return new ArrayList<CTFontDescriptor>();
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setCascadeList(List<CTFontDescriptor> list) {
        data.put(CTFontAttribute.CascadeListValue(), CFArray.create(list));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSCharacterSet getCharacterSet() {
        if (data.containsKey(CTFontAttribute.CharacterSetValue())) {
            NSCharacterSet val = data.get(CTFontAttribute.CharacterSetValue(), NSCharacterSet.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setCharacterSet(NSCharacterSet characterSet) {
        data.put(CTFontAttribute.CharacterSetValue(), characterSet);
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<String> getLanguages() {
        List<String> list = new ArrayList<>();
        if (data.containsKey(CTFontAttribute.LanguagesValue())) {
            CFArray val = data.get(CTFontAttribute.LanguagesValue(), CFArray.class);
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
    public void setLanguages(List<String> languages) {
        data.put(CTFontAttribute.LanguagesValue(), NSArray.fromStrings(languages));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getBaselineAdjust() {
        if (data.containsKey(CTFontAttribute.BaselineAdjustValue())) {
            CFNumber val = data.get(CTFontAttribute.BaselineAdjustValue(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setBaselineAdjust(float baselineAdjust) {
        data.put(CTFontAttribute.BaselineAdjustValue(), CFNumber.valueOf(baselineAdjust));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public long getMacintoshEncodings() {
        if (data.containsKey(CTFontAttribute.MacintoshEncodingsValue())) {
            CFNumber val = data.get(CTFontAttribute.MacintoshEncodingsValue(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setMacintoshEncodings(long encodingBits) {
        data.put(CTFontAttribute.MacintoshEncodingsValue(), CFNumber.valueOf(encodingBits));
    }

//    const CFStringRef kCTFontFeaturesAttribute;
//    const CFStringRef kCTFontFeatureSettingsAttribute; TODO
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getFixedAdvance() {
        if (data.containsKey(CTFontAttribute.FixedAdvanceValue())) {
            CFNumber val = data.get(CTFontAttribute.FixedAdvanceValue(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setFixedAdvance(float fixedAdvance) {
        data.put(CTFontAttribute.FixedAdvanceValue(), CFNumber.valueOf(fixedAdvance));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontOrientation getOrientation() {
        if (data.containsKey(CTFontAttribute.OrientationValue())) {
            CFNumber val = data.get(CTFontAttribute.OrientationValue(), CFNumber.class);
            return CTFontOrientation.valueOf(val.intValue());
        }
        return CTFontOrientation.DefaultOrientation;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setOrientation(CTFontOrientation orientation) {
        data.put(CTFontAttribute.OrientationValue(), CFNumber.valueOf(orientation.value()));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontFormat getFormat() {
        if (data.containsKey(CTFontAttribute.FormatValue())) {
            CFNumber val = data.get(CTFontAttribute.FormatValue(), CFNumber.class);
            return CTFontFormat.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setFormat(CTFontFormat format) {
        data.put(CTFontAttribute.FormatValue(), CFNumber.valueOf(format.value()));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontManagerScope getRegistrationScope() {
        if (data.containsKey(CTFontAttribute.RegistrationScopeValue())) {
            CFNumber val = data.get(CTFontAttribute.RegistrationScopeValue(), CFNumber.class);
            return CTFontManagerScope.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setRegistrationScope(CTFontManagerScope scope) {
        data.put(CTFontAttribute.RegistrationScopeValue(), CFNumber.valueOf(scope.value()));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontPriority getPriority() {
        if (data.containsKey(CTFontAttribute.PriorityValue())) {
            CFNumber val = data.get(CTFontAttribute.PriorityValue(), CFNumber.class);
            return CTFontPriority.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setPriority(CTFontPriority priority) {
        data.put(CTFontAttribute.PriorityValue(), CFNumber.valueOf(priority.value()));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public boolean isEnabled() {
        if (data.containsKey(CTFontAttribute.EnabledValue())) {
            CFNumber val = data.get(CTFontAttribute.EnabledValue(), CFNumber.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setEnabled(boolean enabled) {
        data.put(CTFontAttribute.EnabledValue(), CFNumber.valueOf(enabled));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isDownloadable() {
        if (data.containsKey(CTFontAttribute.DownloadableValue())) {
            CFBoolean val = data.get(CTFontAttribute.DownloadableValue(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setDownloadable(boolean downloadable) {
        data.put(CTFontAttribute.DownloadableValue(), CFBoolean.valueOf(downloadable));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isDownloaded() {
        if (data.containsKey(CTFontAttribute.DownloadedValue())) {
            CFBoolean val = data.get(CTFontAttribute.DownloadedValue(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setDownloaded(boolean downloaded) {
        data.put(CTFontAttribute.DownloadedValue(), CFBoolean.valueOf(downloaded));
    }
    /*<methods>*/
    /*</methods>*/
}
