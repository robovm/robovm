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
package org.robovm.apple.glkit;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GLKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKMatrix4/*</name>*/ 
    extends /*<extends>*/Struct<GLKMatrix4>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKMatrix4Ptr extends Ptr<GLKMatrix4, GLKMatrix4Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKMatrix4.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKMatrix4() {}
    public GLKMatrix4(FloatBuffer m) {
        this.setM(m);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array({16}) FloatBuffer getM();
    @StructMember(0) public native GLKMatrix4 setM(@Array({16}) FloatBuffer m);
    /*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="GLKMatrix4Identity", optional=true)
    public static native @ByVal GLKMatrix4 Identity();
    
    @Bridge(symbol="GLKMatrix4Make", optional=true)
    public static native @ByVal GLKMatrix4 create(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33);
    @Bridge(symbol="GLKMatrix4MakeAndTranspose", optional=true)
    public static native @ByVal GLKMatrix4 createAndTranspose(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33);
    @Bridge(symbol="GLKMatrix4MakeWithArray", optional=true)
    public static native @ByVal GLKMatrix4 create(@Array({16}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix4MakeWithArrayAndTranspose", optional=true)
    public static native @ByVal GLKMatrix4 createAndTranspose(@Array({16}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix4MakeWithRows", optional=true)
    public static native @ByVal GLKMatrix4 createWithRows(@ByVal GLKVector4 row0, @ByVal GLKVector4 row1, @ByVal GLKVector4 row2, @ByVal GLKVector4 row3);
    @Bridge(symbol="GLKMatrix4MakeWithColumns", optional=true)
    public static native @ByVal GLKMatrix4 createWithColumns(@ByVal GLKVector4 column0, @ByVal GLKVector4 column1, @ByVal GLKVector4 column2, @ByVal GLKVector4 column3);
    @Bridge(symbol="GLKMatrix4MakeWithQuaternion", optional=true)
    public static native @ByVal GLKMatrix4 create(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKMatrix4MakeTranslation", optional=true)
    public static native @ByVal GLKMatrix4 createTranslation(float tx, float ty, float tz);
    @Bridge(symbol="GLKMatrix4MakeScale", optional=true)
    public static native @ByVal GLKMatrix4 createScale(float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrix4MakeRotation", optional=true)
    public static native @ByVal GLKMatrix4 createRotation(float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrix4MakeXRotation", optional=true)
    public static native @ByVal GLKMatrix4 createXRotation(float radians);
    @Bridge(symbol="GLKMatrix4MakeYRotation", optional=true)
    public static native @ByVal GLKMatrix4 createYRotation(float radians);
    @Bridge(symbol="GLKMatrix4MakeZRotation", optional=true)
    public static native @ByVal GLKMatrix4 createZRotation(float radians);
    @Bridge(symbol="GLKMatrix4MakePerspective", optional=true)
    public static native @ByVal GLKMatrix4 createPerspective(float fovyRadians, float aspect, float nearZ, float farZ);
    @Bridge(symbol="GLKMatrix4MakeFrustum", optional=true)
    public static native @ByVal GLKMatrix4 createFrustum(float left, float right, float bottom, float top, float nearZ, float farZ);
    @Bridge(symbol="GLKMatrix4MakeOrtho", optional=true)
    public static native @ByVal GLKMatrix4 createOrtho(float left, float right, float bottom, float top, float nearZ, float farZ);
    @Bridge(symbol="GLKMatrix4MakeLookAt", optional=true)
    public static native @ByVal GLKMatrix4 createLookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ);
    public GLKMatrix3 getMatrix3() { return getMatrix3(this); }
    @Bridge(symbol="GLKMatrix4GetMatrix3", optional=true)
    private static native @ByVal GLKMatrix3 getMatrix3(@ByVal GLKMatrix4 matrix);
    public GLKMatrix2 getMatrix2() { return getMatrix2(this); }
    @Bridge(symbol="GLKMatrix4GetMatrix2", optional=true)
    private static native @ByVal GLKMatrix2 getMatrix2(@ByVal GLKMatrix4 matrix);
    public GLKVector4 getRow(int row) { return getRow(this, row); }
    @Bridge(symbol="GLKMatrix4GetRow", optional=true)
    private static native @ByVal GLKVector4 getRow(@ByVal GLKMatrix4 matrix, int row);
    public GLKVector4 getColumn(int column) { return getColumn(this, column); }
    @Bridge(symbol="GLKMatrix4GetColumn", optional=true)
    private static native @ByVal GLKVector4 getColumn(@ByVal GLKMatrix4 matrix, int column);
    public GLKMatrix4 setRow(int row, GLKVector4 vector) { return setRow(this, row, vector); }
    @Bridge(symbol="GLKMatrix4SetRow", optional=true)
    private static native @ByVal GLKMatrix4 setRow(@ByVal GLKMatrix4 matrix, int row, @ByVal GLKVector4 vector);
    public GLKMatrix4 setColumn(int column, GLKVector4 vector) { return setColumn(this, column, vector); }
    @Bridge(symbol="GLKMatrix4SetColumn", optional=true)
    private static native @ByVal GLKMatrix4 setColumn(@ByVal GLKMatrix4 matrix, int column, @ByVal GLKVector4 vector);
    public GLKMatrix4 transpose() { return transpose(this); }
    @Bridge(symbol="GLKMatrix4Transpose", optional=true)
    private static native @ByVal GLKMatrix4 transpose(@ByVal GLKMatrix4 matrix);
    public GLKMatrix4 invert(BooleanPtr isInvertible) { return invert(this, isInvertible); }
    @Bridge(symbol="GLKMatrix4Invert", optional=true)
    private static native @ByVal GLKMatrix4 invert(@ByVal GLKMatrix4 matrix, BooleanPtr isInvertible);
    public GLKMatrix4 invertAndTranspose(BooleanPtr isInvertible) { return invertAndTranspose(this, isInvertible); }
    @Bridge(symbol="GLKMatrix4InvertAndTranspose", optional=true)
    private static native @ByVal GLKMatrix4 invertAndTranspose(@ByVal GLKMatrix4 matrix, BooleanPtr isInvertible);
    public GLKMatrix4 multiply(GLKMatrix4 matrixRight) { return multiply(this, matrixRight); }
    @Bridge(symbol="GLKMatrix4Multiply", optional=true)
    private static native @ByVal GLKMatrix4 multiply(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKMatrix4 matrixRight);
    public GLKMatrix4 add(GLKMatrix4 matrixRight) { return add(this, matrixRight); }
    @Bridge(symbol="GLKMatrix4Add", optional=true)
    private static native @ByVal GLKMatrix4 add(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKMatrix4 matrixRight);
    public GLKMatrix4 subtract(GLKMatrix4 matrixRight) { return subtract(this, matrixRight); }
    @Bridge(symbol="GLKMatrix4Subtract", optional=true)
    private static native @ByVal GLKMatrix4 subtract(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKMatrix4 matrixRight);
    public GLKMatrix4 translate(float tx, float ty, float tz) { return translate(this, tx, ty, tz); }
    @Bridge(symbol="GLKMatrix4Translate", optional=true)
    private static native @ByVal GLKMatrix4 translate(@ByVal GLKMatrix4 matrix, float tx, float ty, float tz);
    public GLKMatrix4 translateWithVector3(GLKVector3 translationVector) { return translateWithVector3(this, translationVector); }
    @Bridge(symbol="GLKMatrix4TranslateWithVector3", optional=true)
    private static native @ByVal GLKMatrix4 translateWithVector3(@ByVal GLKMatrix4 matrix, @ByVal GLKVector3 translationVector);
    public GLKMatrix4 translateWithVector4(GLKVector4 translationVector) { return translateWithVector4(this, translationVector); }
    @Bridge(symbol="GLKMatrix4TranslateWithVector4", optional=true)
    private static native @ByVal GLKMatrix4 translateWithVector4(@ByVal GLKMatrix4 matrix, @ByVal GLKVector4 translationVector);
    public GLKMatrix4 scale(float sx, float sy, float sz) { return scale(this, sx, sy, sz); }
    @Bridge(symbol="GLKMatrix4Scale", optional=true)
    private static native @ByVal GLKMatrix4 scale(@ByVal GLKMatrix4 matrix, float sx, float sy, float sz);
    public GLKMatrix4 scaleWithVector3(GLKVector3 scaleVector) { return scaleWithVector3(this, scaleVector); }
    @Bridge(symbol="GLKMatrix4ScaleWithVector3", optional=true)
    private static native @ByVal GLKMatrix4 scaleWithVector3(@ByVal GLKMatrix4 matrix, @ByVal GLKVector3 scaleVector);
    public GLKMatrix4 scaleWithVector4(GLKVector4 scaleVector) { return scaleWithVector4(this, scaleVector); }
    @Bridge(symbol="GLKMatrix4ScaleWithVector4", optional=true)
    private static native @ByVal GLKMatrix4 scaleWithVector4(@ByVal GLKMatrix4 matrix, @ByVal GLKVector4 scaleVector);
    public GLKMatrix4 rotate(float radians, float x, float y, float z) { return rotate(this, radians, x, y, z); }
    @Bridge(symbol="GLKMatrix4Rotate", optional=true)
    private static native @ByVal GLKMatrix4 rotate(@ByVal GLKMatrix4 matrix, float radians, float x, float y, float z);
    public GLKMatrix4 rotateWithVector3(float radians, GLKVector3 axisVector) { return rotateWithVector3(this, radians, axisVector); }
    @Bridge(symbol="GLKMatrix4RotateWithVector3", optional=true)
    private static native @ByVal GLKMatrix4 rotateWithVector3(@ByVal GLKMatrix4 matrix, float radians, @ByVal GLKVector3 axisVector);
    public GLKMatrix4 rotateWithVector4(float radians, GLKVector4 axisVector) { return rotateWithVector4(this, radians, axisVector); }
    @Bridge(symbol="GLKMatrix4RotateWithVector4", optional=true)
    private static native @ByVal GLKMatrix4 rotateWithVector4(@ByVal GLKMatrix4 matrix, float radians, @ByVal GLKVector4 axisVector);
    public GLKMatrix4 rotateX(float radians) { return rotateX(this, radians); }
    @Bridge(symbol="GLKMatrix4RotateX", optional=true)
    private static native @ByVal GLKMatrix4 rotateX(@ByVal GLKMatrix4 matrix, float radians);
    public GLKMatrix4 rotateY(float radians) { return rotateY(this, radians); }
    @Bridge(symbol="GLKMatrix4RotateY", optional=true)
    private static native @ByVal GLKMatrix4 rotateY(@ByVal GLKMatrix4 matrix, float radians);
    public GLKMatrix4 rotateZ(float radians) { return rotateZ(this, radians); }
    @Bridge(symbol="GLKMatrix4RotateZ", optional=true)
    private static native @ByVal GLKMatrix4 rotateZ(@ByVal GLKMatrix4 matrix, float radians);
    public GLKVector3 multiplyVector3(GLKVector3 vectorRight) { return multiplyVector3(this, vectorRight); }
    @Bridge(symbol="GLKMatrix4MultiplyVector3", optional=true)
    private static native @ByVal GLKVector3 multiplyVector3(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 multiplyVector3WithTranslation(GLKVector3 vectorRight) { return multiplyVector3WithTranslation(this, vectorRight); }
    @Bridge(symbol="GLKMatrix4MultiplyVector3WithTranslation", optional=true)
    private static native @ByVal GLKVector3 multiplyVector3WithTranslation(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 multiplyAndProjectVector3(GLKVector3 vectorRight) { return multiplyAndProjectVector3(this, vectorRight); }
    @Bridge(symbol="GLKMatrix4MultiplyAndProjectVector3", optional=true)
    private static native @ByVal GLKVector3 multiplyAndProjectVector3(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector3 vectorRight);
    public void multiplyVector3Array(GLKVector3 vectors, @MachineSizedUInt long vectorCount) { multiplyVector3Array(this, vectors, vectorCount); }
    @Bridge(symbol="GLKMatrix4MultiplyVector3Array", optional=true)
    private static native void multiplyVector3Array(@ByVal GLKMatrix4 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    public void multiplyVector3ArrayWithTranslation(GLKVector3 vectors, @MachineSizedUInt long vectorCount) { multiplyVector3ArrayWithTranslation(this, vectors, vectorCount); }
    @Bridge(symbol="GLKMatrix4MultiplyVector3ArrayWithTranslation", optional=true)
    private static native void multiplyVector3ArrayWithTranslation(@ByVal GLKMatrix4 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    public void multiplyAndProjectVector3Array(GLKVector3 vectors, @MachineSizedUInt long vectorCount) { multiplyAndProjectVector3Array(this, vectors, vectorCount); }
    @Bridge(symbol="GLKMatrix4MultiplyAndProjectVector3Array", optional=true)
    private static native void multiplyAndProjectVector3Array(@ByVal GLKMatrix4 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    public GLKVector4 multiplyVector4(GLKVector4 vectorRight) { return multiplyVector4(this, vectorRight); }
    @Bridge(symbol="GLKMatrix4MultiplyVector4", optional=true)
    private static native @ByVal GLKVector4 multiplyVector4(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector4 vectorRight);
    public void multiplyVector4Array(GLKVector4 vectors, @MachineSizedUInt long vectorCount) { multiplyVector4Array(this, vectors, vectorCount); }
    @Bridge(symbol="GLKMatrix4MultiplyVector4Array", optional=true)
    private static native void multiplyVector4Array(@ByVal GLKMatrix4 matrix, GLKVector4 vectors, @MachineSizedUInt long vectorCount);
    /*</methods>*/
}
