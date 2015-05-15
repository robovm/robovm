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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMDeviceMotion/*</name>*/ 
    extends /*<extends>*/CMLogItem/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMDeviceMotionPtr extends Ptr<CMDeviceMotion, CMDeviceMotionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CMDeviceMotion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMDeviceMotion() {}
    protected CMDeviceMotion(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "attitude")
    public native CMAttitude getAttitude();
    @Property(selector = "rotationRate")
    public native @ByVal CMRotationRate getRotationRate();
    @Property(selector = "gravity")
    public native @ByVal CMAcceleration getGravity();
    @Property(selector = "userAcceleration")
    public native @ByVal CMAcceleration getUserAcceleration();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "magneticField")
    public native @ByVal CMCalibratedMagneticField getMagneticField();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
