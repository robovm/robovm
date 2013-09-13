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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html">UISwitch Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISwitch /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISwitch /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISwitch /*</name>*/.class);

    /*<constructors>*/
    protected UISwitch(SkipInit skipInit) { super(skipInit); }
    public UISwitch() {}
    
    private static final Selector initWithFrame$ = Selector.register("initWithFrame:");
    @Bridge private native static @Pointer long objc_initWithFrame(UISwitch __self__, Selector __cmd__, @ByVal CGRect frame);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instm/UISwitch/initWithFrame:">- (id)initWithFrame:(CGRect)frame</a>
     * @since Available in iOS 2.0 and later.
     */
    public UISwitch(CGRect frame) {
        super((SkipInit) null);
        initObject(objc_initWithFrame(this, initWithFrame$, frame));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector offImage = Selector.register("offImage");
    @Bridge private native static UIImage objc_getOffImage(UISwitch __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getOffImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/offImage">@property(nonatomic, retain) UIImage *offImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getOffImage() {
        if (customClass) { return objc_getOffImageSuper(getSuper(), offImage); } else { return objc_getOffImage(this, offImage); }
    }
    
    private static final Selector setOffImage$ = Selector.register("setOffImage:");
    @Bridge private native static void objc_setOffImage(UISwitch __self__, Selector __cmd__, UIImage offImage);
    @Bridge private native static void objc_setOffImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage offImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/offImage">@property(nonatomic, retain) UIImage *offImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setOffImage(UIImage offImage) {
        if (customClass) { objc_setOffImageSuper(getSuper(), setOffImage$, offImage); } else { objc_setOffImage(this, setOffImage$, offImage); }
    }
    
    private static final Selector isOn = Selector.register("isOn");
    @Bridge private native static boolean objc_isOn(UISwitch __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isOnSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/on">@property(nonatomic, getter=isOn) BOOL on</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isOn() {
        if (customClass) { return objc_isOnSuper(getSuper(), isOn); } else { return objc_isOn(this, isOn); }
    }
    
    private static final Selector setOn$ = Selector.register("setOn:");
    @Bridge private native static void objc_setOn(UISwitch __self__, Selector __cmd__, boolean on);
    @Bridge private native static void objc_setOnSuper(ObjCSuper __super__, Selector __cmd__, boolean on);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/on">@property(nonatomic, getter=isOn) BOOL on</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setOn(boolean on) {
        if (customClass) { objc_setOnSuper(getSuper(), setOn$, on); } else { objc_setOn(this, setOn$, on); }
    }
    
    private static final Selector onImage = Selector.register("onImage");
    @Bridge private native static UIImage objc_getOnImage(UISwitch __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getOnImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onImage">@property(nonatomic, retain) UIImage *onImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getOnImage() {
        if (customClass) { return objc_getOnImageSuper(getSuper(), onImage); } else { return objc_getOnImage(this, onImage); }
    }
    
    private static final Selector setOnImage$ = Selector.register("setOnImage:");
    @Bridge private native static void objc_setOnImage(UISwitch __self__, Selector __cmd__, UIImage onImage);
    @Bridge private native static void objc_setOnImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage onImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onImage">@property(nonatomic, retain) UIImage *onImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setOnImage(UIImage onImage) {
        if (customClass) { objc_setOnImageSuper(getSuper(), setOnImage$, onImage); } else { objc_setOnImage(this, setOnImage$, onImage); }
    }
    
    private static final Selector onTintColor = Selector.register("onTintColor");
    @Bridge private native static UIColor objc_getOnTintColor(UISwitch __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getOnTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onTintColor">@property(nonatomic, retain) UIColor *onTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getOnTintColor() {
        if (customClass) { return objc_getOnTintColorSuper(getSuper(), onTintColor); } else { return objc_getOnTintColor(this, onTintColor); }
    }
    
    private static final Selector setOnTintColor$ = Selector.register("setOnTintColor:");
    @Bridge private native static void objc_setOnTintColor(UISwitch __self__, Selector __cmd__, UIColor onTintColor);
    @Bridge private native static void objc_setOnTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor onTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onTintColor">@property(nonatomic, retain) UIColor *onTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setOnTintColor(UIColor onTintColor) {
        if (customClass) { objc_setOnTintColorSuper(getSuper(), setOnTintColor$, onTintColor); } else { objc_setOnTintColor(this, setOnTintColor$, onTintColor); }
    }
    
    private static final Selector thumbTintColor = Selector.register("thumbTintColor");
    @Bridge private native static UIColor objc_getThumbTintColor(UISwitch __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getThumbTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getThumbTintColor() {
        if (customClass) { return objc_getThumbTintColorSuper(getSuper(), thumbTintColor); } else { return objc_getThumbTintColor(this, thumbTintColor); }
    }
    
    private static final Selector setThumbTintColor$ = Selector.register("setThumbTintColor:");
    @Bridge private native static void objc_setThumbTintColor(UISwitch __self__, Selector __cmd__, UIColor thumbTintColor);
    @Bridge private native static void objc_setThumbTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor thumbTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setThumbTintColor(UIColor thumbTintColor) {
        if (customClass) { objc_setThumbTintColorSuper(getSuper(), setThumbTintColor$, thumbTintColor); } else { objc_setThumbTintColor(this, setThumbTintColor$, thumbTintColor); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UISwitch __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UISwitch __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setOn$animated$ = Selector.register("setOn:animated:");
    @Bridge private native static void objc_setOn(UISwitch __self__, Selector __cmd__, boolean on, boolean animated);
    @Bridge private native static void objc_setOnSuper(ObjCSuper __super__, Selector __cmd__, boolean on, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instm/UISwitch/setOn:animated:">- (void)setOn:(BOOL)on animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setOn(boolean on, boolean animated) {
        if (customClass) { objc_setOnSuper(getSuper(), setOn$animated$, on, animated); } else { objc_setOn(this, setOn$animated$, on, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("offImage") public static UIImage getOffImage(UISwitch __self__, Selector __cmd__) { return __self__.getOffImage(); }
        @Callback @BindSelector("setOffImage:") public static void setOffImage(UISwitch __self__, Selector __cmd__, UIImage offImage) { __self__.setOffImage(offImage); }
        @Callback @BindSelector("isOn") public static boolean isOn(UISwitch __self__, Selector __cmd__) { return __self__.isOn(); }
        @Callback @BindSelector("setOn:") public static void setOn(UISwitch __self__, Selector __cmd__, boolean on) { __self__.setOn(on); }
        @Callback @BindSelector("onImage") public static UIImage getOnImage(UISwitch __self__, Selector __cmd__) { return __self__.getOnImage(); }
        @Callback @BindSelector("setOnImage:") public static void setOnImage(UISwitch __self__, Selector __cmd__, UIImage onImage) { __self__.setOnImage(onImage); }
        @Callback @BindSelector("onTintColor") public static UIColor getOnTintColor(UISwitch __self__, Selector __cmd__) { return __self__.getOnTintColor(); }
        @Callback @BindSelector("setOnTintColor:") public static void setOnTintColor(UISwitch __self__, Selector __cmd__, UIColor onTintColor) { __self__.setOnTintColor(onTintColor); }
        @Callback @BindSelector("thumbTintColor") public static UIColor getThumbTintColor(UISwitch __self__, Selector __cmd__) { return __self__.getThumbTintColor(); }
        @Callback @BindSelector("setThumbTintColor:") public static void setThumbTintColor(UISwitch __self__, Selector __cmd__, UIColor thumbTintColor) { __self__.setThumbTintColor(thumbTintColor); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UISwitch __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UISwitch __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("setOn:animated:") public static void setOn(UISwitch __self__, Selector __cmd__, boolean on, boolean animated) { __self__.setOn(on, animated); }
    }
    /*</callbacks>*/

}
