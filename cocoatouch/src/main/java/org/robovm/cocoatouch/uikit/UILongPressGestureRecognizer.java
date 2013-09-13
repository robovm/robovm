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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html">UILongPressGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UILongPressGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILongPressGestureRecognizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UILongPressGestureRecognizer /*</name>*/.class);

    /*<constructors>*/
    protected UILongPressGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UILongPressGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector allowableMovement = Selector.register("allowableMovement");
    @Bridge private native static float objc_getAllowableMovement(UILongPressGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static float objc_getAllowableMovementSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/allowableMovement">@property(nonatomic) CGFloat allowableMovement</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getAllowableMovement() {
        if (customClass) { return objc_getAllowableMovementSuper(getSuper(), allowableMovement); } else { return objc_getAllowableMovement(this, allowableMovement); }
    }
    
    private static final Selector setAllowableMovement$ = Selector.register("setAllowableMovement:");
    @Bridge private native static void objc_setAllowableMovement(UILongPressGestureRecognizer __self__, Selector __cmd__, float allowableMovement);
    @Bridge private native static void objc_setAllowableMovementSuper(ObjCSuper __super__, Selector __cmd__, float allowableMovement);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/allowableMovement">@property(nonatomic) CGFloat allowableMovement</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setAllowableMovement(float allowableMovement) {
        if (customClass) { objc_setAllowableMovementSuper(getSuper(), setAllowableMovement$, allowableMovement); } else { objc_setAllowableMovement(this, setAllowableMovement$, allowableMovement); }
    }
    
    private static final Selector minimumPressDuration = Selector.register("minimumPressDuration");
    @Bridge private native static double objc_getMinimumPressDuration(UILongPressGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static double objc_getMinimumPressDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/minimumPressDuration">@property(nonatomic) CFTimeInterval minimumPressDuration</a>
     * @since Available in iOS 3.2 and later.
     */
    public double getMinimumPressDuration() {
        if (customClass) { return objc_getMinimumPressDurationSuper(getSuper(), minimumPressDuration); } else { return objc_getMinimumPressDuration(this, minimumPressDuration); }
    }
    
    private static final Selector setMinimumPressDuration$ = Selector.register("setMinimumPressDuration:");
    @Bridge private native static void objc_setMinimumPressDuration(UILongPressGestureRecognizer __self__, Selector __cmd__, double minimumPressDuration);
    @Bridge private native static void objc_setMinimumPressDurationSuper(ObjCSuper __super__, Selector __cmd__, double minimumPressDuration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/minimumPressDuration">@property(nonatomic) CFTimeInterval minimumPressDuration</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMinimumPressDuration(double minimumPressDuration) {
        if (customClass) { objc_setMinimumPressDurationSuper(getSuper(), setMinimumPressDuration$, minimumPressDuration); } else { objc_setMinimumPressDuration(this, setMinimumPressDuration$, minimumPressDuration); }
    }
    
    private static final Selector numberOfTapsRequired = Selector.register("numberOfTapsRequired");
    @Bridge private native static int objc_getNumberOfTapsRequired(UILongPressGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfTapsRequiredSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/numberOfTapsRequired">@property (nonatomic) NSUInteger  numberOfTapsRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getNumberOfTapsRequired() {
        if (customClass) { return objc_getNumberOfTapsRequiredSuper(getSuper(), numberOfTapsRequired); } else { return objc_getNumberOfTapsRequired(this, numberOfTapsRequired); }
    }
    
    private static final Selector setNumberOfTapsRequired$ = Selector.register("setNumberOfTapsRequired:");
    @Bridge private native static void objc_setNumberOfTapsRequired(UILongPressGestureRecognizer __self__, Selector __cmd__, int numberOfTapsRequired);
    @Bridge private native static void objc_setNumberOfTapsRequiredSuper(ObjCSuper __super__, Selector __cmd__, int numberOfTapsRequired);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/numberOfTapsRequired">@property (nonatomic) NSUInteger  numberOfTapsRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setNumberOfTapsRequired(int numberOfTapsRequired) {
        if (customClass) { objc_setNumberOfTapsRequiredSuper(getSuper(), setNumberOfTapsRequired$, numberOfTapsRequired); } else { objc_setNumberOfTapsRequired(this, setNumberOfTapsRequired$, numberOfTapsRequired); }
    }
    
    private static final Selector numberOfTouchesRequired = Selector.register("numberOfTouchesRequired");
    @Bridge private native static int objc_getNumberOfTouchesRequired(UILongPressGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfTouchesRequiredSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/numberOfTouchesRequired">@property(nonatomic) NSInteger numberOfTouchesRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getNumberOfTouchesRequired() {
        if (customClass) { return objc_getNumberOfTouchesRequiredSuper(getSuper(), numberOfTouchesRequired); } else { return objc_getNumberOfTouchesRequired(this, numberOfTouchesRequired); }
    }
    
    private static final Selector setNumberOfTouchesRequired$ = Selector.register("setNumberOfTouchesRequired:");
    @Bridge private native static void objc_setNumberOfTouchesRequired(UILongPressGestureRecognizer __self__, Selector __cmd__, int numberOfTouchesRequired);
    @Bridge private native static void objc_setNumberOfTouchesRequiredSuper(ObjCSuper __super__, Selector __cmd__, int numberOfTouchesRequired);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UILongPressGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UILongPressGestureRecognizer/numberOfTouchesRequired">@property(nonatomic) NSInteger numberOfTouchesRequired</a>
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
        @Callback @BindSelector("allowableMovement") public static float getAllowableMovement(UILongPressGestureRecognizer __self__, Selector __cmd__) { return __self__.getAllowableMovement(); }
        @Callback @BindSelector("setAllowableMovement:") public static void setAllowableMovement(UILongPressGestureRecognizer __self__, Selector __cmd__, float allowableMovement) { __self__.setAllowableMovement(allowableMovement); }
        @Callback @BindSelector("minimumPressDuration") public static double getMinimumPressDuration(UILongPressGestureRecognizer __self__, Selector __cmd__) { return __self__.getMinimumPressDuration(); }
        @Callback @BindSelector("setMinimumPressDuration:") public static void setMinimumPressDuration(UILongPressGestureRecognizer __self__, Selector __cmd__, double minimumPressDuration) { __self__.setMinimumPressDuration(minimumPressDuration); }
        @Callback @BindSelector("numberOfTapsRequired") public static int getNumberOfTapsRequired(UILongPressGestureRecognizer __self__, Selector __cmd__) { return __self__.getNumberOfTapsRequired(); }
        @Callback @BindSelector("setNumberOfTapsRequired:") public static void setNumberOfTapsRequired(UILongPressGestureRecognizer __self__, Selector __cmd__, int numberOfTapsRequired) { __self__.setNumberOfTapsRequired(numberOfTapsRequired); }
        @Callback @BindSelector("numberOfTouchesRequired") public static int getNumberOfTouchesRequired(UILongPressGestureRecognizer __self__, Selector __cmd__) { return __self__.getNumberOfTouchesRequired(); }
        @Callback @BindSelector("setNumberOfTouchesRequired:") public static void setNumberOfTouchesRequired(UILongPressGestureRecognizer __self__, Selector __cmd__, int numberOfTouchesRequired) { __self__.setNumberOfTouchesRequired(numberOfTouchesRequired); }
    }
    /*</callbacks>*/

}
