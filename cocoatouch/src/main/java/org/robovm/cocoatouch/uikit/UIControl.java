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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html">UIControl Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIControl /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIControl /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIControl /*</name>*/.class);

    private static final Selector handleEvent = Selector.register("handleEvent");
    private static class ListenerWrapper extends NSObject {
        private final Listener listener;
        private final UIControlEvents controlEvent;
        private ListenerWrapper(Listener listener, UIControlEvents controlEvent) {
            this.listener = listener;
            this.controlEvent = controlEvent;
        }
        @Callback @BindSelector("handleEvent") 
        private static void handleEvent(ListenerWrapper wrapper, Selector sel, UIControl control, UIEvent event) {
            Listener listener = wrapper.listener;
            UIControlEvents controlEvent = wrapper.controlEvent;
            if (controlEvent == UIControlEvents.TouchDown) {
                ((OnTouchDownListener) listener).onTouchDown(control, event);
            } else if (controlEvent == UIControlEvents.TouchUpInside) {
                ((OnTouchUpInsideListener) listener).onTouchUpInside(control, event);
            } else if (controlEvent == UIControlEvents.TouchUpOutside) {
                ((OnTouchUpOutsideListener) listener).onTouchUpOutside(control, event);
            }
        }
    }
    public interface Listener {}
    public interface OnTouchDownListener extends Listener {
        void onTouchDown(UIControl control, UIEvent event);
    }
    public interface OnTouchUpInsideListener extends Listener {
        void onTouchUpInside(UIControl control, UIEvent event);
    }
    public interface OnTouchUpOutsideListener extends Listener {
        void onTouchUpOutside(UIControl control, UIEvent event);
    }
    
    public UIControl(CGRect aRect) {
        super(aRect);
    }
    
    public void addOnTouchDownListener(OnTouchDownListener l) {
        addListener(l, UIControlEvents.TouchDown);
    }
    public void addOnTouchUpInsideListener(OnTouchUpInsideListener l) {
        addListener(l, UIControlEvents.TouchUpInside);
    }
    public void addOnTouchUpOutsideListener(OnTouchUpOutsideListener l) {
        addListener(l, UIControlEvents.TouchUpOutside);
    }
    @SuppressWarnings("unchecked")
    private List<ListenerWrapper> getListeners(boolean create) {
        synchronized (UIControl.class) {
            List<ListenerWrapper> listeners = 
                    (List<ListenerWrapper>) getAssociatedObject(UIControl.class.getName() + ".listeners");
            if (listeners == null && create) {
                listeners = new LinkedList<ListenerWrapper>();
                setAssociatedObject(UIControl.class.getName() + ".listeners", listeners);
            }
            return listeners;
        }
    }
    private void addListener(Listener listener, UIControlEvents controlEvent) {
        ListenerWrapper wrapper = new ListenerWrapper(listener, controlEvent);
        List<ListenerWrapper> listeners = getListeners(true);
        synchronized (listeners) {
            listeners.add(wrapper);
        }
        addTarget(wrapper, handleEvent, controlEvent);
    }
    
    public void removeListener(Listener listener) {
        List<ListenerWrapper> listeners = getListeners(false);
        if (listeners == null) {
            return;
        }
        synchronized (listeners) {
            for (Iterator<ListenerWrapper> it = listeners.iterator(); it.hasNext();) {
                ListenerWrapper wrapper = it.next();
                if (wrapper.listener == listener) {
                    removeTarget(wrapper, handleEvent, wrapper.controlEvent);
                    it.remove();
                    break;
                }
            }
        }        
    }
    
    /*<constructors>*/
    protected UIControl(SkipInit skipInit) { super(skipInit); }
    public UIControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector contentHorizontalAlignment = Selector.register("contentHorizontalAlignment");
    @Bridge private native static UIControlContentHorizontalAlignment objc_getContentHorizontalAlignment(UIControl __self__, Selector __cmd__);
    @Bridge private native static UIControlContentHorizontalAlignment objc_getContentHorizontalAlignmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentHorizontalAlignment">@property(nonatomic) UIControlContentHorizontalAlignment contentHorizontalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIControlContentHorizontalAlignment getContentHorizontalAlignment() {
        if (customClass) { return objc_getContentHorizontalAlignmentSuper(getSuper(), contentHorizontalAlignment); } else { return objc_getContentHorizontalAlignment(this, contentHorizontalAlignment); }
    }
    
    private static final Selector setContentHorizontalAlignment$ = Selector.register("setContentHorizontalAlignment:");
    @Bridge private native static void objc_setContentHorizontalAlignment(UIControl __self__, Selector __cmd__, UIControlContentHorizontalAlignment contentHorizontalAlignment);
    @Bridge private native static void objc_setContentHorizontalAlignmentSuper(ObjCSuper __super__, Selector __cmd__, UIControlContentHorizontalAlignment contentHorizontalAlignment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentHorizontalAlignment">@property(nonatomic) UIControlContentHorizontalAlignment contentHorizontalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentHorizontalAlignment(UIControlContentHorizontalAlignment contentHorizontalAlignment) {
        if (customClass) { objc_setContentHorizontalAlignmentSuper(getSuper(), setContentHorizontalAlignment$, contentHorizontalAlignment); } else { objc_setContentHorizontalAlignment(this, setContentHorizontalAlignment$, contentHorizontalAlignment); }
    }
    
    private static final Selector contentVerticalAlignment = Selector.register("contentVerticalAlignment");
    @Bridge private native static UIControlContentVerticalAlignment objc_getContentVerticalAlignment(UIControl __self__, Selector __cmd__);
    @Bridge private native static UIControlContentVerticalAlignment objc_getContentVerticalAlignmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentVerticalAlignment">@property(nonatomic) UIControlContentVerticalAlignment contentVerticalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIControlContentVerticalAlignment getContentVerticalAlignment() {
        if (customClass) { return objc_getContentVerticalAlignmentSuper(getSuper(), contentVerticalAlignment); } else { return objc_getContentVerticalAlignment(this, contentVerticalAlignment); }
    }
    
    private static final Selector setContentVerticalAlignment$ = Selector.register("setContentVerticalAlignment:");
    @Bridge private native static void objc_setContentVerticalAlignment(UIControl __self__, Selector __cmd__, UIControlContentVerticalAlignment contentVerticalAlignment);
    @Bridge private native static void objc_setContentVerticalAlignmentSuper(ObjCSuper __super__, Selector __cmd__, UIControlContentVerticalAlignment contentVerticalAlignment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentVerticalAlignment">@property(nonatomic) UIControlContentVerticalAlignment contentVerticalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentVerticalAlignment(UIControlContentVerticalAlignment contentVerticalAlignment) {
        if (customClass) { objc_setContentVerticalAlignmentSuper(getSuper(), setContentVerticalAlignment$, contentVerticalAlignment); } else { objc_setContentVerticalAlignment(this, setContentVerticalAlignment$, contentVerticalAlignment); }
    }
    
    private static final Selector isEnabled = Selector.register("isEnabled");
    @Bridge private native static boolean objc_isEnabled(UIControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEnabled() {
        if (customClass) { return objc_isEnabledSuper(getSuper(), isEnabled); } else { return objc_isEnabled(this, isEnabled); }
    }
    
    private static final Selector setEnabled$ = Selector.register("setEnabled:");
    @Bridge private native static void objc_setEnabled(UIControl __self__, Selector __cmd__, boolean enabled);
    @Bridge private native static void objc_setEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean enabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEnabled(boolean enabled) {
        if (customClass) { objc_setEnabledSuper(getSuper(), setEnabled$, enabled); } else { objc_setEnabled(this, setEnabled$, enabled); }
    }
    
    private static final Selector isHighlighted = Selector.register("isHighlighted");
    @Bridge private native static boolean objc_isHighlighted(UIControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHighlightedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHighlighted() {
        if (customClass) { return objc_isHighlightedSuper(getSuper(), isHighlighted); } else { return objc_isHighlighted(this, isHighlighted); }
    }
    
    private static final Selector setHighlighted$ = Selector.register("setHighlighted:");
    @Bridge private native static void objc_setHighlighted(UIControl __self__, Selector __cmd__, boolean highlighted);
    @Bridge private native static void objc_setHighlightedSuper(ObjCSuper __super__, Selector __cmd__, boolean highlighted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHighlighted(boolean highlighted) {
        if (customClass) { objc_setHighlightedSuper(getSuper(), setHighlighted$, highlighted); } else { objc_setHighlighted(this, setHighlighted$, highlighted); }
    }
    
    private static final Selector isSelected = Selector.register("isSelected");
    @Bridge private native static boolean objc_isSelected(UIControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isSelectedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isSelected() {
        if (customClass) { return objc_isSelectedSuper(getSuper(), isSelected); } else { return objc_isSelected(this, isSelected); }
    }
    
    private static final Selector setSelected$ = Selector.register("setSelected:");
    @Bridge private native static void objc_setSelected(UIControl __self__, Selector __cmd__, boolean selected);
    @Bridge private native static void objc_setSelectedSuper(ObjCSuper __super__, Selector __cmd__, boolean selected);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelected(boolean selected) {
        if (customClass) { objc_setSelectedSuper(getSuper(), setSelected$, selected); } else { objc_setSelected(this, setSelected$, selected); }
    }
    
    private static final Selector state = Selector.register("state");
    @Bridge private native static UIControlState objc_getState(UIControl __self__, Selector __cmd__);
    @Bridge private native static UIControlState objc_getStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/state">@property(nonatomic, readonly) UIControlState state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIControlState getState() {
        if (customClass) { return objc_getStateSuper(getSuper(), state); } else { return objc_getState(this, state); }
    }
    
    private static final Selector isTouchInside = Selector.register("isTouchInside");
    @Bridge private native static boolean objc_isTouchInside(UIControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTouchInsideSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/touchInside">@property(nonatomic, readonly, getter=isTouchInside) BOOL touchInside</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isTouchInside() {
        if (customClass) { return objc_isTouchInsideSuper(getSuper(), isTouchInside); } else { return objc_isTouchInside(this, isTouchInside); }
    }
    
    private static final Selector isTracking = Selector.register("isTracking");
    @Bridge private native static boolean objc_isTracking(UIControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTrackingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/tracking">@property(nonatomic, readonly, getter=isTracking) BOOL tracking</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isTracking() {
        if (customClass) { return objc_isTrackingSuper(getSuper(), isTracking); } else { return objc_isTracking(this, isTracking); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addTarget$action$forControlEvents$ = Selector.register("addTarget:action:forControlEvents:");
    @Bridge private native static void objc_addTarget(UIControl __self__, Selector __cmd__, NSObject target, Selector action, UIControlEvents controlEvents);
    @Bridge private native static void objc_addTargetSuper(ObjCSuper __super__, Selector __cmd__, NSObject target, Selector action, UIControlEvents controlEvents);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/addTarget:action:forControlEvents:">- (void)addTarget:(id)target action:(SEL)action forControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void addTarget(NSObject target, Selector action, UIControlEvents controlEvents) {
        if (customClass) { objc_addTargetSuper(getSuper(), addTarget$action$forControlEvents$, target, action, controlEvents); } else { objc_addTarget(this, addTarget$action$forControlEvents$, target, action, controlEvents); }
    }
    
    private static final Selector beginTrackingWithTouch$withEvent$ = Selector.register("beginTrackingWithTouch:withEvent:");
    @Bridge private native static boolean objc_beginTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge private native static boolean objc_beginTrackingSuper(ObjCSuper __super__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/beginTrackingWithTouch:withEvent:">- (BOOL)beginTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean beginTracking(UITouch touch, UIEvent event) {
        if (customClass) { return objc_beginTrackingSuper(getSuper(), beginTrackingWithTouch$withEvent$, touch, event); } else { return objc_beginTracking(this, beginTrackingWithTouch$withEvent$, touch, event); }
    }
    
    private static final Selector cancelTrackingWithEvent$ = Selector.register("cancelTrackingWithEvent:");
    @Bridge private native static void objc_cancelTracking(UIControl __self__, Selector __cmd__, UIEvent event);
    @Bridge private native static void objc_cancelTrackingSuper(ObjCSuper __super__, Selector __cmd__, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/cancelTrackingWithEvent:">- (void)cancelTrackingWithEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void cancelTracking(UIEvent event) {
        if (customClass) { objc_cancelTrackingSuper(getSuper(), cancelTrackingWithEvent$, event); } else { objc_cancelTracking(this, cancelTrackingWithEvent$, event); }
    }
    
    private static final Selector continueTrackingWithTouch$withEvent$ = Selector.register("continueTrackingWithTouch:withEvent:");
    @Bridge private native static boolean objc_continueTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge private native static boolean objc_continueTrackingSuper(ObjCSuper __super__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/continueTrackingWithTouch:withEvent:">- (BOOL)continueTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean continueTracking(UITouch touch, UIEvent event) {
        if (customClass) { return objc_continueTrackingSuper(getSuper(), continueTrackingWithTouch$withEvent$, touch, event); } else { return objc_continueTracking(this, continueTrackingWithTouch$withEvent$, touch, event); }
    }
    
    private static final Selector endTrackingWithTouch$withEvent$ = Selector.register("endTrackingWithTouch:withEvent:");
    @Bridge private native static void objc_endTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event);
    @Bridge private native static void objc_endTrackingSuper(ObjCSuper __super__, Selector __cmd__, UITouch touch, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/endTrackingWithTouch:withEvent:">- (void)endTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endTracking(UITouch touch, UIEvent event) {
        if (customClass) { objc_endTrackingSuper(getSuper(), endTrackingWithTouch$withEvent$, touch, event); } else { objc_endTracking(this, endTrackingWithTouch$withEvent$, touch, event); }
    }
    
    private static final Selector actionsForTarget$forControlEvent$ = Selector.register("actionsForTarget:forControlEvent:");
    @Bridge private native static NSArray objc_getActions(UIControl __self__, Selector __cmd__, NSObject target, UIControlEvents controlEvent);
    @Bridge private native static NSArray objc_getActionsSuper(ObjCSuper __super__, Selector __cmd__, NSObject target, UIControlEvents controlEvent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/actionsForTarget:forControlEvent:">- (NSArray *)actionsForTarget:(id)target forControlEvent:(UIControlEvents)controlEvent</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getActions(NSObject target, UIControlEvents controlEvent) {
        if (customClass) { return objc_getActionsSuper(getSuper(), actionsForTarget$forControlEvent$, target, controlEvent); } else { return objc_getActions(this, actionsForTarget$forControlEvent$, target, controlEvent); }
    }
    
    private static final Selector allControlEvents = Selector.register("allControlEvents");
    @Bridge private native static UIControlEvents objc_getAllControlEvents(UIControl __self__, Selector __cmd__);
    @Bridge private native static UIControlEvents objc_getAllControlEventsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/allControlEvents">- (UIControlEvents)allControlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIControlEvents getAllControlEvents() {
        if (customClass) { return objc_getAllControlEventsSuper(getSuper(), allControlEvents); } else { return objc_getAllControlEvents(this, allControlEvents); }
    }
    
    private static final Selector allTargets = Selector.register("allTargets");
    @Bridge private native static NSSet objc_getAllTargets(UIControl __self__, Selector __cmd__);
    @Bridge private native static NSSet objc_getAllTargetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/allTargets">- (NSSet *)allTargets</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSSet getAllTargets() {
        if (customClass) { return objc_getAllTargetsSuper(getSuper(), allTargets); } else { return objc_getAllTargets(this, allTargets); }
    }
    
    private static final Selector removeTarget$action$forControlEvents$ = Selector.register("removeTarget:action:forControlEvents:");
    @Bridge private native static void objc_removeTarget(UIControl __self__, Selector __cmd__, NSObject target, Selector action, UIControlEvents controlEvents);
    @Bridge private native static void objc_removeTargetSuper(ObjCSuper __super__, Selector __cmd__, NSObject target, Selector action, UIControlEvents controlEvents);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/removeTarget:action:forControlEvents:">- (void)removeTarget:(id)target action:(SEL)action forControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeTarget(NSObject target, Selector action, UIControlEvents controlEvents) {
        if (customClass) { objc_removeTargetSuper(getSuper(), removeTarget$action$forControlEvents$, target, action, controlEvents); } else { objc_removeTarget(this, removeTarget$action$forControlEvents$, target, action, controlEvents); }
    }
    
    private static final Selector sendAction$to$forEvent$ = Selector.register("sendAction:to:forEvent:");
    @Bridge private native static void objc_sendAction(UIControl __self__, Selector __cmd__, Selector action, NSObject target, UIEvent event);
    @Bridge private native static void objc_sendActionSuper(ObjCSuper __super__, Selector __cmd__, Selector action, NSObject target, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/sendAction:to:forEvent:">- (void)sendAction:(SEL)action to:(id)target forEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendAction(Selector action, NSObject target, UIEvent event) {
        if (customClass) { objc_sendActionSuper(getSuper(), sendAction$to$forEvent$, action, target, event); } else { objc_sendAction(this, sendAction$to$forEvent$, action, target, event); }
    }
    
    private static final Selector sendActionsForControlEvents$ = Selector.register("sendActionsForControlEvents:");
    @Bridge private native static void objc_sendControlEventsActions(UIControl __self__, Selector __cmd__, UIControlEvents controlEvents);
    @Bridge private native static void objc_sendControlEventsActionsSuper(ObjCSuper __super__, Selector __cmd__, UIControlEvents controlEvents);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/sendActionsForControlEvents:">- (void)sendActionsForControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendControlEventsActions(UIControlEvents controlEvents) {
        if (customClass) { objc_sendControlEventsActionsSuper(getSuper(), sendActionsForControlEvents$, controlEvents); } else { objc_sendControlEventsActions(this, sendActionsForControlEvents$, controlEvents); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("contentHorizontalAlignment") public static UIControlContentHorizontalAlignment getContentHorizontalAlignment(UIControl __self__, Selector __cmd__) { return __self__.getContentHorizontalAlignment(); }
        @Callback @BindSelector("setContentHorizontalAlignment:") public static void setContentHorizontalAlignment(UIControl __self__, Selector __cmd__, UIControlContentHorizontalAlignment contentHorizontalAlignment) { __self__.setContentHorizontalAlignment(contentHorizontalAlignment); }
        @Callback @BindSelector("contentVerticalAlignment") public static UIControlContentVerticalAlignment getContentVerticalAlignment(UIControl __self__, Selector __cmd__) { return __self__.getContentVerticalAlignment(); }
        @Callback @BindSelector("setContentVerticalAlignment:") public static void setContentVerticalAlignment(UIControl __self__, Selector __cmd__, UIControlContentVerticalAlignment contentVerticalAlignment) { __self__.setContentVerticalAlignment(contentVerticalAlignment); }
        @Callback @BindSelector("isEnabled") public static boolean isEnabled(UIControl __self__, Selector __cmd__) { return __self__.isEnabled(); }
        @Callback @BindSelector("setEnabled:") public static void setEnabled(UIControl __self__, Selector __cmd__, boolean enabled) { __self__.setEnabled(enabled); }
        @Callback @BindSelector("isHighlighted") public static boolean isHighlighted(UIControl __self__, Selector __cmd__) { return __self__.isHighlighted(); }
        @Callback @BindSelector("setHighlighted:") public static void setHighlighted(UIControl __self__, Selector __cmd__, boolean highlighted) { __self__.setHighlighted(highlighted); }
        @Callback @BindSelector("isSelected") public static boolean isSelected(UIControl __self__, Selector __cmd__) { return __self__.isSelected(); }
        @Callback @BindSelector("setSelected:") public static void setSelected(UIControl __self__, Selector __cmd__, boolean selected) { __self__.setSelected(selected); }
        @Callback @BindSelector("state") public static UIControlState getState(UIControl __self__, Selector __cmd__) { return __self__.getState(); }
        @Callback @BindSelector("isTouchInside") public static boolean isTouchInside(UIControl __self__, Selector __cmd__) { return __self__.isTouchInside(); }
        @Callback @BindSelector("isTracking") public static boolean isTracking(UIControl __self__, Selector __cmd__) { return __self__.isTracking(); }
        @Callback @BindSelector("addTarget:action:forControlEvents:") public static void addTarget(UIControl __self__, Selector __cmd__, NSObject target, Selector action, UIControlEvents controlEvents) { __self__.addTarget(target, action, controlEvents); }
        @Callback @BindSelector("beginTrackingWithTouch:withEvent:") public static boolean beginTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event) { return __self__.beginTracking(touch, event); }
        @Callback @BindSelector("cancelTrackingWithEvent:") public static void cancelTracking(UIControl __self__, Selector __cmd__, UIEvent event) { __self__.cancelTracking(event); }
        @Callback @BindSelector("continueTrackingWithTouch:withEvent:") public static boolean continueTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event) { return __self__.continueTracking(touch, event); }
        @Callback @BindSelector("endTrackingWithTouch:withEvent:") public static void endTracking(UIControl __self__, Selector __cmd__, UITouch touch, UIEvent event) { __self__.endTracking(touch, event); }
        @Callback @BindSelector("actionsForTarget:forControlEvent:") public static NSArray getActions(UIControl __self__, Selector __cmd__, NSObject target, UIControlEvents controlEvent) { return __self__.getActions(target, controlEvent); }
        @Callback @BindSelector("allControlEvents") public static UIControlEvents getAllControlEvents(UIControl __self__, Selector __cmd__) { return __self__.getAllControlEvents(); }
        @Callback @BindSelector("allTargets") public static NSSet getAllTargets(UIControl __self__, Selector __cmd__) { return __self__.getAllTargets(); }
        @Callback @BindSelector("removeTarget:action:forControlEvents:") public static void removeTarget(UIControl __self__, Selector __cmd__, NSObject target, Selector action, UIControlEvents controlEvents) { __self__.removeTarget(target, action, controlEvents); }
        @Callback @BindSelector("sendAction:to:forEvent:") public static void sendAction(UIControl __self__, Selector __cmd__, Selector action, NSObject target, UIEvent event) { __self__.sendAction(action, target, event); }
        @Callback @BindSelector("sendActionsForControlEvents:") public static void sendControlEventsActions(UIControl __self__, Selector __cmd__, UIControlEvents controlEvents) { __self__.sendControlEventsActions(controlEvents); }
    }
    /*</callbacks>*/

}
