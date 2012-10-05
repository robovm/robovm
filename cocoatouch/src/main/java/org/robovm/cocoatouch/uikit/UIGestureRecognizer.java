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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html">UIGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIGestureRecognizer /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/initWithTarget:action:">- (id)initWithTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIGestureRecognizer(NSObject target, Selector action) {
        super((SkipInit) null);
        objc_initWithTarget(this, initWithTarget$action$, target, action);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/cancelsTouchesInView">@property(nonatomic) BOOL cancelsTouchesInView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("cancelsTouchesInView") public native boolean isCancelsTouchesInView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/cancelsTouchesInView">@property(nonatomic) BOOL cancelsTouchesInView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setCancelsTouchesInView:") public native void setCancelsTouchesInView(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesBegan">@property(nonatomic) BOOL delaysTouchesBegan</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delaysTouchesBegan") public native boolean isDelaysTouchesBegan();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesBegan">@property(nonatomic) BOOL delaysTouchesBegan</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelaysTouchesBegan:") public native void setDelaysTouchesBegan(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesEnded">@property(nonatomic) BOOL delaysTouchesEnded</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delaysTouchesEnded") public native boolean isDelaysTouchesEnded();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesEnded">@property(nonatomic) BOOL delaysTouchesEnded</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelaysTouchesEnded:") public native void setDelaysTouchesEnded(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delegate">@property(nonatomic, assign) id&amp;lt;UIGestureRecognizerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native UIGestureRecognizerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delegate">@property(nonatomic, assign) id&amp;lt;UIGestureRecognizerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIGestureRecognizerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isEnabled") public native boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/state">@property(nonatomic, readonly) UIGestureRecognizerState state</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("state") public native UIGestureRecognizerState getState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/view">@property(nonatomic, readonly) UIView *view</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("view") public native UIView getView();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addTarget$action$ = Selector.register("addTarget:action:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addTargetSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/addTarget:action:">- (void)addTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addTarget(NSObject target, Selector action) {
        if (customClass) { objc_addTargetSuper(getSuper(), this, addTarget$action$, target, action); } else { objc_addTarget(this, addTarget$action$, target, action); }
    }
    
    private static final Selector canBePreventedByGestureRecognizer$ = Selector.register("canBePreventedByGestureRecognizer:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_canBePreventedByGestureRecognizer(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventingGestureRecognizer);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_canBePreventedByGestureRecognizerSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventingGestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/canBePreventedByGestureRecognizer:">- (BOOL)canBePreventedByGestureRecognizer:(UIGestureRecognizer *)preventingGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean canBePreventedByGestureRecognizer(UIGestureRecognizer preventingGestureRecognizer) {
        if (customClass) { return objc_canBePreventedByGestureRecognizerSuper(getSuper(), this, canBePreventedByGestureRecognizer$, preventingGestureRecognizer); } else { return objc_canBePreventedByGestureRecognizer(this, canBePreventedByGestureRecognizer$, preventingGestureRecognizer); }
    }
    
    private static final Selector canPreventGestureRecognizer$ = Selector.register("canPreventGestureRecognizer:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_canPreventGestureRecognizer(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventedGestureRecognizer);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_canPreventGestureRecognizerSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer preventedGestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/canPreventGestureRecognizer:">- (BOOL)canPreventGestureRecognizer:(UIGestureRecognizer *)preventedGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean canPreventGestureRecognizer(UIGestureRecognizer preventedGestureRecognizer) {
        if (customClass) { return objc_canPreventGestureRecognizerSuper(getSuper(), this, canPreventGestureRecognizer$, preventedGestureRecognizer); } else { return objc_canPreventGestureRecognizer(this, canPreventGestureRecognizer$, preventedGestureRecognizer); }
    }
    
    private static final Selector locationInView$ = Selector.register("locationInView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_getLocation(UIGestureRecognizer __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_getLocationSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/locationInView:">- (CGPoint)locationInView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getLocation(UIView view) {
        if (customClass) { return objc_getLocationSuper(getSuper(), this, locationInView$, view); } else { return objc_getLocation(this, locationInView$, view); }
    }
    
    private static final Selector locationOfTouch$inView$ = Selector.register("locationOfTouch:inView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_getLocation(UIGestureRecognizer __self__, Selector __cmd__, int touchIndex, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_getLocationSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, int touchIndex, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/locationOfTouch:inView:">- (CGPoint)locationOfTouch:(NSUInteger)touchIndex inView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getLocation(int touchIndex, UIView view) {
        if (customClass) { return objc_getLocationSuper(getSuper(), this, locationOfTouch$inView$, touchIndex, view); } else { return objc_getLocation(this, locationOfTouch$inView$, touchIndex, view); }
    }
    
    private static final Selector numberOfTouches = Selector.register("numberOfTouches");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getNumberOfTouches(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getNumberOfTouchesSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/numberOfTouches">- (NSUInteger)numberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getNumberOfTouches() {
        if (customClass) { return objc_getNumberOfTouchesSuper(getSuper(), this, numberOfTouches); } else { return objc_getNumberOfTouches(this, numberOfTouches); }
    }
    
    private static final Selector ignoreTouch$forEvent$ = Selector.register("ignoreTouch:forEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_ignoreTouch(UIGestureRecognizer __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_ignoreTouchSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/ignoreTouch:forEvent:">- (void)ignoreTouch:(UITouch *)touch forEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void ignoreTouch(UITouch touch, UIEvent event) {
        if (customClass) { objc_ignoreTouchSuper(getSuper(), this, ignoreTouch$forEvent$, touch, event); } else { objc_ignoreTouch(this, ignoreTouch$forEvent$, touch, event); }
    }
    
    private static final Selector removeTarget$action$ = Selector.register("removeTarget:action:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeTarget(UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeTargetSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/removeTarget:action:">- (void)removeTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public void removeTarget(NSObject target, Selector action) {
        if (customClass) { objc_removeTargetSuper(getSuper(), this, removeTarget$action$, target, action); } else { objc_removeTarget(this, removeTarget$action$, target, action); }
    }
    
    private static final Selector requireGestureRecognizerToFail$ = Selector.register("requireGestureRecognizerToFail:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_requireGestureRecognizerToFail(UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer otherGestureRecognizer);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_requireGestureRecognizerToFailSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, UIGestureRecognizer otherGestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/requireGestureRecognizerToFail:">- (void)requireGestureRecognizerToFail:(UIGestureRecognizer *)otherGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public void requireGestureRecognizerToFail(UIGestureRecognizer otherGestureRecognizer) {
        if (customClass) { objc_requireGestureRecognizerToFailSuper(getSuper(), this, requireGestureRecognizerToFail$, otherGestureRecognizer); } else { objc_requireGestureRecognizerToFail(this, requireGestureRecognizerToFail$, otherGestureRecognizer); }
    }
    
    private static final Selector reset = Selector.register("reset");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reset(UIGestureRecognizer __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_resetSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/reset">- (void)reset</a>
     * @since Available in iOS 3.2 and later.
     */
    public void reset() {
        if (customClass) { objc_resetSuper(getSuper(), this, reset); } else { objc_reset(this, reset); }
    }
    
    private static final Selector touchesBegan$withEvent$ = Selector.register("touchesBegan:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_touchesBegan(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_touchesBeganSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesBegan:withEvent:">- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesBegan(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesBeganSuper(getSuper(), this, touchesBegan$withEvent$, touches, event); } else { objc_touchesBegan(this, touchesBegan$withEvent$, touches, event); }
    }
    
    private static final Selector touchesCancelled$withEvent$ = Selector.register("touchesCancelled:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_touchesCancelled(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_touchesCancelledSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesCancelled:withEvent:">- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesCancelled(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesCancelledSuper(getSuper(), this, touchesCancelled$withEvent$, touches, event); } else { objc_touchesCancelled(this, touchesCancelled$withEvent$, touches, event); }
    }
    
    private static final Selector touchesEnded$withEvent$ = Selector.register("touchesEnded:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_touchesEnded(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_touchesEndedSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesEnded:withEvent:">- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesEnded(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesEndedSuper(getSuper(), this, touchesEnded$withEvent$, touches, event); } else { objc_touchesEnded(this, touchesEnded$withEvent$, touches, event); }
    }
    
    private static final Selector touchesMoved$withEvent$ = Selector.register("touchesMoved:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_touchesMoved(UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_touchesMovedSuper(ObjCSuper __super__, UIGestureRecognizer __self__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesMoved:withEvent:">- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    public void touchesMoved(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesMovedSuper(getSuper(), this, touchesMoved$withEvent$, touches, event); } else { objc_touchesMoved(this, touchesMoved$withEvent$, touches, event); }
    }
    /*</methods>*/

}
