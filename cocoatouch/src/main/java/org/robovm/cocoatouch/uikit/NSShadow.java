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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html">NSShadow Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ NSShadow /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSShadow /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSShadow /*</name>*/.class);

    /*<constructors>*/
    protected NSShadow(SkipInit skipInit) { super(skipInit); }
    public NSShadow() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector shadowBlurRadius = Selector.register("shadowBlurRadius");
    @Bridge private native static float objc_getShadowBlurRadius(NSShadow __self__, Selector __cmd__);
    @Bridge private native static float objc_getShadowBlurRadiusSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowBlurRadius">@property (nonatomic, assign) CGFloat shadowBlurRadius</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getShadowBlurRadius() {
        if (customClass) { return objc_getShadowBlurRadiusSuper(getSuper(), shadowBlurRadius); } else { return objc_getShadowBlurRadius(this, shadowBlurRadius); }
    }
    
    private static final Selector setShadowBlurRadius$ = Selector.register("setShadowBlurRadius:");
    @Bridge private native static void objc_setShadowBlurRadius(NSShadow __self__, Selector __cmd__, float shadowBlurRadius);
    @Bridge private native static void objc_setShadowBlurRadiusSuper(ObjCSuper __super__, Selector __cmd__, float shadowBlurRadius);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowBlurRadius">@property (nonatomic, assign) CGFloat shadowBlurRadius</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowBlurRadius(float shadowBlurRadius) {
        if (customClass) { objc_setShadowBlurRadiusSuper(getSuper(), setShadowBlurRadius$, shadowBlurRadius); } else { objc_setShadowBlurRadius(this, setShadowBlurRadius$, shadowBlurRadius); }
    }
    
    private static final Selector shadowColor = Selector.register("shadowColor");
    @Bridge private native static NSObject objc_getShadowColor(NSShadow __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getShadowColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowColor">@property (nonatomic, retain) id shadowColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject getShadowColor() {
        if (customClass) { return objc_getShadowColorSuper(getSuper(), shadowColor); } else { return objc_getShadowColor(this, shadowColor); }
    }
    
    private static final Selector setShadowColor$ = Selector.register("setShadowColor:");
    @Bridge private native static void objc_setShadowColor(NSShadow __self__, Selector __cmd__, NSObject shadowColor);
    @Bridge private native static void objc_setShadowColorSuper(ObjCSuper __super__, Selector __cmd__, NSObject shadowColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowColor">@property (nonatomic, retain) id shadowColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowColor(NSObject shadowColor) {
        if (customClass) { objc_setShadowColorSuper(getSuper(), setShadowColor$, shadowColor); } else { objc_setShadowColor(this, setShadowColor$, shadowColor); }
    }
    
    private static final Selector shadowOffset = Selector.register("shadowOffset");
    @Bridge private native static @ByVal CGSize objc_getShadowOffset(NSShadow __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getShadowOffsetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowOffset">@property (nonatomic, assign) CGSize shadowOffset</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getShadowOffset() {
        if (customClass) { return objc_getShadowOffsetSuper(getSuper(), shadowOffset); } else { return objc_getShadowOffset(this, shadowOffset); }
    }
    
    private static final Selector setShadowOffset$ = Selector.register("setShadowOffset:");
    @Bridge private native static void objc_setShadowOffset(NSShadow __self__, Selector __cmd__, @ByVal CGSize shadowOffset);
    @Bridge private native static void objc_setShadowOffsetSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize shadowOffset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowOffset">@property (nonatomic, assign) CGSize shadowOffset</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowOffset(CGSize shadowOffset) {
        if (customClass) { objc_setShadowOffsetSuper(getSuper(), setShadowOffset$, shadowOffset); } else { objc_setShadowOffset(this, setShadowOffset$, shadowOffset); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("shadowBlurRadius") public static float getShadowBlurRadius(NSShadow __self__, Selector __cmd__) { return __self__.getShadowBlurRadius(); }
        @Callback @BindSelector("setShadowBlurRadius:") public static void setShadowBlurRadius(NSShadow __self__, Selector __cmd__, float shadowBlurRadius) { __self__.setShadowBlurRadius(shadowBlurRadius); }
        @Callback @BindSelector("shadowColor") public static NSObject getShadowColor(NSShadow __self__, Selector __cmd__) { return __self__.getShadowColor(); }
        @Callback @BindSelector("setShadowColor:") public static void setShadowColor(NSShadow __self__, Selector __cmd__, NSObject shadowColor) { __self__.setShadowColor(shadowColor); }
        @Callback @BindSelector("shadowOffset") public static @ByVal CGSize getShadowOffset(NSShadow __self__, Selector __cmd__) { return __self__.getShadowOffset(); }
        @Callback @BindSelector("setShadowOffset:") public static void setShadowOffset(NSShadow __self__, Selector __cmd__, @ByVal CGSize shadowOffset) { __self__.setShadowOffset(shadowOffset); }
    }
    /*</callbacks>*/

}
