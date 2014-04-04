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
    @Bridge(symbol="CGContextGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CGContextSaveGState", optional=true)
    public native void saveGState();
    @Bridge(symbol="CGContextRestoreGState", optional=true)
    public native void restoreGState();
    @Bridge(symbol="CGContextScaleCTM", optional=true)
    public native void scaleCTM(@MachineSizedFloat double sx, @MachineSizedFloat double sy);
    @Bridge(symbol="CGContextTranslateCTM", optional=true)
    public native void translateCTM(@MachineSizedFloat double tx, @MachineSizedFloat double ty);
    @Bridge(symbol="CGContextRotateCTM", optional=true)
    public native void rotateCTM(@MachineSizedFloat double angle);
    @Bridge(symbol="CGContextConcatCTM", optional=true)
    public native void concatCTM(@ByVal CGAffineTransform transform);
    @Bridge(symbol="CGContextGetCTM", optional=true)
    public native @ByVal CGAffineTransform getCTM();
    @Bridge(symbol="CGContextSetLineWidth", optional=true)
    public native void setLineWidth(@MachineSizedFloat double width);
    @Bridge(symbol="CGContextSetLineCap", optional=true)
    public native void setLineCap(CGLineCap cap);
    @Bridge(symbol="CGContextSetLineJoin", optional=true)
    public native void setLineJoin(CGLineJoin join);
    @Bridge(symbol="CGContextSetMiterLimit", optional=true)
    public native void setMiterLimit(@MachineSizedFloat double limit);
    @Bridge(symbol="CGContextSetLineDash", optional=true)
    protected native void setLineDash(@MachineSizedFloat double phase, @Pointer long lengths, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextSetFlatness", optional=true)
    public native void setFlatness(@MachineSizedFloat double flatness);
    @Bridge(symbol="CGContextSetAlpha", optional=true)
    public native void setAlpha(@MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetBlendMode", optional=true)
    public native void setBlendMode(CGBlendMode mode);
    @Bridge(symbol="CGContextBeginPath", optional=true)
    public native void beginPath();
    @Bridge(symbol="CGContextMoveToPoint", optional=true)
    public native void moveToPoint(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextAddLineToPoint", optional=true)
    public native void addLineToPoint(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextAddCurveToPoint", optional=true)
    public native void addCurveToPoint(@MachineSizedFloat double cp1x, @MachineSizedFloat double cp1y, @MachineSizedFloat double cp2x, @MachineSizedFloat double cp2y, @MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextAddQuadCurveToPoint", optional=true)
    public native void addQuadCurveToPoint(@MachineSizedFloat double cpx, @MachineSizedFloat double cpy, @MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextClosePath", optional=true)
    public native void closePath();
    @Bridge(symbol="CGContextAddRect", optional=true)
    public native void addRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextAddRects", optional=true)
    protected native void addRects(CGRect rects, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextAddLines", optional=true)
    protected native void addLines(CGPoint points, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextAddEllipseInRect", optional=true)
    public native void addEllipseInRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextAddArc", optional=true)
    public native void addArc(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double endAngle, int clockwise);
    @Bridge(symbol="CGContextAddArcToPoint", optional=true)
    public native void addArcToPoint(@MachineSizedFloat double x1, @MachineSizedFloat double y1, @MachineSizedFloat double x2, @MachineSizedFloat double y2, @MachineSizedFloat double radius);
    @Bridge(symbol="CGContextAddPath", optional=true)
    public native void addPath(CGPath path);
    @Bridge(symbol="CGContextReplacePathWithStrokedPath", optional=true)
    public native void replacePathWithStrokedPath();
    @Bridge(symbol="CGContextIsPathEmpty", optional=true)
    public native boolean isPathEmpty();
    @Bridge(symbol="CGContextGetPathCurrentPoint", optional=true)
    public native @ByVal CGPoint getPathCurrentPoint();
    @Bridge(symbol="CGContextGetPathBoundingBox", optional=true)
    public native @ByVal CGRect getPathBoundingBox();
    @Bridge(symbol="CGContextCopyPath", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath getPath();
    @Bridge(symbol="CGContextPathContainsPoint", optional=true)
    public native boolean pathContainsPoint(@ByVal CGPoint point, CGPathDrawingMode mode);
    @Bridge(symbol="CGContextDrawPath", optional=true)
    public native void drawPath(CGPathDrawingMode mode);
    @Bridge(symbol="CGContextFillPath", optional=true)
    public native void fillPath();
    @Bridge(symbol="CGContextEOFillPath", optional=true)
    public native void evenOddFillPath();
    @Bridge(symbol="CGContextStrokePath", optional=true)
    public native void strokePath();
    @Bridge(symbol="CGContextFillRect", optional=true)
    public native void fillRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextFillRects", optional=true)
    protected native void fillRects(CGRect rects, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextStrokeRect", optional=true)
    public native void strokeRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextStrokeRectWithWidth", optional=true)
    public native void strokeRectWithWidth(@ByVal CGRect rect, @MachineSizedFloat double width);
    @Bridge(symbol="CGContextClearRect", optional=true)
    public native void clearRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextFillEllipseInRect", optional=true)
    public native void fillEllipseInRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextStrokeEllipseInRect", optional=true)
    public native void strokeEllipseInRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextStrokeLineSegments", optional=true)
    protected native void strokeLineSegments(CGPoint points, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextClip", optional=true)
    public native void clip();
    @Bridge(symbol="CGContextEOClip", optional=true)
    public native void evenOddClip();
    @Bridge(symbol="CGContextClipToMask", optional=true)
    public native void clipToMask(@ByVal CGRect rect, CGImage mask);
    @Bridge(symbol="CGContextGetClipBoundingBox", optional=true)
    public native @ByVal CGRect getClipBoundingBox();
    @Bridge(symbol="CGContextClipToRect", optional=true)
    public native void clipToRect(@ByVal CGRect rect);
    @Bridge(symbol="CGContextClipToRects", optional=true)
    protected native void clipToRects(CGRect rects, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextSetFillColorWithColor", optional=true)
    public native void setFillColorWithColor(CGColor color);
    @Bridge(symbol="CGContextSetStrokeColorWithColor", optional=true)
    public native void setStrokeColorWithColor(CGColor color);
    @Bridge(symbol="CGContextSetFillColorSpace", optional=true)
    public native void setFillColorSpace(CGColorSpace space);
    @Bridge(symbol="CGContextSetStrokeColorSpace", optional=true)
    public native void setStrokeColorSpace(CGColorSpace space);
    @Bridge(symbol="CGContextSetFillColor", optional=true)
    protected native void setFillColor(@Pointer long components);
    @Bridge(symbol="CGContextSetStrokeColor", optional=true)
    protected native void setStrokeColor(@Pointer long components);
    @Bridge(symbol="CGContextSetFillPattern", optional=true)
    protected native void setFillPattern(CGPattern pattern, @Pointer long components);
    @Bridge(symbol="CGContextSetStrokePattern", optional=true)
    protected native void setStrokePattern(CGPattern pattern, @Pointer long components);
    @Bridge(symbol="CGContextSetPatternPhase", optional=true)
    public native void setPatternPhase(@ByVal CGSize phase);
    @Bridge(symbol="CGContextSetGrayFillColor", optional=true)
    public native void setGrayFillColor(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetGrayStrokeColor", optional=true)
    public native void setGrayStrokeColor(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetRGBFillColor", optional=true)
    public native void setRGBFillColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetRGBStrokeColor", optional=true)
    public native void setRGBStrokeColor(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetCMYKFillColor", optional=true)
    public native void setCMYKFillColor(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetCMYKStrokeColor", optional=true)
    public native void setCMYKStrokeColor(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGContextSetRenderingIntent", optional=true)
    public native void setRenderingIntent(CGColorRenderingIntent intent);
    @Bridge(symbol="CGContextDrawImage", optional=true)
    public native void drawImage(@ByVal CGRect rect, CGImage image);
    @Bridge(symbol="CGContextDrawTiledImage", optional=true)
    public native void drawTiledImage(@ByVal CGRect rect, CGImage image);
    @Bridge(symbol="CGContextGetInterpolationQuality", optional=true)
    public native CGInterpolationQuality getInterpolationQuality();
    @Bridge(symbol="CGContextSetInterpolationQuality", optional=true)
    public native void setInterpolationQuality(CGInterpolationQuality quality);
    @Bridge(symbol="CGContextSetShadowWithColor", optional=true)
    public native void setShadowWithColor(@ByVal CGSize offset, @MachineSizedFloat double blur, CGColor color);
    @Bridge(symbol="CGContextSetShadow", optional=true)
    public native void setShadow(@ByVal CGSize offset, @MachineSizedFloat double blur);
    @Bridge(symbol="CGContextDrawLinearGradient", optional=true)
    public native void drawLinearGradient(CGGradient gradient, @ByVal CGPoint startPoint, @ByVal CGPoint endPoint, CGGradientDrawingOptions options);
    @Bridge(symbol="CGContextDrawRadialGradient", optional=true)
    public native void drawRadialGradient(CGGradient gradient, @ByVal CGPoint startCenter, @MachineSizedFloat double startRadius, @ByVal CGPoint endCenter, @MachineSizedFloat double endRadius, CGGradientDrawingOptions options);
    @Bridge(symbol="CGContextDrawShading", optional=true)
    public native void drawShading(CGShading shading);
    @Bridge(symbol="CGContextSetCharacterSpacing", optional=true)
    public native void setCharacterSpacing(@MachineSizedFloat double spacing);
    @Bridge(symbol="CGContextSetTextPosition", optional=true)
    public native void setTextPosition(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Bridge(symbol="CGContextGetTextPosition", optional=true)
    public native @ByVal CGPoint getTextPosition();
    @Bridge(symbol="CGContextSetTextMatrix", optional=true)
    public native void setTextMatrix(@ByVal CGAffineTransform t);
    @Bridge(symbol="CGContextGetTextMatrix", optional=true)
    public native @ByVal CGAffineTransform getTextMatrix();
    @Bridge(symbol="CGContextSetTextDrawingMode", optional=true)
    public native void setTextDrawingMode(CGTextDrawingMode mode);
    @Bridge(symbol="CGContextSetFont", optional=true)
    public native void setFont(CGFont font);
    @Bridge(symbol="CGContextSetFontSize", optional=true)
    public native void setFontSize(@MachineSizedFloat double size);
    @Bridge(symbol="CGContextShowGlyphsAtPositions", optional=true)
    protected native void showGlyphsAtPositions(@Pointer long glyphs, CGPoint positions, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextDrawPDFPage", optional=true)
    public native void drawPDFPage(CGPDFPage page);
    @Bridge(symbol="CGContextBeginPage", optional=true)
    public native void beginPage(CGRect mediaBox);
    @Bridge(symbol="CGContextEndPage", optional=true)
    public native void endPage();
    @Bridge(symbol="CGContextFlush", optional=true)
    public native void flush();
    @Bridge(symbol="CGContextSynchronize", optional=true)
    public native void synchronize();
    @Bridge(symbol="CGContextSetShouldAntialias", optional=true)
    public native void setShouldAntialias(boolean shouldAntialias);
    @Bridge(symbol="CGContextSetAllowsAntialiasing", optional=true)
    public native void setAllowsAntialiasing(boolean allowsAntialiasing);
    @Bridge(symbol="CGContextSetShouldSmoothFonts", optional=true)
    public native void setShouldSmoothFonts(boolean shouldSmoothFonts);
    @Bridge(symbol="CGContextSetAllowsFontSmoothing", optional=true)
    public native void setAllowsFontSmoothing(boolean allowsFontSmoothing);
    @Bridge(symbol="CGContextSetShouldSubpixelPositionFonts", optional=true)
    public native void setShouldSubpixelPositionFonts(boolean shouldSubpixelPositionFonts);
    @Bridge(symbol="CGContextSetAllowsFontSubpixelPositioning", optional=true)
    public native void setAllowsFontSubpixelPositioning(boolean allowsFontSubpixelPositioning);
    @Bridge(symbol="CGContextSetShouldSubpixelQuantizeFonts", optional=true)
    public native void setShouldSubpixelQuantizeFonts(boolean shouldSubpixelQuantizeFonts);
    @Bridge(symbol="CGContextSetAllowsFontSubpixelQuantization", optional=true)
    public native void setAllowsFontSubpixelQuantization(boolean allowsFontSubpixelQuantization);
    @Bridge(symbol="CGContextBeginTransparencyLayer", optional=true)
    public native void beginTransparencyLayer(NSDictionary<?, ?> auxiliaryInfo);
    @Bridge(symbol="CGContextBeginTransparencyLayerWithRect", optional=true)
    public native void beginTransparencyLayerWithRect(@ByVal CGRect rect, NSDictionary<?, ?> auxiliaryInfo);
    @Bridge(symbol="CGContextEndTransparencyLayer", optional=true)
    public native void endTransparencyLayer();
    @Bridge(symbol="CGContextGetUserSpaceToDeviceSpaceTransform", optional=true)
    public native @ByVal CGAffineTransform getUserSpaceToDeviceSpaceTransform();
    @Bridge(symbol="CGContextConvertPointToDeviceSpace", optional=true)
    public native @ByVal CGPoint convertPointToDeviceSpace(@ByVal CGPoint point);
    @Bridge(symbol="CGContextConvertPointToUserSpace", optional=true)
    public native @ByVal CGPoint convertPointToUserSpace(@ByVal CGPoint point);
    @Bridge(symbol="CGContextConvertSizeToDeviceSpace", optional=true)
    public native @ByVal CGSize convertSizeToDeviceSpace(@ByVal CGSize size);
    @Bridge(symbol="CGContextConvertSizeToUserSpace", optional=true)
    public native @ByVal CGSize convertSizeToUserSpace(@ByVal CGSize size);
    @Bridge(symbol="CGContextConvertRectToDeviceSpace", optional=true)
    public native @ByVal CGRect convertRectToDeviceSpace(@ByVal CGRect rect);
    @Bridge(symbol="CGContextConvertRectToUserSpace", optional=true)
    public native @ByVal CGRect convertRectToUserSpace(@ByVal CGRect rect);
    @Bridge(symbol="CGContextSelectFont", optional=true)
    protected native void selectFont(@Pointer long name, @MachineSizedFloat double size, CGTextEncoding textEncoding);
    @Bridge(symbol="CGContextShowText", optional=true)
    protected native void showText(@Pointer long string, @MachineSizedUInt long length);
    @Bridge(symbol="CGContextShowTextAtPoint", optional=true)
    protected native void showTextAtPoint(@MachineSizedFloat double x, @MachineSizedFloat double y, @Pointer long string, @MachineSizedUInt long length);
    @Bridge(symbol="CGContextShowGlyphs", optional=true)
    protected native void showGlyphs(@Pointer long g, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextShowGlyphsAtPoint", optional=true)
    protected native void showGlyphsAtPoint(@MachineSizedFloat double x, @MachineSizedFloat double y, @Pointer long glyphs, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextShowGlyphsWithAdvances", optional=true)
    protected native void showGlyphsWithAdvances(@Pointer long glyphs, CGSize advances, @MachineSizedUInt long count);
    @Bridge(symbol="CGContextDrawPDFDocument", optional=true)
    public native void drawPDFDocument(@ByVal CGRect rect, CGPDFDocument document, int page);
    @Bridge(symbol="CGContextDrawLayerInRect", optional=true)
    public native void drawLayerInRect(@ByVal CGRect rect, CGLayer layer);
    @Bridge(symbol="CGContextDrawLayerAtPoint", optional=true)
    public native void drawLayerAtPoint(@ByVal CGPoint point, CGLayer layer);
    /*</methods>*/
}
