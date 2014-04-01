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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="NSFontAttributeName")
    public static native NSString AttributeFont();
    @GlobalValue(symbol="NSParagraphStyleAttributeName")
    public static native NSString AttributeParagraphStyle();
    @GlobalValue(symbol="NSForegroundColorAttributeName")
    public static native NSString AttributeForegroundColor();
    @GlobalValue(symbol="NSBackgroundColorAttributeName")
    public static native NSString AttributeBackgroundColor();
    @GlobalValue(symbol="NSLigatureAttributeName")
    public static native NSString AttributeLigature();
    @GlobalValue(symbol="NSKernAttributeName")
    public static native NSString AttributeKern();
    @GlobalValue(symbol="NSStrikethroughStyleAttributeName")
    public static native NSString AttributeStrikethroughStyle();
    @GlobalValue(symbol="NSUnderlineStyleAttributeName")
    public static native NSString AttributeUnderlineStyle();
    @GlobalValue(symbol="NSStrokeColorAttributeName")
    public static native NSString AttributeStrokeColor();
    @GlobalValue(symbol="NSStrokeWidthAttributeName")
    public static native NSString AttributeStrokeWidth();
    @GlobalValue(symbol="NSShadowAttributeName")
    public static native NSString AttributeShadow();
    @GlobalValue(symbol="NSTextEffectAttributeName")
    public static native NSString AttributeTextEffect();
    @GlobalValue(symbol="NSAttachmentAttributeName")
    public static native NSString AttributeAttachment();
    @GlobalValue(symbol="NSLinkAttributeName")
    public static native NSString AttributeLink();
    @GlobalValue(symbol="NSBaselineOffsetAttributeName")
    public static native NSString AttributeBaselineOffset();
    @GlobalValue(symbol="NSUnderlineColorAttributeName")
    public static native NSString AttributeUnderlineColor();
    @GlobalValue(symbol="NSStrikethroughColorAttributeName")
    public static native NSString AttributeStrikethroughColor();
    @GlobalValue(symbol="NSObliquenessAttributeName")
    public static native NSString AttributeObliqueness();
    @GlobalValue(symbol="NSExpansionAttributeName")
    public static native NSString AttributeExpansion();
    @GlobalValue(symbol="NSWritingDirectionAttributeName")
    public static native NSString AttributeWritingDirection();
    @GlobalValue(symbol="NSVerticalGlyphFormAttributeName")
    public static native NSString AttributeVerticalGlyphForm();
    @GlobalValue(symbol="NSTextEffectLetterpressStyle")
    public static native NSString TextEffectLetterpressStyle();
    @GlobalValue(symbol="NSPlainTextDocumentType")
    public static native NSString DocumentTypePlainText();
    @GlobalValue(symbol="NSRTFTextDocumentType")
    public static native NSString DocumentTypeRTFText();
    @GlobalValue(symbol="NSRTFDTextDocumentType")
    public static native NSString DocumentTypeRTFDText();
    @GlobalValue(symbol="NSHTMLTextDocumentType")
    public static native NSString DocumentTypeHTMLText();
    @GlobalValue(symbol="NSTextLayoutSectionOrientation")
    public static native NSString TextLayoutSectionOrientation();
    @GlobalValue(symbol="NSTextLayoutSectionRange")
    public static native NSString TextLayoutSectionRange();
    @GlobalValue(symbol="NSDocumentTypeDocumentAttribute")
    public static native NSString DocumentAttributeDocumentType();
    @GlobalValue(symbol="NSCharacterEncodingDocumentAttribute")
    public static native NSString DocumentAttributeCharacterEncoding();
    @GlobalValue(symbol="NSDefaultAttributesDocumentAttribute")
    public static native NSString DocumentAttributeDefaultAttributes();
    @GlobalValue(symbol="NSPaperSizeDocumentAttribute")
    public static native NSString DocumentAttributePaperSize();
    @GlobalValue(symbol="NSPaperMarginDocumentAttribute")
    public static native NSString DocumentAttributePaperMargin();
    @GlobalValue(symbol="NSViewSizeDocumentAttribute")
    public static native NSString DocumentAttributeViewSize();
    @GlobalValue(symbol="NSViewZoomDocumentAttribute")
    public static native NSString DocumentAttributeViewZoom();
    @GlobalValue(symbol="NSViewModeDocumentAttribute")
    public static native NSString DocumentAttributeViewMode();
    @GlobalValue(symbol="NSReadOnlyDocumentAttribute")
    public static native NSString DocumentAttributeReadOnly();
    @GlobalValue(symbol="NSBackgroundColorDocumentAttribute")
    public static native NSString DocumentAttributeBackgroundColor();
    @GlobalValue(symbol="NSHyphenationFactorDocumentAttribute")
    public static native NSString DocumentAttributeHyphenationFactor();
    @GlobalValue(symbol="NSDefaultTabIntervalDocumentAttribute")
    public static native NSString DocumentAttributeDefaultTabInterval();
    @GlobalValue(symbol="NSTextLayoutSectionsAttribute")
    public static native NSString DocumentAttributeTextLayoutSections();
    
    @Method(selector = "initWithFileURL:options:documentAttributes:error:")
    protected static native @Pointer long initWithFileURL$options$documentAttributes$error$(NSAttributedString thiz, NSURL url, NSDictionary<NSString, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "initWithData:options:documentAttributes:error:")
    protected static native @Pointer long initWithData$options$documentAttributes$error$(NSAttributedString thiz, NSData data, NSDictionary<NSString, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "dataFromRange:documentAttributes:error:")
    public static native NSData getData(NSAttributedString thiz, @ByVal NSRange range, NSDictionary<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "fileWrapperFromRange:documentAttributes:error:")
    public static native NSFileWrapper getFileWrapper(NSAttributedString thiz, @ByVal NSRange range, NSDictionary<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "size")
    public static native @ByVal CGSize getSize(NSAttributedString thiz);
    @Method(selector = "drawAtPoint:")
    public static native void draw(NSAttributedString thiz, @ByVal CGPoint point);
    @Method(selector = "drawInRect:")
    public static native void draw(NSAttributedString thiz, @ByVal CGRect rect);
    @Method(selector = "drawWithRect:options:context:")
    public static native void draw(NSAttributedString thiz, @ByVal CGRect rect, NSStringDrawingOptions options, NSStringDrawingContext context);
    @Method(selector = "boundingRectWithSize:options:context:")
    public static native @ByVal CGRect getBoundingRect(NSAttributedString thiz, @ByVal CGSize size, NSStringDrawingOptions options, NSStringDrawingContext context);
    @Method(selector = "attributedStringWithAttachment:")
    protected static native NSAttributedString createWithAttachment(ObjCClass clazz, NSTextAttachment attachment);
    public static NSAttributedString createWithAttachment(NSTextAttachment attachment) { return createWithAttachment(ObjCClass.getByType(NSAttributedString.class), attachment); }
    /*</methods>*/
}
