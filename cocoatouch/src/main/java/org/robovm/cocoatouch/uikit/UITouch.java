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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html">UITouch Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITouch /*</name>*/ 
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
    
    private static final Selector gestureRecognizers = Selector.register("gestureRecognizers");
    @Bridge private native static NSArray objc_getGestureRecognizers(UITouch __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getGestureRecognizersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/gestureRecognizers">@property(nonatomic,readonly,copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getGestureRecognizers() {
        if (customClass) { return objc_getGestureRecognizersSuper(getSuper(), gestureRecognizers); } else { return objc_getGestureRecognizers(this, gestureRecognizers); }
    }
    
    private static final Selector phase = Selector.register("phase");
    @Bridge private native static UITouchPhase objc_getPhase(UITouch __self__, Selector __cmd__);
    @Bridge private native static UITouchPhase objc_getPhaseSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/phase">@property(nonatomic, readonly) UITouchPhase phase</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITouchPhase getPhase() {
        if (customClass) { return objc_getPhaseSuper(getSuper(), phase); } else { return objc_getPhase(this, phase); }
    }
    
    private static final Selector tapCount = Selector.register("tapCount");
    @Bridge private native static int objc_getTapCount(UITouch __self__, Selector __cmd__);
    @Bridge private native static int objc_getTapCountSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/tapCount">@property(nonatomic, readonly) NSUInteger tapCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getTapCount() {
        if (customClass) { return objc_getTapCountSuper(getSuper(), tapCount); } else { return objc_getTapCount(this, tapCount); }
    }
    
    private static final Selector timestamp = Selector.register("timestamp");
    @Bridge private native static double objc_getTimestamp(UITouch __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimestampSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/timestamp">@property(nonatomic, readonly) NSTimeInterval timestamp</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getTimestamp() {
        if (customClass) { return objc_getTimestampSuper(getSuper(), timestamp); } else { return objc_getTimestamp(this, timestamp); }
    }
    
    private static final Selector view = Selector.register("view");
    @Bridge private native static UIView objc_getView(UITouch __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/view">@property(nonatomic, readonly, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getView() {
        if (customClass) { return objc_getViewSuper(getSuper(), view); } else { return objc_getView(this, view); }
    }
    
    private static final Selector window = Selector.register("window");
    @Bridge private native static UIWindow objc_getWindow(UITouch __self__, Selector __cmd__);
    @Bridge private native static UIWindow objc_getWindowSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instp/UITouch/window">@property(nonatomic, readonly, retain) UIWindow *window</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIWindow getWindow() {
        if (customClass) { return objc_getWindowSuper(getSuper(), window); } else { return objc_getWindow(this, window); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector locationInView$ = Selector.register("locationInView:");
    @Bridge private native static @ByVal CGPoint objc_getLocation(UITouch __self__, Selector __cmd__, UIView view);
    @Bridge private native static @ByVal CGPoint objc_getLocationSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instm/UITouch/locationInView:">- (CGPoint)locationInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint getLocation(UIView view) {
        if (customClass) { return objc_getLocationSuper(getSuper(), locationInView$, view); } else { return objc_getLocation(this, locationInView$, view); }
    }
    
    private static final Selector previousLocationInView$ = Selector.register("previousLocationInView:");
    @Bridge private native static @ByVal CGPoint objc_getPreviousLocation(UITouch __self__, Selector __cmd__, UIView view);
    @Bridge private native static @ByVal CGPoint objc_getPreviousLocationSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITouch_Class/Reference/Reference.html#//apple_ref/occ/instm/UITouch/previousLocationInView:">- (CGPoint)previousLocationInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint getPreviousLocation(UIView view) {
        if (customClass) { return objc_getPreviousLocationSuper(getSuper(), previousLocationInView$, view); } else { return objc_getPreviousLocation(this, previousLocationInView$, view); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("gestureRecognizers") public static NSArray getGestureRecognizers(UITouch __self__, Selector __cmd__) { return __self__.getGestureRecognizers(); }
        @Callback @BindSelector("phase") public static UITouchPhase getPhase(UITouch __self__, Selector __cmd__) { return __self__.getPhase(); }
        @Callback @BindSelector("tapCount") public static int getTapCount(UITouch __self__, Selector __cmd__) { return __self__.getTapCount(); }
        @Callback @BindSelector("timestamp") public static double getTimestamp(UITouch __self__, Selector __cmd__) { return __self__.getTimestamp(); }
        @Callback @BindSelector("view") public static UIView getView(UITouch __self__, Selector __cmd__) { return __self__.getView(); }
        @Callback @BindSelector("window") public static UIWindow getWindow(UITouch __self__, Selector __cmd__) { return __self__.getWindow(); }
        @Callback @BindSelector("locationInView:") public static @ByVal CGPoint getLocation(UITouch __self__, Selector __cmd__, UIView view) { return __self__.getLocation(view); }
        @Callback @BindSelector("previousLocationInView:") public static @ByVal CGPoint getPreviousLocation(UITouch __self__, Selector __cmd__, UIView view) { return __self__.getPreviousLocation(view); }
    }
    /*</callbacks>*/

}
