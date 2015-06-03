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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNVector4/*</name>*/ 
    extends /*<extends>*/Struct<SCNVector4>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNVector4Ptr extends Ptr<SCNVector4, SCNVector4Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNVector4.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNVector4() {}
    public SCNVector4(float x, float y, float z, float w) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setW(w);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getX();
    @StructMember(0) public native SCNVector4 setX(float x);
    @StructMember(1) public native float getY();
    @StructMember(1) public native SCNVector4 setY(float y);
    @StructMember(2) public native float getZ();
    @StructMember(2) public native SCNVector4 setZ(float z);
    @StructMember(3) public native float getW();
    @StructMember(3) public native SCNVector4 setW(float w);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="SCNVector4Zero", optional=true)
    public static native @ByVal SCNVector4 Zero();
    
    public boolean equalsTo(SCNVector4 b) { return equalsTo(this, b); }
    @Bridge(symbol="SCNVector4EqualToVector4", optional=true)
    private static native boolean equalsTo(@ByVal SCNVector4 a, @ByVal SCNVector4 b);
    /*</methods>*/
}
