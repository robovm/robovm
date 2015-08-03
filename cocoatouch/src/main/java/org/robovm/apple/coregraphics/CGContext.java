/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.coregraphics;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGContext/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGContextPtr extends Ptr<CGContext, CGContextPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGContext() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    public void addRects(CGRect[] rects) {
        if (rects == null) {
            throw new NullPointerException("rects");
        }
        CGRect first = Struct.allocate(CGRect.class, rects.length);
        first.update(rects);
        addRects(first, rects.length);
    }
    public void addLines(CGPoint[] points) {
        if (points == null) {
            throw new NullPointerException("points");
        }
        CGPoint first = Struct.allocate(CGPoint.class, points.length);
        first.update(points);
        addLines(first, points.length);
    }
    public void clipToRects(CGRect[] rects) {
        if (rects == null) {
            throw new NullPointerException("rects");
        }
        CGRect first = Struct.allocate(CGRect.class, rects.length);
        first.update(rects);
        clipToRects(first, rects.length);
    }
    public void fillRects(CGRect[] rects) {
        if (rects == null) {
            throw new NullPointerException("rects");
        }
        CGRect first = Struct.allocate(CGRect.class, rects.length);
        first.update(rects);
        fillRects(first, rects.length);
    }
    public void setFillColor(double[] components) {
        setFillColor(VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setFillColor(float[] components) {
        setFillColor(VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setStrokeColor(double[] components) {
        setStrokeColor(VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setStrokeColor(float[] components) {
        setStrokeColor(VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setFillPattern(CGPattern pattern, double[] components) {
        setFillPattern(pattern, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setFillPattern(CGPattern pattern, float[] components) {
        setFillPattern(pattern, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setStrokePattern(CGPattern pattern, double[] components) {
        setStrokePattern(pattern, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setStrokePattern(CGPattern pattern, float[] components) {
        setStrokePattern(pattern, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    public void setLineDash(double phase, double[] lengths) {
        if (lengths == null) {
            setLineDash(phase, 0, 0);
        } else {
            setLineDash(phase, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(lengths)), lengths.length);
        }
    }
    public void setLineDash(double phase, float[] lengths, long count) {
        if (lengths == null) {
            setLineDash(phase, 0, 0);
        } else {
            setLineDash(phase, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(lengths)), lengths.length);
        }
    }
    public void strokeLineSegments(CGPoint[] points) {
        if (points == null) {
            throw new NullPointerException("points");
        }
        CGPoint first = Struct.allocate(CGPoint.class, points.length);
        first.update(points);
        strokeLineSegments(first, points.length);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public void selectFont(String name, double size, CGTextEncoding textEncoding) {
        selectFont(VM.getStringUTFChars(name), size, textEncoding);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public void showText(String string) {
        byte[] bytes = string.getBytes();
        showText(VM.getArrayValuesAddress(bytes), bytes.length);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public void showTextAtPoint(double x, double y, String string) {
        byte[] bytes = string.getBytes();
        showTextAtPoint(x, y, VM.getArrayValuesAddress(bytes), bytes.length);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public void showGlyphs(char[] glyphs) {
        showGlyphs(VM.getArrayValuesAddress(glyphs), glyphs.length);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public void showGlyphsAtPoint(double x, double y, char[] glyphs) {
        showGlyphsAtPoint(x, y, VM.getArrayValuesAddress(glyphs), glyphs.length);
    }
    public void showGlyphsAtPositions(char[] glyphs, CGPoint[] positions, long count) {
        if (glyphs.length != positions.length) {
            throw new IllegalArgumentException("glyphs.length != positions.length");
        }
        CGPoint first = Struct.allocate(CGPoint.class, positions.length);
        first.update(positions);
        showGlyphsAtPositions(VM.getArrayValuesAddress(glyphs), first, glyphs.length);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public void showGlyphsWithAdvances(char[] glyphs, CGSize[] advances) {
        if (glyphs.length != advances.length) {
            throw new IllegalArgumentException("glyphs.length != advances.length");
        }
        CGSize first = Struct.allocate(CGSize.class, advances.length);
        first.update(advances);
        showGlyphsWithAdvances(VM.getArrayValuesAddress(glyphs), first, glyphs.length);
    }    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSaveGState", optional=true)
    public native void saveGState();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextRestoreGState", optional=true)
    public native void restoreGState();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextScaleCTM", optional=true)
    public native void scaleCTM(@MachineSizedFloat double sx, @MachineSizedFloat double sy);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextTranslateCTM", optional=true)
    public native void translateCTM(@MachineSizedFloat double tx, @MachineSizedFloat double ty);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextRotateCTM", optional=true)
    public native void rotateCTM(@MachineSizedFloat double angle);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConcatCTM", optional=true)
    public native void concatCTM(@ByVal CGAffineTransform transform);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetCTM", optional=true)
    public native @ByVal CGAffineTransform getCTM();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetLineWidth", optional=true)
    public native void setLineWidth(@MachineSizedFloat double width);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetLineCap", optional=true)
    public native void setLineCap(CGLineCap cap);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetLineJoin", optional=true)
    public native void setLineJoin(CGLineJoin join);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetMiterLimit", optional=true)
    public native void setMiterLimit(@MachineSizedFloat double limit);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetLineDash", optional=true)
    private native void setLineDash(@MachineSizedFloat double phase, @Pointer long lengths, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFlatness", optional=true)
    public native void setFlatness(@MachineSizedFloat double flatness);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetAlpha", optional=true)
    public native void setAlpha(@MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetBlendMode", optional=true)
    public native void setBlendMode(CGBlendMode mode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextBeginPath", optional=true)
    public native void beginPath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextMoveToPoint", optional=true)
    public native void moveToPoint(@MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddLineToPoint", optional=true)
    public native void addLineToPoint(@MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddCurveToPoint", optional=true)
    public native void addCurveToPoint(@MachineSizedFloat double cp1x, @MachineSizedFloat double cp1y, @MachineSizedFloat double cp2x, @MachineSizedFloat double cp2y, @MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddQuadCurveToPoint", optional=true)
    public native void addQuadCurveToPoint(@MachineSizedFloat double cpx, @MachineSizedFloat double cpy, @MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextClosePath", optional=true)
    public native void closePath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddRect", optional=true)
    public native void addRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddRects", optional=true)
    private native void addRects(CGRect rects, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddLines", optional=true)
    private native void addLines(CGPoint points, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddEllipseInRect", optional=true)
    public native void addEllipseInRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddArc", optional=true)
    public native void addArc(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double endAngle, int clockwise);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddArcToPoint", optional=true)
    public native void addArcToPoint(@MachineSizedFloat double x1, @MachineSizedFloat double y1, @MachineSizedFloat double x2, @MachineSizedFloat double y2, @MachineSizedFloat double radius);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextAddPath", optional=true)
    public native void addPath(CGPath path);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextReplacePathWithStrokedPath", optional=true)
    public native void replacePathWithStrokedPath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextIsPathEmpty", optional=true)
    public native boolean isPathEmpty();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetPathCurrentPoint", optional=true)
    public native @ByVal CGPoint getPathCurrentPoint();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetPathBoundingBox", optional=true)
    public native @ByVal CGRect getPathBoundingBox();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextCopyPath", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath getPath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextPathContainsPoint", optional=true)
    public native boolean pathContainsPoint(@ByVal CGPoint point, CGPathDrawingMode mode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawPath", optional=true)
    public native void drawPath(CGPathDrawingMode mode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextFillPath", optional=true)
    public native void fillPath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextEOFillPath", optional=true)
    public native void evenOddFillPath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextStrokePath", optional=true)
    public native void strokePath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextFillRect", optional=true)
    public native void fillRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextFillRects", optional=true)
    private native void fillRects(CGRect rects, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextStrokeRect", optional=true)
    public native void strokeRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextStrokeRectWithWidth", optional=true)
    public native void strokeRect(@ByVal CGRect rect, @MachineSizedFloat double width);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextClearRect", optional=true)
    public native void clearRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextFillEllipseInRect", optional=true)
    public native void fillEllipseInRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextStrokeEllipseInRect", optional=true)
    public native void strokeEllipseInRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextStrokeLineSegments", optional=true)
    private native void strokeLineSegments(CGPoint points, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextClip", optional=true)
    public native void clip();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextEOClip", optional=true)
    public native void evenOddClip();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextClipToMask", optional=true)
    public native void clipToMask(@ByVal CGRect rect, CGImage mask);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetClipBoundingBox", optional=true)
    public native @ByVal CGRect getClipBoundingBox();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextClipToRect", optional=true)
    public native void clipToRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextClipToRects", optional=true)
    private native void clipToRects(CGRect rects, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFillColorWithColor", optional=true)
    public native void setFillColor(CGColor color);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetStrokeColorWithColor", optional=true)
    public native void setStrokeColor(CGColor color);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFillColorSpace", optional=true)
    public native void setFillColorSpace(CGColorSpace space);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetStrokeColorSpace", optional=true)
    public native void setStrokeColorSpace(CGColorSpace space);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFillColor", optional=true)
    private native void setFillColor(@Pointer long components);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetStrokeColor", optional=true)
    private native void setStrokeColor(@Pointer long components);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFillPattern", optional=true)
    private native void setFillPattern(CGPattern pattern, @Pointer long components);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetStrokePattern", optional=true)
    private native void setStrokePattern(CGPattern pattern, @Pointer long components);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetPatternPhase", optional=true)
    public native void setPatternPhase(@ByVal CGSize phase);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetGrayFillColor", optional=true)
    public native void setGrayFillColor(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetGrayStrokeColor", optional=true)
    public native void setGrayStrokeColor(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetRGBFillColor", optional=true)
    public native void setRGBFillColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetRGBStrokeColor", optional=true)
    public native void setRGBStrokeColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetCMYKFillColor", optional=true)
    public native void setCMYKFillColor(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetCMYKStrokeColor", optional=true)
    public native void setCMYKStrokeColor(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetRenderingIntent", optional=true)
    public native void setRenderingIntent(CGColorRenderingIntent intent);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawImage", optional=true)
    public native void drawImage(@ByVal CGRect rect, CGImage image);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawTiledImage", optional=true)
    public native void drawTiledImage(@ByVal CGRect rect, CGImage image);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetInterpolationQuality", optional=true)
    public native CGInterpolationQuality getInterpolationQuality();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetInterpolationQuality", optional=true)
    public native void setInterpolationQuality(CGInterpolationQuality quality);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetShadowWithColor", optional=true)
    public native void setShadow(@ByVal CGSize offset, @MachineSizedFloat double blur, CGColor color);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetShadow", optional=true)
    public native void setShadow(@ByVal CGSize offset, @MachineSizedFloat double blur);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawLinearGradient", optional=true)
    public native void drawLinearGradient(CGGradient gradient, @ByVal CGPoint startPoint, @ByVal CGPoint endPoint, CGGradientDrawingOptions options);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawRadialGradient", optional=true)
    public native void drawRadialGradient(CGGradient gradient, @ByVal CGPoint startCenter, @MachineSizedFloat double startRadius, @ByVal CGPoint endCenter, @MachineSizedFloat double endRadius, CGGradientDrawingOptions options);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawShading", optional=true)
    public native void drawShading(CGShading shading);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetCharacterSpacing", optional=true)
    public native void setCharacterSpacing(@MachineSizedFloat double spacing);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetTextPosition", optional=true)
    public native void setTextPosition(@MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetTextPosition", optional=true)
    public native @ByVal CGPoint getTextPosition();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetTextMatrix", optional=true)
    public native void setTextMatrix(@ByVal CGAffineTransform t);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetTextMatrix", optional=true)
    public native @ByVal CGAffineTransform getTextMatrix();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetTextDrawingMode", optional=true)
    public native void setTextDrawingMode(CGTextDrawingMode mode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFont", optional=true)
    public native void setFont(CGFont font);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetFontSize", optional=true)
    public native void setFontSize(@MachineSizedFloat double size);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextShowGlyphsAtPositions", optional=true)
    private native void showGlyphsAtPositions(@Pointer long glyphs, CGPoint positions, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawPDFPage", optional=true)
    public native void drawPDFPage(CGPDFPage page);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextBeginPage", optional=true)
    public native void beginPage(CGRect mediaBox);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextEndPage", optional=true)
    public native void endPage();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextFlush", optional=true)
    public native void flush();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSynchronize", optional=true)
    public native void synchronize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetShouldAntialias", optional=true)
    public native void setShouldAntialias(boolean shouldAntialias);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetAllowsAntialiasing", optional=true)
    public native void setAllowsAntialiasing(boolean allowsAntialiasing);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetShouldSmoothFonts", optional=true)
    public native void setShouldSmoothFonts(boolean shouldSmoothFonts);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetAllowsFontSmoothing", optional=true)
    public native void setAllowsFontSmoothing(boolean allowsFontSmoothing);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetShouldSubpixelPositionFonts", optional=true)
    public native void setShouldSubpixelPositionFonts(boolean shouldSubpixelPositionFonts);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetAllowsFontSubpixelPositioning", optional=true)
    public native void setAllowsFontSubpixelPositioning(boolean allowsFontSubpixelPositioning);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetShouldSubpixelQuantizeFonts", optional=true)
    public native void setShouldSubpixelQuantizeFonts(boolean shouldSubpixelQuantizeFonts);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextSetAllowsFontSubpixelQuantization", optional=true)
    public native void setAllowsFontSubpixelQuantization(boolean allowsFontSubpixelQuantization);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextBeginTransparencyLayer", optional=true)
    public native void beginTransparencyLayer(NSDictionary auxiliaryInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextBeginTransparencyLayerWithRect", optional=true)
    public native void beginTransparencyLayer(@ByVal CGRect rect, NSDictionary auxiliaryInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextEndTransparencyLayer", optional=true)
    public native void endTransparencyLayer();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextGetUserSpaceToDeviceSpaceTransform", optional=true)
    public native @ByVal CGAffineTransform getUserSpaceToDeviceSpaceTransform();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConvertPointToDeviceSpace", optional=true)
    public native @ByVal CGPoint convertPointToDeviceSpace(@ByVal CGPoint point);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConvertPointToUserSpace", optional=true)
    public native @ByVal CGPoint convertPointToUserSpace(@ByVal CGPoint point);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConvertSizeToDeviceSpace", optional=true)
    public native @ByVal CGSize convertSizeToDeviceSpace(@ByVal CGSize size);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConvertSizeToUserSpace", optional=true)
    public native @ByVal CGSize convertSizeToUserSpace(@ByVal CGSize size);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConvertRectToDeviceSpace", optional=true)
    public native @ByVal CGRect convertRectToDeviceSpace(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextConvertRectToUserSpace", optional=true)
    public native @ByVal CGRect convertRectToUserSpace(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CGContextSelectFont", optional=true)
    private native void selectFont(@Pointer long name, @MachineSizedFloat double size, CGTextEncoding textEncoding);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CGContextShowText", optional=true)
    private native void showText(@Pointer long string, @MachineSizedUInt long length);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CGContextShowTextAtPoint", optional=true)
    private native void showTextAtPoint(@MachineSizedFloat double x, @MachineSizedFloat double y, @Pointer long string, @MachineSizedUInt long length);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CGContextShowGlyphs", optional=true)
    private native void showGlyphs(@Pointer long g, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CGContextShowGlyphsAtPoint", optional=true)
    private native void showGlyphsAtPoint(@MachineSizedFloat double x, @MachineSizedFloat double y, @Pointer long glyphs, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CGContextShowGlyphsWithAdvances", optional=true)
    private native void showGlyphsWithAdvances(@Pointer long glyphs, CGSize advances, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawLayerInRect", optional=true)
    public native void drawLayerInRect(@ByVal CGRect rect, CGLayer layer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGContextDrawLayerAtPoint", optional=true)
    public native void drawLayerAtPoint(@ByVal CGPoint point, CGLayer layer);
    /*</methods>*/
}
