/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html">NSParagraphStyle Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ NSParagraphStyle /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSParagraphStyle /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSParagraphStyle /*</name>*/.class);

    /*<constructors>*/
    protected NSParagraphStyle(SkipInit skipInit) { super(skipInit); }
    public NSParagraphStyle() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alignment = Selector.register("alignment");
    @Bridge private native static NSTextAlignment objc_getAlignment(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static NSTextAlignment objc_getAlignmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/alignment">@property(readonly) NSTextAlignment alignment</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSTextAlignment getAlignment() {
        if (customClass) { return objc_getAlignmentSuper(getSuper(), alignment); } else { return objc_getAlignment(this, alignment); }
    }
    
    private static final Selector baseWritingDirection = Selector.register("baseWritingDirection");
    @Bridge private native static NSWritingDirection objc_getBaseWritingDirection(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static NSWritingDirection objc_getBaseWritingDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/baseWritingDirection">@property(readonly) NSWritingDirection baseWritingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSWritingDirection getBaseWritingDirection() {
        if (customClass) { return objc_getBaseWritingDirectionSuper(getSuper(), baseWritingDirection); } else { return objc_getBaseWritingDirection(this, baseWritingDirection); }
    }
    
    private static final Selector firstLineHeadIndent = Selector.register("firstLineHeadIndent");
    @Bridge private native static float objc_getFirstLineHeadIndent(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getFirstLineHeadIndentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/firstLineHeadIndent">@property(readonly) CGFloat firstLineHeadIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getFirstLineHeadIndent() {
        if (customClass) { return objc_getFirstLineHeadIndentSuper(getSuper(), firstLineHeadIndent); } else { return objc_getFirstLineHeadIndent(this, firstLineHeadIndent); }
    }
    
    private static final Selector headIndent = Selector.register("headIndent");
    @Bridge private native static float objc_getHeadIndent(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getHeadIndentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/headIndent">@property(readonly) CGFloat headIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getHeadIndent() {
        if (customClass) { return objc_getHeadIndentSuper(getSuper(), headIndent); } else { return objc_getHeadIndent(this, headIndent); }
    }
    
    private static final Selector hyphenationFactor = Selector.register("hyphenationFactor");
    @Bridge private native static float objc_getHyphenationFactor(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getHyphenationFactorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/hyphenationFactor">@property(readonly) float hyphenationFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getHyphenationFactor() {
        if (customClass) { return objc_getHyphenationFactorSuper(getSuper(), hyphenationFactor); } else { return objc_getHyphenationFactor(this, hyphenationFactor); }
    }
    
    private static final Selector lineBreakMode = Selector.register("lineBreakMode");
    @Bridge private native static NSLineBreakMode objc_getLineBreakMode(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static NSLineBreakMode objc_getLineBreakModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/lineBreakMode">@property(readonly) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSLineBreakMode getLineBreakMode() {
        if (customClass) { return objc_getLineBreakModeSuper(getSuper(), lineBreakMode); } else { return objc_getLineBreakMode(this, lineBreakMode); }
    }
    
    private static final Selector lineHeightMultiple = Selector.register("lineHeightMultiple");
    @Bridge private native static float objc_getLineHeightMultiple(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getLineHeightMultipleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/lineHeightMultiple">@property(readonly) CGFloat lineHeightMultiple</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getLineHeightMultiple() {
        if (customClass) { return objc_getLineHeightMultipleSuper(getSuper(), lineHeightMultiple); } else { return objc_getLineHeightMultiple(this, lineHeightMultiple); }
    }
    
    private static final Selector lineSpacing = Selector.register("lineSpacing");
    @Bridge private native static float objc_getLineSpacing(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getLineSpacingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/lineSpacing">@property(readonly) CGFloat lineSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getLineSpacing() {
        if (customClass) { return objc_getLineSpacingSuper(getSuper(), lineSpacing); } else { return objc_getLineSpacing(this, lineSpacing); }
    }
    
    private static final Selector maximumLineHeight = Selector.register("maximumLineHeight");
    @Bridge private native static float objc_getMaximumLineHeight(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getMaximumLineHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/maximumLineHeight">@property(readonly) CGFloat maximumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMaximumLineHeight() {
        if (customClass) { return objc_getMaximumLineHeightSuper(getSuper(), maximumLineHeight); } else { return objc_getMaximumLineHeight(this, maximumLineHeight); }
    }
    
    private static final Selector minimumLineHeight = Selector.register("minimumLineHeight");
    @Bridge private native static float objc_getMinimumLineHeight(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumLineHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/minimumLineHeight">@property(readonly) CGFloat minimumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMinimumLineHeight() {
        if (customClass) { return objc_getMinimumLineHeightSuper(getSuper(), minimumLineHeight); } else { return objc_getMinimumLineHeight(this, minimumLineHeight); }
    }
    
    private static final Selector paragraphSpacing = Selector.register("paragraphSpacing");
    @Bridge private native static float objc_getParagraphSpacing(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getParagraphSpacingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/paragraphSpacing">@property(readonly) CGFloat paragraphSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getParagraphSpacing() {
        if (customClass) { return objc_getParagraphSpacingSuper(getSuper(), paragraphSpacing); } else { return objc_getParagraphSpacing(this, paragraphSpacing); }
    }
    
    private static final Selector paragraphSpacingBefore = Selector.register("paragraphSpacingBefore");
    @Bridge private native static float objc_getParagraphSpacingBefore(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getParagraphSpacingBeforeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/paragraphSpacingBefore">@property(readonly) CGFloat paragraphSpacingBefore</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getParagraphSpacingBefore() {
        if (customClass) { return objc_getParagraphSpacingBeforeSuper(getSuper(), paragraphSpacingBefore); } else { return objc_getParagraphSpacingBefore(this, paragraphSpacingBefore); }
    }
    
    private static final Selector tailIndent = Selector.register("tailIndent");
    @Bridge private native static float objc_getTailIndent(NSParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getTailIndentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/tailIndent">@property(readonly) CGFloat tailIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getTailIndent() {
        if (customClass) { return objc_getTailIndentSuper(getSuper(), tailIndent); } else { return objc_getTailIndent(this, tailIndent); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector defaultParagraphStyle = Selector.register("defaultParagraphStyle");
    @Bridge private native static NSParagraphStyle objc_defaultParagraphStyle(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/clm/NSParagraphStyle/defaultParagraphStyle">+ (NSParagraphStyle *)defaultParagraphStyle</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSParagraphStyle defaultParagraphStyle() {
        return objc_defaultParagraphStyle(objCClass, defaultParagraphStyle);
    }
    
    private static final Selector defaultWritingDirectionForLanguage$ = Selector.register("defaultWritingDirectionForLanguage:");
    @Bridge private native static NSWritingDirection objc_defaultWritingDirectionForLanguage(ObjCClass __self__, Selector __cmd__, String languageName);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/clm/NSParagraphStyle/defaultWritingDirectionForLanguage:">+ (NSWritingDirection)defaultWritingDirectionForLanguage:(NSString *)languageName</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSWritingDirection defaultWritingDirectionForLanguage(String languageName) {
        return objc_defaultWritingDirectionForLanguage(objCClass, defaultWritingDirectionForLanguage$, languageName);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alignment") public static NSTextAlignment getAlignment(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getAlignment(); }
        @Callback @BindSelector("baseWritingDirection") public static NSWritingDirection getBaseWritingDirection(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getBaseWritingDirection(); }
        @Callback @BindSelector("firstLineHeadIndent") public static float getFirstLineHeadIndent(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getFirstLineHeadIndent(); }
        @Callback @BindSelector("headIndent") public static float getHeadIndent(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getHeadIndent(); }
        @Callback @BindSelector("hyphenationFactor") public static float getHyphenationFactor(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getHyphenationFactor(); }
        @Callback @BindSelector("lineBreakMode") public static NSLineBreakMode getLineBreakMode(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getLineBreakMode(); }
        @Callback @BindSelector("lineHeightMultiple") public static float getLineHeightMultiple(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getLineHeightMultiple(); }
        @Callback @BindSelector("lineSpacing") public static float getLineSpacing(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getLineSpacing(); }
        @Callback @BindSelector("maximumLineHeight") public static float getMaximumLineHeight(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getMaximumLineHeight(); }
        @Callback @BindSelector("minimumLineHeight") public static float getMinimumLineHeight(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getMinimumLineHeight(); }
        @Callback @BindSelector("paragraphSpacing") public static float getParagraphSpacing(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getParagraphSpacing(); }
        @Callback @BindSelector("paragraphSpacingBefore") public static float getParagraphSpacingBefore(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getParagraphSpacingBefore(); }
        @Callback @BindSelector("tailIndent") public static float getTailIndent(NSParagraphStyle __self__, Selector __cmd__) { return __self__.getTailIndent(); }
    }
    /*</callbacks>*/

}
