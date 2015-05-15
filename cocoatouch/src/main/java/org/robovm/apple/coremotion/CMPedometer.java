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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreMotion") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMPedometer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMPedometerPtr extends Ptr<CMPedometer, CMPedometerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CMPedometer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMPedometer() {}
    protected CMPedometer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "queryPedometerDataFromDate:toDate:withHandler:")
    public native void queryPedometerData(NSDate start, NSDate end, @Block VoidBlock2<CMPedometerData, NSError> handler);
    @Method(selector = "startPedometerUpdatesFromDate:withHandler:")
    public native void startPedometerUpdates(NSDate start, @Block VoidBlock2<CMPedometerData, NSError> handler);
    @Method(selector = "stopPedometerUpdates")
    public native void stopPedometerUpdates();
    @Method(selector = "isStepCountingAvailable")
    public static native boolean isStepCountingAvailable();
    @Method(selector = "isDistanceAvailable")
    public static native boolean isDistanceAvailable();
    @Method(selector = "isFloorCountingAvailable")
    public static native boolean isFloorCountingAvailable();
    /*</methods>*/
}
