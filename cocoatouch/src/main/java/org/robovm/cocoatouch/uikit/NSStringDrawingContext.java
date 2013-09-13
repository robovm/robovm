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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html">NSStringDrawingContext Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ NSStringDrawingContext /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSStringDrawingContext /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSStringDrawingContext /*</name>*/.class);

    /*<constructors>*/
    protected NSStringDrawingContext(SkipInit skipInit) { super(skipInit); }
    public NSStringDrawingContext() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector actualScaleFactor = Selector.register("actualScaleFactor");
    @Bridge private native static float objc_getActualScaleFactor(NSStringDrawingContext __self__, Selector __cmd__);
    @Bridge private native static float objc_getActualScaleFactorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/actualScaleFactor">@property(nonatomic, readonly) CGFloat actualScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getActualScaleFactor() {
        if (customClass) { return objc_getActualScaleFactorSuper(getSuper(), actualScaleFactor); } else { return objc_getActualScaleFactor(this, actualScaleFactor); }
    }
    
    private static final Selector actualTrackingAdjustment = Selector.register("actualTrackingAdjustment");
    @Bridge private native static float objc_getActualTrackingAdjustment(NSStringDrawingContext __self__, Selector __cmd__);
    @Bridge private native static float objc_getActualTrackingAdjustmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/actualTrackingAdjustment">@property(nonatomic, readonly) CGFloat actualTrackingAdjustment</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getActualTrackingAdjustment() {
        if (customClass) { return objc_getActualTrackingAdjustmentSuper(getSuper(), actualTrackingAdjustment); } else { return objc_getActualTrackingAdjustment(this, actualTrackingAdjustment); }
    }
    
    private static final Selector minimumScaleFactor = Selector.register("minimumScaleFactor");
    @Bridge private native static float objc_getMinimumScaleFactor(NSStringDrawingContext __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumScaleFactorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMinimumScaleFactor() {
        if (customClass) { return objc_getMinimumScaleFactorSuper(getSuper(), minimumScaleFactor); } else { return objc_getMinimumScaleFactor(this, minimumScaleFactor); }
    }
    
    private static final Selector setMinimumScaleFactor$ = Selector.register("setMinimumScaleFactor:");
    @Bridge private native static void objc_setMinimumScaleFactor(NSStringDrawingContext __self__, Selector __cmd__, float minimumScaleFactor);
    @Bridge private native static void objc_setMinimumScaleFactorSuper(ObjCSuper __super__, Selector __cmd__, float minimumScaleFactor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setMinimumScaleFactor(float minimumScaleFactor) {
        if (customClass) { objc_setMinimumScaleFactorSuper(getSuper(), setMinimumScaleFactor$, minimumScaleFactor); } else { objc_setMinimumScaleFactor(this, setMinimumScaleFactor$, minimumScaleFactor); }
    }
    
    private static final Selector minimumTrackingAdjustment = Selector.register("minimumTrackingAdjustment");
    @Bridge private native static float objc_getMinimumTrackingAdjustment(NSStringDrawingContext __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumTrackingAdjustmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumTrackingAdjustment">@property(nonatomic) CGFloat minimumTrackingAdjustment</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMinimumTrackingAdjustment() {
        if (customClass) { return objc_getMinimumTrackingAdjustmentSuper(getSuper(), minimumTrackingAdjustment); } else { return objc_getMinimumTrackingAdjustment(this, minimumTrackingAdjustment); }
    }
    
    private static final Selector setMinimumTrackingAdjustment$ = Selector.register("setMinimumTrackingAdjustment:");
    @Bridge private native static void objc_setMinimumTrackingAdjustment(NSStringDrawingContext __self__, Selector __cmd__, float minimumTrackingAdjustment);
    @Bridge private native static void objc_setMinimumTrackingAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, float minimumTrackingAdjustment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumTrackingAdjustment">@property(nonatomic) CGFloat minimumTrackingAdjustment</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setMinimumTrackingAdjustment(float minimumTrackingAdjustment) {
        if (customClass) { objc_setMinimumTrackingAdjustmentSuper(getSuper(), setMinimumTrackingAdjustment$, minimumTrackingAdjustment); } else { objc_setMinimumTrackingAdjustment(this, setMinimumTrackingAdjustment$, minimumTrackingAdjustment); }
    }
    
    private static final Selector totalBounds = Selector.register("totalBounds");
    @Bridge private native static @ByVal CGRect objc_getTotalBounds(NSStringDrawingContext __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getTotalBoundsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/totalBounds">@property(nonatomic, readonly) CGRect totalBounds</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getTotalBounds() {
        if (customClass) { return objc_getTotalBoundsSuper(getSuper(), totalBounds); } else { return objc_getTotalBounds(this, totalBounds); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("actualScaleFactor") public static float getActualScaleFactor(NSStringDrawingContext __self__, Selector __cmd__) { return __self__.getActualScaleFactor(); }
        @Callback @BindSelector("actualTrackingAdjustment") public static float getActualTrackingAdjustment(NSStringDrawingContext __self__, Selector __cmd__) { return __self__.getActualTrackingAdjustment(); }
        @Callback @BindSelector("minimumScaleFactor") public static float getMinimumScaleFactor(NSStringDrawingContext __self__, Selector __cmd__) { return __self__.getMinimumScaleFactor(); }
        @Callback @BindSelector("setMinimumScaleFactor:") public static void setMinimumScaleFactor(NSStringDrawingContext __self__, Selector __cmd__, float minimumScaleFactor) { __self__.setMinimumScaleFactor(minimumScaleFactor); }
        @Callback @BindSelector("minimumTrackingAdjustment") public static float getMinimumTrackingAdjustment(NSStringDrawingContext __self__, Selector __cmd__) { return __self__.getMinimumTrackingAdjustment(); }
        @Callback @BindSelector("setMinimumTrackingAdjustment:") public static void setMinimumTrackingAdjustment(NSStringDrawingContext __self__, Selector __cmd__, float minimumTrackingAdjustment) { __self__.setMinimumTrackingAdjustment(minimumTrackingAdjustment); }
        @Callback @BindSelector("totalBounds") public static @ByVal CGRect getTotalBounds(NSStringDrawingContext __self__, Selector __cmd__) { return __self__.getTotalBounds(); }
    }
    /*</callbacks>*/

}
