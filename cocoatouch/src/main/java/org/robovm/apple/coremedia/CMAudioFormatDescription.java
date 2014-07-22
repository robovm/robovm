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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMAudioFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMAudioFormatDescriptionPtr extends Ptr<CMAudioFormatDescription, CMAudioFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMAudioFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CMAudioFormatDescription create(AudioStreamBasicDescription asbd, @MachineSizedUInt long layoutSize, AudioChannelLayout layout, @MachineSizedUInt long magicCookieSize, VoidPtr magicCookie, NSDictionary<NSString, ?> extensions) {
        CMAudioFormatDescriptionPtr ptr = new CMAudioFormatDescriptionPtr();
        create(null, asbd, layoutSize, layout, magicCookieSize, magicCookie, extensions, ptr);
        return ptr.get();
    }
    
    public VoidPtr getMagicCookie() {
        return getMagicCookie(null);
    }
    
    public @MachineSizedUInt long getMagicCookieSize() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getMagicCookie(ptr);
        return ptr.get();
    }
    
    public AudioChannelLayout getChannelLayout() {
        return getChannelLayout(null);
    }
    
    public @MachineSizedUInt long getChannelLayoutSize() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getChannelLayout(ptr);
        return ptr.get();
    }
    
    public AudioFormatListItem getFormatList() {
        return getFormatList(null);
    }
    
    public @MachineSizedUInt long getFormatListSize() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getFormatList(ptr);
        return ptr.get();
    }
    
    public static CMAudioFormatDescription createSummary(NSArray<?> formatDescriptionArray) {
        CMAudioFormatDescriptionPtr ptr = new CMAudioFormatDescriptionPtr();
        createSummary(null, formatDescriptionArray, 0, ptr);
        return ptr.get();
    }
    
    public boolean equals(CMAudioFormatDescription desc2, CMAudioFormatDescriptionMask equalityMask) {
        return equals(desc2, equalityMask, null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionCreate", optional=true)
    protected static native int create(CFAllocator allocator, AudioStreamBasicDescription asbd, @MachineSizedUInt long layoutSize, AudioChannelLayout layout, @MachineSizedUInt long magicCookieSize, VoidPtr magicCookie, NSDictionary<NSString, ?> extensions, CMAudioFormatDescription.CMAudioFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionGetStreamBasicDescription", optional=true)
    public native AudioStreamBasicDescription getStreamBasicDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionGetMagicCookie", optional=true)
    protected native VoidPtr getMagicCookie(MachineSizedUIntPtr cookieSizeOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionGetChannelLayout", optional=true)
    protected native AudioChannelLayout getChannelLayout(MachineSizedUIntPtr layoutSize);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionGetFormatList", optional=true)
    protected native AudioFormatListItem getFormatList(MachineSizedUIntPtr formatListSize);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionGetRichestDecodableFormat", optional=true)
    public native AudioFormatListItem getRichestDecodableFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionGetMostCompatibleFormat", optional=true)
    public native AudioFormatListItem getMostCompatibleFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionCreateSummary", optional=true)
    protected static native int createSummary(CFAllocator allocator, NSArray<?> formatDescriptionArray, int flags, CMAudioFormatDescription.CMAudioFormatDescriptionPtr summaryFormatDescriptionOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioFormatDescriptionEqual", optional=true)
    protected native boolean equals(CMAudioFormatDescription desc2, CMAudioFormatDescriptionMask equalityMask, IntPtr equalityMaskOut);
    /*</methods>*/
}
