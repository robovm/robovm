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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKVector4/*</name>*/ 
    extends /*<extends>*/Struct<GLKVector4>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKVector4Ptr extends Ptr<GLKVector4, GLKVector4Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKVector4.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKVector4() {}
    public GLKVector4(FloatBuffer v) {
        this.setV(v);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array({4}) FloatBuffer getV();
    @StructMember(0) public native GLKVector4 setV(@Array({4}) FloatBuffer v);
    /*</members>*/
    /*<methods>*/
    @Bridge(symbol="GLKVector4Make", optional=true)
    public static native @ByVal GLKVector4 create(float x, float y, float z, float w);
    @Bridge(symbol="GLKVector4MakeWithArray", optional=true)
    public static native @ByVal GLKVector4 create(@Array({4}) FloatBuffer values);
    @Bridge(symbol="GLKVector4MakeWithVector3", optional=true)
    public static native @ByVal GLKVector4 create(@ByVal GLKVector3 vector, float w);
    public GLKVector4 negate() { return negate(this); }
    @Bridge(symbol="GLKVector4Negate", optional=true)
    private static native @ByVal GLKVector4 negate(@ByVal GLKVector4 vector);
    public GLKVector4 add(GLKVector4 vectorRight) { return add(this, vectorRight); }
    @Bridge(symbol="GLKVector4Add", optional=true)
    private static native @ByVal GLKVector4 add(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public GLKVector4 subtract(GLKVector4 vectorRight) { return subtract(this, vectorRight); }
    @Bridge(symbol="GLKVector4Subtract", optional=true)
    private static native @ByVal GLKVector4 subtract(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public GLKVector4 multiply(GLKVector4 vectorRight) { return multiply(this, vectorRight); }
    @Bridge(symbol="GLKVector4Multiply", optional=true)
    private static native @ByVal GLKVector4 multiply(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public GLKVector4 divide(GLKVector4 vectorRight) { return divide(this, vectorRight); }
    @Bridge(symbol="GLKVector4Divide", optional=true)
    private static native @ByVal GLKVector4 divide(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public GLKVector4 addScalar(float value) { return addScalar(this, value); }
    @Bridge(symbol="GLKVector4AddScalar", optional=true)
    private static native @ByVal GLKVector4 addScalar(@ByVal GLKVector4 vector, float value);
    public GLKVector4 subtractScalar(float value) { return subtractScalar(this, value); }
    @Bridge(symbol="GLKVector4SubtractScalar", optional=true)
    private static native @ByVal GLKVector4 subtractScalar(@ByVal GLKVector4 vector, float value);
    public GLKVector4 multiplyScalar(float value) { return multiplyScalar(this, value); }
    @Bridge(symbol="GLKVector4MultiplyScalar", optional=true)
    private static native @ByVal GLKVector4 multiplyScalar(@ByVal GLKVector4 vector, float value);
    public GLKVector4 divideScalar(float value) { return divideScalar(this, value); }
    @Bridge(symbol="GLKVector4DivideScalar", optional=true)
    private static native @ByVal GLKVector4 divideScalar(@ByVal GLKVector4 vector, float value);
    public GLKVector4 maximum(GLKVector4 vectorRight) { return maximum(this, vectorRight); }
    @Bridge(symbol="GLKVector4Maximum", optional=true)
    private static native @ByVal GLKVector4 maximum(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public GLKVector4 minimum(GLKVector4 vectorRight) { return minimum(this, vectorRight); }
    @Bridge(symbol="GLKVector4Minimum", optional=true)
    private static native @ByVal GLKVector4 minimum(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public boolean allEqualToVector4(GLKVector4 vectorRight) { return allEqualToVector4(this, vectorRight); }
    @Bridge(symbol="GLKVector4AllEqualToVector4", optional=true)
    private static native boolean allEqualToVector4(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public boolean allEqualToScalar(float value) { return allEqualToScalar(this, value); }
    @Bridge(symbol="GLKVector4AllEqualToScalar", optional=true)
    private static native boolean allEqualToScalar(@ByVal GLKVector4 vector, float value);
    public boolean allGreaterThanVector4(GLKVector4 vectorRight) { return allGreaterThanVector4(this, vectorRight); }
    @Bridge(symbol="GLKVector4AllGreaterThanVector4", optional=true)
    private static native boolean allGreaterThanVector4(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public boolean allGreaterThanScalar(float value) { return allGreaterThanScalar(this, value); }
    @Bridge(symbol="GLKVector4AllGreaterThanScalar", optional=true)
    private static native boolean allGreaterThanScalar(@ByVal GLKVector4 vector, float value);
    public boolean allGreaterThanOrEqualToVector4(GLKVector4 vectorRight) { return allGreaterThanOrEqualToVector4(this, vectorRight); }
    @Bridge(symbol="GLKVector4AllGreaterThanOrEqualToVector4", optional=true)
    private static native boolean allGreaterThanOrEqualToVector4(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public boolean allGreaterThanOrEqualToScalar(float value) { return allGreaterThanOrEqualToScalar(this, value); }
    @Bridge(symbol="GLKVector4AllGreaterThanOrEqualToScalar", optional=true)
    private static native boolean allGreaterThanOrEqualToScalar(@ByVal GLKVector4 vector, float value);
    public GLKVector4 normalize() { return normalize(this); }
    @Bridge(symbol="GLKVector4Normalize", optional=true)
    private static native @ByVal GLKVector4 normalize(@ByVal GLKVector4 vector);
    public float dotProduct(GLKVector4 vectorRight) { return dotProduct(this, vectorRight); }
    @Bridge(symbol="GLKVector4DotProduct", optional=true)
    private static native float dotProduct(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public float length() { return length(this); }
    @Bridge(symbol="GLKVector4Length", optional=true)
    private static native float length(@ByVal GLKVector4 vector);
    public float distance(GLKVector4 vectorEnd) { return distance(this, vectorEnd); }
    @Bridge(symbol="GLKVector4Distance", optional=true)
    private static native float distance(@ByVal GLKVector4 vectorStart, @ByVal GLKVector4 vectorEnd);
    public GLKVector4 lerp(GLKVector4 vectorEnd, float t) { return lerp(this, vectorEnd, t); }
    @Bridge(symbol="GLKVector4Lerp", optional=true)
    private static native @ByVal GLKVector4 lerp(@ByVal GLKVector4 vectorStart, @ByVal GLKVector4 vectorEnd, float t);
    public GLKVector4 crossProduct(GLKVector4 vectorRight) { return crossProduct(this, vectorRight); }
    @Bridge(symbol="GLKVector4CrossProduct", optional=true)
    private static native @ByVal GLKVector4 crossProduct(@ByVal GLKVector4 vectorLeft, @ByVal GLKVector4 vectorRight);
    public GLKVector4 project(GLKVector4 projectionVector) { return project(this, projectionVector); }
    @Bridge(symbol="GLKVector4Project", optional=true)
    private static native @ByVal GLKVector4 project(@ByVal GLKVector4 vectorToProject, @ByVal GLKVector4 projectionVector);
    /*</methods>*/
}
