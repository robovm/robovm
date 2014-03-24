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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKMatrixStack/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKMatrixStackPtr extends Ptr<GLKMatrixStack, GLKMatrixStackPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKMatrixStack.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected GLKMatrixStack() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="GLKMatrixStackCreate")
    public static native GLKMatrixStack create(CFAllocator alloc);
    @Bridge(symbol="GLKMatrixStackGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="GLKMatrixStackPush")
    public native void push();
    @Bridge(symbol="GLKMatrixStackPop")
    public native void pop();
    @Bridge(symbol="GLKMatrixStackSize")
    public native int size();
    @Bridge(symbol="GLKMatrixStackLoadMatrix4")
    public native void loadMatrix4(@ByVal GLKMatrix4 matrix);
    @Bridge(symbol="GLKMatrixStackGetMatrix4")
    public native @ByVal GLKMatrix4 getMatrix4();
    @Bridge(symbol="GLKMatrixStackGetMatrix3")
    public native @ByVal GLKMatrix3 getMatrix3();
    @Bridge(symbol="GLKMatrixStackGetMatrix2")
    public native @ByVal GLKMatrix2 getMatrix2();
    @Bridge(symbol="GLKMatrixStackGetMatrix4Inverse")
    public native @ByVal GLKMatrix4 getMatrix4Inverse();
    @Bridge(symbol="GLKMatrixStackGetMatrix4InverseTranspose")
    public native @ByVal GLKMatrix4 getMatrix4InverseTranspose();
    @Bridge(symbol="GLKMatrixStackGetMatrix3Inverse")
    public native @ByVal GLKMatrix3 getMatrix3Inverse();
    @Bridge(symbol="GLKMatrixStackGetMatrix3InverseTranspose")
    public native @ByVal GLKMatrix3 getMatrix3InverseTranspose();
    @Bridge(symbol="GLKMatrixStackMultiplyMatrix4")
    public native void multiplyMatrix4(@ByVal GLKMatrix4 matrix);
    @Bridge(symbol="GLKMatrixStackMultiplyMatrixStack")
    public native void multiplyMatrixStack(GLKMatrixStack stackRight);
    @Bridge(symbol="GLKMatrixStackTranslate")
    public native void translate(float tx, float ty, float tz);
    @Bridge(symbol="GLKMatrixStackTranslateWithVector3")
    public native void translateWithVector3(@ByVal GLKVector3 translationVector);
    @Bridge(symbol="GLKMatrixStackTranslateWithVector4")
    public native void translateWithVector4(@ByVal GLKVector4 translationVector);
    @Bridge(symbol="GLKMatrixStackScale")
    public native void scale(float sx, float sy, float sz);
    @Bridge(symbol="GLKMatrixStackScaleWithVector3")
    public native void scaleWithVector3(@ByVal GLKVector3 scaleVector);
    @Bridge(symbol="GLKMatrixStackScaleWithVector4")
    public native void scaleWithVector4(@ByVal GLKVector4 scaleVector);
    @Bridge(symbol="GLKMatrixStackRotate")
    public native void rotate(float radians, float x, float y, float z);
    @Bridge(symbol="GLKMatrixStackRotateWithVector3")
    public native void rotateWithVector3(float radians, @ByVal GLKVector3 axisVector);
    @Bridge(symbol="GLKMatrixStackRotateWithVector4")
    public native void rotateWithVector4(float radians, @ByVal GLKVector4 axisVector);
    @Bridge(symbol="GLKMatrixStackRotateX")
    public native void rotateX(float radians);
    @Bridge(symbol="GLKMatrixStackRotateY")
    public native void rotateY(float radians);
    @Bridge(symbol="GLKMatrixStackRotateZ")
    public native void rotateZ(float radians);
    /*</methods>*/
}
