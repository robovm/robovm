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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html">UIColor Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIColor /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIColor /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIColor /*</name>*/.class);

    /*<constructors>*/
    protected UIColor(SkipInit skipInit) { super(skipInit); }
    public UIColor() {}
    
    private static final Selector initWithCGColor$ = Selector.register("initWithCGColor:");
    @Bridge private native static @Pointer long objc_initWithCGColor(UIColor __self__, Selector __cmd__, CGColor cgColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/initWithCGColor:">- (UIColor *)initWithCGColor:(CGColorRef)cgColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor(CGColor cgColor) {
        super((SkipInit) null);
        initObject(objc_initWithCGColor(this, initWithCGColor$, cgColor));
    }
    
    private static final Selector initWithCIColor$ = Selector.register("initWithCIColor:");
    @Bridge private native static @Pointer long objc_initWithCIColor(UIColor __self__, Selector __cmd__, CIColor ciColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/initWithCIColor:">- (UIColor *)initWithCIColor:(CIColor *)ciColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor(CIColor ciColor) {
        super((SkipInit) null);
        initObject(objc_initWithCIColor(this, initWithCIColor$, ciColor));
    }
    
    private static final Selector initWithRed$green$blue$alpha$ = Selector.register("initWithRed:green:blue:alpha:");
    @Bridge private native static @Pointer long objc_initWithRed(UIColor __self__, Selector __cmd__, float red, float green, float blue, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/initWithRed:green:blue:alpha:">- (UIColor *)initWithRed:(CGFloat)red green:(CGFloat)green blue:(CGFloat)blue alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor(float red, float green, float blue, float alpha) {
        super((SkipInit) null);
        initObject(objc_initWithRed(this, initWithRed$green$blue$alpha$, red, green, blue, alpha));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector CGColor = Selector.register("CGColor");
    @Bridge private native static CGColor objc_getCGColor(UIColor __self__, Selector __cmd__);
    @Bridge private native static CGColor objc_getCGColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instp/UIColor/CGColor">@property(nonatomic, readonly) CGColorRef CGColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGColor getCGColor() {
        if (customClass) { return objc_getCGColorSuper(getSuper(), CGColor); } else { return objc_getCGColor(this, CGColor); }
    }
    
    private static final Selector CIColor = Selector.register("CIColor");
    @Bridge private native static CIColor objc_getCIColor(UIColor __self__, Selector __cmd__);
    @Bridge private native static CIColor objc_getCIColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instp/UIColor/CIColor">@property(nonatomic, readonly) CIColor *CIColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public CIColor getCIColor() {
        if (customClass) { return objc_getCIColorSuper(getSuper(), CIColor); } else { return objc_getCIColor(this, CIColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector blackColor = Selector.register("blackColor");
    @Bridge private native static UIColor objc_blackColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/blackColor">+ (UIColor *)blackColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor blackColor() {
        return objc_blackColor(objCClass, blackColor);
    }
    
    private static final Selector blueColor = Selector.register("blueColor");
    @Bridge private native static UIColor objc_blueColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/blueColor">+ (UIColor *)blueColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor blueColor() {
        return objc_blueColor(objCClass, blueColor);
    }
    
    private static final Selector brownColor = Selector.register("brownColor");
    @Bridge private native static UIColor objc_brownColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/brownColor">+ (UIColor *)brownColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor brownColor() {
        return objc_brownColor(objCClass, brownColor);
    }
    
    private static final Selector clearColor = Selector.register("clearColor");
    @Bridge private native static UIColor objc_clearColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/clearColor">+ (UIColor *)clearColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor clearColor() {
        return objc_clearColor(objCClass, clearColor);
    }
    
    private static final Selector cyanColor = Selector.register("cyanColor");
    @Bridge private native static UIColor objc_cyanColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/cyanColor">+ (UIColor *)cyanColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor cyanColor() {
        return objc_cyanColor(objCClass, cyanColor);
    }
    
    private static final Selector darkGrayColor = Selector.register("darkGrayColor");
    @Bridge private native static UIColor objc_darkGrayColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/darkGrayColor">+ (UIColor *)darkGrayColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor darkGrayColor() {
        return objc_darkGrayColor(objCClass, darkGrayColor);
    }
    
    private static final Selector darkTextColor = Selector.register("darkTextColor");
    @Bridge private native static UIColor objc_darkTextColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/darkTextColor">+ (UIColor *)darkTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor darkTextColor() {
        return objc_darkTextColor(objCClass, darkTextColor);
    }
    
    private static final Selector colorWithCGColor$ = Selector.register("colorWithCGColor:");
    @Bridge private native static UIColor objc_fromCGColor(ObjCClass __self__, Selector __cmd__, CGColor cgColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithCGColor:">+ (UIColor *)colorWithCGColor:(CGColorRef)cgColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor fromCGColor(CGColor cgColor) {
        return objc_fromCGColor(objCClass, colorWithCGColor$, cgColor);
    }
    
    private static final Selector colorWithCIColor$ = Selector.register("colorWithCIColor:");
    @Bridge private native static UIColor objc_fromCIColor(ObjCClass __self__, Selector __cmd__, CIColor ciColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithCIColor:">+ (UIColor *)colorWithCIColor:(CIColor *)ciColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIColor fromCIColor(CIColor ciColor) {
        return objc_fromCIColor(objCClass, colorWithCIColor$, ciColor);
    }
    
    private static final Selector colorWithHue$saturation$brightness$alpha$ = Selector.register("colorWithHue:saturation:brightness:alpha:");
    @Bridge private native static UIColor objc_fromHSBA(ObjCClass __self__, Selector __cmd__, float hue, float saturation, float brightness, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithHue:saturation:brightness:alpha:">+ (UIColor *)colorWithHue:(CGFloat)hue saturation:(CGFloat)saturation brightness:(CGFloat)brightness alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor fromHSBA(float hue, float saturation, float brightness, float alpha) {
        return objc_fromHSBA(objCClass, colorWithHue$saturation$brightness$alpha$, hue, saturation, brightness, alpha);
    }
    
    private static final Selector colorWithPatternImage$ = Selector.register("colorWithPatternImage:");
    @Bridge private native static UIColor objc_fromPatternImage(ObjCClass __self__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithPatternImage:">+ (UIColor *)colorWithPatternImage:(UIImage *)image</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor fromPatternImage(UIImage image) {
        return objc_fromPatternImage(objCClass, colorWithPatternImage$, image);
    }
    
    private static final Selector colorWithRed$green$blue$alpha$ = Selector.register("colorWithRed:green:blue:alpha:");
    @Bridge private native static UIColor objc_fromRGBA(ObjCClass __self__, Selector __cmd__, float red, float green, float blue, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithRed:green:blue:alpha:">+ (UIColor *)colorWithRed:(CGFloat)red green:(CGFloat)green blue:(CGFloat)blue alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor fromRGBA(float red, float green, float blue, float alpha) {
        return objc_fromRGBA(objCClass, colorWithRed$green$blue$alpha$, red, green, blue, alpha);
    }
    
    private static final Selector colorWithWhite$alpha$ = Selector.register("colorWithWhite:alpha:");
    @Bridge private native static UIColor objc_fromWhiteAlpha(ObjCClass __self__, Selector __cmd__, float white, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/colorWithWhite:alpha:">+ (UIColor *)colorWithWhite:(CGFloat)white alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor fromWhiteAlpha(float white, float alpha) {
        return objc_fromWhiteAlpha(objCClass, colorWithWhite$alpha$, white, alpha);
    }
    
    private static final Selector grayColor = Selector.register("grayColor");
    @Bridge private native static UIColor objc_grayColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/grayColor">+ (UIColor *)grayColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor grayColor() {
        return objc_grayColor(objCClass, grayColor);
    }
    
    private static final Selector greenColor = Selector.register("greenColor");
    @Bridge private native static UIColor objc_greenColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/greenColor">+ (UIColor *)greenColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor greenColor() {
        return objc_greenColor(objCClass, greenColor);
    }
    
    private static final Selector groupTableViewBackgroundColor = Selector.register("groupTableViewBackgroundColor");
    @Bridge private native static UIColor objc_groupTableViewBackgroundColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/groupTableViewBackgroundColor">+ (UIColor *)groupTableViewBackgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor groupTableViewBackgroundColor() {
        return objc_groupTableViewBackgroundColor(objCClass, groupTableViewBackgroundColor);
    }
    
    private static final Selector lightGrayColor = Selector.register("lightGrayColor");
    @Bridge private native static UIColor objc_lightGrayColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/lightGrayColor">+ (UIColor *)lightGrayColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor lightGrayColor() {
        return objc_lightGrayColor(objCClass, lightGrayColor);
    }
    
    private static final Selector lightTextColor = Selector.register("lightTextColor");
    @Bridge private native static UIColor objc_lightTextColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/lightTextColor">+ (UIColor *)lightTextColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor lightTextColor() {
        return objc_lightTextColor(objCClass, lightTextColor);
    }
    
    private static final Selector magentaColor = Selector.register("magentaColor");
    @Bridge private native static UIColor objc_magentaColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/magentaColor">+ (UIColor *)magentaColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor magentaColor() {
        return objc_magentaColor(objCClass, magentaColor);
    }
    
    private static final Selector orangeColor = Selector.register("orangeColor");
    @Bridge private native static UIColor objc_orangeColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/orangeColor">+ (UIColor *)orangeColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor orangeColor() {
        return objc_orangeColor(objCClass, orangeColor);
    }
    
    private static final Selector purpleColor = Selector.register("purpleColor");
    @Bridge private native static UIColor objc_purpleColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/purpleColor">+ (UIColor *)purpleColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor purpleColor() {
        return objc_purpleColor(objCClass, purpleColor);
    }
    
    private static final Selector redColor = Selector.register("redColor");
    @Bridge private native static UIColor objc_redColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/redColor">+ (UIColor *)redColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor redColor() {
        return objc_redColor(objCClass, redColor);
    }
    
    private static final Selector scrollViewTexturedBackgroundColor = Selector.register("scrollViewTexturedBackgroundColor");
    @Bridge private native static UIColor objc_scrollViewTexturedBackgroundColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/scrollViewTexturedBackgroundColor">+ (UIColor *)scrollViewTexturedBackgroundColor</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIColor scrollViewTexturedBackgroundColor() {
        return objc_scrollViewTexturedBackgroundColor(objCClass, scrollViewTexturedBackgroundColor);
    }
    
    private static final Selector underPageBackgroundColor = Selector.register("underPageBackgroundColor");
    @Bridge private native static UIColor objc_underPageBackgroundColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/underPageBackgroundColor">+ (UIColor *)underPageBackgroundColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIColor underPageBackgroundColor() {
        return objc_underPageBackgroundColor(objCClass, underPageBackgroundColor);
    }
    
    private static final Selector viewFlipsideBackgroundColor = Selector.register("viewFlipsideBackgroundColor");
    @Bridge private native static UIColor objc_viewFlipsideBackgroundColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/viewFlipsideBackgroundColor">+ (UIColor *)viewFlipsideBackgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor viewFlipsideBackgroundColor() {
        return objc_viewFlipsideBackgroundColor(objCClass, viewFlipsideBackgroundColor);
    }
    
    private static final Selector whiteColor = Selector.register("whiteColor");
    @Bridge private native static UIColor objc_whiteColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/whiteColor">+ (UIColor *)whiteColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor whiteColor() {
        return objc_whiteColor(objCClass, whiteColor);
    }
    
    private static final Selector yellowColor = Selector.register("yellowColor");
    @Bridge private native static UIColor objc_yellowColor(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/clm/UIColor/yellowColor">+ (UIColor *)yellowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIColor yellowColor() {
        return objc_yellowColor(objCClass, yellowColor);
    }
    
    private static final Selector colorWithAlphaComponent$ = Selector.register("colorWithAlphaComponent:");
    @Bridge private native static UIColor objc_colorWithAlpha(UIColor __self__, Selector __cmd__, float alpha);
    @Bridge private native static UIColor objc_colorWithAlphaSuper(ObjCSuper __super__, Selector __cmd__, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/colorWithAlphaComponent:">- (UIColor *)colorWithAlphaComponent:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor colorWithAlpha(float alpha) {
        if (customClass) { return objc_colorWithAlphaSuper(getSuper(), colorWithAlphaComponent$, alpha); } else { return objc_colorWithAlpha(this, colorWithAlphaComponent$, alpha); }
    }
    
    private static final Selector getHue$saturation$brightness$alpha$ = Selector.register("getHue:saturation:brightness:alpha:");
    @Bridge private native static boolean objc_getHSBA(UIColor __self__, Selector __cmd__, FloatPtr hue, FloatPtr saturation, FloatPtr brightness, FloatPtr alpha);
    @Bridge private native static boolean objc_getHSBASuper(ObjCSuper __super__, Selector __cmd__, FloatPtr hue, FloatPtr saturation, FloatPtr brightness, FloatPtr alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/getHue:saturation:brightness:alpha:">- (BOOL)getHue:(CGFloat *)hue saturation:(CGFloat *)saturation brightness:(CGFloat *)brightness alpha:(CGFloat *)alpha</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean getHSBA(FloatPtr hue, FloatPtr saturation, FloatPtr brightness, FloatPtr alpha) {
        if (customClass) { return objc_getHSBASuper(getSuper(), getHue$saturation$brightness$alpha$, hue, saturation, brightness, alpha); } else { return objc_getHSBA(this, getHue$saturation$brightness$alpha$, hue, saturation, brightness, alpha); }
    }
    
    private static final Selector getRed$green$blue$alpha$ = Selector.register("getRed:green:blue:alpha:");
    @Bridge private native static boolean objc_getRGBA(UIColor __self__, Selector __cmd__, FloatPtr red, FloatPtr green, FloatPtr blue, FloatPtr alpha);
    @Bridge private native static boolean objc_getRGBASuper(ObjCSuper __super__, Selector __cmd__, FloatPtr red, FloatPtr green, FloatPtr blue, FloatPtr alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/getRed:green:blue:alpha:">- (BOOL)getRed:(CGFloat *)red green:(CGFloat *)green blue:(CGFloat *)blue alpha:(CGFloat *)alpha</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean getRGBA(FloatPtr red, FloatPtr green, FloatPtr blue, FloatPtr alpha) {
        if (customClass) { return objc_getRGBASuper(getSuper(), getRed$green$blue$alpha$, red, green, blue, alpha); } else { return objc_getRGBA(this, getRed$green$blue$alpha$, red, green, blue, alpha); }
    }
    
    private static final Selector getWhite$alpha$ = Selector.register("getWhite:alpha:");
    @Bridge private native static boolean objc_getWhiteAlpha(UIColor __self__, Selector __cmd__, FloatPtr white, FloatPtr alpha);
    @Bridge private native static boolean objc_getWhiteAlphaSuper(ObjCSuper __super__, Selector __cmd__, FloatPtr white, FloatPtr alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/getWhite:alpha:">- (BOOL)getWhite:(CGFloat *)white alpha:(CGFloat *)alpha</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean getWhiteAlpha(FloatPtr white, FloatPtr alpha) {
        if (customClass) { return objc_getWhiteAlphaSuper(getSuper(), getWhite$alpha$, white, alpha); } else { return objc_getWhiteAlpha(this, getWhite$alpha$, white, alpha); }
    }
    
    private static final Selector setFill = Selector.register("setFill");
    @Bridge private native static void objc_setFill(UIColor __self__, Selector __cmd__);
    @Bridge private native static void objc_setFillSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/setFill">- (void)setFill</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setFill() {
        if (customClass) { objc_setFillSuper(getSuper(), setFill); } else { objc_setFill(this, setFill); }
    }
    
    private static final Selector set = Selector.register("set");
    @Bridge private native static void objc_setFillAndStroke(UIColor __self__, Selector __cmd__);
    @Bridge private native static void objc_setFillAndStrokeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/set">- (void)set</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setFillAndStroke() {
        if (customClass) { objc_setFillAndStrokeSuper(getSuper(), set); } else { objc_setFillAndStroke(this, set); }
    }
    
    private static final Selector setStroke = Selector.register("setStroke");
    @Bridge private native static void objc_setStroke(UIColor __self__, Selector __cmd__);
    @Bridge private native static void objc_setStrokeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIColor_Class/Reference/Reference.html#//apple_ref/occ/instm/UIColor/setStroke">- (void)setStroke</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStroke() {
        if (customClass) { objc_setStrokeSuper(getSuper(), setStroke); } else { objc_setStroke(this, setStroke); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("CGColor") public static CGColor getCGColor(UIColor __self__, Selector __cmd__) { return __self__.getCGColor(); }
        @Callback @BindSelector("CIColor") public static CIColor getCIColor(UIColor __self__, Selector __cmd__) { return __self__.getCIColor(); }
        @Callback @BindSelector("colorWithAlphaComponent:") public static UIColor colorWithAlpha(UIColor __self__, Selector __cmd__, float alpha) { return __self__.colorWithAlpha(alpha); }
        @Callback @BindSelector("getHue:saturation:brightness:alpha:") public static boolean getHSBA(UIColor __self__, Selector __cmd__, FloatPtr hue, FloatPtr saturation, FloatPtr brightness, FloatPtr alpha) { return __self__.getHSBA(hue, saturation, brightness, alpha); }
        @Callback @BindSelector("getRed:green:blue:alpha:") public static boolean getRGBA(UIColor __self__, Selector __cmd__, FloatPtr red, FloatPtr green, FloatPtr blue, FloatPtr alpha) { return __self__.getRGBA(red, green, blue, alpha); }
        @Callback @BindSelector("getWhite:alpha:") public static boolean getWhiteAlpha(UIColor __self__, Selector __cmd__, FloatPtr white, FloatPtr alpha) { return __self__.getWhiteAlpha(white, alpha); }
        @Callback @BindSelector("setFill") public static void setFill(UIColor __self__, Selector __cmd__) { __self__.setFill(); }
        @Callback @BindSelector("set") public static void setFillAndStroke(UIColor __self__, Selector __cmd__) { __self__.setFillAndStroke(); }
        @Callback @BindSelector("setStroke") public static void setStroke(UIColor __self__, Selector __cmd__) { __self__.setStroke(); }
    }
    /*</callbacks>*/

}
