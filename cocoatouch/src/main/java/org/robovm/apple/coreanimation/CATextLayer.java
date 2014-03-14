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
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    public native VoidPtr getFont();
    @Property(selector = "setFont:")
    public native void setFont(VoidPtr v);
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
    public native String getTruncationMode();
    @Property(selector = "setTruncationMode:")
    public native void setTruncationMode(String v);
    @Property(selector = "alignmentMode")
    public native String getAlignmentMode();
    @Property(selector = "setAlignmentMode:")
    public native void setAlignmentMode(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCATruncationNone")
    public static native String TruncationNone();
    @GlobalValue(symbol="kCATruncationStart")
    public static native String TruncationStart();
    @GlobalValue(symbol="kCATruncationEnd")
    public static native String TruncationEnd();
    @GlobalValue(symbol="kCATruncationMiddle")
    public static native String TruncationMiddle();
    @GlobalValue(symbol="kCAAlignmentNatural")
    public static native String AlignmentNatural();
    @GlobalValue(symbol="kCAAlignmentLeft")
    public static native String AlignmentLeft();
    @GlobalValue(symbol="kCAAlignmentRight")
    public static native String AlignmentRight();
    @GlobalValue(symbol="kCAAlignmentCenter")
    public static native String AlignmentCenter();
    @GlobalValue(symbol="kCAAlignmentJustified")
    public static native String AlignmentJustified();
    
    
    /*</methods>*/
}
