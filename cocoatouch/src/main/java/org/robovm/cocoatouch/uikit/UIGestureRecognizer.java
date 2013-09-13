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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html">UIGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIGestureRecognizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIGestureRecognizer /*</name>*/.class);

    /*<constructors>*/
    protected UIGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UIGestureRecognizer() {}
    
    private static final Selector initWithTarget$action$ = Selector.register("initWithTarget:action:");
    @Bridge private native static @Pointer long objc_initWithTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/initWithTarget:action:">- (id)initWithTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIGestureRecognizer(NSObject target, Selector action) {
        super((SkipInit) null);
        initObject(objc_initWithTarget(this, initWithTarget$action$, target, action));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector cancelsTouchesInView = Selector.register("cancelsTouchesInView");
    @Bridge private native static boolean objc_isCancelsTouchesInView(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isCancelsTouchesInViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/cancelsTouchesInView">@property(nonatomic) BOOL cancelsTouchesInView</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isCancelsTouchesInView() {
        if (customClass) { return objc_isCancelsTouchesInViewSuper(getSuper(), cancelsTouchesInView); } else { return objc_isCancelsTouchesInView(this, cancelsTouchesInView); }
    }
    
    private static final Selector setCancelsTouchesInView$ = Selector.register("setCancelsTouchesInView:");
    @Bridge private native static void objc_setCancelsTouchesInView(UIGestureRecognizer __self__, Selector __cmd__, boolean cancelsTouchesInView);
    @Bridge private native static void objc_setCancelsTouchesInViewSuper(ObjCSuper __super__, Selector __cmd__, boolean cancelsTouchesInView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/cancelsTouchesInView">@property(nonatomic) BOOL cancelsTouchesInView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setCancelsTouchesInView(boolean cancelsTouchesInView) {
        if (customClass) { objc_setCancelsTouchesInViewSuper(getSuper(), setCancelsTouchesInView$, cancelsTouchesInView); } else { objc_setCancelsTouchesInView(this, setCancelsTouchesInView$, cancelsTouchesInView); }
    }
    
    private static final Selector delaysTouchesBegan = Selector.register("delaysTouchesBegan");
    @Bridge private native static boolean objc_isDelaysTouchesBegan(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDelaysTouchesBeganSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesBegan">@property(nonatomic) BOOL delaysTouchesBegan</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isDelaysTouchesBegan() {
        if (customClass) { return objc_isDelaysTouchesBeganSuper(getSuper(), delaysTouchesBegan); } else { return objc_isDelaysTouchesBegan(this, delaysTouchesBegan); }
    }
    
    private static final Selector setDelaysTouchesBegan$ = Selector.register("setDelaysTouchesBegan:");
    @Bridge private native static void objc_setDelaysTouchesBegan(UIGestureRecognizer __self__, Selector __cmd__, boolean delaysTouchesBegan);
    @Bridge private native static void objc_setDelaysTouchesBeganSuper(ObjCSuper __super__, Selector __cmd__, boolean delaysTouchesBegan);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesBegan">@property(nonatomic) BOOL delaysTouchesBegan</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDelaysTouchesBegan(boolean delaysTouchesBegan) {
        if (customClass) { objc_setDelaysTouchesBeganSuper(getSuper(), setDelaysTouchesBegan$, delaysTouchesBegan); } else { objc_setDelaysTouchesBegan(this, setDelaysTouchesBegan$, delaysTouchesBegan); }
    }
    
    private static final Selector delaysTouchesEnded = Selector.register("delaysTouchesEnded");
    @Bridge private native static boolean objc_isDelaysTouchesEnded(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDelaysTouchesEndedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesEnded">@property(nonatomic) BOOL delaysTouchesEnded</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isDelaysTouchesEnded() {
        if (customClass) { return objc_isDelaysTouchesEndedSuper(getSuper(), delaysTouchesEnded); } else { return objc_isDelaysTouchesEnded(this, delaysTouchesEnded); }
    }
    
    private static final Selector setDelaysTouchesEnded$ = Selector.register("setDelaysTouchesEnded:");
    @Bridge private native static void objc_setDelaysTouchesEnded(UIGestureRecognizer __self__, Selector __cmd__, boolean delaysTouchesEnded);
    @Bridge private native static void objc_setDelaysTouchesEndedSuper(ObjCSuper __super__, Selector __cmd__, boolean delaysTouchesEnded);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesEnded">@property(nonatomic) BOOL delaysTouchesEnded</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDelaysTouchesEnded(boolean delaysTouchesEnded) {
        if (customClass) { objc_setDelaysTouchesEndedSuper(getSuper(), setDelaysTouchesEnded$, delaysTouchesEnded); } else { objc_setDelaysTouchesEnded(this, setDelaysTouchesEnded$, delaysTouchesEnded); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIGestureRecognizerDelegate objc_getDelegate(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static UIGestureRecognizerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delegate">@property(nonatomic, assign) id&amp;lt;UIGestureRecognizerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIGestureRecognizerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delegate">@property(nonatomic, assign) id&amp;lt;UIGestureRecognizerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDelegate(UIGestureRecognizerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isEnabled = Selector.register("isEnabled");
    @Bridge private native static boolean objc_isEnabled(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isEnabled() {
        if (customClass) { return objc_isEnabledSuper(getSuper(), isEnabled); } else { return objc_isEnabled(this, isEnabled); }
    }
    
    private static final Selector setEnabled$ = Selector.register("setEnabled:");
    @Bridge private native static void objc_setEnabled(UIGestureRecognizer __self__, Selector __cmd__, boolean enabled);
    @Bridge private native static void objc_setEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean enabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setEnabled(boolean enabled) {
        if (customClass) { objc_setEnabledSuper(getSuper(), setEnabled$, enabled); } else { objc_setEnabled(this, setEnabled$, enabled); }
    }
    
    private static final Selector state = Selector.register("state");
    @Bridge private native static UIGestureRecognizerState objc_getState(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static UIGestureRecognizerState objc_getStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/state">@property(nonatomic, readonly) UIGestureRecognizerState state</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIGestureRecognizerState getState() {
        if (customClass) { return objc_getStateSuper(getSuper(), state); } else { return objc_getState(this, state); }
    }
    
    private static final Selector view = Selector.register("view");
    @Bridge private native static UIView objc_getView(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/view">@property(nonatomic, readonly) UIView *view</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getView() {
        if (customClass) { return objc_getViewSuper(getSuper(), view); } else { return objc_getView(this, view); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addTarget$action$ = Selector.register("addTarget:action:");
    @Bridge private native static void objc_addTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    @Bridge private native static void objc_addTargetSuper(ObjCSuper __super__, Selector __cmd__, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/addTarget:action:">- (void)addTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addTarget(NSObject target, Selector action) {
        if (customClass) { objc_addTargetSuper(getSuper(), addTarget$action$, target, action); } else { objc_addTarget(this, addTarget$action$, target, action); }
    }
    
    private static final Selector canBePreventedByGestureRecognizer$ = Selector.register("canBePreventedByGestureRecognizer:");
    @Bridge private native static boolean objc_canBePreventedByGestureRecognizer(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventingGestureRecognizer);
    @Bridge private native static boolean objc_canBePreventedByGestureRecognizerSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizer preventingGestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/canBePreventedByGestureRecognizer:">- (BOOL)canBePreventedByGestureRecognizer:(UIGestureRecognizer *)preventingGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean canBePreventedByGestureRecognizer(UIGestureRecognizer preventingGestureRecognizer) {
        if (customClass) { return objc_canBePreventedByGestureRecognizerSuper(getSuper(), canBePreventedByGestureRecognizer$, preventingGestureRecognizer); } else { return objc_canBePreventedByGestureRecognizer(this, canBePreventedByGestureRecognizer$, preventingGestureRecognizer); }
    }
    
    private static final Selector canPreventGestureRecognizer$ = Selector.register("canPreventGestureRecognizer:");
    @Bridge private native static boolean objc_canPreventGestureRecognizer(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventedGestureRecognizer);
    @Bridge private native static boolean objc_canPreventGestureRecognizerSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizer preventedGestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/canPreventGestureRecognizer:">- (BOOL)canPreventGestureRecognizer:(UIGestureRecognizer *)preventedGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean canPreventGestureRecognizer(UIGestureRecognizer preventedGestureRecognizer) {
        if (customClass) { return objc_canPreventGestureRecognizerSuper(getSuper(), canPreventGestureRecognizer$, preventedGestureRecognizer); } else { return objc_canPreventGestureRecognizer(this, canPreventGestureRecognizer$, preventedGestureRecognizer); }
    }
    
    private static final Selector locationInView$ = Selector.register("locationInView:");
    @Bridge private native static @ByVal CGPoint objc_getLocation(UIGestureRecognizer __self__, Selector __cmd__, UIView view);
    @Bridge private native static @ByVal CGPoint objc_getLocationSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/locationInView:">- (CGPoint)locationInView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getLocation(UIView view) {
        if (customClass) { return objc_getLocationSuper(getSuper(), locationInView$, view); } else { return objc_getLocation(this, locationInView$, view); }
    }
    
    private static final Selector locationOfTouch$inView$ = Selector.register("locationOfTouch:inView:");
    @Bridge private native static @ByVal CGPoint objc_getLocation(UIGestureRecognizer __self__, Selector __cmd__, int touchIndex, UIView view);
    @Bridge private native static @ByVal CGPoint objc_getLocationSuper(ObjCSuper __super__, Selector __cmd__, int touchIndex, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/locationOfTouch:inView:">- (CGPoint)locationOfTouch:(NSUInteger)touchIndex inView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getLocation(int touchIndex, UIView view) {
        if (customClass) { return objc_getLocationSuper(getSuper(), locationOfTouch$inView$, touchIndex, view); } else { return objc_getLocation(this, locationOfTouch$inView$, touchIndex, view); }
    }
    
    private static final Selector numberOfTouches = Selector.register("numberOfTouches");
    @Bridge private native static int objc_getNumberOfTouches(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfTouchesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/numberOfTouches">- (NSUInteger)numberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getNumberOfTouches() {
        if (customClass) { return objc_getNumberOfTouchesSuper(getSuper(), numberOfTouches); } else { return objc_getNumberOfTouches(this, numberOfTouches); }
    }
    
    private static final Selector ignoreTouch$forEvent$ = Selector.register("ignoreTouch:forEvent:");
    @Bridge private native static void objc_ignoreTouch(UIGestureRecognizer __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge private native static void objc_ignoreTouchSuper(ObjCSuper __super__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/ignoreTouch:forEvent:">- (void)ignoreTouch:(UITouch *)touch forEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void ignoreTouch(UITouch touch, UIEvent event) {
        if (customClass) { objc_ignoreTouchSuper(getSuper(), ignoreTouch$forEvent$, touch, event); } else { objc_ignoreTouch(this, ignoreTouch$forEvent$, touch, event); }
    }
    
    private static final Selector removeTarget$action$ = Selector.register("removeTarget:action:");
    @Bridge private native static void objc_removeTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    @Bridge private native static void objc_removeTargetSuper(ObjCSuper __super__, Selector __cmd__, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/removeTarget:action:">- (void)removeTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public void removeTarget(NSObject target, Selector action) {
        if (customClass) { objc_removeTargetSuper(getSuper(), removeTarget$action$, target, action); } else { objc_removeTarget(this, removeTarget$action$, target, action); }
    }
    
    private static final Selector requireGestureRecognizerToFail$ = Selector.register("requireGestureRecognizerToFail:");
    @Bridge private native static void objc_requireGestureRecognizerToFail(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer otherGestureRecognizer);
    @Bridge private native static void objc_requireGestureRecognizerToFailSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizer otherGestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/requireGestureRecognizerToFail:">- (void)requireGestureRecognizerToFail:(UIGestureRecognizer *)otherGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public void requireGestureRecognizerToFail(UIGestureRecognizer otherGestureRecognizer) {
        if (customClass) { objc_requireGestureRecognizerToFailSuper(getSuper(), requireGestureRecognizerToFail$, otherGestureRecognizer); } else { objc_requireGestureRecognizerToFail(this, requireGestureRecognizerToFail$, otherGestureRecognizer); }
    }
    
    private static final Selector reset = Selector.register("reset");
    @Bridge private native static void objc_reset(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static void objc_resetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/reset">- (void)reset</a>
     * @since Available in iOS 3.2 and later.
     */
    public void reset() {
        if (customClass) { objc_resetSuper(getSuper(), reset); } else { objc_reset(this, reset); }
    }
    
    private static final Selector touchesBegan$withEvent$ = Selector.register("touchesBegan:withEvent:");
    @Bridge private native static void objc_touchesBegan(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesBeganSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesBegan:withEvent:">- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesBegan(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesBeganSuper(getSuper(), touchesBegan$withEvent$, touches, event); } else { objc_touchesBegan(this, touchesBegan$withEvent$, touches, event); }
    }
    
    private static final Selector touchesCancelled$withEvent$ = Selector.register("touchesCancelled:withEvent:");
    @Bridge private native static void objc_touchesCancelled(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesCancelledSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesCancelled:withEvent:">- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesCancelled(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesCancelledSuper(getSuper(), touchesCancelled$withEvent$, touches, event); } else { objc_touchesCancelled(this, touchesCancelled$withEvent$, touches, event); }
    }
    
    private static final Selector touchesEnded$withEvent$ = Selector.register("touchesEnded:withEvent:");
    @Bridge private native static void objc_touchesEnded(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesEndedSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesEnded:withEvent:">- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesEnded(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesEndedSuper(getSuper(), touchesEnded$withEvent$, touches, event); } else { objc_touchesEnded(this, touchesEnded$withEvent$, touches, event); }
    }
    
    private static final Selector touchesMoved$withEvent$ = Selector.register("touchesMoved:withEvent:");
    @Bridge private native static void objc_touchesMoved(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesMovedSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesMoved:withEvent:">- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesMoved(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesMovedSuper(getSuper(), touchesMoved$withEvent$, touches, event); } else { objc_touchesMoved(this, touchesMoved$withEvent$, touches, event); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("cancelsTouchesInView") public static boolean isCancelsTouchesInView(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.isCancelsTouchesInView(); }
        @Callback @BindSelector("setCancelsTouchesInView:") public static void setCancelsTouchesInView(UIGestureRecognizer __self__, Selector __cmd__, boolean cancelsTouchesInView) { __self__.setCancelsTouchesInView(cancelsTouchesInView); }
        @Callback @BindSelector("delaysTouchesBegan") public static boolean isDelaysTouchesBegan(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.isDelaysTouchesBegan(); }
        @Callback @BindSelector("setDelaysTouchesBegan:") public static void setDelaysTouchesBegan(UIGestureRecognizer __self__, Selector __cmd__, boolean delaysTouchesBegan) { __self__.setDelaysTouchesBegan(delaysTouchesBegan); }
        @Callback @BindSelector("delaysTouchesEnded") public static boolean isDelaysTouchesEnded(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.isDelaysTouchesEnded(); }
        @Callback @BindSelector("setDelaysTouchesEnded:") public static void setDelaysTouchesEnded(UIGestureRecognizer __self__, Selector __cmd__, boolean delaysTouchesEnded) { __self__.setDelaysTouchesEnded(delaysTouchesEnded); }
        @Callback @BindSelector("delegate") public static UIGestureRecognizerDelegate getDelegate(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("isEnabled") public static boolean isEnabled(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.isEnabled(); }
        @Callback @BindSelector("setEnabled:") public static void setEnabled(UIGestureRecognizer __self__, Selector __cmd__, boolean enabled) { __self__.setEnabled(enabled); }
        @Callback @BindSelector("state") public static UIGestureRecognizerState getState(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.getState(); }
        @Callback @BindSelector("view") public static UIView getView(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.getView(); }
        @Callback @BindSelector("addTarget:action:") public static void addTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action) { __self__.addTarget(target, action); }
        @Callback @BindSelector("canBePreventedByGestureRecognizer:") public static boolean canBePreventedByGestureRecognizer(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventingGestureRecognizer) { return __self__.canBePreventedByGestureRecognizer(preventingGestureRecognizer); }
        @Callback @BindSelector("canPreventGestureRecognizer:") public static boolean canPreventGestureRecognizer(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventedGestureRecognizer) { return __self__.canPreventGestureRecognizer(preventedGestureRecognizer); }
        @Callback @BindSelector("locationInView:") public static @ByVal CGPoint getLocation(UIGestureRecognizer __self__, Selector __cmd__, UIView view) { return __self__.getLocation(view); }
        @Callback @BindSelector("locationOfTouch:inView:") public static @ByVal CGPoint getLocation(UIGestureRecognizer __self__, Selector __cmd__, int touchIndex, UIView view) { return __self__.getLocation(touchIndex, view); }
        @Callback @BindSelector("numberOfTouches") public static int getNumberOfTouches(UIGestureRecognizer __self__, Selector __cmd__) { return __self__.getNumberOfTouches(); }
        @Callback @BindSelector("ignoreTouch:forEvent:") public static void ignoreTouch(UIGestureRecognizer __self__, Selector __cmd__, UITouch touch, UIEvent event) { __self__.ignoreTouch(touch, event); }
        @Callback @BindSelector("removeTarget:action:") public static void removeTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action) { __self__.removeTarget(target, action); }
        @Callback @BindSelector("requireGestureRecognizerToFail:") public static void requireGestureRecognizerToFail(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer otherGestureRecognizer) { __self__.requireGestureRecognizerToFail(otherGestureRecognizer); }
        @Callback @BindSelector("reset") public static void reset(UIGestureRecognizer __self__, Selector __cmd__) { __self__.reset(); }
        @Callback @BindSelector("touchesBegan:withEvent:") public static void touchesBegan(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesBegan(touches, event); }
        @Callback @BindSelector("touchesCancelled:withEvent:") public static void touchesCancelled(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesCancelled(touches, event); }
        @Callback @BindSelector("touchesEnded:withEvent:") public static void touchesEnded(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesEnded(touches, event); }
        @Callback @BindSelector("touchesMoved:withEvent:") public static void touchesMoved(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesMoved(touches, event); }
    }
    /*</callbacks>*/

}
