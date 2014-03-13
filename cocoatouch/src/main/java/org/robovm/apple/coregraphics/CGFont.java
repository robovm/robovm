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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGFont/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGFontPtr extends Ptr<CGFont, CGFontPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGFont.class); }/*</bind>*/
    /*<constants>*/
    public static final int FontIndexMax = 65534;
    public static final int FontIndexInvalid = 65535;
    public static final int GlyphMax = 65534;
    /*</constants>*/
    /*<constructors>*/
    protected CGFont() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    public int[] getGlyphAdvances(char[] glyphs) {
        if (glyphs == null) {
            throw new NullPointerException("glyphs");
        }
        int count = glyphs.length;
        int[] advances = new int[count];
        ShortPtr glyphsPtr = Struct.toStruct(ShortPtr.class, VM.getArrayValuesAddress(glyphs));
        IntPtr advancesPtr = Struct.toStruct(IntPtr.class, VM.getArrayValuesAddress(advances));
        if (getGlyphAdvances(glyphsPtr, count, advancesPtr)) {
            return advances;
        }
        return null;
    }
    
    public CGRect[] getGlyphBBoxes(char[] glyphs) {
        if (glyphs == null) {
            throw new NullPointerException("glyphs");
        }
        int count = glyphs.length;
        CGRect bboxes = Struct.allocate(CGRect.class, count);
        ShortPtr glyphsPtr = Struct.toStruct(ShortPtr.class, VM.getArrayValuesAddress(glyphs));
        if (getGlyphBBoxes(glyphsPtr, count, bboxes)) {
            return bboxes.toArray(count);
        }
        return null;
    }
    
    /*<methods>*/
    @Bridge(symbol="CGFontGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CGFontCreateWithDataProvider")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGFont create(CGDataProvider provider);
    @Bridge(symbol="CGFontCreateWithFontName")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGFont create(String name);
    @Bridge(symbol="CGFontGetNumberOfGlyphs")
    public native @MachineSizedUInt long getNumberOfGlyphs();
    @Bridge(symbol="CGFontGetUnitsPerEm")
    public native int getUnitsPerEm();
    @Bridge(symbol="CGFontCopyPostScriptName")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getPostScriptName();
    @Bridge(symbol="CGFontCopyFullName")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getFullName();
    @Bridge(symbol="CGFontGetAscent")
    public native int getAscent();
    @Bridge(symbol="CGFontGetDescent")
    public native int getDescent();
    @Bridge(symbol="CGFontGetLeading")
    public native int getLeading();
    @Bridge(symbol="CGFontGetCapHeight")
    public native int getCapHeight();
    @Bridge(symbol="CGFontGetXHeight")
    public native int getXHeight();
    @Bridge(symbol="CGFontGetFontBBox")
    public native @ByVal CGRect getFontBBox();
    @Bridge(symbol="CGFontGetItalicAngle")
    public native @MachineSizedFloat double getItalicAngle();
    @Bridge(symbol="CGFontGetStemV")
    public native @MachineSizedFloat double getStemV();
    @Bridge(symbol="CGFontGetGlyphAdvances")
    protected native boolean getGlyphAdvances(ShortPtr glyphs, @MachineSizedUInt long count, IntPtr advances);
    @Bridge(symbol="CGFontGetGlyphBBoxes")
    protected native boolean getGlyphBBoxes(ShortPtr glyphs, @MachineSizedUInt long count, CGRect bboxes);
    @Bridge(symbol="CGFontGetGlyphWithGlyphName")
    public native char getGlyphWithGlyphName(String name);
    @Bridge(symbol="CGFontCopyGlyphNameForGlyph")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getGlyphNameForGlyph(char glyph);
    /*</methods>*/
}
