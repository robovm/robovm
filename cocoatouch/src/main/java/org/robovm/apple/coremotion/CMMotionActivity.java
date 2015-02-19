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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreMotion") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMotionActivity/*</name>*/ 
    extends /*<extends>*/CMLogItem/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMMotionActivityPtr extends Ptr<CMMotionActivity, CMMotionActivityPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CMMotionActivity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMMotionActivity() {}
    protected CMMotionActivity(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "confidence")
    public native CMMotionActivityConfidence getConfidence();
    @Property(selector = "startDate")
    public native NSDate getStartDate();
    @Property(selector = "unknown")
    public native boolean isUnknown();
    @Property(selector = "stationary")
    public native boolean isStationary();
    @Property(selector = "walking")
    public native boolean isWalking();
    @Property(selector = "running")
    public native boolean isRunning();
    @Property(selector = "automotive")
    public native boolean isAutomotive();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "cycling")
    public native boolean isCycling();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
