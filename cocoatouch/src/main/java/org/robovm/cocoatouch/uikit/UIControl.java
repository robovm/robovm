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

    /*<constructors>*/
    public UIControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentHorizontalAlignment">@property(nonatomic) UIControlContentHorizontalAlignment contentHorizontalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentHorizontalAlignment") public native @Type("UIControlContentHorizontalAlignment") UIControlContentHorizontalAlignment getContentHorizontalAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentHorizontalAlignment">@property(nonatomic) UIControlContentHorizontalAlignment contentHorizontalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentHorizontalAlignment:") public native void setContentHorizontalAlignment(@Type("UIControlContentHorizontalAlignment") UIControlContentHorizontalAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentVerticalAlignment">@property(nonatomic) UIControlContentVerticalAlignment contentVerticalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentVerticalAlignment") public native @Type("UIControlContentVerticalAlignment") UIControlContentVerticalAlignment getContentVerticalAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/contentVerticalAlignment">@property(nonatomic) UIControlContentVerticalAlignment contentVerticalAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentVerticalAlignment:") public native void setContentVerticalAlignment(@Type("UIControlContentVerticalAlignment") UIControlContentVerticalAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSelected") public native @Type("BOOL") boolean isSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelected:") public native void setSelected(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/state">@property(nonatomic, readonly) UIControlState state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("state") public native @Type("UIControlState") UIControlState getState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/touchInside">@property(nonatomic, readonly, getter=isTouchInside) BOOL touchInside</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isTouchInside") public native @Type("BOOL") boolean isTouchInside();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIControl/tracking">@property(nonatomic, readonly, getter=isTracking) BOOL tracking</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isTracking") public native @Type("BOOL") boolean isTracking();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/addTarget:action:forControlEvents:">- (void)addTarget:(id)target action:(SEL)action forControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("addTarget:action:forControlEvents:") public native @Type("void") void addTarget(@Type("id") NSObject target, @Type("SEL") Selector action, @Type("UIControlEvents") EnumSet<UIControlEvent> controlEvents);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/beginTrackingWithTouch:withEvent:">- (BOOL)beginTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("beginTrackingWithTouch:withEvent:") public native @Type("BOOL") boolean beginTracking(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/cancelTrackingWithEvent:">- (void)cancelTrackingWithEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cancelTrackingWithEvent:") public native @Type("void") void cancelTracking(@Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/continueTrackingWithTouch:withEvent:">- (BOOL)continueTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("continueTrackingWithTouch:withEvent:") public native @Type("BOOL") boolean continueTracking(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/endTrackingWithTouch:withEvent:">- (void)endTrackingWithTouch:(UITouch *)touch withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("endTrackingWithTouch:withEvent:") public native @Type("void") void endTracking(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/actionsForTarget:forControlEvent:">- (NSArray *)actionsForTarget:(id)target forControlEvent:(UIControlEvents)controlEvent</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionsForTarget:forControlEvent:") public native @Type("NSArray *") NSArray getActions(@Type("id") NSObject target, @Type("UIControlEvents") UIControlEvent controlEvent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/allControlEvents">- (UIControlEvents)allControlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("allControlEvents") public native @Type("UIControlEvents") EnumSet<UIControlEvent> getAllControlEvents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/allTargets">- (NSSet *)allTargets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("allTargets") public native @Type("NSSet *") NSSet getAllTargets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/removeTarget:action:forControlEvents:">- (void)removeTarget:(id)target action:(SEL)action forControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("removeTarget:action:forControlEvents:") public native @Type("void") void removeTarget(@Type("id") NSObject target, @Type("SEL") Selector action, @Type("UIControlEvents") EnumSet<UIControlEvent> controlEvents);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/sendAction:to:forEvent:">- (void)sendAction:(SEL)action to:(id)target forEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sendAction:to:forEvent:") public native @Type("void") void sendAction(@Type("SEL") Selector action, @Type("id") NSObject target, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIControl/sendActionsForControlEvents:">- (void)sendActionsForControlEvents:(UIControlEvents)controlEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sendActionsForControlEvents:") public native @Type("void") void sendControlEventsActions(@Type("UIControlEvents") EnumSet<UIControlEvent> controlEvents);
    /*</methods>*/

}
