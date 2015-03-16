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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontManager/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeRegisteredFontsChanged(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(RegisteredFontsChangedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.run();
                }
            });
        }
    }

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
    @GlobalValue(symbol="kCTFontManagerRegisteredFontsChangedNotification", optional=true)
    public static native NSString RegisteredFontsChangedNotification();
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontManagerCreateFontDescriptorsFromURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CTFontDescriptor.AsListMarshaler.class) List<CTFontDescriptor> createFontDescriptors(NSURL fileURL);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontManagerCreateFontDescriptorFromData", optional=true)
    public static native CTFontDescriptor createFontDescriptor(NSData data);
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean registerFonts(NSURL fontURL, CTFontManagerScope scope) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = registerFonts(fontURL, scope, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerRegisterFontsForURL", optional=true)
    private static native boolean registerFonts(NSURL fontURL, CTFontManagerScope scope, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean unregisterFonts(NSURL fontURL, CTFontManagerScope scope) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = unregisterFonts(fontURL, scope, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerUnregisterFontsForURL", optional=true)
    private static native boolean unregisterFonts(NSURL fontURL, CTFontManagerScope scope, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean registerGraphicsFont(CGFont font) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = registerGraphicsFont(font, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerRegisterGraphicsFont", optional=true)
    private static native boolean registerGraphicsFont(CGFont font, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.1 and later.
     */
    public static boolean unregisterGraphicsFont(CGFont font) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = unregisterGraphicsFont(font, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="CTFontManagerUnregisterGraphicsFont", optional=true)
    private static native boolean unregisterGraphicsFont(CGFont font, NSError.NSErrorPtr error);
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
