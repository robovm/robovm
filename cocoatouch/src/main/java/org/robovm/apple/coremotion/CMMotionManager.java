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
package org.robovm.apple.coremotion;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreMotion") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMotionManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMMotionManagerPtr extends Ptr<CMMotionManager, CMMotionManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CMMotionManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMMotionManager() {}
    protected CMMotionManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "accelerometerUpdateInterval")
    public native double getAccelerometerUpdateInterval();
    @Property(selector = "setAccelerometerUpdateInterval:")
    public native void setAccelerometerUpdateInterval(double v);
    @Property(selector = "isAccelerometerAvailable")
    public native boolean isAccelerometerAvailable();
    @Property(selector = "isAccelerometerActive")
    public native boolean isAccelerometerActive();
    @Property(selector = "accelerometerData")
    public native CMAccelerometerData getAccelerometerData();
    @Property(selector = "gyroUpdateInterval")
    public native double getGyroUpdateInterval();
    @Property(selector = "setGyroUpdateInterval:")
    public native void setGyroUpdateInterval(double v);
    @Property(selector = "isGyroAvailable")
    public native boolean isGyroAvailable();
    @Property(selector = "isGyroActive")
    public native boolean isGyroActive();
    @Property(selector = "gyroData")
    public native CMGyroData getGyroData();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "magnetometerUpdateInterval")
    public native double getMagnetometerUpdateInterval();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setMagnetometerUpdateInterval:")
    public native void setMagnetometerUpdateInterval(double v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isMagnetometerAvailable")
    public native boolean isMagnetometerAvailable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isMagnetometerActive")
    public native boolean isMagnetometerActive();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "magnetometerData")
    public native CMMagnetometerData getMagnetometerData();
    @Property(selector = "deviceMotionUpdateInterval")
    public native double getDeviceMotionUpdateInterval();
    @Property(selector = "setDeviceMotionUpdateInterval:")
    public native void setDeviceMotionUpdateInterval(double v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "attitudeReferenceFrame")
    public native CMAttitudeReferenceFrame getAttitudeReferenceFrame();
    @Property(selector = "isDeviceMotionAvailable")
    public native boolean isDeviceMotionAvailable();
    @Property(selector = "isDeviceMotionActive")
    public native boolean isDeviceMotionActive();
    @Property(selector = "deviceMotion")
    public native CMDeviceMotion getDeviceMotion();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "showsDeviceMovementDisplay")
    public native boolean isShowsDeviceMovementDisplay();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setShowsDeviceMovementDisplay:")
    public native void setShowsDeviceMovementDisplay(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "startAccelerometerUpdates")
    public native void startAccelerometerUpdates();
    @Method(selector = "startAccelerometerUpdatesToQueue:withHandler:")
    public native void startAccelerometerUpdates(NSOperationQueue queue, @Block VoidBlock2<CMAccelerometerData, NSError> handler);
    @Method(selector = "stopAccelerometerUpdates")
    public native void stopAccelerometerUpdates();
    @Method(selector = "startGyroUpdates")
    public native void startGyroUpdates();
    @Method(selector = "startGyroUpdatesToQueue:withHandler:")
    public native void startGyroUpdates(NSOperationQueue queue, @Block VoidBlock2<CMGyroData, NSError> handler);
    @Method(selector = "stopGyroUpdates")
    public native void stopGyroUpdates();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startMagnetometerUpdates")
    public native void startMagnetometerUpdates();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startMagnetometerUpdatesToQueue:withHandler:")
    public native void startMagnetometerUpdates(NSOperationQueue queue, @Block VoidBlock2<CMMagnetometerData, NSError> handler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "stopMagnetometerUpdates")
    public native void stopMagnetometerUpdates();
    @Method(selector = "startDeviceMotionUpdates")
    public native void startDeviceMotionUpdates();
    @Method(selector = "startDeviceMotionUpdatesToQueue:withHandler:")
    public native void startDeviceMotionUpdates(NSOperationQueue queue, @Block VoidBlock2<CMDeviceMotion, NSError> handler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startDeviceMotionUpdatesUsingReferenceFrame:")
    public native void startDeviceMotionUpdates(CMAttitudeReferenceFrame referenceFrame);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startDeviceMotionUpdatesUsingReferenceFrame:toQueue:withHandler:")
    public native void startDeviceMotionUpdates(CMAttitudeReferenceFrame referenceFrame, NSOperationQueue queue, @Block VoidBlock2<CMDeviceMotion, NSError> handler);
    @Method(selector = "stopDeviceMotionUpdates")
    public native void stopDeviceMotionUpdates();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "availableAttitudeReferenceFrames")
    public static native @MachineSizedUInt long getAvailableAttitudeReferenceFrames();
    /*</methods>*/
}
