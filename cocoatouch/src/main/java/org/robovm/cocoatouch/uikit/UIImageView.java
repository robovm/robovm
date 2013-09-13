/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html">UIImageView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIImageView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImageView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIImageView /*</name>*/.class);

    public UIImageView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIImageView(SkipInit skipInit) { super(skipInit); }
    public UIImageView() {}
    
    private static final Selector initWithImage$ = Selector.register("initWithImage:");
    @Bridge private native static @Pointer long objc_initWithImage(UIImageView __self__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/initWithImage:">- (id)initWithImage:(UIImage *)image</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImageView(UIImage image) {
        super((SkipInit) null);
        initObject(objc_initWithImage(this, initWithImage$, image));
    }
    
    private static final Selector initWithImage$highlightedImage$ = Selector.register("initWithImage:highlightedImage:");
    @Bridge private native static @Pointer long objc_initWithImage(UIImageView __self__, Selector __cmd__, UIImage image, UIImage highlightedImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/initWithImage:highlightedImage:">- (id)initWithImage:(UIImage *)image highlightedImage:(UIImage *)highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImageView(UIImage image, UIImage highlightedImage) {
        super((SkipInit) null);
        initObject(objc_initWithImage(this, initWithImage$highlightedImage$, image, highlightedImage));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector animationDuration = Selector.register("animationDuration");
    @Bridge private native static double objc_getAnimationDuration(UIImageView __self__, Selector __cmd__);
    @Bridge private native static double objc_getAnimationDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationDuration">@property(nonatomic) NSTimeInterval animationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getAnimationDuration() {
        if (customClass) { return objc_getAnimationDurationSuper(getSuper(), animationDuration); } else { return objc_getAnimationDuration(this, animationDuration); }
    }
    
    private static final Selector setAnimationDuration$ = Selector.register("setAnimationDuration:");
    @Bridge private native static void objc_setAnimationDuration(UIImageView __self__, Selector __cmd__, double animationDuration);
    @Bridge private native static void objc_setAnimationDurationSuper(ObjCSuper __super__, Selector __cmd__, double animationDuration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationDuration">@property(nonatomic) NSTimeInterval animationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAnimationDuration(double animationDuration) {
        if (customClass) { objc_setAnimationDurationSuper(getSuper(), setAnimationDuration$, animationDuration); } else { objc_setAnimationDuration(this, setAnimationDuration$, animationDuration); }
    }
    
    private static final Selector animationImages = Selector.register("animationImages");
    @Bridge private native static NSArray objc_getAnimationImages(UIImageView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getAnimationImagesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationImages">@property(nonatomic, copy) NSArray *animationImages</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getAnimationImages() {
        if (customClass) { return objc_getAnimationImagesSuper(getSuper(), animationImages); } else { return objc_getAnimationImages(this, animationImages); }
    }
    
    private static final Selector setAnimationImages$ = Selector.register("setAnimationImages:");
    @Bridge private native static void objc_setAnimationImages(UIImageView __self__, Selector __cmd__, NSArray animationImages);
    @Bridge private native static void objc_setAnimationImagesSuper(ObjCSuper __super__, Selector __cmd__, NSArray animationImages);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationImages">@property(nonatomic, copy) NSArray *animationImages</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAnimationImages(NSArray animationImages) {
        if (customClass) { objc_setAnimationImagesSuper(getSuper(), setAnimationImages$, animationImages); } else { objc_setAnimationImages(this, setAnimationImages$, animationImages); }
    }
    
    private static final Selector animationRepeatCount = Selector.register("animationRepeatCount");
    @Bridge private native static int objc_getAnimationRepeatCount(UIImageView __self__, Selector __cmd__);
    @Bridge private native static int objc_getAnimationRepeatCountSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationRepeatCount">@property(nonatomic) NSInteger animationRepeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getAnimationRepeatCount() {
        if (customClass) { return objc_getAnimationRepeatCountSuper(getSuper(), animationRepeatCount); } else { return objc_getAnimationRepeatCount(this, animationRepeatCount); }
    }
    
    private static final Selector setAnimationRepeatCount$ = Selector.register("setAnimationRepeatCount:");
    @Bridge private native static void objc_setAnimationRepeatCount(UIImageView __self__, Selector __cmd__, int animationRepeatCount);
    @Bridge private native static void objc_setAnimationRepeatCountSuper(ObjCSuper __super__, Selector __cmd__, int animationRepeatCount);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/animationRepeatCount">@property(nonatomic) NSInteger animationRepeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAnimationRepeatCount(int animationRepeatCount) {
        if (customClass) { objc_setAnimationRepeatCountSuper(getSuper(), setAnimationRepeatCount$, animationRepeatCount); } else { objc_setAnimationRepeatCount(this, setAnimationRepeatCount$, animationRepeatCount); }
    }
    
    private static final Selector isHighlighted = Selector.register("isHighlighted");
    @Bridge private native static boolean objc_isHighlighted(UIImageView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHighlightedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isHighlighted() {
        if (customClass) { return objc_isHighlightedSuper(getSuper(), isHighlighted); } else { return objc_isHighlighted(this, isHighlighted); }
    }
    
    private static final Selector setHighlighted$ = Selector.register("setHighlighted:");
    @Bridge private native static void objc_setHighlighted(UIImageView __self__, Selector __cmd__, boolean highlighted);
    @Bridge private native static void objc_setHighlightedSuper(ObjCSuper __super__, Selector __cmd__, boolean highlighted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setHighlighted(boolean highlighted) {
        if (customClass) { objc_setHighlightedSuper(getSuper(), setHighlighted$, highlighted); } else { objc_setHighlighted(this, setHighlighted$, highlighted); }
    }
    
    private static final Selector highlightedAnimationImages = Selector.register("highlightedAnimationImages");
    @Bridge private native static NSArray objc_getHighlightedAnimationImages(UIImageView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getHighlightedAnimationImagesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedAnimationImages">@property(nonatomic, copy) NSArray *highlightedAnimationImages</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getHighlightedAnimationImages() {
        if (customClass) { return objc_getHighlightedAnimationImagesSuper(getSuper(), highlightedAnimationImages); } else { return objc_getHighlightedAnimationImages(this, highlightedAnimationImages); }
    }
    
    private static final Selector setHighlightedAnimationImages$ = Selector.register("setHighlightedAnimationImages:");
    @Bridge private native static void objc_setHighlightedAnimationImages(UIImageView __self__, Selector __cmd__, NSArray highlightedAnimationImages);
    @Bridge private native static void objc_setHighlightedAnimationImagesSuper(ObjCSuper __super__, Selector __cmd__, NSArray highlightedAnimationImages);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedAnimationImages">@property(nonatomic, copy) NSArray *highlightedAnimationImages</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setHighlightedAnimationImages(NSArray highlightedAnimationImages) {
        if (customClass) { objc_setHighlightedAnimationImagesSuper(getSuper(), setHighlightedAnimationImages$, highlightedAnimationImages); } else { objc_setHighlightedAnimationImages(this, setHighlightedAnimationImages$, highlightedAnimationImages); }
    }
    
    private static final Selector highlightedImage = Selector.register("highlightedImage");
    @Bridge private native static UIImage objc_getHighlightedImage(UIImageView __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getHighlightedImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedImage">@property(nonatomic, retain) UIImage *highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImage getHighlightedImage() {
        if (customClass) { return objc_getHighlightedImageSuper(getSuper(), highlightedImage); } else { return objc_getHighlightedImage(this, highlightedImage); }
    }
    
    private static final Selector setHighlightedImage$ = Selector.register("setHighlightedImage:");
    @Bridge private native static void objc_setHighlightedImage(UIImageView __self__, Selector __cmd__, UIImage highlightedImage);
    @Bridge private native static void objc_setHighlightedImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage highlightedImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/highlightedImage">@property(nonatomic, retain) UIImage *highlightedImage</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setHighlightedImage(UIImage highlightedImage) {
        if (customClass) { objc_setHighlightedImageSuper(getSuper(), setHighlightedImage$, highlightedImage); } else { objc_setHighlightedImage(this, setHighlightedImage$, highlightedImage); }
    }
    
    private static final Selector image = Selector.register("image");
    @Bridge private native static UIImage objc_getImage(UIImageView __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getImage() {
        if (customClass) { return objc_getImageSuper(getSuper(), image); } else { return objc_getImage(this, image); }
    }
    
    private static final Selector setImage$ = Selector.register("setImage:");
    @Bridge private native static void objc_setImage(UIImageView __self__, Selector __cmd__, UIImage image);
    @Bridge private native static void objc_setImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImage(UIImage image) {
        if (customClass) { objc_setImageSuper(getSuper(), setImage$, image); } else { objc_setImage(this, setImage$, image); }
    }
    
    private static final Selector isUserInteractionEnabled = Selector.register("isUserInteractionEnabled");
    @Bridge private native static boolean objc_isUserInteractionEnabled(UIImageView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isUserInteractionEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isUserInteractionEnabled() {
        if (customClass) { return objc_isUserInteractionEnabledSuper(getSuper(), isUserInteractionEnabled); } else { return objc_isUserInteractionEnabled(this, isUserInteractionEnabled); }
    }
    
    private static final Selector setUserInteractionEnabled$ = Selector.register("setUserInteractionEnabled:");
    @Bridge private native static void objc_setUserInteractionEnabled(UIImageView __self__, Selector __cmd__, boolean userInteractionEnabled);
    @Bridge private native static void objc_setUserInteractionEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean userInteractionEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImageView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setUserInteractionEnabled(boolean userInteractionEnabled) {
        if (customClass) { objc_setUserInteractionEnabledSuper(getSuper(), setUserInteractionEnabled$, userInteractionEnabled); } else { objc_setUserInteractionEnabled(this, setUserInteractionEnabled$, userInteractionEnabled); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector isAnimating = Selector.register("isAnimating");
    @Bridge private native static boolean objc_isAnimating(UIImageView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAnimatingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/isAnimating">- (BOOL)isAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAnimating() {
        if (customClass) { return objc_isAnimatingSuper(getSuper(), isAnimating); } else { return objc_isAnimating(this, isAnimating); }
    }
    
    private static final Selector startAnimating = Selector.register("startAnimating");
    @Bridge private native static void objc_startAnimating(UIImageView __self__, Selector __cmd__);
    @Bridge private native static void objc_startAnimatingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/startAnimating">- (void)startAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void startAnimating() {
        if (customClass) { objc_startAnimatingSuper(getSuper(), startAnimating); } else { objc_startAnimating(this, startAnimating); }
    }
    
    private static final Selector stopAnimating = Selector.register("stopAnimating");
    @Bridge private native static void objc_stopAnimating(UIImageView __self__, Selector __cmd__);
    @Bridge private native static void objc_stopAnimatingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImageView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImageView/stopAnimating">- (void)stopAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void stopAnimating() {
        if (customClass) { objc_stopAnimatingSuper(getSuper(), stopAnimating); } else { objc_stopAnimating(this, stopAnimating); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("animationDuration") public static double getAnimationDuration(UIImageView __self__, Selector __cmd__) { return __self__.getAnimationDuration(); }
        @Callback @BindSelector("setAnimationDuration:") public static void setAnimationDuration(UIImageView __self__, Selector __cmd__, double animationDuration) { __self__.setAnimationDuration(animationDuration); }
        @Callback @BindSelector("animationImages") public static NSArray getAnimationImages(UIImageView __self__, Selector __cmd__) { return __self__.getAnimationImages(); }
        @Callback @BindSelector("setAnimationImages:") public static void setAnimationImages(UIImageView __self__, Selector __cmd__, NSArray animationImages) { __self__.setAnimationImages(animationImages); }
        @Callback @BindSelector("animationRepeatCount") public static int getAnimationRepeatCount(UIImageView __self__, Selector __cmd__) { return __self__.getAnimationRepeatCount(); }
        @Callback @BindSelector("setAnimationRepeatCount:") public static void setAnimationRepeatCount(UIImageView __self__, Selector __cmd__, int animationRepeatCount) { __self__.setAnimationRepeatCount(animationRepeatCount); }
        @Callback @BindSelector("isHighlighted") public static boolean isHighlighted(UIImageView __self__, Selector __cmd__) { return __self__.isHighlighted(); }
        @Callback @BindSelector("setHighlighted:") public static void setHighlighted(UIImageView __self__, Selector __cmd__, boolean highlighted) { __self__.setHighlighted(highlighted); }
        @Callback @BindSelector("highlightedAnimationImages") public static NSArray getHighlightedAnimationImages(UIImageView __self__, Selector __cmd__) { return __self__.getHighlightedAnimationImages(); }
        @Callback @BindSelector("setHighlightedAnimationImages:") public static void setHighlightedAnimationImages(UIImageView __self__, Selector __cmd__, NSArray highlightedAnimationImages) { __self__.setHighlightedAnimationImages(highlightedAnimationImages); }
        @Callback @BindSelector("highlightedImage") public static UIImage getHighlightedImage(UIImageView __self__, Selector __cmd__) { return __self__.getHighlightedImage(); }
        @Callback @BindSelector("setHighlightedImage:") public static void setHighlightedImage(UIImageView __self__, Selector __cmd__, UIImage highlightedImage) { __self__.setHighlightedImage(highlightedImage); }
        @Callback @BindSelector("image") public static UIImage getImage(UIImageView __self__, Selector __cmd__) { return __self__.getImage(); }
        @Callback @BindSelector("setImage:") public static void setImage(UIImageView __self__, Selector __cmd__, UIImage image) { __self__.setImage(image); }
        @Callback @BindSelector("isUserInteractionEnabled") public static boolean isUserInteractionEnabled(UIImageView __self__, Selector __cmd__) { return __self__.isUserInteractionEnabled(); }
        @Callback @BindSelector("setUserInteractionEnabled:") public static void setUserInteractionEnabled(UIImageView __self__, Selector __cmd__, boolean userInteractionEnabled) { __self__.setUserInteractionEnabled(userInteractionEnabled); }
        @Callback @BindSelector("isAnimating") public static boolean isAnimating(UIImageView __self__, Selector __cmd__) { return __self__.isAnimating(); }
        @Callback @BindSelector("startAnimating") public static void startAnimating(UIImageView __self__, Selector __cmd__) { __self__.startAnimating(); }
        @Callback @BindSelector("stopAnimating") public static void stopAnimating(UIImageView __self__, Selector __cmd__) { __self__.stopAnimating(); }
    }
    /*</callbacks>*/

}
