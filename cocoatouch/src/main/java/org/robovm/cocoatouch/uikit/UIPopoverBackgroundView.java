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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html">UIPopoverBackgroundView Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPopoverBackgroundView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPopoverBackgroundView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPopoverBackgroundView /*</name>*/.class);

    public UIPopoverBackgroundView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIPopoverBackgroundView(SkipInit skipInit) { super(skipInit); }
    public UIPopoverBackgroundView() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector arrowDirection = Selector.register("arrowDirection");
    @Bridge private native static UIPopoverArrowDirection objc_getArrowDirection(UIPopoverBackgroundView __self__, Selector __cmd__);
    @Bridge private native static UIPopoverArrowDirection objc_getArrowDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowDirection">@property (nonatomic, readwrite) UIPopoverArrowDirection arrowDirection</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPopoverArrowDirection getArrowDirection() {
        if (customClass) { return objc_getArrowDirectionSuper(getSuper(), arrowDirection); } else { return objc_getArrowDirection(this, arrowDirection); }
    }
    
    private static final Selector setArrowDirection$ = Selector.register("setArrowDirection:");
    @Bridge private native static void objc_setArrowDirection(UIPopoverBackgroundView __self__, Selector __cmd__, UIPopoverArrowDirection arrowDirection);
    @Bridge private native static void objc_setArrowDirectionSuper(ObjCSuper __super__, Selector __cmd__, UIPopoverArrowDirection arrowDirection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowDirection">@property (nonatomic, readwrite) UIPopoverArrowDirection arrowDirection</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setArrowDirection(UIPopoverArrowDirection arrowDirection) {
        if (customClass) { objc_setArrowDirectionSuper(getSuper(), setArrowDirection$, arrowDirection); } else { objc_setArrowDirection(this, setArrowDirection$, arrowDirection); }
    }
    
    private static final Selector arrowOffset = Selector.register("arrowOffset");
    @Bridge private native static float objc_getArrowOffset(UIPopoverBackgroundView __self__, Selector __cmd__);
    @Bridge private native static float objc_getArrowOffsetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowOffset">@property (nonatomic, readwrite) CGFloat arrowOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getArrowOffset() {
        if (customClass) { return objc_getArrowOffsetSuper(getSuper(), arrowOffset); } else { return objc_getArrowOffset(this, arrowOffset); }
    }
    
    private static final Selector setArrowOffset$ = Selector.register("setArrowOffset:");
    @Bridge private native static void objc_setArrowOffset(UIPopoverBackgroundView __self__, Selector __cmd__, float arrowOffset);
    @Bridge private native static void objc_setArrowOffsetSuper(ObjCSuper __super__, Selector __cmd__, float arrowOffset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowOffset">@property (nonatomic, readwrite) CGFloat arrowOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setArrowOffset(float arrowOffset) {
        if (customClass) { objc_setArrowOffsetSuper(getSuper(), setArrowOffset$, arrowOffset); } else { objc_setArrowOffset(this, setArrowOffset$, arrowOffset); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector arrowBase = Selector.register("arrowBase");
    @Bridge private native static float objc_getArrowBase(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/arrowBase">+ (CGFloat)arrowBase</a>
     * @since Available in iOS 5.0 and later.
     */
    public static float getArrowBase() {
        return objc_getArrowBase(objCClass, arrowBase);
    }
    
    private static final Selector arrowHeight = Selector.register("arrowHeight");
    @Bridge private native static float objc_getArrowHeight(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/arrowHeight">+ (CGFloat)arrowHeight</a>
     * @since Available in iOS 5.0 and later.
     */
    public static float getArrowHeight() {
        return objc_getArrowHeight(objCClass, arrowHeight);
    }
    
    private static final Selector contentViewInsets = Selector.register("contentViewInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getContentViewInsets(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/contentViewInsets">+ (UIEdgeInsets)contentViewInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIEdgeInsets getContentViewInsets() {
        return objc_getContentViewInsets(objCClass, contentViewInsets);
    }
    
    private static final Selector wantsDefaultContentAppearance = Selector.register("wantsDefaultContentAppearance");
    @Bridge private native static boolean objc_wantsDefaultContentAppearance(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/wantsDefaultContentAppearance">+ (BOOL)wantsDefaultContentAppearance</a>
     * @since Available in iOS 6.0 and later.
     */
    public static boolean wantsDefaultContentAppearance() {
        return objc_wantsDefaultContentAppearance(objCClass, wantsDefaultContentAppearance);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("arrowDirection") public static UIPopoverArrowDirection getArrowDirection(UIPopoverBackgroundView __self__, Selector __cmd__) { return __self__.getArrowDirection(); }
        @Callback @BindSelector("setArrowDirection:") public static void setArrowDirection(UIPopoverBackgroundView __self__, Selector __cmd__, UIPopoverArrowDirection arrowDirection) { __self__.setArrowDirection(arrowDirection); }
        @Callback @BindSelector("arrowOffset") public static float getArrowOffset(UIPopoverBackgroundView __self__, Selector __cmd__) { return __self__.getArrowOffset(); }
        @Callback @BindSelector("setArrowOffset:") public static void setArrowOffset(UIPopoverBackgroundView __self__, Selector __cmd__, float arrowOffset) { __self__.setArrowOffset(arrowOffset); }
    }
    /*</callbacks>*/

}
