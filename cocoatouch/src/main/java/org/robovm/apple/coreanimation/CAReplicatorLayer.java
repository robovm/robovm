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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAReplicatorLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAReplicatorLayerPtr extends Ptr<CAReplicatorLayer, CAReplicatorLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAReplicatorLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAReplicatorLayer() {}
    protected CAReplicatorLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "instanceCount")
    public native @MachineSizedSInt long getInstanceCount();
    @Property(selector = "setInstanceCount:")
    public native void setInstanceCount(@MachineSizedSInt long v);
    @Property(selector = "preservesDepth")
    public native boolean preservesDepth();
    @Property(selector = "setPreservesDepth:")
    public native void setPreservesDepth(boolean v);
    @Property(selector = "instanceDelay")
    public native double getInstanceDelay();
    @Property(selector = "setInstanceDelay:")
    public native void setInstanceDelay(double v);
    @Property(selector = "instanceTransform")
    public native @ByVal CATransform3D getInstanceTransform();
    @Property(selector = "setInstanceTransform:")
    public native void setInstanceTransform(@ByVal CATransform3D v);
    @Property(selector = "instanceColor")
    public native CGColor getInstanceColor();
    @Property(selector = "setInstanceColor:")
    public native void setInstanceColor(CGColor v);
    @Property(selector = "instanceRedOffset")
    public native float getInstanceRedOffset();
    @Property(selector = "setInstanceRedOffset:")
    public native void setInstanceRedOffset(float v);
    @Property(selector = "instanceGreenOffset")
    public native float getInstanceGreenOffset();
    @Property(selector = "setInstanceGreenOffset:")
    public native void setInstanceGreenOffset(float v);
    @Property(selector = "instanceBlueOffset")
    public native float getInstanceBlueOffset();
    @Property(selector = "setInstanceBlueOffset:")
    public native void setInstanceBlueOffset(float v);
    @Property(selector = "instanceAlphaOffset")
    public native float getInstanceAlphaOffset();
    @Property(selector = "setInstanceAlphaOffset:")
    public native void setInstanceAlphaOffset(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
