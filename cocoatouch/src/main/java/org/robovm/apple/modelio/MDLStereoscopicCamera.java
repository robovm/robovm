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
package org.robovm.apple.modelio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("ModelIO") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLStereoscopicCamera/*</name>*/ 
    extends /*<extends>*/MDLCamera/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLStereoscopicCameraPtr extends Ptr<MDLStereoscopicCamera, MDLStereoscopicCameraPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLStereoscopicCamera.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLStereoscopicCamera() {}
    protected MDLStereoscopicCamera(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "interPupillaryDistance")
    public native float getInterPupillaryDistance();
    @Property(selector = "setInterPupillaryDistance:")
    public native void setInterPupillaryDistance(float v);
    @Property(selector = "leftVergence")
    public native float getLeftVergence();
    @Property(selector = "setLeftVergence:")
    public native void setLeftVergence(float v);
    @Property(selector = "rightVergence")
    public native float getRightVergence();
    @Property(selector = "setRightVergence:")
    public native void setRightVergence(float v);
    @Property(selector = "overlap")
    public native float getOverlap();
    @Property(selector = "setOverlap:")
    public native void setOverlap(float v);
    @Property(selector = "leftViewMatrix")
    public native MatrixFloat4x4 getLeftViewMatrix();
    @Property(selector = "rightViewMatrix")
    public native MatrixFloat4x4 getRightViewMatrix();
    @Property(selector = "leftProjectionMatrix")
    public native MatrixFloat4x4 getLeftProjectionMatrix();
    @Property(selector = "rightProjectionMatrix")
    public native MatrixFloat4x4 getRightProjectionMatrix();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
