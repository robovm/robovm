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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKMath/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(GLKMath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static GLKVector3 project(GLKVector3 object, GLKMatrix4 model, GLKMatrix4 projection, int[] viewport) {
        IntPtr ptr = new IntPtr();
        ptr.set(viewport);
        return project(object, model, projection, ptr);
    }
    public static GLKVector3 unproject(GLKVector3 window, GLKMatrix4 model, GLKMatrix4 projection, int[] viewport, BooleanPtr success) {
        IntPtr ptr = new IntPtr();
        ptr.set(viewport);
        return unproject(window, model, projection, ptr, success);
    }
    /*<methods>*/
    @Bridge(symbol="GLKMathProject", optional=true)
    private static native @ByVal GLKVector3 project(@ByVal GLKVector3 object, @ByVal GLKMatrix4 model, @ByVal GLKMatrix4 projection, IntPtr viewport);
    @Bridge(symbol="GLKMathUnproject", optional=true)
    private static native @ByVal GLKVector3 unproject(@ByVal GLKVector3 window, @ByVal GLKMatrix4 model, @ByVal GLKMatrix4 projection, IntPtr viewport, BooleanPtr success);
    /*</methods>*/
}
