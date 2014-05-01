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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("GLKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKit/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="GLKQuaternionIdentity", optional=true)
    public static native @ByVal GLKQuaternion Value__GLKQuaternionIdentity();
    @GlobalValue(symbol="GLKMatrix3Identity", optional=true)
    public static native @ByVal GLKMatrix3 Value__GLKMatrix3Identity();
    @GlobalValue(symbol="GLKMatrix4Identity", optional=true)
    public static native @ByVal GLKMatrix4 Value__GLKMatrix4Identity();
    @GlobalValue(symbol="GLKTextureLoaderApplyPremultiplication", optional=true)
    public static native String Value__GLKTextureLoaderApplyPremultiplication();
    @GlobalValue(symbol="GLKTextureLoaderGenerateMipmaps", optional=true)
    public static native String Value__GLKTextureLoaderGenerateMipmaps();
    @GlobalValue(symbol="GLKTextureLoaderOriginBottomLeft", optional=true)
    public static native String Value__GLKTextureLoaderOriginBottomLeft();
    @GlobalValue(symbol="GLKTextureLoaderGrayscaleAsAlpha", optional=true)
    public static native String Value__GLKTextureLoaderGrayscaleAsAlpha();
    @GlobalValue(symbol="GLKTextureLoaderSRGB", optional=true)
    public static native String Value__GLKTextureLoaderSRGB();
    @GlobalValue(symbol="GLKTextureLoaderErrorDomain", optional=true)
    public static native String Value__GLKTextureLoaderErrorDomain();
    @GlobalValue(symbol="GLKTextureLoaderErrorKey", optional=true)
    public static native String Value__GLKTextureLoaderErrorKey();
    @GlobalValue(symbol="GLKTextureLoaderGLErrorKey", optional=true)
    public static native String Value__GLKTextureLoaderGLErrorKey();
    
    @Bridge(symbol="GLKVector3Make", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Make(float x, float y, float z);
    @Bridge(symbol="GLKVector3MakeWithArray", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3MakeWithArray(@Array({3}) FloatBuffer values);
    @Bridge(symbol="GLKVector3Negate", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Negate(@ByVal GLKVector3 vector);
    @Bridge(symbol="GLKVector3Add", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Add(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3Subtract", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Subtract(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3Multiply", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Multiply(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3Divide", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Divide(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3AddScalar", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3AddScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3SubtractScalar", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3SubtractScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3MultiplyScalar", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3MultiplyScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3DivideScalar", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3DivideScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3Maximum", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Maximum(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3Minimum", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Minimum(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3AllEqualToVector3", optional=true)
    public static native boolean function__GLKVector3AllEqualToVector3(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3AllEqualToScalar", optional=true)
    public static native boolean function__GLKVector3AllEqualToScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3AllGreaterThanVector3", optional=true)
    public static native boolean function__GLKVector3AllGreaterThanVector3(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3AllGreaterThanScalar", optional=true)
    public static native boolean function__GLKVector3AllGreaterThanScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3AllGreaterThanOrEqualToVector3", optional=true)
    public static native boolean function__GLKVector3AllGreaterThanOrEqualToVector3(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3AllGreaterThanOrEqualToScalar", optional=true)
    public static native boolean function__GLKVector3AllGreaterThanOrEqualToScalar(@ByVal GLKVector3 vector, float value);
    @Bridge(symbol="GLKVector3Normalize", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Normalize(@ByVal GLKVector3 vector);
    @Bridge(symbol="GLKVector3DotProduct", optional=true)
    public static native float function__GLKVector3DotProduct(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3Length", optional=true)
    public static native float function__GLKVector3Length(@ByVal GLKVector3 vector);
    @Bridge(symbol="GLKVector3Distance", optional=true)
    public static native float function__GLKVector3Distance(@ByVal GLKVector3 vectorStart, @ByVal GLKVector3 vectorEnd);
    @Bridge(symbol="GLKVector3Lerp", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Lerp(@ByVal GLKVector3 vectorStart, @ByVal GLKVector3 vectorEnd, float t);
    @Bridge(symbol="GLKVector3CrossProduct", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3CrossProduct(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKVector3Project", optional=true)
    public static native @ByVal GLKVector3 function__GLKVector3Project(@ByVal GLKVector3 vectorToProject, @ByVal GLKVector3 projectionVector);
    @Bridge(symbol="GLKVector4Make", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Make(float x, float y, float z, float w);
    @Bridge(symbol="GLKVector4MakeWithArray", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4MakeWithArray(@Array({4}) FloatBuffer values);
    @Bridge(symbol="GLKVector4MakeWithVector3", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4MakeWithVector3(@ByVal GLKVector3 vector, float w);
    @Bridge(symbol="GLKVector4Negate", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Negate(@ByVal GLKVector4 vector);
    @Bridge(symbol="GLKVector4Add", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Add(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4Subtract", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Subtract(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4Multiply", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Multiply(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4Divide", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Divide(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4AddScalar", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4AddScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4SubtractScalar", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4SubtractScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4MultiplyScalar", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4MultiplyScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4DivideScalar", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4DivideScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4Maximum", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Maximum(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4Minimum", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Minimum(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4AllEqualToVector4", optional=true)
    public static native boolean function__GLKVector4AllEqualToVector4(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4AllEqualToScalar", optional=true)
    public static native boolean function__GLKVector4AllEqualToScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4AllGreaterThanVector4", optional=true)
    public static native boolean function__GLKVector4AllGreaterThanVector4(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4AllGreaterThanScalar", optional=true)
    public static native boolean function__GLKVector4AllGreaterThanScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4AllGreaterThanOrEqualToVector4", optional=true)
    public static native boolean function__GLKVector4AllGreaterThanOrEqualToVector4(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4AllGreaterThanOrEqualToScalar", optional=true)
    public static native boolean function__GLKVector4AllGreaterThanOrEqualToScalar(@ByVal GLKVector4 vector, float value);
    @Bridge(symbol="GLKVector4Normalize", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Normalize(@ByVal GLKVector4 vector);
    @Bridge(symbol="GLKVector4DotProduct", optional=true)
    public static native float function__GLKVector4DotProduct(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4Length", optional=true)
    public static native float function__GLKVector4Length(@ByVal GLKVector4 vector);
    @Bridge(symbol="GLKVector4Distance", optional=true)
    public static native float function__GLKVector4Distance(@ByVal GLKVector4 vectorStart, @ByVal GLKVector4 vectorEnd);
    @Bridge(symbol="GLKVector4Lerp", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Lerp(@ByVal GLKVector4 vectorStart, @ByVal GLKVector4 vectorEnd, float t);
    @Bridge(symbol="GLKVector4CrossProduct", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4CrossProduct(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKVector4Project", optional=true)
    public static native @ByVal GLKVector4 function__GLKVector4Project(@ByVal GLKVector4 vectorToProject, @ByVal GLKVector4 projectionVector);
    @Bridge(symbol="GLKQuaternionMake", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMake(float x, float y, float z, float w);
    @Bridge(symbol="GLKQuaternionMakeWithVector3", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMakeWithVector3(@ByVal GLKVector3 vector, float scalar);
    @Bridge(symbol="GLKQuaternionMakeWithArray", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMakeWithArray(@Array({4}) FloatBuffer values);
    @Bridge(symbol="GLKQuaternionMakeWithAngleAndAxis", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMakeWithAngleAndAxis(float radians, float x, float y, float z);
    @Bridge(symbol="GLKQuaternionMakeWithAngleAndVector3Axis", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMakeWithAngleAndVector3Axis(float radians, @ByVal GLKVector3 axisVector);
    @Bridge(symbol="GLKQuaternionMakeWithMatrix3", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMakeWithMatrix3(@ByVal GLKMatrix3 matrix);
    @Bridge(symbol="GLKQuaternionMakeWithMatrix4", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMakeWithMatrix4(@ByVal GLKMatrix4 matrix);
    @Bridge(symbol="GLKQuaternionAngle", optional=true)
    public static native float function__GLKQuaternionAngle(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKQuaternionAxis", optional=true)
    public static native @ByVal GLKVector3 function__GLKQuaternionAxis(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKQuaternionAdd", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionAdd(@ByVal GLKQuaternion quaternionLeft, @ByVal GLKQuaternion quaternionRight);
    @Bridge(symbol="GLKQuaternionSubtract", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionSubtract(@ByVal GLKQuaternion quaternionLeft, @ByVal GLKQuaternion quaternionRight);
    @Bridge(symbol="GLKQuaternionMultiply", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionMultiply(@ByVal GLKQuaternion quaternionLeft, @ByVal GLKQuaternion quaternionRight);
    @Bridge(symbol="GLKQuaternionSlerp", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionSlerp(@ByVal GLKQuaternion quaternionStart, @ByVal GLKQuaternion quaternionEnd, float t);
    @Bridge(symbol="GLKQuaternionLength", optional=true)
    public static native float function__GLKQuaternionLength(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKQuaternionConjugate", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionConjugate(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKQuaternionInvert", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionInvert(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKQuaternionNormalize", optional=true)
    public static native @ByVal GLKQuaternion function__GLKQuaternionNormalize(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKQuaternionRotateVector3", optional=true)
    public static native @ByVal GLKVector3 function__GLKQuaternionRotateVector3(@ByVal GLKQuaternion quaternion, @ByVal GLKVector3 vector);
    @Bridge(symbol="GLKQuaternionRotateVector3Array", optional=true)
    public static native void function__GLKQuaternionRotateVector3Array(@ByVal GLKQuaternion quaternion, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKQuaternionRotateVector4", optional=true)
    public static native @ByVal GLKVector4 function__GLKQuaternionRotateVector4(@ByVal GLKQuaternion quaternion, @ByVal GLKVector4 vector);
    @Bridge(symbol="GLKQuaternionRotateVector4Array", optional=true)
    public static native void function__GLKQuaternionRotateVector4Array(@ByVal GLKQuaternion quaternion, GLKVector4 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKMatrix3Make", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Make(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22);
    @Bridge(symbol="GLKMatrix3MakeAndTranspose", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeAndTranspose(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22);
    @Bridge(symbol="GLKMatrix3MakeWithArray", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeWithArray(@Array({9}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix3MakeWithArrayAndTranspose", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeWithArrayAndTranspose(@Array({9}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix3MakeWithRows", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeWithRows(@ByVal GLKVector3 row0, @ByVal GLKVector3 row1, @ByVal GLKVector3 row2);
    @Bridge(symbol="GLKMatrix3MakeWithColumns", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeWithColumns(@ByVal GLKVector3 column0, @ByVal GLKVector3 column1, @ByVal GLKVector3 column2);
    @Bridge(symbol="GLKMatrix3MakeWithQuaternion", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeWithQuaternion(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKMatrix3MakeScale", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeScale(float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrix3MakeRotation", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeRotation(float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrix3MakeXRotation", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeXRotation(float radians);
    @Bridge(symbol="GLKMatrix3MakeYRotation", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeYRotation(float radians);
    @Bridge(symbol="GLKMatrix3MakeZRotation", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3MakeZRotation(float radians);
    @Bridge(symbol="GLKMatrix3GetMatrix2", optional=true)
    public static native @ByVal GLKMatrix2 function__GLKMatrix3GetMatrix2(@ByVal GLKMatrix3 matrix);
    @Bridge(symbol="GLKMatrix3GetRow", optional=true)
    public static native @ByVal GLKVector3 function__GLKMatrix3GetRow(@ByVal GLKMatrix3 matrix, int row);
    @Bridge(symbol="GLKMatrix3GetColumn", optional=true)
    public static native @ByVal GLKVector3 function__GLKMatrix3GetColumn(@ByVal GLKMatrix3 matrix, int column);
    @Bridge(symbol="GLKMatrix3SetRow", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3SetRow(@ByVal GLKMatrix3 matrix, int row, @ByVal GLKVector3 vector);
    @Bridge(symbol="GLKMatrix3SetColumn", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3SetColumn(@ByVal GLKMatrix3 matrix, int column, @ByVal GLKVector3 vector);
    @Bridge(symbol="GLKMatrix3Transpose", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Transpose(@ByVal GLKMatrix3 matrix);
    @Bridge(symbol="GLKMatrix3Invert", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Invert(@ByVal GLKMatrix3 matrix, BytePtr isInvertible);
    @Bridge(symbol="GLKMatrix3InvertAndTranspose", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3InvertAndTranspose(@ByVal GLKMatrix3 matrix, BytePtr isInvertible);
    @Bridge(symbol="GLKMatrix3Multiply", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Multiply(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKMatrix3 matrixRight);
    @Bridge(symbol="GLKMatrix3Add", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Add(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKMatrix3 matrixRight);
    @Bridge(symbol="GLKMatrix3Subtract", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Subtract(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKMatrix3 matrixRight);
    @Bridge(symbol="GLKMatrix3Scale", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Scale(@ByVal GLKMatrix3 matrix, float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrix3ScaleWithVector3", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3ScaleWithVector3(@ByVal GLKMatrix3 matrix, @ByVal GLKVector3 scaleVector);
    @Bridge(symbol="GLKMatrix3ScaleWithVector4", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3ScaleWithVector4(@ByVal GLKMatrix3 matrix, @ByVal GLKVector4 scaleVector);
    @Bridge(symbol="GLKMatrix3Rotate", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3Rotate(@ByVal GLKMatrix3 matrix, float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrix3RotateWithVector3", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3RotateWithVector3(@ByVal GLKMatrix3 matrix, float radians, @ByVal GLKVector3 axisVector);
    @Bridge(symbol="GLKMatrix3RotateWithVector4", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3RotateWithVector4(@ByVal GLKMatrix3 matrix, float radians, @ByVal GLKVector4 axisVector);
    @Bridge(symbol="GLKMatrix3RotateX", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3RotateX(@ByVal GLKMatrix3 matrix, float radians);
    @Bridge(symbol="GLKMatrix3RotateY", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3RotateY(@ByVal GLKMatrix3 matrix, float radians);
    @Bridge(symbol="GLKMatrix3RotateZ", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix3RotateZ(@ByVal GLKMatrix3 matrix, float radians);
    @Bridge(symbol="GLKMatrix3MultiplyVector3", optional=true)
    public static native @ByVal GLKVector3 function__GLKMatrix3MultiplyVector3(@ByVal GLKMatrix3 matrixLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKMatrix3MultiplyVector3Array", optional=true)
    public static native void function__GLKMatrix3MultiplyVector3Array(@ByVal GLKMatrix3 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKMatrix4Make", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Make(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33);
    @Bridge(symbol="GLKMatrix4MakeAndTranspose", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeAndTranspose(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33);
    @Bridge(symbol="GLKMatrix4MakeWithArray", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeWithArray(@Array({16}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix4MakeWithArrayAndTranspose", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeWithArrayAndTranspose(@Array({16}) FloatBuffer values);
    @Bridge(symbol="GLKMatrix4MakeWithRows", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeWithRows(@ByVal GLKVector4 row0, @ByVal GLKVector4 row1, @ByVal GLKVector4 row2, @ByVal GLKVector4 row3);
    @Bridge(symbol="GLKMatrix4MakeWithColumns", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeWithColumns(@ByVal GLKVector4 column0, @ByVal GLKVector4 column1, @ByVal GLKVector4 column2, @ByVal GLKVector4 column3);
    @Bridge(symbol="GLKMatrix4MakeWithQuaternion", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeWithQuaternion(@ByVal GLKQuaternion quaternion);
    @Bridge(symbol="GLKMatrix4MakeTranslation", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeTranslation(float tx, float ty, float tz);
    @Bridge(symbol="GLKMatrix4MakeScale", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeScale(float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrix4MakeRotation", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeRotation(float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrix4MakeXRotation", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeXRotation(float radians);
    @Bridge(symbol="GLKMatrix4MakeYRotation", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeYRotation(float radians);
    @Bridge(symbol="GLKMatrix4MakeZRotation", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeZRotation(float radians);
    @Bridge(symbol="GLKMatrix4MakePerspective", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakePerspective(float fovyRadians, float aspect, float nearZ, float farZ);
    @Bridge(symbol="GLKMatrix4MakeFrustum", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeFrustum(float left, float right, float bottom, float top, float nearZ, float farZ);
    @Bridge(symbol="GLKMatrix4MakeOrtho", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeOrtho(float left, float right, float bottom, float top, float nearZ, float farZ);
    @Bridge(symbol="GLKMatrix4MakeLookAt", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4MakeLookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ);
    @Bridge(symbol="GLKMatrix4GetMatrix3", optional=true)
    public static native @ByVal GLKMatrix3 function__GLKMatrix4GetMatrix3(@ByVal GLKMatrix4 matrix);
    @Bridge(symbol="GLKMatrix4GetMatrix2", optional=true)
    public static native @ByVal GLKMatrix2 function__GLKMatrix4GetMatrix2(@ByVal GLKMatrix4 matrix);
    @Bridge(symbol="GLKMatrix4GetRow", optional=true)
    public static native @ByVal GLKVector4 function__GLKMatrix4GetRow(@ByVal GLKMatrix4 matrix, int row);
    @Bridge(symbol="GLKMatrix4GetColumn", optional=true)
    public static native @ByVal GLKVector4 function__GLKMatrix4GetColumn(@ByVal GLKMatrix4 matrix, int column);
    @Bridge(symbol="GLKMatrix4SetRow", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4SetRow(@ByVal GLKMatrix4 matrix, int row, @ByVal GLKVector4 vector);
    @Bridge(symbol="GLKMatrix4SetColumn", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4SetColumn(@ByVal GLKMatrix4 matrix, int column, @ByVal GLKVector4 vector);
    @Bridge(symbol="GLKMatrix4Transpose", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Transpose(@ByVal GLKMatrix4 matrix);
    @Bridge(symbol="GLKMatrix4Invert", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Invert(@ByVal GLKMatrix4 matrix, BytePtr isInvertible);
    @Bridge(symbol="GLKMatrix4InvertAndTranspose", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4InvertAndTranspose(@ByVal GLKMatrix4 matrix, BytePtr isInvertible);
    @Bridge(symbol="GLKMatrix4Multiply", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Multiply(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKMatrix4 matrixRight);
    @Bridge(symbol="GLKMatrix4Add", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Add(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKMatrix4 matrixRight);
    @Bridge(symbol="GLKMatrix4Subtract", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Subtract(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKMatrix4 matrixRight);
    @Bridge(symbol="GLKMatrix4Translate", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Translate(@ByVal GLKMatrix4 matrix, float tx, float ty, float tz);
    @Bridge(symbol="GLKMatrix4TranslateWithVector3", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4TranslateWithVector3(@ByVal GLKMatrix4 matrix, @ByVal GLKVector3 translationVector);
    @Bridge(symbol="GLKMatrix4TranslateWithVector4", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4TranslateWithVector4(@ByVal GLKMatrix4 matrix, @ByVal GLKVector4 translationVector);
    @Bridge(symbol="GLKMatrix4Scale", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Scale(@ByVal GLKMatrix4 matrix, float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrix4ScaleWithVector3", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4ScaleWithVector3(@ByVal GLKMatrix4 matrix, @ByVal GLKVector3 scaleVector);
    @Bridge(symbol="GLKMatrix4ScaleWithVector4", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4ScaleWithVector4(@ByVal GLKMatrix4 matrix, @ByVal GLKVector4 scaleVector);
    @Bridge(symbol="GLKMatrix4Rotate", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4Rotate(@ByVal GLKMatrix4 matrix, float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrix4RotateWithVector3", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4RotateWithVector3(@ByVal GLKMatrix4 matrix, float radians, @ByVal GLKVector3 axisVector);
    @Bridge(symbol="GLKMatrix4RotateWithVector4", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4RotateWithVector4(@ByVal GLKMatrix4 matrix, float radians, @ByVal GLKVector4 axisVector);
    @Bridge(symbol="GLKMatrix4RotateX", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4RotateX(@ByVal GLKMatrix4 matrix, float radians);
    @Bridge(symbol="GLKMatrix4RotateY", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4RotateY(@ByVal GLKMatrix4 matrix, float radians);
    @Bridge(symbol="GLKMatrix4RotateZ", optional=true)
    public static native @ByVal GLKMatrix4 function__GLKMatrix4RotateZ(@ByVal GLKMatrix4 matrix, float radians);
    @Bridge(symbol="GLKMatrix4MultiplyVector3", optional=true)
    public static native @ByVal GLKVector3 function__GLKMatrix4MultiplyVector3(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKMatrix4MultiplyVector3WithTranslation", optional=true)
    public static native @ByVal GLKVector3 function__GLKMatrix4MultiplyVector3WithTranslation(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKMatrix4MultiplyAndProjectVector3", optional=true)
    public static native @ByVal GLKVector3 function__GLKMatrix4MultiplyAndProjectVector3(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector3 vectorRight);
    @Bridge(symbol="GLKMatrix4MultiplyVector3Array", optional=true)
    public static native void function__GLKMatrix4MultiplyVector3Array(@ByVal GLKMatrix4 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKMatrix4MultiplyVector3ArrayWithTranslation", optional=true)
    public static native void function__GLKMatrix4MultiplyVector3ArrayWithTranslation(@ByVal GLKMatrix4 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKMatrix4MultiplyAndProjectVector3Array", optional=true)
    public static native void function__GLKMatrix4MultiplyAndProjectVector3Array(@ByVal GLKMatrix4 matrix, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKMatrix4MultiplyVector4", optional=true)
    public static native @ByVal GLKVector4 function__GLKMatrix4MultiplyVector4(@ByVal GLKMatrix4 matrixLeft, @ByVal GLKVector4 vectorRight);
    @Bridge(symbol="GLKMatrix4MultiplyVector4Array", optional=true)
    public static native void function__GLKMatrix4MultiplyVector4Array(@ByVal GLKMatrix4 matrix, GLKVector4 vectors, @MachineSizedUInt long vectorCount);
    @Bridge(symbol="GLKVector2Make", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Make(float x, float y);
    @Bridge(symbol="GLKVector2MakeWithArray", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2MakeWithArray(@Array({2}) FloatBuffer values);
    @Bridge(symbol="GLKVector2Negate", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Negate(@ByVal GLKVector2 vector);
    @Bridge(symbol="GLKVector2Add", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Add(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2Subtract", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Subtract(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2Multiply", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Multiply(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2Divide", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Divide(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2AddScalar", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2AddScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2SubtractScalar", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2SubtractScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2MultiplyScalar", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2MultiplyScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2DivideScalar", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2DivideScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2Maximum", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Maximum(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2Minimum", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Minimum(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2AllEqualToVector2", optional=true)
    public static native boolean function__GLKVector2AllEqualToVector2(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2AllEqualToScalar", optional=true)
    public static native boolean function__GLKVector2AllEqualToScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2AllGreaterThanVector2", optional=true)
    public static native boolean function__GLKVector2AllGreaterThanVector2(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2AllGreaterThanScalar", optional=true)
    public static native boolean function__GLKVector2AllGreaterThanScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2AllGreaterThanOrEqualToVector2", optional=true)
    public static native boolean function__GLKVector2AllGreaterThanOrEqualToVector2(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2AllGreaterThanOrEqualToScalar", optional=true)
    public static native boolean function__GLKVector2AllGreaterThanOrEqualToScalar(@ByVal GLKVector2 vector, float value);
    @Bridge(symbol="GLKVector2Normalize", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Normalize(@ByVal GLKVector2 vector);
    @Bridge(symbol="GLKVector2DotProduct", optional=true)
    public static native float function__GLKVector2DotProduct(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    @Bridge(symbol="GLKVector2Length", optional=true)
    public static native float function__GLKVector2Length(@ByVal GLKVector2 vector);
    @Bridge(symbol="GLKVector2Distance", optional=true)
    public static native float function__GLKVector2Distance(@ByVal GLKVector2 vectorStart, @ByVal GLKVector2 vectorEnd);
    @Bridge(symbol="GLKVector2Lerp", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Lerp(@ByVal GLKVector2 vectorStart, @ByVal GLKVector2 vectorEnd, float t);
    @Bridge(symbol="GLKVector2Project", optional=true)
    public static native @ByVal GLKVector2 function__GLKVector2Project(@ByVal GLKVector2 vectorToProject, @ByVal GLKVector2 projectionVector);
    @Bridge(symbol="GLKMathProject", optional=true)
    public static native @ByVal GLKVector3 function__GLKMathProject(@ByVal GLKVector3 object, @ByVal GLKMatrix4 model, @ByVal GLKMatrix4 projection, IntPtr viewport);
    @Bridge(symbol="GLKMathUnproject", optional=true)
    public static native @ByVal GLKVector3 function__GLKMathUnproject(@ByVal GLKVector3 window, @ByVal GLKMatrix4 model, @ByVal GLKMatrix4 projection, IntPtr viewport, BytePtr success);
    /*</methods>*/
}
