/*
 * Copyright (C) 2014 Trillian Mobile AB
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

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIImageView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIImageViewPtr extends Ptr<UIImageView, UIImageViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIImageView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIImageView() {}
    protected UIImageView(SkipInit skipInit) { super(skipInit); }
    public UIImageView(UIImage image) { super((SkipInit) null); initObject(initWithImage$(image)); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UIImageView(UIImage image, UIImage highlightedImage) { super((SkipInit) null); initObject(initWithImage$highlightedImage$(image, highlightedImage)); }
    /*</constructors>*/
    
    public UIImageView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "image")
    public native UIImage getImage();
    @Property(selector = "setImage:")
    public native void setImage(UIImage v);
    @Property(selector = "highlightedImage")
    public native UIImage getHighlightedImage();
    @Property(selector = "setHighlightedImage:")
    public native void setHighlightedImage(UIImage v);
    @Property(selector = "isUserInteractionEnabled")
    public native boolean isUserInteractionEnabled();
    @Property(selector = "setUserInteractionEnabled:")
    public native void setUserInteractionEnabled(boolean v);
    @Property(selector = "isHighlighted")
    public native boolean isHighlighted();
    @Property(selector = "setHighlighted:")
    public native void setHighlighted(boolean v);
    @Property(selector = "animationImages")
    public native NSArray<UIImage> getAnimationImages();
    @Property(selector = "setAnimationImages:")
    public native void setAnimationImages(NSArray<UIImage> v);
    @Property(selector = "highlightedAnimationImages")
    public native NSArray<UIImage> getHighlightedAnimationImages();
    @Property(selector = "setHighlightedAnimationImages:")
    public native void setHighlightedAnimationImages(NSArray<UIImage> v);
    @Property(selector = "animationDuration")
    public native double getAnimationDuration();
    @Property(selector = "setAnimationDuration:")
    public native void setAnimationDuration(double v);
    @Property(selector = "animationRepeatCount")
    public native @MachineSizedSInt long getAnimationRepeatCount();
    @Property(selector = "setAnimationRepeatCount:")
    public native void setAnimationRepeatCount(@MachineSizedSInt long v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithImage:")
    protected native @Pointer long initWithImage$(UIImage image);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "initWithImage:highlightedImage:")
    protected native @Pointer long initWithImage$highlightedImage$(UIImage image, UIImage highlightedImage);
    @Method(selector = "startAnimating")
    public native void startAnimating();
    @Method(selector = "stopAnimating")
    public native void stopAnimating();
    @Method(selector = "isAnimating")
    public native boolean isAnimating();
    /*</methods>*/
}
