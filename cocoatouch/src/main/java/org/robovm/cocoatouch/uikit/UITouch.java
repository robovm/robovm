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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITouch /*</name>*/.class);

    /*<constructors>*/
    protected UITouch(SkipInit skipInit) { super(skipInit); }
    public UITouch() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/gestureRecognizers">@property(nonatomic,readonly,copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("gestureRecognizers") public native NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/phase">@property(nonatomic, readonly) UITouchPhase phase</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("phase") public native UITouchPhase getPhase();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/tapCount">@property(nonatomic, readonly) NSUInteger tapCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tapCount") public native int getTapCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/timestamp">@property(nonatomic, readonly) NSTimeInterval timestamp</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("timestamp") public native double getTimestamp();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/view">@property(nonatomic, readonly, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("view") public native UIView getView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/window">@property(nonatomic, readonly, retain) UIWindow *window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("window") public native UIWindow getWindow();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector locationInView$ = Selector.register("locationInView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_getLocation(UITouch __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_getLocationSuper(ObjCSuper __super__, UITouch __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instm/UITouch/locationInView:">- (CGPoint)locationInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint getLocation(UIView view) {
        if (customClass) { return objc_getLocationSuper(getSuper(), this, locationInView$, view); } else { return objc_getLocation(this, locationInView$, view); }
    }
    
    private static final Selector previousLocationInView$ = Selector.register("previousLocationInView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_getPreviousLocation(UITouch __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_getPreviousLocationSuper(ObjCSuper __super__, UITouch __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instm/UITouch/previousLocationInView:">- (CGPoint)previousLocationInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint getPreviousLocation(UIView view) {
        if (customClass) { return objc_getPreviousLocationSuper(getSuper(), this, previousLocationInView$, view); } else { return objc_getPreviousLocation(this, previousLocationInView$, view); }
    }
    /*</methods>*/

}
