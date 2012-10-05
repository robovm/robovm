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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html">UIView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIView /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ implements UIAppearance, UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/initWithFrame:">- (id)initWithFrame:(CGRect)aRect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithFrame:") public UIView(@Type("CGRect") CGRect aRect) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/alpha">@property(nonatomic) CGFloat alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("alpha") public native @Type("CGFloat") float getAlpha();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/alpha">@property(nonatomic) CGFloat alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAlpha:") public native void setAlpha(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizesSubviews">@property(nonatomic) BOOL autoresizesSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autoresizesSubviews") public native @Type("BOOL") boolean isAutoresizesSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizesSubviews">@property(nonatomic) BOOL autoresizesSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutoresizesSubviews:") public native void setAutoresizesSubviews(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizingMask">@property(nonatomic) UIViewAutoresizing autoresizingMask</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autoresizingMask") public native @Type("UIViewAutoresizing") EnumSet<UIViewAutoresizing> getAutoresizingMask();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizingMask">@property(nonatomic) UIViewAutoresizing autoresizingMask</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutoresizingMask:") public native void setAutoresizingMask(@Type("UIViewAutoresizing") EnumSet<UIViewAutoresizing> v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/backgroundColor">@property(nonatomic, copy) UIColor *backgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backgroundColor") public native @Type("UIColor *") UIColor getBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/backgroundColor">@property(nonatomic, copy) UIColor *backgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackgroundColor:") public native void setBackgroundColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/bounds">@property(nonatomic) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bounds") public native @Type("CGRect") CGRect getBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/bounds">@property(nonatomic) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBounds:") public native void setBounds(@Type("CGRect") CGRect v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/center">@property(nonatomic) CGPoint center</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("center") public native @Type("CGPoint") CGPoint getCenter();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/center">@property(nonatomic) CGPoint center</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCenter:") public native void setCenter(@Type("CGPoint") CGPoint v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clearsContextBeforeDrawing">@property(nonatomic) BOOL clearsContextBeforeDrawing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clearsContextBeforeDrawing") public native @Type("BOOL") boolean isClearsContextBeforeDrawing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clearsContextBeforeDrawing">@property(nonatomic) BOOL clearsContextBeforeDrawing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setClearsContextBeforeDrawing:") public native void setClearsContextBeforeDrawing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clipsToBounds">@property(nonatomic) BOOL clipsToBounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clipsToBounds") public native @Type("BOOL") boolean isClipsToBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clipsToBounds">@property(nonatomic) BOOL clipsToBounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setClipsToBounds:") public native void setClipsToBounds(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentMode">@property(nonatomic) UIViewContentMode contentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentMode") public native @Type("UIViewContentMode") UIViewContentMode getContentMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentMode">@property(nonatomic) UIViewContentMode contentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentMode:") public native void setContentMode(@Type("UIViewContentMode") UIViewContentMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentScaleFactor">@property(nonatomic) CGFloat contentScaleFactor</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("contentScaleFactor") public native @Type("CGFloat") float getContentScaleFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentScaleFactor">@property(nonatomic) CGFloat contentScaleFactor</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setContentScaleFactor:") public native void setContentScaleFactor(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/exclusiveTouch">@property(nonatomic, getter=isExclusiveTouch) BOOL exclusiveTouch</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isExclusiveTouch") public native @Type("BOOL") boolean isExclusiveTouch();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/exclusiveTouch">@property(nonatomic, getter=isExclusiveTouch) BOOL exclusiveTouch</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setExclusiveTouch:") public native void setExclusiveTouch(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/frame">@property(nonatomic) CGRect frame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("frame") public native @Type("CGRect") CGRect getFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/frame">@property(nonatomic) CGRect frame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFrame:") public native void setFrame(@Type("CGRect") CGRect v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/gestureRecognizers">@property(nonatomic, copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/gestureRecognizers">@property(nonatomic, copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setGestureRecognizers:") public native void setGestureRecognizers(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/hidden">@property(nonatomic, getter=isHidden) BOOL hidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isHidden") public native @Type("BOOL") boolean isHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/hidden">@property(nonatomic, getter=isHidden) BOOL hidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidden:") public native void setHidden(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/layer">@property(nonatomic, readonly, retain) CALayer *layer</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("layer") public native @Type("CALayer *") CALayer getLayer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/multipleTouchEnabled">@property(nonatomic, getter=isMultipleTouchEnabled) BOOL multipleTouchEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isMultipleTouchEnabled") public native @Type("BOOL") boolean isMultipleTouchEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/multipleTouchEnabled">@property(nonatomic, getter=isMultipleTouchEnabled) BOOL multipleTouchEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMultipleTouchEnabled:") public native void setMultipleTouchEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/opaque">@property(nonatomic, getter=isOpaque) BOOL opaque</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isOpaque") public native @Type("BOOL") boolean isOpaque();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/opaque">@property(nonatomic, getter=isOpaque) BOOL opaque</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setOpaque:") public native void setOpaque(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/restorationIdentifier">@property (nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("restorationIdentifier") public native @Type("NSString *") String getRestorationIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/restorationIdentifier">@property (nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRestorationIdentifier:") public native void setRestorationIdentifier(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/subviews">@property(nonatomic, readonly, copy) NSArray *subviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("subviews") public native @Type("NSArray *") NSArray getSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/superview">@property(nonatomic, readonly) UIView *superview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("superview") public native @Type("UIView *") UIView getSuperview();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tag") public native @Type("NSInteger") int getTag();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTag:") public native void setTag(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/transform">@property(nonatomic) CGAffineTransform transform</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("transform") public native @Type("CGAffineTransform") CGAffineTransform getTransform();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/transform">@property(nonatomic) CGAffineTransform transform</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTransform:") public native void setTransform(@Type("CGAffineTransform") CGAffineTransform v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isUserInteractionEnabled") public native @Type("BOOL") boolean isUserInteractionEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/window">@property(nonatomic, readonly) UIWindow *window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("window") public native @Type("UIWindow *") UIWindow getWindow();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:animations:">+ (void)animateWithDuration:(NSTimeInterval)duration animations:(void (^)(void))animations</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("animateWithDuration:animations:") public native static @Type("void") void animate(@Type("NSTimeInterval") double duration, @Type("void (^)(void)") VoidBlock animations);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:animations:completion:">+ (void)animateWithDuration:(NSTimeInterval)duration animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("animateWithDuration:animations:completion:") public native static @Type("void") void animate(@Type("NSTimeInterval") double duration, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:delay:options:animations:completion:">+ (void)animateWithDuration:(NSTimeInterval)duration delay:(NSTimeInterval)delay options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("animateWithDuration:delay:options:animations:completion:") public native static @Type("void") void animate(@Type("NSTimeInterval") double duration, @Type("NSTimeInterval") double delay, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/areAnimationsEnabled">+ (BOOL)areAnimationsEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("areAnimationsEnabled") public native static @Type("BOOL") boolean areAnimationsEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/beginAnimations:context:">+ (void)beginAnimations:(NSString *)animationID context:(void *)context</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("beginAnimations:context:") public native static @Type("void") void beginAnimations(@Type("NSString *") String animationID, @Type("void *") VoidPtr context);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/commitAnimations">+ (void)commitAnimations</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("commitAnimations") public native static @Type("void") void commitAnimations();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/layerClass">+ (Class)layerClass</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("layerClass") public native static @Type("Class") ObjCClass getLayerClass();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/requiresConstraintBasedLayout">+ (BOOL)requiresConstraintBasedLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("requiresConstraintBasedLayout") public native static @Type("BOOL") boolean requiresConstraintBasedLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationBeginsFromCurrentState:">+ (void)setAnimationBeginsFromCurrentState:(BOOL)fromCurrentState</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationBeginsFromCurrentState:") public native static @Type("void") void setAnimationBeginsFromCurrentState(@Type("BOOL") boolean fromCurrentState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationCurve:">+ (void)setAnimationCurve:(UIViewAnimationCurve)curve</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationCurve:") public native static @Type("void") void setAnimationCurve(@Type("UIViewAnimationCurve") UIViewAnimationCurve curve);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDelay:">+ (void)setAnimationDelay:(NSTimeInterval)delay</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationDelay:") public native static @Type("void") void setAnimationDelay(@Type("NSTimeInterval") double delay);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDelegate:">+ (void)setAnimationDelegate:(id)delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationDelegate:") public native static @Type("void") void setAnimationDelegate(@Type("id") NSObject delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDidStopSelector:">+ (void)setAnimationDidStopSelector:(SEL)selector</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationDidStopSelector:") public native static @Type("void") void setAnimationDidStopSelector(@Type("SEL") Selector selector);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationRepeatAutoreverses:">+ (void)setAnimationRepeatAutoreverses:(BOOL)repeatAutoreverses</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationRepeatAutoreverses:") public native static @Type("void") void setAnimationRepeatAutoreverses(@Type("BOOL") boolean repeatAutoreverses);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationRepeatCount:">+ (void)setAnimationRepeatCount:(float)repeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationRepeatCount:") public native static @Type("void") void setAnimationRepeatCount(@Type("float") float repeatCount);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationStartDate:">+ (void)setAnimationStartDate:(NSDate *)startTime</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationStartDate:") public native static @Type("void") void setAnimationStartDate(@Type("NSDate *") NSDate startTime);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationTransition:forView:cache:">+ (void)setAnimationTransition:(UIViewAnimationTransition)transition forView:(UIView *)view cache:(BOOL)cache</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationTransition:forView:cache:") public native static @Type("void") void setAnimationTransition(@Type("UIViewAnimationTransition") UIViewAnimationTransition transition, @Type("UIView *") UIView view, @Type("BOOL") boolean cache);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationWillStartSelector:">+ (void)setAnimationWillStartSelector:(SEL)selector</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationWillStartSelector:") public native static @Type("void") void setAnimationWillStartSelector(@Type("SEL") Selector selector);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationsEnabled:">+ (void)setAnimationsEnabled:(BOOL)enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationsEnabled:") public native static @Type("void") void setAnimationsEnabled(@Type("BOOL") boolean enabled);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDuration:">+ (void)setAnimationDuration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAnimationDuration:") public native static @Type("void") void setDurationForAnimation(@Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/transitionWithView:duration:options:animations:completion:">+ (void)transitionWithView:(UIView *)view duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("transitionWithView:duration:options:animations:completion:") public native static @Type("void") void transition(@Type("UIView *") UIView view, @Type("NSTimeInterval") double duration, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/transitionFromView:toView:duration:options:completion:">+ (void)transitionFromView:(UIView *)fromView toView:(UIView *)toView duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("transitionFromView:toView:duration:options:completion:") public native static @Type("void") void transition(@Type("UIView *") UIView fromView, @Type("UIView *") UIView toView, @Type("NSTimeInterval") double duration, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addConstraint:">- (void)addConstraint:(NSLayoutConstraint *)constraint</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("addConstraint:") public native @Type("void") void addConstraint(@Type("NSLayoutConstraint *") NSLayoutConstraint constraint);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addConstraints:">- (void)addConstraints:(NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("addConstraints:") public native @Type("void") void addConstraints(@Type("NSArray *") NSArray constraints);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addGestureRecognizer:">- (void)addGestureRecognizer:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("addGestureRecognizer:") public native @Type("void") void addGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addSubview:">- (void)addSubview:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("addSubview:") public native @Type("void") void addSubview(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/bringSubviewToFront:">- (void)bringSubviewToFront:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bringSubviewToFront:") public native @Type("void") void bringSubviewToFront(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertPoint:fromView:">- (CGPoint)convertPoint:(CGPoint)point fromView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertPoint:fromView:") public native @Type("CGPoint") CGPoint convertPointFromView(@Type("CGPoint") CGPoint point, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertPoint:toView:">- (CGPoint)convertPoint:(CGPoint)point toView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertPoint:toView:") public native @Type("CGPoint") CGPoint convertPointToView(@Type("CGPoint") CGPoint point, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertRect:fromView:">- (CGRect)convertRect:(CGRect)rect fromView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertRect:fromView:") public native @Type("CGRect") CGRect convertRectFromView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertRect:toView:">- (CGRect)convertRect:(CGRect)rect toView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertRect:toView:") public native @Type("CGRect") CGRect convertRectToView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/decodeRestorableStateWithCoder:">- (void) decodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("decodeRestorableStateWithCoder:") public native @Type("void") void decodeRestorableState(@Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didAddSubview:">- (void)didAddSubview:(UIView *)subview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("didAddSubview:") public native @Type("void") void didAddSubview(@Type("UIView *") UIView subview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didMoveToSuperview">- (void)didMoveToSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("didMoveToSuperview") public native @Type("void") void didMoveToSuperview();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didMoveToWindow">- (void)didMoveToWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("didMoveToWindow") public native @Type("void") void didMoveToWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/drawRect:">- (void)drawRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawRect:") public native @Type("void") void draw(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/drawRect:forViewPrintFormatter:">- (void)drawRect:(CGRect)area forViewPrintFormatter:(UIViewPrintFormatter *)formatter</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawRect:forViewPrintFormatter:") public native @Type("void") void drawRect(@Type("CGRect") CGRect area, @Type("UIViewPrintFormatter *") UIViewPrintFormatter formatter);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/encodeRestorableStateWithCoder:">- (void) encodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("encodeRestorableStateWithCoder:") public native @Type("void") void encodeRestorableState(@Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/endEditing:">- (BOOL)endEditing:(BOOL)force</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("endEditing:") public native @Type("BOOL") boolean endEditing(@Type("BOOL") boolean force);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/exchangeSubviewAtIndex:withSubviewAtIndex:">- (void)exchangeSubviewAtIndex:(NSInteger)index1 withSubviewAtIndex:(NSInteger)index2</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("exchangeSubviewAtIndex:withSubviewAtIndex:") public native @Type("void") void exchangeSubview(@Type("NSInteger") int index1, @Type("NSInteger") int index2);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/exerciseAmbiguityInLayout">- (void)exerciseAmbiguityInLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("exerciseAmbiguityInLayout") public native @Type("void") void exerciseAmbiguityInLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/gestureRecognizerShouldBegin:">- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("gestureRecognizerShouldBegin:") public native @Type("BOOL") boolean gestureRecognizerShouldBegin(@Type("UIGestureRecognizer *") UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/frameForAlignmentRect:">- (CGRect)frameForAlignmentRect:(CGRect)alignmentRect</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("frameForAlignmentRect:") public native @Type("CGRect") CGRect getAlignmentRectFrame(@Type("CGRect") CGRect alignmentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/alignmentRectInsets">- (UIEdgeInsets)alignmentRectInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("alignmentRectInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getAlignmentRectInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewForBaselineLayout">- (UIView *)viewForBaselineLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("viewForBaselineLayout") public native @Type("UIView *") UIView getBaselineLayoutView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/constraints">- (NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("constraints") public native @Type("NSArray *") NSArray getConstraints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/constraintsAffectingLayoutForAxis:">- (NSArray *)constraintsAffectingLayoutForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("constraintsAffectingLayoutForAxis:") public native @Type("NSArray *") NSArray getConstraintsAffectingLayout(@Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/contentCompressionResistancePriorityForAxis:">- (UILayoutPriority)contentCompressionResistancePriorityForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("contentCompressionResistancePriorityForAxis:") public native @Type("UILayoutPriority") UILayoutPriority getContentCompressionResistancePriority(@Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/contentHuggingPriorityForAxis:">- (UILayoutPriority)contentHuggingPriorityForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("contentHuggingPriorityForAxis:") public native @Type("UILayoutPriority") UILayoutPriority getContentHuggingPriority(@Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/alignmentRectForFrame:">- (CGRect)alignmentRectForFrame:(CGRect)frame</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("alignmentRectForFrame:") public native @Type("CGRect") CGRect getFrameAlignmentRect(@Type("CGRect") CGRect frame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/intrinsicContentSize">- (CGSize)intrinsicContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("intrinsicContentSize") public native @Type("CGSize") CGSize getIntrinsicContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sizeThatFits:">- (CGSize)sizeThatFits:(CGSize)size</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sizeThatFits:") public native @Type("CGSize") CGSize getSizeThatFits(@Type("CGSize") CGSize size);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/systemLayoutSizeFittingSize:">- (CGSize)systemLayoutSizeFittingSize:(CGSize)targetSize</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("systemLayoutSizeFittingSize:") public native @Type("CGSize") CGSize getSystemLayoutSizeFittingSize(@Type("CGSize") CGSize targetSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewPrintFormatter">- (UIViewPrintFormatter *)viewPrintFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("viewPrintFormatter") public native @Type("UIViewPrintFormatter *") UIViewPrintFormatter getViewPrintFormatter();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewWithTag:">- (UIView *)viewWithTag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewWithTag:") public native @Type("UIView *") UIView getViewWithTag(@Type("NSInteger") int tag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/hasAmbiguousLayout">- (BOOL)hasAmbiguousLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("hasAmbiguousLayout") public native @Type("BOOL") boolean hasAmbiguousLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/hitTest:withEvent:">- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hitTest:withEvent:") public native @Type("UIView *") UIView hitTest(@Type("CGPoint") CGPoint point, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:atIndex:">- (void)insertSubview:(UIView *)view atIndex:(NSInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertSubview:atIndex:") public native @Type("void") void insertSubview(@Type("UIView *") UIView view, @Type("NSInteger") int index);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:aboveSubview:">- (void)insertSubview:(UIView *)view aboveSubview:(UIView *)siblingSubview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertSubview:aboveSubview:") public native @Type("void") void insertSubviewAbove(@Type("UIView *") UIView view, @Type("UIView *") UIView siblingSubview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:belowSubview:">- (void)insertSubview:(UIView *)view belowSubview:(UIView *)siblingSubview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertSubview:belowSubview:") public native @Type("void") void insertSubviewBelow(@Type("UIView *") UIView view, @Type("UIView *") UIView siblingSubview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/invalidateIntrinsicContentSize">- (void)invalidateIntrinsicContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("invalidateIntrinsicContentSize") public native @Type("void") void invalidateIntrinsicContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/isDescendantOfView:">- (BOOL)isDescendantOfView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDescendantOfView:") public native @Type("BOOL") boolean isDescendantOf(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/translatesAutoresizingMaskIntoConstraints">- (BOOL)translatesAutoresizingMaskIntoConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("translatesAutoresizingMaskIntoConstraints") public native @Type("BOOL") boolean isTranslatesAutoresizingMaskIntoConstraints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/layoutIfNeeded">- (void)layoutIfNeeded</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("layoutIfNeeded") public native @Type("void") void layoutIfNeeded();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/layoutSubviews">- (void)layoutSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("layoutSubviews") public native @Type("void") void layoutSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/needsUpdateConstraints">- (BOOL)needsUpdateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("needsUpdateConstraints") public native @Type("BOOL") boolean needsUpdateConstraints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/pointInside:withEvent:">- (BOOL)pointInside:(CGPoint)point withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pointInside:withEvent:") public native @Type("BOOL") boolean pointInside(@Type("CGPoint") CGPoint point, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeConstraint:">- (void)removeConstraint:(NSLayoutConstraint *)constraint</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("removeConstraint:") public native @Type("void") void removeConstraint(@Type("NSLayoutConstraint *") NSLayoutConstraint constraint);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeConstraints:">- (void)removeConstraints:(NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("removeConstraints:") public native @Type("void") void removeConstraints(@Type("NSArray *") NSArray constraints);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeFromSuperview">- (void)removeFromSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("removeFromSuperview") public native @Type("void") void removeFromSuperview();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeGestureRecognizer:">- (void)removeGestureRecognizer:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("removeGestureRecognizer:") public native @Type("void") void removeGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sizeToFit">- (void)sizeToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sizeToFit") public native @Type("void") void resizeToFit();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sendSubviewToBack:">- (void)sendSubviewToBack:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sendSubviewToBack:") public native @Type("void") void sendSubviewToBack(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setContentCompressionResistancePriority:forAxis:">- (void)setContentCompressionResistancePriority:(UILayoutPriority)priority forAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setContentCompressionResistancePriority:forAxis:") public native @Type("void") void setContentCompressionResistancePriority(@Type("UILayoutPriority") UILayoutPriority priority, @Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setContentHuggingPriority:forAxis:">- (void)setContentHuggingPriority:(UILayoutPriority)priority forAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setContentHuggingPriority:forAxis:") public native @Type("void") void setContentHuggingPriority(@Type("UILayoutPriority") UILayoutPriority priority, @Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsDisplay">- (void)setNeedsDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNeedsDisplay") public native @Type("void") void setNeedsDisplay();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsDisplayInRect:">- (void)setNeedsDisplayInRect:(CGRect)invalidRect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNeedsDisplayInRect:") public native @Type("void") void setNeedsDisplay(@Type("CGRect") CGRect invalidRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsLayout">- (void)setNeedsLayout</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNeedsLayout") public native @Type("void") void setNeedsLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsUpdateConstraints">- (void)setNeedsUpdateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setNeedsUpdateConstraints") public native @Type("void") void setNeedsUpdateConstraints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setTranslatesAutoresizingMaskIntoConstraints:">- (void)setTranslatesAutoresizingMaskIntoConstraints:(BOOL)flag</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTranslatesAutoresizingMaskIntoConstraints:") public native @Type("void") void setTranslatesAutoresizingMaskIntoConstraints(@Type("BOOL") boolean flag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/updateConstraints">- (void)updateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("updateConstraints") public native @Type("void") void updateConstraints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/updateConstraintsIfNeeded">- (void)updateConstraintsIfNeeded</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("updateConstraintsIfNeeded") public native @Type("void") void updateConstraintsIfNeeded();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willMoveToSuperview:">- (void)willMoveToSuperview:(UIView *)newSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("willMoveToSuperview:") public native @Type("void") void willMoveToSuperview(@Type("UIView *") UIView newSuperview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willMoveToWindow:">- (void)willMoveToWindow:(UIWindow *)newWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("willMoveToWindow:") public native @Type("void") void willMoveToWindow(@Type("UIWindow *") UIWindow newWindow);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willRemoveSubview:">- (void)willRemoveSubview:(UIView *)subview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("willRemoveSubview:") public native @Type("void") void willRemoveSubview(@Type("UIView *") UIView subview);
    /*</methods>*/

}
