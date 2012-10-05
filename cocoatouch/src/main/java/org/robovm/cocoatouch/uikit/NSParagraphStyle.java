/*
 * Copyright (C) 2012 RoboVM
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
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html">NSParagraphStyle Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ NSParagraphStyle /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSParagraphStyle /*</name>*/.class);
    }

    /*<constructors>*/
    public NSParagraphStyle() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/alignment">@property(readonly) NSTextAlignment alignment</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("alignment") public native @Type("NSTextAlignment") NSTextAlignment getAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/baseWritingDirection">@property(readonly) NSWritingDirection baseWritingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("baseWritingDirection") public native @Type("NSWritingDirection") NSWritingDirection getBaseWritingDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/firstLineHeadIndent">@property(readonly) CGFloat firstLineHeadIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("firstLineHeadIndent") public native @Type("CGFloat") float getFirstLineHeadIndent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/headIndent">@property(readonly) CGFloat headIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("headIndent") public native @Type("CGFloat") float getHeadIndent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/hyphenationFactor">@property(readonly) float hyphenationFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("hyphenationFactor") public native @Type("float") float getHyphenationFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/lineBreakMode">@property(readonly) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("lineBreakMode") public native @Type("NSLineBreakMode") NSLineBreakMode getLineBreakMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/lineHeightMultiple">@property(readonly) CGFloat lineHeightMultiple</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("lineHeightMultiple") public native @Type("CGFloat") float getLineHeightMultiple();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/lineSpacing">@property(readonly) CGFloat lineSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("lineSpacing") public native @Type("CGFloat") float getLineSpacing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/maximumLineHeight">@property(readonly) CGFloat maximumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("maximumLineHeight") public native @Type("CGFloat") float getMaximumLineHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/minimumLineHeight">@property(readonly) CGFloat minimumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumLineHeight") public native @Type("CGFloat") float getMinimumLineHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/paragraphSpacing">@property(readonly) CGFloat paragraphSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("paragraphSpacing") public native @Type("CGFloat") float getParagraphSpacing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/paragraphSpacingBefore">@property(readonly) CGFloat paragraphSpacingBefore</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("paragraphSpacingBefore") public native @Type("CGFloat") float getParagraphSpacingBefore();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSParagraphStyle/tailIndent">@property(readonly) CGFloat tailIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tailIndent") public native @Type("CGFloat") float getTailIndent();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/clm/NSParagraphStyle/defaultParagraphStyle">+ (NSParagraphStyle *)defaultParagraphStyle</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("defaultParagraphStyle") public native static @Type("NSParagraphStyle *") NSParagraphStyle defaultParagraphStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/clm/NSParagraphStyle/defaultWritingDirectionForLanguage:">+ (NSWritingDirection)defaultWritingDirectionForLanguage:(NSString *)languageName</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("defaultWritingDirectionForLanguage:") public native static @Type("NSWritingDirection") NSWritingDirection defaultWritingDirectionForLanguage(@Type("NSString *") String languageName);
    /*</methods>*/

}
