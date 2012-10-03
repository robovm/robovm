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
public class /*<name>*/ UIImageView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImageView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIImageView() {}
    @Bind("initWithImage:") public UIImageView(@Type("UIImage *") UIImage image) {}
    @Bind("initWithImage:highlightedImage:") public UIImageView(@Type("UIImage *") UIImage image, @Type("UIImage *") UIImage highlightedImage) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("animationDuration") public native @Type("NSTimeInterval") double getAnimationDuration();
    @Bind("setAnimationDuration:") public native void setAnimationDuration(@Type("NSTimeInterval") double v);
    @Bind("animationImages") public native @Type("NSArray *") NSArray getAnimationImages();
    @Bind("setAnimationImages:") public native void setAnimationImages(@Type("NSArray *") NSArray v);
    @Bind("animationRepeatCount") public native @Type("NSInteger") int getAnimationRepeatCount();
    @Bind("setAnimationRepeatCount:") public native void setAnimationRepeatCount(@Type("NSInteger") int v);
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    @Bind("highlightedAnimationImages") public native @Type("NSArray *") NSArray getHighlightedAnimationImages();
    @Bind("setHighlightedAnimationImages:") public native void setHighlightedAnimationImages(@Type("NSArray *") NSArray v);
    @Bind("highlightedImage") public native @Type("UIImage *") UIImage getHighlightedImage();
    @Bind("setHighlightedImage:") public native void setHighlightedImage(@Type("UIImage *") UIImage v);
    @Bind("image") public native @Type("UIImage *") UIImage getImage();
    @Bind("setImage:") public native void setImage(@Type("UIImage *") UIImage v);
    @Bind("isUserInteractionEnabled") public native @Type("BOOL") boolean isUserInteractionEnabled();
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("isAnimating") public native @Type("BOOL") boolean isAnimating();
    @Bind("startAnimating") public native @Type("void") void startAnimating();
    @Bind("stopAnimating") public native @Type("void") void stopAnimating();
    /*</methods>*/

}
