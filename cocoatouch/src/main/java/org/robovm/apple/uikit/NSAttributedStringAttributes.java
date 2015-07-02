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
@Marshaler(/*<name>*/NSAttributedStringAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedStringAttributes/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSAttributedStringAttributes toObject(Class<NSAttributedStringAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSAttributedStringAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(NSAttributedStringAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSAttributedStringAttributes> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSAttributedStringAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSAttributedStringAttributes(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSAttributedStringAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (NSAttributedStringAttributes i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    public NSAttributedStringAttributes(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public NSAttributedStringAttributes() {}
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
    public NSAttributedStringAttributes set(String key, NSObject value) {
        data.put(new NSString(key), value);
        return this;
    }
    /*<methods>*/
    public boolean has(NSAttributedStringAttribute key) {
        return data.containsKey(key.value());
    }
    public NSObject get(NSAttributedStringAttribute key) {
        if (has(key)) {
            return data.get(key.value());
        }
        return null;
    }
    public NSAttributedStringAttributes set(NSAttributedStringAttribute key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isSpeechPunctuation() {
        if (has(NSAttributedStringAttribute.SpeechPunctuation)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.SpeechPunctuation);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setSpeechPunctuation(boolean speechPunctuation) {
        set(NSAttributedStringAttribute.SpeechPunctuation, NSNumber.valueOf(speechPunctuation));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getSpeechLanguage() {
        if (has(NSAttributedStringAttribute.SpeechLanguage)) {
            NSString val = (NSString) get(NSAttributedStringAttribute.SpeechLanguage);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setSpeechLanguage(String speechLanguage) {
        set(NSAttributedStringAttribute.SpeechLanguage, new NSString(speechLanguage));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getSpeechPitch() {
        if (has(NSAttributedStringAttribute.SpeechPitch)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.SpeechPitch);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setSpeechPitch(double speechPitch) {
        set(NSAttributedStringAttribute.SpeechPitch, NSNumber.valueOf(speechPitch));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIFont getFont() {
        if (has(NSAttributedStringAttribute.Font)) {
            UIFont val = (UIFont) get(NSAttributedStringAttribute.Font);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setFont(UIFont font) {
        set(NSAttributedStringAttribute.Font, font);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSParagraphStyle getParagraphStyle() {
        if (has(NSAttributedStringAttribute.ParagraphStyle)) {
            NSParagraphStyle val = (NSParagraphStyle) get(NSAttributedStringAttribute.ParagraphStyle);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setParagraphStyle(NSParagraphStyle paragraphStyle) {
        set(NSAttributedStringAttribute.ParagraphStyle, paragraphStyle);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getForegroundColor() {
        if (has(NSAttributedStringAttribute.ForegroundColor)) {
            UIColor val = (UIColor) get(NSAttributedStringAttribute.ForegroundColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setForegroundColor(UIColor foregroundColor) {
        set(NSAttributedStringAttribute.ForegroundColor, foregroundColor);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getBackgroundColor() {
        if (has(NSAttributedStringAttribute.BackgroundColor)) {
            UIColor val = (UIColor) get(NSAttributedStringAttribute.BackgroundColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setBackgroundColor(UIColor backgroundColor) {
        set(NSAttributedStringAttribute.BackgroundColor, backgroundColor);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getKerning() {
        if (has(NSAttributedStringAttribute.Kern)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.Kern);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setKerning(double kerning) {
        set(NSAttributedStringAttribute.Kern, NSNumber.valueOf(kerning));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSUnderlineStyle getStrikethroughStyle() {
        if (has(NSAttributedStringAttribute.StrikethroughStyle)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.StrikethroughStyle);
            return NSUnderlineStyle.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setStrikethroughStyle(NSUnderlineStyle strikethroughStyle) {
        set(NSAttributedStringAttribute.StrikethroughStyle, NSNumber.valueOf(strikethroughStyle.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSUnderlineStyle getUnderlineStyle() {
        if (has(NSAttributedStringAttribute.UnderlineStyle)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.UnderlineStyle);
            return NSUnderlineStyle.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setUnderlineStyle(NSUnderlineStyle underlineStyle) {
        set(NSAttributedStringAttribute.UnderlineStyle, NSNumber.valueOf(underlineStyle.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getStrokeColor() {
        if (has(NSAttributedStringAttribute.StrokeColor)) {
            UIColor val = (UIColor) get(NSAttributedStringAttribute.StrokeColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setStrokeColor(UIColor strokeColor) {
        set(NSAttributedStringAttribute.StrokeColor, strokeColor);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getStrokeWidth() {
        if (has(NSAttributedStringAttribute.StrokeWidth)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.StrokeWidth);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setStrokeWidth(double strokeWidth) {
        set(NSAttributedStringAttribute.StrokeWidth, NSNumber.valueOf(strokeWidth));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSShadow getShadow() {
        if (has(NSAttributedStringAttribute.Shadow)) {
            NSShadow val = (NSShadow) get(NSAttributedStringAttribute.Shadow);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setShadow(NSShadow shadow) {
        set(NSAttributedStringAttribute.Shadow, shadow);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSTextEffect getTextEffect() {
        if (has(NSAttributedStringAttribute.TextEffect)) {
            NSString val = (NSString) get(NSAttributedStringAttribute.TextEffect);
            return NSTextEffect.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setTextEffect(NSTextEffect textEffect) {
        set(NSAttributedStringAttribute.TextEffect, textEffect.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSTextAttachment getAttachment() {
        if (has(NSAttributedStringAttribute.Attachment)) {
            NSTextAttachment val = (NSTextAttachment) get(NSAttributedStringAttribute.Attachment);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setAttachment(NSTextAttachment attachment) {
        set(NSAttributedStringAttribute.Attachment, attachment);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSURL getLink() {
        if (has(NSAttributedStringAttribute.Link)) {
            NSURL val = (NSURL) get(NSAttributedStringAttribute.Link);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setLink(NSURL link) {
        set(NSAttributedStringAttribute.Link, link);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getBaselineOffset() {
        if (has(NSAttributedStringAttribute.BaselineOffset)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.BaselineOffset);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setBaselineOffset(double baselineOffset) {
        set(NSAttributedStringAttribute.BaselineOffset, NSNumber.valueOf(baselineOffset));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIColor getUnderlineColor() {
        if (has(NSAttributedStringAttribute.UnderlineColor)) {
            UIColor val = (UIColor) get(NSAttributedStringAttribute.UnderlineColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setUnderlineColor(UIColor underlineColor) {
        set(NSAttributedStringAttribute.UnderlineColor, underlineColor);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIColor getStrikethroughColor() {
        if (has(NSAttributedStringAttribute.StrikethroughColor)) {
            UIColor val = (UIColor) get(NSAttributedStringAttribute.StrikethroughColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setStrikethroughColor(UIColor strikethroughColor) {
        set(NSAttributedStringAttribute.StrikethroughColor, strikethroughColor);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getObliqueness() {
        if (has(NSAttributedStringAttribute.Obliqueness)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.Obliqueness);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setObliqueness(double obliqueness) {
        set(NSAttributedStringAttribute.Obliqueness, NSNumber.valueOf(obliqueness));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getExpansion() {
        if (has(NSAttributedStringAttribute.Expansion)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.Expansion);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setExpansion(double expansion) {
        set(NSAttributedStringAttribute.Expansion, NSNumber.valueOf(expansion));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isVerticalText() {
        if (has(NSAttributedStringAttribute.VerticalGlyphForm)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.VerticalGlyphForm);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setVerticalText(boolean verticalText) {
        set(NSAttributedStringAttribute.VerticalGlyphForm, NSNumber.valueOf(verticalText));
        return this;
    }
    /*</methods>*/
    
    @SuppressWarnings("unchecked")
    public List<NSTextDirection> getWritingDirections() {
        List<NSTextDirection> list = new ArrayList<>();
        if (has(NSAttributedStringAttribute.WritingDirection)) {
            NSArray<NSNumber> val = (NSArray<NSNumber>)get(NSAttributedStringAttribute.WritingDirection);
            for (NSNumber n : val) {
                list.add(NSTextDirection.values()[n.intValue()]);
            }
        }
        return list;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setWritingDirections(List<NSTextDirection> writingDirections) {
        NSArray<NSNumber> list = new NSMutableArray<>();
        for (NSTextDirection n : writingDirections) {
            list.add(NSNumber.valueOf(n.ordinal()));
        }
        set(NSAttributedStringAttribute.WritingDirection, list);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSLigature getLigature() {
        if (has(NSAttributedStringAttribute.Ligature)) {
            NSNumber val = (NSNumber) get(NSAttributedStringAttribute.Ligature);
            return NSLigature.values()[val.intValue()];
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setLigature(NSLigature ligature) {
        set(NSAttributedStringAttribute.Ligature, NSNumber.valueOf(ligature.ordinal()));
        return this;
    }
    
    /*<keys>*/
    /*</keys>*/
}
