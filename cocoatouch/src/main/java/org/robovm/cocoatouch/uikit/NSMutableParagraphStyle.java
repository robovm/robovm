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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html">NSMutableParagraphStyle Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ NSMutableParagraphStyle /*</name>*/ 
    extends /*<extends>*/ NSParagraphStyle /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSMutableParagraphStyle /*</name>*/.class);
    }

    /*<constructors>*/
    public NSMutableParagraphStyle() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/alignment">@property(readwrite) NSTextAlignment alignment</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("alignment") public native @Type("NSTextAlignment") NSTextAlignment getAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/alignment">@property(readwrite) NSTextAlignment alignment</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAlignment:") public native void setAlignment(@Type("NSTextAlignment") NSTextAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/baseWritingDirection">@property(readwrite) NSWritingDirection baseWritingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("baseWritingDirection") public native @Type("NSWritingDirection") NSWritingDirection getBaseWritingDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/baseWritingDirection">@property(readwrite) NSWritingDirection baseWritingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setBaseWritingDirection:") public native void setBaseWritingDirection(@Type("NSWritingDirection") NSWritingDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/firstLineHeadIndent">@property(readwrite) CGFloat firstLineHeadIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("firstLineHeadIndent") public native @Type("CGFloat") float getFirstLineHeadIndent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/firstLineHeadIndent">@property(readwrite) CGFloat firstLineHeadIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setFirstLineHeadIndent:") public native void setFirstLineHeadIndent(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/headIndent">@property(readwrite) CGFloat headIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("headIndent") public native @Type("CGFloat") float getHeadIndent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/headIndent">@property(readwrite) CGFloat headIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setHeadIndent:") public native void setHeadIndent(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/hyphenationFactor">@property(readwrite) float hyphenationFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("hyphenationFactor") public native @Type("float") float getHyphenationFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/hyphenationFactor">@property(readwrite) float hyphenationFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setHyphenationFactor:") public native void setHyphenationFactor(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineBreakMode">@property(readwrite) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("lineBreakMode") public native @Type("NSLineBreakMode") NSLineBreakMode getLineBreakMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineBreakMode">@property(readwrite) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setLineBreakMode:") public native void setLineBreakMode(@Type("NSLineBreakMode") NSLineBreakMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineHeightMultiple">@property(readwrite) CGFloat lineHeightMultiple</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("lineHeightMultiple") public native @Type("CGFloat") float getLineHeightMultiple();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineHeightMultiple">@property(readwrite) CGFloat lineHeightMultiple</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setLineHeightMultiple:") public native void setLineHeightMultiple(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineSpacing">@property(readwrite) CGFloat lineSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("lineSpacing") public native @Type("CGFloat") float getLineSpacing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/lineSpacing">@property(readwrite) CGFloat lineSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setLineSpacing:") public native void setLineSpacing(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/maximumLineHeight">@property(readwrite) CGFloat maximumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("maximumLineHeight") public native @Type("CGFloat") float getMaximumLineHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/maximumLineHeight">@property(readwrite) CGFloat maximumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMaximumLineHeight:") public native void setMaximumLineHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/minimumLineHeight">@property(readwrite) CGFloat minimumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumLineHeight") public native @Type("CGFloat") float getMinimumLineHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/minimumLineHeight">@property(readwrite) CGFloat minimumLineHeight</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumLineHeight:") public native void setMinimumLineHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacing">@property(readwrite) CGFloat paragraphSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("paragraphSpacing") public native @Type("CGFloat") float getParagraphSpacing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacing">@property(readwrite) CGFloat paragraphSpacing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setParagraphSpacing:") public native void setParagraphSpacing(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacingBefore">@property(readwrite) CGFloat paragraphSpacingBefore</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("paragraphSpacingBefore") public native @Type("CGFloat") float getParagraphSpacingBefore();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/paragraphSpacingBefore">@property(readwrite) CGFloat paragraphSpacingBefore</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setParagraphSpacingBefore:") public native void setParagraphSpacingBefore(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/tailIndent">@property(readwrite) CGFloat tailIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tailIndent") public native @Type("CGFloat") float getTailIndent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSMutableParagraphStyle_Class/Reference/Reference.html#//apple_ref/occ/instp/NSMutableParagraphStyle/tailIndent">@property(readwrite) CGFloat tailIndent</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTailIndent:") public native void setTailIndent(@Type("CGFloat") float v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
