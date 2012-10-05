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

    /*<constructors>*/
    public UISwitch() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instm/UISwitch/initWithFrame:">- (id)initWithFrame:(CGRect)frame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithFrame:") public UISwitch(@Type("CGRect") CGRect frame) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/offImage">@property(nonatomic, retain) UIImage *offImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("offImage") public native @Type("UIImage *") UIImage getOffImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/offImage">@property(nonatomic, retain) UIImage *offImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setOffImage:") public native void setOffImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/on">@property(nonatomic, getter=isOn) BOOL on</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isOn") public native @Type("BOOL") boolean isOn();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/on">@property(nonatomic, getter=isOn) BOOL on</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setOn:") public native void setOn(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onImage">@property(nonatomic, retain) UIImage *onImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("onImage") public native @Type("UIImage *") UIImage getOnImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onImage">@property(nonatomic, retain) UIImage *onImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setOnImage:") public native void setOnImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onTintColor">@property(nonatomic, retain) UIColor *onTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("onTintColor") public native @Type("UIColor *") UIColor getOnTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/onTintColor">@property(nonatomic, retain) UIColor *onTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setOnTintColor:") public native void setOnTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("thumbTintColor") public native @Type("UIColor *") UIColor getThumbTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setThumbTintColor:") public native void setThumbTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instp/UISwitch/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISwitch_Class/Reference/Reference.html#//apple_ref/occ/instm/UISwitch/setOn:animated:">- (void)setOn:(BOOL)on animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setOn:animated:") public native @Type("void") void setOn(@Type("BOOL") boolean on, @Type("BOOL") boolean animated);
    /*</methods>*/

}
