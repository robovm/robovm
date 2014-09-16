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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSAttributedStringAttributes.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class NSAttributedStringAttributes 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    public NSAttributedStringAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSAttributedStringAttributes() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSAttributedStringAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSAttributedStringAttributes set(String attribute, NSObject value) {
        data.put(new NSString(attribute), value);
        return this;
    }
    public NSAttributedStringAttributes set(NSAttributedStringAttribute attribute, NSObject value) {
        data.put(attribute.value(), value);
        return this;
    }
    public NSObject get(String attribute) {
        return data.get(new NSString(attribute));
    }
    public NSObject get(NSAttributedStringAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(String attribute) {
        return data.containsKey(new NSString(attribute));
    }
    public boolean contains(NSAttributedStringAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIFont getFont() {
        if (contains(NSAttributedStringAttribute.Font)) {
            UIFont val = (UIFont)get(NSAttributedStringAttribute.Font);
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
        if (contains(NSAttributedStringAttribute.ParagraphStyle)) {
            NSParagraphStyle val = (NSParagraphStyle)get(NSAttributedStringAttribute.ParagraphStyle);
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
        if (contains(NSAttributedStringAttribute.ForegroundColor)) {
            UIColor val = (UIColor)get(NSAttributedStringAttribute.ForegroundColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setForegroundColor(UIColor color) {
        set(NSAttributedStringAttribute.ForegroundColor, color);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getBackgroundColor() {
        if (contains(NSAttributedStringAttribute.BackgroundColor)) {
            UIColor val = (UIColor)get(NSAttributedStringAttribute.BackgroundColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setBackgroundColor(UIColor color) {
        set(NSAttributedStringAttribute.BackgroundColor, color);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSLigature getLigature() {
        if (contains(NSAttributedStringAttribute.Ligature)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.Ligature);
            return NSLigature.values()[val.intValue()];
        }
        return NSLigature.Default;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setLigature(NSLigature ligature) {
        set(NSAttributedStringAttribute.Ligature, NSNumber.valueOf(ligature.ordinal()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getKerning() {
        if (contains(NSAttributedStringAttribute.Kern)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.Kern);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setKerning(double kern) {
        set(NSAttributedStringAttribute.Kern, NSNumber.valueOf(kern));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSUnderlineStyle getStrikethroughStyle() {
        if (contains(NSAttributedStringAttribute.StrikethroughStyle)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.StrikethroughStyle);
            return NSUnderlineStyle.valueOf(val.intValue());
        }
        return NSUnderlineStyle.StyleNone;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setStrikethroughStyle(NSUnderlineStyle style) {
        set(NSAttributedStringAttribute.StrikethroughStyle, NSNumber.valueOf((int)style.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSUnderlineStyle getUnderlineStyle() {
        if (contains(NSAttributedStringAttribute.UnderlineStyle)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.UnderlineStyle);
            return NSUnderlineStyle.valueOf(val.intValue());
        }
        return NSUnderlineStyle.StyleNone;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setUnderlineStyle(NSUnderlineStyle style) {
        set(NSAttributedStringAttribute.UnderlineStyle, NSNumber.valueOf((int)style.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getStrokeColor() {
        if (contains(NSAttributedStringAttribute.StrokeColor)) {
            UIColor val = (UIColor)get(NSAttributedStringAttribute.StrokeColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setStrokeColor(UIColor color) {
        set(NSAttributedStringAttribute.StrokeColor, color);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getStrokeWidth() {
        if (contains(NSAttributedStringAttribute.StrokeWidth)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.StrokeWidth);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setStrokeWidth(double width) {
        set(NSAttributedStringAttribute.StrokeWidth, NSNumber.valueOf(width));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSShadow getShadow() {
        if (contains(NSAttributedStringAttribute.Shadow)) {
            NSShadow val = (NSShadow)get(NSAttributedStringAttribute.Shadow);
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
        if (contains(NSAttributedStringAttribute.TextEffect)) {
            NSString val = (NSString)get(NSAttributedStringAttribute.TextEffect);
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
        if (contains(NSAttributedStringAttribute.Attachment)) {
            NSTextAttachment val = (NSTextAttachment)get(NSAttributedStringAttribute.Attachment);
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
        if (contains(NSAttributedStringAttribute.Link)) {
            NSURL val = (NSURL)get(NSAttributedStringAttribute.Link);
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
        if (contains(NSAttributedStringAttribute.BaselineOffset)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.BaselineOffset);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setBaselineOffset(double offset) {
        set(NSAttributedStringAttribute.BaselineOffset, NSNumber.valueOf(offset));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIColor getUnderlineColor() {
        if (contains(NSAttributedStringAttribute.UnderlineColor)) {
            UIColor val = (UIColor)get(NSAttributedStringAttribute.UnderlineColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setUnderlineColor(UIColor color) {
        set(NSAttributedStringAttribute.UnderlineColor, color);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIColor getStrikethroughColor() {
        if (contains(NSAttributedStringAttribute.StrikethroughColor)) {
            UIColor val = (UIColor)get(NSAttributedStringAttribute.StrikethroughColor);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setStrikethroughColor(UIColor color) {
        set(NSAttributedStringAttribute.StrikethroughColor, color);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getObliqueness() {
        if (contains(NSAttributedStringAttribute.Obliqueness)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.Obliqueness);
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
        if (contains(NSAttributedStringAttribute.Expansion)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.Expansion);
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
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<NSTextDirection> getWritingDirections() {
        List<NSTextDirection> list = new ArrayList<>();
        if (contains(NSAttributedStringAttribute.WritingDirection)) {
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
    public boolean isVerticalText() {
        if (contains(NSAttributedStringAttribute.VerticalGlyphForm)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.VerticalGlyphForm);
            return val.intValue() == 1;
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes setVerticalText(boolean vertical) {
        set(NSAttributedStringAttribute.VerticalGlyphForm, vertical ? NSNumber.valueOf(1) : NSNumber.valueOf(0));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isSpeechPunctuation() {
        if (contains(NSAttributedStringAttribute.SpeechPunctuation)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.SpeechPunctuation);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setSpeechPunctuation(boolean punctuation) {
        set(NSAttributedStringAttribute.SpeechPunctuation, NSNumber.valueOf(punctuation));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getSpeechLanguage() {
        if (contains(NSAttributedStringAttribute.SpeechLanguage)) {
            NSString val = (NSString)get(NSAttributedStringAttribute.SpeechLanguage);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setSpeechLanguage(String language) {
        set(NSAttributedStringAttribute.SpeechLanguage, new NSString(language));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getSpeechPitch() {
        if (contains(NSAttributedStringAttribute.SpeechPitch)) {
            NSNumber val = (NSNumber)get(NSAttributedStringAttribute.SpeechPitch);
            return val.doubleValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes setSpeechPitch(double pitch) {
        set(NSAttributedStringAttribute.SpeechPitch, NSNumber.valueOf(pitch));
        return this;
    }
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
