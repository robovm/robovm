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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html">UIBezierPath Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIBezierPath /*</name>*/ 
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/CGPath">@property(nonatomic) CGPathRef CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("CGPath") public native CGPath getCGPath();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/CGPath">@property(nonatomic) CGPathRef CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setCGPath:") public native void setCGPath(CGPath v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/bounds">@property(nonatomic, readonly) CGRect bounds</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bounds") public native CGRect getBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/currentPoint">@property(nonatomic, readonly) CGPoint currentPoint</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("currentPoint") public native CGPoint getCurrentPoint();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/empty">@property(readonly, getter=isEmpty) BOOL empty</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isEmpty") public native boolean isEmpty();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/flatness">@property(nonatomic) CGFloat flatness</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("flatness") public native float getFlatness();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/flatness">@property(nonatomic) CGFloat flatness</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setFlatness:") public native void setFlatness(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineCapStyle">@property(nonatomic) CGLineCap lineCapStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("lineCapStyle") public native CGLineCap getLineCapStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineCapStyle">@property(nonatomic) CGLineCap lineCapStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineCapStyle:") public native void setLineCapStyle(CGLineCap v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineJoinStyle">@property(nonatomic) CGLineJoin lineJoinStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("lineJoinStyle") public native CGLineJoin getLineJoinStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineJoinStyle">@property(nonatomic) CGLineJoin lineJoinStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineJoinStyle:") public native void setLineJoinStyle(CGLineJoin v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineWidth">@property(nonatomic) CGFloat lineWidth</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("lineWidth") public native float getLineWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineWidth">@property(nonatomic) CGFloat lineWidth</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineWidth:") public native void setLineWidth(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/miterLimit">@property(nonatomic) CGFloat miterLimit</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("miterLimit") public native float getMiterLimit();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/miterLimit">@property(nonatomic) CGFloat miterLimit</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMiterLimit:") public native void setMiterLimit(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/usesEvenOddFillRule">@property(nonatomic) BOOL usesEvenOddFillRule</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("usesEvenOddFillRule") public native boolean isUsesEvenOddFillRule();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/usesEvenOddFillRule">@property(nonatomic) BOOL usesEvenOddFillRule</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setUsesEvenOddFillRule:") public native void setUsesEvenOddFillRule(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector bezierPath = Selector.register("bezierPath");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_create(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPath">+ (UIBezierPath *)bezierPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath create() {
        return objc_create(objCClass, bezierPath);
    }
    
    private static final Selector bezierPathWithArcCenter$radius$startAngle$endAngle$clockwise$ = Selector.register("bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_fromArc(ObjCClass __self__, Selector __cmd__, CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:">+ (UIBezierPath *)bezierPathWithArcCenter:(CGPoint)center radius:(CGFloat)radius startAngle:(CGFloat)startAngle endAngle:(CGFloat)endAngle clockwise:(BOOL)clockwise</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromArc(CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise) {
        return objc_fromArc(objCClass, bezierPathWithArcCenter$radius$startAngle$endAngle$clockwise$, center, radius, startAngle, endAngle, clockwise);
    }
    
    private static final Selector bezierPathWithOvalInRect$ = Selector.register("bezierPathWithOvalInRect:");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_fromOval(ObjCClass __self__, Selector __cmd__, CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithOvalInRect:">+ (UIBezierPath *)bezierPathWithOvalInRect:(CGRect)rect</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromOval(CGRect rect) {
        return objc_fromOval(objCClass, bezierPathWithOvalInRect$, rect);
    }
    
    private static final Selector bezierPathWithCGPath$ = Selector.register("bezierPathWithCGPath:");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_fromPath(ObjCClass __self__, Selector __cmd__, CGPath CGPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithCGPath:">+ (UIBezierPath *)bezierPathWithCGPath:(CGPathRef)CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromPath(CGPath CGPath) {
        return objc_fromPath(objCClass, bezierPathWithCGPath$, CGPath);
    }
    
    private static final Selector bezierPathWithRect$ = Selector.register("bezierPathWithRect:");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_fromRect(ObjCClass __self__, Selector __cmd__, CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRect:">+ (UIBezierPath *)bezierPathWithRect:(CGRect)rect</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromRect(CGRect rect) {
        return objc_fromRect(objCClass, bezierPathWithRect$, rect);
    }
    
    private static final Selector bezierPathWithRoundedRect$byRoundingCorners$cornerRadii$ = Selector.register("bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_fromRoundedRect(ObjCClass __self__, Selector __cmd__, CGRect rect, UIRectCorner corners, CGSize cornerRadii);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:">+ (UIBezierPath *)bezierPathWithRoundedRect:(CGRect)rect byRoundingCorners:(UIRectCorner)corners cornerRadii:(CGSize)cornerRadii</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromRoundedRect(CGRect rect, UIRectCorner corners, CGSize cornerRadii) {
        return objc_fromRoundedRect(objCClass, bezierPathWithRoundedRect$byRoundingCorners$cornerRadii$, rect, corners, cornerRadii);
    }
    
    private static final Selector bezierPathWithRoundedRect$cornerRadius$ = Selector.register("bezierPathWithRoundedRect:cornerRadius:");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_fromRoundedRect(ObjCClass __self__, Selector __cmd__, CGRect rect, float cornerRadius);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRoundedRect:cornerRadius:">+ (UIBezierPath *)bezierPathWithRoundedRect:(CGRect)rect cornerRadius:(CGFloat)cornerRadius</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIBezierPath fromRoundedRect(CGRect rect, float cornerRadius) {
        return objc_fromRoundedRect(objCClass, bezierPathWithRoundedRect$cornerRadius$, rect, cornerRadius);
    }
    
    private static final Selector addArcWithCenter$radius$startAngle$endAngle$clockwise$ = Selector.register("addArcWithCenter:radius:startAngle:endAngle:clockwise:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addArc(UIBezierPath __self__, Selector __cmd__, CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addArcSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addArcWithCenter:radius:startAngle:endAngle:clockwise:">- (void)addArcWithCenter:(CGPoint)center radius:(CGFloat)radius startAngle:(CGFloat)startAngle endAngle:(CGFloat)endAngle clockwise:(BOOL)clockwise</a>
     * @since Available in iOS 4.0 and later.
     */
    public void addArc(CGPoint center, float radius, float startAngle, float endAngle, boolean clockwise) {
        if (customClass) { objc_addArcSuper(getSuper(), this, addArcWithCenter$radius$startAngle$endAngle$clockwise$, center, radius, startAngle, endAngle, clockwise); } else { objc_addArc(this, addArcWithCenter$radius$startAngle$endAngle$clockwise$, center, radius, startAngle, endAngle, clockwise); }
    }
    
    private static final Selector addClip = Selector.register("addClip");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addClip(UIBezierPath __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addClipSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addClip">- (void)addClip</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addClip() {
        if (customClass) { objc_addClipSuper(getSuper(), this, addClip); } else { objc_addClip(this, addClip); }
    }
    
    private static final Selector addCurveToPoint$controlPoint1$controlPoint2$ = Selector.register("addCurveToPoint:controlPoint1:controlPoint2:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addCurve(UIBezierPath __self__, Selector __cmd__, CGPoint endPoint, CGPoint controlPoint1, CGPoint controlPoint2);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addCurveSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGPoint endPoint, CGPoint controlPoint1, CGPoint controlPoint2);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addCurveToPoint:controlPoint1:controlPoint2:">- (void)addCurveToPoint:(CGPoint)endPoint controlPoint1:(CGPoint)controlPoint1 controlPoint2:(CGPoint)controlPoint2</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addCurve(CGPoint endPoint, CGPoint controlPoint1, CGPoint controlPoint2) {
        if (customClass) { objc_addCurveSuper(getSuper(), this, addCurveToPoint$controlPoint1$controlPoint2$, endPoint, controlPoint1, controlPoint2); } else { objc_addCurve(this, addCurveToPoint$controlPoint1$controlPoint2$, endPoint, controlPoint1, controlPoint2); }
    }
    
    private static final Selector addLineToPoint$ = Selector.register("addLineToPoint:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addLine(UIBezierPath __self__, Selector __cmd__, CGPoint point);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addLineSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addLineToPoint:">- (void)addLineToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addLine(CGPoint point) {
        if (customClass) { objc_addLineSuper(getSuper(), this, addLineToPoint$, point); } else { objc_addLine(this, addLineToPoint$, point); }
    }
    
    private static final Selector addQuadCurveToPoint$controlPoint$ = Selector.register("addQuadCurveToPoint:controlPoint:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addQuadCurve(UIBezierPath __self__, Selector __cmd__, CGPoint endPoint, CGPoint controlPoint);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addQuadCurveSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGPoint endPoint, CGPoint controlPoint);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addQuadCurveToPoint:controlPoint:">- (void)addQuadCurveToPoint:(CGPoint)endPoint controlPoint:(CGPoint)controlPoint</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addQuadCurve(CGPoint endPoint, CGPoint controlPoint) {
        if (customClass) { objc_addQuadCurveSuper(getSuper(), this, addQuadCurveToPoint$controlPoint$, endPoint, controlPoint); } else { objc_addQuadCurve(this, addQuadCurveToPoint$controlPoint$, endPoint, controlPoint); }
    }
    
    private static final Selector appendPath$ = Selector.register("appendPath:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_appendPath(UIBezierPath __self__, Selector __cmd__, UIBezierPath bezierPath);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_appendPathSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, UIBezierPath bezierPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/appendPath:">- (void)appendPath:(UIBezierPath *)bezierPath</a>
     * @since Available in iOS 3.2 and later.
     */
    public void appendPath(UIBezierPath bezierPath) {
        if (customClass) { objc_appendPathSuper(getSuper(), this, appendPath$, bezierPath); } else { objc_appendPath(this, appendPath$, bezierPath); }
    }
    
    private static final Selector applyTransform$ = Selector.register("applyTransform:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_applyTransform(UIBezierPath __self__, Selector __cmd__, CGAffineTransform transform);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_applyTransformSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGAffineTransform transform);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/applyTransform:">- (void)applyTransform:(CGAffineTransform)transform</a>
     * @since Available in iOS 3.2 and later.
     */
    public void applyTransform(CGAffineTransform transform) {
        if (customClass) { objc_applyTransformSuper(getSuper(), this, applyTransform$, transform); } else { objc_applyTransform(this, applyTransform$, transform); }
    }
    
    private static final Selector closePath = Selector.register("closePath");
    @Bridge(symbol = "objc_msgSend") private native static void objc_closePath(UIBezierPath __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_closePathSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/closePath">- (void)closePath</a>
     * @since Available in iOS 3.2 and later.
     */
    public void closePath() {
        if (customClass) { objc_closePathSuper(getSuper(), this, closePath); } else { objc_closePath(this, closePath); }
    }
    
    private static final Selector containsPoint$ = Selector.register("containsPoint:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_containsPoint(UIBezierPath __self__, Selector __cmd__, CGPoint point);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_containsPointSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/containsPoint:">- (BOOL)containsPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean containsPoint(CGPoint point) {
        if (customClass) { return objc_containsPointSuper(getSuper(), this, containsPoint$, point); } else { return objc_containsPoint(this, containsPoint$, point); }
    }
    
    private static final Selector fillWithBlendMode$alpha$ = Selector.register("fillWithBlendMode:alpha:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_fill(UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_fillSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/fillWithBlendMode:alpha:">- (void)fillWithBlendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 3.2 and later.
     */
    public void fill(CGBlendMode blendMode, float alpha) {
        if (customClass) { objc_fillSuper(getSuper(), this, fillWithBlendMode$alpha$, blendMode, alpha); } else { objc_fill(this, fillWithBlendMode$alpha$, blendMode, alpha); }
    }
    
    private static final Selector fill = Selector.register("fill");
    @Bridge(symbol = "objc_msgSend") private native static void objc_fill(UIBezierPath __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_fillSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/fill">- (void)fill</a>
     * @since Available in iOS 3.2 and later.
     */
    public void fill() {
        if (customClass) { objc_fillSuper(getSuper(), this, fill); } else { objc_fill(this, fill); }
    }
    
    private static final Selector getLineDash$count$phase$ = Selector.register("getLineDash:count:phase:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_getLineDash(UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, IntPtr count, FloatPtr phase);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_getLineDashSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, IntPtr count, FloatPtr phase);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/getLineDash:count:phase:">- (void)getLineDash:(CGFloat *)pattern count:(NSInteger *)count phase:(CGFloat *)phase</a>
     * @since Available in iOS 3.2 and later.
     */
    public void getLineDash(FloatPtr pattern, IntPtr count, FloatPtr phase) {
        if (customClass) { objc_getLineDashSuper(getSuper(), this, getLineDash$count$phase$, pattern, count, phase); } else { objc_getLineDash(this, getLineDash$count$phase$, pattern, count, phase); }
    }
    
    private static final Selector moveToPoint$ = Selector.register("moveToPoint:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_move(UIBezierPath __self__, Selector __cmd__, CGPoint point);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_moveSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/moveToPoint:">- (void)moveToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public void move(CGPoint point) {
        if (customClass) { objc_moveSuper(getSuper(), this, moveToPoint$, point); } else { objc_move(this, moveToPoint$, point); }
    }
    
    private static final Selector removeAllPoints = Selector.register("removeAllPoints");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeAllPoints(UIBezierPath __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeAllPointsSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/removeAllPoints">- (void)removeAllPoints</a>
     * @since Available in iOS 3.2 and later.
     */
    public void removeAllPoints() {
        if (customClass) { objc_removeAllPointsSuper(getSuper(), this, removeAllPoints); } else { objc_removeAllPoints(this, removeAllPoints); }
    }
    
    private static final Selector bezierPathByReversingPath = Selector.register("bezierPathByReversingPath");
    @Bridge(symbol = "objc_msgSend") private native static UIBezierPath objc_reverse(UIBezierPath __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIBezierPath objc_reverseSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/bezierPathByReversingPath">- (UIBezierPath *)bezierPathByReversingPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIBezierPath reverse() {
        if (customClass) { return objc_reverseSuper(getSuper(), this, bezierPathByReversingPath); } else { return objc_reverse(this, bezierPathByReversingPath); }
    }
    
    private static final Selector setLineDash$count$phase$ = Selector.register("setLineDash:count:phase:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLineDash(UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, int count, float phase);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLineDashSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, FloatPtr pattern, int count, float phase);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/setLineDash:count:phase:">- (void)setLineDash:(const CGFloat *)pattern count:(NSInteger)count phase:(CGFloat)phase</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setLineDash(FloatPtr pattern, int count, float phase) {
        if (customClass) { objc_setLineDashSuper(getSuper(), this, setLineDash$count$phase$, pattern, count, phase); } else { objc_setLineDash(this, setLineDash$count$phase$, pattern, count, phase); }
    }
    
    private static final Selector stroke = Selector.register("stroke");
    @Bridge(symbol = "objc_msgSend") private native static void objc_stroke(UIBezierPath __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_strokeSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/stroke">- (void)stroke</a>
     * @since Available in iOS 3.2 and later.
     */
    public void stroke() {
        if (customClass) { objc_strokeSuper(getSuper(), this, stroke); } else { objc_stroke(this, stroke); }
    }
    
    private static final Selector strokeWithBlendMode$alpha$ = Selector.register("strokeWithBlendMode:alpha:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_stroke(UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_strokeSuper(ObjCSuper __super__, UIBezierPath __self__, Selector __cmd__, CGBlendMode blendMode, float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/strokeWithBlendMode:alpha:">- (void)strokeWithBlendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 3.2 and later.
     */
    public void stroke(CGBlendMode blendMode, float alpha) {
        if (customClass) { objc_strokeSuper(getSuper(), this, strokeWithBlendMode$alpha$, blendMode, alpha); } else { objc_stroke(this, strokeWithBlendMode$alpha$, blendMode, alpha); }
    }
    /*</methods>*/

}
