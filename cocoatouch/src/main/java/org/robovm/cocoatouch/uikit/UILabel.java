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
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html">UILabel Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UILabel /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILabel /*</name>*/.class);
    }

    /*<constructors>*/
    public UILabel() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsFontSizeToFitWidth") public native @Type("BOOL") boolean isAdjustsFontSizeToFitWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsFontSizeToFitWidth:") public native void setAdjustsFontSizeToFitWidth(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsLetterSpacingToFitWidth">@property(nonatomic) BOOL adjustsLetterSpacingToFitWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("adjustsLetterSpacingToFitWidth") public native @Type("BOOL") boolean isAdjustsLetterSpacingToFitWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsLetterSpacingToFitWidth">@property(nonatomic) BOOL adjustsLetterSpacingToFitWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAdjustsLetterSpacingToFitWidth:") public native void setAdjustsLetterSpacingToFitWidth(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedText") public native @Type("NSAttributedString *") NSAttributedString getAttributedText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedText:") public native void setAttributedText(@Type("NSAttributedString *") NSAttributedString v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/baselineAdjustment">@property(nonatomic) UIBaselineAdjustment baselineAdjustment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("baselineAdjustment") public native @Type("UIBaselineAdjustment") UIBaselineAdjustment getBaselineAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/baselineAdjustment">@property(nonatomic) UIBaselineAdjustment baselineAdjustment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBaselineAdjustment:") public native void setBaselineAdjustment(@Type("UIBaselineAdjustment") UIBaselineAdjustment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("font") public native @Type("UIFont *") UIFont getFont();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFont:") public native void setFont(@Type("UIFont *") UIFont v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlightedTextColor">@property(nonatomic, retain) UIColor *highlightedTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("highlightedTextColor") public native @Type("UIColor *") UIColor getHighlightedTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlightedTextColor">@property(nonatomic, retain) UIColor *highlightedTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHighlightedTextColor:") public native void setHighlightedTextColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/lineBreakMode">@property(nonatomic) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("lineBreakMode") public native @Type("NSLineBreakMode") NSLineBreakMode getLineBreakMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/lineBreakMode">@property(nonatomic) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLineBreakMode:") public native void setLineBreakMode(@Type("NSLineBreakMode") NSLineBreakMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumScaleFactor") public native @Type("CGFloat") float getMinimumScaleFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumScaleFactor:") public native void setMinimumScaleFactor(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/numberOfLines">@property(nonatomic) NSInteger numberOfLines</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfLines") public native @Type("NSInteger") int getNumberOfLines();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/numberOfLines">@property(nonatomic) NSInteger numberOfLines</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNumberOfLines:") public native void setNumberOfLines(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/preferredMaxLayoutWidth">@property(nonatomic) CGFloat preferredMaxLayoutWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("preferredMaxLayoutWidth") public native @Type("CGFloat") float getPreferredMaxLayoutWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/preferredMaxLayoutWidth">@property(nonatomic) CGFloat preferredMaxLayoutWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setPreferredMaxLayoutWidth:") public native void setPreferredMaxLayoutWidth(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowColor">@property(nonatomic, retain) UIColor *shadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("shadowColor") public native @Type("UIColor *") UIColor getShadowColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowColor">@property(nonatomic, retain) UIColor *shadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShadowColor:") public native void setShadowColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowOffset">@property(nonatomic) CGSize shadowOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("shadowOffset") public native @Type("CGSize") CGSize getShadowOffset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowOffset">@property(nonatomic) CGSize shadowOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShadowOffset:") public native void setShadowOffset(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("text") public native @Type("NSString *") String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textAlignment") public native @Type("NSTextAlignment") NSTextAlignment getTextAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextAlignment:") public native void setTextAlignment(@Type("NSTextAlignment") NSTextAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textColor") public native @Type("UIColor *") UIColor getTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextColor:") public native void setTextColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isUserInteractionEnabled") public native @Type("BOOL") boolean isUserInteractionEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instm/UILabel/drawTextInRect:">- (void)drawTextInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawTextInRect:") public native @Type("void") void drawText(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instm/UILabel/textRectForBounds:limitedToNumberOfLines:">- (CGRect)textRectForBounds:(CGRect)bounds limitedToNumberOfLines:(NSInteger)numberOfLines</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textRectForBounds:limitedToNumberOfLines:") public native @Type("CGRect") CGRect getTextRect(@Type("CGRect") CGRect bounds, @Type("NSInteger") int numberOfLines);
    /*</methods>*/

}
