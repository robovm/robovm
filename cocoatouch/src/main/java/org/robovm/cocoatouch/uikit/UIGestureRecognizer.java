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

    /*<constructors>*/
    public UIGestureRecognizer() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/initWithTarget:action:">- (id)initWithTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("initWithTarget:action:") public UIGestureRecognizer(@Type("id") NSObject target, @Type("SEL") Selector action) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/cancelsTouchesInView">@property(nonatomic) BOOL cancelsTouchesInView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("cancelsTouchesInView") public native @Type("BOOL") boolean isCancelsTouchesInView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/cancelsTouchesInView">@property(nonatomic) BOOL cancelsTouchesInView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setCancelsTouchesInView:") public native void setCancelsTouchesInView(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesBegan">@property(nonatomic) BOOL delaysTouchesBegan</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delaysTouchesBegan") public native @Type("BOOL") boolean isDelaysTouchesBegan();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesBegan">@property(nonatomic) BOOL delaysTouchesBegan</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelaysTouchesBegan:") public native void setDelaysTouchesBegan(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesEnded">@property(nonatomic) BOOL delaysTouchesEnded</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delaysTouchesEnded") public native @Type("BOOL") boolean isDelaysTouchesEnded();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delaysTouchesEnded">@property(nonatomic) BOOL delaysTouchesEnded</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelaysTouchesEnded:") public native void setDelaysTouchesEnded(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delegate">@property(nonatomic, assign) id&amp;lt;UIGestureRecognizerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native @Type("id<UIGestureRecognizerDelegate>") UIGestureRecognizerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/delegate">@property(nonatomic, assign) id&amp;lt;UIGestureRecognizerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIGestureRecognizerDelegate>") UIGestureRecognizerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/state">@property(nonatomic, readonly) UIGestureRecognizerState state</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("state") public native @Type("UIGestureRecognizerState") UIGestureRecognizerState getState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIGestureRecognizer/view">@property(nonatomic, readonly) UIView *view</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("view") public native @Type("UIView *") UIView getView();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/addTarget:action:">- (void)addTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("addTarget:action:") public native @Type("void") void addTarget(@Type("id") NSObject target, @Type("SEL") Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/canBePreventedByGestureRecognizer:">- (BOOL)canBePreventedByGestureRecognizer:(UIGestureRecognizer *)preventingGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("canBePreventedByGestureRecognizer:") public native @Type("BOOL") boolean canBePreventedByGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer preventingGestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/canPreventGestureRecognizer:">- (BOOL)canPreventGestureRecognizer:(UIGestureRecognizer *)preventedGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("canPreventGestureRecognizer:") public native @Type("BOOL") boolean canPreventGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer preventedGestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/locationInView:">- (CGPoint)locationInView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("locationInView:") public native @Type("CGPoint") CGPoint getLocation(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/locationOfTouch:inView:">- (CGPoint)locationOfTouch:(NSUInteger)touchIndex inView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("locationOfTouch:inView:") public native @Type("CGPoint") CGPoint getLocation(@Type("NSUInteger") int touchIndex, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/numberOfTouches">- (NSUInteger)numberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("numberOfTouches") public native @Type("NSUInteger") int getNumberOfTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/ignoreTouch:forEvent:">- (void)ignoreTouch:(UITouch *)touch forEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("ignoreTouch:forEvent:") public native @Type("void") void ignoreTouch(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/removeTarget:action:">- (void)removeTarget:(id)target action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("removeTarget:action:") public native @Type("void") void removeTarget(@Type("id") NSObject target, @Type("SEL") Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/requireGestureRecognizerToFail:">- (void)requireGestureRecognizerToFail:(UIGestureRecognizer *)otherGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("requireGestureRecognizerToFail:") public native @Type("void") void requireGestureRecognizerToFail(@Type("UIGestureRecognizer *") UIGestureRecognizer otherGestureRecognizer);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/reset">- (void)reset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("reset") public native @Type("void") void reset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesBegan:withEvent:">- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("touchesBegan:withEvent:") public native @Type("void") void touchesBegan(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesCancelled:withEvent:">- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("touchesCancelled:withEvent:") public native @Type("void") void touchesCancelled(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesEnded:withEvent:">- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("touchesEnded:withEvent:") public native @Type("void") void touchesEnded(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIGestureRecognizer/touchesMoved:withEvent:">- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("touchesMoved:withEvent:") public native @Type("void") void touchesMoved(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /*</methods>*/

}
