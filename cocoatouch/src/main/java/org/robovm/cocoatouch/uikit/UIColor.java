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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html">UIColor Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIColor /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIColor /*</name>*/.class);
    }

    /*<constructors>*/
    public UIColor() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/initWithCGColor:">- (UIColor *)initWithCGColor:(CGColorRef)cgColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithCGColor:") public UIColor(@Type("CGColorRef") CGColor cgColor) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/initWithCIColor:">- (UIColor *)initWithCIColor:(CIColor *)ciColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("initWithCIColor:") public UIColor(@Type("CIColor *") CIColor ciColor) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/initWithRed:green:blue:alpha:">- (UIColor *)initWithRed:(CGFloat)red green:(CGFloat)green blue:(CGFloat)blue alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithRed:green:blue:alpha:") public UIColor(@Type("CGFloat") float red, @Type("CGFloat") float green, @Type("CGFloat") float blue, @Type("CGFloat") float alpha) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instp/UIColor/CGColor">@property(nonatomic, readonly) CGColorRef CGColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("CGColor") public native @Type("CGColorRef") CGColor getCGColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instp/UIColor/CIColor">@property(nonatomic, readonly) CIColor *CIColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("CIColor") public native @Type("CIColor *") CIColor getCIColor();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/blackColor">+ (UIColor *)blackColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("blackColor") public native static @Type("UIColor *") UIColor blackColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/blueColor">+ (UIColor *)blueColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("blueColor") public native static @Type("UIColor *") UIColor blueColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/brownColor">+ (UIColor *)brownColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("brownColor") public native static @Type("UIColor *") UIColor brownColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/clearColor">+ (UIColor *)clearColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clearColor") public native static @Type("UIColor *") UIColor clearColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/cyanColor">+ (UIColor *)cyanColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cyanColor") public native static @Type("UIColor *") UIColor cyanColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/darkGrayColor">+ (UIColor *)darkGrayColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("darkGrayColor") public native static @Type("UIColor *") UIColor darkGrayColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/darkTextColor">+ (UIColor *)darkTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("darkTextColor") public native static @Type("UIColor *") UIColor darkTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithCGColor:">+ (UIColor *)colorWithCGColor:(CGColorRef)cgColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("colorWithCGColor:") public native static @Type("UIColor *") UIColor fromCGColor(@Type("CGColorRef") CGColor cgColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithCIColor:">+ (UIColor *)colorWithCIColor:(CIColor *)ciColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("colorWithCIColor:") public native static @Type("UIColor *") UIColor fromCIColor(@Type("CIColor *") CIColor ciColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithHue:saturation:brightness:alpha:">+ (UIColor *)colorWithHue:(CGFloat)hue saturation:(CGFloat)saturation brightness:(CGFloat)brightness alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("colorWithHue:saturation:brightness:alpha:") public native static @Type("UIColor *") UIColor fromHSBA(@Type("CGFloat") float hue, @Type("CGFloat") float saturation, @Type("CGFloat") float brightness, @Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithPatternImage:">+ (UIColor *)colorWithPatternImage:(UIImage *)image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("colorWithPatternImage:") public native static @Type("UIColor *") UIColor fromPatternImage(@Type("UIImage *") UIImage image);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithRed:green:blue:alpha:">+ (UIColor *)colorWithRed:(CGFloat)red green:(CGFloat)green blue:(CGFloat)blue alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("colorWithRed:green:blue:alpha:") public native static @Type("UIColor *") UIColor fromRGBA(@Type("CGFloat") float red, @Type("CGFloat") float green, @Type("CGFloat") float blue, @Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithWhite:alpha:">+ (UIColor *)colorWithWhite:(CGFloat)white alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("colorWithWhite:alpha:") public native static @Type("UIColor *") UIColor fromWhiteAlpha(@Type("CGFloat") float white, @Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/grayColor">+ (UIColor *)grayColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("grayColor") public native static @Type("UIColor *") UIColor grayColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/greenColor">+ (UIColor *)greenColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("greenColor") public native static @Type("UIColor *") UIColor greenColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/groupTableViewBackgroundColor">+ (UIColor *)groupTableViewBackgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("groupTableViewBackgroundColor") public native static @Type("UIColor *") UIColor groupTableViewBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/lightGrayColor">+ (UIColor *)lightGrayColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("lightGrayColor") public native static @Type("UIColor *") UIColor lightGrayColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/lightTextColor">+ (UIColor *)lightTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("lightTextColor") public native static @Type("UIColor *") UIColor lightTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/magentaColor">+ (UIColor *)magentaColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("magentaColor") public native static @Type("UIColor *") UIColor magentaColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/orangeColor">+ (UIColor *)orangeColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("orangeColor") public native static @Type("UIColor *") UIColor orangeColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/purpleColor">+ (UIColor *)purpleColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("purpleColor") public native static @Type("UIColor *") UIColor purpleColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/redColor">+ (UIColor *)redColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("redColor") public native static @Type("UIColor *") UIColor redColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/scrollViewTexturedBackgroundColor">+ (UIColor *)scrollViewTexturedBackgroundColor</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("scrollViewTexturedBackgroundColor") public native static @Type("UIColor *") UIColor scrollViewTexturedBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/underPageBackgroundColor">+ (UIColor *)underPageBackgroundColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("underPageBackgroundColor") public native static @Type("UIColor *") UIColor underPageBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/viewFlipsideBackgroundColor">+ (UIColor *)viewFlipsideBackgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewFlipsideBackgroundColor") public native static @Type("UIColor *") UIColor viewFlipsideBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/whiteColor">+ (UIColor *)whiteColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("whiteColor") public native static @Type("UIColor *") UIColor whiteColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/yellowColor">+ (UIColor *)yellowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("yellowColor") public native static @Type("UIColor *") UIColor yellowColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/colorWithAlphaComponent:">- (UIColor *)colorWithAlphaComponent:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("colorWithAlphaComponent:") public native @Type("UIColor *") UIColor colorWithAlpha(@Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/getHue:saturation:brightness:alpha:">- (BOOL)getHue:(CGFloat *)hue saturation:(CGFloat *)saturation brightness:(CGFloat *)brightness alpha:(CGFloat *)alpha</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("getHue:saturation:brightness:alpha:") public native @Type("BOOL") boolean getHSBA(@Type("CGFloat *") FloatPtr hue, @Type("CGFloat *") FloatPtr saturation, @Type("CGFloat *") FloatPtr brightness, @Type("CGFloat *") FloatPtr alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/getRed:green:blue:alpha:">- (BOOL)getRed:(CGFloat *)red green:(CGFloat *)green blue:(CGFloat *)blue alpha:(CGFloat *)alpha</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("getRed:green:blue:alpha:") public native @Type("BOOL") boolean getRGBA(@Type("CGFloat *") FloatPtr red, @Type("CGFloat *") FloatPtr green, @Type("CGFloat *") FloatPtr blue, @Type("CGFloat *") FloatPtr alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/getWhite:alpha:">- (BOOL)getWhite:(CGFloat *)white alpha:(CGFloat *)alpha</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("getWhite:alpha:") public native @Type("BOOL") boolean getWhiteAlpha(@Type("CGFloat *") FloatPtr white, @Type("CGFloat *") FloatPtr alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/setFill">- (void)setFill</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFill") public native @Type("void") void setFill();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/set">- (void)set</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("set") public native @Type("void") void setFillAndStroke();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/setStroke">- (void)setStroke</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStroke") public native @Type("void") void setStroke();
    /*</methods>*/

}
