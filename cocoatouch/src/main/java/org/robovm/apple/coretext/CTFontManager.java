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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontManager/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CTFontManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean registerFonts(NSURL fontURL, CTFontManagerScope scope) {
        return registerFonts(fontURL, scope, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean unregisterFonts(NSURL fontURL, CTFontManagerScope scope) {
        return unregisterFonts(fontURL, scope, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean registerGraphicsFont(CGFont font) {
        return registerGraphicsFont(font, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean unregisterGraphicsFont(CGFont font) {
        return unregisterGraphicsFont(font, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean registerFonts(List<NSURL> fontURLs, CTFontManagerScope scope) {
        return registerFonts(new NSArray<>(fontURLs), scope, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean registerFonts(NSArray<NSURL> fontURLs, CTFontManagerScope scope) {
        return registerFonts(fontURLs, scope, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean unregisterFonts(List<NSURL> fontURLs, CTFontManagerScope scope) {
        return unregisterFonts(new NSArray<>(fontURLs), scope, null);
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean unregisterFonts(NSArray<NSURL> fontURLs, CTFontManagerScope scope) {
        return unregisterFonts(fontURLs, scope, null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontManagerCreateFontDescriptorsFromURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<CTFontDescriptor> createFontDescriptors0(NSURL fileURL);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontManagerCreateFontDescriptorFromData", optional=true)
    public static native CTFontDescriptor createFontDescriptor(NSData data);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerRegisterFontsForURL", optional=true)
    protected static native boolean registerFonts(NSURL fontURL, CTFontManagerScope scope, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerUnregisterFontsForURL", optional=true)
    protected static native boolean unregisterFonts(NSURL fontURL, CTFontManagerScope scope, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerRegisterGraphicsFont", optional=true)
    protected static native boolean registerGraphicsFont(CGFont font, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerUnregisterGraphicsFont", optional=true)
    protected static native boolean unregisterGraphicsFont(CGFont font, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerRegisterFontsForURLs", optional=true)
    protected static native boolean registerFonts(NSArray<NSURL> fontURLs, CTFontManagerScope scope, NSArray.NSArrayPtr errors);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerUnregisterFontsForURLs", optional=true)
    protected static native boolean unregisterFonts(NSArray<NSURL> fontURLs, CTFontManagerScope scope, NSArray.NSArrayPtr errors);
    /*</methods>*/
}
