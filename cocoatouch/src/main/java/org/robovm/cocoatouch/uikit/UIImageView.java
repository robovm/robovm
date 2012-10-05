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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIImageView /*</name>*/.class);

    /*<constructors>*/
    protected UIImageView(SkipInit skipInit) { super(skipInit); }
    public UIImageView() {}
    
    private static final Selector initWithImage$ = Selector.register("initWithImage:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithImage(UIImageView __self__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/initWithImage:">- (id)initWithImage:(UIImage *)image</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImageView(UIImage image) {
        super((SkipInit) null);
        objc_initWithImage(this, initWithImage$, image);
    }
    
    private static final Selector initWithImage$highlightedImage$ = Selector.register("initWithImage:highlightedImage:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithImage(UIImageView __self__, Selector __cmd__, UIImage image, UIImage highlightedImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/initWithImage:highlightedImage:">- (id)initWithImage:(UIImage *)image highlightedImage:(UIImage *)highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImageView(UIImage image, UIImage highlightedImage) {
        super((SkipInit) null);
        objc_initWithImage(this, initWithImage$highlightedImage$, image, highlightedImage);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationDuration">@property(nonatomic) NSTimeInterval animationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("animationDuration") public native double getAnimationDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationDuration">@property(nonatomic) NSTimeInterval animationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationDuration:") public native void setAnimationDuration(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationImages">@property(nonatomic, copy) NSArray *animationImages</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("animationImages") public native NSArray getAnimationImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationImages">@property(nonatomic, copy) NSArray *animationImages</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationImages:") public native void setAnimationImages(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationRepeatCount">@property(nonatomic) NSInteger animationRepeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("animationRepeatCount") public native int getAnimationRepeatCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationRepeatCount">@property(nonatomic) NSInteger animationRepeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationRepeatCount:") public native void setAnimationRepeatCount(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isHighlighted") public native boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedAnimationImages">@property(nonatomic, copy) NSArray *highlightedAnimationImages</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("highlightedAnimationImages") public native NSArray getHighlightedAnimationImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedAnimationImages">@property(nonatomic, copy) NSArray *highlightedAnimationImages</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlightedAnimationImages:") public native void setHighlightedAnimationImages(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedImage">@property(nonatomic, retain) UIImage *highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("highlightedImage") public native UIImage getHighlightedImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedImage">@property(nonatomic, retain) UIImage *highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlightedImage:") public native void setHighlightedImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("image") public native UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImage:") public native void setImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isUserInteractionEnabled") public native boolean isUserInteractionEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector isAnimating = Selector.register("isAnimating");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAnimating(UIImageView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAnimatingSuper(ObjCSuper __super__, UIImageView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/isAnimating">- (BOOL)isAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAnimating() {
        if (customClass) { return objc_isAnimatingSuper(getSuper(), this, isAnimating); } else { return objc_isAnimating(this, isAnimating); }
    }
    
    private static final Selector startAnimating = Selector.register("startAnimating");
    @Bridge(symbol = "objc_msgSend") private native static void objc_startAnimating(UIImageView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_startAnimatingSuper(ObjCSuper __super__, UIImageView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/startAnimating">- (void)startAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void startAnimating() {
        if (customClass) { objc_startAnimatingSuper(getSuper(), this, startAnimating); } else { objc_startAnimating(this, startAnimating); }
    }
    
    private static final Selector stopAnimating = Selector.register("stopAnimating");
    @Bridge(symbol = "objc_msgSend") private native static void objc_stopAnimating(UIImageView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_stopAnimatingSuper(ObjCSuper __super__, UIImageView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/stopAnimating">- (void)stopAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void stopAnimating() {
        if (customClass) { objc_stopAnimatingSuper(getSuper(), this, stopAnimating); } else { objc_stopAnimating(this, stopAnimating); }
    }
    /*</methods>*/

}
