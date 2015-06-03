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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKVector3/*</name>*/ 
    extends /*<extends>*/Struct<GLKVector3>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKVector3Ptr extends Ptr<GLKVector3, GLKVector3Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKVector3.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKVector3() {}
    public GLKVector3(FloatBuffer v) {
        this.setV(v);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array({3}) FloatBuffer getV();
    @StructMember(0) public native GLKVector3 setV(@Array({3}) FloatBuffer v);
    /*</members>*/
    /*<methods>*/
    @Bridge(symbol="GLKVector3Make", optional=true)
    public static native @ByVal GLKVector3 create(float x, float y, float z);
    @Bridge(symbol="GLKVector3MakeWithArray", optional=true)
    public static native @ByVal GLKVector3 create(@Array({3}) FloatBuffer values);
    public GLKVector3 negate() { return negate(this); }
    @Bridge(symbol="GLKVector3Negate", optional=true)
    private static native @ByVal GLKVector3 negate(@ByVal GLKVector3 vector);
    public GLKVector3 add(GLKVector3 vectorRight) { return add(this, vectorRight); }
    @Bridge(symbol="GLKVector3Add", optional=true)
    private static native @ByVal GLKVector3 add(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 subtract(GLKVector3 vectorRight) { return subtract(this, vectorRight); }
    @Bridge(symbol="GLKVector3Subtract", optional=true)
    private static native @ByVal GLKVector3 subtract(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 multiply(GLKVector3 vectorRight) { return multiply(this, vectorRight); }
    @Bridge(symbol="GLKVector3Multiply", optional=true)
    private static native @ByVal GLKVector3 multiply(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 divide(GLKVector3 vectorRight) { return divide(this, vectorRight); }
    @Bridge(symbol="GLKVector3Divide", optional=true)
    private static native @ByVal GLKVector3 divide(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 addScalar(float value) { return addScalar(this, value); }
    @Bridge(symbol="GLKVector3AddScalar", optional=true)
    private static native @ByVal GLKVector3 addScalar(@ByVal GLKVector3 vector, float value);
    public GLKVector3 subtractScalar(float value) { return subtractScalar(this, value); }
    @Bridge(symbol="GLKVector3SubtractScalar", optional=true)
    private static native @ByVal GLKVector3 subtractScalar(@ByVal GLKVector3 vector, float value);
    public GLKVector3 multiplyScalar(float value) { return multiplyScalar(this, value); }
    @Bridge(symbol="GLKVector3MultiplyScalar", optional=true)
    private static native @ByVal GLKVector3 multiplyScalar(@ByVal GLKVector3 vector, float value);
    public GLKVector3 divideScalar(float value) { return divideScalar(this, value); }
    @Bridge(symbol="GLKVector3DivideScalar", optional=true)
    private static native @ByVal GLKVector3 divideScalar(@ByVal GLKVector3 vector, float value);
    public GLKVector3 maximum(GLKVector3 vectorRight) { return maximum(this, vectorRight); }
    @Bridge(symbol="GLKVector3Maximum", optional=true)
    private static native @ByVal GLKVector3 maximum(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 minimum(GLKVector3 vectorRight) { return minimum(this, vectorRight); }
    @Bridge(symbol="GLKVector3Minimum", optional=true)
    private static native @ByVal GLKVector3 minimum(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public boolean allEqualToVector3(GLKVector3 vectorRight) { return allEqualToVector3(this, vectorRight); }
    @Bridge(symbol="GLKVector3AllEqualToVector3", optional=true)
    private static native boolean allEqualToVector3(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public boolean allEqualToScalar(float value) { return allEqualToScalar(this, value); }
    @Bridge(symbol="GLKVector3AllEqualToScalar", optional=true)
    private static native boolean allEqualToScalar(@ByVal GLKVector3 vector, float value);
    public boolean allGreaterThanVector3(GLKVector3 vectorRight) { return allGreaterThanVector3(this, vectorRight); }
    @Bridge(symbol="GLKVector3AllGreaterThanVector3", optional=true)
    private static native boolean allGreaterThanVector3(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public boolean allGreaterThanScalar(float value) { return allGreaterThanScalar(this, value); }
    @Bridge(symbol="GLKVector3AllGreaterThanScalar", optional=true)
    private static native boolean allGreaterThanScalar(@ByVal GLKVector3 vector, float value);
    public boolean allGreaterThanOrEqualToVector3(GLKVector3 vectorRight) { return allGreaterThanOrEqualToVector3(this, vectorRight); }
    @Bridge(symbol="GLKVector3AllGreaterThanOrEqualToVector3", optional=true)
    private static native boolean allGreaterThanOrEqualToVector3(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public boolean allGreaterThanOrEqualToScalar(float value) { return allGreaterThanOrEqualToScalar(this, value); }
    @Bridge(symbol="GLKVector3AllGreaterThanOrEqualToScalar", optional=true)
    private static native boolean allGreaterThanOrEqualToScalar(@ByVal GLKVector3 vector, float value);
    public GLKVector3 normalize() { return normalize(this); }
    @Bridge(symbol="GLKVector3Normalize", optional=true)
    private static native @ByVal GLKVector3 normalize(@ByVal GLKVector3 vector);
    public float dotProduct(GLKVector3 vectorRight) { return dotProduct(this, vectorRight); }
    @Bridge(symbol="GLKVector3DotProduct", optional=true)
    private static native float dotProduct(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public float length() { return length(this); }
    @Bridge(symbol="GLKVector3Length", optional=true)
    private static native float length(@ByVal GLKVector3 vector);
    public float distance(GLKVector3 vectorEnd) { return distance(this, vectorEnd); }
    @Bridge(symbol="GLKVector3Distance", optional=true)
    private static native float distance(@ByVal GLKVector3 vectorStart, @ByVal GLKVector3 vectorEnd);
    public GLKVector3 lerp(GLKVector3 vectorEnd, float t) { return lerp(this, vectorEnd, t); }
    @Bridge(symbol="GLKVector3Lerp", optional=true)
    private static native @ByVal GLKVector3 lerp(@ByVal GLKVector3 vectorStart, @ByVal GLKVector3 vectorEnd, float t);
    public GLKVector3 crossProduct(GLKVector3 vectorRight) { return crossProduct(this, vectorRight); }
    @Bridge(symbol="GLKVector3CrossProduct", optional=true)
    private static native @ByVal GLKVector3 crossProduct(@ByVal GLKVector3 vectorLeft, @ByVal GLKVector3 vectorRight);
    public GLKVector3 project(GLKVector3 projectionVector) { return project(this, projectionVector); }
    @Bridge(symbol="GLKVector3Project", optional=true)
    private static native @ByVal GLKVector3 project(@ByVal GLKVector3 vectorToProject, @ByVal GLKVector3 projectionVector);
    /*</methods>*/
}
