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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html">UIEvent Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIEvent /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIEvent /*</name>*/.class);
    }

    /*<constructors>*/
    public UIEvent() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instp/UIEvent/subtype">@property(readonly) UIEventSubtype subtype</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("subtype") public native @Type("UIEventSubtype") UIEventSubtype getSubtype();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instp/UIEvent/timestamp">@property(nonatomic, readonly) NSTimeInterval timestamp</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("timestamp") public native @Type("NSTimeInterval") double getTimestamp();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instp/UIEvent/type">@property(readonly) UIEventType type</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("type") public native @Type("UIEventType") UIEventType getType();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/allTouches">- (NSSet *)allTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("allTouches") public native @Type("NSSet *") NSSet getAllTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/touchesForView:">- (NSSet *)touchesForView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesForView:") public native @Type("NSSet *") NSSet getTouches(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/touchesForGestureRecognizer:">- (NSSet *)touchesForGestureRecognizer:(UIGestureRecognizer *)gesture</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("touchesForGestureRecognizer:") public native @Type("NSSet *") NSSet getTouches(@Type("UIGestureRecognizer *") UIGestureRecognizer gesture);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/touchesForWindow:">- (NSSet *)touchesForWindow:(UIWindow *)window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesForWindow:") public native @Type("NSSet *") NSSet getTouches(@Type("UIWindow *") UIWindow window);
    /*</methods>*/

}
