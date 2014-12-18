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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNVector3/*</name>*/ 
    extends /*<extends>*/Struct<SCNVector3>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNVector3Ptr extends Ptr<SCNVector3, SCNVector3Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNVector3.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNVector3() {}
    public SCNVector3(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getX();
    @StructMember(0) public native SCNVector3 setX(float x);
    
    @Deprecated
    @StructMember(0) public native float x();
    @Deprecated
    @StructMember(0) public native SCNVector3 x(float x);
    
    @StructMember(1) public native float getY();
    @StructMember(1) public native SCNVector3 setY(float y);
    
    @Deprecated
    @StructMember(1) public native float y();
    @Deprecated
    @StructMember(1) public native SCNVector3 y(float y);
    
    @StructMember(2) public native float getZ();
    @StructMember(2) public native SCNVector3 setZ(float z);
    
    @Deprecated
    @StructMember(2) public native float z();
    @Deprecated
    @StructMember(2) public native SCNVector3 z(float z);
    
    /*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="SCNVector3Zero", optional=true)
    public static native @ByVal SCNVector3 Zero();
    
    public boolean equalsTo(SCNVector3 b) { return equalsTo(this, b); }
    @Bridge(symbol="SCNVector3EqualToVector3", optional=true)
    private static native boolean equalsTo(@ByVal SCNVector3 a, @ByVal SCNVector3 b);
    /*</methods>*/
}
