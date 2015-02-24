/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMTextFormatDescriptionPtr extends Ptr<CMTextFormatDescription, CMTextFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTextFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CMTextFormatDescription create(CMMediaType mediaType, CMTextFormatType mediaSubtype, CMTextFormatDescriptionExtension extensions) {
        CMFormatDescriptionPtr ptr = new CMFormatDescriptionPtr();
        create(null, mediaType, (int)mediaSubtype.value(), extensions.getDictionary(), ptr);
        if (ptr.get() != null) {
            return ptr.get().as(CMTextFormatDescription.class);
        }
        return null;
    }
    
    public CMTextFormatDescriptionExtension getExtensions() {
        return new CMTextFormatDescriptionExtension(getExtensionDictionary());
    }
    public CMTextFormatType getTextFormatType() {
        return CMTextFormatType.valueOf(getMediaSubType());
    }
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMTextFormatDescription createFromBigEndianTextDescriptionData(BytePtr textDescriptionData, @MachineSizedUInt long textDescriptionSize, String textDescriptionFlavor, CMMediaType mediaType) {
        CMTextFormatDescription.CMTextFormatDescriptionPtr ptr = new CMTextFormatDescription.CMTextFormatDescriptionPtr();
        createFromBigEndianTextDescriptionData(null, textDescriptionData, textDescriptionSize, textDescriptionFlavor, mediaType, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMTextFormatDescription createFromBigEndianTextDescriptionBlockBuffer(CMBlockBuffer textDescriptionBlockBuffer, String textDescriptionFlavor, CMMediaType mediaType) {
        CMTextFormatDescription.CMTextFormatDescriptionPtr ptr = new CMTextFormatDescription.CMTextFormatDescriptionPtr();
        createFromBigEndianTextDescriptionBlockBuffer(null, textDescriptionBlockBuffer, textDescriptionFlavor, mediaType, ptr);
        return ptr.get();
    }

    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextDisplayFlags getDisplayFlags() {
        IntPtr ptr = new IntPtr();
        getDisplayFlags(ptr);
        return new CMTextDisplayFlags(ptr.get());
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getHorizontalJustification() {
        BytePtr ptr = new BytePtr();
        getJustification(ptr, null);
        return CMTextJustification.valueOf(ptr.get());
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getVerticalJustification() {
        BytePtr ptr = new BytePtr();
        getJustification(null, ptr);
        return CMTextJustification.valueOf(ptr.get());
    } 
    /**
     * @since Available in iOS 4.0 and later.
     */
    public @ByVal CGRect getDefaultTextBox(boolean originIsAtTopLeft, @MachineSizedFloat double heightOfTextTrack) {
        CGRect.CGRectPtr ptr = new CGRect.CGRectPtr();
        getDefaultTextBox(originIsAtTopLeft, heightOfTextTrack, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public short getDefaultStyleLocalFontID() {
        ShortPtr ptr = new ShortPtr();
        getDefaultStyle(ptr, null, null, null, null, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDefaultStyleBold() {
        BooleanPtr ptr = new BooleanPtr();
        getDefaultStyle(null, ptr, null, null, null, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDefaultStyleItalic() {
        BooleanPtr ptr = new BooleanPtr();
        getDefaultStyle(null, null, ptr, null, null, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDefaultStyleUnderline() {
        BooleanPtr ptr = new BooleanPtr();
        getDefaultStyle(null, null, null, ptr, null, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public @MachineSizedFloat double getDefaultStyleFontSize() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getDefaultStyle(null, null, null, null, ptr, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMBlockBuffer copyAsBigEndianTextDescriptionBlockBuffer(String textDescriptionFlavor) {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        copyAsBigEndianTextDescriptionBlockBuffer(null, this, textDescriptionFlavor, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDisplayFlags", optional=true)
    private native OSStatus getDisplayFlags(IntPtr outDisplayFlags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetJustification", optional=true)
    private native OSStatus getJustification(BytePtr outHorizontalJust, BytePtr outVerticalJust);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDefaultTextBox", optional=true)
    private native OSStatus getDefaultTextBox(boolean originIsAtTopLeft, @MachineSizedFloat double heightOfTextTrack, CGRect.CGRectPtr outDefaultTextBox);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDefaultStyle", optional=true)
    private native OSStatus getDefaultStyle(ShortPtr outLocalFontID, BooleanPtr outBold, BooleanPtr outItalic, BooleanPtr outUnderline, MachineSizedFloatPtr outFontSize, MachineSizedFloatPtr outColorComponents);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetFontName", optional=true)
    private native OSStatus getFontName(short localFontID, CFString.CFStringPtr outFontName);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionCreateFromBigEndianTextDescriptionData", optional=true)
    private static native OSStatus createFromBigEndianTextDescriptionData(CFAllocator allocator, BytePtr textDescriptionData, @MachineSizedUInt long textDescriptionSize, String textDescriptionFlavor, CMMediaType mediaType, CMTextFormatDescription.CMTextFormatDescriptionPtr textFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionCreateFromBigEndianTextDescriptionBlockBuffer", optional=true)
    private static native OSStatus createFromBigEndianTextDescriptionBlockBuffer(CFAllocator allocator, CMBlockBuffer textDescriptionBlockBuffer, String textDescriptionFlavor, CMMediaType mediaType, CMTextFormatDescription.CMTextFormatDescriptionPtr textFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionCopyAsBigEndianTextDescriptionBlockBuffer", optional=true)
    private static native OSStatus copyAsBigEndianTextDescriptionBlockBuffer(CFAllocator allocator, CMTextFormatDescription textFormatDescription, String textDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr textDescriptionBlockBufferOut);
    /*</methods>*/
}
