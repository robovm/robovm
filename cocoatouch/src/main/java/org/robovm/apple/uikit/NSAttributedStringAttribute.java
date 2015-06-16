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
/*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSAttributedStringAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedStringAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSAttributedStringAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSAttributedStringAttribute toObject(Class<NSAttributedStringAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSAttributedStringAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSAttributedStringAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSAttributedStringAttribute> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSAttributedStringAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSAttributedStringAttribute.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSAttributedStringAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSAttributedStringAttribute o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextFont = new NSAttributedStringAttribute("TextFont");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextColor = new NSAttributedStringAttribute("TextColor");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextShadowColor = new NSAttributedStringAttribute("TextShadowColor");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextShadowOffset = new NSAttributedStringAttribute("TextShadowOffset");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute SpeechPunctuation = new NSAttributedStringAttribute("SpeechPunctuation");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute SpeechLanguage = new NSAttributedStringAttribute("SpeechLanguage");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute SpeechPitch = new NSAttributedStringAttribute("SpeechPitch");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Font = new NSAttributedStringAttribute("Font");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute ParagraphStyle = new NSAttributedStringAttribute("ParagraphStyle");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute ForegroundColor = new NSAttributedStringAttribute("ForegroundColor");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute BackgroundColor = new NSAttributedStringAttribute("BackgroundColor");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Ligature = new NSAttributedStringAttribute("Ligature");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Kern = new NSAttributedStringAttribute("Kern");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute StrikethroughStyle = new NSAttributedStringAttribute("StrikethroughStyle");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute UnderlineStyle = new NSAttributedStringAttribute("UnderlineStyle");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute StrokeColor = new NSAttributedStringAttribute("StrokeColor");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute StrokeWidth = new NSAttributedStringAttribute("StrokeWidth");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Shadow = new NSAttributedStringAttribute("Shadow");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute TextEffect = new NSAttributedStringAttribute("TextEffect");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Attachment = new NSAttributedStringAttribute("Attachment");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Link = new NSAttributedStringAttribute("Link");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute BaselineOffset = new NSAttributedStringAttribute("BaselineOffset");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute UnderlineColor = new NSAttributedStringAttribute("UnderlineColor");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute StrikethroughColor = new NSAttributedStringAttribute("StrikethroughColor");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Obliqueness = new NSAttributedStringAttribute("Obliqueness");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Expansion = new NSAttributedStringAttribute("Expansion");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute WritingDirection = new NSAttributedStringAttribute("WritingDirection");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute VerticalGlyphForm = new NSAttributedStringAttribute("VerticalGlyphForm");
    /*</constants>*/
    
    private static /*<name>*/NSAttributedStringAttribute/*</name>*/[] values = new /*<name>*/NSAttributedStringAttribute/*</name>*/[] {/*<value_list>*/TextFont, TextColor, TextShadowColor, TextShadowOffset, SpeechPunctuation, SpeechLanguage, SpeechPitch, Font, ParagraphStyle, ForegroundColor, BackgroundColor, Ligature, Kern, StrikethroughStyle, UnderlineStyle, StrokeColor, StrokeWidth, Shadow, TextEffect, Attachment, Link, BaselineOffset, UnderlineColor, StrikethroughColor, Obliqueness, Expansion, WritingDirection, VerticalGlyphForm/*</value_list>*/};
    
    /*<name>*/NSAttributedStringAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSAttributedStringAttribute/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSAttributedStringAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSAttributedStringAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="UITextAttributeFont", optional=true)
        public static native NSString TextFont();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="UITextAttributeTextColor", optional=true)
        public static native NSString TextColor();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="UITextAttributeTextShadowColor", optional=true)
        public static native NSString TextShadowColor();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="UITextAttributeTextShadowOffset", optional=true)
        public static native NSString TextShadowOffset();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilitySpeechAttributePunctuation", optional=true)
        public static native NSString SpeechPunctuation();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilitySpeechAttributeLanguage", optional=true)
        public static native NSString SpeechLanguage();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilitySpeechAttributePitch", optional=true)
        public static native NSString SpeechPitch();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSFontAttributeName", optional=true)
        public static native NSString Font();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSParagraphStyleAttributeName", optional=true)
        public static native NSString ParagraphStyle();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSForegroundColorAttributeName", optional=true)
        public static native NSString ForegroundColor();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSBackgroundColorAttributeName", optional=true)
        public static native NSString BackgroundColor();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSLigatureAttributeName", optional=true)
        public static native NSString Ligature();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSKernAttributeName", optional=true)
        public static native NSString Kern();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSStrikethroughStyleAttributeName", optional=true)
        public static native NSString StrikethroughStyle();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSUnderlineStyleAttributeName", optional=true)
        public static native NSString UnderlineStyle();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSStrokeColorAttributeName", optional=true)
        public static native NSString StrokeColor();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSStrokeWidthAttributeName", optional=true)
        public static native NSString StrokeWidth();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSShadowAttributeName", optional=true)
        public static native NSString Shadow();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSTextEffectAttributeName", optional=true)
        public static native NSString TextEffect();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSAttachmentAttributeName", optional=true)
        public static native NSString Attachment();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSLinkAttributeName", optional=true)
        public static native NSString Link();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSBaselineOffsetAttributeName", optional=true)
        public static native NSString BaselineOffset();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSUnderlineColorAttributeName", optional=true)
        public static native NSString UnderlineColor();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSStrikethroughColorAttributeName", optional=true)
        public static native NSString StrikethroughColor();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSObliquenessAttributeName", optional=true)
        public static native NSString Obliqueness();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSExpansionAttributeName", optional=true)
        public static native NSString Expansion();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSWritingDirectionAttributeName", optional=true)
        public static native NSString WritingDirection();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSVerticalGlyphFormAttributeName", optional=true)
        public static native NSString VerticalGlyphForm();
        /*</values>*/
    }
}
