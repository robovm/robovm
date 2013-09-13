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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html">NSMutableParagraphStyle Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ NSMutableParagraphStyle /*</name>*/ 
    extends /*<extends>*/ NSParagraphStyle /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSMutableParagraphStyle /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSMutableParagraphStyle /*</name>*/.class);

    /*<constructors>*/
    protected NSMutableParagraphStyle(SkipInit skipInit) { super(skipInit); }
    public NSMutableParagraphStyle() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alignment = Selector.register("alignment");
    @Bridge private native static NSTextAlignment objc_getAlignment(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static NSTextAlignment objc_getAlignmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/alignment">@property(readwrite) NSTextAlignment alignment</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSTextAlignment getAlignment() {
        if (customClass) { return objc_getAlignmentSuper(getSuper(), alignment); } else { return objc_getAlignment(this, alignment); }
    }
    
    private static final Selector setAlignment$ = Selector.register("setAlignment:");
    @Bridge private native static void objc_setAlignment(NSMutableParagraphStyle __self__, Selector __cmd__, NSTextAlignment alignment);
    @Bridge private native static void objc_setAlignmentSuper(ObjCSuper __super__, Selector __cmd__, NSTextAlignment alignment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/alignment">@property(readwrite) NSTextAlignment alignment</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAlignment(NSTextAlignment alignment) {
        if (customClass) { objc_setAlignmentSuper(getSuper(), setAlignment$, alignment); } else { objc_setAlignment(this, setAlignment$, alignment); }
    }
    
    private static final Selector baseWritingDirection = Selector.register("baseWritingDirection");
    @Bridge private native static NSWritingDirection objc_getBaseWritingDirection(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static NSWritingDirection objc_getBaseWritingDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/baseWritingDirection">@property(readwrite) NSWritingDirection baseWritingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSWritingDirection getBaseWritingDirection() {
        if (customClass) { return objc_getBaseWritingDirectionSuper(getSuper(), baseWritingDirection); } else { return objc_getBaseWritingDirection(this, baseWritingDirection); }
    }
    
    private static final Selector setBaseWritingDirection$ = Selector.register("setBaseWritingDirection:");
    @Bridge private native static void objc_setBaseWritingDirection(NSMutableParagraphStyle __self__, Selector __cmd__, NSWritingDirection baseWritingDirection);
    @Bridge private native static void objc_setBaseWritingDirectionSuper(ObjCSuper __super__, Selector __cmd__, NSWritingDirection baseWritingDirection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/baseWritingDirection">@property(readwrite) NSWritingDirection baseWritingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBaseWritingDirection(NSWritingDirection baseWritingDirection) {
        if (customClass) { objc_setBaseWritingDirectionSuper(getSuper(), setBaseWritingDirection$, baseWritingDirection); } else { objc_setBaseWritingDirection(this, setBaseWritingDirection$, baseWritingDirection); }
    }
    
    private static final Selector firstLineHeadIndent = Selector.register("firstLineHeadIndent");
    @Bridge private native static float objc_getFirstLineHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getFirstLineHeadIndentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/firstLineHeadIndent">@property(readwrite) CGFloat firstLineHeadIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getFirstLineHeadIndent() {
        if (customClass) { return objc_getFirstLineHeadIndentSuper(getSuper(), firstLineHeadIndent); } else { return objc_getFirstLineHeadIndent(this, firstLineHeadIndent); }
    }
    
    private static final Selector setFirstLineHeadIndent$ = Selector.register("setFirstLineHeadIndent:");
    @Bridge private native static void objc_setFirstLineHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__, float firstLineHeadIndent);
    @Bridge private native static void objc_setFirstLineHeadIndentSuper(ObjCSuper __super__, Selector __cmd__, float firstLineHeadIndent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/firstLineHeadIndent">@property(readwrite) CGFloat firstLineHeadIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setFirstLineHeadIndent(float firstLineHeadIndent) {
        if (customClass) { objc_setFirstLineHeadIndentSuper(getSuper(), setFirstLineHeadIndent$, firstLineHeadIndent); } else { objc_setFirstLineHeadIndent(this, setFirstLineHeadIndent$, firstLineHeadIndent); }
    }
    
    private static final Selector headIndent = Selector.register("headIndent");
    @Bridge private native static float objc_getHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getHeadIndentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/headIndent">@property(readwrite) CGFloat headIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getHeadIndent() {
        if (customClass) { return objc_getHeadIndentSuper(getSuper(), headIndent); } else { return objc_getHeadIndent(this, headIndent); }
    }
    
    private static final Selector setHeadIndent$ = Selector.register("setHeadIndent:");
    @Bridge private native static void objc_setHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__, float headIndent);
    @Bridge private native static void objc_setHeadIndentSuper(ObjCSuper __super__, Selector __cmd__, float headIndent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/headIndent">@property(readwrite) CGFloat headIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setHeadIndent(float headIndent) {
        if (customClass) { objc_setHeadIndentSuper(getSuper(), setHeadIndent$, headIndent); } else { objc_setHeadIndent(this, setHeadIndent$, headIndent); }
    }
    
    private static final Selector hyphenationFactor = Selector.register("hyphenationFactor");
    @Bridge private native static float objc_getHyphenationFactor(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getHyphenationFactorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/hyphenationFactor">@property(readwrite) float hyphenationFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getHyphenationFactor() {
        if (customClass) { return objc_getHyphenationFactorSuper(getSuper(), hyphenationFactor); } else { return objc_getHyphenationFactor(this, hyphenationFactor); }
    }
    
    private static final Selector setHyphenationFactor$ = Selector.register("setHyphenationFactor:");
    @Bridge private native static void objc_setHyphenationFactor(NSMutableParagraphStyle __self__, Selector __cmd__, float hyphenationFactor);
    @Bridge private native static void objc_setHyphenationFactorSuper(ObjCSuper __super__, Selector __cmd__, float hyphenationFactor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/hyphenationFactor">@property(readwrite) float hyphenationFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setHyphenationFactor(float hyphenationFactor) {
        if (customClass) { objc_setHyphenationFactorSuper(getSuper(), setHyphenationFactor$, hyphenationFactor); } else { objc_setHyphenationFactor(this, setHyphenationFactor$, hyphenationFactor); }
    }
    
    private static final Selector lineBreakMode = Selector.register("lineBreakMode");
    @Bridge private native static NSLineBreakMode objc_getLineBreakMode(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static NSLineBreakMode objc_getLineBreakModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineBreakMode">@property(readwrite) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSLineBreakMode getLineBreakMode() {
        if (customClass) { return objc_getLineBreakModeSuper(getSuper(), lineBreakMode); } else { return objc_getLineBreakMode(this, lineBreakMode); }
    }
    
    private static final Selector setLineBreakMode$ = Selector.register("setLineBreakMode:");
    @Bridge private native static void objc_setLineBreakMode(NSMutableParagraphStyle __self__, Selector __cmd__, NSLineBreakMode lineBreakMode);
    @Bridge private native static void objc_setLineBreakModeSuper(ObjCSuper __super__, Selector __cmd__, NSLineBreakMode lineBreakMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineBreakMode">@property(readwrite) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setLineBreakMode(NSLineBreakMode lineBreakMode) {
        if (customClass) { objc_setLineBreakModeSuper(getSuper(), setLineBreakMode$, lineBreakMode); } else { objc_setLineBreakMode(this, setLineBreakMode$, lineBreakMode); }
    }
    
    private static final Selector lineHeightMultiple = Selector.register("lineHeightMultiple");
    @Bridge private native static float objc_getLineHeightMultiple(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getLineHeightMultipleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineHeightMultiple">@property(readwrite) CGFloat lineHeightMultiple</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getLineHeightMultiple() {
        if (customClass) { return objc_getLineHeightMultipleSuper(getSuper(), lineHeightMultiple); } else { return objc_getLineHeightMultiple(this, lineHeightMultiple); }
    }
    
    private static final Selector setLineHeightMultiple$ = Selector.register("setLineHeightMultiple:");
    @Bridge private native static void objc_setLineHeightMultiple(NSMutableParagraphStyle __self__, Selector __cmd__, float lineHeightMultiple);
    @Bridge private native static void objc_setLineHeightMultipleSuper(ObjCSuper __super__, Selector __cmd__, float lineHeightMultiple);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineHeightMultiple">@property(readwrite) CGFloat lineHeightMultiple</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setLineHeightMultiple(float lineHeightMultiple) {
        if (customClass) { objc_setLineHeightMultipleSuper(getSuper(), setLineHeightMultiple$, lineHeightMultiple); } else { objc_setLineHeightMultiple(this, setLineHeightMultiple$, lineHeightMultiple); }
    }
    
    private static final Selector lineSpacing = Selector.register("lineSpacing");
    @Bridge private native static float objc_getLineSpacing(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getLineSpacingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineSpacing">@property(readwrite) CGFloat lineSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getLineSpacing() {
        if (customClass) { return objc_getLineSpacingSuper(getSuper(), lineSpacing); } else { return objc_getLineSpacing(this, lineSpacing); }
    }
    
    private static final Selector setLineSpacing$ = Selector.register("setLineSpacing:");
    @Bridge private native static void objc_setLineSpacing(NSMutableParagraphStyle __self__, Selector __cmd__, float lineSpacing);
    @Bridge private native static void objc_setLineSpacingSuper(ObjCSuper __super__, Selector __cmd__, float lineSpacing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineSpacing">@property(readwrite) CGFloat lineSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setLineSpacing(float lineSpacing) {
        if (customClass) { objc_setLineSpacingSuper(getSuper(), setLineSpacing$, lineSpacing); } else { objc_setLineSpacing(this, setLineSpacing$, lineSpacing); }
    }
    
    private static final Selector maximumLineHeight = Selector.register("maximumLineHeight");
    @Bridge private native static float objc_getMaximumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getMaximumLineHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/maximumLineHeight">@property(readwrite) CGFloat maximumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMaximumLineHeight() {
        if (customClass) { return objc_getMaximumLineHeightSuper(getSuper(), maximumLineHeight); } else { return objc_getMaximumLineHeight(this, maximumLineHeight); }
    }
    
    private static final Selector setMaximumLineHeight$ = Selector.register("setMaximumLineHeight:");
    @Bridge private native static void objc_setMaximumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__, float maximumLineHeight);
    @Bridge private native static void objc_setMaximumLineHeightSuper(ObjCSuper __super__, Selector __cmd__, float maximumLineHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/maximumLineHeight">@property(readwrite) CGFloat maximumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setMaximumLineHeight(float maximumLineHeight) {
        if (customClass) { objc_setMaximumLineHeightSuper(getSuper(), setMaximumLineHeight$, maximumLineHeight); } else { objc_setMaximumLineHeight(this, setMaximumLineHeight$, maximumLineHeight); }
    }
    
    private static final Selector minimumLineHeight = Selector.register("minimumLineHeight");
    @Bridge private native static float objc_getMinimumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumLineHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/minimumLineHeight">@property(readwrite) CGFloat minimumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMinimumLineHeight() {
        if (customClass) { return objc_getMinimumLineHeightSuper(getSuper(), minimumLineHeight); } else { return objc_getMinimumLineHeight(this, minimumLineHeight); }
    }
    
    private static final Selector setMinimumLineHeight$ = Selector.register("setMinimumLineHeight:");
    @Bridge private native static void objc_setMinimumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__, float minimumLineHeight);
    @Bridge private native static void objc_setMinimumLineHeightSuper(ObjCSuper __super__, Selector __cmd__, float minimumLineHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/minimumLineHeight">@property(readwrite) CGFloat minimumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setMinimumLineHeight(float minimumLineHeight) {
        if (customClass) { objc_setMinimumLineHeightSuper(getSuper(), setMinimumLineHeight$, minimumLineHeight); } else { objc_setMinimumLineHeight(this, setMinimumLineHeight$, minimumLineHeight); }
    }
    
    private static final Selector paragraphSpacing = Selector.register("paragraphSpacing");
    @Bridge private native static float objc_getParagraphSpacing(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getParagraphSpacingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacing">@property(readwrite) CGFloat paragraphSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getParagraphSpacing() {
        if (customClass) { return objc_getParagraphSpacingSuper(getSuper(), paragraphSpacing); } else { return objc_getParagraphSpacing(this, paragraphSpacing); }
    }
    
    private static final Selector setParagraphSpacing$ = Selector.register("setParagraphSpacing:");
    @Bridge private native static void objc_setParagraphSpacing(NSMutableParagraphStyle __self__, Selector __cmd__, float paragraphSpacing);
    @Bridge private native static void objc_setParagraphSpacingSuper(ObjCSuper __super__, Selector __cmd__, float paragraphSpacing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacing">@property(readwrite) CGFloat paragraphSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setParagraphSpacing(float paragraphSpacing) {
        if (customClass) { objc_setParagraphSpacingSuper(getSuper(), setParagraphSpacing$, paragraphSpacing); } else { objc_setParagraphSpacing(this, setParagraphSpacing$, paragraphSpacing); }
    }
    
    private static final Selector paragraphSpacingBefore = Selector.register("paragraphSpacingBefore");
    @Bridge private native static float objc_getParagraphSpacingBefore(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getParagraphSpacingBeforeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacingBefore">@property(readwrite) CGFloat paragraphSpacingBefore</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getParagraphSpacingBefore() {
        if (customClass) { return objc_getParagraphSpacingBeforeSuper(getSuper(), paragraphSpacingBefore); } else { return objc_getParagraphSpacingBefore(this, paragraphSpacingBefore); }
    }
    
    private static final Selector setParagraphSpacingBefore$ = Selector.register("setParagraphSpacingBefore:");
    @Bridge private native static void objc_setParagraphSpacingBefore(NSMutableParagraphStyle __self__, Selector __cmd__, float paragraphSpacingBefore);
    @Bridge private native static void objc_setParagraphSpacingBeforeSuper(ObjCSuper __super__, Selector __cmd__, float paragraphSpacingBefore);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacingBefore">@property(readwrite) CGFloat paragraphSpacingBefore</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setParagraphSpacingBefore(float paragraphSpacingBefore) {
        if (customClass) { objc_setParagraphSpacingBeforeSuper(getSuper(), setParagraphSpacingBefore$, paragraphSpacingBefore); } else { objc_setParagraphSpacingBefore(this, setParagraphSpacingBefore$, paragraphSpacingBefore); }
    }
    
    private static final Selector tailIndent = Selector.register("tailIndent");
    @Bridge private native static float objc_getTailIndent(NSMutableParagraphStyle __self__, Selector __cmd__);
    @Bridge private native static float objc_getTailIndentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/tailIndent">@property(readwrite) CGFloat tailIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getTailIndent() {
        if (customClass) { return objc_getTailIndentSuper(getSuper(), tailIndent); } else { return objc_getTailIndent(this, tailIndent); }
    }
    
    private static final Selector setTailIndent$ = Selector.register("setTailIndent:");
    @Bridge private native static void objc_setTailIndent(NSMutableParagraphStyle __self__, Selector __cmd__, float tailIndent);
    @Bridge private native static void objc_setTailIndentSuper(ObjCSuper __super__, Selector __cmd__, float tailIndent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/tailIndent">@property(readwrite) CGFloat tailIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTailIndent(float tailIndent) {
        if (customClass) { objc_setTailIndentSuper(getSuper(), setTailIndent$, tailIndent); } else { objc_setTailIndent(this, setTailIndent$, tailIndent); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alignment") public static NSTextAlignment getAlignment(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getAlignment(); }
        @Callback @BindSelector("setAlignment:") public static void setAlignment(NSMutableParagraphStyle __self__, Selector __cmd__, NSTextAlignment alignment) { __self__.setAlignment(alignment); }
        @Callback @BindSelector("baseWritingDirection") public static NSWritingDirection getBaseWritingDirection(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getBaseWritingDirection(); }
        @Callback @BindSelector("setBaseWritingDirection:") public static void setBaseWritingDirection(NSMutableParagraphStyle __self__, Selector __cmd__, NSWritingDirection baseWritingDirection) { __self__.setBaseWritingDirection(baseWritingDirection); }
        @Callback @BindSelector("firstLineHeadIndent") public static float getFirstLineHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getFirstLineHeadIndent(); }
        @Callback @BindSelector("setFirstLineHeadIndent:") public static void setFirstLineHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__, float firstLineHeadIndent) { __self__.setFirstLineHeadIndent(firstLineHeadIndent); }
        @Callback @BindSelector("headIndent") public static float getHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getHeadIndent(); }
        @Callback @BindSelector("setHeadIndent:") public static void setHeadIndent(NSMutableParagraphStyle __self__, Selector __cmd__, float headIndent) { __self__.setHeadIndent(headIndent); }
        @Callback @BindSelector("hyphenationFactor") public static float getHyphenationFactor(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getHyphenationFactor(); }
        @Callback @BindSelector("setHyphenationFactor:") public static void setHyphenationFactor(NSMutableParagraphStyle __self__, Selector __cmd__, float hyphenationFactor) { __self__.setHyphenationFactor(hyphenationFactor); }
        @Callback @BindSelector("lineBreakMode") public static NSLineBreakMode getLineBreakMode(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getLineBreakMode(); }
        @Callback @BindSelector("setLineBreakMode:") public static void setLineBreakMode(NSMutableParagraphStyle __self__, Selector __cmd__, NSLineBreakMode lineBreakMode) { __self__.setLineBreakMode(lineBreakMode); }
        @Callback @BindSelector("lineHeightMultiple") public static float getLineHeightMultiple(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getLineHeightMultiple(); }
        @Callback @BindSelector("setLineHeightMultiple:") public static void setLineHeightMultiple(NSMutableParagraphStyle __self__, Selector __cmd__, float lineHeightMultiple) { __self__.setLineHeightMultiple(lineHeightMultiple); }
        @Callback @BindSelector("lineSpacing") public static float getLineSpacing(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getLineSpacing(); }
        @Callback @BindSelector("setLineSpacing:") public static void setLineSpacing(NSMutableParagraphStyle __self__, Selector __cmd__, float lineSpacing) { __self__.setLineSpacing(lineSpacing); }
        @Callback @BindSelector("maximumLineHeight") public static float getMaximumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getMaximumLineHeight(); }
        @Callback @BindSelector("setMaximumLineHeight:") public static void setMaximumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__, float maximumLineHeight) { __self__.setMaximumLineHeight(maximumLineHeight); }
        @Callback @BindSelector("minimumLineHeight") public static float getMinimumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getMinimumLineHeight(); }
        @Callback @BindSelector("setMinimumLineHeight:") public static void setMinimumLineHeight(NSMutableParagraphStyle __self__, Selector __cmd__, float minimumLineHeight) { __self__.setMinimumLineHeight(minimumLineHeight); }
        @Callback @BindSelector("paragraphSpacing") public static float getParagraphSpacing(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getParagraphSpacing(); }
        @Callback @BindSelector("setParagraphSpacing:") public static void setParagraphSpacing(NSMutableParagraphStyle __self__, Selector __cmd__, float paragraphSpacing) { __self__.setParagraphSpacing(paragraphSpacing); }
        @Callback @BindSelector("paragraphSpacingBefore") public static float getParagraphSpacingBefore(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getParagraphSpacingBefore(); }
        @Callback @BindSelector("setParagraphSpacingBefore:") public static void setParagraphSpacingBefore(NSMutableParagraphStyle __self__, Selector __cmd__, float paragraphSpacingBefore) { __self__.setParagraphSpacingBefore(paragraphSpacingBefore); }
        @Callback @BindSelector("tailIndent") public static float getTailIndent(NSMutableParagraphStyle __self__, Selector __cmd__) { return __self__.getTailIndent(); }
        @Callback @BindSelector("setTailIndent:") public static void setTailIndent(NSMutableParagraphStyle __self__, Selector __cmd__, float tailIndent) { __self__.setTailIndent(tailIndent); }
    }
    /*</callbacks>*/

}
