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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UILabel /*</name>*/.class);

    /*<constructors>*/
    protected UILabel(SkipInit skipInit) { super(skipInit); }
    public UILabel() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsFontSizeToFitWidth") public native boolean isAdjustsFontSizeToFitWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsFontSizeToFitWidth:") public native void setAdjustsFontSizeToFitWidth(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsLetterSpacingToFitWidth">@property(nonatomic) BOOL adjustsLetterSpacingToFitWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("adjustsLetterSpacingToFitWidth") public native boolean isAdjustsLetterSpacingToFitWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/adjustsLetterSpacingToFitWidth">@property(nonatomic) BOOL adjustsLetterSpacingToFitWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAdjustsLetterSpacingToFitWidth:") public native void setAdjustsLetterSpacingToFitWidth(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedText") public native NSAttributedString getAttributedText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedText:") public native void setAttributedText(NSAttributedString v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/baselineAdjustment">@property(nonatomic) UIBaselineAdjustment baselineAdjustment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("baselineAdjustment") public native UIBaselineAdjustment getBaselineAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/baselineAdjustment">@property(nonatomic) UIBaselineAdjustment baselineAdjustment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBaselineAdjustment:") public native void setBaselineAdjustment(UIBaselineAdjustment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabled") public native boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("font") public native UIFont getFont();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFont:") public native void setFont(UIFont v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isHighlighted") public native boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlightedTextColor">@property(nonatomic, retain) UIColor *highlightedTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("highlightedTextColor") public native UIColor getHighlightedTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/highlightedTextColor">@property(nonatomic, retain) UIColor *highlightedTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHighlightedTextColor:") public native void setHighlightedTextColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/lineBreakMode">@property(nonatomic) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("lineBreakMode") public native NSLineBreakMode getLineBreakMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/lineBreakMode">@property(nonatomic) NSLineBreakMode lineBreakMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLineBreakMode:") public native void setLineBreakMode(NSLineBreakMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumScaleFactor") public native float getMinimumScaleFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumScaleFactor:") public native void setMinimumScaleFactor(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/numberOfLines">@property(nonatomic) NSInteger numberOfLines</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfLines") public native int getNumberOfLines();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/numberOfLines">@property(nonatomic) NSInteger numberOfLines</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNumberOfLines:") public native void setNumberOfLines(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/preferredMaxLayoutWidth">@property(nonatomic) CGFloat preferredMaxLayoutWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("preferredMaxLayoutWidth") public native float getPreferredMaxLayoutWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/preferredMaxLayoutWidth">@property(nonatomic) CGFloat preferredMaxLayoutWidth</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setPreferredMaxLayoutWidth:") public native void setPreferredMaxLayoutWidth(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowColor">@property(nonatomic, retain) UIColor *shadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("shadowColor") public native UIColor getShadowColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowColor">@property(nonatomic, retain) UIColor *shadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShadowColor:") public native void setShadowColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowOffset">@property(nonatomic) CGSize shadowOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("shadowOffset") public native CGSize getShadowOffset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/shadowOffset">@property(nonatomic) CGSize shadowOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShadowOffset:") public native void setShadowOffset(CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("text") public native String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setText:") public native void setText(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textAlignment") public native NSTextAlignment getTextAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextAlignment:") public native void setTextAlignment(NSTextAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textColor") public native UIColor getTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextColor:") public native void setTextColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isUserInteractionEnabled") public native boolean isUserInteractionEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instp/UILabel/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector drawTextInRect$ = Selector.register("drawTextInRect:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_drawText(UILabel __self__, Selector __cmd__, CGRect rect);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_drawTextSuper(ObjCSuper __super__, UILabel __self__, Selector __cmd__, CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instm/UILabel/drawTextInRect:">- (void)drawTextInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void drawText(CGRect rect) {
        if (customClass) { objc_drawTextSuper(getSuper(), this, drawTextInRect$, rect); } else { objc_drawText(this, drawTextInRect$, rect); }
    }
    
    private static final Selector textRectForBounds$limitedToNumberOfLines$ = Selector.register("textRectForBounds:limitedToNumberOfLines:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getTextRect(UILabel __self__, Selector __cmd__, CGRect bounds, int numberOfLines);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getTextRectSuper(ObjCSuper __super__, UILabel __self__, Selector __cmd__, CGRect bounds, int numberOfLines);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILabel_Class/Reference/UILabel.html#//apple_ref/occ/instm/UILabel/textRectForBounds:limitedToNumberOfLines:">- (CGRect)textRectForBounds:(CGRect)bounds limitedToNumberOfLines:(NSInteger)numberOfLines</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTextRect(CGRect bounds, int numberOfLines) {
        if (customClass) { return objc_getTextRectSuper(getSuper(), this, textRectForBounds$limitedToNumberOfLines$, bounds, numberOfLines); } else { return objc_getTextRect(this, textRectForBounds$limitedToNumberOfLines$, bounds, numberOfLines); }
    }
    /*</methods>*/

}
