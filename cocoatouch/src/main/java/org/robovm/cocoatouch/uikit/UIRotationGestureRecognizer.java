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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html">UIRotationGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIRotationGestureRecognizer /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static float objc_getRotation(UIRotationGestureRecognizer __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getRotationSuper(ObjCSuper __super__, UIRotationGestureRecognizer __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIRotationGestureRecognizer/rotation">@property(nonatomic) CGFloat rotation</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getRotation() {
        if (customClass) { return objc_getRotationSuper(getSuper(), this, rotation); } else { return objc_getRotation(this, rotation); }
    }
    
    private static final Selector setRotation$ = Selector.register("setRotation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRotation(UIRotationGestureRecognizer __self__, Selector __cmd__, float rotation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRotationSuper(ObjCSuper __super__, UIRotationGestureRecognizer __self__, Selector __cmd__, float rotation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIRotationGestureRecognizer/rotation">@property(nonatomic) CGFloat rotation</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setRotation(float rotation) {
        if (customClass) { objc_setRotationSuper(getSuper(), this, setRotation$, rotation); } else { objc_setRotation(this, setRotation$, rotation); }
    }
    
    private static final Selector velocity = Selector.register("velocity");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getVelocity(UIRotationGestureRecognizer __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getVelocitySuper(ObjCSuper __super__, UIRotationGestureRecognizer __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRotateGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIRotationGestureRecognizer/velocity">@property(nonatomic, readonly) CGFloat velocity</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getVelocity() {
        if (customClass) { return objc_getVelocitySuper(getSuper(), this, velocity); } else { return objc_getVelocity(this, velocity); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    /*</callbacks>*/

}
