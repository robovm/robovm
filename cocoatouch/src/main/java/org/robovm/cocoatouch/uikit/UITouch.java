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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html">UITouch Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITouch /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITouch /*</name>*/.class);
    }

    /*<constructors>*/
    public UITouch() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/gestureRecognizers">@property(nonatomic,readonly,copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/phase">@property(nonatomic, readonly) UITouchPhase phase</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("phase") public native @Type("UITouchPhase") UITouchPhase getPhase();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/tapCount">@property(nonatomic, readonly) NSUInteger tapCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tapCount") public native @Type("NSUInteger") int getTapCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/timestamp">@property(nonatomic, readonly) NSTimeInterval timestamp</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("timestamp") public native @Type("NSTimeInterval") double getTimestamp();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/view">@property(nonatomic, readonly, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("view") public native @Type("UIView *") UIView getView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/window">@property(nonatomic, readonly, retain) UIWindow *window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("window") public native @Type("UIWindow *") UIWindow getWindow();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instm/UITouch/locationInView:">- (CGPoint)locationInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("locationInView:") public native @Type("CGPoint") CGPoint getLocation(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instm/UITouch/previousLocationInView:">- (CGPoint)previousLocationInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("previousLocationInView:") public native @Type("CGPoint") CGPoint getPreviousLocation(@Type("UIView *") UIView view);
    /*</methods>*/

}
