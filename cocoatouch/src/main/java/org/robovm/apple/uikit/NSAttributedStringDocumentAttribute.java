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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedStringDocumentAttribute/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSAttributedStringDocumentAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute DocumentType = new NSAttributedStringDocumentAttribute("DocumentTypeDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute CharacterEncoding = new NSAttributedStringDocumentAttribute("CharacterEncodingDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute DefaultAttributes = new NSAttributedStringDocumentAttribute("DefaultAttributesDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute PaperSize = new NSAttributedStringDocumentAttribute("PaperSizeDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute PaperMargin = new NSAttributedStringDocumentAttribute("PaperMarginDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute ViewSize = new NSAttributedStringDocumentAttribute("ViewSizeDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute ViewZoom = new NSAttributedStringDocumentAttribute("ViewZoomDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute ViewMode = new NSAttributedStringDocumentAttribute("ViewModeDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute ReadOnly = new NSAttributedStringDocumentAttribute("ReadOnlyDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute BackgroundColor = new NSAttributedStringDocumentAttribute("BackgroundColorDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute HyphenationFactor = new NSAttributedStringDocumentAttribute("HyphenationFactorDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute DefaultTabInterval = new NSAttributedStringDocumentAttribute("DefaultTabIntervalDocumentAttribute");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSAttributedStringDocumentAttribute TextLayoutSections = new NSAttributedStringDocumentAttribute("TextLayoutSectionsAttribute");
    private static NSAttributedStringDocumentAttribute[] values = new NSAttributedStringDocumentAttribute[] {DocumentType, CharacterEncoding, DefaultAttributes, PaperSize, PaperMargin, ViewSize, 
        ViewZoom, ViewMode, ReadOnly, BackgroundColor, HyphenationFactor, DefaultTabInterval};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSAttributedStringDocumentAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSAttributedStringDocumentAttribute valueOf(NSString value) {
        for (NSAttributedStringDocumentAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSAttributedStringDocumentAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDocumentTypeDocumentAttribute", optional=true)
    protected static native NSString DocumentTypeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSCharacterEncodingDocumentAttribute", optional=true)
    protected static native NSString CharacterEncodingDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDefaultAttributesDocumentAttribute", optional=true)
    protected static native NSString DefaultAttributesDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPaperSizeDocumentAttribute", optional=true)
    protected static native NSString PaperSizeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPaperMarginDocumentAttribute", optional=true)
    protected static native NSString PaperMarginDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewSizeDocumentAttribute", optional=true)
    protected static native NSString ViewSizeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewZoomDocumentAttribute", optional=true)
    protected static native NSString ViewZoomDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewModeDocumentAttribute", optional=true)
    protected static native NSString ViewModeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSReadOnlyDocumentAttribute", optional=true)
    protected static native NSString ReadOnlyDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSBackgroundColorDocumentAttribute", optional=true)
    protected static native NSString BackgroundColorDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSHyphenationFactorDocumentAttribute", optional=true)
    protected static native NSString HyphenationFactorDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDefaultTabIntervalDocumentAttribute", optional=true)
    protected static native NSString DefaultTabIntervalDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionsAttribute", optional=true)
    protected static native NSString TextLayoutSectionsAttribute();
    /*</methods>*/
}
