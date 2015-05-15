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
    public static CMTextFormatDescription create(CMMediaType mediaType, CMTextFormatType mediaSubtype, CMTextFormatDescriptionExtension extensions) throws OSStatusException {
        CMFormatDescriptionPtr ptr = new CMFormatDescriptionPtr();
        OSStatus status = create0(null, mediaType, (int)mediaSubtype.value(), extensions.getDictionary(), ptr);
        if (OSStatusException.throwIfNecessary(status)) {
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
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMTextFormatDescription createFromBigEndianTextDescriptionData(BytePtr textDescriptionData, @MachineSizedUInt long textDescriptionSize, String textDescriptionFlavor, CMMediaType mediaType) throws OSStatusException {
        CMTextFormatDescription.CMTextFormatDescriptionPtr ptr = new CMTextFormatDescription.CMTextFormatDescriptionPtr();
        OSStatus status = createFromBigEndianTextDescriptionData0(null, textDescriptionData, textDescriptionSize, textDescriptionFlavor, mediaType, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMTextFormatDescription createFromBigEndianTextDescriptionBlockBuffer(CMBlockBuffer textDescriptionBlockBuffer, String textDescriptionFlavor, CMMediaType mediaType) throws OSStatusException {
        CMTextFormatDescription.CMTextFormatDescriptionPtr ptr = new CMTextFormatDescription.CMTextFormatDescriptionPtr();
        OSStatus status = createFromBigEndianTextDescriptionBlockBuffer0(null, textDescriptionBlockBuffer, textDescriptionFlavor, mediaType, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }

    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMTextDisplayFlags getDisplayFlags() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getDisplayFlags0(ptr);
        OSStatusException.throwIfNecessary(status);
        return new CMTextDisplayFlags(ptr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getHorizontalJustification() throws OSStatusException {
        BytePtr ptr = new BytePtr();
        OSStatus status = getJustification0(ptr, null);
        OSStatusException.throwIfNecessary(status);
        return CMTextJustification.valueOf(ptr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getVerticalJustification() throws OSStatusException {
        BytePtr ptr = new BytePtr();
        OSStatus status = getJustification0(null, ptr);
        OSStatusException.throwIfNecessary(status);
        return CMTextJustification.valueOf(ptr.get());
    } 
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public @ByVal CGRect getDefaultTextBox(boolean originIsAtTopLeft, @MachineSizedFloat double heightOfTextTrack) throws OSStatusException {
        CGRect.CGRectPtr ptr = new CGRect.CGRectPtr();
        OSStatus status = getDefaultTextBox0(originIsAtTopLeft, heightOfTextTrack, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public short getDefaultStyleLocalFontID() throws OSStatusException {
        ShortPtr ptr = new ShortPtr();
        OSStatus status = getDefaultStyle0(ptr, null, null, null, null, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDefaultStyleBold() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getDefaultStyle0(null, ptr, null, null, null, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDefaultStyleItalic() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getDefaultStyle0(null, null, ptr, null, null, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDefaultStyleUnderline() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getDefaultStyle0(null, null, null, ptr, null, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public @MachineSizedFloat double getDefaultStyleFontSize() throws OSStatusException {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        OSStatus status = getDefaultStyle0(null, null, null, null, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public CMBlockBuffer copyAsBigEndianTextDescriptionBlockBuffer(String textDescriptionFlavor) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = copyAsBigEndianTextDescriptionBlockBuffer0(null, this, textDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDisplayFlags", optional=true)
    protected native OSStatus getDisplayFlags0(IntPtr outDisplayFlags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetJustification", optional=true)
    protected native OSStatus getJustification0(BytePtr outHorizontalJust, BytePtr outVerticalJust);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDefaultTextBox", optional=true)
    protected native OSStatus getDefaultTextBox0(boolean originIsAtTopLeft, @MachineSizedFloat double heightOfTextTrack, CGRect.CGRectPtr outDefaultTextBox);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDefaultStyle", optional=true)
    protected native OSStatus getDefaultStyle0(ShortPtr outLocalFontID, BooleanPtr outBold, BooleanPtr outItalic, BooleanPtr outUnderline, MachineSizedFloatPtr outFontSize, MachineSizedFloatPtr outColorComponents);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetFontName", optional=true)
    protected native OSStatus getFontName0(short localFontID, CFString.CFStringPtr outFontName);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionCreateFromBigEndianTextDescriptionData", optional=true)
    private static native OSStatus createFromBigEndianTextDescriptionData0(CFAllocator allocator, BytePtr textDescriptionData, @MachineSizedUInt long textDescriptionSize, String textDescriptionFlavor, CMMediaType mediaType, CMTextFormatDescription.CMTextFormatDescriptionPtr textFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionCreateFromBigEndianTextDescriptionBlockBuffer", optional=true)
    private static native OSStatus createFromBigEndianTextDescriptionBlockBuffer0(CFAllocator allocator, CMBlockBuffer textDescriptionBlockBuffer, String textDescriptionFlavor, CMMediaType mediaType, CMTextFormatDescription.CMTextFormatDescriptionPtr textFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionCopyAsBigEndianTextDescriptionBlockBuffer", optional=true)
    private static native OSStatus copyAsBigEndianTextDescriptionBlockBuffer0(CFAllocator allocator, CMTextFormatDescription textFormatDescription, String textDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr textDescriptionBlockBufferOut);
    /*</methods>*/
}
