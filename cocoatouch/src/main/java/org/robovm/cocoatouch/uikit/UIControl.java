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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html">UIControl Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIControl /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIControl /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIControl /*</name>*/.class);

    /*<constructors>*/
    protected UIControl(SkipInit skipInit) { super(skipInit); }
    public UIControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentHorizontalAlignment">@property(nonatomic) UIControlContentHorizontalAlignment contentHorizontalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentHorizontalAlignment") public native UIControlContentHorizontalAlignment getContentHorizontalAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentHorizontalAlignment">@property(nonatomic) UIControlContentHorizontalAlignment contentHorizontalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentHorizontalAlignment:") public native void setContentHorizontalAlignment(UIControlContentHorizontalAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentVerticalAlignment">@property(nonatomic) UIControlContentVerticalAlignment contentVerticalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentVerticalAlignment") public native UIControlContentVerticalAlignment getContentVerticalAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentVerticalAlignment">@property(nonatomic) UIControlContentVerticalAlignment contentVerticalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentVerticalAlignment:") public native void setContentVerticalAlignment(UIControlContentVerticalAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabled") public native boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isHighlighted") public native boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSelected") public native boolean isSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelected:") public native void setSelected(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/state">@property(nonatomic, readonly) UIControlState state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("state") public native UIControlState getState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/touchInside">@property(nonatomic, readonly, getter=isTouchInside) BOOL touchInside</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isTouchInside") public native boolean isTouchInside();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/tracking">@property(nonatomic, readonly, getter=isTracking) BOOL tracking</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isTracking") public native boolean isTracking();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addTarget$action$forControlEvents$ = Selector.register("addTarget:action:forControlEvents:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addTarget(UIControl __self__, Selector __cmd__, NSObject target, Selector action, EnumSet<UIControlEvent> controlEvents);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addTargetSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, NSObject target, Selector action, EnumSet<UIControlEvent> controlEvents);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/addTarget:action:forControlEvents:">- (void)addTarget:(id)target action:(SEL)action forControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void addTarget(NSObject target, Selector action, EnumSet<UIControlEvent> controlEvents) {
        if (customClass) { objc_addTargetSuper(getSuper(), this, addTarget$action$forControlEvents$, target, action, controlEvents); } else { objc_addTarget(this, addTarget$action$forControlEvents$, target, action, controlEvents); }
    }
    
    private static final Selector beginTrackingWithTouch$withEvent$ = Selector.register("beginTrackingWithTouch:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_beginTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_beginTrackingSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/beginTrackingWithTouch:withEvent:">- (BOOL)beginTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean beginTracking(UITouch touch, UIEvent event) {
        if (customClass) { return objc_beginTrackingSuper(getSuper(), this, beginTrackingWithTouch$withEvent$, touch, event); } else { return objc_beginTracking(this, beginTrackingWithTouch$withEvent$, touch, event); }
    }
    
    private static final Selector cancelTrackingWithEvent$ = Selector.register("cancelTrackingWithEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_cancelTracking(UIControl __self__, Selector __cmd__, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_cancelTrackingSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/cancelTrackingWithEvent:">- (void)cancelTrackingWithEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void cancelTracking(UIEvent event) {
        if (customClass) { objc_cancelTrackingSuper(getSuper(), this, cancelTrackingWithEvent$, event); } else { objc_cancelTracking(this, cancelTrackingWithEvent$, event); }
    }
    
    private static final Selector continueTrackingWithTouch$withEvent$ = Selector.register("continueTrackingWithTouch:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_continueTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_continueTrackingSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/continueTrackingWithTouch:withEvent:">- (BOOL)continueTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean continueTracking(UITouch touch, UIEvent event) {
        if (customClass) { return objc_continueTrackingSuper(getSuper(), this, continueTrackingWithTouch$withEvent$, touch, event); } else { return objc_continueTracking(this, continueTrackingWithTouch$withEvent$, touch, event); }
    }
    
    private static final Selector endTrackingWithTouch$withEvent$ = Selector.register("endTrackingWithTouch:withEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_endTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_endTrackingSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/endTrackingWithTouch:withEvent:">- (void)endTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endTracking(UITouch touch, UIEvent event) {
        if (customClass) { objc_endTrackingSuper(getSuper(), this, endTrackingWithTouch$withEvent$, touch, event); } else { objc_endTracking(this, endTrackingWithTouch$withEvent$, touch, event); }
    }
    
    private static final Selector actionsForTarget$forControlEvent$ = Selector.register("actionsForTarget:forControlEvent:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getActions(UIControl __self__, Selector __cmd__, NSObject target, UIControlEvent controlEvent);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getActionsSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, NSObject target, UIControlEvent controlEvent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/actionsForTarget:forControlEvent:">- (NSArray *)actionsForTarget:(id)target forControlEvent:(UIControlEvents)controlEvent</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getActions(NSObject target, UIControlEvent controlEvent) {
        if (customClass) { return objc_getActionsSuper(getSuper(), this, actionsForTarget$forControlEvent$, target, controlEvent); } else { return objc_getActions(this, actionsForTarget$forControlEvent$, target, controlEvent); }
    }
    
    private static final Selector allControlEvents = Selector.register("allControlEvents");
    @Bridge(symbol = "objc_msgSend") private native static EnumSet<UIControlEvent> objc_getAllControlEvents(UIControl __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static EnumSet<UIControlEvent> objc_getAllControlEventsSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/allControlEvents">- (UIControlEvents)allControlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public EnumSet<UIControlEvent> getAllControlEvents() {
        if (customClass) { return objc_getAllControlEventsSuper(getSuper(), this, allControlEvents); } else { return objc_getAllControlEvents(this, allControlEvents); }
    }
    
    private static final Selector allTargets = Selector.register("allTargets");
    @Bridge(symbol = "objc_msgSend") private native static NSSet objc_getAllTargets(UIControl __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSSet objc_getAllTargetsSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/allTargets">- (NSSet *)allTargets</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSSet getAllTargets() {
        if (customClass) { return objc_getAllTargetsSuper(getSuper(), this, allTargets); } else { return objc_getAllTargets(this, allTargets); }
    }
    
    private static final Selector removeTarget$action$forControlEvents$ = Selector.register("removeTarget:action:forControlEvents:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeTarget(UIControl __self__, Selector __cmd__, NSObject target, Selector action, EnumSet<UIControlEvent> controlEvents);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeTargetSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, NSObject target, Selector action, EnumSet<UIControlEvent> controlEvents);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/removeTarget:action:forControlEvents:">- (void)removeTarget:(id)target action:(SEL)action forControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeTarget(NSObject target, Selector action, EnumSet<UIControlEvent> controlEvents) {
        if (customClass) { objc_removeTargetSuper(getSuper(), this, removeTarget$action$forControlEvents$, target, action, controlEvents); } else { objc_removeTarget(this, removeTarget$action$forControlEvents$, target, action, controlEvents); }
    }
    
    private static final Selector sendAction$to$forEvent$ = Selector.register("sendAction:to:forEvent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_sendAction(UIControl __self__, Selector __cmd__, Selector action, NSObject target, UIEvent event);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_sendActionSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, Selector action, NSObject target, UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/sendAction:to:forEvent:">- (void)sendAction:(SEL)action to:(id)target forEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendAction(Selector action, NSObject target, UIEvent event) {
        if (customClass) { objc_sendActionSuper(getSuper(), this, sendAction$to$forEvent$, action, target, event); } else { objc_sendAction(this, sendAction$to$forEvent$, action, target, event); }
    }
    
    private static final Selector sendActionsForControlEvents$ = Selector.register("sendActionsForControlEvents:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_sendControlEventsActions(UIControl __self__, Selector __cmd__, EnumSet<UIControlEvent> controlEvents);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_sendControlEventsActionsSuper(ObjCSuper __super__, UIControl __self__, Selector __cmd__, EnumSet<UIControlEvent> controlEvents);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/sendActionsForControlEvents:">- (void)sendActionsForControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendControlEventsActions(EnumSet<UIControlEvent> controlEvents) {
        if (customClass) { objc_sendControlEventsActionsSuper(getSuper(), this, sendActionsForControlEvents$, controlEvents); } else { objc_sendControlEventsActions(this, sendActionsForControlEvents$, controlEvents); }
    }
    /*</methods>*/

}
