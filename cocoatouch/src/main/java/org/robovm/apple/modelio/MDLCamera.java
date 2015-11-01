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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLCamera/*</name>*/ 
    extends /*<extends>*/MDLObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLCameraPtr extends Ptr<MDLCamera, MDLCameraPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLCamera.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLCamera() {}
    protected MDLCamera(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "projectionMatrix")
    public native MatrixFloat4x4 getProjectionMatrix();
    @Property(selector = "nearVisibilityDistance")
    public native float getNearVisibilityDistance();
    @Property(selector = "setNearVisibilityDistance:")
    public native void setNearVisibilityDistance(float v);
    @Property(selector = "farVisibilityDistance")
    public native float getFarVisibilityDistance();
    @Property(selector = "setFarVisibilityDistance:")
    public native void setFarVisibilityDistance(float v);
    @Property(selector = "worldToMetersConversionScale")
    public native float getWorldToMetersConversionScale();
    @Property(selector = "setWorldToMetersConversionScale:")
    public native void setWorldToMetersConversionScale(float v);
    @Property(selector = "barrelDistortion")
    public native float getBarrelDistortion();
    @Property(selector = "setBarrelDistortion:")
    public native void setBarrelDistortion(float v);
    @Property(selector = "fisheyeDistortion")
    public native float getFisheyeDistortion();
    @Property(selector = "setFisheyeDistortion:")
    public native void setFisheyeDistortion(float v);
    @Property(selector = "opticalVignetting")
    public native float getOpticalVignetting();
    @Property(selector = "setOpticalVignetting:")
    public native void setOpticalVignetting(float v);
    @Property(selector = "chromaticAberration")
    public native float getChromaticAberration();
    @Property(selector = "setChromaticAberration:")
    public native void setChromaticAberration(float v);
    @Property(selector = "focalLength")
    public native float getFocalLength();
    @Property(selector = "setFocalLength:")
    public native void setFocalLength(float v);
    @Property(selector = "focusDistance")
    public native float getFocusDistance();
    @Property(selector = "setFocusDistance:")
    public native void setFocusDistance(float v);
    @Property(selector = "fieldOfView")
    public native float getFieldOfView();
    @Property(selector = "setFieldOfView:")
    public native void setFieldOfView(float v);
    @Property(selector = "fStop")
    public native float getFStop();
    @Property(selector = "setFStop:")
    public native void setFStop(float v);
    @Property(selector = "apertureBladeCount")
    public native @MachineSizedUInt long getApertureBladeCount();
    @Property(selector = "setApertureBladeCount:")
    public native void setApertureBladeCount(@MachineSizedUInt long v);
    @Property(selector = "maximumCircleOfConfusion")
    public native float getMaximumCircleOfConfusion();
    @Property(selector = "setMaximumCircleOfConfusion:")
    public native void setMaximumCircleOfConfusion(float v);
    @Property(selector = "shutterOpenInterval")
    public native double getShutterOpenInterval();
    @Property(selector = "setShutterOpenInterval:")
    public native void setShutterOpenInterval(double v);
    @Property(selector = "sensorVerticalAperture")
    public native float getSensorVerticalAperture();
    @Property(selector = "setSensorVerticalAperture:")
    public native void setSensorVerticalAperture(float v);
    @Property(selector = "sensorAspect")
    public native float getSensorAspect();
    @Property(selector = "setSensorAspect:")
    public native void setSensorAspect(float v);
    @Property(selector = "sensorEnlargement")
    public native VectorFloat2 getSensorEnlargement();
    @Property(selector = "setSensorEnlargement:", strongRef = true)
    public native void setSensorEnlargement(VectorFloat2 v);
    @Property(selector = "sensorShift")
    public native VectorFloat2 getSensorShift();
    @Property(selector = "setSensorShift:", strongRef = true)
    public native void setSensorShift(VectorFloat2 v);
    @Property(selector = "flash")
    public native VectorFloat3 getFlash();
    @Property(selector = "setFlash:", strongRef = true)
    public native void setFlash(VectorFloat3 v);
    @Property(selector = "exposureCompression")
    public native VectorFloat2 getExposureCompression();
    @Property(selector = "setExposureCompression:", strongRef = true)
    public native void setExposureCompression(VectorFloat2 v);
    @Property(selector = "exposure")
    public native VectorFloat3 getExposure();
    @Property(selector = "setExposure:", strongRef = true)
    public native void setExposure(VectorFloat3 v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "frameBoundingBox:setNearAndFar:")
    public native void frameBoundingBox(@ByVal MDLAxisAlignedBoundingBox boundingBox, boolean setNearAndFar);
    @Method(selector = "lookAt:")
    public native void lookAt(VectorFloat3 focusPosition);
    @Method(selector = "lookAt:from:")
    public native void lookAt(VectorFloat3 focusPosition, VectorFloat3 cameraPosition);
    @Method(selector = "rayTo:forViewPort:")
    public native VectorFloat3 rayTo(VectorInt2 pixel, VectorInt2 size);
    @Method(selector = "bokehKernelWithSize:")
    public native MDLTexture newBokehKernel(VectorInt2 size);
    /*</methods>*/
}
