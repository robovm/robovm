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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
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
    public UIColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha) { super((SkipInit) null); initObject(initWithRed$green$blue$alpha$(red, green, blue, alpha)); }
    public UIColor(CGColor cgColor) { super((SkipInit) null); initObject(initWithCGColor$(cgColor)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UIColor(CIColor ciColor) { super((SkipInit) null); initObject(initWithCIColor$(ciColor)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "CGColor")
    public native CGColor getCGColor();
    @Property(selector = "CIColor")
    public native CIColor getCIColor();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public boolean getWhiteAlpha(double[] whiteAlpha) {
        if (whiteAlpha == null) {
            throw new NullPointerException("whiteAlpha");
        }
        if (whiteAlpha.length != 2) {
            throw new IllegalArgumentException("whiteAlpha.length != 2");
        }
        MachineSizedFloatPtr white = new MachineSizedFloatPtr();
        MachineSizedFloatPtr alpha = new MachineSizedFloatPtr();
        boolean ret = getWhiteAlpha(white, alpha);
        whiteAlpha[0] = white.get();
        whiteAlpha[1] = alpha.get();
        return ret;
    }

    public boolean getHSBA(double[] hsba) {
        if (hsba == null) {
            throw new NullPointerException("hsba");
        }
        if (hsba.length != 4) {
            throw new IllegalArgumentException("hsba.length != 4");
        }
        MachineSizedFloatPtr hue = new MachineSizedFloatPtr();
        MachineSizedFloatPtr saturation = new MachineSizedFloatPtr();
        MachineSizedFloatPtr brightness = new MachineSizedFloatPtr();
        MachineSizedFloatPtr alpha = new MachineSizedFloatPtr();
        boolean ret = getHSBA(hue, saturation, brightness, alpha);
        hsba[0] = hue.get();
        hsba[1] = saturation.get();
        hsba[2] = brightness.get();
        hsba[3] = alpha.get();
        return ret;
    }

    public boolean getRGBA(double[] rgba) {
        if (rgba == null) {
            throw new NullPointerException("rgba");
        }
        if (rgba.length != 4) {
            throw new IllegalArgumentException("rgba.length != 4");
        }
        MachineSizedFloatPtr red = new MachineSizedFloatPtr();
        MachineSizedFloatPtr green = new MachineSizedFloatPtr();
        MachineSizedFloatPtr blue = new MachineSizedFloatPtr();
        MachineSizedFloatPtr alpha = new MachineSizedFloatPtr();
        boolean ret = getRGBA(red, green, blue, alpha);
        rgba[0] = red.get();
        rgba[1] = green.get();
        rgba[2] = blue.get();
        rgba[3] = alpha.get();
        return ret;
    }
    
    /*<methods>*/
    @Method(selector = "initWithRed:green:blue:alpha:")
    protected native @Pointer long initWithRed$green$blue$alpha$(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Method(selector = "initWithCGColor:")
    protected native @Pointer long initWithCGColor$(CGColor cgColor);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithCIColor:")
    protected native @Pointer long initWithCIColor$(CIColor ciColor);
    @Method(selector = "set")
    public native void setFillAndStroke();
    @Method(selector = "setFill")
    public native void setFill();
    @Method(selector = "setStroke")
    public native void setStroke();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "getWhite:alpha:")
    protected native boolean getWhiteAlpha(MachineSizedFloatPtr white, MachineSizedFloatPtr alpha);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "getHue:saturation:brightness:alpha:")
    protected native boolean getHSBA(MachineSizedFloatPtr hue, MachineSizedFloatPtr saturation, MachineSizedFloatPtr brightness, MachineSizedFloatPtr alpha);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "getRed:green:blue:alpha:")
    protected native boolean getRGBA(MachineSizedFloatPtr red, MachineSizedFloatPtr green, MachineSizedFloatPtr blue, MachineSizedFloatPtr alpha);
    @Method(selector = "colorWithAlphaComponent:")
    public native UIColor addAlpha(@MachineSizedFloat double alpha);
    @Method(selector = "colorWithWhite:alpha:")
    public static native UIColor createFromWhiteAlpha(@MachineSizedFloat double white, @MachineSizedFloat double alpha);
    @Method(selector = "colorWithHue:saturation:brightness:alpha:")
    public static native UIColor createFromHSBA(@MachineSizedFloat double hue, @MachineSizedFloat double saturation, @MachineSizedFloat double brightness, @MachineSizedFloat double alpha);
    @Method(selector = "colorWithRed:green:blue:alpha:")
    public static native UIColor createFromRGBA(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Method(selector = "colorWithCGColor:")
    public static native UIColor createFromCGColor(CGColor cgColor);
    @Method(selector = "colorWithPatternImage:")
    public static native UIColor createFromPatternImage(UIImage image);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "colorWithCIColor:")
    public static native UIColor createFromCIColor(CIColor ciColor);
    @Method(selector = "blackColor")
    public static native UIColor colorBlack();
    @Method(selector = "darkGrayColor")
    public static native UIColor colorDarkGray();
    @Method(selector = "lightGrayColor")
    public static native UIColor colorLightGray();
    @Method(selector = "whiteColor")
    public static native UIColor colorWhite();
    @Method(selector = "grayColor")
    public static native UIColor colorGray();
    @Method(selector = "redColor")
    public static native UIColor colorRed();
    @Method(selector = "greenColor")
    public static native UIColor colorGreen();
    @Method(selector = "blueColor")
    public static native UIColor colorBlue();
    @Method(selector = "cyanColor")
    public static native UIColor colorCyan();
    @Method(selector = "yellowColor")
    public static native UIColor colorYellow();
    @Method(selector = "magentaColor")
    public static native UIColor colorMagenta();
    @Method(selector = "orangeColor")
    public static native UIColor colorOrange();
    @Method(selector = "purpleColor")
    public static native UIColor colorPurple();
    @Method(selector = "brownColor")
    public static native UIColor colorBrown();
    @Method(selector = "clearColor")
    public static native UIColor colorClear();
    @Method(selector = "lightTextColor")
    public static native UIColor colorLightText();
    @Method(selector = "darkTextColor")
    public static native UIColor colorDarkText();
    @Method(selector = "groupTableViewBackgroundColor")
    public static native UIColor colorGroupTableViewBackground();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "viewFlipsideBackgroundColor")
    public static native UIColor colorViewFlipsideBackground();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "scrollViewTexturedBackgroundColor")
    public static native UIColor colorScrollViewTexturedBackground();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "underPageBackgroundColor")
    public static native UIColor colorUnderPageBackground();
    /*</methods>*/
}
