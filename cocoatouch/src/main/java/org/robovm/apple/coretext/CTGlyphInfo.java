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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTGlyphInfo/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTGlyphInfoPtr extends Ptr<CTGlyphInfo, CTGlyphInfoPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTGlyphInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTGlyphInfo() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoCreateWithGlyphName", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTGlyphInfo create(String glyphName, CTFont font, String baseString);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoCreateWithGlyph", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTGlyphInfo create(short glyph, CTFont font, String baseString);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoCreateWithCharacterIdentifier", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTGlyphInfo create(short cid, CTCharacterCollection collection, String baseString);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoGetGlyphName", optional=true)
    public native String getGlyphName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoGetCharacterIdentifier", optional=true)
    public native short getCharacterIdentifier();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGlyphInfoGetCharacterCollection", optional=true)
    public native CTCharacterCollection getCharacterCollection();
    /*</methods>*/
}
