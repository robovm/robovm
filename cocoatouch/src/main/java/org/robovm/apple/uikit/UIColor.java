/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIColor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIColorPtr extends Ptr<UIColor, UIColorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIColor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIColor() {}
    protected UIColor(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "CGColor")
    public native CGColor getCGColor();
    @Property(selector = "CIColor")
    public native CIColor getCIColor();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRed:green:blue:alpha:")
    public native UIColor initWithRed$green$blue$alpha$(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Method(selector = "initWithCGColor:")
    public native UIColor initWithCGColor$(CGColor cgColor);
    @Method(selector = "initWithCIColor:")
    public native UIColor initWithCIColor$(CIColor ciColor);
    @Method(selector = "set")
    public native void setFillAndStroke();
    @Method(selector = "setFill")
    public native void setFill();
    @Method(selector = "setStroke")
    public native void setStroke();
    @Method(selector = "getWhite:alpha:")
    public native boolean getWhiteAlpha(MachineSizedFloatPtr white, MachineSizedFloatPtr alpha);
    @Method(selector = "getHue:saturation:brightness:alpha:")
    public native boolean getHSBA(MachineSizedFloatPtr hue, MachineSizedFloatPtr saturation, MachineSizedFloatPtr brightness, MachineSizedFloatPtr alpha);
    @Method(selector = "getRed:green:blue:alpha:")
    public native boolean getRGBA(MachineSizedFloatPtr red, MachineSizedFloatPtr green, MachineSizedFloatPtr blue, MachineSizedFloatPtr alpha);
    @Method(selector = "colorWithAlphaComponent:")
    public native UIColor colorWithAlpha(@MachineSizedFloat double alpha);
    @Method(selector = "CGColor")
    public native CGColor CGColor();
    @Method(selector = "colorWithWhite:alpha:")
    public static native UIColor fromWhiteAlpha(@MachineSizedFloat double white, @MachineSizedFloat double alpha);
    @Method(selector = "colorWithHue:saturation:brightness:alpha:")
    public static native UIColor fromHSBA(@MachineSizedFloat double hue, @MachineSizedFloat double saturation, @MachineSizedFloat double brightness, @MachineSizedFloat double alpha);
    @Method(selector = "colorWithRed:green:blue:alpha:")
    public static native UIColor fromRGBA(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Method(selector = "colorWithCGColor:")
    public static native UIColor fromCGColor(CGColor cgColor);
    @Method(selector = "colorWithPatternImage:")
    public static native UIColor fromPatternImage(UIImage image);
    @Method(selector = "colorWithCIColor:")
    public static native UIColor fromCIColor(CIColor ciColor);
    @Method(selector = "blackColor")
    public static native UIColor blackColor();
    @Method(selector = "darkGrayColor")
    public static native UIColor darkGrayColor();
    @Method(selector = "lightGrayColor")
    public static native UIColor lightGrayColor();
    @Method(selector = "whiteColor")
    public static native UIColor whiteColor();
    @Method(selector = "grayColor")
    public static native UIColor grayColor();
    @Method(selector = "redColor")
    public static native UIColor redColor();
    @Method(selector = "greenColor")
    public static native UIColor greenColor();
    @Method(selector = "blueColor")
    public static native UIColor blueColor();
    @Method(selector = "cyanColor")
    public static native UIColor cyanColor();
    @Method(selector = "yellowColor")
    public static native UIColor yellowColor();
    @Method(selector = "magentaColor")
    public static native UIColor magentaColor();
    @Method(selector = "orangeColor")
    public static native UIColor orangeColor();
    @Method(selector = "purpleColor")
    public static native UIColor purpleColor();
    @Method(selector = "brownColor")
    public static native UIColor brownColor();
    @Method(selector = "clearColor")
    public static native UIColor clearColor();
    @Method(selector = "lightTextColor")
    public static native UIColor lightTextColor();
    @Method(selector = "darkTextColor")
    public static native UIColor darkTextColor();
    @Method(selector = "groupTableViewBackgroundColor")
    public static native UIColor groupTableViewBackgroundColor();
    @Method(selector = "viewFlipsideBackgroundColor")
    public static native UIColor viewFlipsideBackgroundColor();
    @Method(selector = "scrollViewTexturedBackgroundColor")
    public static native UIColor scrollViewTexturedBackgroundColor();
    @Method(selector = "underPageBackgroundColor")
    public static native UIColor underPageBackgroundColor();
    /*</methods>*/
}
