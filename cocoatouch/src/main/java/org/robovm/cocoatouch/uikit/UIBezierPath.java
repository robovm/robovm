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

    /*<constructors>*/
    public UIBezierPath() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/CGPath">@property(nonatomic) CGPathRef CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("CGPath") public native @Type("CGPathRef") CGPath getCGPath();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/CGPath">@property(nonatomic) CGPathRef CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setCGPath:") public native void setCGPath(@Type("CGPathRef") CGPath v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/bounds">@property(nonatomic, readonly) CGRect bounds</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bounds") public native @Type("CGRect") CGRect getBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/currentPoint">@property(nonatomic, readonly) CGPoint currentPoint</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("currentPoint") public native @Type("CGPoint") CGPoint getCurrentPoint();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/empty">@property(readonly, getter=isEmpty) BOOL empty</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isEmpty") public native @Type("BOOL") boolean isEmpty();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/flatness">@property(nonatomic) CGFloat flatness</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("flatness") public native @Type("CGFloat") float getFlatness();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/flatness">@property(nonatomic) CGFloat flatness</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setFlatness:") public native void setFlatness(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineCapStyle">@property(nonatomic) CGLineCap lineCapStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("lineCapStyle") public native @Type("CGLineCap") CGLineCap getLineCapStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineCapStyle">@property(nonatomic) CGLineCap lineCapStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineCapStyle:") public native void setLineCapStyle(@Type("CGLineCap") CGLineCap v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineJoinStyle">@property(nonatomic) CGLineJoin lineJoinStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("lineJoinStyle") public native @Type("CGLineJoin") CGLineJoin getLineJoinStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineJoinStyle">@property(nonatomic) CGLineJoin lineJoinStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineJoinStyle:") public native void setLineJoinStyle(@Type("CGLineJoin") CGLineJoin v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineWidth">@property(nonatomic) CGFloat lineWidth</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("lineWidth") public native @Type("CGFloat") float getLineWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/lineWidth">@property(nonatomic) CGFloat lineWidth</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineWidth:") public native void setLineWidth(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/miterLimit">@property(nonatomic) CGFloat miterLimit</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("miterLimit") public native @Type("CGFloat") float getMiterLimit();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/miterLimit">@property(nonatomic) CGFloat miterLimit</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMiterLimit:") public native void setMiterLimit(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/usesEvenOddFillRule">@property(nonatomic) BOOL usesEvenOddFillRule</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("usesEvenOddFillRule") public native @Type("BOOL") boolean isUsesEvenOddFillRule();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instp/UIBezierPath/usesEvenOddFillRule">@property(nonatomic) BOOL usesEvenOddFillRule</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setUsesEvenOddFillRule:") public native void setUsesEvenOddFillRule(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPath">+ (UIBezierPath *)bezierPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPath") public native static @Type("UIBezierPath *") UIBezierPath create();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:">+ (UIBezierPath *)bezierPathWithArcCenter:(CGPoint)center radius:(CGFloat)radius startAngle:(CGFloat)startAngle endAngle:(CGFloat)endAngle clockwise:(BOOL)clockwise</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:") public native static @Type("UIBezierPath *") UIBezierPath fromArc(@Type("CGPoint") CGPoint center, @Type("CGFloat") float radius, @Type("CGFloat") float startAngle, @Type("CGFloat") float endAngle, @Type("BOOL") boolean clockwise);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithOvalInRect:">+ (UIBezierPath *)bezierPathWithOvalInRect:(CGRect)rect</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPathWithOvalInRect:") public native static @Type("UIBezierPath *") UIBezierPath fromOval(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithCGPath:">+ (UIBezierPath *)bezierPathWithCGPath:(CGPathRef)CGPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPathWithCGPath:") public native static @Type("UIBezierPath *") UIBezierPath fromPath(@Type("CGPathRef") CGPath CGPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRect:">+ (UIBezierPath *)bezierPathWithRect:(CGRect)rect</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPathWithRect:") public native static @Type("UIBezierPath *") UIBezierPath fromRect(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:">+ (UIBezierPath *)bezierPathWithRoundedRect:(CGRect)rect byRoundingCorners:(UIRectCorner)corners cornerRadii:(CGSize)cornerRadii</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:") public native static @Type("UIBezierPath *") UIBezierPath fromRoundedRect(@Type("CGRect") CGRect rect, @Type("UIRectCorner") EnumSet<UIRectCorner> corners, @Type("CGSize") CGSize cornerRadii);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/clm/UIBezierPath/bezierPathWithRoundedRect:cornerRadius:">+ (UIBezierPath *)bezierPathWithRoundedRect:(CGRect)rect cornerRadius:(CGFloat)cornerRadius</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("bezierPathWithRoundedRect:cornerRadius:") public native static @Type("UIBezierPath *") UIBezierPath fromRoundedRect(@Type("CGRect") CGRect rect, @Type("CGFloat") float cornerRadius);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addArcWithCenter:radius:startAngle:endAngle:clockwise:">- (void)addArcWithCenter:(CGPoint)center radius:(CGFloat)radius startAngle:(CGFloat)startAngle endAngle:(CGFloat)endAngle clockwise:(BOOL)clockwise</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("addArcWithCenter:radius:startAngle:endAngle:clockwise:") public native @Type("void") void addArc(@Type("CGPoint") CGPoint center, @Type("CGFloat") float radius, @Type("CGFloat") float startAngle, @Type("CGFloat") float endAngle, @Type("BOOL") boolean clockwise);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addClip">- (void)addClip</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("addClip") public native @Type("void") void addClip();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addCurveToPoint:controlPoint1:controlPoint2:">- (void)addCurveToPoint:(CGPoint)endPoint controlPoint1:(CGPoint)controlPoint1 controlPoint2:(CGPoint)controlPoint2</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("addCurveToPoint:controlPoint1:controlPoint2:") public native @Type("void") void addCurve(@Type("CGPoint") CGPoint endPoint, @Type("CGPoint") CGPoint controlPoint1, @Type("CGPoint") CGPoint controlPoint2);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addLineToPoint:">- (void)addLineToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("addLineToPoint:") public native @Type("void") void addLine(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/addQuadCurveToPoint:controlPoint:">- (void)addQuadCurveToPoint:(CGPoint)endPoint controlPoint:(CGPoint)controlPoint</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("addQuadCurveToPoint:controlPoint:") public native @Type("void") void addQuadCurve(@Type("CGPoint") CGPoint endPoint, @Type("CGPoint") CGPoint controlPoint);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/appendPath:">- (void)appendPath:(UIBezierPath *)bezierPath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("appendPath:") public native @Type("void") void appendPath(@Type("UIBezierPath *") UIBezierPath bezierPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/applyTransform:">- (void)applyTransform:(CGAffineTransform)transform</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("applyTransform:") public native @Type("void") void applyTransform(@Type("CGAffineTransform") CGAffineTransform transform);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/closePath">- (void)closePath</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("closePath") public native @Type("void") void closePath();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/containsPoint:">- (BOOL)containsPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("containsPoint:") public native @Type("BOOL") boolean containsPoint(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/fillWithBlendMode:alpha:">- (void)fillWithBlendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("fillWithBlendMode:alpha:") public native @Type("void") void fill(@Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/fill">- (void)fill</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("fill") public native @Type("void") void fill();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/getLineDash:count:phase:">- (void)getLineDash:(CGFloat *)pattern count:(NSInteger *)count phase:(CGFloat *)phase</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("getLineDash:count:phase:") public native @Type("void") void getLineDash(@Type("CGFloat *") FloatPtr pattern, @Type("NSInteger *") IntPtr count, @Type("CGFloat *") FloatPtr phase);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/moveToPoint:">- (void)moveToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("moveToPoint:") public native @Type("void") void move(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/removeAllPoints">- (void)removeAllPoints</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("removeAllPoints") public native @Type("void") void removeAllPoints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/bezierPathByReversingPath">- (UIBezierPath *)bezierPathByReversingPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("bezierPathByReversingPath") public native @Type("UIBezierPath *") UIBezierPath reverse();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/setLineDash:count:phase:">- (void)setLineDash:(const CGFloat *)pattern count:(NSInteger)count phase:(CGFloat)phase</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setLineDash:count:phase:") public native @Type("void") void setLineDash(@Type("CGFloat *") FloatPtr pattern, @Type("NSInteger") int count, @Type("CGFloat") float phase);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/stroke">- (void)stroke</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("stroke") public native @Type("void") void stroke();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBezierPath_class/Reference/Reference.html#//apple_ref/occ/instm/UIBezierPath/strokeWithBlendMode:alpha:">- (void)strokeWithBlendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("strokeWithBlendMode:alpha:") public native @Type("void") void stroke(@Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    /*</methods>*/

}
