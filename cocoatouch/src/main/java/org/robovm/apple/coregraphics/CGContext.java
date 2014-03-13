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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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

    @Deprecated
    public void selectFont(String name, double size, CGTextEncoding textEncoding) {
        selectFont(VM.getStringUTFChars(name), size, textEncoding);
    }
    
    @Deprecated
    public void showText(String string) {
        byte[] bytes = string.getBytes();
        showText(VM.getArrayValuesAddress(bytes), bytes.length);
    }
    
    @Deprecated
    public void showTextAtPoint(double x, double y, String string) {
        byte[] bytes = string.getBytes();
        showTextAtPoint(x, y, VM.getArrayValuesAddress(bytes), bytes.length);
    }

    @Deprecated
    public void showGlyphs(char[] glyphs) {
        showGlyphs(VM.getArrayValuesAddress(glyphs), glyphs.length);
    }
    
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
    @Bridge(symbol="CGContextGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CGContextSaveGState")
    public native void saveGState();
    @Bridge(symbol="CGContextRestoreGState")
    public native void restoreGState();
    @Bridge(symbol="CGContextScaleCTM")
    public native void scaleCTM(@MachineSizedFloat double sx, @MachineSizedFloat double sy);
    @Bridge(symbol="CGContextTranslateCTM")
    public native void translateCTM(@MachineSizedFloat double tx, @MachineSizedFloat double ty);
    @Bridge(symbol="CGContextRotateCTM")
    public native void rotateCTM(@MachineSizedFloat double angle);
    @Bridge(symbol="CGContextConcatCTM")
    public native void concatCTM(@ByVal CGAffineTransform transform);
    @Bridge(symbol="CGContextGetCTM")
    public native @ByVal CGAffineTransform getCTM();
    @Bridge(symbol="CGContextSetLineWidth")
    public native void setLineWidth(@MachineSizedFloat double width);
    @Bridge(symbol="CGContextSetLineCap")
    public native void setLineCap(CGLineCap cap);
    @Bridge(symbol="CGContextSetLineJoin")
    public native void setLineJoin(CGLineJoin join);
    @Bridge(symbol="CGContextSetMiterLimit")
    public native void setMiterLimit(@MachineSizedFloat double limit);
    @Bridge(symbol="CGContextSetLineDash")
    protected native void setLineDash(@MachineSizedFloat double phase, @Pointer long lengths, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextSetFlatness")
    public native void setFlatness(@MachineSizedFloat double flatness);
    @Bridge(symbol="CGContextSetAlpha")
    public native void setAlpha(@MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetBlendMode")
    public native void setBlendMode(CGBlendMode mode);
    @Bridge(symbol="CGContextBeginPath")
    public native void beginPath();
    @Bridge(symbol="CGContextMoveToPoint")
    public native void moveToPoint(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextAddLineToPoint")
    public native void addLineToPoint(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextAddCurveToPoint")
    public native void addCurveToPoint(@MachineSizedFloat double cp1x, @MachineSizedFloat double cp1y, @MachineSizedFloat double cp2x, @MachineSizedFloat double cp2y, @MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextAddQuadCurveToPoint")
    public native void addQuadCurveToPoint(@MachineSizedFloat double cpx, @MachineSizedFloat double cpy, @MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextClosePath")
    public native void closePath();
    @Bridge(symbol="CGContextAddRect")
    public native void addRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextAddRects")
    protected native void addRects(CGRect rects, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextAddLines")
    protected native void addLines(CGPoint points, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextAddEllipseInRect")
    public native void addEllipseInRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextAddArc")
    public native void addArc(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double endAngle, int clockwise);
    @Bridge(symbol="CGContextAddArcToPoint")
    public native void addArcToPoint(@MachineSizedFloat double x1, @MachineSizedFloat double y1, @MachineSizedFloat double x2, @MachineSizedFloat double y2, @MachineSizedFloat double radius);
    @Bridge(symbol="CGContextAddPath")
    public native void addPath(CGPath path);
    @Bridge(symbol="CGContextReplacePathWithStrokedPath")
    public native void replacePathWithStrokedPath();
    @Bridge(symbol="CGContextIsPathEmpty")
    public native boolean isPathEmpty();
    @Bridge(symbol="CGContextGetPathCurrentPoint")
    public native @ByVal CGPoint getPathCurrentPoint();
    @Bridge(symbol="CGContextGetPathBoundingBox")
    public native @ByVal CGRect getPathBoundingBox();
    @Bridge(symbol="CGContextCopyPath")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath getPath();
    @Bridge(symbol="CGContextPathContainsPoint")
    public native boolean pathContainsPoint(@ByVal CGPoint point, CGPathDrawingMode mode);
    @Bridge(symbol="CGContextDrawPath")
    public native void drawPath(CGPathDrawingMode mode);
    @Bridge(symbol="CGContextFillPath")
    public native void fillPath();
    @Bridge(symbol="CGContextEOFillPath")
    public native void evenOddFillPath();
    @Bridge(symbol="CGContextStrokePath")
    public native void strokePath();
    @Bridge(symbol="CGContextFillRect")
    public native void fillRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextFillRects")
    protected native void fillRects(CGRect rects, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextStrokeRect")
    public native void strokeRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextStrokeRectWithWidth")
    public native void strokeRectWithWidth(@ByVal CGRect rect, @MachineSizedFloat double width);
    @Bridge(symbol="CGContextClearRect")
    public native void clearRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextFillEllipseInRect")
    public native void fillEllipseInRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextStrokeEllipseInRect")
    public native void strokeEllipseInRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextStrokeLineSegments")
    protected native void strokeLineSegments(CGPoint points, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextClip")
    public native void clip();
    @Bridge(symbol="CGContextEOClip")
    public native void evenOddClip();
    @Bridge(symbol="CGContextClipToMask")
    public native void clipToMask(@ByVal CGRect rect, CGImage mask);
    @Bridge(symbol="CGContextGetClipBoundingBox")
    public native @ByVal CGRect getClipBoundingBox();
    @Bridge(symbol="CGContextClipToRect")
    public native void clipToRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextClipToRects")
    protected native void clipToRects(CGRect rects, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextSetFillColorWithColor")
    public native void setFillColorWithColor(CGColor color);
    @Bridge(symbol="CGContextSetStrokeColorWithColor")
    public native void setStrokeColorWithColor(CGColor color);
    @Bridge(symbol="CGContextSetFillColorSpace")
    public native void setFillColorSpace(CGColorSpace space);
    @Bridge(symbol="CGContextSetStrokeColorSpace")
    public native void setStrokeColorSpace(CGColorSpace space);
    @Bridge(symbol="CGContextSetFillColor")
    protected native void setFillColor(@Pointer long components);
    @Bridge(symbol="CGContextSetStrokeColor")
    protected native void setStrokeColor(@Pointer long components);
    @Bridge(symbol="CGContextSetFillPattern")
    protected native void setFillPattern(CGPattern pattern, @Pointer long components);
    @Bridge(symbol="CGContextSetStrokePattern")
    protected native void setStrokePattern(CGPattern pattern, @Pointer long components);
    @Bridge(symbol="CGContextSetPatternPhase")
    public native void setPatternPhase(@ByVal CGSize phase);
    @Bridge(symbol="CGContextSetGrayFillColor")
    public native void setGrayFillColor(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetGrayStrokeColor")
    public native void setGrayStrokeColor(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetRGBFillColor")
    public native void setRGBFillColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetRGBStrokeColor")
    public native void setRGBStrokeColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetCMYKFillColor")
    public native void setCMYKFillColor(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetCMYKStrokeColor")
    public native void setCMYKStrokeColor(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetRenderingIntent")
    public native void setRenderingIntent(CGColorRenderingIntent intent);
    @Bridge(symbol="CGContextDrawImage")
    public native void drawImage(@ByVal CGRect rect, CGImage image);
    @Bridge(symbol="CGContextDrawTiledImage")
    public native void drawTiledImage(@ByVal CGRect rect, CGImage image);
    @Bridge(symbol="CGContextGetInterpolationQuality")
    public native CGInterpolationQuality getInterpolationQuality();
    @Bridge(symbol="CGContextSetInterpolationQuality")
    public native void setInterpolationQuality(CGInterpolationQuality quality);
    @Bridge(symbol="CGContextSetShadowWithColor")
    public native void setShadowWithColor(@ByVal CGSize offset, @MachineSizedFloat double blur, CGColor color);
    @Bridge(symbol="CGContextSetShadow")
    public native void setShadow(@ByVal CGSize offset, @MachineSizedFloat double blur);
    @Bridge(symbol="CGContextDrawLinearGradient")
    public native void drawLinearGradient(CGGradient gradient, @ByVal CGPoint startPoint, @ByVal CGPoint endPoint, CGGradientDrawingOptions options);
    @Bridge(symbol="CGContextDrawRadialGradient")
    public native void drawRadialGradient(CGGradient gradient, @ByVal CGPoint startCenter, @MachineSizedFloat double startRadius, @ByVal CGPoint endCenter, @MachineSizedFloat double endRadius, CGGradientDrawingOptions options);
    @Bridge(symbol="CGContextDrawShading")
    public native void drawShading(CGShading shading);
    @Bridge(symbol="CGContextSetCharacterSpacing")
    public native void setCharacterSpacing(@MachineSizedFloat double spacing);
    @Bridge(symbol="CGContextSetTextPosition")
    public native void setTextPosition(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextGetTextPosition")
    public native @ByVal CGPoint getTextPosition();
    @Bridge(symbol="CGContextSetTextMatrix")
    public native void setTextMatrix(@ByVal CGAffineTransform t);
    @Bridge(symbol="CGContextGetTextMatrix")
    public native @ByVal CGAffineTransform getTextMatrix();
    @Bridge(symbol="CGContextSetTextDrawingMode")
    public native void setTextDrawingMode(CGTextDrawingMode mode);
    @Bridge(symbol="CGContextSetFont")
    public native void setFont(CGFont font);
    @Bridge(symbol="CGContextSetFontSize")
    public native void setFontSize(@MachineSizedFloat double size);
    @Bridge(symbol="CGContextShowGlyphsAtPositions")
    protected native void showGlyphsAtPositions(@Pointer long glyphs, CGPoint positions, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextDrawPDFPage")
    public native void drawPDFPage(CGPDFPage page);
    @Bridge(symbol="CGContextBeginPage")
    public native void beginPage(CGRect mediaBox);
    @Bridge(symbol="CGContextEndPage")
    public native void endPage();
    @Bridge(symbol="CGContextFlush")
    public native void flush();
    @Bridge(symbol="CGContextSynchronize")
    public native void synchronize();
    @Bridge(symbol="CGContextSetShouldAntialias")
    public native void setShouldAntialias(boolean shouldAntialias);
    @Bridge(symbol="CGContextSetAllowsAntialiasing")
    public native void setAllowsAntialiasing(boolean allowsAntialiasing);
    @Bridge(symbol="CGContextSetShouldSmoothFonts")
    public native void setShouldSmoothFonts(boolean shouldSmoothFonts);
    @Bridge(symbol="CGContextSetAllowsFontSmoothing")
    public native void setAllowsFontSmoothing(boolean allowsFontSmoothing);
    @Bridge(symbol="CGContextSetShouldSubpixelPositionFonts")
    public native void setShouldSubpixelPositionFonts(boolean shouldSubpixelPositionFonts);
    @Bridge(symbol="CGContextSetAllowsFontSubpixelPositioning")
    public native void setAllowsFontSubpixelPositioning(boolean allowsFontSubpixelPositioning);
    @Bridge(symbol="CGContextSetShouldSubpixelQuantizeFonts")
    public native void setShouldSubpixelQuantizeFonts(boolean shouldSubpixelQuantizeFonts);
    @Bridge(symbol="CGContextSetAllowsFontSubpixelQuantization")
    public native void setAllowsFontSubpixelQuantization(boolean allowsFontSubpixelQuantization);
    @Bridge(symbol="CGContextBeginTransparencyLayer")
    public native void beginTransparencyLayer(NSDictionary<?, ?> auxiliaryInfo);
    @Bridge(symbol="CGContextBeginTransparencyLayerWithRect")
    public native void beginTransparencyLayerWithRect(@ByVal CGRect rect, NSDictionary<?, ?> auxiliaryInfo);
    @Bridge(symbol="CGContextEndTransparencyLayer")
    public native void endTransparencyLayer();
    @Bridge(symbol="CGContextGetUserSpaceToDeviceSpaceTransform")
    public native @ByVal CGAffineTransform getUserSpaceToDeviceSpaceTransform();
    @Bridge(symbol="CGContextConvertPointToDeviceSpace")
    public native @ByVal CGPoint convertPointToDeviceSpace(@ByVal CGPoint point);
    @Bridge(symbol="CGContextConvertPointToUserSpace")
    public native @ByVal CGPoint convertPointToUserSpace(@ByVal CGPoint point);
    @Bridge(symbol="CGContextConvertSizeToDeviceSpace")
    public native @ByVal CGSize convertSizeToDeviceSpace(@ByVal CGSize size);
    @Bridge(symbol="CGContextConvertSizeToUserSpace")
    public native @ByVal CGSize convertSizeToUserSpace(@ByVal CGSize size);
    @Bridge(symbol="CGContextConvertRectToDeviceSpace")
    public native @ByVal CGRect convertRectToDeviceSpace(@ByVal CGRect rect);
    @Bridge(symbol="CGContextConvertRectToUserSpace")
    public native @ByVal CGRect convertRectToUserSpace(@ByVal CGRect rect);
    @Bridge(symbol="CGContextSelectFont")
    protected native void selectFont(@Pointer long name, @MachineSizedFloat double size, CGTextEncoding textEncoding);
    @Bridge(symbol="CGContextShowText")
    protected native void showText(@Pointer long string, @MachineSizedUInt long length);
    @Bridge(symbol="CGContextShowTextAtPoint")
    protected native void showTextAtPoint(@MachineSizedFloat double x, @MachineSizedFloat double y, @Pointer long string, @MachineSizedUInt long length);
    @Bridge(symbol="CGContextShowGlyphs")
    protected native void showGlyphs(@Pointer long g, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextShowGlyphsAtPoint")
    protected native void showGlyphsAtPoint(@MachineSizedFloat double x, @MachineSizedFloat double y, @Pointer long glyphs, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextShowGlyphsWithAdvances")
    protected native void showGlyphsWithAdvances(@Pointer long glyphs, CGSize advances, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextDrawPDFDocument")
    public native void drawPDFDocument(@ByVal CGRect rect, CGPDFDocument document, int page);
    @Bridge(symbol="CGContextDrawLayerInRect")
    public native void drawLayerInRect(@ByVal CGRect rect, CGLayer layer);
    @Bridge(symbol="CGContextDrawLayerAtPoint")
    public native void drawLayerAtPoint(@ByVal CGPoint point, CGLayer layer);
    /*</methods>*/
}
