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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(/*<name>*/NSAttributedStringDocumentAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedStringDocumentAttributes/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSAttributedStringDocumentAttributes toObject(Class<NSAttributedStringDocumentAttributes> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSAttributedStringDocumentAttributes> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSAttributedStringDocumentAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSAttributedStringDocumentAttributes(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSAttributedStringDocumentAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (NSAttributedStringDocumentAttributes i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSAttributedStringDocumentAttributes(NSDictionary data) {
        super(data);
    }
    public NSAttributedStringDocumentAttributes() {}
    /*</constructors>*/

    public boolean has(String key) {
        return data.containsKey(new NSString(key));
    }
    public NSObject get(String key) {
        if (has(key)) {
            return data.get(new NSString(key));
        }
        return null;
    }
    public NSAttributedStringDocumentAttributes set(String key, NSObject value) {
        data.put(new NSString(key), value);
        return this;
    }
    /*<methods>*/
    public boolean has(NSAttributedStringDocumentAttribute key) {
        return data.containsKey(key.value());
    }
    public NSObject get(NSAttributedStringDocumentAttribute key) {
        if (has(key)) {
            return data.get(key.value());
        }
        return null;
    }
    public NSAttributedStringDocumentAttributes set(NSAttributedStringDocumentAttribute key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSDocumentType getDocumentType() {
        if (has(NSAttributedStringDocumentAttribute.DocumentType)) {
            NSString val = (NSString) get(NSAttributedStringDocumentAttribute.DocumentType);
            return NSDocumentType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setDocumentType(NSDocumentType documentType) {
        set(NSAttributedStringDocumentAttribute.DocumentType, documentType.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSStringEncoding getCharacterEncoding() {
        if (has(NSAttributedStringDocumentAttribute.CharacterEncoding)) {
            NSNumber val = (NSNumber) get(NSAttributedStringDocumentAttribute.CharacterEncoding);
            return NSStringEncoding.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setCharacterEncoding(NSStringEncoding characterEncoding) {
        set(NSAttributedStringDocumentAttribute.CharacterEncoding, NSNumber.valueOf(characterEncoding.value()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes getDefaultAttributes() {
        if (has(NSAttributedStringDocumentAttribute.DefaultAttributes)) {
            NSDictionary val = (NSDictionary) get(NSAttributedStringDocumentAttribute.DefaultAttributes);
            return new NSAttributedStringAttributes(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setDefaultAttributes(NSAttributedStringAttributes defaultAttributes) {
        set(NSAttributedStringDocumentAttribute.DefaultAttributes, defaultAttributes.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGSize getPaperSize() {
        if (has(NSAttributedStringDocumentAttribute.PaperSize)) {
            NSValue val = (NSValue) get(NSAttributedStringDocumentAttribute.PaperSize);
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
        if (has(NSAttributedStringDocumentAttribute.PaperMargin)) {
            NSValue val = (NSValue) get(NSAttributedStringDocumentAttribute.PaperMargin);
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
        if (has(NSAttributedStringDocumentAttribute.ViewSize)) {
            NSValue val = (NSValue) get(NSAttributedStringDocumentAttribute.ViewSize);
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
        if (has(NSAttributedStringDocumentAttribute.ViewZoom)) {
            NSNumber val = (NSNumber) get(NSAttributedStringDocumentAttribute.ViewZoom);
            return val.doubleValue();
        }
        return 0;
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
    public boolean isReadOnly() {
        if (has(NSAttributedStringDocumentAttribute.ReadOnly)) {
            NSNumber val = (NSNumber) get(NSAttributedStringDocumentAttribute.ReadOnly);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setReadOnly(boolean readOnly) {
        set(NSAttributedStringDocumentAttribute.ReadOnly, NSNumber.valueOf(readOnly));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIColor getBackgroundColor() {
        if (has(NSAttributedStringDocumentAttribute.BackgroundColor)) {
            UIColor val = (UIColor) get(NSAttributedStringDocumentAttribute.BackgroundColor);
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
        if (has(NSAttributedStringDocumentAttribute.HyphenationFactor)) {
            NSNumber val = (NSNumber) get(NSAttributedStringDocumentAttribute.HyphenationFactor);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setHyphenationFactor(double hyphenationFactor) {
        set(NSAttributedStringDocumentAttribute.HyphenationFactor, NSNumber.valueOf(hyphenationFactor));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getDefaultTabInterval() {
        if (has(NSAttributedStringDocumentAttribute.DefaultTabInterval)) {
            NSNumber val = (NSNumber) get(NSAttributedStringDocumentAttribute.DefaultTabInterval);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringDocumentAttributes setDefaultTabInterval(double defaultTabInterval) {
        set(NSAttributedStringDocumentAttribute.DefaultTabInterval, NSNumber.valueOf(defaultTabInterval));
        return this;
    }
    /*</methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSDocumentViewMode getViewMode() {
        if (has(NSAttributedStringDocumentAttribute.ViewMode)) {
            NSNumber val = (NSNumber) get(NSAttributedStringDocumentAttribute.ViewMode);
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
    
    @SuppressWarnings("unchecked")
    public List<NSTextLayoutSection> getTextLayoutSections() {
        List<NSTextLayoutSection> list = new ArrayList<>();
        if (has(NSAttributedStringDocumentAttribute.TextLayoutSections)) {
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
        NSArray<NSDictionary<?, ?>> list = new NSMutableArray<>();
        for (NSTextLayoutSection e : sections) {
            list.add(e.getDictionary());
        }
        set(NSAttributedStringDocumentAttribute.TextLayoutSections, list);
        return this;
    }
    
    /*<keys>*/
    /*</keys>*/
}
