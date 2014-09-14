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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATextLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CATextLayerPtr extends Ptr<CATextLayer, CATextLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CATextLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CATextLayer() {}
    protected CATextLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "string")
    public native NSObject getString();
    @Property(selector = "setString:")
    public native void setString(NSObject v);
    @Property(selector = "font")
    protected native @Pointer long getFontPtr();
    @Property(selector = "setFont:")
    protected native void setFontPtr(@Pointer long v);
    @Property(selector = "fontSize")
    public native @MachineSizedFloat double getFontSize();
    @Property(selector = "setFontSize:")
    public native void setFontSize(@MachineSizedFloat double v);
    @Property(selector = "foregroundColor")
    public native CGColor getForegroundColor();
    @Property(selector = "setForegroundColor:")
    public native void setForegroundColor(CGColor v);
    @Property(selector = "isWrapped")
    public native boolean isWrapped();
    @Property(selector = "setWrapped:")
    public native void setWrapped(boolean v);
    @Property(selector = "truncationMode")
    public native NSString getTruncationMode();
    @Property(selector = "setTruncationMode:")
    public native void setTruncationMode(NSString v);
    @Property(selector = "alignmentMode")
    public native NSString getAlignmentMode();
    @Property(selector = "setAlignmentMode:")
    public native void setAlignmentMode(NSString v);
    /*</properties>*/
    /*<members>*//*</members>*/

    // Font is either a CTFontRef, a CGFontRef, an instance of NSFont (OS X only), or an NSString naming the font.
    /**
     * Returns the value of the {@code font} property. Either a  {@link CTFont},
     * {@link CGFont} or {@link String}.
     */
    public Object getFont() {
        long ptr = getFontPtr();
        if (ptr == 0) {
            return null;
        }
        org.robovm.apple.corefoundation.CFType cfObj = 
                org.robovm.apple.corefoundation.CFType.Marshaler.toObject(
                        org.robovm.apple.corefoundation.CFType.class, ptr, 0);
        if (cfObj instanceof org.robovm.apple.corefoundation.CFString) {
            return cfObj.toString();
        }
        return cfObj;
    }
    public void setFont(CTFont font) {
        setFontPtr(font.getHandle());
    }
    public void setFont(CGFont font) {
        setFontPtr(font.getHandle());
    }
    public void setFont(String font) {
        setFontPtr(new NSString(font).getHandle());
    }

    /*<methods>*/
    
    /*</methods>*/
}
