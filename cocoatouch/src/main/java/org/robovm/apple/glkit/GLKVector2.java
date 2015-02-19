/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKVector2/*</name>*/ 
    extends /*<extends>*/Struct<GLKVector2>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKVector2Ptr extends Ptr<GLKVector2, GLKVector2Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKVector2.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKVector2() {}
    public GLKVector2(FloatBuffer v) {
        this.setV(v);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @Array({2}) FloatBuffer getV();
    @StructMember(0) public native GLKVector2 setV(@Array({2}) FloatBuffer v);
    /*</members>*/
    /*<methods>*/
    @Bridge(symbol="GLKVector2Make", optional=true)
    public static native @ByVal GLKVector2 create(float x, float y);
    @Bridge(symbol="GLKVector2MakeWithArray", optional=true)
    public static native @ByVal GLKVector2 create(@Array({2}) FloatBuffer values);
    public GLKVector2 negate() { return negate(this); }
    @Bridge(symbol="GLKVector2Negate", optional=true)
    private static native @ByVal GLKVector2 negate(@ByVal GLKVector2 vector);
    public GLKVector2 add(GLKVector2 vectorRight) { return add(this, vectorRight); }
    @Bridge(symbol="GLKVector2Add", optional=true)
    private static native @ByVal GLKVector2 add(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public GLKVector2 subtract(GLKVector2 vectorRight) { return subtract(this, vectorRight); }
    @Bridge(symbol="GLKVector2Subtract", optional=true)
    private static native @ByVal GLKVector2 subtract(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public GLKVector2 multiply(GLKVector2 vectorRight) { return multiply(this, vectorRight); }
    @Bridge(symbol="GLKVector2Multiply", optional=true)
    private static native @ByVal GLKVector2 multiply(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public GLKVector2 divide(GLKVector2 vectorRight) { return divide(this, vectorRight); }
    @Bridge(symbol="GLKVector2Divide", optional=true)
    private static native @ByVal GLKVector2 divide(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public GLKVector2 addScalar(float value) { return addScalar(this, value); }
    @Bridge(symbol="GLKVector2AddScalar", optional=true)
    private static native @ByVal GLKVector2 addScalar(@ByVal GLKVector2 vector, float value);
    public GLKVector2 subtractScalar(float value) { return subtractScalar(this, value); }
    @Bridge(symbol="GLKVector2SubtractScalar", optional=true)
    private static native @ByVal GLKVector2 subtractScalar(@ByVal GLKVector2 vector, float value);
    public GLKVector2 multiplyScalar(float value) { return multiplyScalar(this, value); }
    @Bridge(symbol="GLKVector2MultiplyScalar", optional=true)
    private static native @ByVal GLKVector2 multiplyScalar(@ByVal GLKVector2 vector, float value);
    public GLKVector2 divideScalar(float value) { return divideScalar(this, value); }
    @Bridge(symbol="GLKVector2DivideScalar", optional=true)
    private static native @ByVal GLKVector2 divideScalar(@ByVal GLKVector2 vector, float value);
    public GLKVector2 maximum(GLKVector2 vectorRight) { return maximum(this, vectorRight); }
    @Bridge(symbol="GLKVector2Maximum", optional=true)
    private static native @ByVal GLKVector2 maximum(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public GLKVector2 minimum(GLKVector2 vectorRight) { return minimum(this, vectorRight); }
    @Bridge(symbol="GLKVector2Minimum", optional=true)
    private static native @ByVal GLKVector2 minimum(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public boolean allEqualToVector2(GLKVector2 vectorRight) { return allEqualToVector2(this, vectorRight); }
    @Bridge(symbol="GLKVector2AllEqualToVector2", optional=true)
    private static native boolean allEqualToVector2(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public boolean allEqualToScalar(float value) { return allEqualToScalar(this, value); }
    @Bridge(symbol="GLKVector2AllEqualToScalar", optional=true)
    private static native boolean allEqualToScalar(@ByVal GLKVector2 vector, float value);
    public boolean allGreaterThanVector2(GLKVector2 vectorRight) { return allGreaterThanVector2(this, vectorRight); }
    @Bridge(symbol="GLKVector2AllGreaterThanVector2", optional=true)
    private static native boolean allGreaterThanVector2(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public boolean allGreaterThanScalar(float value) { return allGreaterThanScalar(this, value); }
    @Bridge(symbol="GLKVector2AllGreaterThanScalar", optional=true)
    private static native boolean allGreaterThanScalar(@ByVal GLKVector2 vector, float value);
    public boolean allGreaterThanOrEqualToVector2(GLKVector2 vectorRight) { return allGreaterThanOrEqualToVector2(this, vectorRight); }
    @Bridge(symbol="GLKVector2AllGreaterThanOrEqualToVector2", optional=true)
    private static native boolean allGreaterThanOrEqualToVector2(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public boolean allGreaterThanOrEqualToScalar(float value) { return allGreaterThanOrEqualToScalar(this, value); }
    @Bridge(symbol="GLKVector2AllGreaterThanOrEqualToScalar", optional=true)
    private static native boolean allGreaterThanOrEqualToScalar(@ByVal GLKVector2 vector, float value);
    public GLKVector2 normalize() { return normalize(this); }
    @Bridge(symbol="GLKVector2Normalize", optional=true)
    private static native @ByVal GLKVector2 normalize(@ByVal GLKVector2 vector);
    public float dotProduct(GLKVector2 vectorRight) { return dotProduct(this, vectorRight); }
    @Bridge(symbol="GLKVector2DotProduct", optional=true)
    private static native float dotProduct(@ByVal GLKVector2 vectorLeft, @ByVal GLKVector2 vectorRight);
    public float length() { return length(this); }
    @Bridge(symbol="GLKVector2Length", optional=true)
    private static native float length(@ByVal GLKVector2 vector);
    public float distance(GLKVector2 vectorEnd) { return distance(this, vectorEnd); }
    @Bridge(symbol="GLKVector2Distance", optional=true)
    private static native float distance(@ByVal GLKVector2 vectorStart, @ByVal GLKVector2 vectorEnd);
    public GLKVector2 lerp(GLKVector2 vectorEnd, float t) { return lerp(this, vectorEnd, t); }
    @Bridge(symbol="GLKVector2Lerp", optional=true)
    private static native @ByVal GLKVector2 lerp(@ByVal GLKVector2 vectorStart, @ByVal GLKVector2 vectorEnd, float t);
    public GLKVector2 project(GLKVector2 projectionVector) { return project(this, projectionVector); }
    @Bridge(symbol="GLKVector2Project", optional=true)
    private static native @ByVal GLKVector2 project(@ByVal GLKVector2 vectorToProject, @ByVal GLKVector2 projectionVector);
    /*</methods>*/
}
