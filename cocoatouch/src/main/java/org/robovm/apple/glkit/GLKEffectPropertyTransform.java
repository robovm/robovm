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
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GLKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKEffectPropertyTransform/*</name>*/ 
    extends /*<extends>*/GLKEffectProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKEffectPropertyTransformPtr extends Ptr<GLKEffectPropertyTransform, GLKEffectPropertyTransformPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKEffectPropertyTransform.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKEffectPropertyTransform() {}
    protected GLKEffectPropertyTransform(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "modelviewMatrix")
    public native @ByVal GLKMatrix4 getModelviewMatrix();
    @Property(selector = "setModelviewMatrix:")
    public native void setModelviewMatrix(@ByVal GLKMatrix4 v);
    @Property(selector = "projectionMatrix")
    public native @ByVal GLKMatrix4 getProjectionMatrix();
    @Property(selector = "setProjectionMatrix:")
    public native void setProjectionMatrix(@ByVal GLKMatrix4 v);
    @Property(selector = "normalMatrix")
    public native @ByVal GLKMatrix3 getNormalMatrix();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
