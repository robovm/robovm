/*
 * Copyright (C) 2014 Trillian AB
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
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIButton/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIButtonPtr extends Ptr<UIButton, UIButtonPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIButton.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIButton() {}
    protected UIButton(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contentEdgeInsets")
    public native @ByVal UIEdgeInsets getContentEdgeInsets();
    @Property(selector = "setContentEdgeInsets:")
    public native void setContentEdgeInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "titleEdgeInsets")
    public native @ByVal UIEdgeInsets getTitleEdgeInsets();
    @Property(selector = "setTitleEdgeInsets:")
    public native void setTitleEdgeInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "reversesTitleShadowWhenHighlighted")
    public native boolean isReversesTitleShadowWhenHighlighted();
    @Property(selector = "setReversesTitleShadowWhenHighlighted:")
    public native void setReversesTitleShadowWhenHighlighted(boolean v);
    @Property(selector = "imageEdgeInsets")
    public native @ByVal UIEdgeInsets getImageEdgeInsets();
    @Property(selector = "setImageEdgeInsets:")
    public native void setImageEdgeInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "adjustsImageWhenHighlighted")
    public native boolean isAdjustsImageWhenHighlighted();
    @Property(selector = "setAdjustsImageWhenHighlighted:")
    public native void setAdjustsImageWhenHighlighted(boolean v);
    @Property(selector = "adjustsImageWhenDisabled")
    public native boolean isAdjustsImageWhenDisabled();
    @Property(selector = "setAdjustsImageWhenDisabled:")
    public native void setAdjustsImageWhenDisabled(boolean v);
    @Property(selector = "showsTouchWhenHighlighted")
    public native boolean isShowsTouchWhenHighlighted();
    @Property(selector = "setShowsTouchWhenHighlighted:")
    public native void setShowsTouchWhenHighlighted(boolean v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    @Property(selector = "buttonType")
    public native UIButtonType getButtonType();
    @Property(selector = "currentTitle")
    public native String getCurrentTitle();
    @Property(selector = "currentTitleColor")
    public native UIColor getCurrentTitleColor();
    @Property(selector = "currentTitleShadowColor")
    public native UIColor getCurrentTitleShadowColor();
    @Property(selector = "currentImage")
    public native UIImage getCurrentImage();
    @Property(selector = "currentBackgroundImage")
    public native UIImage getCurrentBackgroundImage();
    @Property(selector = "currentAttributedTitle")
    public native NSAttributedString getCurrentAttributedTitle();
    @Property(selector = "titleLabel")
    public native UILabel getTitleLabel();
    @Property(selector = "imageView")
    public native UIImageView getImageView();
    @Property(selector = "font")
    public native UIFont getFont();
    @Property(selector = "setFont:")
    public native void setFont(UIFont v);
    @Property(selector = "lineBreakMode")
    public native NSLineBreakMode getLineBreakMode();
    @Property(selector = "setLineBreakMode:")
    public native void setLineBreakMode(NSLineBreakMode v);
    @Property(selector = "titleShadowOffset")
    public native @ByVal CGSize getTitleShadowOffset();
    @Property(selector = "setTitleShadowOffset:")
    public native void setTitleShadowOffset(@ByVal CGSize v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setTitle:forState:")
    public native void setTitle$forState$(String title, UIControlState state);
    @Method(selector = "setTitleColor:forState:")
    public native void setTitleColor$forState$(UIColor color, UIControlState state);
    @Method(selector = "setTitleShadowColor:forState:")
    public native void setTitleShadowColor$forState$(UIColor color, UIControlState state);
    @Method(selector = "setImage:forState:")
    public native void setImage$forState$(UIImage image, UIControlState state);
    @Method(selector = "setBackgroundImage:forState:")
    public native void setBackgroundImage$forState$(UIImage image, UIControlState state);
    @Method(selector = "setAttributedTitle:forState:")
    public native void setAttributedTitle$forState$(NSAttributedString title, UIControlState state);
    @Method(selector = "titleForState:")
    public native String getTitle(UIControlState state);
    @Method(selector = "titleColorForState:")
    public native UIColor getTitleColor(UIControlState state);
    @Method(selector = "titleShadowColorForState:")
    public native UIColor getTitleShadowColor(UIControlState state);
    @Method(selector = "imageForState:")
    public native UIImage getImage(UIControlState state);
    @Method(selector = "backgroundImageForState:")
    public native UIImage getBackgroundImage(UIControlState state);
    @Method(selector = "attributedTitleForState:")
    public native NSAttributedString getAttributedTitle(UIControlState state);
    @Method(selector = "backgroundRectForBounds:")
    public native @ByVal CGRect getBackgroundRect(@ByVal CGRect bounds);
    @Method(selector = "contentRectForBounds:")
    public native @ByVal CGRect getContentRect(@ByVal CGRect bounds);
    @Method(selector = "titleRectForContentRect:")
    public native @ByVal CGRect getTitleRect(@ByVal CGRect contentRect);
    @Method(selector = "imageRectForContentRect:")
    public native @ByVal CGRect getImageRect(@ByVal CGRect contentRect);
    @Method(selector = "buttonWithType:")
    public static native UIButton fromType(UIButtonType buttonType);
    /*</methods>*/
}
