/*
 * Copyright (C) 2012 Trillian Mobile AB
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
package org.robovm.cocoatouch.coreanimation;

/*<imports>*/
import org.robovm.cocoatouch.coregraphics.*;
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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html">CALayer Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("QuartzCore")/*</library>*/
@NativeClass public class /*<name>*/ CALayer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ CALayer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ CALayer /*</name>*/.class);

    /*<constructors>*/
    protected CALayer(SkipInit skipInit) { super(skipInit); }
    public CALayer() {}
    
    /*</constructors>*/
    /*<properties>*/
    private static final Selector cornerRadius = Selector.register("cornerRadius");
    @Bridge private native static float objc_getCornerRadius(CALayer __self__, Selector __cmd__);
    @Bridge private native static float objc_getCornerRadiusSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/cornerRadius">@property CGFloat cornerRadius</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getCornerRadius() {
        if (customClass) { return objc_getCornerRadiusSuper(getSuper(), cornerRadius); } else { return objc_getCornerRadius(this, cornerRadius); }
    }
    
    private static final Selector setCornerRadius$ = Selector.register("setCornerRadius:");
    @Bridge private native static void objc_setCornerRadius(CALayer __self__, Selector __cmd__, float cornerRadius);
    @Bridge private native static void objc_setCornerRadiusSuper(ObjCSuper __super__, Selector __cmd__, float cornerRadius);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/cornerRadius">@property CGFloat cornerRadius</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCornerRadius(float cornerRadius) {
        if (customClass) { objc_setCornerRadiusSuper(getSuper(), setCornerRadius$, cornerRadius); } else { objc_setCornerRadius(this, setCornerRadius$, cornerRadius); }
    }
    
    private static final Selector shadowColor = Selector.register("shadowColor");
    @Bridge private native static CGColor objc_getShadowColor(CALayer __self__, Selector __cmd__);
    @Bridge private native static CGColor objc_getShadowColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowColor">@property CGColorRef shadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGColor getShadowColor() {
        if (customClass) { return objc_getShadowColorSuper(getSuper(), shadowColor); } else { return objc_getShadowColor(this, shadowColor); }
    }
    
    private static final Selector setShadowColor = Selector.register("setShadowColor:");
    @Bridge private native static void objc_setShadowColor(CALayer __self__, Selector __cmd__, CGColor shadowColor);
    @Bridge private native static void objc_setShadowColorSuper(ObjCSuper __super__, Selector __cmd__, CGColor shadowColor);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowColor">@property CGColorRef shadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShadowColor(CGColor shadowColor) {
        if (customClass) { objc_setShadowColorSuper(getSuper(), setShadowColor, shadowColor); } else { objc_setShadowColor(this, setShadowColor, shadowColor); }
    }
    
    private static final Selector shadowOpacity = Selector.register("shadowOpacity");
    @Bridge private native static float objc_getShadowOpacity(CALayer __self__, Selector __cmd__);
    @Bridge private native static float objc_getShadowOpacitySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowOpacity">@property CGFloat shadowOpacity</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getShadowOpacity() {
        if (customClass) { return objc_getShadowOpacitySuper(getSuper(), shadowOpacity); } else { return objc_getShadowOpacity(this, shadowOpacity); }
    }
    
    private static final Selector setShadowOpacity = Selector.register("setShadowOpacity:");
    @Bridge private native static void objc_setShadowOpacity(CALayer __self__, Selector __cmd__, float shadowOpacity);
    @Bridge private native static void objc_setShadowOpacitySuper(ObjCSuper __super__, Selector __cmd__, float shadowOpacity);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowOpacity">@property CGFloat shadowOpacity</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShadowOpacity(float shadowOpacity) {
        if (customClass) { objc_setShadowOpacitySuper(getSuper(), setShadowOpacity, shadowOpacity); } else { objc_setShadowOpacity(this, setShadowOpacity, shadowOpacity); }
    }
    
    private static final Selector shadowRadius = Selector.register("shadowRadius");
    @Bridge private native static float objc_getShadowRadius(CALayer __self__, Selector __cmd__);
    @Bridge private native static float objc_getShadowRadiusSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowRadius">@property CGFloat shadowRadius</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getShadowRadius() {
        if (customClass) { return objc_getShadowRadiusSuper(getSuper(), shadowRadius); } else { return objc_getShadowRadius(this, shadowRadius); }
    }
    
    private static final Selector setShadowRadius = Selector.register("setShadowRadius:");
    @Bridge private native static void objc_setShadowRadius(CALayer __self__, Selector __cmd__, float shadowRadius);
    @Bridge private native static void objc_setShadowRadiusSuper(ObjCSuper __super__, Selector __cmd__, float shadowRadius);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowRadius">@property CGFloat shadowRadius</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShadowRadius(float shadowRadius) {
        if (customClass) { objc_setShadowRadiusSuper(getSuper(), setShadowRadius, shadowRadius); } else { objc_setShadowRadius(this, setShadowRadius, shadowRadius); }
    }
    
    private static final Selector shadowOffset = Selector.register("shadowOffset");
    @Bridge private native static CGSize objc_getShadowOffset(CALayer __self__, Selector __cmd__);
    @Bridge private native static CGSize objc_getShadowOffsetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowOffset">@property CGSize shadowOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getShadowOffset() {
        if (customClass) { return objc_getShadowOffsetSuper(getSuper(), shadowOffset); } else { return objc_getShadowOffset(this, shadowOffset); }
    }
    
    private static final Selector setShadowOffset = Selector.register("setShadowOffset:");
    @Bridge private native static void objc_setShadowOffset(CALayer __self__, Selector __cmd__, CGSize shadowOffset);
    @Bridge private native static void objc_setShadowOffsetSuper(ObjCSuper __super__, Selector __cmd__, CGSize shadowOffset);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowOffset">@property CGSize shadowOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShadowOffset(CGSize shadowOffset) {
        if (customClass) { objc_setShadowOffsetSuper(getSuper(), setShadowOffset, shadowOffset); } else { objc_setShadowOffset(this, setShadowOffset, shadowOffset); }
    }
    
    private static final Selector borderWidth = Selector.register("borderWidth");
    @Bridge private native static float objc_getBorderWidth(CALayer __self__, Selector __cmd__);
    @Bridge private native static float objc_getBorderWidthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/borderWidth">@property CGFloat borderWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getBorderWidth() {
        if (customClass) { return objc_getBorderWidthSuper(getSuper(), borderWidth); } else { return objc_getBorderWidth(this, borderWidth); }
    }
    
    private static final Selector setBorderWidth = Selector.register("setBorderWidth:");
    @Bridge private native static void objc_setBorderWidth(CALayer __self__, Selector __cmd__, float borderWidth);
    @Bridge private native static void objc_setBorderWidthSuper(ObjCSuper __super__, Selector __cmd__, float borderWidth);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowOffset">@property CGFloat borderWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBorderWidth(float borderWidth) {
        if (customClass) { objc_setBorderWidthSuper(getSuper(), setBorderWidth, borderWidth); } else { objc_setBorderWidth(this, setBorderWidth, borderWidth); }
    }
    
    private static final Selector borderColor = Selector.register("borderColor");
    @Bridge private native static CGColor objc_getBorderColor(CALayer __self__, Selector __cmd__);
    @Bridge private native static CGColor objc_getBorderColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/borderColor">@property CGColor borderColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGColor getBorderColor() {
        if (customClass) { return objc_getBorderColorSuper(getSuper(), borderColor); } else { return objc_getBorderColor(this, borderColor); }
    }
    
    private static final Selector setBorderColor = Selector.register("setBorderColor:");
    @Bridge private native static void objc_setBorderColor(CALayer __self__, Selector __cmd__, CGColor borderColor);
    @Bridge private native static void objc_setBorderColorSuper(ObjCSuper __super__, Selector __cmd__, CGColor borderColor);
    /**
     * @see <a href="https://developer.apple.com/library/mac/documentation/GraphicsImaging/Reference/CALayer_class/Introduction/Introduction.html#//apple_ref/occ/instp/CALayer/shadowOffset">@property CGColor borderColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBorderColor(CGColor borderColor) {
        if (customClass) { objc_setBorderColorSuper(getSuper(), setBorderColor, borderColor); } else { objc_setBorderColor(this, setBorderColor, borderColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("cornerRadius") public static float getCornerRadius(CALayer __self__, Selector __cmd__) { return __self__.getCornerRadius(); }
        @Callback @BindSelector("setCornerRadius:") public static void setCornerRadius(CALayer __self__, Selector __cmd__, float cornerRadius) { __self__.setCornerRadius(cornerRadius); }
        @Callback @BindSelector("shadowColor") public static CGColor getShadowColor(CALayer __self__, Selector __cmd__) { return __self__.getShadowColor(); }
        @Callback @BindSelector("setShadowColor:") public static void setShadowColor(CALayer __self__, Selector __cmd__, CGColor shadowColor) { __self__.setShadowColor(shadowColor); }
        @Callback @BindSelector("shadowOpacity") public static float getShadowOpacity(CALayer __self__, Selector __cmd__) { return __self__.getShadowOpacity(); }
        @Callback @BindSelector("setShadowOpacity:") public static void setShadowOpacity(CALayer __self__, Selector __cmd__, float shadowOpacity) { __self__.setShadowOpacity(shadowOpacity); }
        @Callback @BindSelector("shadowRadius") public static float getShadowRadius(CALayer __self__, Selector __cmd__) { return __self__.getShadowRadius(); }
        @Callback @BindSelector("setShadowRadius:") public static void setShadowRadius(CALayer __self__, Selector __cmd__, float shadowRadius) { __self__.setShadowRadius(shadowRadius); }
        @Callback @BindSelector("shadowOffset") public static CGSize getShadowOffset(CALayer __self__, Selector __cmd__) { return __self__.getShadowOffset(); }
        @Callback @BindSelector("setShadowOffset:") public static void setShadowOffset(CALayer __self__, Selector __cmd__, CGSize shadowOffset) { __self__.setShadowOffset(shadowOffset); }
        @Callback @BindSelector("borderWidth") public static float getBorderWidth(CALayer __self__, Selector __cmd__) { return __self__.getBorderWidth(); }
        @Callback @BindSelector("setBorderWidth:") public static void setBorderWidth(CALayer __self__, Selector __cmd__, float borderWidth) { __self__.setBorderWidth(borderWidth); }
        @Callback @BindSelector("borderColor") public static CGColor getBorderColor(CALayer __self__, Selector __cmd__) { return __self__.getBorderColor(); }
        @Callback @BindSelector("setBorderColor:") public static void setBorderColor(CALayer __self__, Selector __cmd__, CGColor borderColor) { __self__.setBorderColor(borderColor); }
    }
    /*</callbacks>*/

}
