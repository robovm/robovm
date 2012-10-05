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
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html">UIPopoverBackgroundView Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPopoverBackgroundView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPopoverBackgroundView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPopoverBackgroundView /*</name>*/.class);

    /*<constructors>*/
    protected UIPopoverBackgroundView(SkipInit skipInit) { super(skipInit); }
    public UIPopoverBackgroundView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowDirection">@property (nonatomic, readwrite) UIPopoverArrowDirection arrowDirection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("arrowDirection") public native UIPopoverArrowDirection getArrowDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowDirection">@property (nonatomic, readwrite) UIPopoverArrowDirection arrowDirection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setArrowDirection:") public native void setArrowDirection(UIPopoverArrowDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowOffset">@property (nonatomic, readwrite) CGFloat arrowOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("arrowOffset") public native float getArrowOffset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverBackgroundView/arrowOffset">@property (nonatomic, readwrite) CGFloat arrowOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setArrowOffset:") public native void setArrowOffset(float v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector arrowBase = Selector.register("arrowBase");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getArrowBase(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/arrowBase">+ (CGFloat)arrowBase</a>
     * @since Available in iOS 5.0 and later.
     */
    public static float getArrowBase() {
        return objc_getArrowBase(objCClass, arrowBase);
    }
    
    private static final Selector arrowHeight = Selector.register("arrowHeight");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getArrowHeight(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/arrowHeight">+ (CGFloat)arrowHeight</a>
     * @since Available in iOS 5.0 and later.
     */
    public static float getArrowHeight() {
        return objc_getArrowHeight(objCClass, arrowHeight);
    }
    
    private static final Selector contentViewInsets = Selector.register("contentViewInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getContentViewInsets(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/contentViewInsets">+ (UIEdgeInsets)contentViewInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIEdgeInsets getContentViewInsets() {
        return objc_getContentViewInsets(objCClass, contentViewInsets);
    }
    
    private static final Selector wantsDefaultContentAppearance = Selector.register("wantsDefaultContentAppearance");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_wantsDefaultContentAppearance(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverBackgroundView_class/Reference/Reference.html#//apple_ref/occ/clm/UIPopoverBackgroundView/wantsDefaultContentAppearance">+ (BOOL)wantsDefaultContentAppearance</a>
     * @since Available in iOS 6.0 and later.
     */
    public static boolean wantsDefaultContentAppearance() {
        return objc_wantsDefaultContentAppearance(objCClass, wantsDefaultContentAppearance);
    }
    /*</methods>*/

}
