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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html">UIActivityIndicatorView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIActivityIndicatorView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivityIndicatorView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActivityIndicatorView /*</name>*/.class);

    public UIActivityIndicatorView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIActivityIndicatorView(SkipInit skipInit) { super(skipInit); }
    public UIActivityIndicatorView() {}
    
    private static final Selector initWithActivityIndicatorStyle$ = Selector.register("initWithActivityIndicatorStyle:");
    @Bridge private native static @Pointer long objc_initWithActivityIndicatorStyle(UIActivityIndicatorView __self__, Selector __cmd__, UIActivityIndicatorViewStyle style);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/initWithActivityIndicatorStyle:">- (id)initWithActivityIndicatorStyle:(UIActivityIndicatorViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIActivityIndicatorView(UIActivityIndicatorViewStyle style) {
        super((SkipInit) null);
        initObject(objc_initWithActivityIndicatorStyle(this, initWithActivityIndicatorStyle$, style));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector activityIndicatorViewStyle = Selector.register("activityIndicatorViewStyle");
    @Bridge private native static UIActivityIndicatorViewStyle objc_getActivityIndicatorViewStyle(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge private native static UIActivityIndicatorViewStyle objc_getActivityIndicatorViewStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/activityIndicatorViewStyle">@property UIActivityIndicatorViewStyle activityIndicatorViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIActivityIndicatorViewStyle getActivityIndicatorViewStyle() {
        if (customClass) { return objc_getActivityIndicatorViewStyleSuper(getSuper(), activityIndicatorViewStyle); } else { return objc_getActivityIndicatorViewStyle(this, activityIndicatorViewStyle); }
    }
    
    private static final Selector setActivityIndicatorViewStyle$ = Selector.register("setActivityIndicatorViewStyle:");
    @Bridge private native static void objc_setActivityIndicatorViewStyle(UIActivityIndicatorView __self__, Selector __cmd__, UIActivityIndicatorViewStyle activityIndicatorViewStyle);
    @Bridge private native static void objc_setActivityIndicatorViewStyleSuper(ObjCSuper __super__, Selector __cmd__, UIActivityIndicatorViewStyle activityIndicatorViewStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/activityIndicatorViewStyle">@property UIActivityIndicatorViewStyle activityIndicatorViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setActivityIndicatorViewStyle(UIActivityIndicatorViewStyle activityIndicatorViewStyle) {
        if (customClass) { objc_setActivityIndicatorViewStyleSuper(getSuper(), setActivityIndicatorViewStyle$, activityIndicatorViewStyle); } else { objc_setActivityIndicatorViewStyle(this, setActivityIndicatorViewStyle$, activityIndicatorViewStyle); }
    }
    
    private static final Selector color = Selector.register("color");
    @Bridge private native static UIColor objc_getColor(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/color">@property (readwrite, nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getColor() {
        if (customClass) { return objc_getColorSuper(getSuper(), color); } else { return objc_getColor(this, color); }
    }
    
    private static final Selector setColor$ = Selector.register("setColor:");
    @Bridge private native static void objc_setColor(UIActivityIndicatorView __self__, Selector __cmd__, UIColor color);
    @Bridge private native static void objc_setColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor color);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/color">@property (readwrite, nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setColor(UIColor color) {
        if (customClass) { objc_setColorSuper(getSuper(), setColor$, color); } else { objc_setColor(this, setColor$, color); }
    }
    
    private static final Selector hidesWhenStopped = Selector.register("hidesWhenStopped");
    @Bridge private native static boolean objc_isHidesWhenStopped(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHidesWhenStoppedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/hidesWhenStopped">@property BOOL hidesWhenStopped</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHidesWhenStopped() {
        if (customClass) { return objc_isHidesWhenStoppedSuper(getSuper(), hidesWhenStopped); } else { return objc_isHidesWhenStopped(this, hidesWhenStopped); }
    }
    
    private static final Selector setHidesWhenStopped$ = Selector.register("setHidesWhenStopped:");
    @Bridge private native static void objc_setHidesWhenStopped(UIActivityIndicatorView __self__, Selector __cmd__, boolean hidesWhenStopped);
    @Bridge private native static void objc_setHidesWhenStoppedSuper(ObjCSuper __super__, Selector __cmd__, boolean hidesWhenStopped);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/hidesWhenStopped">@property BOOL hidesWhenStopped</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesWhenStopped(boolean hidesWhenStopped) {
        if (customClass) { objc_setHidesWhenStoppedSuper(getSuper(), setHidesWhenStopped$, hidesWhenStopped); } else { objc_setHidesWhenStopped(this, setHidesWhenStopped$, hidesWhenStopped); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector isAnimating = Selector.register("isAnimating");
    @Bridge private native static boolean objc_isAnimating(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAnimatingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/isAnimating">- (BOOL)isAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAnimating() {
        if (customClass) { return objc_isAnimatingSuper(getSuper(), isAnimating); } else { return objc_isAnimating(this, isAnimating); }
    }
    
    private static final Selector startAnimating = Selector.register("startAnimating");
    @Bridge private native static void objc_startAnimating(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge private native static void objc_startAnimatingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/startAnimating">- (void)startAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void startAnimating() {
        if (customClass) { objc_startAnimatingSuper(getSuper(), startAnimating); } else { objc_startAnimating(this, startAnimating); }
    }
    
    private static final Selector stopAnimating = Selector.register("stopAnimating");
    @Bridge private native static void objc_stopAnimating(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge private native static void objc_stopAnimatingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/stopAnimating">- (void)stopAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void stopAnimating() {
        if (customClass) { objc_stopAnimatingSuper(getSuper(), stopAnimating); } else { objc_stopAnimating(this, stopAnimating); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("activityIndicatorViewStyle") public static UIActivityIndicatorViewStyle getActivityIndicatorViewStyle(UIActivityIndicatorView __self__, Selector __cmd__) { return __self__.getActivityIndicatorViewStyle(); }
        @Callback @BindSelector("setActivityIndicatorViewStyle:") public static void setActivityIndicatorViewStyle(UIActivityIndicatorView __self__, Selector __cmd__, UIActivityIndicatorViewStyle activityIndicatorViewStyle) { __self__.setActivityIndicatorViewStyle(activityIndicatorViewStyle); }
        @Callback @BindSelector("color") public static UIColor getColor(UIActivityIndicatorView __self__, Selector __cmd__) { return __self__.getColor(); }
        @Callback @BindSelector("setColor:") public static void setColor(UIActivityIndicatorView __self__, Selector __cmd__, UIColor color) { __self__.setColor(color); }
        @Callback @BindSelector("hidesWhenStopped") public static boolean isHidesWhenStopped(UIActivityIndicatorView __self__, Selector __cmd__) { return __self__.isHidesWhenStopped(); }
        @Callback @BindSelector("setHidesWhenStopped:") public static void setHidesWhenStopped(UIActivityIndicatorView __self__, Selector __cmd__, boolean hidesWhenStopped) { __self__.setHidesWhenStopped(hidesWhenStopped); }
        @Callback @BindSelector("isAnimating") public static boolean isAnimating(UIActivityIndicatorView __self__, Selector __cmd__) { return __self__.isAnimating(); }
        @Callback @BindSelector("startAnimating") public static void startAnimating(UIActivityIndicatorView __self__, Selector __cmd__) { __self__.startAnimating(); }
        @Callback @BindSelector("stopAnimating") public static void stopAnimating(UIActivityIndicatorView __self__, Selector __cmd__) { __self__.stopAnimating(); }
    }
    /*</callbacks>*/

}
