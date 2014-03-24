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
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
        this.m11(m11);
        this.m12(m12);
        this.m13(m13);
        this.m14(m14);
        this.m21(m21);
        this.m22(m22);
        this.m23(m23);
        this.m24(m24);
        this.m31(m31);
        this.m32(m32);
        this.m33(m33);
        this.m34(m34);
        this.m41(m41);
        this.m42(m42);
        this.m43(m43);
        this.m44(m44);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double m11();
    @StructMember(0) public native CATransform3D m11(@MachineSizedFloat double m11);
    @StructMember(1) public native @MachineSizedFloat double m12();
    @StructMember(1) public native CATransform3D m12(@MachineSizedFloat double m12);
    @StructMember(2) public native @MachineSizedFloat double m13();
    @StructMember(2) public native CATransform3D m13(@MachineSizedFloat double m13);
    @StructMember(3) public native @MachineSizedFloat double m14();
    @StructMember(3) public native CATransform3D m14(@MachineSizedFloat double m14);
    @StructMember(4) public native @MachineSizedFloat double m21();
    @StructMember(4) public native CATransform3D m21(@MachineSizedFloat double m21);
    @StructMember(5) public native @MachineSizedFloat double m22();
    @StructMember(5) public native CATransform3D m22(@MachineSizedFloat double m22);
    @StructMember(6) public native @MachineSizedFloat double m23();
    @StructMember(6) public native CATransform3D m23(@MachineSizedFloat double m23);
    @StructMember(7) public native @MachineSizedFloat double m24();
    @StructMember(7) public native CATransform3D m24(@MachineSizedFloat double m24);
    @StructMember(8) public native @MachineSizedFloat double m31();
    @StructMember(8) public native CATransform3D m31(@MachineSizedFloat double m31);
    @StructMember(9) public native @MachineSizedFloat double m32();
    @StructMember(9) public native CATransform3D m32(@MachineSizedFloat double m32);
    @StructMember(10) public native @MachineSizedFloat double m33();
    @StructMember(10) public native CATransform3D m33(@MachineSizedFloat double m33);
    @StructMember(11) public native @MachineSizedFloat double m34();
    @StructMember(11) public native CATransform3D m34(@MachineSizedFloat double m34);
    @StructMember(12) public native @MachineSizedFloat double m41();
    @StructMember(12) public native CATransform3D m41(@MachineSizedFloat double m41);
    @StructMember(13) public native @MachineSizedFloat double m42();
    @StructMember(13) public native CATransform3D m42(@MachineSizedFloat double m42);
    @StructMember(14) public native @MachineSizedFloat double m43();
    @StructMember(14) public native CATransform3D m43(@MachineSizedFloat double m43);
    @StructMember(15) public native @MachineSizedFloat double m44();
    @StructMember(15) public native CATransform3D m44(@MachineSizedFloat double m44);
    /*</members>*/
    
    public boolean isIdentity() {
        return isIdentity(this);
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof CATransform3D && equalToTransform(this, (CATransform3D) o);
    }
    
    public CATransform3D translate(double tx, double ty, double tz) {
        return translate(this, tx, ty, tz);
    }
    
    public CATransform3D scale(double sx, double sy, double sz) {
        return scale(this, sx, sy, sz);
    }
    
    public CATransform3D rotate(double angle, double x, double y, double z) {
        return rotate(this, angle, x, y, z);
    }
    
    public CATransform3D concat(CATransform3D b) {
        return concat(this, b);
    }
    
    public CATransform3D invert() {
        return invert(this);
    }
    
    public boolean isAffine() {
        return isAffine(this);
    }
    
    public CGAffineTransform getAffineTransform() {
        return getAffineTransform(this);
    }
    
    /*<methods>*/
    @GlobalValue(symbol="CATransform3DIdentity")
    public static native @ByVal CATransform3D Identity();
    
    @Bridge(symbol="CATransform3DIsIdentity")
    protected static native boolean isIdentity(@ByVal CATransform3D t);
    @Bridge(symbol="CATransform3DEqualToTransform")
    protected static native boolean equalToTransform(@ByVal CATransform3D a, @ByVal CATransform3D b);
    @Bridge(symbol="CATransform3DMakeTranslation")
    public static native @ByVal CATransform3D makeTranslation(@MachineSizedFloat double tx, @MachineSizedFloat double ty, @MachineSizedFloat double tz);
    @Bridge(symbol="CATransform3DMakeScale")
    public static native @ByVal CATransform3D makeScale(@MachineSizedFloat double sx, @MachineSizedFloat double sy, @MachineSizedFloat double sz);
    @Bridge(symbol="CATransform3DMakeRotation")
    public static native @ByVal CATransform3D makeRotation(@MachineSizedFloat double angle, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z);
    @Bridge(symbol="CATransform3DTranslate")
    protected static native @ByVal CATransform3D translate(@ByVal CATransform3D t, @MachineSizedFloat double tx, @MachineSizedFloat double ty, @MachineSizedFloat double tz);
    @Bridge(symbol="CATransform3DScale")
    protected static native @ByVal CATransform3D scale(@ByVal CATransform3D t, @MachineSizedFloat double sx, @MachineSizedFloat double sy, @MachineSizedFloat double sz);
    @Bridge(symbol="CATransform3DRotate")
    protected static native @ByVal CATransform3D rotate(@ByVal CATransform3D t, @MachineSizedFloat double angle, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double z);
    @Bridge(symbol="CATransform3DConcat")
    protected static native @ByVal CATransform3D concat(@ByVal CATransform3D a, @ByVal CATransform3D b);
    @Bridge(symbol="CATransform3DInvert")
    protected static native @ByVal CATransform3D invert(@ByVal CATransform3D t);
    @Bridge(symbol="CATransform3DMakeAffineTransform")
    public static native @ByVal CATransform3D makeAffineTransform(@ByVal CGAffineTransform m);
    @Bridge(symbol="CATransform3DIsAffine")
    protected static native boolean isAffine(@ByVal CATransform3D t);
    @Bridge(symbol="CATransform3DGetAffineTransform")
    protected static native @ByVal CGAffineTransform getAffineTransform(@ByVal CATransform3D t);
    /*</methods>*/
}
