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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNMatrix4/*</name>*/ 
    extends /*<extends>*/Struct<SCNMatrix4>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNMatrix4> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSValue> o = (NSArray<NSValue>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNMatrix4> list = new ArrayList<>();
            for (NSValue val : o) {
                list.add(val.SCNMatrix4Value());
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNMatrix4> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSValue> array = new NSMutableArray<>();
            for (SCNMatrix4 i : l) {
                array.add(NSValue.valueOf(i));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/public static class SCNMatrix4Ptr extends Ptr<SCNMatrix4, SCNMatrix4Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNMatrix4.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNMatrix4() {}
    public SCNMatrix4(float m11, float m12, float m13, float m14, float m21, float m22, float m23, float m24, float m31, float m32, float m33, float m34, float m41, float m42, float m43, float m44) {
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
    @StructMember(0) public native float getM11();
    @StructMember(0) public native SCNMatrix4 setM11(float m11);
    @StructMember(1) public native float getM12();
    @StructMember(1) public native SCNMatrix4 setM12(float m12);
    @StructMember(2) public native float getM13();
    @StructMember(2) public native SCNMatrix4 setM13(float m13);
    @StructMember(3) public native float getM14();
    @StructMember(3) public native SCNMatrix4 setM14(float m14);
    @StructMember(4) public native float getM21();
    @StructMember(4) public native SCNMatrix4 setM21(float m21);
    @StructMember(5) public native float getM22();
    @StructMember(5) public native SCNMatrix4 setM22(float m22);
    @StructMember(6) public native float getM23();
    @StructMember(6) public native SCNMatrix4 setM23(float m23);
    @StructMember(7) public native float getM24();
    @StructMember(7) public native SCNMatrix4 setM24(float m24);
    @StructMember(8) public native float getM31();
    @StructMember(8) public native SCNMatrix4 setM31(float m31);
    @StructMember(9) public native float getM32();
    @StructMember(9) public native SCNMatrix4 setM32(float m32);
    @StructMember(10) public native float getM33();
    @StructMember(10) public native SCNMatrix4 setM33(float m33);
    @StructMember(11) public native float getM34();
    @StructMember(11) public native SCNMatrix4 setM34(float m34);
    @StructMember(12) public native float getM41();
    @StructMember(12) public native SCNMatrix4 setM41(float m41);
    @StructMember(13) public native float getM42();
    @StructMember(13) public native SCNMatrix4 setM42(float m42);
    @StructMember(14) public native float getM43();
    @StructMember(14) public native SCNMatrix4 setM43(float m43);
    @StructMember(15) public native float getM44();
    @StructMember(15) public native SCNMatrix4 setM44(float m44);
    /*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="SCNMatrix4Identity", optional=true)
    public static native @ByVal SCNMatrix4 Identity();
    
    @Bridge(symbol="SCNMatrix4MakeRotation", optional=true)
    public static native @ByVal SCNMatrix4 createRotation(float angle, float x, float y, float z);
    public SCNMatrix4 scale(float x, float y, float z) { return scale(this, x, y, z); }
    @Bridge(symbol="SCNMatrix4Scale", optional=true)
    private static native @ByVal SCNMatrix4 scale(@ByVal SCNMatrix4 mat, float x, float y, float z);
    public SCNMatrix4 rotate(float angle, float x, float y, float z) { return rotate(this, angle, x, y, z); }
    @Bridge(symbol="SCNMatrix4Rotate", optional=true)
    private static native @ByVal SCNMatrix4 rotate(@ByVal SCNMatrix4 mat, float angle, float x, float y, float z);
    public SCNMatrix4 invert() { return invert(this); }
    @Bridge(symbol="SCNMatrix4Invert", optional=true)
    private static native @ByVal SCNMatrix4 invert(@ByVal SCNMatrix4 mat);
    public SCNMatrix4 mult(SCNMatrix4 matB) { return mult(this, matB); }
    @Bridge(symbol="SCNMatrix4Mult", optional=true)
    private static native @ByVal SCNMatrix4 mult(@ByVal SCNMatrix4 matA, @ByVal SCNMatrix4 matB);
    public boolean isIdentity() { return isIdentity(this); }
    @Bridge(symbol="SCNMatrix4IsIdentity", optional=true)
    private static native boolean isIdentity(@ByVal SCNMatrix4 mat);
    public boolean equalsTo(SCNMatrix4 matB) { return equalsTo(this, matB); }
    @Bridge(symbol="SCNMatrix4EqualToMatrix4", optional=true)
    private static native boolean equalsTo(@ByVal SCNMatrix4 matA, @ByVal SCNMatrix4 matB);
    public GLKMatrix4 toGLKMatrix4() { return toGLKMatrix4(this); }
    @Bridge(symbol="SCNMatrix4ToGLKMatrix4", optional=true)
    private static native @ByVal GLKMatrix4 toGLKMatrix4(@ByVal SCNMatrix4 mat);
    @Bridge(symbol="SCNMatrix4FromGLKMatrix4", optional=true)
    public static native @ByVal SCNMatrix4 fromGLKMatrix4(@ByVal GLKMatrix4 mat);
    /*</methods>*/
}
