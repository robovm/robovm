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
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATransform3D/*</name>*/ 
    extends /*<extends>*/Struct<CATransform3D>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CATransform3DPtr extends Ptr<CATransform3D, CATransform3DPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CATransform3D.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CATransform3D() {}
    public CATransform3D(@MachineSizedFloat double m11, @MachineSizedFloat double m12, @MachineSizedFloat double m13, @MachineSizedFloat double m14, @MachineSizedFloat double m21, @MachineSizedFloat double m22, @MachineSizedFloat double m23, @MachineSizedFloat double m24, @MachineSizedFloat double m31, @MachineSizedFloat double m32, @MachineSizedFloat double m33, @MachineSizedFloat double m34, @MachineSizedFloat double m41, @MachineSizedFloat double m42, @MachineSizedFloat double m43, @MachineSizedFloat double m44) {
        this.setM11(m11);
        this.setM12(m12);
        this.setM13(m13);
        this.setM14(m14);
        this.setM21(m21);
        this.setM22(m22);
        this.setM23(m23);
        this.setM24(m24);
        this.setM31(m31);
        this.setM32(m32);
        this.setM33(m33);
        this.setM34(m34);
        this.setM41(m41);
        this.setM42(m42);
        this.setM43(m43);
        this.setM44(m44);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double getM11();
    @StructMember(0) public native CATransform3D setM11(@MachineSizedFloat double m11);
    @StructMember(1) public native @MachineSizedFloat double getM12();
    @StructMember(1) public native CATransform3D setM12(@MachineSizedFloat double m12);
    @StructMember(2) public native @MachineSizedFloat double getM13();
    @StructMember(2) public native CATransform3D setM13(@MachineSizedFloat double m13);
    @StructMember(3) public native @MachineSizedFloat double getM14();
    @StructMember(3) public native CATransform3D setM14(@MachineSizedFloat double m14);
    @StructMember(4) public native @MachineSizedFloat double getM21();
    @StructMember(4) public native CATransform3D setM21(@MachineSizedFloat double m21);
    @StructMember(5) public native @MachineSizedFloat double getM22();
    @StructMember(5) public native CATransform3D setM22(@MachineSizedFloat double m22);
    @StructMember(6) public native @MachineSizedFloat double getM23();
    @StructMember(6) public native CATransform3D setM23(@MachineSizedFloat double m23);
    @StructMember(7) public native @MachineSizedFloat double getM24();
    @StructMember(7) public native CATransform3D setM24(@MachineSizedFloat double m24);
    @StructMember(8) public native @MachineSizedFloat double getM31();
    @StructMember(8) public native CATransform3D setM31(@MachineSizedFloat double m31);
    @StructMember(9) public native @MachineSizedFloat double getM32();
    @StructMember(9) public native CATransform3D setM32(@MachineSizedFloat double m32);
    @StructMember(10) public native @MachineSizedFloat double getM33();
    @StructMember(10) public native CATransform3D setM33(@MachineSizedFloat double m33);
    @StructMember(11) public native @MachineSizedFloat double getM34();
    @StructMember(11) public native CATransform3D setM34(@MachineSizedFloat double m34);
    @StructMember(12) public native @MachineSizedFloat double getM41();
    @StructMember(12) public native CATransform3D setM41(@MachineSizedFloat double m41);
    @StructMember(13) public native @MachineSizedFloat double getM42();
    @StructMember(13) public native CATransform3D setM42(@MachineSizedFloat double m42);
    @StructMember(14) public native @MachineSizedFloat double getM43();
    @StructMember(14) public native CATransform3D setM43(@MachineSizedFloat double m43);
    @StructMember(15) public native @MachineSizedFloat double getM44();
    @StructMember(15) public native CATransform3D setM44(@MachineSizedFloat double m44);
    /*</members>*/
    
    @Override
    public boolean equals(Object o) {
        return o instanceof CATransform3D && equalToTransform(this, (CATransform3D) o);
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CATransform3DIdentity", optional=true)
    public static native @ByVal CATransform3D Identity();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isIdentity() { return isIdentity(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DIsIdentity", optional=true)
    private static native boolean isIdentity(@ByVal CATransform3D t);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean equalsTo(CATransform3D b) { return equalsTo(this, b); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DEqualToTransform", optional=true)
    private static native boolean equalsTo(@ByVal CATransform3D a, @ByVal CATransform3D b);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DMakeTranslation", optional=true)
    public static native @ByVal CATransform3D createTranslation(@MachineSizedFloat double tx, @MachineSizedFloat double ty, @MachineSizedFloat double tz);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DMakeScale", optional=true)
    public static native @ByVal CATransform3D createScale(@MachineSizedFloat double sx, @MachineSizedFloat double sy, @MachineSizedFloat double sz);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DMakeRotation", optional=true)
    public static native @ByVal CATransform3D createRotation(@MachineSizedFloat double angle, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CATransform3D translate(@MachineSizedFloat double tx, @MachineSizedFloat double ty, @MachineSizedFloat double tz) { return translate(this, tx, ty, tz); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DTranslate", optional=true)
    private static native @ByVal CATransform3D translate(@ByVal CATransform3D t, @MachineSizedFloat double tx, @MachineSizedFloat double ty, @MachineSizedFloat double tz);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CATransform3D scale(@MachineSizedFloat double sx, @MachineSizedFloat double sy, @MachineSizedFloat double sz) { return scale(this, sx, sy, sz); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DScale", optional=true)
    private static native @ByVal CATransform3D scale(@ByVal CATransform3D t, @MachineSizedFloat double sx, @MachineSizedFloat double sy, @MachineSizedFloat double sz);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CATransform3D rotate(@MachineSizedFloat double angle, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z) { return rotate(this, angle, x, y, z); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DRotate", optional=true)
    private static native @ByVal CATransform3D rotate(@ByVal CATransform3D t, @MachineSizedFloat double angle, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CATransform3D concat(CATransform3D b) { return concat(this, b); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DConcat", optional=true)
    private static native @ByVal CATransform3D concat(@ByVal CATransform3D a, @ByVal CATransform3D b);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CATransform3D invert() { return invert(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DInvert", optional=true)
    private static native @ByVal CATransform3D invert(@ByVal CATransform3D t);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DMakeAffineTransform", optional=true)
    public static native @ByVal CATransform3D createAffineTransform(@ByVal CGAffineTransform m);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAffine() { return isAffine(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DIsAffine", optional=true)
    private static native boolean isAffine(@ByVal CATransform3D t);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform getAffineTransform() { return getAffineTransform(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CATransform3DGetAffineTransform", optional=true)
    private static native @ByVal CGAffineTransform getAffineTransform(@ByVal CATransform3D t);
    /*</methods>*/
}
