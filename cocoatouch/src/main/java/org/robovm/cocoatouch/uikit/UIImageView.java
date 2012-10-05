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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html">UIImageView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIImageView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImageView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIImageView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/initWithImage:">- (id)initWithImage:(UIImage *)image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithImage:") public UIImageView(@Type("UIImage *") UIImage image) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/initWithImage:highlightedImage:">- (id)initWithImage:(UIImage *)image highlightedImage:(UIImage *)highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("initWithImage:highlightedImage:") public UIImageView(@Type("UIImage *") UIImage image, @Type("UIImage *") UIImage highlightedImage) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationDuration">@property(nonatomic) NSTimeInterval animationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("animationDuration") public native @Type("NSTimeInterval") double getAnimationDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationDuration">@property(nonatomic) NSTimeInterval animationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationDuration:") public native void setAnimationDuration(@Type("NSTimeInterval") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationImages">@property(nonatomic, copy) NSArray *animationImages</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("animationImages") public native @Type("NSArray *") NSArray getAnimationImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationImages">@property(nonatomic, copy) NSArray *animationImages</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationImages:") public native void setAnimationImages(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationRepeatCount">@property(nonatomic) NSInteger animationRepeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("animationRepeatCount") public native @Type("NSInteger") int getAnimationRepeatCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationRepeatCount">@property(nonatomic) NSInteger animationRepeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationRepeatCount:") public native void setAnimationRepeatCount(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedAnimationImages">@property(nonatomic, copy) NSArray *highlightedAnimationImages</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("highlightedAnimationImages") public native @Type("NSArray *") NSArray getHighlightedAnimationImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedAnimationImages">@property(nonatomic, copy) NSArray *highlightedAnimationImages</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlightedAnimationImages:") public native void setHighlightedAnimationImages(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedImage">@property(nonatomic, retain) UIImage *highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("highlightedImage") public native @Type("UIImage *") UIImage getHighlightedImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedImage">@property(nonatomic, retain) UIImage *highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlightedImage:") public native void setHighlightedImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("image") public native @Type("UIImage *") UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImage:") public native void setImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isUserInteractionEnabled") public native @Type("BOOL") boolean isUserInteractionEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/isAnimating">- (BOOL)isAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isAnimating") public native @Type("BOOL") boolean isAnimating();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/startAnimating">- (void)startAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("startAnimating") public native @Type("void") void startAnimating();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/stopAnimating">- (void)stopAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("stopAnimating") public native @Type("void") void stopAnimating();
    /*</methods>*/

}
