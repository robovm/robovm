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
package org.robovm.apple.coreimage;

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
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIColor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class CIColorPtr extends Ptr<CIColor, CIColorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIColor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CIColor() {}
    protected CIColor(SkipInit skipInit) { super(skipInit); }
    public CIColor(CGColor c) { super((SkipInit) null); initObject(initWithCGColor$(c)); }
    /*</constructors>*/
    
    public CIColor(double r, double g, double b, double a) {
        super(colorWithRed$green$blue$alpha$(r, g, b, a));
    }
    public CIColor(double r, double g, double b) {
        super(colorWithRed$green$blue$(r, g, b));
    }
    public CIColor(String representation) {
        super(colorWithString$(representation));
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public double[] getComponentsD() {
        return components().toDoubleArray((int) getNumberOfComponents());
    }

    public float[] getComponentsF() {
        return components().toFloatArray((int) getNumberOfComponents());
    }

    
    /*<methods>*/
    @Method(selector = "initWithCGColor:")
    protected native @Pointer long initWithCGColor$(CGColor c);
    @Method(selector = "numberOfComponents")
    public native @MachineSizedUInt long getNumberOfComponents();
    @Method(selector = "components")
    protected native MachineSizedFloatPtr components();
    @Method(selector = "alpha")
    public native @MachineSizedFloat double getAlpha();
    @Method(selector = "colorSpace")
    protected native @Pointer long colorSpace();
    @Method(selector = "red")
    public native @MachineSizedFloat double getRed();
    @Method(selector = "green")
    public native @MachineSizedFloat double getGreen();
    @Method(selector = "blue")
    public native @MachineSizedFloat double getBlue();
    @Method(selector = "stringRepresentation")
    public native String getStringRepresentation();
    @Method(selector = "colorWithRed:green:blue:alpha:")
    protected static native @Pointer long colorWithRed$green$blue$alpha$(@MachineSizedFloat double r, @MachineSizedFloat double g, @MachineSizedFloat double b, @MachineSizedFloat double a);
    @Method(selector = "colorWithRed:green:blue:")
    protected static native @Pointer long colorWithRed$green$blue$(@MachineSizedFloat double r, @MachineSizedFloat double g, @MachineSizedFloat double b);
    @Method(selector = "colorWithString:")
    protected static native @Pointer long colorWithString$(String representation);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
