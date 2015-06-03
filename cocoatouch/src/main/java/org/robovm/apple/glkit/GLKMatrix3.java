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
package org.robovm.apple.glkit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GLKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKMatrix3/*</name>*/ 
    extends /*<extends>*/Struct<GLKMatrix3>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKMatrix3Ptr extends Ptr<GLKMatrix3, GLKMatrix3Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKMatrix3.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKMatrix3() {}
    public GLKMatrix3(FloatBuffer m) {
        this.setM(m);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array({9}) FloatBuffer getM();
    @StructMember(0) public native GLKMatrix3 setM(@Array({9}) FloatBuffer m);
    /*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="GLKMatrix3Identity", optional=true)
    public static native @ByVal GLKMatrix3 Identity();
    
    @Bridge(symbol="GLKMatrix3Make", optional=true)
    public static native @ByVal GLKMatrix3 create(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22);
    @Bridge(symbol="GLKMatrix3MakeAndTranspose", optional=true)
    public static native @ByVal GLKMatrix3 createAndTranspose(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22);
    @Bridge(symbol="GLKMatrix3MakeWithArray", optional=true)
    public static native @ByVal GLKMatrix3 create(@Array({9}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix3MakeWithArrayAndTranspose", optional=true)
    public static native @ByVal GLKMatrix3 createAndTranspose(@Array({9}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix3MakeWithRows", optional=true)
    public static native @ByVal GLKMatrix3 createWithRows(@ByVal GLKVector3 row0, @ByVal GLKVector3 row1, @ByVal GLKVector3 row2);
    @Bridge(symbol="GLKMatrix3MakeWithColumns", optional=true)
    public static native @ByVal GLKMatrix3 createWithColumns(@ByVal GLKVector3 column0, @ByVal GLKVector3 column1, @ByVal GLKVector3 column2);
    @Bridge(symbol="GLKMatrix3MakeWithQuaternion", optional=true)
    public static native @ByVal GLKMatrix3 create(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKMatrix3MakeScale", optional=true)
    public static native @ByVal GLKMatrix3 createScale(float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrix3MakeRotation", optional=true)
    public static native @ByVal GLKMatrix3 createRotation(float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrix3MakeXRotation", optional=true)
    public static native @ByVal GLKMatrix3 createXRotation(float radians);
    @Bridge(symbol="GLKMatrix3MakeYRotation", optional=true)
    public static native @ByVal GLKMatrix3 createYRotation(float radians);
    @Bridge(symbol="GLKMatrix3MakeZRotation", optional=true)
    public static native @ByVal GLKMatrix3 createZRotation(float radians);
    public GLKMatrix2 getMatrix2() { return getMatrix2(this); }
    @Bridge(symbol="GLKMatrix3GetMatrix2", optional=true)
    private static native @ByVal GLKMatrix2 getMatrix2(@ByVal GLKMatrix3 matrix);
    public GLKVector3 getRow(int row) { return getRow(this, row); }
    @Bridge(symbol="GLKMatrix3GetRow", optional=true)
    private static native @ByVal GLKVector3 getRow(@ByVal GLKMatrix3 matrix, int row);
    public GLKVector3 getColumn(int column) { return getColumn(this, column); }
    @Bridge(symbol="GLKMatrix3GetColumn", optional=true)
    private static native @ByVal GLKVector3 getColumn(@ByVal GLKMatrix3 matrix, int column);
    public GLKMatrix3 setRow(int row, GLKVector3 vector) { return setRow(this, row, vector); }
    @Bridge(symbol="GLKMatrix3SetRow", optional=true)
    private static native @ByVal GLKMatrix3 setRow(@ByVal GLKMatrix3 matrix, int row, @ByVal GLKVector3 vector);
    public GLKMatrix3 setColumn(int column, GLKVector3 vector) { return setColumn(this, column, vector); }
    @Bridge(symbol="GLKMatrix3SetColumn", optional=true)
    private static native @ByVal GLKMatrix3 setColumn(@ByVal GLKMatrix3 matrix, int column, @ByVal GLKVector3 vector);
    public GLKMatrix3 transpose() { return transpose(this); }
    @Bridge(symbol="GLKMatrix3Transpose", optional=true)
    private static native @ByVal GLKMatrix3 transpose(@ByVal GLKMatrix3 matrix);
    public GLKMatrix3 invert(BooleanPtr isInvertible) { return invert(this, isInvertible); }
    @Bridge(symbol="GLKMatrix3Invert", optional=true)
    private static native @ByVal GLKMatrix3 invert(@ByVal GLKMatrix3 matrix, BooleanPtr isInvertible);
    public GLKMatrix3 invertAndTranspose(BooleanPtr isInvertible) { return invertAndTranspose(this, isInvertible); }
    @Bridge(symbol="GLKMatrix3InvertAndTranspose", optional=true)
    private static native @ByVal GLKMatrix3 invertAndTranspose(@ByVal GLKMatrix3 matrix, BooleanPtr isInvertible);
    public GLKMatrix3 multiply(GLKMatrix3 matrixRight) { return multiply(this, matrixRight); }
    @Bridge(symbol="GLKMatrix3Multiply", optional=true)
    private static native @ByVal GLKMatrix3 multiply(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKMatrix3 matrixRight);
    public GLKMatrix3 add(GLKMatrix3 matrixRight) { return add(this, matrixRight); }
    @Bridge(symbol="GLKMatrix3Add", optional=true)
    private static native @ByVal GLKMatrix3 add(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKMatrix3 matrixRight);
    public GLKMatrix3 subtract(GLKMatrix3 matrixRight) { return subtract(this, matrixRight); }
    @Bridge(symbol="GLKMatrix3Subtract", optional=true)
    private static native @ByVal GLKMatrix3 subtract(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKMatrix3 matrixRight);
    public GLKMatrix3 scale(float sx, float sy, float sz) { return scale(this, sx, sy, sz); }
    @Bridge(symbol="GLKMatrix3Scale", optional=true)
    private static native @ByVal GLKMatrix3 scale(@ByVal GLKMatrix3 matrix, float sx, float sy, float sz);
    public GLKMatrix3 scale(GLKVector3 scaleVector) { return scale(this, scaleVector); }
    @Bridge(symbol="GLKMatrix3ScaleWithVector3", optional=true)
    private static native @ByVal GLKMatrix3 scale(@ByVal GLKMatrix3 matrix, @ByVal GLKVector3 scaleVector);
    public GLKMatrix3 scale(GLKVector4 scaleVector) { return scale(this, scaleVector); }
    @Bridge(symbol="GLKMatrix3ScaleWithVector4", optional=true)
    private static native @ByVal GLKMatrix3 scale(@ByVal GLKMatrix3 matrix, @ByVal GLKVector4 scaleVector);
    public GLKMatrix3 rotate(float radians, float x, float y, float z) { return rotate(this, radians, x, y, z); }
    @Bridge(symbol="GLKMatrix3Rotate", optional=true)
    private static native @ByVal GLKMatrix3 rotate(@ByVal GLKMatrix3 matrix, float radians, float x, float y, float z);
    public GLKMatrix3 rotate(float radians, GLKVector3 axisVector) { return rotate(this, radians, axisVector); }
    @Bridge(symbol="GLKMatrix3RotateWithVector3", optional=true)
    private static native @ByVal GLKMatrix3 rotate(@ByVal GLKMatrix3 matrix, float radians, @ByVal GLKVector3 axisVector);
    public GLKMatrix3 rotate(float radians, GLKVector4 axisVector) { return rotate(this, radians, axisVector); }
    @Bridge(symbol="GLKMatrix3RotateWithVector4", optional=true)
    private static native @ByVal GLKMatrix3 rotate(@ByVal GLKMatrix3 matrix, float radians, @ByVal GLKVector4 axisVector);
    public GLKMatrix3 rotateX(float radians) { return rotateX(this, radians); }
    @Bridge(symbol="GLKMatrix3RotateX", optional=true)
    private static native @ByVal GLKMatrix3 rotateX(@ByVal GLKMatrix3 matrix, float radians);
    public GLKMatrix3 rotateY(float radians) { return rotateY(this, radians); }
    @Bridge(symbol="GLKMatrix3RotateY", optional=true)
    private static native @ByVal GLKMatrix3 rotateY(@ByVal GLKMatrix3 matrix, float radians);
    public GLKMatrix3 rotateZ(float radians) { return rotateZ(this, radians); }
    @Bridge(symbol="GLKMatrix3RotateZ", optional=true)
    private static native @ByVal GLKMatrix3 rotateZ(@ByVal GLKMatrix3 matrix, float radians);
    public GLKVector3 multiplyVector3(GLKVector3 vectorRight) { return multiplyVector3(this, vectorRight); }
    @Bridge(symbol="GLKMatrix3MultiplyVector3", optional=true)
    private static native @ByVal GLKVector3 multiplyVector3(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKVector3 vectorRight);
    public void multiplyVector3Array(GLKVector3 vectors, @MachineSizedUInt long vectorCount) { multiplyVector3Array(this, vectors, vectorCount); }
    @Bridge(symbol="GLKMatrix3MultiplyVector3Array", optional=true)
    private static native void multiplyVector3Array(@ByVal GLKMatrix3 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    /*</methods>*/
}
