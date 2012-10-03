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
    @Bind("adjustsFontSizeToFitWidth") public native @Type("BOOL") boolean isAdjustsFontSizeToFitWidth();
    @Bind("setAdjustsFontSizeToFitWidth:") public native void setAdjustsFontSizeToFitWidth(@Type("BOOL") boolean v);
    @Bind("adjustsLetterSpacingToFitWidth") public native @Type("BOOL") boolean isAdjustsLetterSpacingToFitWidth();
    @Bind("setAdjustsLetterSpacingToFitWidth:") public native void setAdjustsLetterSpacingToFitWidth(@Type("BOOL") boolean v);
    @Bind("attributedText") public native @Type("NSAttributedString *") NSAttributedString getAttributedText();
    @Bind("setAttributedText:") public native void setAttributedText(@Type("NSAttributedString *") NSAttributedString v);
    @Bind("baselineAdjustment") public native @Type("UIBaselineAdjustment") UIBaselineAdjustment getBaselineAdjustment();
    @Bind("setBaselineAdjustment:") public native void setBaselineAdjustment(@Type("UIBaselineAdjustment") UIBaselineAdjustment v);
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    @Bind("font") public native @Type("UIFont *") UIFont getFont();
    @Bind("setFont:") public native void setFont(@Type("UIFont *") UIFont v);
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    @Bind("highlightedTextColor") public native @Type("UIColor *") UIColor getHighlightedTextColor();
    @Bind("setHighlightedTextColor:") public native void setHighlightedTextColor(@Type("UIColor *") UIColor v);
    @Bind("lineBreakMode") public native @Type("NSLineBreakMode") NSLineBreakMode getLineBreakMode();
    @Bind("setLineBreakMode:") public native void setLineBreakMode(@Type("NSLineBreakMode") NSLineBreakMode v);
    @Bind("minimumScaleFactor") public native @Type("CGFloat") float getMinimumScaleFactor();
    @Bind("setMinimumScaleFactor:") public native void setMinimumScaleFactor(@Type("CGFloat") float v);
    @Bind("numberOfLines") public native @Type("NSInteger") int getNumberOfLines();
    @Bind("setNumberOfLines:") public native void setNumberOfLines(@Type("NSInteger") int v);
    @Bind("preferredMaxLayoutWidth") public native @Type("CGFloat") float getPreferredMaxLayoutWidth();
    @Bind("setPreferredMaxLayoutWidth:") public native void setPreferredMaxLayoutWidth(@Type("CGFloat") float v);
    @Bind("shadowColor") public native @Type("UIColor *") UIColor getShadowColor();
    @Bind("setShadowColor:") public native void setShadowColor(@Type("UIColor *") UIColor v);
    @Bind("shadowOffset") public native @Type("CGSize") CGSize getShadowOffset();
    @Bind("setShadowOffset:") public native void setShadowOffset(@Type("CGSize") CGSize v);
    @Bind("text") public native @Type("NSString *") String getText();
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    @Bind("textAlignment") public native @Type("NSTextAlignment") NSTextAlignment getTextAlignment();
    @Bind("setTextAlignment:") public native void setTextAlignment(@Type("NSTextAlignment") NSTextAlignment v);
    @Bind("textColor") public native @Type("UIColor *") UIColor getTextColor();
    @Bind("setTextColor:") public native void setTextColor(@Type("UIColor *") UIColor v);
    @Bind("isUserInteractionEnabled") public native @Type("BOOL") boolean isUserInteractionEnabled();
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("drawTextInRect:") public native @Type("void") void drawText(@Type("CGRect") CGRect rect);
    @Bind("textRectForBounds:limitedToNumberOfLines:") public native @Type("CGRect") CGRect getTextRect(@Type("CGRect") CGRect bounds, @Type("NSInteger") int numberOfLines);
    /*</methods>*/

}
