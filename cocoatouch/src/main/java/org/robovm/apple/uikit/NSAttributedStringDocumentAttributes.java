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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSAttributedStringDocumentAttributes.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class NSAttributedStringDocumentAttributes 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSAttributedStringDocumentAttributes toObject(Class<NSAttributedStringDocumentAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSAttributedStringDocumentAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(NSAttributedStringDocumentAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    public NSAttributedStringDocumentAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSAttributedStringDocumentAttributes() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSAttributedStringDocumentAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSAttributedStringDocumentAttributes set(String attribute, NSObject value) {
        data.put(new NSString(attribute), value);
        return this;
    }
    public NSAttributedStringDocumentAttributes set(NSAttributedStringDocumentAttribute attribute, NSObject value) {
        data.put(attribute.value(), value);
        return this;
    }
    public NSObject get(String attribute) {
        return data.get(new NSString(attribute));
    }
    public NSObject get(NSAttributedStringDocumentAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(String attribute) {
        return data.containsKey(new NSString(attribute));
    }
    public boolean contains(NSAttributedStringDocumentAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSDocumentType getDocumentType() {
        if (contains(NSAttributedStringDocumentAttribute.DocumentType)) {
            NSString val = (NSString)get(NSAttributedStringDocumentAttribute.DocumentType);
            return NSDocumentType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setDocumentType(NSDocumentType type) {
        set(NSAttributedStringDocumentAttribute.DocumentType, type.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSStringEncoding getCharacterEncoding() {
        if (contains(NSAttributedStringDocumentAttribute.CharacterEncoding)) {
            NSNumber val = (NSNumber)get(NSAttributedStringDocumentAttribute.CharacterEncoding);
            return NSStringEncoding.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setCharacterEncoding(NSStringEncoding encoding) {
        set(NSAttributedStringDocumentAttribute.CharacterEncoding, NSNumber.valueOf((int)encoding.value()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSAttributedStringAttributes getDefaultAttributes() {
        if (contains(NSAttributedStringDocumentAttribute.DefaultAttributes)) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)get(NSAttributedStringDocumentAttribute.DefaultAttributes);
            return new NSAttributedStringAttributes(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setDefaultAttributes(NSAttributedStringAttributes attributes) {
        set(NSAttributedStringDocumentAttribute.DefaultAttributes, attributes.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGSize getPaperSize() {
        if (contains(NSAttributedStringDocumentAttribute.PaperSize)) {
            NSValue val = (NSValue)get(NSAttributedStringDocumentAttribute.PaperSize);
            return val.sizeValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setPaperSize(CGSize paperSize) {
        set(NSAttributedStringDocumentAttribute.PaperSize, NSValue.valueOf(paperSize));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIEdgeInsets getPaperMargin() {
        if (contains(NSAttributedStringDocumentAttribute.PaperMargin)) {
            NSValue val = (NSValue)get(NSAttributedStringDocumentAttribute.PaperMargin);
            return val.edgeInsetsValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setPaperMargin(UIEdgeInsets paperMargin) {
        set(NSAttributedStringDocumentAttribute.PaperMargin, NSValue.valueOf(paperMargin));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGSize getViewSize() {
        if (contains(NSAttributedStringDocumentAttribute.ViewSize)) {
            NSValue val = (NSValue)get(NSAttributedStringDocumentAttribute.ViewSize);
            return val.sizeValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setViewSize(CGSize viewSize) {
        set(NSAttributedStringDocumentAttribute.ViewSize, NSValue.valueOf(viewSize));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getViewZoom() {
        if (contains(NSAttributedStringDocumentAttribute.ViewZoom)) {
            NSNumber val = (NSNumber)get(NSAttributedStringDocumentAttribute.ViewZoom);
            return val.doubleValue();
        }
        return 100;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setViewZoom(double viewZoom) {
        set(NSAttributedStringDocumentAttribute.ViewZoom, NSNumber.valueOf(viewZoom));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSDocumentViewMode getViewMode() {
        if (contains(NSAttributedStringDocumentAttribute.ViewMode)) {
            NSNumber val = (NSNumber)get(NSAttributedStringDocumentAttribute.ViewMode);
            return NSDocumentViewMode.values()[val.intValue()];
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setViewMode(NSDocumentViewMode viewMode) {
        set(NSAttributedStringDocumentAttribute.ViewMode, NSNumber.valueOf(viewMode.ordinal()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isReadOnly() {
        if (contains(NSAttributedStringDocumentAttribute.ReadOnly)) {
            NSNumber val = (NSNumber)get(NSAttributedStringDocumentAttribute.ReadOnly);
            return val.intValue() != 0;
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setReadOnly(boolean readOnly) {
        set(NSAttributedStringDocumentAttribute.ReadOnly, NSNumber.valueOf(readOnly ? 1 : 0));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIColor getBackgroundColor() {
        if (contains(NSAttributedStringDocumentAttribute.BackgroundColor)) {
            UIColor val = (UIColor)get(NSAttributedStringDocumentAttribute.BackgroundColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setBackgroundColor(UIColor backgroundColor) {
        set(NSAttributedStringDocumentAttribute.BackgroundColor, backgroundColor);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getHyphenationFactor() {
        if (contains(NSAttributedStringDocumentAttribute.HyphenationFactor)) {
            NSNumber val = (NSNumber)get(NSAttributedStringDocumentAttribute.HyphenationFactor);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setHyphenationFactor(double factor) {
        set(NSAttributedStringDocumentAttribute.HyphenationFactor, NSNumber.valueOf(factor));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getDefaultTabInterval() {
        if (contains(NSAttributedStringDocumentAttribute.DefaultTabInterval)) {
            NSNumber val = (NSNumber)get(NSAttributedStringDocumentAttribute.DefaultTabInterval);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setDefaultTabInterval(double interval) {
        set(NSAttributedStringDocumentAttribute.DefaultTabInterval, NSNumber.valueOf(interval));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<NSTextLayoutSection> getTextLayoutSections() {
        List<NSTextLayoutSection> list = new ArrayList<>();
        if (contains(NSAttributedStringDocumentAttribute.TextLayoutSections)) {
            NSArray<NSDictionary<NSString, NSObject>> val = (NSArray<NSDictionary<NSString, NSObject>>)get(NSAttributedStringDocumentAttribute.TextLayoutSections);
            for (NSDictionary<NSString, NSObject> e : val) {
                list.add(new NSTextLayoutSection(e));
            }
        }
        return list;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setTextLayoutSections(List<NSTextLayoutSection> sections) {
        NSArray<NSDictionary<NSString, NSObject>> list = new NSMutableArray<>();
        for (NSTextLayoutSection e : sections) {
            list.add(e.getDictionary());
        }
        set(NSAttributedStringDocumentAttribute.TextLayoutSections, list);
        return this;
    }
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
