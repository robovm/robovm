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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html">UISwitch Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISwitch /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithFrame(UISwitch __self__, Selector __cmd__, CGRect frame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instm/UISwitch/initWithFrame:">- (id)initWithFrame:(CGRect)frame</a>
     * @since Available in iOS 2.0 and later.
     */
    public UISwitch(CGRect frame) {
        super((SkipInit) null);
        objc_initWithFrame(this, initWithFrame$, frame);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/offImage">@property(nonatomic, retain) UIImage *offImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("offImage") public native UIImage getOffImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/offImage">@property(nonatomic, retain) UIImage *offImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setOffImage:") public native void setOffImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/on">@property(nonatomic, getter=isOn) BOOL on</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isOn") public native boolean isOn();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/on">@property(nonatomic, getter=isOn) BOOL on</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setOn:") public native void setOn(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onImage">@property(nonatomic, retain) UIImage *onImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("onImage") public native UIImage getOnImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onImage">@property(nonatomic, retain) UIImage *onImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setOnImage:") public native void setOnImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onTintColor">@property(nonatomic, retain) UIColor *onTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("onTintColor") public native UIColor getOnTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onTintColor">@property(nonatomic, retain) UIColor *onTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setOnTintColor:") public native void setOnTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("thumbTintColor") public native UIColor getThumbTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setThumbTintColor:") public native void setThumbTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setOn$animated$ = Selector.register("setOn:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setOn(UISwitch __self__, Selector __cmd__, boolean on, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setOnSuper(ObjCSuper __super__, UISwitch __self__, Selector __cmd__, boolean on, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instm/UISwitch/setOn:animated:">- (void)setOn:(BOOL)on animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setOn(boolean on, boolean animated) {
        if (customClass) { objc_setOnSuper(getSuper(), this, setOn$animated$, on, animated); } else { objc_setOn(this, setOn$animated$, on, animated); }
    }
    /*</methods>*/

}
