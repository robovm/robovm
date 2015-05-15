/*
 * Copyright (C) 2013-2015 RoboVM AB
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
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
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
    
    public UIButton(CGRect frame) {
        super(frame);
    }
    
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
    public native boolean reversesTitleShadowWhenHighlighted();
    @Property(selector = "setReversesTitleShadowWhenHighlighted:")
    public native void setReversesTitleShadowWhenHighlighted(boolean v);
    @Property(selector = "imageEdgeInsets")
    public native @ByVal UIEdgeInsets getImageEdgeInsets();
    @Property(selector = "setImageEdgeInsets:")
    public native void setImageEdgeInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "adjustsImageWhenHighlighted")
    public native boolean adjustsImageWhenHighlighted();
    @Property(selector = "setAdjustsImageWhenHighlighted:")
    public native void setAdjustsImageWhenHighlighted(boolean v);
    @Property(selector = "adjustsImageWhenDisabled")
    public native boolean adjustsImageWhenDisabled();
    @Property(selector = "setAdjustsImageWhenDisabled:")
    public native void setAdjustsImageWhenDisabled(boolean v);
    @Property(selector = "showsTouchWhenHighlighted")
    public native boolean showsTouchWhenHighlighted();
    @Property(selector = "setShowsTouchWhenHighlighted:")
    public native void setShowsTouchWhenHighlighted(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    /**
     * @since Available in iOS 5.0 and later.
     */
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
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "currentAttributedTitle")
    public native NSAttributedString getCurrentAttributedTitle();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "titleLabel")
    public native UILabel getTitleLabel();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "imageView")
    public native UIImageView getImageView();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setTitle:forState:")
    public native void setTitle(String title, UIControlState state);
    @Method(selector = "setTitleColor:forState:")
    public native void setTitleColor(UIColor color, UIControlState state);
    @Method(selector = "setTitleShadowColor:forState:")
    public native void setTitleShadowColor(UIColor color, UIControlState state);
    @Method(selector = "setImage:forState:")
    public native void setImage(UIImage image, UIControlState state);
    @Method(selector = "setBackgroundImage:forState:")
    public native void setBackgroundImage(UIImage image, UIControlState state);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setAttributedTitle:forState:")
    public native void setAttributedTitle(NSAttributedString title, UIControlState state);
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
    /**
     * @since Available in iOS 6.0 and later.
     */
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
    public static native UIButton create(UIButtonType buttonType);
    /*</methods>*/
}
