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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometer_Class/Reference/UIAccelerometer.html">UIAccelerometer Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIAccelerometer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIAccelerometer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIAccelerometer /*</name>*/.class);

    /*<constructors>*/
    protected UIAccelerometer(SkipInit skipInit) { super(skipInit); }
    public UIAccelerometer() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIAccelerometerDelegate objc_getDelegate(UIAccelerometer __self__, Selector __cmd__);
    @Bridge private native static UIAccelerometerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometer_Class/Reference/UIAccelerometer.html#//apple_ref/occ/instp/UIAccelerometer/delegate">@property(nonatomic, assign) id&amp;lt;UIAccelerometerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIAccelerometerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIAccelerometer __self__, Selector __cmd__, UIAccelerometerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIAccelerometerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometer_Class/Reference/UIAccelerometer.html#//apple_ref/occ/instp/UIAccelerometer/delegate">@property(nonatomic, assign) id&amp;lt;UIAccelerometerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIAccelerometerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector updateInterval = Selector.register("updateInterval");
    @Bridge private native static double objc_getUpdateInterval(UIAccelerometer __self__, Selector __cmd__);
    @Bridge private native static double objc_getUpdateIntervalSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometer_Class/Reference/UIAccelerometer.html#//apple_ref/occ/instp/UIAccelerometer/updateInterval">@property(nonatomic) NSTimeInterval updateInterval</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getUpdateInterval() {
        if (customClass) { return objc_getUpdateIntervalSuper(getSuper(), updateInterval); } else { return objc_getUpdateInterval(this, updateInterval); }
    }
    
    private static final Selector setUpdateInterval$ = Selector.register("setUpdateInterval:");
    @Bridge private native static void objc_setUpdateInterval(UIAccelerometer __self__, Selector __cmd__, double updateInterval);
    @Bridge private native static void objc_setUpdateIntervalSuper(ObjCSuper __super__, Selector __cmd__, double updateInterval);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometer_Class/Reference/UIAccelerometer.html#//apple_ref/occ/instp/UIAccelerometer/updateInterval">@property(nonatomic) NSTimeInterval updateInterval</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setUpdateInterval(double updateInterval) {
        if (customClass) { objc_setUpdateIntervalSuper(getSuper(), setUpdateInterval$, updateInterval); } else { objc_setUpdateInterval(this, setUpdateInterval$, updateInterval); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector sharedAccelerometer = Selector.register("sharedAccelerometer");
    @Bridge private native static UIAccelerometer objc_getSharedAccelerometer(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometer_Class/Reference/UIAccelerometer.html#//apple_ref/occ/clm/UIAccelerometer/sharedAccelerometer">+ (UIAccelerometer *)sharedAccelerometer</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIAccelerometer getSharedAccelerometer() {
        return objc_getSharedAccelerometer(objCClass, sharedAccelerometer);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("delegate") public static UIAccelerometerDelegate getDelegate(UIAccelerometer __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIAccelerometer __self__, Selector __cmd__, UIAccelerometerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("updateInterval") public static double getUpdateInterval(UIAccelerometer __self__, Selector __cmd__) { return __self__.getUpdateInterval(); }
        @Callback @BindSelector("setUpdateInterval:") public static void setUpdateInterval(UIAccelerometer __self__, Selector __cmd__, double updateInterval) { __self__.setUpdateInterval(updateInterval); }
    }
    /*</callbacks>*/

}
