/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISwitch/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UISwitchPtr extends Ptr<UISwitch, UISwitchPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISwitch.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UISwitch() {}
    protected UISwitch(SkipInit skipInit) { super(skipInit); }
    public UISwitch(@ByVal CGRect frame) { super((SkipInit) null); initObject(initWithFrame$(frame)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "onTintColor")
    public native UIColor getOnTintColor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setOnTintColor:")
    public native void setOnTintColor(UIColor v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "thumbTintColor")
    public native UIColor getThumbTintColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setThumbTintColor:")
    public native void setThumbTintColor(UIColor v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "onImage")
    public native UIImage getOnImage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setOnImage:")
    public native void setOnImage(UIImage v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "offImage")
    public native UIImage getOffImage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setOffImage:")
    public native void setOffImage(UIImage v);
    @Property(selector = "isOn")
    public native boolean isOn();
    @Property(selector = "setOn:")
    public native void setOn(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFrame:")
    protected native @Pointer long initWithFrame$(@ByVal CGRect frame);
    @Method(selector = "setOn:animated:")
    public native void setOn(boolean on, boolean animated);
    /*</methods>*/
}
