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
public class /*<name>*/ UIButton /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIButton /*</name>*/.class);
    }

    /*<constructors>*/
    public UIButton() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("adjustsImageWhenDisabled") public native @Type("BOOL") boolean isAdjustsImageWhenDisabled();
    @Bind("setAdjustsImageWhenDisabled:") public native void setAdjustsImageWhenDisabled(@Type("BOOL") boolean v);
    @Bind("adjustsImageWhenHighlighted") public native @Type("BOOL") boolean isAdjustsImageWhenHighlighted();
    @Bind("setAdjustsImageWhenHighlighted:") public native void setAdjustsImageWhenHighlighted(@Type("BOOL") boolean v);
    @Bind("buttonType") public native @Type("UIButtonType") UIButtonType getButtonType();
    @Bind("contentEdgeInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getContentEdgeInsets();
    @Bind("setContentEdgeInsets:") public native void setContentEdgeInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("currentAttributedTitle") public native @Type("NSAttributedString *") NSAttributedString getCurrentAttributedTitle();
    @Bind("currentBackgroundImage") public native @Type("UIImage *") UIImage getCurrentBackgroundImage();
    @Bind("currentImage") public native @Type("UIImage *") UIImage getCurrentImage();
    @Bind("currentTitle") public native @Type("NSString *") String getCurrentTitle();
    @Bind("currentTitleColor") public native @Type("UIColor *") UIColor getCurrentTitleColor();
    @Bind("currentTitleShadowColor") public native @Type("UIColor *") UIColor getCurrentTitleShadowColor();
    @Bind("imageEdgeInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getImageEdgeInsets();
    @Bind("setImageEdgeInsets:") public native void setImageEdgeInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("imageView") public native @Type("UIImageView *") UIImageView getImageView();
    @Bind("reversesTitleShadowWhenHighlighted") public native @Type("BOOL") boolean isReversesTitleShadowWhenHighlighted();
    @Bind("setReversesTitleShadowWhenHighlighted:") public native void setReversesTitleShadowWhenHighlighted(@Type("BOOL") boolean v);
    @Bind("showsTouchWhenHighlighted") public native @Type("BOOL") boolean isShowsTouchWhenHighlighted();
    @Bind("setShowsTouchWhenHighlighted:") public native void setShowsTouchWhenHighlighted(@Type("BOOL") boolean v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    @Bind("titleEdgeInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getTitleEdgeInsets();
    @Bind("setTitleEdgeInsets:") public native void setTitleEdgeInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("titleLabel") public native @Type("UILabel *") UILabel getTitleLabel();
    /*</properties>*/
    /*<methods>*/
    @Bind("buttonWithType:") public native static @Type("id") UIButton fromType(@Type("UIButtonType") UIButtonType buttonType);
    @Bind("attributedTitleForState:") public native @Type("NSAttributedString *") NSAttributedString getAttributedTitle(@Type("UIControlState") UIControlState state);
    @Bind("backgroundImageForState:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state);
    @Bind("backgroundRectForBounds:") public native @Type("CGRect") CGRect getBackgroundRect(@Type("CGRect") CGRect bounds);
    @Bind("contentRectForBounds:") public native @Type("CGRect") CGRect getContentRect(@Type("CGRect") CGRect bounds);
    @Bind("imageForState:") public native @Type("UIImage *") UIImage getImage(@Type("UIControlState") UIControlState state);
    @Bind("imageRectForContentRect:") public native @Type("CGRect") CGRect getImageRect(@Type("CGRect") CGRect contentRect);
    @Bind("titleForState:") public native @Type("NSString *") String getTitle(@Type("UIControlState") UIControlState state);
    @Bind("titleColorForState:") public native @Type("UIColor *") UIColor getTitleColor(@Type("UIControlState") UIControlState state);
    @Bind("titleRectForContentRect:") public native @Type("CGRect") CGRect getTitleRect(@Type("CGRect") CGRect contentRect);
    @Bind("titleShadowColorForState:") public native @Type("UIColor *") UIColor getTitleShadowColor(@Type("UIControlState") UIControlState state);
    @Bind("setAttributedTitle:forState:") public native @Type("void") void setAttributedTitle(@Type("NSAttributedString *") NSAttributedString title, @Type("UIControlState") UIControlState state);
    @Bind("setBackgroundImage:forState:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setImage:forState:") public native @Type("void") void setImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setTitle:forState:") public native @Type("void") void setTitle(@Type("NSString *") String title, @Type("UIControlState") UIControlState state);
    @Bind("setTitleColor:forState:") public native @Type("void") void setTitleColor(@Type("UIColor *") UIColor color, @Type("UIControlState") UIControlState state);
    @Bind("setTitleShadowColor:forState:") public native @Type("void") void setTitleShadowColor(@Type("UIColor *") UIColor color, @Type("UIControlState") UIControlState state);
    /*</methods>*/

}
