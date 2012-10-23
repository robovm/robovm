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
import org.robovm.objc.annotation.*;
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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIEvent /*</name>*/.class);

    /*<constructors>*/
    protected UIEvent(SkipInit skipInit) { super(skipInit); }
    public UIEvent() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector subtype = Selector.register("subtype");
    @Bridge(symbol = "objc_msgSend") private native static UIEventSubtype objc_getSubtype(UIEvent __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEventSubtype objc_getSubtypeSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instp/UIEvent/subtype">@property(readonly) UIEventSubtype subtype</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIEventSubtype getSubtype() {
        if (customClass) { return objc_getSubtypeSuper(getSuper(), this, subtype); } else { return objc_getSubtype(this, subtype); }
    }
    
    private static final Selector timestamp = Selector.register("timestamp");
    @Bridge(symbol = "objc_msgSend") private native static double objc_getTimestamp(UIEvent __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static double objc_getTimestampSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instp/UIEvent/timestamp">@property(nonatomic, readonly) NSTimeInterval timestamp</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getTimestamp() {
        if (customClass) { return objc_getTimestampSuper(getSuper(), this, timestamp); } else { return objc_getTimestamp(this, timestamp); }
    }
    
    private static final Selector type = Selector.register("type");
    @Bridge(symbol = "objc_msgSend") private native static UIEventType objc_getType(UIEvent __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEventType objc_getTypeSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instp/UIEvent/type">@property(readonly) UIEventType type</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIEventType getType() {
        if (customClass) { return objc_getTypeSuper(getSuper(), this, type); } else { return objc_getType(this, type); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector allTouches = Selector.register("allTouches");
    @Bridge(symbol = "objc_msgSend") private native static NSSet objc_getAllTouches(UIEvent __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSSet objc_getAllTouchesSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/allTouches">- (NSSet *)allTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSSet getAllTouches() {
        if (customClass) { return objc_getAllTouchesSuper(getSuper(), this, allTouches); } else { return objc_getAllTouches(this, allTouches); }
    }
    
    private static final Selector touchesForView$ = Selector.register("touchesForView:");
    @Bridge(symbol = "objc_msgSend") private native static NSSet objc_getTouches(UIEvent __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSSet objc_getTouchesSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/touchesForView:">- (NSSet *)touchesForView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSSet getTouches(UIView view) {
        if (customClass) { return objc_getTouchesSuper(getSuper(), this, touchesForView$, view); } else { return objc_getTouches(this, touchesForView$, view); }
    }
    
    private static final Selector touchesForGestureRecognizer$ = Selector.register("touchesForGestureRecognizer:");
    @Bridge(symbol = "objc_msgSend") private native static NSSet objc_getTouches(UIEvent __self__, Selector __cmd__, UIGestureRecognizer gesture);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSSet objc_getTouchesSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__, UIGestureRecognizer gesture);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/touchesForGestureRecognizer:">- (NSSet *)touchesForGestureRecognizer:(UIGestureRecognizer *)gesture</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSSet getTouches(UIGestureRecognizer gesture) {
        if (customClass) { return objc_getTouchesSuper(getSuper(), this, touchesForGestureRecognizer$, gesture); } else { return objc_getTouches(this, touchesForGestureRecognizer$, gesture); }
    }
    
    private static final Selector touchesForWindow$ = Selector.register("touchesForWindow:");
    @Bridge(symbol = "objc_msgSend") private native static NSSet objc_getTouches(UIEvent __self__, Selector __cmd__, UIWindow window);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSSet objc_getTouchesSuper(ObjCSuper __super__, UIEvent __self__, Selector __cmd__, UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIEvent_Class/Reference/Reference.html#//apple_ref/occ/instm/UIEvent/touchesForWindow:">- (NSSet *)touchesForWindow:(UIWindow *)window</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSSet getTouches(UIWindow window) {
        if (customClass) { return objc_getTouchesSuper(getSuper(), this, touchesForWindow$, window); } else { return objc_getTouches(this, touchesForWindow$, window); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allTouches") public static NSSet getAllTouches(UIEvent __self__, Selector __cmd__) { return __self__.getAllTouches(); }
        @Callback @BindSelector("touchesForView:") public static NSSet getTouches(UIEvent __self__, Selector __cmd__, UIView view) { return __self__.getTouches(view); }
        @Callback @BindSelector("touchesForGestureRecognizer:") public static NSSet getTouches(UIEvent __self__, Selector __cmd__, UIGestureRecognizer gesture) { return __self__.getTouches(gesture); }
        @Callback @BindSelector("touchesForWindow:") public static NSSet getTouches(UIEvent __self__, Selector __cmd__, UIWindow window) { return __self__.getTouches(window); }
    }
    /*</callbacks>*/

}
