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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIColor /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIColor /*</name>*/.class);
    }

    /*<constructors>*/
    public UIColor() {}
    @Bind("initWithCGColor:") public UIColor(@Type("CGColorRef") CGColor cgColor) {}
    @Bind("initWithCIColor:") public UIColor(@Type("CIColor *") CIColor ciColor) {}
    @Bind("initWithRed:green:blue:alpha:") public UIColor(@Type("CGFloat") float red, @Type("CGFloat") float green, @Type("CGFloat") float blue, @Type("CGFloat") float alpha) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("CGColor") public native @Type("CGColorRef") CGColor getCGColor();
    @Bind("CIColor") public native @Type("CIColor *") CIColor getCIColor();
    /*</properties>*/
    /*<methods>*/
    @Bind("blackColor") public native static @Type("UIColor *") UIColor blackColor();
    @Bind("blueColor") public native static @Type("UIColor *") UIColor blueColor();
    @Bind("brownColor") public native static @Type("UIColor *") UIColor brownColor();
    @Bind("clearColor") public native static @Type("UIColor *") UIColor clearColor();
    @Bind("cyanColor") public native static @Type("UIColor *") UIColor cyanColor();
    @Bind("darkGrayColor") public native static @Type("UIColor *") UIColor darkGrayColor();
    @Bind("darkTextColor") public native static @Type("UIColor *") UIColor darkTextColor();
    @Bind("colorWithCGColor:") public native static @Type("UIColor *") UIColor fromCGColor(@Type("CGColorRef") CGColor cgColor);
    @Bind("colorWithCIColor:") public native static @Type("UIColor *") UIColor fromCIColor(@Type("CIColor *") CIColor ciColor);
    @Bind("colorWithHue:saturation:brightness:alpha:") public native static @Type("UIColor *") UIColor fromHSBA(@Type("CGFloat") float hue, @Type("CGFloat") float saturation, @Type("CGFloat") float brightness, @Type("CGFloat") float alpha);
    @Bind("colorWithPatternImage:") public native static @Type("UIColor *") UIColor fromPatternImage(@Type("UIImage *") UIImage image);
    @Bind("colorWithRed:green:blue:alpha:") public native static @Type("UIColor *") UIColor fromRGBA(@Type("CGFloat") float red, @Type("CGFloat") float green, @Type("CGFloat") float blue, @Type("CGFloat") float alpha);
    @Bind("colorWithWhite:alpha:") public native static @Type("UIColor *") UIColor fromWhiteAlpha(@Type("CGFloat") float white, @Type("CGFloat") float alpha);
    @Bind("grayColor") public native static @Type("UIColor *") UIColor grayColor();
    @Bind("greenColor") public native static @Type("UIColor *") UIColor greenColor();
    @Bind("groupTableViewBackgroundColor") public native static @Type("UIColor *") UIColor groupTableViewBackgroundColor();
    @Bind("lightGrayColor") public native static @Type("UIColor *") UIColor lightGrayColor();
    @Bind("lightTextColor") public native static @Type("UIColor *") UIColor lightTextColor();
    @Bind("magentaColor") public native static @Type("UIColor *") UIColor magentaColor();
    @Bind("orangeColor") public native static @Type("UIColor *") UIColor orangeColor();
    @Bind("purpleColor") public native static @Type("UIColor *") UIColor purpleColor();
    @Bind("redColor") public native static @Type("UIColor *") UIColor redColor();
    @Bind("scrollViewTexturedBackgroundColor") public native static @Type("UIColor *") UIColor scrollViewTexturedBackgroundColor();
    @Bind("underPageBackgroundColor") public native static @Type("UIColor *") UIColor underPageBackgroundColor();
    @Bind("viewFlipsideBackgroundColor") public native static @Type("UIColor *") UIColor viewFlipsideBackgroundColor();
    @Bind("whiteColor") public native static @Type("UIColor *") UIColor whiteColor();
    @Bind("yellowColor") public native static @Type("UIColor *") UIColor yellowColor();
    @Bind("colorWithAlphaComponent:") public native @Type("UIColor *") UIColor colorWithAlpha(@Type("CGFloat") float alpha);
    @Bind("getHue:saturation:brightness:alpha:") public native @Type("BOOL") boolean getHSBA(@Type("CGFloat *") FloatPtr hue, @Type("CGFloat *") FloatPtr saturation, @Type("CGFloat *") FloatPtr brightness, @Type("CGFloat *") FloatPtr alpha);
    @Bind("getRed:green:blue:alpha:") public native @Type("BOOL") boolean getRGBA(@Type("CGFloat *") FloatPtr red, @Type("CGFloat *") FloatPtr green, @Type("CGFloat *") FloatPtr blue, @Type("CGFloat *") FloatPtr alpha);
    @Bind("getWhite:alpha:") public native @Type("BOOL") boolean getWhiteAlpha(@Type("CGFloat *") FloatPtr white, @Type("CGFloat *") FloatPtr alpha);
    @Bind("setFill") public native @Type("void") void setFill();
    @Bind("set") public native @Type("void") void setFillAndStroke();
    @Bind("setStroke") public native @Type("void") void setStroke();
    /*</methods>*/

}
