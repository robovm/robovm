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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFaceFeature/*</name>*/ 
    extends /*<extends>*/CIFeature/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CIFaceFeaturePtr extends Ptr<CIFaceFeature, CIFaceFeaturePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIFaceFeature.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CIFaceFeature() {}
    protected CIFaceFeature(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "bounds")
    public native @ByVal CGRect getBounds();
    @Property(selector = "hasLeftEyePosition")
    public native boolean isHasLeftEyePosition();
    @Property(selector = "leftEyePosition")
    public native @ByVal CGPoint getLeftEyePosition();
    @Property(selector = "hasRightEyePosition")
    public native boolean isHasRightEyePosition();
    @Property(selector = "rightEyePosition")
    public native @ByVal CGPoint getRightEyePosition();
    @Property(selector = "hasMouthPosition")
    public native boolean isHasMouthPosition();
    @Property(selector = "mouthPosition")
    public native @ByVal CGPoint getMouthPosition();
    @Property(selector = "hasTrackingID")
    public native boolean isHasTrackingID();
    @Property(selector = "trackingID")
    public native int getTrackingID();
    @Property(selector = "hasTrackingFrameCount")
    public native boolean isHasTrackingFrameCount();
    @Property(selector = "trackingFrameCount")
    public native int getTrackingFrameCount();
    @Property(selector = "hasFaceAngle")
    public native boolean isHasFaceAngle();
    @Property(selector = "faceAngle")
    public native float getFaceAngle();
    @Property(selector = "hasSmile")
    public native boolean isHasSmile();
    @Property(selector = "leftEyeClosed")
    public native boolean isLeftEyeClosed();
    @Property(selector = "rightEyeClosed")
    public native boolean isRightEyeClosed();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
