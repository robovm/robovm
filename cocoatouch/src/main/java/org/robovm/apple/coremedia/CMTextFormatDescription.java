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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMTextFormatDescriptionPtr extends Ptr<CMTextFormatDescription, CMTextFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTextFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CMTextDisplayFlag getDisplayFlags() {
        IntPtr ptr = new IntPtr();
        getDisplayFlags(ptr);
        return new CMTextDisplayFlag(ptr.get());
    }
    
    public CMTextJustification getHorizontalJustification() {
        BytePtr ptr = new BytePtr();
        getJustification(ptr, null);
        return CMTextJustification.valueOf(ptr.get());
    }
    
    public CMTextJustification getVerticalJustification() {
        BytePtr ptr = new BytePtr();
        getJustification(null, ptr);
        return CMTextJustification.valueOf(ptr.get());
    } 
    
    public @ByVal CGRect getDefaultTextBox(boolean originIsAtTopLeft, @MachineSizedFloat double heightOfTextTrack) {
        CGRect.CGRectPtr ptr = new CGRect.CGRectPtr();
        getDefaultTextBox(originIsAtTopLeft, heightOfTextTrack, ptr);
        return ptr.get();
    }
    
    public short getDefaultStyleLocalFontID() {
        ShortPtr ptr = new ShortPtr();
        getDefaultStyle(ptr, null, null, null, null, null);
        return ptr.get();
    }
    
    public boolean isDefaultStyleBold() {
        BytePtr ptr = new BytePtr();
        getDefaultStyle(null, ptr, null, null, null, null);
        return ptr.get() >= 1;
    }
    
    public boolean isDefaultStyleItalic() {
        BytePtr ptr = new BytePtr();
        getDefaultStyle(null, null, ptr, null, null, null);
        return ptr.get() >= 1;
    }
    
    public boolean isDefaultStyleUnderline() {
        BytePtr ptr = new BytePtr();
        getDefaultStyle(null, null, null, ptr, null, null);
        return ptr.get() >= 1;
    }
    
    public @MachineSizedFloat double getDefaultStyleFontSize() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getDefaultStyle(null, null, null, null, ptr, null);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDisplayFlags", optional=true)
    protected native int getDisplayFlags(IntPtr outDisplayFlags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetJustification", optional=true)
    protected native int getJustification(BytePtr outHorizontalJust, BytePtr outVerticalJust);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDefaultTextBox", optional=true)
    protected native int getDefaultTextBox(boolean originIsAtTopLeft, @MachineSizedFloat double heightOfTextTrack, CGRect.CGRectPtr outDefaultTextBox);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetDefaultStyle", optional=true)
    protected native int getDefaultStyle(ShortPtr outLocalFontID, BytePtr outBold, BytePtr outItalic, BytePtr outUnderline, MachineSizedFloatPtr outFontSize, MachineSizedFloatPtr outColorComponents);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTextFormatDescriptionGetFontName", optional=true)
    protected native int getFontName(short localFontID, CFString.CFStringPtr outFontName);
    /*</methods>*/
}
