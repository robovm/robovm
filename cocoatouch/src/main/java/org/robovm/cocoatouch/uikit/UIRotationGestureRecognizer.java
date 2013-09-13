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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html">UIRotationGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIRotationGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIRotationGestureRecognizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIRotationGestureRecognizer /*</name>*/.class);

    /*<constructors>*/
    protected UIRotationGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UIRotationGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector rotation = Selector.register("rotation");
    @Bridge private native static float objc_getRotation(UIRotationGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static float objc_getRotationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIRotationGestureRecognizer/rotation">@property(nonatomic) CGFloat rotation</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getRotation() {
        if (customClass) { return objc_getRotationSuper(getSuper(), rotation); } else { return objc_getRotation(this, rotation); }
    }
    
    private static final Selector setRotation$ = Selector.register("setRotation:");
    @Bridge private native static void objc_setRotation(UIRotationGestureRecognizer __self__, Selector __cmd__, float rotation);
    @Bridge private native static void objc_setRotationSuper(ObjCSuper __super__, Selector __cmd__, float rotation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIRotationGestureRecognizer/rotation">@property(nonatomic) CGFloat rotation</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setRotation(float rotation) {
        if (customClass) { objc_setRotationSuper(getSuper(), setRotation$, rotation); } else { objc_setRotation(this, setRotation$, rotation); }
    }
    
    private static final Selector velocity = Selector.register("velocity");
    @Bridge private native static float objc_getVelocity(UIRotationGestureRecognizer __self__, Selector __cmd__);
    @Bridge private native static float objc_getVelocitySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIRotationGestureRecognizer/velocity">@property(nonatomic, readonly) CGFloat velocity</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getVelocity() {
        if (customClass) { return objc_getVelocitySuper(getSuper(), velocity); } else { return objc_getVelocity(this, velocity); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("rotation") public static float getRotation(UIRotationGestureRecognizer __self__, Selector __cmd__) { return __self__.getRotation(); }
        @Callback @BindSelector("setRotation:") public static void setRotation(UIRotationGestureRecognizer __self__, Selector __cmd__, float rotation) { __self__.setRotation(rotation); }
        @Callback @BindSelector("velocity") public static float getVelocity(UIRotationGestureRecognizer __self__, Selector __cmd__) { return __self__.getVelocity(); }
    }
    /*</callbacks>*/

}
