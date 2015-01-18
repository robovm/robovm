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
package org.robovm.apple.coretext;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTRun/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTRunPtr extends Ptr<CTRun, CTRunPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTRun.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTRun() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public short[] getGlyphs(@ByVal CFRange range) {
        ShortPtr ptr = new ShortPtr();
        getGlyphs(range, ptr);
        return ptr.toShortArray((int) getGlyphCount());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint[] getPositions(@ByVal CFRange range) {
        CGPoint p = Struct.allocate(CGPoint.class, (int)range.getLength());
        getPositions(range, p);
        return p.toArray((int) getGlyphCount());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CGSize[] getAdvances(@ByVal CFRange range) {
        CGSize s = Struct.allocate(CGSize.class, (int)range.getLength());
        getAdvances(range, s);
        return s.toArray((int) getGlyphCount());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public long[] getStringIndices(@ByVal CFRange range) {
        LongPtr ptr = new LongPtr();
        getStringIndices(range, ptr);
        return ptr.toLongArray((int) getGlyphCount());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @MachineSizedFloat double getWidth(@ByVal CFRange range) {
        return getTypographicBounds(range, null, null, null);
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @MachineSizedFloat double getAscent(@ByVal CFRange range) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getTypographicBounds(range, ptr, null, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @MachineSizedFloat double getDescent(@ByVal CFRange range) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getTypographicBounds(range, null, ptr, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @MachineSizedFloat double getLeading(@ByVal CFRange range) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getTypographicBounds(range, null, null, ptr);
        return ptr.get();
    }    
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetGlyphCount", optional=true)
    public native @MachineSizedSInt long getGlyphCount();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetAttributes", optional=true)
    public native CFDictionary getAttributes();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetStatus", optional=true)
    public native CTRunStatus getStatus();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetGlyphs", optional=true)
    protected native void getGlyphs(@ByVal CFRange range, ShortPtr buffer);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetPositions", optional=true)
    protected native void getPositions(@ByVal CFRange range, CGPoint buffer);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetAdvances", optional=true)
    protected native void getAdvances(@ByVal CFRange range, CGSize buffer);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetStringIndices", optional=true)
    protected native void getStringIndices(@ByVal CFRange range, LongPtr buffer);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetStringRange", optional=true)
    public native @ByVal CFRange getStringRange();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetTypographicBounds", optional=true)
    protected native double getTypographicBounds(@ByVal CFRange range, MachineSizedFloatPtr ascent, MachineSizedFloatPtr descent, MachineSizedFloatPtr leading);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetImageBounds", optional=true)
    public native @ByVal CGRect getImageBounds(CGContext context, @ByVal CFRange range);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunGetTextMatrix", optional=true)
    public native @ByVal CGAffineTransform getTextMatrix();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTRunDraw", optional=true)
    public native void draw(CGContext context, @ByVal CFRange range);
    /*</methods>*/
}
