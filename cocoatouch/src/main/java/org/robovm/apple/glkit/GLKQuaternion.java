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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKQuaternion/*</name>*/ 
    extends /*<extends>*/Struct<GLKQuaternion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKQuaternionPtr extends Ptr<GLKQuaternion, GLKQuaternionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKQuaternion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKQuaternion() {}
    public GLKQuaternion(FloatBuffer q) {
        this.setQ(q);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array({4}) FloatBuffer getQ();
    @StructMember(0) public native GLKQuaternion setQ(@Array({4}) FloatBuffer q);
    /*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="GLKQuaternionIdentity", optional=true)
    public static native @ByVal GLKQuaternion Identity();
    
    @Bridge(symbol="GLKQuaternionMake", optional=true)
    public static native @ByVal GLKQuaternion create(float x, float y, float z, float w);
    @Bridge(symbol="GLKQuaternionMakeWithVector3", optional=true)
    public static native @ByVal GLKQuaternion create(@ByVal GLKVector3 vector, float scalar);
    @Bridge(symbol="GLKQuaternionMakeWithArray", optional=true)
    public static native @ByVal GLKQuaternion create(@Array({4}) FloatBuffer values);
    @Bridge(symbol="GLKQuaternionMakeWithAngleAndAxis", optional=true)
    public static native @ByVal GLKQuaternion createRotation(float radians, float x, float y, float z);
    @Bridge(symbol="GLKQuaternionMakeWithAngleAndVector3Axis", optional=true)
    public static native @ByVal GLKQuaternion createRotation(float radians, @ByVal GLKVector3 axisVector);
    @Bridge(symbol="GLKQuaternionMakeWithMatrix3", optional=true)
    public static native @ByVal GLKQuaternion create(@ByVal GLKMatrix3 matrix);
    @Bridge(symbol="GLKQuaternionMakeWithMatrix4", optional=true)
    public static native @ByVal GLKQuaternion create(@ByVal GLKMatrix4 matrix);
    public float angle() { return angle(this); }
    @Bridge(symbol="GLKQuaternionAngle", optional=true)
    private static native float angle(@ByVal GLKQuaternion quaternion);
    public GLKVector3 axis() { return axis(this); }
    @Bridge(symbol="GLKQuaternionAxis", optional=true)
    private static native @ByVal GLKVector3 axis(@ByVal GLKQuaternion quaternion);
    public GLKQuaternion add(GLKQuaternion quaternionRight) { return add(this, quaternionRight); }
    @Bridge(symbol="GLKQuaternionAdd", optional=true)
    private static native @ByVal GLKQuaternion add(@ByVal GLKQuaternion quaternionLeft, @ByVal GLKQuaternion quaternionRight);
    public GLKQuaternion subtract(GLKQuaternion quaternionRight) { return subtract(this, quaternionRight); }
    @Bridge(symbol="GLKQuaternionSubtract", optional=true)
    private static native @ByVal GLKQuaternion subtract(@ByVal GLKQuaternion quaternionLeft, @ByVal GLKQuaternion quaternionRight);
    public GLKQuaternion multiply(GLKQuaternion quaternionRight) { return multiply(this, quaternionRight); }
    @Bridge(symbol="GLKQuaternionMultiply", optional=true)
    private static native @ByVal GLKQuaternion multiply(@ByVal GLKQuaternion quaternionLeft, @ByVal GLKQuaternion quaternionRight);
    public GLKQuaternion slerp(GLKQuaternion quaternionEnd, float t) { return slerp(this, quaternionEnd, t); }
    @Bridge(symbol="GLKQuaternionSlerp", optional=true)
    private static native @ByVal GLKQuaternion slerp(@ByVal GLKQuaternion quaternionStart, @ByVal GLKQuaternion quaternionEnd, float t);
    public float length() { return length(this); }
    @Bridge(symbol="GLKQuaternionLength", optional=true)
    private static native float length(@ByVal GLKQuaternion quaternion);
    public GLKQuaternion conjugate() { return conjugate(this); }
    @Bridge(symbol="GLKQuaternionConjugate", optional=true)
    private static native @ByVal GLKQuaternion conjugate(@ByVal GLKQuaternion quaternion);
    public GLKQuaternion invert() { return invert(this); }
    @Bridge(symbol="GLKQuaternionInvert", optional=true)
    private static native @ByVal GLKQuaternion invert(@ByVal GLKQuaternion quaternion);
    public GLKQuaternion normalize() { return normalize(this); }
    @Bridge(symbol="GLKQuaternionNormalize", optional=true)
    private static native @ByVal GLKQuaternion normalize(@ByVal GLKQuaternion quaternion);
    public GLKVector3 rotateVector3(GLKVector3 vector) { return rotateVector3(this, vector); }
    @Bridge(symbol="GLKQuaternionRotateVector3", optional=true)
    private static native @ByVal GLKVector3 rotateVector3(@ByVal GLKQuaternion quaternion, @ByVal GLKVector3 vector);
    public void rotateVector3Array(GLKVector3 vectors, @MachineSizedUInt long vectorCount) { rotateVector3Array(this, vectors, vectorCount); }
    @Bridge(symbol="GLKQuaternionRotateVector3Array", optional=true)
    private static native void rotateVector3Array(@ByVal GLKQuaternion quaternion, GLKVector3 vectors, @MachineSizedUInt long vectorCount);
    public GLKVector4 rotateVector4(GLKVector4 vector) { return rotateVector4(this, vector); }
    @Bridge(symbol="GLKQuaternionRotateVector4", optional=true)
    private static native @ByVal GLKVector4 rotateVector4(@ByVal GLKQuaternion quaternion, @ByVal GLKVector4 vector);
    public void rotateVector4Array(GLKVector4 vectors, @MachineSizedUInt long vectorCount) { rotateVector4Array(this, vectors, vectorCount); }
    @Bridge(symbol="GLKQuaternionRotateVector4Array", optional=true)
    private static native void rotateVector4Array(@ByVal GLKQuaternion quaternion, GLKVector4 vectors, @MachineSizedUInt long vectorCount);
    /*</methods>*/
}
