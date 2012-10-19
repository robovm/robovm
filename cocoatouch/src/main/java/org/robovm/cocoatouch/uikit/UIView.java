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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIView /*</name>*/.class);

    /*<constructors>*/
    protected UIView(SkipInit skipInit) { super(skipInit); }
    public UIView() {}
    
    private static final Selector initWithFrame$ = Selector.register("initWithFrame:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithFrame(UIView __self__, Selector __cmd__, CGRect aRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/initWithFrame:">- (id)initWithFrame:(CGRect)aRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView(CGRect aRect) {
        super((SkipInit) null);
        objc_initWithFrame(this, initWithFrame$, aRect);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/alpha">@property(nonatomic) CGFloat alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("alpha") public native float getAlpha();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/alpha">@property(nonatomic) CGFloat alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAlpha:") public native void setAlpha(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizesSubviews">@property(nonatomic) BOOL autoresizesSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autoresizesSubviews") public native boolean isAutoresizesSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizesSubviews">@property(nonatomic) BOOL autoresizesSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutoresizesSubviews:") public native void setAutoresizesSubviews(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizingMask">@property(nonatomic) UIViewAutoresizing autoresizingMask</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autoresizingMask") public native UIViewAutoresizing getAutoresizingMask();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizingMask">@property(nonatomic) UIViewAutoresizing autoresizingMask</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutoresizingMask:") public native void setAutoresizingMask(UIViewAutoresizing v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/backgroundColor">@property(nonatomic, copy) UIColor *backgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backgroundColor") public native UIColor getBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/backgroundColor">@property(nonatomic, copy) UIColor *backgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackgroundColor:") public native void setBackgroundColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/bounds">@property(nonatomic) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bounds") public native CGRect getBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/bounds">@property(nonatomic) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBounds:") public native void setBounds(CGRect v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/center">@property(nonatomic) CGPoint center</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("center") public native CGPoint getCenter();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/center">@property(nonatomic) CGPoint center</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCenter:") public native void setCenter(CGPoint v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clearsContextBeforeDrawing">@property(nonatomic) BOOL clearsContextBeforeDrawing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clearsContextBeforeDrawing") public native boolean isClearsContextBeforeDrawing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clearsContextBeforeDrawing">@property(nonatomic) BOOL clearsContextBeforeDrawing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setClearsContextBeforeDrawing:") public native void setClearsContextBeforeDrawing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clipsToBounds">@property(nonatomic) BOOL clipsToBounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clipsToBounds") public native boolean isClipsToBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clipsToBounds">@property(nonatomic) BOOL clipsToBounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setClipsToBounds:") public native void setClipsToBounds(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentMode">@property(nonatomic) UIViewContentMode contentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentMode") public native UIViewContentMode getContentMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentMode">@property(nonatomic) UIViewContentMode contentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentMode:") public native void setContentMode(UIViewContentMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentScaleFactor">@property(nonatomic) CGFloat contentScaleFactor</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("contentScaleFactor") public native float getContentScaleFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentScaleFactor">@property(nonatomic) CGFloat contentScaleFactor</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setContentScaleFactor:") public native void setContentScaleFactor(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/exclusiveTouch">@property(nonatomic, getter=isExclusiveTouch) BOOL exclusiveTouch</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isExclusiveTouch") public native boolean isExclusiveTouch();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/exclusiveTouch">@property(nonatomic, getter=isExclusiveTouch) BOOL exclusiveTouch</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setExclusiveTouch:") public native void setExclusiveTouch(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/frame">@property(nonatomic) CGRect frame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("frame") public native CGRect getFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/frame">@property(nonatomic) CGRect frame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFrame:") public native void setFrame(CGRect v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/gestureRecognizers">@property(nonatomic, copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("gestureRecognizers") public native NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/gestureRecognizers">@property(nonatomic, copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setGestureRecognizers:") public native void setGestureRecognizers(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/hidden">@property(nonatomic, getter=isHidden) BOOL hidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isHidden") public native boolean isHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/hidden">@property(nonatomic, getter=isHidden) BOOL hidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidden:") public native void setHidden(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/layer">@property(nonatomic, readonly, retain) CALayer *layer</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("layer") public native CALayer getLayer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/multipleTouchEnabled">@property(nonatomic, getter=isMultipleTouchEnabled) BOOL multipleTouchEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isMultipleTouchEnabled") public native boolean isMultipleTouchEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/multipleTouchEnabled">@property(nonatomic, getter=isMultipleTouchEnabled) BOOL multipleTouchEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMultipleTouchEnabled:") public native void setMultipleTouchEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/opaque">@property(nonatomic, getter=isOpaque) BOOL opaque</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isOpaque") public native boolean isOpaque();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/opaque">@property(nonatomic, getter=isOpaque) BOOL opaque</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setOpaque:") public native void setOpaque(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/restorationIdentifier">@property (nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("restorationIdentifier") public native String getRestorationIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/restorationIdentifier">@property (nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRestorationIdentifier:") public native void setRestorationIdentifier(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/subviews">@property(nonatomic, readonly, copy) NSArray *subviews</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("subviews") public native NSArray getSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/superview">@property(nonatomic, readonly) UIView *superview</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("superview") public native UIView getSuperview();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tag") public native int getTag();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTag:") public native void setTag(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/transform">@property(nonatomic) CGAffineTransform transform</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("transform") public native CGAffineTransform getTransform();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/transform">@property(nonatomic) CGAffineTransform transform</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTransform:") public native void setTransform(CGAffineTransform v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isUserInteractionEnabled") public native boolean isUserInteractionEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/window">@property(nonatomic, readonly) UIWindow *window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("window") public native UIWindow getWindow();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector animateWithDuration$animations$ = Selector.register("animateWithDuration:animations:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_animate(ObjCClass __self__, Selector __cmd__, double duration, VoidBlock animations);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:animations:">+ (void)animateWithDuration:(NSTimeInterval)duration animations:(void (^)(void))animations</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void animate(double duration, VoidBlock animations) {
        objc_animate(objCClass, animateWithDuration$animations$, duration, animations);
    }
    
    private static final Selector animateWithDuration$animations$completion$ = Selector.register("animateWithDuration:animations:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_animate(ObjCClass __self__, Selector __cmd__, double duration, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:animations:completion:">+ (void)animateWithDuration:(NSTimeInterval)duration animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void animate(double duration, VoidBlock animations, VoidBooleanBlock completion) {
        objc_animate(objCClass, animateWithDuration$animations$completion$, duration, animations, completion);
    }
    
    private static final Selector animateWithDuration$delay$options$animations$completion$ = Selector.register("animateWithDuration:delay:options:animations:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_animate(ObjCClass __self__, Selector __cmd__, double duration, double delay, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:delay:options:animations:completion:">+ (void)animateWithDuration:(NSTimeInterval)duration delay:(NSTimeInterval)delay options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void animate(double duration, double delay, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) {
        objc_animate(objCClass, animateWithDuration$delay$options$animations$completion$, duration, delay, options, animations, completion);
    }
    
    private static final Selector areAnimationsEnabled = Selector.register("areAnimationsEnabled");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_areAnimationsEnabled(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/areAnimationsEnabled">+ (BOOL)areAnimationsEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public static boolean areAnimationsEnabled() {
        return objc_areAnimationsEnabled(objCClass, areAnimationsEnabled);
    }
    
    private static final Selector beginAnimations$context$ = Selector.register("beginAnimations:context:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_beginAnimations(ObjCClass __self__, Selector __cmd__, String animationID, VoidPtr context);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/beginAnimations:context:">+ (void)beginAnimations:(NSString *)animationID context:(void *)context</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void beginAnimations(String animationID, VoidPtr context) {
        objc_beginAnimations(objCClass, beginAnimations$context$, animationID, context);
    }
    
    private static final Selector commitAnimations = Selector.register("commitAnimations");
    @Bridge(symbol = "objc_msgSend") private native static void objc_commitAnimations(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/commitAnimations">+ (void)commitAnimations</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void commitAnimations() {
        objc_commitAnimations(objCClass, commitAnimations);
    }
    
    private static final Selector layerClass = Selector.register("layerClass");
    @Bridge(symbol = "objc_msgSend") private native static ObjCClass objc_getLayerClass(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/layerClass">+ (Class)layerClass</a>
     * @since Available in iOS 2.0 and later.
     */
    public static ObjCClass getLayerClass() {
        return objc_getLayerClass(objCClass, layerClass);
    }
    
    private static final Selector requiresConstraintBasedLayout = Selector.register("requiresConstraintBasedLayout");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_requiresConstraintBasedLayout(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/requiresConstraintBasedLayout">+ (BOOL)requiresConstraintBasedLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public static boolean requiresConstraintBasedLayout() {
        return objc_requiresConstraintBasedLayout(objCClass, requiresConstraintBasedLayout);
    }
    
    private static final Selector setAnimationBeginsFromCurrentState$ = Selector.register("setAnimationBeginsFromCurrentState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationBeginsFromCurrentState(ObjCClass __self__, Selector __cmd__, boolean fromCurrentState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationBeginsFromCurrentState:">+ (void)setAnimationBeginsFromCurrentState:(BOOL)fromCurrentState</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationBeginsFromCurrentState(boolean fromCurrentState) {
        objc_setAnimationBeginsFromCurrentState(objCClass, setAnimationBeginsFromCurrentState$, fromCurrentState);
    }
    
    private static final Selector setAnimationCurve$ = Selector.register("setAnimationCurve:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationCurve(ObjCClass __self__, Selector __cmd__, UIViewAnimationCurve curve);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationCurve:">+ (void)setAnimationCurve:(UIViewAnimationCurve)curve</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationCurve(UIViewAnimationCurve curve) {
        objc_setAnimationCurve(objCClass, setAnimationCurve$, curve);
    }
    
    private static final Selector setAnimationDelay$ = Selector.register("setAnimationDelay:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationDelay(ObjCClass __self__, Selector __cmd__, double delay);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDelay:">+ (void)setAnimationDelay:(NSTimeInterval)delay</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationDelay(double delay) {
        objc_setAnimationDelay(objCClass, setAnimationDelay$, delay);
    }
    
    private static final Selector setAnimationDelegate$ = Selector.register("setAnimationDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationDelegate(ObjCClass __self__, Selector __cmd__, NSObject delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDelegate:">+ (void)setAnimationDelegate:(id)delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationDelegate(NSObject delegate) {
        objc_setAnimationDelegate(objCClass, setAnimationDelegate$, delegate);
    }
    
    private static final Selector setAnimationDidStopSelector$ = Selector.register("setAnimationDidStopSelector:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationDidStopSelector(ObjCClass __self__, Selector __cmd__, Selector selector);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDidStopSelector:">+ (void)setAnimationDidStopSelector:(SEL)selector</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationDidStopSelector(Selector selector) {
        objc_setAnimationDidStopSelector(objCClass, setAnimationDidStopSelector$, selector);
    }
    
    private static final Selector setAnimationRepeatAutoreverses$ = Selector.register("setAnimationRepeatAutoreverses:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationRepeatAutoreverses(ObjCClass __self__, Selector __cmd__, boolean repeatAutoreverses);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationRepeatAutoreverses:">+ (void)setAnimationRepeatAutoreverses:(BOOL)repeatAutoreverses</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationRepeatAutoreverses(boolean repeatAutoreverses) {
        objc_setAnimationRepeatAutoreverses(objCClass, setAnimationRepeatAutoreverses$, repeatAutoreverses);
    }
    
    private static final Selector setAnimationRepeatCount$ = Selector.register("setAnimationRepeatCount:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationRepeatCount(ObjCClass __self__, Selector __cmd__, float repeatCount);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationRepeatCount:">+ (void)setAnimationRepeatCount:(float)repeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationRepeatCount(float repeatCount) {
        objc_setAnimationRepeatCount(objCClass, setAnimationRepeatCount$, repeatCount);
    }
    
    private static final Selector setAnimationStartDate$ = Selector.register("setAnimationStartDate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationStartDate(ObjCClass __self__, Selector __cmd__, NSDate startTime);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationStartDate:">+ (void)setAnimationStartDate:(NSDate *)startTime</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationStartDate(NSDate startTime) {
        objc_setAnimationStartDate(objCClass, setAnimationStartDate$, startTime);
    }
    
    private static final Selector setAnimationTransition$forView$cache$ = Selector.register("setAnimationTransition:forView:cache:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationTransition(ObjCClass __self__, Selector __cmd__, UIViewAnimationTransition transition, UIView view, boolean cache);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationTransition:forView:cache:">+ (void)setAnimationTransition:(UIViewAnimationTransition)transition forView:(UIView *)view cache:(BOOL)cache</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationTransition(UIViewAnimationTransition transition, UIView view, boolean cache) {
        objc_setAnimationTransition(objCClass, setAnimationTransition$forView$cache$, transition, view, cache);
    }
    
    private static final Selector setAnimationWillStartSelector$ = Selector.register("setAnimationWillStartSelector:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationWillStartSelector(ObjCClass __self__, Selector __cmd__, Selector selector);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationWillStartSelector:">+ (void)setAnimationWillStartSelector:(SEL)selector</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationWillStartSelector(Selector selector) {
        objc_setAnimationWillStartSelector(objCClass, setAnimationWillStartSelector$, selector);
    }
    
    private static final Selector setAnimationsEnabled$ = Selector.register("setAnimationsEnabled:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnimationsEnabled(ObjCClass __self__, Selector __cmd__, boolean enabled);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationsEnabled:">+ (void)setAnimationsEnabled:(BOOL)enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationsEnabled(boolean enabled) {
        objc_setAnimationsEnabled(objCClass, setAnimationsEnabled$, enabled);
    }
    
    private static final Selector setAnimationDuration$ = Selector.register("setAnimationDuration:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDurationForAnimation(ObjCClass __self__, Selector __cmd__, double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDuration:">+ (void)setAnimationDuration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setDurationForAnimation(double duration) {
        objc_setDurationForAnimation(objCClass, setAnimationDuration$, duration);
    }
    
    private static final Selector transitionWithView$duration$options$animations$completion$ = Selector.register("transitionWithView:duration:options:animations:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_transition(ObjCClass __self__, Selector __cmd__, UIView view, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/transitionWithView:duration:options:animations:completion:">+ (void)transitionWithView:(UIView *)view duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void transition(UIView view, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) {
        objc_transition(objCClass, transitionWithView$duration$options$animations$completion$, view, duration, options, animations, completion);
    }
    
    private static final Selector transitionFromView$toView$duration$options$completion$ = Selector.register("transitionFromView:toView:duration:options:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_transition(ObjCClass __self__, Selector __cmd__, UIView fromView, UIView toView, double duration, UIViewAnimationOptions options, VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/transitionFromView:toView:duration:options:completion:">+ (void)transitionFromView:(UIView *)fromView toView:(UIView *)toView duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void transition(UIView fromView, UIView toView, double duration, UIViewAnimationOptions options, VoidBooleanBlock completion) {
        objc_transition(objCClass, transitionFromView$toView$duration$options$completion$, fromView, toView, duration, options, completion);
    }
    
    private static final Selector addConstraint$ = Selector.register("addConstraint:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addConstraint(UIView __self__, Selector __cmd__, NSLayoutConstraint constraint);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addConstraintSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, NSLayoutConstraint constraint);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addConstraint:">- (void)addConstraint:(NSLayoutConstraint *)constraint</a>
     * @since Available in iOS 6.0 and later.
     */
    public void addConstraint(NSLayoutConstraint constraint) {
        if (customClass) { objc_addConstraintSuper(getSuper(), this, addConstraint$, constraint); } else { objc_addConstraint(this, addConstraint$, constraint); }
    }
    
    private static final Selector addConstraints$ = Selector.register("addConstraints:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addConstraints(UIView __self__, Selector __cmd__, NSArray constraints);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, NSArray constraints);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addConstraints:">- (void)addConstraints:(NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void addConstraints(NSArray constraints) {
        if (customClass) { objc_addConstraintsSuper(getSuper(), this, addConstraints$, constraints); } else { objc_addConstraints(this, addConstraints$, constraints); }
    }
    
    private static final Selector addGestureRecognizer$ = Selector.register("addGestureRecognizer:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addGestureRecognizer(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addGestureRecognizerSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addGestureRecognizer:">- (void)addGestureRecognizer:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addGestureRecognizer(UIGestureRecognizer gestureRecognizer) {
        if (customClass) { objc_addGestureRecognizerSuper(getSuper(), this, addGestureRecognizer$, gestureRecognizer); } else { objc_addGestureRecognizer(this, addGestureRecognizer$, gestureRecognizer); }
    }
    
    private static final Selector addSubview$ = Selector.register("addSubview:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addSubview(UIView __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addSubviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addSubview:">- (void)addSubview:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void addSubview(UIView view) {
        if (customClass) { objc_addSubviewSuper(getSuper(), this, addSubview$, view); } else { objc_addSubview(this, addSubview$, view); }
    }
    
    private static final Selector bringSubviewToFront$ = Selector.register("bringSubviewToFront:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_bringSubviewToFront(UIView __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_bringSubviewToFrontSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/bringSubviewToFront:">- (void)bringSubviewToFront:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void bringSubviewToFront(UIView view) {
        if (customClass) { objc_bringSubviewToFrontSuper(getSuper(), this, bringSubviewToFront$, view); } else { objc_bringSubviewToFront(this, bringSubviewToFront$, view); }
    }
    
    private static final Selector convertPoint$fromView$ = Selector.register("convertPoint:fromView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_convertPointFromView(UIView __self__, Selector __cmd__, CGPoint point, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_convertPointFromViewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGPoint point, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertPoint:fromView:">- (CGPoint)convertPoint:(CGPoint)point fromView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint convertPointFromView(CGPoint point, UIView view) {
        if (customClass) { return objc_convertPointFromViewSuper(getSuper(), this, convertPoint$fromView$, point, view); } else { return objc_convertPointFromView(this, convertPoint$fromView$, point, view); }
    }
    
    private static final Selector convertPoint$toView$ = Selector.register("convertPoint:toView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_convertPointToView(UIView __self__, Selector __cmd__, CGPoint point, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_convertPointToViewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGPoint point, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertPoint:toView:">- (CGPoint)convertPoint:(CGPoint)point toView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint convertPointToView(CGPoint point, UIView view) {
        if (customClass) { return objc_convertPointToViewSuper(getSuper(), this, convertPoint$toView$, point, view); } else { return objc_convertPointToView(this, convertPoint$toView$, point, view); }
    }
    
    private static final Selector convertRect$fromView$ = Selector.register("convertRect:fromView:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_convertRectFromView(UIView __self__, Selector __cmd__, CGRect rect, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_convertRectFromViewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect rect, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertRect:fromView:">- (CGRect)convertRect:(CGRect)rect fromView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect convertRectFromView(CGRect rect, UIView view) {
        if (customClass) { return objc_convertRectFromViewSuper(getSuper(), this, convertRect$fromView$, rect, view); } else { return objc_convertRectFromView(this, convertRect$fromView$, rect, view); }
    }
    
    private static final Selector convertRect$toView$ = Selector.register("convertRect:toView:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_convertRectToView(UIView __self__, Selector __cmd__, CGRect rect, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_convertRectToViewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect rect, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertRect:toView:">- (CGRect)convertRect:(CGRect)rect toView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect convertRectToView(CGRect rect, UIView view) {
        if (customClass) { return objc_convertRectToViewSuper(getSuper(), this, convertRect$toView$, rect, view); } else { return objc_convertRectToView(this, convertRect$toView$, rect, view); }
    }
    
    private static final Selector decodeRestorableStateWithCoder$ = Selector.register("decodeRestorableStateWithCoder:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_decodeRestorableState(UIView __self__, Selector __cmd__, NSCoder coder);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_decodeRestorableStateSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/decodeRestorableStateWithCoder:">- (void) decodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void decodeRestorableState(NSCoder coder) {
        if (customClass) { objc_decodeRestorableStateSuper(getSuper(), this, decodeRestorableStateWithCoder$, coder); } else { objc_decodeRestorableState(this, decodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector didAddSubview$ = Selector.register("didAddSubview:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didAddSubview(UIView __self__, Selector __cmd__, UIView subview);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didAddSubviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView subview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didAddSubview:">- (void)didAddSubview:(UIView *)subview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didAddSubview(UIView subview) {
        if (customClass) { objc_didAddSubviewSuper(getSuper(), this, didAddSubview$, subview); } else { objc_didAddSubview(this, didAddSubview$, subview); }
    }
    
    private static final Selector didMoveToSuperview = Selector.register("didMoveToSuperview");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didMoveToSuperview(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didMoveToSuperviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didMoveToSuperview">- (void)didMoveToSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didMoveToSuperview() {
        if (customClass) { objc_didMoveToSuperviewSuper(getSuper(), this, didMoveToSuperview); } else { objc_didMoveToSuperview(this, didMoveToSuperview); }
    }
    
    private static final Selector didMoveToWindow = Selector.register("didMoveToWindow");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didMoveToWindow(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didMoveToWindowSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didMoveToWindow">- (void)didMoveToWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didMoveToWindow() {
        if (customClass) { objc_didMoveToWindowSuper(getSuper(), this, didMoveToWindow); } else { objc_didMoveToWindow(this, didMoveToWindow); }
    }
    
    private static final Selector drawRect$ = Selector.register("drawRect:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_draw(UIView __self__, Selector __cmd__, CGRect rect);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_drawSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/drawRect:">- (void)drawRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void draw(CGRect rect) {
        if (customClass) { objc_drawSuper(getSuper(), this, drawRect$, rect); } else { objc_draw(this, drawRect$, rect); }
    }
    
    private static final Selector drawRect$forViewPrintFormatter$ = Selector.register("drawRect:forViewPrintFormatter:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_drawRect(UIView __self__, Selector __cmd__, CGRect area, UIViewPrintFormatter formatter);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_drawRectSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect area, UIViewPrintFormatter formatter);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/drawRect:forViewPrintFormatter:">- (void)drawRect:(CGRect)area forViewPrintFormatter:(UIViewPrintFormatter *)formatter</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawRect(CGRect area, UIViewPrintFormatter formatter) {
        if (customClass) { objc_drawRectSuper(getSuper(), this, drawRect$forViewPrintFormatter$, area, formatter); } else { objc_drawRect(this, drawRect$forViewPrintFormatter$, area, formatter); }
    }
    
    private static final Selector encodeRestorableStateWithCoder$ = Selector.register("encodeRestorableStateWithCoder:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_encodeRestorableState(UIView __self__, Selector __cmd__, NSCoder coder);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_encodeRestorableStateSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/encodeRestorableStateWithCoder:">- (void) encodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void encodeRestorableState(NSCoder coder) {
        if (customClass) { objc_encodeRestorableStateSuper(getSuper(), this, encodeRestorableStateWithCoder$, coder); } else { objc_encodeRestorableState(this, encodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector endEditing$ = Selector.register("endEditing:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_endEditing(UIView __self__, Selector __cmd__, boolean force);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_endEditingSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, boolean force);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/endEditing:">- (BOOL)endEditing:(BOOL)force</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean endEditing(boolean force) {
        if (customClass) { return objc_endEditingSuper(getSuper(), this, endEditing$, force); } else { return objc_endEditing(this, endEditing$, force); }
    }
    
    private static final Selector exchangeSubviewAtIndex$withSubviewAtIndex$ = Selector.register("exchangeSubviewAtIndex:withSubviewAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_exchangeSubview(UIView __self__, Selector __cmd__, int index1, int index2);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_exchangeSubviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, int index1, int index2);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/exchangeSubviewAtIndex:withSubviewAtIndex:">- (void)exchangeSubviewAtIndex:(NSInteger)index1 withSubviewAtIndex:(NSInteger)index2</a>
     * @since Available in iOS 2.0 and later.
     */
    public void exchangeSubview(int index1, int index2) {
        if (customClass) { objc_exchangeSubviewSuper(getSuper(), this, exchangeSubviewAtIndex$withSubviewAtIndex$, index1, index2); } else { objc_exchangeSubview(this, exchangeSubviewAtIndex$withSubviewAtIndex$, index1, index2); }
    }
    
    private static final Selector exerciseAmbiguityInLayout = Selector.register("exerciseAmbiguityInLayout");
    @Bridge(symbol = "objc_msgSend") private native static void objc_exerciseAmbiguityInLayout(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_exerciseAmbiguityInLayoutSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/exerciseAmbiguityInLayout">- (void)exerciseAmbiguityInLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public void exerciseAmbiguityInLayout() {
        if (customClass) { objc_exerciseAmbiguityInLayoutSuper(getSuper(), this, exerciseAmbiguityInLayout); } else { objc_exerciseAmbiguityInLayout(this, exerciseAmbiguityInLayout); }
    }
    
    private static final Selector gestureRecognizerShouldBegin$ = Selector.register("gestureRecognizerShouldBegin:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_gestureRecognizerShouldBegin(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_gestureRecognizerShouldBeginSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/gestureRecognizerShouldBegin:">- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean gestureRecognizerShouldBegin(UIGestureRecognizer gestureRecognizer) {
        if (customClass) { return objc_gestureRecognizerShouldBeginSuper(getSuper(), this, gestureRecognizerShouldBegin$, gestureRecognizer); } else { return objc_gestureRecognizerShouldBegin(this, gestureRecognizerShouldBegin$, gestureRecognizer); }
    }
    
    private static final Selector frameForAlignmentRect$ = Selector.register("frameForAlignmentRect:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getAlignmentRectFrame(UIView __self__, Selector __cmd__, CGRect alignmentRect);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getAlignmentRectFrameSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect alignmentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/frameForAlignmentRect:">- (CGRect)frameForAlignmentRect:(CGRect)alignmentRect</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getAlignmentRectFrame(CGRect alignmentRect) {
        if (customClass) { return objc_getAlignmentRectFrameSuper(getSuper(), this, frameForAlignmentRect$, alignmentRect); } else { return objc_getAlignmentRectFrame(this, frameForAlignmentRect$, alignmentRect); }
    }
    
    private static final Selector alignmentRectInsets = Selector.register("alignmentRectInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getAlignmentRectInsets(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getAlignmentRectInsetsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/alignmentRectInsets">- (UIEdgeInsets)alignmentRectInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIEdgeInsets getAlignmentRectInsets() {
        if (customClass) { return objc_getAlignmentRectInsetsSuper(getSuper(), this, alignmentRectInsets); } else { return objc_getAlignmentRectInsets(this, alignmentRectInsets); }
    }
    
    private static final Selector viewForBaselineLayout = Selector.register("viewForBaselineLayout");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getBaselineLayoutView(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getBaselineLayoutViewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewForBaselineLayout">- (UIView *)viewForBaselineLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getBaselineLayoutView() {
        if (customClass) { return objc_getBaselineLayoutViewSuper(getSuper(), this, viewForBaselineLayout); } else { return objc_getBaselineLayoutView(this, viewForBaselineLayout); }
    }
    
    private static final Selector constraints = Selector.register("constraints");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getConstraints(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/constraints">- (NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getConstraints() {
        if (customClass) { return objc_getConstraintsSuper(getSuper(), this, constraints); } else { return objc_getConstraints(this, constraints); }
    }
    
    private static final Selector constraintsAffectingLayoutForAxis$ = Selector.register("constraintsAffectingLayoutForAxis:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getConstraintsAffectingLayout(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getConstraintsAffectingLayoutSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/constraintsAffectingLayoutForAxis:">- (NSArray *)constraintsAffectingLayoutForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getConstraintsAffectingLayout(UILayoutConstraintAxis axis) {
        if (customClass) { return objc_getConstraintsAffectingLayoutSuper(getSuper(), this, constraintsAffectingLayoutForAxis$, axis); } else { return objc_getConstraintsAffectingLayout(this, constraintsAffectingLayoutForAxis$, axis); }
    }
    
    private static final Selector contentCompressionResistancePriorityForAxis$ = Selector.register("contentCompressionResistancePriorityForAxis:");
    @Bridge(symbol = "objc_msgSend") private native static UILayoutPriority objc_getContentCompressionResistancePriority(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    @Bridge(symbol = "objc_msgSendSuper") private native static UILayoutPriority objc_getContentCompressionResistancePrioritySuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/contentCompressionResistancePriorityForAxis:">- (UILayoutPriority)contentCompressionResistancePriorityForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILayoutPriority getContentCompressionResistancePriority(UILayoutConstraintAxis axis) {
        if (customClass) { return objc_getContentCompressionResistancePrioritySuper(getSuper(), this, contentCompressionResistancePriorityForAxis$, axis); } else { return objc_getContentCompressionResistancePriority(this, contentCompressionResistancePriorityForAxis$, axis); }
    }
    
    private static final Selector contentHuggingPriorityForAxis$ = Selector.register("contentHuggingPriorityForAxis:");
    @Bridge(symbol = "objc_msgSend") private native static UILayoutPriority objc_getContentHuggingPriority(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    @Bridge(symbol = "objc_msgSendSuper") private native static UILayoutPriority objc_getContentHuggingPrioritySuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/contentHuggingPriorityForAxis:">- (UILayoutPriority)contentHuggingPriorityForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILayoutPriority getContentHuggingPriority(UILayoutConstraintAxis axis) {
        if (customClass) { return objc_getContentHuggingPrioritySuper(getSuper(), this, contentHuggingPriorityForAxis$, axis); } else { return objc_getContentHuggingPriority(this, contentHuggingPriorityForAxis$, axis); }
    }
    
    private static final Selector alignmentRectForFrame$ = Selector.register("alignmentRectForFrame:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getFrameAlignmentRect(UIView __self__, Selector __cmd__, CGRect frame);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getFrameAlignmentRectSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect frame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/alignmentRectForFrame:">- (CGRect)alignmentRectForFrame:(CGRect)frame</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getFrameAlignmentRect(CGRect frame) {
        if (customClass) { return objc_getFrameAlignmentRectSuper(getSuper(), this, alignmentRectForFrame$, frame); } else { return objc_getFrameAlignmentRect(this, alignmentRectForFrame$, frame); }
    }
    
    private static final Selector intrinsicContentSize = Selector.register("intrinsicContentSize");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getIntrinsicContentSize(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getIntrinsicContentSizeSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/intrinsicContentSize">- (CGSize)intrinsicContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getIntrinsicContentSize() {
        if (customClass) { return objc_getIntrinsicContentSizeSuper(getSuper(), this, intrinsicContentSize); } else { return objc_getIntrinsicContentSize(this, intrinsicContentSize); }
    }
    
    private static final Selector sizeThatFits$ = Selector.register("sizeThatFits:");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getSizeThatFits(UIView __self__, Selector __cmd__, CGSize size);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getSizeThatFitsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGSize size);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sizeThatFits:">- (CGSize)sizeThatFits:(CGSize)size</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getSizeThatFits(CGSize size) {
        if (customClass) { return objc_getSizeThatFitsSuper(getSuper(), this, sizeThatFits$, size); } else { return objc_getSizeThatFits(this, sizeThatFits$, size); }
    }
    
    private static final Selector systemLayoutSizeFittingSize$ = Selector.register("systemLayoutSizeFittingSize:");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getSystemLayoutSizeFittingSize(UIView __self__, Selector __cmd__, CGSize targetSize);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getSystemLayoutSizeFittingSizeSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGSize targetSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/systemLayoutSizeFittingSize:">- (CGSize)systemLayoutSizeFittingSize:(CGSize)targetSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getSystemLayoutSizeFittingSize(CGSize targetSize) {
        if (customClass) { return objc_getSystemLayoutSizeFittingSizeSuper(getSuper(), this, systemLayoutSizeFittingSize$, targetSize); } else { return objc_getSystemLayoutSizeFittingSize(this, systemLayoutSizeFittingSize$, targetSize); }
    }
    
    private static final Selector viewPrintFormatter = Selector.register("viewPrintFormatter");
    @Bridge(symbol = "objc_msgSend") private native static UIViewPrintFormatter objc_getViewPrintFormatter(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIViewPrintFormatter objc_getViewPrintFormatterSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewPrintFormatter">- (UIViewPrintFormatter *)viewPrintFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIViewPrintFormatter getViewPrintFormatter() {
        if (customClass) { return objc_getViewPrintFormatterSuper(getSuper(), this, viewPrintFormatter); } else { return objc_getViewPrintFormatter(this, viewPrintFormatter); }
    }
    
    private static final Selector viewWithTag$ = Selector.register("viewWithTag:");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getViewWithTag(UIView __self__, Selector __cmd__, int tag);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getViewWithTagSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, int tag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewWithTag:">- (UIView *)viewWithTag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getViewWithTag(int tag) {
        if (customClass) { return objc_getViewWithTagSuper(getSuper(), this, viewWithTag$, tag); } else { return objc_getViewWithTag(this, viewWithTag$, tag); }
    }
    
    private static final Selector hasAmbiguousLayout = Selector.register("hasAmbiguousLayout");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_hasAmbiguousLayout(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_hasAmbiguousLayoutSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/hasAmbiguousLayout">- (BOOL)hasAmbiguousLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean hasAmbiguousLayout() {
        if (customClass) { return objc_hasAmbiguousLayoutSuper(getSuper(), this, hasAmbiguousLayout); } else { return objc_hasAmbiguousLayout(this, hasAmbiguousLayout); }
    }
    
    private static final Selector hitTest$withEvent$ = Selector.register("hitTest:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_hitTest(UIView __self__, Selector __cmd__, CGPoint point, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_hitTestSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGPoint point, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/hitTest:withEvent:">- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView hitTest(CGPoint point, UIEvent event) {
        if (customClass) { return objc_hitTestSuper(getSuper(), this, hitTest$withEvent$, point, event); } else { return objc_hitTest(this, hitTest$withEvent$, point, event); }
    }
    
    private static final Selector insertSubview$atIndex$ = Selector.register("insertSubview:atIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertSubview(UIView __self__, Selector __cmd__, UIView view, int index);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertSubviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view, int index);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:atIndex:">- (void)insertSubview:(UIView *)view atIndex:(NSInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSubview(UIView view, int index) {
        if (customClass) { objc_insertSubviewSuper(getSuper(), this, insertSubview$atIndex$, view, index); } else { objc_insertSubview(this, insertSubview$atIndex$, view, index); }
    }
    
    private static final Selector insertSubview$aboveSubview$ = Selector.register("insertSubview:aboveSubview:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertSubviewAbove(UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertSubviewAboveSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:aboveSubview:">- (void)insertSubview:(UIView *)view aboveSubview:(UIView *)siblingSubview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSubviewAbove(UIView view, UIView siblingSubview) {
        if (customClass) { objc_insertSubviewAboveSuper(getSuper(), this, insertSubview$aboveSubview$, view, siblingSubview); } else { objc_insertSubviewAbove(this, insertSubview$aboveSubview$, view, siblingSubview); }
    }
    
    private static final Selector insertSubview$belowSubview$ = Selector.register("insertSubview:belowSubview:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertSubviewBelow(UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertSubviewBelowSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:belowSubview:">- (void)insertSubview:(UIView *)view belowSubview:(UIView *)siblingSubview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSubviewBelow(UIView view, UIView siblingSubview) {
        if (customClass) { objc_insertSubviewBelowSuper(getSuper(), this, insertSubview$belowSubview$, view, siblingSubview); } else { objc_insertSubviewBelow(this, insertSubview$belowSubview$, view, siblingSubview); }
    }
    
    private static final Selector invalidateIntrinsicContentSize = Selector.register("invalidateIntrinsicContentSize");
    @Bridge(symbol = "objc_msgSend") private native static void objc_invalidateIntrinsicContentSize(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_invalidateIntrinsicContentSizeSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/invalidateIntrinsicContentSize">- (void)invalidateIntrinsicContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public void invalidateIntrinsicContentSize() {
        if (customClass) { objc_invalidateIntrinsicContentSizeSuper(getSuper(), this, invalidateIntrinsicContentSize); } else { objc_invalidateIntrinsicContentSize(this, invalidateIntrinsicContentSize); }
    }
    
    private static final Selector isDescendantOfView$ = Selector.register("isDescendantOfView:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isDescendantOf(UIView __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isDescendantOfSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/isDescendantOfView:">- (BOOL)isDescendantOfView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDescendantOf(UIView view) {
        if (customClass) { return objc_isDescendantOfSuper(getSuper(), this, isDescendantOfView$, view); } else { return objc_isDescendantOf(this, isDescendantOfView$, view); }
    }
    
    private static final Selector translatesAutoresizingMaskIntoConstraints = Selector.register("translatesAutoresizingMaskIntoConstraints");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isTranslatesAutoresizingMaskIntoConstraints(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isTranslatesAutoresizingMaskIntoConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/translatesAutoresizingMaskIntoConstraints">- (BOOL)translatesAutoresizingMaskIntoConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isTranslatesAutoresizingMaskIntoConstraints() {
        if (customClass) { return objc_isTranslatesAutoresizingMaskIntoConstraintsSuper(getSuper(), this, translatesAutoresizingMaskIntoConstraints); } else { return objc_isTranslatesAutoresizingMaskIntoConstraints(this, translatesAutoresizingMaskIntoConstraints); }
    }
    
    private static final Selector layoutIfNeeded = Selector.register("layoutIfNeeded");
    @Bridge(symbol = "objc_msgSend") private native static void objc_layoutIfNeeded(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_layoutIfNeededSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/layoutIfNeeded">- (void)layoutIfNeeded</a>
     * @since Available in iOS 2.0 and later.
     */
    public void layoutIfNeeded() {
        if (customClass) { objc_layoutIfNeededSuper(getSuper(), this, layoutIfNeeded); } else { objc_layoutIfNeeded(this, layoutIfNeeded); }
    }
    
    private static final Selector layoutSubviews = Selector.register("layoutSubviews");
    @Bridge(symbol = "objc_msgSend") private native static void objc_layoutSubviews(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_layoutSubviewsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/layoutSubviews">- (void)layoutSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    public void layoutSubviews() {
        if (customClass) { objc_layoutSubviewsSuper(getSuper(), this, layoutSubviews); } else { objc_layoutSubviews(this, layoutSubviews); }
    }
    
    private static final Selector needsUpdateConstraints = Selector.register("needsUpdateConstraints");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_needsUpdateConstraints(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_needsUpdateConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/needsUpdateConstraints">- (BOOL)needsUpdateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean needsUpdateConstraints() {
        if (customClass) { return objc_needsUpdateConstraintsSuper(getSuper(), this, needsUpdateConstraints); } else { return objc_needsUpdateConstraints(this, needsUpdateConstraints); }
    }
    
    private static final Selector pointInside$withEvent$ = Selector.register("pointInside:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_pointInside(UIView __self__, Selector __cmd__, CGPoint point, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_pointInsideSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGPoint point, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/pointInside:withEvent:">- (BOOL)pointInside:(CGPoint)point withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean pointInside(CGPoint point, UIEvent event) {
        if (customClass) { return objc_pointInsideSuper(getSuper(), this, pointInside$withEvent$, point, event); } else { return objc_pointInside(this, pointInside$withEvent$, point, event); }
    }
    
    private static final Selector removeConstraint$ = Selector.register("removeConstraint:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeConstraint(UIView __self__, Selector __cmd__, NSLayoutConstraint constraint);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeConstraintSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, NSLayoutConstraint constraint);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeConstraint:">- (void)removeConstraint:(NSLayoutConstraint *)constraint</a>
     * @since Available in iOS 6.0 and later.
     */
    public void removeConstraint(NSLayoutConstraint constraint) {
        if (customClass) { objc_removeConstraintSuper(getSuper(), this, removeConstraint$, constraint); } else { objc_removeConstraint(this, removeConstraint$, constraint); }
    }
    
    private static final Selector removeConstraints$ = Selector.register("removeConstraints:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeConstraints(UIView __self__, Selector __cmd__, NSArray constraints);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, NSArray constraints);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeConstraints:">- (void)removeConstraints:(NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void removeConstraints(NSArray constraints) {
        if (customClass) { objc_removeConstraintsSuper(getSuper(), this, removeConstraints$, constraints); } else { objc_removeConstraints(this, removeConstraints$, constraints); }
    }
    
    private static final Selector removeFromSuperview = Selector.register("removeFromSuperview");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeFromSuperview(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeFromSuperviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeFromSuperview">- (void)removeFromSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeFromSuperview() {
        if (customClass) { objc_removeFromSuperviewSuper(getSuper(), this, removeFromSuperview); } else { objc_removeFromSuperview(this, removeFromSuperview); }
    }
    
    private static final Selector removeGestureRecognizer$ = Selector.register("removeGestureRecognizer:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeGestureRecognizer(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeGestureRecognizerSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeGestureRecognizer:">- (void)removeGestureRecognizer:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public void removeGestureRecognizer(UIGestureRecognizer gestureRecognizer) {
        if (customClass) { objc_removeGestureRecognizerSuper(getSuper(), this, removeGestureRecognizer$, gestureRecognizer); } else { objc_removeGestureRecognizer(this, removeGestureRecognizer$, gestureRecognizer); }
    }
    
    private static final Selector sizeToFit = Selector.register("sizeToFit");
    @Bridge(symbol = "objc_msgSend") private native static void objc_resizeToFit(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_resizeToFitSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sizeToFit">- (void)sizeToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    public void resizeToFit() {
        if (customClass) { objc_resizeToFitSuper(getSuper(), this, sizeToFit); } else { objc_resizeToFit(this, sizeToFit); }
    }
    
    private static final Selector sendSubviewToBack$ = Selector.register("sendSubviewToBack:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_sendSubviewToBack(UIView __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_sendSubviewToBackSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sendSubviewToBack:">- (void)sendSubviewToBack:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendSubviewToBack(UIView view) {
        if (customClass) { objc_sendSubviewToBackSuper(getSuper(), this, sendSubviewToBack$, view); } else { objc_sendSubviewToBack(this, sendSubviewToBack$, view); }
    }
    
    private static final Selector setContentCompressionResistancePriority$forAxis$ = Selector.register("setContentCompressionResistancePriority:forAxis:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentCompressionResistancePriority(UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentCompressionResistancePrioritySuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setContentCompressionResistancePriority:forAxis:">- (void)setContentCompressionResistancePriority:(UILayoutPriority)priority forAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setContentCompressionResistancePriority(UILayoutPriority priority, UILayoutConstraintAxis axis) {
        if (customClass) { objc_setContentCompressionResistancePrioritySuper(getSuper(), this, setContentCompressionResistancePriority$forAxis$, priority, axis); } else { objc_setContentCompressionResistancePriority(this, setContentCompressionResistancePriority$forAxis$, priority, axis); }
    }
    
    private static final Selector setContentHuggingPriority$forAxis$ = Selector.register("setContentHuggingPriority:forAxis:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentHuggingPriority(UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentHuggingPrioritySuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setContentHuggingPriority:forAxis:">- (void)setContentHuggingPriority:(UILayoutPriority)priority forAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setContentHuggingPriority(UILayoutPriority priority, UILayoutConstraintAxis axis) {
        if (customClass) { objc_setContentHuggingPrioritySuper(getSuper(), this, setContentHuggingPriority$forAxis$, priority, axis); } else { objc_setContentHuggingPriority(this, setContentHuggingPriority$forAxis$, priority, axis); }
    }
    
    private static final Selector setNeedsDisplay = Selector.register("setNeedsDisplay");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setNeedsDisplay(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setNeedsDisplaySuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsDisplay">- (void)setNeedsDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNeedsDisplay() {
        if (customClass) { objc_setNeedsDisplaySuper(getSuper(), this, setNeedsDisplay); } else { objc_setNeedsDisplay(this, setNeedsDisplay); }
    }
    
    private static final Selector setNeedsDisplayInRect$ = Selector.register("setNeedsDisplayInRect:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setNeedsDisplay(UIView __self__, Selector __cmd__, CGRect invalidRect);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setNeedsDisplaySuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, CGRect invalidRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsDisplayInRect:">- (void)setNeedsDisplayInRect:(CGRect)invalidRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNeedsDisplay(CGRect invalidRect) {
        if (customClass) { objc_setNeedsDisplaySuper(getSuper(), this, setNeedsDisplayInRect$, invalidRect); } else { objc_setNeedsDisplay(this, setNeedsDisplayInRect$, invalidRect); }
    }
    
    private static final Selector setNeedsLayout = Selector.register("setNeedsLayout");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setNeedsLayout(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setNeedsLayoutSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsLayout">- (void)setNeedsLayout</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNeedsLayout() {
        if (customClass) { objc_setNeedsLayoutSuper(getSuper(), this, setNeedsLayout); } else { objc_setNeedsLayout(this, setNeedsLayout); }
    }
    
    private static final Selector setNeedsUpdateConstraints = Selector.register("setNeedsUpdateConstraints");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setNeedsUpdateConstraints(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setNeedsUpdateConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsUpdateConstraints">- (void)setNeedsUpdateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setNeedsUpdateConstraints() {
        if (customClass) { objc_setNeedsUpdateConstraintsSuper(getSuper(), this, setNeedsUpdateConstraints); } else { objc_setNeedsUpdateConstraints(this, setNeedsUpdateConstraints); }
    }
    
    private static final Selector setTranslatesAutoresizingMaskIntoConstraints$ = Selector.register("setTranslatesAutoresizingMaskIntoConstraints:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTranslatesAutoresizingMaskIntoConstraints(UIView __self__, Selector __cmd__, boolean flag);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTranslatesAutoresizingMaskIntoConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, boolean flag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setTranslatesAutoresizingMaskIntoConstraints:">- (void)setTranslatesAutoresizingMaskIntoConstraints:(BOOL)flag</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTranslatesAutoresizingMaskIntoConstraints(boolean flag) {
        if (customClass) { objc_setTranslatesAutoresizingMaskIntoConstraintsSuper(getSuper(), this, setTranslatesAutoresizingMaskIntoConstraints$, flag); } else { objc_setTranslatesAutoresizingMaskIntoConstraints(this, setTranslatesAutoresizingMaskIntoConstraints$, flag); }
    }
    
    private static final Selector updateConstraints = Selector.register("updateConstraints");
    @Bridge(symbol = "objc_msgSend") private native static void objc_updateConstraints(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_updateConstraintsSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/updateConstraints">- (void)updateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void updateConstraints() {
        if (customClass) { objc_updateConstraintsSuper(getSuper(), this, updateConstraints); } else { objc_updateConstraints(this, updateConstraints); }
    }
    
    private static final Selector updateConstraintsIfNeeded = Selector.register("updateConstraintsIfNeeded");
    @Bridge(symbol = "objc_msgSend") private native static void objc_updateConstraintsIfNeeded(UIView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_updateConstraintsIfNeededSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/updateConstraintsIfNeeded">- (void)updateConstraintsIfNeeded</a>
     * @since Available in iOS 6.0 and later.
     */
    public void updateConstraintsIfNeeded() {
        if (customClass) { objc_updateConstraintsIfNeededSuper(getSuper(), this, updateConstraintsIfNeeded); } else { objc_updateConstraintsIfNeeded(this, updateConstraintsIfNeeded); }
    }
    
    private static final Selector willMoveToSuperview$ = Selector.register("willMoveToSuperview:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willMoveToSuperview(UIView __self__, Selector __cmd__, UIView newSuperview);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willMoveToSuperviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView newSuperview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willMoveToSuperview:">- (void)willMoveToSuperview:(UIView *)newSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willMoveToSuperview(UIView newSuperview) {
        if (customClass) { objc_willMoveToSuperviewSuper(getSuper(), this, willMoveToSuperview$, newSuperview); } else { objc_willMoveToSuperview(this, willMoveToSuperview$, newSuperview); }
    }
    
    private static final Selector willMoveToWindow$ = Selector.register("willMoveToWindow:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willMoveToWindow(UIView __self__, Selector __cmd__, UIWindow newWindow);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willMoveToWindowSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIWindow newWindow);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willMoveToWindow:">- (void)willMoveToWindow:(UIWindow *)newWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willMoveToWindow(UIWindow newWindow) {
        if (customClass) { objc_willMoveToWindowSuper(getSuper(), this, willMoveToWindow$, newWindow); } else { objc_willMoveToWindow(this, willMoveToWindow$, newWindow); }
    }
    
    private static final Selector willRemoveSubview$ = Selector.register("willRemoveSubview:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willRemoveSubview(UIView __self__, Selector __cmd__, UIView subview);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willRemoveSubviewSuper(ObjCSuper __super__, UIView __self__, Selector __cmd__, UIView subview);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willRemoveSubview:">- (void)willRemoveSubview:(UIView *)subview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willRemoveSubview(UIView subview) {
        if (customClass) { objc_willRemoveSubviewSuper(getSuper(), this, willRemoveSubview$, subview); } else { objc_willRemoveSubview(this, willRemoveSubview$, subview); }
    }
    /*</methods>*/

}
