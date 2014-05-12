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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSAttributedStringExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAttributedStringExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSAttributedStringExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public static NSAttributedString createFromURL(NSURL url, NSDictionary<NSString, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error) {
        NSAttributedString thiz = alloc(NSAttributedString.class);
        initObject(thiz, initWithFileURL$options$documentAttributes$error$(thiz, url, options, dict, error));
        return thiz;
    }

    public static NSAttributedString createFromData(NSData data, NSDictionary<NSString, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error) {
        NSAttributedString thiz = alloc(NSAttributedString.class);
        initObject(thiz, initWithData$options$documentAttributes$error$(thiz, data, options, dict, error));
        return thiz;
    }

    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSFontAttributeName", optional=true)
    public static native NSString AttributeFont();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSParagraphStyleAttributeName", optional=true)
    public static native NSString AttributeParagraphStyle();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSForegroundColorAttributeName", optional=true)
    public static native NSString AttributeForegroundColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSBackgroundColorAttributeName", optional=true)
    public static native NSString AttributeBackgroundColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSLigatureAttributeName", optional=true)
    public static native NSString AttributeLigature();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSKernAttributeName", optional=true)
    public static native NSString AttributeKern();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrikethroughStyleAttributeName", optional=true)
    public static native NSString AttributeStrikethroughStyle();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSUnderlineStyleAttributeName", optional=true)
    public static native NSString AttributeUnderlineStyle();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrokeColorAttributeName", optional=true)
    public static native NSString AttributeStrokeColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrokeWidthAttributeName", optional=true)
    public static native NSString AttributeStrokeWidth();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSShadowAttributeName", optional=true)
    public static native NSString AttributeShadow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextEffectAttributeName", optional=true)
    public static native NSString AttributeTextEffect();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSAttachmentAttributeName", optional=true)
    public static native NSString AttributeAttachment();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSLinkAttributeName", optional=true)
    public static native NSString AttributeLink();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSBaselineOffsetAttributeName", optional=true)
    public static native NSString AttributeBaselineOffset();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSUnderlineColorAttributeName", optional=true)
    public static native NSString AttributeUnderlineColor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSStrikethroughColorAttributeName", optional=true)
    public static native NSString AttributeStrikethroughColor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSObliquenessAttributeName", optional=true)
    public static native NSString AttributeObliqueness();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSExpansionAttributeName", optional=true)
    public static native NSString AttributeExpansion();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSWritingDirectionAttributeName", optional=true)
    public static native NSString AttributeWritingDirection();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSVerticalGlyphFormAttributeName", optional=true)
    public static native NSString AttributeVerticalGlyphForm();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextEffectLetterpressStyle", optional=true)
    public static native NSString TextEffectLetterpressStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPlainTextDocumentType", optional=true)
    public static native NSString DocumentTypePlainText();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSRTFTextDocumentType", optional=true)
    public static native NSString DocumentTypeRTFText();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSRTFDTextDocumentType", optional=true)
    public static native NSString DocumentTypeRTFDText();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSHTMLTextDocumentType", optional=true)
    public static native NSString DocumentTypeHTMLText();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionOrientation", optional=true)
    public static native NSString TextLayoutSectionOrientation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionRange", optional=true)
    public static native NSString TextLayoutSectionRange();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDocumentTypeDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeDocumentType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSCharacterEncodingDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeCharacterEncoding();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDefaultAttributesDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeDefaultAttributes();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPaperSizeDocumentAttribute", optional=true)
    public static native NSString DocumentAttributePaperSize();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPaperMarginDocumentAttribute", optional=true)
    public static native NSString DocumentAttributePaperMargin();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewSizeDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeViewSize();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewZoomDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeViewZoom();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewModeDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeViewMode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSReadOnlyDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeReadOnly();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSBackgroundColorDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeBackgroundColor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSHyphenationFactorDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeHyphenationFactor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDefaultTabIntervalDocumentAttribute", optional=true)
    public static native NSString DocumentAttributeDefaultTabInterval();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionsAttribute", optional=true)
    public static native NSString DocumentAttributeTextLayoutSections();
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithFileURL:options:documentAttributes:error:")
    protected static native @Pointer long initWithFileURL$options$documentAttributes$error$(NSAttributedString thiz, NSURL url, NSDictionary<NSString, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithData:options:documentAttributes:error:")
    protected static native @Pointer long initWithData$options$documentAttributes$error$(NSAttributedString thiz, NSData data, NSDictionary<NSString, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dataFromRange:documentAttributes:error:")
    public static native NSData getData(NSAttributedString thiz, @ByVal NSRange range, NSDictionary<?, ?> dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "fileWrapperFromRange:documentAttributes:error:")
    public static native NSFileWrapper getFileWrapper(NSAttributedString thiz, @ByVal NSRange range, NSDictionary<?, ?> dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "size")
    public static native @ByVal CGSize getSize(NSAttributedString thiz);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "drawAtPoint:")
    public static native void draw(NSAttributedString thiz, @ByVal CGPoint point);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "drawInRect:")
    public static native void draw(NSAttributedString thiz, @ByVal CGRect rect);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "drawWithRect:options:context:")
    public static native void draw(NSAttributedString thiz, @ByVal CGRect rect, NSStringDrawingOptions options, NSStringDrawingContext context);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "boundingRectWithSize:options:context:")
    public static native @ByVal CGRect getBoundingRect(NSAttributedString thiz, @ByVal CGSize size, NSStringDrawingOptions options, NSStringDrawingContext context);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "attributedStringWithAttachment:")
    protected static native NSAttributedString createWithAttachment(ObjCClass clazz, NSTextAttachment attachment);
    public static NSAttributedString createWithAttachment(NSTextAttachment attachment) { return createWithAttachment(ObjCClass.getByType(NSAttributedString.class), attachment); }
    /*</methods>*/
}
