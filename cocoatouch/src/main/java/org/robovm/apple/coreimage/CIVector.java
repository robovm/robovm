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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIVector/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class CIVectorPtr extends Ptr<CIVector, CIVectorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIVector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CIVector() {}
    protected CIVector(SkipInit skipInit) { super(skipInit); }
    public CIVector(@MachineSizedFloat double x) { super((SkipInit) null); initObject(init(x)); }
    public CIVector(@MachineSizedFloat double x, @MachineSizedFloat double y) { super((SkipInit) null); initObject(init(x, y)); }
    public CIVector(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z) { super((SkipInit) null); initObject(init(x, y, z)); }
    public CIVector(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z, @MachineSizedFloat double w) { super((SkipInit) null); initObject(init(x, y, z, w)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIVector(@ByVal CGPoint p) { super((SkipInit) null); initObject(init(p)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIVector(@ByVal CGRect r) { super((SkipInit) null); initObject(init(r)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIVector(@ByVal CGAffineTransform r) { super((SkipInit) null); initObject(init(r)); }
    public CIVector(String representation) { super((SkipInit) null); initObject(init(representation)); }
    /*</constructors>*/

    public CIVector(double[] values) {
        super((SkipInit) null);
        if (values == null) {
            throw new NullPointerException("values");
        }
        MachineSizedFloatPtr p = Struct.allocate(MachineSizedFloatPtr.class, values.length);
        p.set(values);
        initObject(init(p, values.length));
    }

    public CIVector(float[] values) {
        super((SkipInit) null);
        if (values == null) {
            throw new NullPointerException("values");
        }
        MachineSizedFloatPtr p = Struct.allocate(MachineSizedFloatPtr.class, values.length);
        p.set(values);
        initObject(init(p, values.length));
    }

    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithValues:count:")
    protected native @Pointer long init(MachineSizedFloatPtr values, @MachineSizedUInt long count);
    @Method(selector = "initWithX:")
    protected native @Pointer long init(@MachineSizedFloat double x);
    @Method(selector = "initWithX:Y:")
    protected native @Pointer long init(@MachineSizedFloat double x, @MachineSizedFloat double y);
    @Method(selector = "initWithX:Y:Z:")
    protected native @Pointer long init(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z);
    @Method(selector = "initWithX:Y:Z:W:")
    protected native @Pointer long init(@MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z, @MachineSizedFloat double w);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithCGPoint:")
    protected native @Pointer long init(@ByVal CGPoint p);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithCGRect:")
    protected native @Pointer long init(@ByVal CGRect r);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithCGAffineTransform:")
    protected native @Pointer long init(@ByVal CGAffineTransform r);
    @Method(selector = "initWithString:")
    protected native @Pointer long init(String representation);
    @Method(selector = "valueAtIndex:")
    public native @MachineSizedFloat double getValueAtIndex(@MachineSizedUInt long index);
    @Method(selector = "count")
    public native @MachineSizedUInt long getCount();
    @Method(selector = "X")
    public native @MachineSizedFloat double getX();
    @Method(selector = "Y")
    public native @MachineSizedFloat double getY();
    @Method(selector = "Z")
    public native @MachineSizedFloat double getZ();
    @Method(selector = "W")
    public native @MachineSizedFloat double getW();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "CGPointValue")
    public native @ByVal CGPoint getCGPointValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "CGRectValue")
    public native @ByVal CGRect getCGRectValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "CGAffineTransformValue")
    public native @ByVal CGAffineTransform getCGAffineTransformValue();
    @Method(selector = "stringRepresentation")
    public native String getStringRepresentation();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
