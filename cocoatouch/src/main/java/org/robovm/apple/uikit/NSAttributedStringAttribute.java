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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedStringAttribute/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSAttributedStringAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextFont = new NSAttributedStringAttribute("TextFontAttribute");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextColor = new NSAttributedStringAttribute("TextColorAttribute");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextShadowColor = new NSAttributedStringAttribute("TextShadowColorAttribute");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSAttributedStringAttribute TextShadowOffset = new NSAttributedStringAttribute("TextShadowOffsetAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute SpeechPunctuation = new NSAttributedStringAttribute("SpeechPunctuationAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute SpeechLanguage = new NSAttributedStringAttribute("SpeechLanguageAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute SpeechPitch = new NSAttributedStringAttribute("SpeechPitchAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Font = new NSAttributedStringAttribute("FontAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute ParagraphStyle = new NSAttributedStringAttribute("ParagraphStyleAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute ForegroundColor = new NSAttributedStringAttribute("ForegroundColorAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute BackgroundColor = new NSAttributedStringAttribute("BackgroundColorAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Ligature = new NSAttributedStringAttribute("LigatureAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Kern = new NSAttributedStringAttribute("KernAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute StrikethroughStyle = new NSAttributedStringAttribute("StrikethroughStyleAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute UnderlineStyle = new NSAttributedStringAttribute("UnderlineStyleAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute StrokeColor = new NSAttributedStringAttribute("StrokeColorAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute StrokeWidth = new NSAttributedStringAttribute("StrokeWidthAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute Shadow = new NSAttributedStringAttribute("ShadowAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute TextEffect = new NSAttributedStringAttribute("TextEffectAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Attachment = new NSAttributedStringAttribute("AttachmentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Link = new NSAttributedStringAttribute("LinkAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute BaselineOffset = new NSAttributedStringAttribute("BaselineOffsetAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute UnderlineColor = new NSAttributedStringAttribute("UnderlineColorAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute StrikethroughColor = new NSAttributedStringAttribute("StrikethroughColorAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Obliqueness = new NSAttributedStringAttribute("ObliquenessAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute Expansion = new NSAttributedStringAttribute("ExpansionAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringAttribute WritingDirection = new NSAttributedStringAttribute("WritingDirectionAttribute");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSAttributedStringAttribute VerticalGlyphForm = new NSAttributedStringAttribute("VerticalGlyphFormAttribute");
    private static NSAttributedStringAttribute[] values = new NSAttributedStringAttribute[] {Font, ParagraphStyle, ForegroundColor, BackgroundColor, Ligature, Kern, StrikethroughStyle, UnderlineStyle, StrokeColor, StrokeWidth, Shadow, VerticalGlyphForm, TextEffect, Attachment, Link, BaselineOffset, UnderlineColor, StrikethroughColor, Obliqueness, Expansion, WritingDirection, 
        SpeechPunctuation, SpeechPitch, SpeechPitch};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSAttributedStringAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSAttributedStringAttribute valueOf(NSString value) {
        for (NSAttributedStringAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSAttributedStringAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeFont", optional=true)
    protected static native NSString TextFontAttribute();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeTextColor", optional=true)
    protected static native NSString TextColorAttribute();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeTextShadowColor", optional=true)
    protected static native NSString TextShadowColorAttribute();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeTextShadowOffset", optional=true)
    protected static native NSString TextShadowOffsetAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePunctuation", optional=true)
    protected static native NSString SpeechPunctuationAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeechAttributeLanguage", optional=true)
    protected static native NSString SpeechLanguageAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePitch", optional=true)
    protected static native NSString SpeechPitchAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSFontAttributeName", optional=true)
    protected static native NSString FontAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSParagraphStyleAttributeName", optional=true)
    protected static native NSString ParagraphStyleAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSForegroundColorAttributeName", optional=true)
    protected static native NSString ForegroundColorAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSBackgroundColorAttributeName", optional=true)
    protected static native NSString BackgroundColorAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSLigatureAttributeName", optional=true)
    protected static native NSString LigatureAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSKernAttributeName", optional=true)
    protected static native NSString KernAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrikethroughStyleAttributeName", optional=true)
    protected static native NSString StrikethroughStyleAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSUnderlineStyleAttributeName", optional=true)
    protected static native NSString UnderlineStyleAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrokeColorAttributeName", optional=true)
    protected static native NSString StrokeColorAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrokeWidthAttributeName", optional=true)
    protected static native NSString StrokeWidthAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSShadowAttributeName", optional=true)
    protected static native NSString ShadowAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextEffectAttributeName", optional=true)
    protected static native NSString TextEffectAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSAttachmentAttributeName", optional=true)
    protected static native NSString AttachmentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSLinkAttributeName", optional=true)
    protected static native NSString LinkAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSBaselineOffsetAttributeName", optional=true)
    protected static native NSString BaselineOffsetAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSUnderlineColorAttributeName", optional=true)
    protected static native NSString UnderlineColorAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSStrikethroughColorAttributeName", optional=true)
    protected static native NSString StrikethroughColorAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSObliquenessAttributeName", optional=true)
    protected static native NSString ObliquenessAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSExpansionAttributeName", optional=true)
    protected static native NSString ExpansionAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSWritingDirectionAttributeName", optional=true)
    protected static native NSString WritingDirectionAttribute();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSVerticalGlyphFormAttributeName", optional=true)
    protected static native NSString VerticalGlyphFormAttribute();
    /*</methods>*/
}
