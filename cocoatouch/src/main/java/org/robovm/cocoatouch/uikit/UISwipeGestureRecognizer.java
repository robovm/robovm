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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwipeGestureRecognizer_Class/Reference/Reference.html">UISwipeGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISwipeGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISwipeGestureRecognizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISwipeGestureRecognizer /*</name>*/.class);

    /*<constructors>*/
    protected UISwipeGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UISwipeGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector direction = Selector.register("direction");
    @Bridge private native static UISwipeGestureRecognizerDirection objc_getDirection(UISwipeGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static UISwipeGestureRecognizerDirection objc_getDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwipeGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwipeGestureRecognizer/direction">@property(nonatomic) UISwipeGestureRecognizerDirection direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UISwipeGestureRecognizerDirection getDirection() {
        if (customClass) { return objc_getDirectionSuper(getSuper(), direction); } else { return objc_getDirection(this, direction); }
    }
    
    private static final Selector setDirection$ = Selector.register("setDirection:");
    @Bridge private native static void objc_setDirection(UISwipeGestureRecognizer __self__, Selector __cmd__, UISwipeGestureRecognizerDirection direction);
    @Bridge private native static void objc_setDirectionSuper(ObjCSuper __super__, Selector __cmd__, UISwipeGestureRecognizerDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwipeGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwipeGestureRecognizer/direction">@property(nonatomic) UISwipeGestureRecognizerDirection direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDirection(UISwipeGestureRecognizerDirection direction) {
        if (customClass) { objc_setDirectionSuper(getSuper(), setDirection$, direction); } else { objc_setDirection(this, setDirection$, direction); }
    }
    
    private static final Selector numberOfTouchesRequired = Selector.register("numberOfTouchesRequired");
    @Bridge private native static int objc_getNumberOfTouchesRequired(UISwipeGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfTouchesRequiredSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwipeGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwipeGestureRecognizer/numberOfTouchesRequired">@property(nonatomic) NSUInteger numberOfTouchesRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getNumberOfTouchesRequired() {
        if (customClass) { return objc_getNumberOfTouchesRequiredSuper(getSuper(), numberOfTouchesRequired); } else { return objc_getNumberOfTouchesRequired(this, numberOfTouchesRequired); }
    }
    
    private static final Selector setNumberOfTouchesRequired$ = Selector.register("setNumberOfTouchesRequired:");
    @Bridge private native static void objc_setNumberOfTouchesRequired(UISwipeGestureRecognizer __self__, Selector __cmd__, int numberOfTouchesRequired);
    @Bridge private native static void objc_setNumberOfTouchesRequiredSuper(ObjCSuper __super__, Selector __cmd__, int numberOfTouchesRequired);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwipeGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwipeGestureRecognizer/numberOfTouchesRequired">@property(nonatomic) NSUInteger numberOfTouchesRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setNumberOfTouchesRequired(int numberOfTouchesRequired) {
        if (customClass) { objc_setNumberOfTouchesRequiredSuper(getSuper(), setNumberOfTouchesRequired$, numberOfTouchesRequired); } else { objc_setNumberOfTouchesRequired(this, setNumberOfTouchesRequired$, numberOfTouchesRequired); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("direction") public static UISwipeGestureRecognizerDirection getDirection(UISwipeGestureRecognizer __self__, Selector __cmd__) { return __self__.getDirection(); }
        @Callback @BindSelector("setDirection:") public static void setDirection(UISwipeGestureRecognizer __self__, Selector __cmd__, UISwipeGestureRecognizerDirection direction) { __self__.setDirection(direction); }
        @Callback @BindSelector("numberOfTouchesRequired") public static int getNumberOfTouchesRequired(UISwipeGestureRecognizer __self__, Selector __cmd__) { return __self__.getNumberOfTouchesRequired(); }
        @Callback @BindSelector("setNumberOfTouchesRequired:") public static void setNumberOfTouchesRequired(UISwipeGestureRecognizer __self__, Selector __cmd__, int numberOfTouchesRequired) { __self__.setNumberOfTouchesRequired(numberOfTouchesRequired); }
    }
    /*</callbacks>*/

}
