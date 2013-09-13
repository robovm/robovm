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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html">UIBezierPath Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIBezierPath /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBezierPath /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIBezierPath /*</name>*/.class);

    /*<constructors>*/
    protected UIBezierPath(SkipInit skipInit) { super(skipInit); }
    public UIBezierPath() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector CGPath = Selector.register("CGPath");
    @Bridge private native static CGPath objc_getCGPath(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static CGPath objc_getCGPathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/CGPath">@property(nonatomic) CGPathRef CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPath getCGPath() {
        if (customClass) { return objc_getCGPathSuper(getSuper(), CGPath); } else { return objc_getCGPath(this, CGPath); }
    }
    
    private static final Selector setCGPath$ = Selector.register("setCGPath:");
    @Bridge private native static void objc_setCGPath(UIBezierPath __self__, Selector __cmd__, CGPath CGPath);
    @Bridge private native static void objc_setCGPathSuper(ObjCSuper __super__, Selector __cmd__, CGPath CGPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/CGPath">@property(nonatomic) CGPathRef CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setCGPath(CGPath CGPath) {
        if (customClass) { objc_setCGPathSuper(getSuper(), setCGPath$, CGPath); } else { objc_setCGPath(this, setCGPath$, CGPath); }
    }
    
    private static final Selector bounds = Selector.register("bounds");
    @Bridge private native static @ByVal CGRect objc_getBounds(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getBoundsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/bounds">@property(nonatomic, readonly) CGRect bounds</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGRect getBounds() {
        if (customClass) { return objc_getBoundsSuper(getSuper(), bounds); } else { return objc_getBounds(this, bounds); }
    }
    
    private static final Selector currentPoint = Selector.register("currentPoint");
    @Bridge private native static @ByVal CGPoint objc_getCurrentPoint(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGPoint objc_getCurrentPointSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/currentPoint">@property(nonatomic, readonly) CGPoint currentPoint</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getCurrentPoint() {
        if (customClass) { return objc_getCurrentPointSuper(getSuper(), currentPoint); } else { return objc_getCurrentPoint(this, currentPoint); }
    }
    
    private static final Selector isEmpty = Selector.register("isEmpty");
    @Bridge private native static boolean objc_isEmpty(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEmptySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/empty">@property(readonly, getter=isEmpty) BOOL empty</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isEmpty() {
        if (customClass) { return objc_isEmptySuper(getSuper(), isEmpty); } else { return objc_isEmpty(this, isEmpty); }
    }
    
    private static final Selector flatness = Selector.register("flatness");
    @Bridge private native static float objc_getFlatness(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static float objc_getFlatnessSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/flatness">@property(nonatomic) CGFloat flatness</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getFlatness() {
        if (customClass) { return objc_getFlatnessSuper(getSuper(), flatness); } else { return objc_getFlatness(this, flatness); }
    }
    
    private static final Selector setFlatness$ = Selector.register("setFlatness:");
    @Bridge private native static void objc_setFlatness(UIBezierPath __self__, Selector __cmd__, float flatness);
    @Bridge private native static void objc_setFlatnessSuper(ObjCSuper __super__, Selector __cmd__, float flatness);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/flatness">@property(nonatomic) CGFloat flatness</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setFlatness(float flatness) {
        if (customClass) { objc_setFlatnessSuper(getSuper(), setFlatness$, flatness); } else { objc_setFlatness(this, setFlatness$, flatness); }
    }
    
    private static final Selector lineCapStyle = Selector.register("lineCapStyle");
    @Bridge private native static CGLineCap objc_getLineCapStyle(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static CGLineCap objc_getLineCapStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineCapStyle">@property(nonatomic) CGLineCap lineCapStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGLineCap getLineCapStyle() {
        if (customClass) { return objc_getLineCapStyleSuper(getSuper(), lineCapStyle); } else { return objc_getLineCapStyle(this, lineCapStyle); }
    }
    
    private static final Selector setLineCapStyle$ = Selector.register("setLineCapStyle:");
    @Bridge private native static void objc_setLineCapStyle(UIBezierPath __self__, Selector __cmd__, CGLineCap lineCapStyle);
    @Bridge private native static void objc_setLineCapStyleSuper(ObjCSuper __super__, Selector __cmd__, CGLineCap lineCapStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineCapStyle">@property(nonatomic) CGLineCap lineCapStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setLineCapStyle(CGLineCap lineCapStyle) {
        if (customClass) { objc_setLineCapStyleSuper(getSuper(), setLineCapStyle$, lineCapStyle); } else { objc_setLineCapStyle(this, setLineCapStyle$, lineCapStyle); }
    }
    
    private static final Selector lineJoinStyle = Selector.register("lineJoinStyle");
    @Bridge private native static CGLineJoin objc_getLineJoinStyle(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static CGLineJoin objc_getLineJoinStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineJoinStyle">@property(nonatomic) CGLineJoin lineJoinStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGLineJoin getLineJoinStyle() {
        if (customClass) { return objc_getLineJoinStyleSuper(getSuper(), lineJoinStyle); } else { return objc_getLineJoinStyle(this, lineJoinStyle); }
    }
    
    private static final Selector setLineJoinStyle$ = Selector.register("setLineJoinStyle:");
    @Bridge private native static void objc_setLineJoinStyle(UIBezierPath __self__, Selector __cmd__, CGLineJoin lineJoinStyle);
    @Bridge private native static void objc_setLineJoinStyleSuper(ObjCSuper __super__, Selector __cmd__, CGLineJoin lineJoinStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineJoinStyle">@property(nonatomic) CGLineJoin lineJoinStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setLineJoinStyle(CGLineJoin lineJoinStyle) {
        if (customClass) { objc_setLineJoinStyleSuper(getSuper(), setLineJoinStyle$, lineJoinStyle); } else { objc_setLineJoinStyle(this, setLineJoinStyle$, lineJoinStyle); }
    }
    
    private static final Selector lineWidth = Selector.register("lineWidth");
    @Bridge private native static float objc_getLineWidth(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static float objc_getLineWidthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineWidth">@property(nonatomic) CGFloat lineWidth</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getLineWidth() {
        if (customClass) { return objc_getLineWidthSuper(getSuper(), lineWidth); } else { return objc_getLineWidth(this, lineWidth); }
    }
    
    private static final Selector setLineWidth$ = Selector.register("setLineWidth:");
    @Bridge private native static void objc_setLineWidth(UIBezierPath __self__, Selector __cmd__, float lineWidth);
    @Bridge private native static void objc_setLineWidthSuper(ObjCSuper __super__, Selector __cmd__, float lineWidth);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineWidth">@property(nonatomic) CGFloat lineWidth</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setLineWidth(float lineWidth) {
        if (customClass) { objc_setLineWidthSuper(getSuper(), setLineWidth$, lineWidth); } else { objc_setLineWidth(this, setLineWidth$, lineWidth); }
    }
    
    private static final Selector miterLimit = Selector.register("miterLimit");
    @Bridge private native static float objc_getMiterLimit(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static float objc_getMiterLimitSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/miterLimit">@property(nonatomic) CGFloat miterLimit</a>
     * @since Available in iOS 3.2 and later.
     */
    public float getMiterLimit() {
        if (customClass) { return objc_getMiterLimitSuper(getSuper(), miterLimit); } else { return objc_getMiterLimit(this, miterLimit); }
    }
    
    private static final Selector setMiterLimit$ = Selector.register("setMiterLimit:");
    @Bridge private native static void objc_setMiterLimit(UIBezierPath __self__, Selector __cmd__, float miterLimit);
    @Bridge private native static void objc_setMiterLimitSuper(ObjCSuper __super__, Selector __cmd__, float miterLimit);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/miterLimit">@property(nonatomic) CGFloat miterLimit</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMiterLimit(float miterLimit) {
        if (customClass) { objc_setMiterLimitSuper(getSuper(), setMiterLimit$, miterLimit); } else { objc_setMiterLimit(this, setMiterLimit$, miterLimit); }
    }
    
    private static final Selector usesEvenOddFillRule = Selector.register("usesEvenOddFillRule");
    @Bridge private native static boolean objc_isUsesEvenOddFillRule(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isUsesEvenOddFillRuleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/usesEvenOddFillRule">@property(nonatomic) BOOL usesEvenOddFillRule</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isUsesEvenOddFillRule() {
        if (customClass) { return objc_isUsesEvenOddFillRuleSuper(getSuper(), usesEvenOddFillRule); } else { return objc_isUsesEvenOddFillRule(this, usesEvenOddFillRule); }
    }
    
    private static final Selector setUsesEvenOddFillRule$ = Selector.register("setUsesEvenOddFillRule:");
    @Bridge private native static void objc_setUsesEvenOddFillRule(UIBezierPath __self__, Selector __cmd__, boolean usesEvenOddFillRule);
    @Bridge private native static void objc_setUsesEvenOddFillRuleSuper(ObjCSuper __super__, Selector __cmd__, boolean usesEvenOddFillRule);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/usesEvenOddFillRule">@property(nonatomic) BOOL usesEvenOddFillRule</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setUsesEvenOddFillRule(boolean usesEvenOddFillRule) {
        if (customClass) { objc_setUsesEvenOddFillRuleSuper(getSuper(), setUsesEvenOddFillRule$, usesEvenOddFillRule); } else { objc_setUsesEvenOddFillRule(this, setUsesEvenOddFillRule$, usesEvenOddFillRule); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector bezierPath = Selector.register("bezierPath");
    @Bridge private native static UIBezierPath objc_create(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPath">+ (UIBezierPath *)bezierPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath create() {
        return objc_create(objCClass, bezierPath);
    }
    
    private static final Selector bezierPathWithArcCenter$radius$startAngle$endAngle$clockwise$ = Selector.register("bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:");
    @Bridge private native static UIBezierPath objc_fromArc(ObjCClass __self__, Selector __cmd__, @ByVal CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:">+ (UIBezierPath *)bezierPathWithArcCenter:(CGPoint)center radius:(CGFloat)radius startAngle:(CGFloat)startAngle endAngle:(CGFloat)endAngle clockwise:(BOOL)clockwise</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromArc(CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise) {
        return objc_fromArc(objCClass, bezierPathWithArcCenter$radius$startAngle$endAngle$clockwise$, center, radius, startAngle, endAngle, clockwise);
    }
    
    private static final Selector bezierPathWithOvalInRect$ = Selector.register("bezierPathWithOvalInRect:");
    @Bridge private native static UIBezierPath objc_fromOval(ObjCClass __self__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithOvalInRect:">+ (UIBezierPath *)bezierPathWithOvalInRect:(CGRect)rect</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromOval(CGRect rect) {
        return objc_fromOval(objCClass, bezierPathWithOvalInRect$, rect);
    }
    
    private static final Selector bezierPathWithCGPath$ = Selector.register("bezierPathWithCGPath:");
    @Bridge private native static UIBezierPath objc_fromPath(ObjCClass __self__, Selector __cmd__, CGPath CGPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithCGPath:">+ (UIBezierPath *)bezierPathWithCGPath:(CGPathRef)CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromPath(CGPath CGPath) {
        return objc_fromPath(objCClass, bezierPathWithCGPath$, CGPath);
    }
    
    private static final Selector bezierPathWithRect$ = Selector.register("bezierPathWithRect:");
    @Bridge private native static UIBezierPath objc_fromRect(ObjCClass __self__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRect:">+ (UIBezierPath *)bezierPathWithRect:(CGRect)rect</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromRect(CGRect rect) {
        return objc_fromRect(objCClass, bezierPathWithRect$, rect);
    }
    
    private static final Selector bezierPathWithRoundedRect$byRoundingCorners$cornerRadii$ = Selector.register("bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:");
    @Bridge private native static UIBezierPath objc_fromRoundedRect(ObjCClass __self__, Selector __cmd__, @ByVal CGRect rect, UIRectCorner corners, @ByVal CGSize cornerRadii);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:">+ (UIBezierPath *)bezierPathWithRoundedRect:(CGRect)rect byRoundingCorners:(UIRectCorner)corners cornerRadii:(CGSize)cornerRadii</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromRoundedRect(CGRect rect, UIRectCorner corners, CGSize cornerRadii) {
        return objc_fromRoundedRect(objCClass, bezierPathWithRoundedRect$byRoundingCorners$cornerRadii$, rect, corners, cornerRadii);
    }
    
    private static final Selector bezierPathWithRoundedRect$cornerRadius$ = Selector.register("bezierPathWithRoundedRect:cornerRadius:");
    @Bridge private native static UIBezierPath objc_fromRoundedRect(ObjCClass __self__, Selector __cmd__, @ByVal CGRect rect, float cornerRadius);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRoundedRect:cornerRadius:">+ (UIBezierPath *)bezierPathWithRoundedRect:(CGRect)rect cornerRadius:(CGFloat)cornerRadius</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromRoundedRect(CGRect rect, float cornerRadius) {
        return objc_fromRoundedRect(objCClass, bezierPathWithRoundedRect$cornerRadius$, rect, cornerRadius);
    }
    
    private static final Selector addArcWithCenter$radius$startAngle$endAngle$clockwise$ = Selector.register("addArcWithCenter:radius:startAngle:endAngle:clockwise:");
    @Bridge private native static void objc_addArc(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise);
    @Bridge private native static void objc_addArcSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addArcWithCenter:radius:startAngle:endAngle:clockwise:">- (void)addArcWithCenter:(CGPoint)center radius:(CGFloat)radius startAngle:(CGFloat)startAngle endAngle:(CGFloat)endAngle clockwise:(BOOL)clockwise</a>
     * @since Available in iOS 4.0 and later.
     */
    public void addArc(CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise) {
        if (customClass) { objc_addArcSuper(getSuper(), addArcWithCenter$radius$startAngle$endAngle$clockwise$, center, radius, startAngle, endAngle, clockwise); } else { objc_addArc(this, addArcWithCenter$radius$startAngle$endAngle$clockwise$, center, radius, startAngle, endAngle, clockwise); }
    }
    
    private static final Selector addClip = Selector.register("addClip");
    @Bridge private native static void objc_addClip(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static void objc_addClipSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addClip">- (void)addClip</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addClip() {
        if (customClass) { objc_addClipSuper(getSuper(), addClip); } else { objc_addClip(this, addClip); }
    }
    
    private static final Selector addCurveToPoint$controlPoint1$controlPoint2$ = Selector.register("addCurveToPoint:controlPoint1:controlPoint2:");
    @Bridge private native static void objc_addCurve(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint endPoint, @ByVal CGPoint controlPoint1, @ByVal CGPoint controlPoint2);
    @Bridge private native static void objc_addCurveSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint endPoint, @ByVal CGPoint controlPoint1, @ByVal CGPoint controlPoint2);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addCurveToPoint:controlPoint1:controlPoint2:">- (void)addCurveToPoint:(CGPoint)endPoint controlPoint1:(CGPoint)controlPoint1 controlPoint2:(CGPoint)controlPoint2</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addCurve(CGPoint endPoint, CGPoint controlPoint1, CGPoint controlPoint2) {
        if (customClass) { objc_addCurveSuper(getSuper(), addCurveToPoint$controlPoint1$controlPoint2$, endPoint, controlPoint1, controlPoint2); } else { objc_addCurve(this, addCurveToPoint$controlPoint1$controlPoint2$, endPoint, controlPoint1, controlPoint2); }
    }
    
    private static final Selector addLineToPoint$ = Selector.register("addLineToPoint:");
    @Bridge private native static void objc_addLine(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static void objc_addLineSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addLineToPoint:">- (void)addLineToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addLine(CGPoint point) {
        if (customClass) { objc_addLineSuper(getSuper(), addLineToPoint$, point); } else { objc_addLine(this, addLineToPoint$, point); }
    }
    
    private static final Selector addQuadCurveToPoint$controlPoint$ = Selector.register("addQuadCurveToPoint:controlPoint:");
    @Bridge private native static void objc_addQuadCurve(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint endPoint, @ByVal CGPoint controlPoint);
    @Bridge private native static void objc_addQuadCurveSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint endPoint, @ByVal CGPoint controlPoint);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addQuadCurveToPoint:controlPoint:">- (void)addQuadCurveToPoint:(CGPoint)endPoint controlPoint:(CGPoint)controlPoint</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addQuadCurve(CGPoint endPoint, CGPoint controlPoint) {
        if (customClass) { objc_addQuadCurveSuper(getSuper(), addQuadCurveToPoint$controlPoint$, endPoint, controlPoint); } else { objc_addQuadCurve(this, addQuadCurveToPoint$controlPoint$, endPoint, controlPoint); }
    }
    
    private static final Selector appendPath$ = Selector.register("appendPath:");
    @Bridge private native static void objc_appendPath(UIBezierPath __self__, Selector __cmd__, UIBezierPath bezierPath);
    @Bridge private native static void objc_appendPathSuper(ObjCSuper __super__, Selector __cmd__, UIBezierPath bezierPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/appendPath:">- (void)appendPath:(UIBezierPath *)bezierPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public void appendPath(UIBezierPath bezierPath) {
        if (customClass) { objc_appendPathSuper(getSuper(), appendPath$, bezierPath); } else { objc_appendPath(this, appendPath$, bezierPath); }
    }
    
    private static final Selector applyTransform$ = Selector.register("applyTransform:");
    @Bridge private native static void objc_applyTransform(UIBezierPath __self__, Selector __cmd__, @ByVal CGAffineTransform transform);
    @Bridge private native static void objc_applyTransformSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGAffineTransform transform);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/applyTransform:">- (void)applyTransform:(CGAffineTransform)transform</a>
     * @since Available in iOS 3.2 and later.
     */
    public void applyTransform(CGAffineTransform transform) {
        if (customClass) { objc_applyTransformSuper(getSuper(), applyTransform$, transform); } else { objc_applyTransform(this, applyTransform$, transform); }
    }
    
    private static final Selector closePath = Selector.register("closePath");
    @Bridge private native static void objc_closePath(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static void objc_closePathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/closePath">- (void)closePath</a>
     * @since Available in iOS 3.2 and later.
     */
    public void closePath() {
        if (customClass) { objc_closePathSuper(getSuper(), closePath); } else { objc_closePath(this, closePath); }
    }
    
    private static final Selector containsPoint$ = Selector.register("containsPoint:");
    @Bridge private native static boolean objc_containsPoint(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static boolean objc_containsPointSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/containsPoint:">- (BOOL)containsPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean containsPoint(CGPoint point) {
        if (customClass) { return objc_containsPointSuper(getSuper(), containsPoint$, point); } else { return objc_containsPoint(this, containsPoint$, point); }
    }
    
    private static final Selector fillWithBlendMode$alpha$ = Selector.register("fillWithBlendMode:alpha:");
    @Bridge private native static void objc_fill(UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    @Bridge private native static void objc_fillSuper(ObjCSuper __super__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/fillWithBlendMode:alpha:">- (void)fillWithBlendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 3.2 and later.
     */
    public void fill(CGBlendMode blendMode, float alpha) {
        if (customClass) { objc_fillSuper(getSuper(), fillWithBlendMode$alpha$, blendMode, alpha); } else { objc_fill(this, fillWithBlendMode$alpha$, blendMode, alpha); }
    }
    
    private static final Selector fill = Selector.register("fill");
    @Bridge private native static void objc_fill(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static void objc_fillSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/fill">- (void)fill</a>
     * @since Available in iOS 3.2 and later.
     */
    public void fill() {
        if (customClass) { objc_fillSuper(getSuper(), fill); } else { objc_fill(this, fill); }
    }
    
    private static final Selector getLineDash$count$phase$ = Selector.register("getLineDash:count:phase:");
    @Bridge private native static void objc_getLineDash(UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, IntPtr count, FloatPtr phase);
    @Bridge private native static void objc_getLineDashSuper(ObjCSuper __super__, Selector __cmd__, FloatPtr pattern, IntPtr count, FloatPtr phase);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/getLineDash:count:phase:">- (void)getLineDash:(CGFloat *)pattern count:(NSInteger *)count phase:(CGFloat *)phase</a>
     * @since Available in iOS 3.2 and later.
     */
    public void getLineDash(FloatPtr pattern, IntPtr count, FloatPtr phase) {
        if (customClass) { objc_getLineDashSuper(getSuper(), getLineDash$count$phase$, pattern, count, phase); } else { objc_getLineDash(this, getLineDash$count$phase$, pattern, count, phase); }
    }
    
    private static final Selector moveToPoint$ = Selector.register("moveToPoint:");
    @Bridge private native static void objc_move(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static void objc_moveSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/moveToPoint:">- (void)moveToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public void move(CGPoint point) {
        if (customClass) { objc_moveSuper(getSuper(), moveToPoint$, point); } else { objc_move(this, moveToPoint$, point); }
    }
    
    private static final Selector removeAllPoints = Selector.register("removeAllPoints");
    @Bridge private native static void objc_removeAllPoints(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static void objc_removeAllPointsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/removeAllPoints">- (void)removeAllPoints</a>
     * @since Available in iOS 3.2 and later.
     */
    public void removeAllPoints() {
        if (customClass) { objc_removeAllPointsSuper(getSuper(), removeAllPoints); } else { objc_removeAllPoints(this, removeAllPoints); }
    }
    
    private static final Selector bezierPathByReversingPath = Selector.register("bezierPathByReversingPath");
    @Bridge private native static UIBezierPath objc_reverse(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static UIBezierPath objc_reverseSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/bezierPathByReversingPath">- (UIBezierPath *)bezierPathByReversingPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIBezierPath reverse() {
        if (customClass) { return objc_reverseSuper(getSuper(), bezierPathByReversingPath); } else { return objc_reverse(this, bezierPathByReversingPath); }
    }
    
    private static final Selector setLineDash$count$phase$ = Selector.register("setLineDash:count:phase:");
    @Bridge private native static void objc_setLineDash(UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, int count, float phase);
    @Bridge private native static void objc_setLineDashSuper(ObjCSuper __super__, Selector __cmd__, FloatPtr pattern, int count, float phase);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/setLineDash:count:phase:">- (void)setLineDash:(const CGFloat *)pattern count:(NSInteger)count phase:(CGFloat)phase</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setLineDash(FloatPtr pattern, int count, float phase) {
        if (customClass) { objc_setLineDashSuper(getSuper(), setLineDash$count$phase$, pattern, count, phase); } else { objc_setLineDash(this, setLineDash$count$phase$, pattern, count, phase); }
    }
    
    private static final Selector stroke = Selector.register("stroke");
    @Bridge private native static void objc_stroke(UIBezierPath __self__, Selector __cmd__);
    @Bridge private native static void objc_strokeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/stroke">- (void)stroke</a>
     * @since Available in iOS 3.2 and later.
     */
    public void stroke() {
        if (customClass) { objc_strokeSuper(getSuper(), stroke); } else { objc_stroke(this, stroke); }
    }
    
    private static final Selector strokeWithBlendMode$alpha$ = Selector.register("strokeWithBlendMode:alpha:");
    @Bridge private native static void objc_stroke(UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    @Bridge private native static void objc_strokeSuper(ObjCSuper __super__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/strokeWithBlendMode:alpha:">- (void)strokeWithBlendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 3.2 and later.
     */
    public void stroke(CGBlendMode blendMode, float alpha) {
        if (customClass) { objc_strokeSuper(getSuper(), strokeWithBlendMode$alpha$, blendMode, alpha); } else { objc_stroke(this, strokeWithBlendMode$alpha$, blendMode, alpha); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("CGPath") public static CGPath getCGPath(UIBezierPath __self__, Selector __cmd__) { return __self__.getCGPath(); }
        @Callback @BindSelector("setCGPath:") public static void setCGPath(UIBezierPath __self__, Selector __cmd__, CGPath CGPath) { __self__.setCGPath(CGPath); }
        @Callback @BindSelector("bounds") public static @ByVal CGRect getBounds(UIBezierPath __self__, Selector __cmd__) { return __self__.getBounds(); }
        @Callback @BindSelector("currentPoint") public static @ByVal CGPoint getCurrentPoint(UIBezierPath __self__, Selector __cmd__) { return __self__.getCurrentPoint(); }
        @Callback @BindSelector("isEmpty") public static boolean isEmpty(UIBezierPath __self__, Selector __cmd__) { return __self__.isEmpty(); }
        @Callback @BindSelector("flatness") public static float getFlatness(UIBezierPath __self__, Selector __cmd__) { return __self__.getFlatness(); }
        @Callback @BindSelector("setFlatness:") public static void setFlatness(UIBezierPath __self__, Selector __cmd__, float flatness) { __self__.setFlatness(flatness); }
        @Callback @BindSelector("lineCapStyle") public static CGLineCap getLineCapStyle(UIBezierPath __self__, Selector __cmd__) { return __self__.getLineCapStyle(); }
        @Callback @BindSelector("setLineCapStyle:") public static void setLineCapStyle(UIBezierPath __self__, Selector __cmd__, CGLineCap lineCapStyle) { __self__.setLineCapStyle(lineCapStyle); }
        @Callback @BindSelector("lineJoinStyle") public static CGLineJoin getLineJoinStyle(UIBezierPath __self__, Selector __cmd__) { return __self__.getLineJoinStyle(); }
        @Callback @BindSelector("setLineJoinStyle:") public static void setLineJoinStyle(UIBezierPath __self__, Selector __cmd__, CGLineJoin lineJoinStyle) { __self__.setLineJoinStyle(lineJoinStyle); }
        @Callback @BindSelector("lineWidth") public static float getLineWidth(UIBezierPath __self__, Selector __cmd__) { return __self__.getLineWidth(); }
        @Callback @BindSelector("setLineWidth:") public static void setLineWidth(UIBezierPath __self__, Selector __cmd__, float lineWidth) { __self__.setLineWidth(lineWidth); }
        @Callback @BindSelector("miterLimit") public static float getMiterLimit(UIBezierPath __self__, Selector __cmd__) { return __self__.getMiterLimit(); }
        @Callback @BindSelector("setMiterLimit:") public static void setMiterLimit(UIBezierPath __self__, Selector __cmd__, float miterLimit) { __self__.setMiterLimit(miterLimit); }
        @Callback @BindSelector("usesEvenOddFillRule") public static boolean isUsesEvenOddFillRule(UIBezierPath __self__, Selector __cmd__) { return __self__.isUsesEvenOddFillRule(); }
        @Callback @BindSelector("setUsesEvenOddFillRule:") public static void setUsesEvenOddFillRule(UIBezierPath __self__, Selector __cmd__, boolean usesEvenOddFillRule) { __self__.setUsesEvenOddFillRule(usesEvenOddFillRule); }
        @Callback @BindSelector("addArcWithCenter:radius:startAngle:endAngle:clockwise:") public static void addArc(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise) { __self__.addArc(center, radius, startAngle, endAngle, clockwise); }
        @Callback @BindSelector("addClip") public static void addClip(UIBezierPath __self__, Selector __cmd__) { __self__.addClip(); }
        @Callback @BindSelector("addCurveToPoint:controlPoint1:controlPoint2:") public static void addCurve(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint endPoint, @ByVal CGPoint controlPoint1, @ByVal CGPoint controlPoint2) { __self__.addCurve(endPoint, controlPoint1, controlPoint2); }
        @Callback @BindSelector("addLineToPoint:") public static void addLine(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint point) { __self__.addLine(point); }
        @Callback @BindSelector("addQuadCurveToPoint:controlPoint:") public static void addQuadCurve(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint endPoint, @ByVal CGPoint controlPoint) { __self__.addQuadCurve(endPoint, controlPoint); }
        @Callback @BindSelector("appendPath:") public static void appendPath(UIBezierPath __self__, Selector __cmd__, UIBezierPath bezierPath) { __self__.appendPath(bezierPath); }
        @Callback @BindSelector("applyTransform:") public static void applyTransform(UIBezierPath __self__, Selector __cmd__, @ByVal CGAffineTransform transform) { __self__.applyTransform(transform); }
        @Callback @BindSelector("closePath") public static void closePath(UIBezierPath __self__, Selector __cmd__) { __self__.closePath(); }
        @Callback @BindSelector("containsPoint:") public static boolean containsPoint(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.containsPoint(point); }
        @Callback @BindSelector("fillWithBlendMode:alpha:") public static void fill(UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha) { __self__.fill(blendMode, alpha); }
        @Callback @BindSelector("fill") public static void fill(UIBezierPath __self__, Selector __cmd__) { __self__.fill(); }
        @Callback @BindSelector("getLineDash:count:phase:") public static void getLineDash(UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, IntPtr count, FloatPtr phase) { __self__.getLineDash(pattern, count, phase); }
        @Callback @BindSelector("moveToPoint:") public static void move(UIBezierPath __self__, Selector __cmd__, @ByVal CGPoint point) { __self__.move(point); }
        @Callback @BindSelector("removeAllPoints") public static void removeAllPoints(UIBezierPath __self__, Selector __cmd__) { __self__.removeAllPoints(); }
        @Callback @BindSelector("bezierPathByReversingPath") public static UIBezierPath reverse(UIBezierPath __self__, Selector __cmd__) { return __self__.reverse(); }
        @Callback @BindSelector("setLineDash:count:phase:") public static void setLineDash(UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, int count, float phase) { __self__.setLineDash(pattern, count, phase); }
        @Callback @BindSelector("stroke") public static void stroke(UIBezierPath __self__, Selector __cmd__) { __self__.stroke(); }
        @Callback @BindSelector("strokeWithBlendMode:alpha:") public static void stroke(UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha) { __self__.stroke(blendMode, alpha); }
    }
    /*</callbacks>*/

}
