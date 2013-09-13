/*
 * Copyright (C) 2013 Trillian AB
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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAcceleration_Class/Reference/UIAcceleration.html">UIAcceleration Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIAcceleration /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIAcceleration /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIAcceleration /*</name>*/.class);

    /*<constructors>*/
    protected UIAcceleration(SkipInit skipInit) { super(skipInit); }
    public UIAcceleration() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector timestamp = Selector.register("timestamp");
    @Bridge private native static double objc_getTimestamp(UIAcceleration __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimestampSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAcceleration_Class/Reference/UIAcceleration.html#//apple_ref/occ/instp/UIAcceleration/timestamp">@property(nonatomic, readonly) NSTimeInterval timestamp</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getTimestamp() {
        if (customClass) { return objc_getTimestampSuper(getSuper(), timestamp); } else { return objc_getTimestamp(this, timestamp); }
    }
    
    private static final Selector x = Selector.register("x");
    @Bridge private native static double objc_getX(UIAcceleration __self__, Selector __cmd__);
    @Bridge private native static double objc_getXSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAcceleration_Class/Reference/UIAcceleration.html#//apple_ref/occ/instp/UIAcceleration/x">@property(nonatomic, readonly) UIAccelerationValue x</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getX() {
        if (customClass) { return objc_getXSuper(getSuper(), x); } else { return objc_getX(this, x); }
    }
    
    private static final Selector y = Selector.register("y");
    @Bridge private native static double objc_getY(UIAcceleration __self__, Selector __cmd__);
    @Bridge private native static double objc_getYSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAcceleration_Class/Reference/UIAcceleration.html#//apple_ref/occ/instp/UIAcceleration/y">@property(nonatomic, readonly) UIAccelerationValue y</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getY() {
        if (customClass) { return objc_getYSuper(getSuper(), y); } else { return objc_getY(this, y); }
    }
    
    private static final Selector z = Selector.register("z");
    @Bridge private native static double objc_getZ(UIAcceleration __self__, Selector __cmd__);
    @Bridge private native static double objc_getZSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAcceleration_Class/Reference/UIAcceleration.html#//apple_ref/occ/instp/UIAcceleration/z">@property(nonatomic, readonly) UIAccelerationValue z</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getZ() {
        if (customClass) { return objc_getZSuper(getSuper(), z); } else { return objc_getZ(this, z); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("timestamp") public static double getTimestamp(UIAcceleration __self__, Selector __cmd__) { return __self__.getTimestamp(); }
        @Callback @BindSelector("x") public static double getX(UIAcceleration __self__, Selector __cmd__) { return __self__.getX(); }
        @Callback @BindSelector("y") public static double getY(UIAcceleration __self__, Selector __cmd__) { return __self__.getY(); }
        @Callback @BindSelector("z") public static double getZ(UIAcceleration __self__, Selector __cmd__) { return __self__.getZ(); }
    }
    /*</callbacks>*/

}
